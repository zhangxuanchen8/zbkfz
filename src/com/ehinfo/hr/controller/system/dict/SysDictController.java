package com.ehinfo.hr.controller.system.dict;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ehinfo.hr.common.ajax.AjaxRes;
import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.dict.SysDict;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.service.system.dict.SysDictService;

import net.sf.json.JSONArray;
/*
 * 系统参数
 */
@Controller
@RequestMapping("/backstage/sysDict/")
public class SysDictController extends BaseController<SysDict>{

	@Autowired
	public SysDictService service;
	/**
	 * 系统字典首页
	 * 通过作用范围(scope) 加载数据 1:默认加载所有；2：按scope加载
	 */
	@RequestMapping("index")
	public String index(ModelMap model,String pageNum,String numPerPage,String scope,String code,String type,HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String nodecode = a.getNodecode();
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			Page<SysDict> p = new Page<SysDict>();
			if(pageNum==null||pageNum.equals("")){
				p.setPageNum(1);
			}else{
				p.setPageNum(Integer.parseInt(pageNum));
			}
			if(numPerPage==null||numPerPage.equals("")){
				p.setPageSize(20);
			}else{
				p.setPageSize(Integer.parseInt(numPerPage));
			}
			if(scope!=null&&!"".equals(scope)){
			Page<SysDict> page_sysdict = new Page<SysDict>();
			List<SysDict> sysdicts = null;
			page_sysdict = (Page<SysDict>)findSysDictByPage(p,scope,code,type,hosnum,nodecode);
			sysdicts = page_sysdict.getResults();
			model.put("pageNum", page_sysdict.getPageNum());
			model.put("totalcount", page_sysdict.getTotalRecord());
			model.put("numPerPage", page_sysdict.getPageSize());
			model.put("code", code);
			model.put("sysdictlist", sysdicts);
			model.put("scope", scope);
			model.put("type", type);
			return "/system/dict/sys/sysdictlist";
			}else {
				return "/system/dict/sys/sysdictlist";
			}
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value = "load_sysdict_tree", method = RequestMethod.POST)
	@ResponseBody
	public void loadParmsTree(HttpServletRequest request,HttpServletResponse response){
		  
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String nodecode = a.getNodecode();
		List<String> lstTree = new ArrayList<String>(); //用于构建树
		try {
			List<SysDict> bps = null;
			bps = service.findScopeToTree(hosnum, nodecode);
			// 手动构建树
			int i = 1;
			String temp = "";
			String strParent = "{id:1, pId:0, name:'系统参数', open:true}";
			lstTree.add(strParent);
			for (SysDict bp : bps) {
				String scope = bp.getScope();
				temp = "{id:" + (++i) + ",pId:" + 1 + ",name:\"" + scope + "\"}";
				lstTree.add(temp);
			}
			response.getWriter().print(JSONArray.fromObject(lstTree).toString());
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}
	/***
	 * 详细信息页面   type：0 是查看； type：1 新增 */
	@RequestMapping("addsysdictform")
	public String addsysdictform(ModelMap model,int showtype){
		
			SysDict sysdict = new SysDict();
			sysdict.setCanedit(1);
			model.put("sysdict", sysdict);
			model.put("showtype", showtype);
			return "/system/dict/sys/sysdictform";
		
	}
	
	@RequestMapping("editsysdictform")
	public String editsysdictform(ModelMap model,HttpServletRequest request, String id) {
		
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String permission = null;
		int showtype = 0;
		
		SysDict o = new SysDict();
		o.setHosnum(hosnum);
		o.setParmid(id);
		List<SysDict> list = service.find(o);
		if (list.size() > 0) {
			model.put("sysdict", list.get(0));
		}
		model.put("showtype", showtype);
		if ("only".equals(permission)) {
			return "/system/dict/sys/onlysysdictform";
		} else {
			return "/system/dict/sys/sysdictform";
		}
	
	}
	
	
	/***
	 * 获取系统分类*/
	@RequestMapping(value = "getSysnames", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getSysnames(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		List<String> sysnames = null;
		try{
			sysnames = service.getSysnames(hosnum);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return sysnames;
	}
	
	/***
	 * 获取作用范围*/
	@RequestMapping(value = "getScopes", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getScopes(){
		List<String> scopes = null;
		try {
			scopes = service.getScopes();
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return scopes;
	}
	
	
	/***
	 * 保存的数据*/
	@RequestMapping(value = "saveSysDict", method = RequestMethod.POST)
	@ResponseBody
	public DwzResJson saveSysDict(SysDict dict, String showtype, HttpServletRequest request) {
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		DwzResJson resJson = new DwzResJson();
		dict.setHosnum(hosnum);
		resJson = checkSysDicts(dict, showtype);
		if ("200".equals(resJson.getStatusCode())) {
			try {
				if ("0".equals(showtype)) { // 0:更新
					service.update(dict);
					resJson.closeCurrentAndRefresh("", "修改成功");
				} else if ("1".equals(showtype)) { // 1:插入
					dict.setParmid(get32UUID());
				
					service.insert(dict);
					resJson.closeCurrentAndRefresh("", "添加成功");
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
				resJson.error("出现无法预知的错误！");
			}
		}
		return resJson;
	}
	
	/***
	 * 删除数据*/
	@RequestMapping(value = "delSysdict", method = RequestMethod.POST)
	@ResponseBody
	public DwzResJson delSysdict(String ids,HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		AjaxRes ar = getAjaxRes();
		DwzResJson resJson = new DwzResJson();
		int num = 0;
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU))) {
			try {
				String[] parmids = ids.split(",");
				for (String parmid : parmids) {
					SysDict dict = new SysDict();
					dict.setHosnum(hosnum);
					dict.setParmid(parmid);
					int res = service.delSysdict(dict);
					num += res;
				}
				if (num == 0) {
					resJson.setMessage("删除成功");
				} else {
					resJson.error(num + "条数据删除失败！");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				resJson.error();
			}
		}
		return resJson;
	}
	
	private Page<SysDict> findSysDictByPage(Page<SysDict> page,String scope, String code, String type,String hosnum,String nodecode) {
		Page<SysDict> result = new Page<SysDict>();
		try {
			result = service.findSysDictByPage(page,scope,code,type,hosnum,nodecode);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return result;
	}
	
	/***
	 * 验证要保存的数据*/
	public DwzResJson checkSysDicts(SysDict dict, String showtype) {
		DwzResJson msg = new DwzResJson();
		try {
			List<SysDict> list = null;
			if ("0".equals(showtype)) { // 更新时候的验证去除本身的重复
				list = service.checkSysDicts(dict);
			} else {
				list = service.checkSysDicts(dict);
			}
			if (list.size() > 0) {
				msg.error("已存在的参数名!");
			} else {
				msg.success();
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
			msg.error();
		}
		return msg;
	}
	
	
	
	@RequestMapping(value = "getSysDict", method = RequestMethod.POST)
	@ResponseBody
	private void getSysDict(HttpServletRequest request,HttpServletResponse response,SysDict dict) {
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			dict.setHosnum(a.getHosnum());
			dict  = service.findByName(dict);
			pw.print(JSONArray.fromObject(dict).toString());
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		
	}
	@RequestMapping("defListIdex")
	public String defListIdex(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String userId = a.getId();
		SysDict sys = new SysDict();
		sys.setUserid(userId);
		sys.setHosnum(hosnum);
		sys.setParmname("人事列表排序");
		sys = service.findByName(sys);
		StringBuffer all = new StringBuffer("p_no,c_name,sex,age,p_num,post_status,come_source,p_gone,health,polity_face_status,id_card,birthday,deptname,kq_deptname,zc_name,zc_type,zc_level,engage_headship_date,practice_name,practice_type,practice_area,post_type,engage_headship,salary_source,salary_type,post_level,post_scale,post_rank_sjt,manager_flag,base_salary,s_type,edu_level_f,graduate_school,edu_major_f,edu_level_t,edu_school_t,edu_major_t,n_detail,p_status,first_work_date,join_unit_date,p_source,level_unit_date,personnel_reduction,bargain_end_date,work_years_before,unit_work_year,work_year,technical_job,manage_job,security_job,s_date,ismarried,polity_face,join_party_date,nation,religion,native_place,home_address,home_tel,mobile_tel,office_tel,email,remark");
		if(sys!=null&&Tools.notEmpty(sys.getParmvalue())){
			String theOrder = sys.getParmvalue();
			String orders [] = theOrder.split(",");
			for(int i=0;i<orders.length;i++){

				all.replace(all.indexOf(orders[i]), all.indexOf(orders[i])+orders[i].length(), "");
			}
			String res = all.toString();
			char [] ss = res.toCharArray();
			for(int i=ss.length;i>-1;i--){
				if(i<ss.length-1){
					if(','==ss[i]&&','==ss[i+1]){
						ss[i+1]=' ';
					}
				}
			}
			res = String.valueOf(ss);
			res = res.replaceAll(" ", "");
			if(",".equals(res.substring(0, 1))){
				res = res.substring(1);
			}
			model.put("theGetCol", sys.getParmvalue().split(","));
			model.put("theRestCol", res.split(","));
		}else{
			model.put("theGetCol", all.toString().split(","));
		}
		return "ehr/employee/definedListIdex";
	}
	@RequestMapping("saveEmpIndex")
	public void saveEmpIndex(HttpServletRequest request,HttpServletResponse response,HttpSession session,String name){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			String hosnum = a.getHosnum();
			String userId = a.getId();
			SysDict sys = new SysDict();
			sys.setUserid(userId);
			sys.setHosnum(hosnum);
			sys.setParmname("人事列表排序");
			sys = service.findByName(sys);
			if(sys==null){
				sys = new SysDict();
				sys.setUserid(userId);
				sys.setHosnum(hosnum);
				sys.setParmname("人事列表排序");
				sys.setParmid(get32UUID());
				sys.setParmvalue(name);
				sys.setCanedit(1);
				sys.setScope("人员档案管理");
				service.insert(sys);
			}else{
				sys.setParmvalue(name);
				service.update(sys);
			}
			pw.write(EhUtil.createcallbackjson("200", "", "", "", "", "", "", ""));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			pw.write(EhUtil.createcallbackjson("300", "", "", "", "", "", "", ""));
		}
	}
}
