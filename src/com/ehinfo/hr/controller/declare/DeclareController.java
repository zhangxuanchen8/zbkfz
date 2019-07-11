package com.ehinfo.hr.controller.declare;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.CreateExcelMoBusiness;
import com.ehinfo.hr.common.utils.DateUtils;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.declare.Declare;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.Employee;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.declare.DeclareService;
import com.ehinfo.hr.service.declare.PersonalRecordService;
import com.ehinfo.hr.service.system.dict.BaseDictService;
import com.ehinfo.hr.service.system.org.HospitalService;
import com.ehinfo.hr.service.system.user.CpUserService;
import com.ehinfo.hr.service.zhibiao.zbk_fjService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/backstage/declare")
public class DeclareController {

	@Autowired
	private DeclareService declareService;
	@Autowired
	private PersonalRecordService perService;
    @Autowired
    private BaseDictService dictService;
    @Autowired
	private zbk_fjService fjService;
	@Autowired
	public HospitalService hosservice;
	/**
	 * 申请的主页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "/ehr/declare/declareList";
	}

	/**
	 * table的内容
	 * 
	 * @param model
	 * @param request
	 * @param pageNum
	 * @param numPerPage
	 * @param dept_declareList
	 * @return
	 */
	@RequestMapping("readyTable")
	public String readyTable(ModelMap model, HttpSession session, HttpServletRequest request, String pageNum,
			String numPerPage, String dept_declareList, String candidate, String code, String dept_declareOne) {
		
		List<Employee> emps = this.candidate(candidate);
		// 如果存在就移除
		if (Tools.notEmpty(dept_declareOne)) {
			Declare declare = new Declare();
			declare.setP_id(dept_declareOne);
			declare.setYear(DateUtils.getDate("yyyy"));
			Declare byPidYear = declareService.getByPidYear(declare);
			if (byPidYear != null) {
				
				Iterator<Employee> iterator = emps.iterator();
				while (iterator.hasNext()) {
					Employee employee = iterator.next();
					if (employee.getP_id().equals(byPidYear.getP_id())) {
						iterator.remove();
						model.put("dept_declareMessage", "该用户已经申报过，存在申报列中");
					}
				}
				candidate = this.candidateListToString(emps);
			}
		}

		// page分页
		if (numPerPage == null || "".equals(numPerPage)) {
			numPerPage = "30";
		}
		if (pageNum == null || "".equals(pageNum)) {
			pageNum = "1";
		}
		Page<Employee> page = new Page<Employee>();
		page.setPageNum(Integer.parseInt(pageNum));
		page.setPageSize(Integer.parseInt(numPerPage));

		// 根据部门id查询出该部门的所以人员
		Employee employee = new Employee();
		employee.setDepartmentid(dept_declareList);// 添加部门id

		if (Tools.notEmpty(code))
			employee.setKeyWord("%" + code + "%");// 添加模糊查询条件

		//Page<Employee> findByPage = service.findByPage(employee, page);
		//page = this.candidateTable(findByPage, emps, candidate);
		// 返回信息
		model.put("declareListPage", page);
		model.put("dept_declareList", dept_declareList);
		// model.put("dept_declareList",
		// findByPage);//结果在page中results，前段使用results

		 if (candidate == null) candidate = ""; 
		model.put("candidate", candidate);
		model.put("code", code);
		model.put("candidateDetails", emps);// 选中 人员的信息
		// 在表格中显示 
		//model.put("declareError", declareError(session));// 选中 人员的信息错误
		//model.put("declareSuccessGoto", declareSuccessGoto(session));// 选中 人员的信息精心
		//model.put("declareSuccess", declareSuccess(session));// 选中 人员的信息
		return "/ehr/declare/declareReady";
	}

	// 上传不成功
	private List<Declare> declareError(HttpSession session) {
		List<Declare> find = null;
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		if (a != null && Tools.notEmpty(a.getHosnum())) {
			String year = DateUtils.getDate("yyyy");
			Declare declare = new Declare();
			declare.setHosnum(a.getHosnum());
			declare.setYear(year);
			declare.setDeclare_state(Declare.DECLARE_RESULT0);
			find = declareService.find(declare);
		}
		return find;
	}

