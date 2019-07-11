package com.ehinfo.hr.controller.hr.warning;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.entity.system.employee.Employee;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.task.base.ScheduleJob;
import com.ehinfo.hr.service.hr.warning.WarningConService;
import com.ehinfo.hr.service.hr.warning.WarningService;
import com.ehinfo.hr.service.task.base.ScheduleJobService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/warning/")

		 
public class WarningController extends BaseController<Warning> implements Job {
	
	@Autowired
	private WarningService service;
	@Autowired
	private ScheduleJobService jobService;
	@Autowired
	private WarningConService warnConService;

	
	@RequestMapping("WarningAdd")
	public String WarningList(ModelMap model ,HttpServletRequest request,HttpServletResponse response,Warning o){
		if(o!=null&&Tools.notEmpty(o.getId())){
			o = service.findById(o);
		}
		
		model.put("theWarning", o);
		return "ehr/warning/warningAddForm";
	}
	
	
	
					
	@RequestMapping("warningIndex")
	public String WarningIndex(ModelMap model ,HttpServletRequest request,HttpServletResponse response,Warning o,String pageNum,String numPerPage,String orderField,String orderDirection ){
		//-------------初始化page----------------
		BasUser user = (BasUser)request.getSession().getAttribute(Const.SESSION_USER);
		String hosnum=user.getHosnum();
		Page<Warning> page = new Page<Warning>();
		page.setPageNum((int)Integer.parseInt(Tools.isEmpty(pageNum)?"1":pageNum));
		page.setPageSize((int)Integer.parseInt(Tools.isEmpty(numPerPage)?"30":numPerPage));
		orderField = Tools.isEmpty(orderField)?"operatdate":orderField;
		orderDirection = Tools.isEmpty(orderDirection)?"desc":orderDirection;
		model.put("orderField", orderField);
		model.put("orderDirection", orderDirection);
		//------------------------------------------------------------
		page.setOrder(orderField+" "+orderDirection);
		o.setHosnum(hosnum);
		page = service.findByPage(o, page);
		model.put("page", page);
		model.put("warning", o);
		return "ehr/warning/warningTable";
	}
	
	@RequestMapping("add")
	public void WarningAdd(Warning o,HttpServletRequest request,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			BasUser user = (BasUser)request.getSession().getAttribute(Const.SESSION_USER);
			o.setHosnum(user.getHosnum());
			o.setOperator(user.getName());
			if(Tools.isEmpty(o.getId())){
				o.setId(get32UUID());
				service.insert(o);
			}else{
				service.update(o);
			}
			
			pw.write(EhUtil.createcallbackjson("200", "保存成功","", "","closeCurrent", "","",""));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			pw.write(EhUtil.createcallbackjson("300", "保存失败","", "","", "","",""));
		}
	}
	@RequestMapping("delBatch")
	public void delBatch(String ids,HttpServletRequest request,HttpServletResponse response){
		PrintWriter pw = null;
		List<Warning> list = new ArrayList<Warning>();
		try {
			pw = response.getWriter();
			if(Tools.notEmpty(ids)){
				String [] id = ids.split(",");
				for(String s:id){
					Warning o = new Warning();
					o.setId(s);
					list.add(o);
				}
			}
			service.deleteBatch(list);
			pw.write(EhUtil.createcallbackjson("200", "保存成功","", "","", "","",""));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			pw.write(EhUtil.createcallbackjson("300", "保存失败","", "","", "","",""));
		}
	}
	@RequestMapping("findWarn_name")
	@ResponseBody
	public void findWarn_name(String ids,HttpServletRequest request,HttpServletResponse response){
		String warn_id = request.getParameter("warn_id");
		PrintWriter pw =null;
		try {
			pw= response.getWriter();
			List<WarningCon> warningcon =new ArrayList<WarningCon>();
			if(warn_id!=null&&!"".equals(warn_id)){
				String[] warn_ids=warn_id.split(",");
				warningcon=service.findWarnName(warn_ids);
			}
			pw.print(JSONArray.fromObject(warningcon).toString());
			pw.flush();
	        pw.close();
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}



	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("预警的定时器");
		String jobGroup = arg0.getTrigger().getJobKey().getGroup();
		String jobName = arg0.getTrigger().getJobKey().getName();
		ScheduleJob job = new ScheduleJob();
		job.setJobGroup(jobGroup);
		job.setJobName(jobName);
		
		List<ScheduleJob> jobs = jobService.find(job);
		if(jobs.size()==1){
			job = jobs.get(0);
			if(job!=null&&Tools.notEmpty(job.getDescription())){
				Warning warn = new Warning();
				warn.setId(job.getDescription());
				warn = service.findById(warn);
				if(warn!=null){
					Employee e = new Employee();
					Page<Employee> page = new Page<Employee>();
					page.setPageNum(0);
					page.setPageSize(100000);
					
					e.setRemark(warn.getGetwarn_sql());
				//	page = empService.findByPage(e,page);
					List<Employee> emps = page.getResults();
					//--------放入的内容有重复的，保留以前的，还要删除以前存在的，现在没有的--------
					//1、先根据预警的id查出这个预警定义对应的预警记录
					List<WarningCon> listWc= warnConService.findByPid(warn.getId());
					
					
					
					if(emps.size()>0){
						for(WarningCon con:listWc){
							
						}
						
						
						//2、比对，
						
						//以前的有的现在没有的，用参数控制其是否使失效
						
						//以前有的现在也有的，采用以前的
						
						//以前没有，现在有的，添加
						
					}
				}
			}
			
		}
		
	}
	
}
