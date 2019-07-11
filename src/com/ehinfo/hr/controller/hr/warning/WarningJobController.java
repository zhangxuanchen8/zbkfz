package com.ehinfo.hr.controller.hr.warning;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.Employee;
import com.ehinfo.hr.entity.task.base.ScheduleJob;
import com.ehinfo.hr.service.hr.warning.WarningConService;
import com.ehinfo.hr.service.hr.warning.WarningService;
import com.ehinfo.hr.service.task.base.ScheduleJobService;

public class WarningJobController  extends BaseController<BaseDict> implements Job{
	@Autowired
	private ScheduleJobService service;
	@Autowired
	private WarningService warnService;
	@Autowired
	private WarningConService warnConService;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public void execute(JobExecutionContext arg0) throws JobExecutionException {  
        //更新上一次执行时间和下一次计划执行时间  
    	
          
        //业务逻辑  
       // System.out.println(uploadMap.get("jobStart"));
		String jobGroup = arg0.getTrigger().getJobKey().getGroup();
		String jobName = arg0.getTrigger().getJobKey().getName();
		ScheduleJob job = new ScheduleJob();
		job.setJobGroup(jobGroup);
		job.setJobName(jobName);
		logger.error("执行"+jobName+"-定时器");
		List<ScheduleJob> jobs = service.find(job);
		if(jobs.size()==1){
			job = jobs.get(0);
			if(job!=null&&Tools.notEmpty(job.getDescription())){
				Warning warn = new Warning();
				warn.setId(job.getDescription());
				warn = warnService.findById(warn);
				if(warn!=null){
					//--------放入的内容有重复的，保留以前的，还要删除以前存在的，现在没有的--------
					//1、先根据预警的id查出这个预警定义对应的预警记录
					List<WarningCon> listWc= warnConService.findByPid(warn.getId());
					if(listWc!=null&&listWc.size()>0){
						for(WarningCon o:listWc){
							o.setValid("H");
							warnConService.update(o);
						}
					}
					//------------分个人和单位的预警---------------------------
					//下面的是个人的
					if("个人".equals(warn.getWarn_forward())){
						Employee e = new Employee();
						e.setRemark("and p_id in ("+warn.getGetwarn_sql()+") and hosnum in(select hosnum from(with cs (hosnum,SUPUNIT,hosname) as (select hosnum,SUPUNIT,hosname  from bas_hospitals where SUPUNIT = '"+warn.getHosnum()+"' union all select  e.hosnum,e.SUPUNIT,e.hosname  from cs t, bas_hospitals e where t.hosnum = e.SUPUNIT)select * from cs union all select hosnum,SUPUNIT,hosname  from bas_hospitals where hosnum = '"+warn.getHosnum()+"'))");
								
						Page<Employee> page = new Page<Employee>();
						page.setPageNum(1);
						page.setPageSize(10000);
						//page = empService.findByPage(e,page);
						List<Employee> emps = page.getResults();
						if(emps!=null&&emps.size()>0){
							for(Employee n:emps){
								boolean flagAdd = true;
								if(listWc!=null&&listWc.size()>0){
									for(int i=0;i< listWc.size();i++){
										if(warn.getId().equals(listWc.get(i).getWarn_id())&&n.getP_id().equals(listWc.get(i).getForwardid())){
											flagAdd=false;
											//以前有的现在也有的，采用以前的
											listWc.get(i).setValid("Y");
											warnConService.update(listWc.get(i));
											listWc.remove(i);
										}
									}
								}
								if(flagAdd==true){
									//以前没有，现在有的，添加
									WarningCon wc = new WarningCon();
									wc.setId(UUID.randomUUID().toString());
									wc.setForwardid(n.getP_id());
									wc.setValid("Y");
									wc.setWarn_get_time(new Date());
									wc.setWarn_forward(warn.getWarn_forward());
									wc.setWarn_role(warn.getForward_role());
									wc.setWarn_type(warn.getWarn_type());
									wc.setWarn_name(warn.getWarn_name());
									wc.setWarn_status("W");
									wc.setWarn_id(warn.getId());
									warnConService.insert(wc);
								}
							}
						}
					}else{
						//----------单位的预警现在不晓得有些什么，有了以后在写
					}
					
				}
			}
			
		}
    }  
	
}