	// 上传中
	private List<Declare> declareSuccessGoto(HttpSession session) {
		List<Declare> find = null;
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		if (a != null && Tools.notEmpty(a.getHosnum())) {
			String year = DateUtils.getDate("yyyy");
			Declare declare = new Declare();
			declare.setHosnum(a.getHosnum());
			declare.setYear(year);
			declare.setDeclare_state(Declare.DECLARE_RESULT2);
			find = declareService.find(declare);
		}
		return find;
}

	// 上传成功
	private List<Declare> declareSuccess(HttpSession session) {
		List<Declare> find = null;
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		if (a != null && Tools.notEmpty(a.getHosnum())) {
			String year = DateUtils.getDate("yyyy");
			Declare declare = new Declare();
			declare.setHosnum(a.getHosnum());
			declare.setYear(year);
			declare.setDeclare_state(Declare.DECLARE_RESULT1);
			find = declareService.find(declare);
		}
		return find;
	}

	/**
	 * 处理选中人员信息，
	 * 
	 * @param candidate
	 * @return
	 */
	private String candidateListToString(List<Employee> candidate) {
		if(candidate!=null){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(",");
		for (Employee employee : candidate) {
			stringBuilder.append(employee.getP_id());
			stringBuilder.append(",");
		}

		String string = stringBuilder.toString();
		//String substring = string.substring(0, string.length() - 1);
		return string;
		}else{
			return "";
		}
		
	}

	/**
	 * 处理选中人员信息，
	 * 
	 * @param candidate
	 * @return
	 */
	private List<Employee> candidate(String candidate) {
		String pids[] = null;
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp1 = null;
		// 获取选中人员信息
		if (candidate != null && !"".equals(candidate) && candidate.indexOf(",") > -1) {
			pids = candidate.substring(1, candidate.length()).split(",");
			for (int i = 0; i < pids.length; i++) {
				emp1 = new Employee();
				emp1.setP_id(pids[i]);
				emps.add(emp1);
			}
		}
		if (emps.size() > 0) {
		//	emps = service.findByPids(emps);
		}

		return emps;
	}

	/**
	 * 处理table中选中人员变色
	 * 
	 * @param emps
	 * @param findByPage
	 * @param candidate
	 * @return
	 */
	private Page<Employee> candidateTable(Page<Employee> findByPage, List<Employee> emps, String candidate) {
		if (findByPage == null || findByPage.getResults() == null) {
			return findByPage;
		}
		Iterator<Employee> iterator = findByPage.getResults().iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if (candidate != null && candidate.indexOf("," + employee.getP_id() + ",") > -1) {
				employee.setSelected("1");
			}
		}
		return findByPage;
	}

	/*************************************************************************************************/
	/**
	 * 批量申报
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/declareInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> declareInfo(HttpSession session,HttpServletRequest request, HttpServletResponse response,
			String candidate) {
		List<Declare> declareError = this.declareError(session);
		List<Employee> emps = this.candidate(candidate);
		
		List<Declare> arrayList = new ArrayList<Declare>();
		Declare declare;
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		String year = DateUtils.getDate("yyyy");
		for (Employee employee : emps) {
			declare = new Declare();
			declare.setD_id(UuidUtil.get32UUID());
			declare.setP_id(employee.getP_id());
			declare.setC_name(employee.getC_name());
			declare.setP_no(employee.getP_no());
			declare.setId_card(employee.getId_card());
			declare.setDeclare_date(date);
			declare.setDeclare_state(Declare.DECLARE_STATE2);
			declare.setYear(year);
			arrayList.add(declare);
		}
		List<Declare> addList = declareService.addList(arrayList);// 上传使用addList
		addList.addAll(declareError);//合并
		if (addList != null) {
			
		}
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	// 一个上传（预留）
	@RequestMapping(value = "/declareInfo_one", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> declareInfo_one(HttpServletRequest request, HttpServletResponse response,
			String dept_declareOneView, String pageNum, String numPerPage, String dept_declareListView, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		Employee emp1 = new Employee();
		emp1.setP_id(dept_declareOneView);
		//Employee employee = service.findByPid(emp1);
		Declare declare = new Declare();
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		String year = DateUtils.getDate("yyyy");

		declare.setD_id(UuidUtil.get32UUID());
		//declare.setP_id(employee.getP_id());
		//declare.setC_name(employee.getC_name());
		//declare.setP_no(employee.getP_no());
		//declare.setId_card(employee.getId_card());
		//declare.setDeclare_date(date);
		declare.setDeclare_state(Declare.DECLARE_STATE2);
		declare.setYear(year);

		Declare addOne = declareService.addOne(declare);// 上传使用addList
		if (addOne != null) {
			// 上传
		}

		return map;

	}

	/**
	 * 查看上传人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		BaseDict dict2 = new BaseDict();
		dict2.setNekey(2);
		 List<BaseDict> dicts2 = dictService.selectDictByNekeys(dict2);
	        BaseDict dict15 = new BaseDict();
	        dict15.setNekey(15);
	        List<BaseDict> dicts15 = dictService.selectDictByNekeys(dict15);
	        model.put("dicts2", dicts2);
	        model.put("dicts15", dicts15);
		return "/ehr/declare/declareListView";
	}

	/**
	 * 获取数据库年份
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getYear")
	public void getYear(HttpServletRequest request, HttpServletResponse response) {
		List<Declare> year = declareService.getYear();
		try {
			List<ZNodes> r = new ArrayList<ZNodes>();
			ZNodes zNodes;
			for (Declare declare : year) {
				zNodes = new ZNodes();
				zNodes.setpId("zxc");
				zNodes.setId(declare.getDeclare_date());
				zNodes.setName(declare.getDeclare_date());
				r.add(zNodes);
			}
			zNodes =new ZNodes();
			zNodes.setId("zxc");
			zNodes.setpId("");
			zNodes.setName("年度");
			r.add(zNodes);
			JSONArray jsons = JSONArray.fromObject(r);
			response.getWriter().print(jsons.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传视图table的内容
	 * 
	 * @param model
	 * @param request
	 * @param pageNum
	 * @param numPerPage
	 * @param dept_declareList
	 * @return
	 */
	@RequestMapping("readyTableView")
	public String readyTableView(ModelMap model, HttpSession session, HttpServletRequest request, String pageNum,
			String numPerPage, String dept_declareListView, String code,String tjzw,String xcszy) {
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		// page分页
		if (numPerPage == null || "".equals(numPerPage)) {
			numPerPage = "30";
		}
		if (pageNum == null || "".equals(pageNum)) {
			pageNum = "1";
		}
		BaseDict dict2 = new BaseDict();
		dict2.setNekey(2);
		List<BaseDict> dicts2 = dictService.selectDictByNekeys(dict2);
        BaseDict dict15 = new BaseDict();
        dict15.setNekey(15);
        List<BaseDict> dicts15 = dictService.selectDictByNekeys(dict15);
        model.put("dicts2", dicts2);
        model.put("dicts15", dicts15);
		Page<PersonalRecord> page = new Page<PersonalRecord>();
		page.setPageNum(Integer.parseInt(pageNum));
		page.setPageSize(Integer.parseInt(numPerPage));
		PersonalRecord per = new PersonalRecord();
		per.setUnitid(a.getHosnum());
		per.setXm(code);
		per.setYear(dept_declareListView);
		per.setTjhrzzg(tjzw);
		per.setXcszy(xcszy);
		page = perService.findByPage1(per, page);
		List<zbk_fj> o =new ArrayList<zbk_fj>();
		zbk_fj fj = new zbk_fj();
		if(page.getResults()!=null){
			for(int i=0;i<page.getResults().size();i++){
				fj.setHosnum(a.getHosnum());
				fj.setUser_id(page.getResults().get(i).getRecordno());
				o=fjService.findByid(fj);
				List<String> list = new ArrayList<String>();
				String fjsize="";
				if(o.size()>0){
					for(int k=0;k<o.size();k++){
						fjsize+=o.get(k).getZbk_fj();
					}
					String[] chk =fjsize.split(",");
					page.getResults().get(i).setFjsize(chk.length+"");
				}
			}
		}
		model.put("declareListViewPage", page);
		model.put("dept_declareListView", dept_declareListView);
		model.put("code", code);
		model.put("tjzw", tjzw);
		model.put("xcszy", xcszy);
		return "/ehr/declare/declareReadyView";
	}

	
	@RequestMapping(value = "/ryindex")
	public String ryindex(ModelMap model,HttpServletRequest request, HttpServletResponse response,String pageNum,String numPerPage,String name,String modelid,String tjdate,String scbj,String option02,String prqx,String flag,String biaozhi,String statu) {
		PersonalRecord per = new PersonalRecord();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		per.setUnitid(a.getHosnum());
		Page<PersonalRecord> p = new Page<PersonalRecord>();
		if(Tools.notEmpty(pageNum)){
			p.setPageNum(Integer.parseInt(pageNum));
		}else{
			p.setPageNum(1);
		}
		if(Tools.notEmpty(numPerPage)){
			p.setPageSize(Integer.parseInt(numPerPage));
		}else{
			p.setPageSize(30);
		}
		per.setXm(name);
		per.setP_id(modelid);
		per.setTjdate(tjdate);
		per.setScbj(scbj);
		per.setOption02(option02);
		per.setPrqx(prqx);
		per.setStatu(statu);
		//per.setFlag(flag);
		p = perService.findByPage1(per, p);
		List<zbk_fj> o =new ArrayList<zbk_fj>();
		zbk_fj fj = new zbk_fj();
		if(p.getResults()!=null){
			for(int i=0;i<p.getResults().size();i++){
				fj.setHosnum(a.getHosnum());
				fj.setUser_id(p.getResults().get(i).getRecordno());
				o=fjService.findByid(fj);
				List<String> list = new ArrayList<String>();
				String fjsize="";
				if(o.size()>0){
					for(int k=0;k<o.size();k++){
						fjsize+=o.get(k).getZbk_fj();
					}
					String[] chk =fjsize.split(",");
					p.getResults().get(i).setFjsize(chk.length+"");
				}
			}
		}
		model.put("flag", flag);
		model.put("biaozhi", biaozhi);
		model.put("prqx", prqx);
		model.put("option02", option02);
		model.put("scbj", scbj);
		model.put("tjdate", tjdate);
		model.put("modelid", modelid);
		model.put("page", p);
		model.put("numPerPage",p.getPageSize());
		model.put("pageNum", p.getPageNum());
		model.put("totalcount", p.getTotalRecord());
		model.put("name", name);
		model.put("statu", statu);
		return "/ehr/declare/declareRyList";
	}

	@RequestMapping(value = "impDialog")
	public String impDialog(ModelMap model, HttpServletRequest request, HttpServletResponse response,String flag,String statu) {
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		model.put("hosnum", a.getHosnum());
		Hospital hos = new Hospital();
		hos.setHosnum(a.getHosnum());
		hos = hosservice.findById(hos);
		model.put("hos", hos);
		model.put("flag", flag);
		model.put("statu", statu);
		return "/ehr/declare/declareRyDialog";
	}
	@RequestMapping(value="saveBatch",method=RequestMethod.POST)
	@ResponseBody
	public void  saveBatch(String ryInfo,HttpServletResponse response,HttpServletRequest request,String hosnum,String flag,String jgzxid){
		PrintWriter pw=null;	
		JSONArray array = JSONArray.fromObject(ryInfo);
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		try {
			pw = response.getWriter();
			Calendar year=Calendar.getInstance();
			List<PersonalRecord> list = new ArrayList<PersonalRecord>();
			Iterator it = array.iterator();
			PersonalRecord o =null;
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			BaseDict dict2 = null;
			while(it.hasNext()){
				o = new PersonalRecord();
				JSONObject ob = (JSONObject)it.next();
				o.setXm(ob.getString("xm"));
				o.setXb(ob.getString("xb"));
				o.setCsny("");
				o.setZzmm("");
				o.setXl("");
				o.setXszw("");
				o.setXzzw("");
				o.setZgxl("");
				
				if(Tools.notEmpty(ob.getString("xcszy"))){
					 dict2 = new BaseDict();
			        dict2.setNekey(2);
			        dict2.setContents(ob.getString("xcszy"));
			        List<BaseDict> dicts2 = dictService.selectDictByNekeys(dict2);
			        if(dicts2!=null && dicts2.size()>0){
			        	o.setXcszy(dicts2.get(0).getNevalue());
			        }else{
			        	o.setXcszy("Y");
			        }
				}else{
					o.setXcszy("Y");
				}
				o.setCszygznx("");
				o.setXrzzgmc("");
				o.setXprzw("");
				o.setIdcard(ob.getString("idcard"));
				o.setZzzt("");
				o.setXw("");
				o.setYzbm("");
				o.setYear("");
				o.setZcfw("");
				o.setUnitid(hosnum);
				o.setCurrent_manage_unit(jgzxid);
				//if("Y".equals(flag)){
				o.setTjhrzzg(ob.getString("tjhrzzg"));
				//}
				String no = perService.getPerseq();
				String year1 = year.get(Calendar.YEAR)+"";
				o.setYear(year1);
				o.setRecordno(year1.substring(0,4)+hosnum+no);
				list.add(o);
			}
			List<String> list1 = new ArrayList();
			list1=perService.insertBatch1(list);
			if(list1.size()>0){
				response.getWriter().write(EhUtil.createcallbackjson("200", "导入成功,"+list1+"人员已有,未能导入","", "","", "","",""));
			}else{
				response.getWriter().write(EhUtil.createcallbackjson("200", "导入成功","", "","", "","",""));
			}
		}catch (Exception e) {
			try {
				e.printStackTrace();
				response.getWriter().write(EhUtil.createcallbackjson("300", "导入失败","", "","", "","",""));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="fjscym")
	public String  beforeSynergy(ModelMap model,HttpServletRequest res,HttpServletResponse rep,String statu){
		model.put("statu", statu);
		return "/ehr/declare/declareRyscsj";
	}
	
	@RequestMapping(value = "/ryview")
	public String ryview(ModelMap model,HttpServletRequest request, HttpServletResponse response,String pageNum,String numPerPage) {
		PersonalRecord per = new PersonalRecord();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		per.setUnitid(a.getHosnum());
		Page<PersonalRecord> p = new Page<PersonalRecord>();
		if(Tools.notEmpty(pageNum)){
			p.setPageNum(Integer.parseInt(pageNum));
		}else{
			p.setPageNum(1);
		}
		if(Tools.notEmpty(numPerPage)){
			p.setPageSize(Integer.parseInt(numPerPage));
		}else{
			p.setPageSize(30);
		}
		p = perService.findByPage1(per, p);
		model.put("page", p);
		model.put("numPerPage",p.getPageSize());
		model.put("pageNum", p.getPageNum());
		model.put("totalcount", p.getTotalRecord());
		return "/ehr/declare/declareRyList1";
	}
	
	@RequestMapping(value = "expModel")
	public void expModel(HttpServletRequest request, HttpServletResponse response) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			HttpSession session = request.getSession();
			BasUser user = (BasUser) session.getAttribute(Const.SESSION_USER);
			String hosnum = user.getHosnum();
			BaseDict dict09 = new BaseDict();
			dict09.setNekey(9);
			dict09.setHosnum(hosnum);
	        List<BaseDict> dicts09 = dictService.selectDictByNekey(dict09);//学历
	        BaseDict dict16 = new BaseDict();
	        dict16.setNekey(16);
	        dict16.setHosnum(hosnum);
	        List<BaseDict> dicts16 = dictService.selectDictByNekey(dict16);//政治面貌
	        BaseDict dict95 = new BaseDict();
	        dict95.setNekey(95);
	        dict95.setHosnum(hosnum);
	        List<BaseDict> dicts95 = dictService.selectDictByNekey(dict95);//现从事专业
	        BaseDict dict96 = new BaseDict();
	        dict96.setNekey(96);
	        dict96.setHosnum(hosnum);
	        List<BaseDict> dicts96 = dictService.selectDictByNekey(dict96);//现任职资格名称
	        BaseDict dict39 = new BaseDict();
	        dict39.setNekey(39);
	        dict39.setHosnum(hosnum);
	        List<BaseDict> dicts39 = dictService.selectDictByNekey(dict39);//在职状态
	        BaseDict dict4 = new BaseDict();
	        dict4.setNekey(4);
	        dict4.setHosnum(hosnum);
	        List<BaseDict> dicts4 = dictService.selectDictByNekey(dict4);//学位
	        BaseDict dict52 = new BaseDict();
	        dict52.setNekey(52);
	        dict52.setHosnum(hosnum);
	        List<BaseDict> dicts52 = dictService.selectDictByNekey(dict52);//执业范围
	        BaseDict dict0 = new BaseDict();
	        dict0.setNekey(0);
	        dict0.setHosnum(hosnum);
	        List<BaseDict> dicts0 = dictService.selectDictByNekey(dict0);//执业范围
	        
	        
	        BaseDict dict2 = new BaseDict();
	        dict2.setNekey(2);
	        List<BaseDict> dicts2 = dictService.selectDictByNekeys(dict2);
	        BaseDict dict15 = new BaseDict();
	        dict15.setNekey(15);
	        List<BaseDict> dicts15 = dictService.selectDictByNekeys(dict15);
	        String zy[] = new String[dicts2.size()];
	        for (int i = 0; i < dicts2.size(); i++) {
	        	zy[i] = dicts2.get(i).getContents();
            }
	        String zw[] = new String[dicts15.size()];
	        for (int i = 0; i < dicts15.size(); i++) {
	        	zw[i] = dicts15.get(i).getContents();
            }
	        
	        
	        String xb[] = new String[dicts0.size()];
	        for (int i = 0; i < dicts0.size(); i++) {
	        	xb[i] = dicts0.get(i).getContents();
            }
	        String xl[] = new String[dicts09.size()];
	        for (int i = 0; i < dicts09.size(); i++) {
	        	xl[i] = dicts09.get(i).getContents();
            }
	        String zzmm[] = new String[dicts16.size()];
	        for (int i = 0; i < dicts16.size(); i++) {
	        	zzmm[i] = dicts16.get(i).getContents();
            }
	        String xcszy[] = new String[dicts95.size()];
	        for (int i = 0; i < dicts95.size(); i++) {
	        	xcszy[i] = dicts95.get(i).getContents();
            }
	        String xrzzgmc[] = new String[dicts96.size()];
	        for (int i = 0; i < dicts96.size(); i++) {
	        	xrzzgmc[i] = dicts96.get(i).getContents();
            }
	        String zzzt[] = new String[dicts39.size()];
	        for (int i = 0; i < dicts39.size(); i++) {
	        	zzzt[i] = dicts39.get(i).getContents();
            }
	        String xw[] = new String[dicts4.size()];
	        for (int i = 0; i < dicts4.size(); i++) {
	        	xw[i] = dicts4.get(i).getContents();
            }
	        String zcfw[] = new String[dicts52.size()];
	        for (int i = 0; i < dicts52.size(); i++) {
	        	zcfw[i] = dicts52.get(i).getContents();
            }
	        String path = request.getSession().getServletContext().getRealPath("/");
	        Workbook wb = CreateExcelMoBusiness.toWorkBook(path + "model\\推荐人员模板.xls");//获取原始模板excel
	        String sheetName = "SheetName";
            Sheet hideInfoSheet = wb.createSheet(sheetName);
            // 在隐藏页设置选择信息
            // 第一行设置人员学历
            Row xlRow = hideInfoSheet.createRow(0);
            CreateExcelMoBusiness.creatRow(xlRow, xl);
            // 第二行设置政治面貌
            Row zzmmRow = hideInfoSheet.createRow(1);
            CreateExcelMoBusiness.creatRow(zzmmRow, zzmm);
            // 第三行设置现从事专业
            Row xcszyRow = hideInfoSheet.createRow(5);
            CreateExcelMoBusiness.creatRow(xcszyRow, xcszy);
            // 第四行设置现任职资格名称
            Row xrzzgmcRow = hideInfoSheet.createRow(3);
            CreateExcelMoBusiness.creatRow(xrzzgmcRow, xrzzgmc);
            // 第五行设置在职状态
            Row zzztRow = hideInfoSheet.createRow(4);
            CreateExcelMoBusiness.creatRow(zzztRow, zzzt);
            // 第六行设置学位
            Row xwRow = hideInfoSheet.createRow(5);
            CreateExcelMoBusiness.creatRow(xwRow, xw);
            // 第七行设置执业范围
            Row zcfwRow = hideInfoSheet.createRow(6);
            CreateExcelMoBusiness.creatRow(zcfwRow, zcfw);
            // 第八行设置性别
            Row xbRow = hideInfoSheet.createRow(7);
            CreateExcelMoBusiness.creatRow(xbRow, xb);
            Row zyRow = hideInfoSheet.createRow(8);
            CreateExcelMoBusiness.creatRow(zyRow, zy);
            Row zwRow = hideInfoSheet.createRow(9);
            CreateExcelMoBusiness.creatRow(zwRow, zw);
            // 第三行设置现从事专业
            CreateExcelMoBusiness.creatExcelNameList(wb, "zy", 9, zy.length, false, sheetName);
            CreateExcelMoBusiness.creatExcelNameList(wb, "zw", 10, zw.length, false, sheetName);
            /*
            // 隐藏sheet里面加值（用于下拉选值得来源），并设置下拉关联
            // 第一行设置人员学历
            CreateExcelMoBusiness.creatExcelNameList(wb, "xl", 1, xl.length, false, sheetName);
            // 第二行设置政治面貌
            CreateExcelMoBusiness.creatExcelNameList(wb, "zzmm", 2, zzmm.length, false, sheetName);
            // 第三行设置现任职资格名称
            CreateExcelMoBusiness.creatExcelNameList(wb, "xrzzgmc", 4, xrzzgmc.length, false, sheetName);
            // 第五行设置在职状态
            CreateExcelMoBusiness.creatExcelNameList(wb, "zzzt", 5, zzzt.length, false, sheetName);
            // 第六行设置学位
            CreateExcelMoBusiness.creatExcelNameList(wb, "xw", 6, xw.length, false, sheetName);
            // 第六行设置执业范围
            CreateExcelMoBusiness.creatExcelNameList(wb, "zcfw", 7, zcfw.length, false, sheetName);
            // 第七行设置性别
            CreateExcelMoBusiness.creatExcelNameList(wb, "xb", 8, xb.length, false, sheetName);*/
            // 设置隐藏页标志
            int sheetIndex = wb.getNumberOfSheets();
            if (sheetIndex > 0) {
            	for (int i = 0; i < sheetIndex; i++) {
            		Sheet sheet = wb.getSheetAt(i);
            		if (!sheetName.equals(sheet.getSheetName())) {
            			 DataValidation data_validation_list = null;
            			 for (int a = 2; a < 3000; a++) {
            				 //学历添加验证数据
            				/* data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xl", a, 5);
                             sheet.addValidationData(data_validation_list);
                             //性别添加验证数据
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xb", a, 2);
                             sheet.addValidationData(data_validation_list);
                        	  //政治面貌添加验证数据
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("zzmm", a, 4);
                             sheet.addValidationData(data_validation_list);
                             //现从事专业添加验证数据
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xcszy", a, 9);
                             sheet.addValidationData(data_validation_list);
                             //现任职资格名称
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xrzzgmc", a, 11);
                             sheet.addValidationData(data_validation_list);
                             //在职状态
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("zzzt", a, 14);
                             sheet.addValidationData(data_validation_list);
                             //学位
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xw", a, 15);
                             sheet.addValidationData(data_validation_list);
                             //执业范围
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("zcfw", a, 18);
                             sheet.addValidationData(data_validation_list);
                             //执业范围
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("xrzzgmc", a, 12);
                             sheet.addValidationData(data_validation_list);*/
            				 data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("zy", a, 5);
                             sheet.addValidationData(data_validation_list);
                             data_validation_list = CreateExcelMoBusiness.getDataValidationByFormula("zw", a, 4);
                             sheet.addValidationData(data_validation_list);
            			 }
            		}
            	}
            }
            wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("人员申报信息导入模板.xls", "UTF-8"))));
            wb.write(out);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delper")
	@ResponseBody
	public DwzResJson delper(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res=perService.deletelist(idslist);
				if(res==1){
					resJson.success(Const.DEL_SUCCEED);
				}
			} catch (Exception e) {
				resJson.error(Const.DEL_FAIL);
			}
		return resJson;
		}
	
}
