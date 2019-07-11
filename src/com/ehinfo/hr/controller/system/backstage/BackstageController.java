package com.ehinfo.hr.controller.system.backstage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.AjaxRes;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.webpage.PageData;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.como.ComBacklog;
import com.ehinfo.hr.entity.como.ComNotice;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.entity.system.log.LoginLog;
import com.ehinfo.hr.entity.system.resources.Resources;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.declare.PersonalRecordService;
import com.ehinfo.hr.service.system.resources.ResourcesService;
import com.ehinfo.hr.service.system.user.BasUserService;
import com.ehinfo.hr.service.system.user.CpUserService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.new_ryzbkService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;
import com.ehinfo.hr.service.zhibiao.zhibiaoglService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/")
public class BackstageController extends BaseController<Object>{
	
	@Autowired
	public ResourcesService menuService;

    @Autowired
    private ModelService modelService;
	@Autowired
	private BasUserService userService;
	@Autowired
	public new_ryzbkService new_ryzbkService;
	@Autowired
	public zhibiao_sbService zbkservice;
	@Autowired
	public zhibiaoglService score;
	@Autowired
	private CpUserService cpService;
	@Autowired
	private PersonalRecordService perService;
	/*@Autowired
	private LoginLogService loginLogService;*/
	/**
	 * 访问系统首页
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("index")
	public String index(ModelMap model,HttpServletRequest request,HttpSession session,Page<LoginLog> page){	
		//--------   -------加上合同预警----------------------
		BasUser user = (BasUser)session.getAttribute(Const.SESSION_USER);

		model.addAttribute("currentAccount", user);
	
	//-----------------------菜单----------------------
		List<Resources> menus =new ArrayList<Resources>();
		
		String userId = user.getId();
		String path = request.getContextPath();
		menus=menuService.findMenuTree(userId,"root",path);
		//-------按1级节点分----------
		List<Map<String,String>> menu_json = new ArrayList<Map<String,String>>();
		Map<String,String> rootMenu = null;
		if(menus!=null){
			for(Resources menu:menus){
				if(menu.getpId()!=null&&"root".equals(menu.getpId())){
					List<Resources> MenuByRoot =new ArrayList<Resources>();
					MenuByRoot = menuService.findMenuTree(userId,menu.getId(),path);
					rootMenu = new HashMap<String,String>();
					rootMenu.put("id", menu.getId());
					rootMenu.put("name", menu.getName());
					rootMenu.put("menu", JSONArray.fromObject(MenuByRoot).toString());
					rootMenu.put("icon", menu.getIcon());
					menu_json.add(rootMenu);
				}
			}
		}
		model.put("menuList", menu_json);
		
		
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
		Modelm m = new Modelm();
		m.setHosnum(user.getHosnum());
		m.setYear(year);
		List<Modelm> list_model = modelService.findbystoptime(m);
		new_ryzbk ry = null;
		zhibiaoku zbk= null;
		zbkscore zbks = null;
		int sum ;
		for(Modelm h:list_model){
			ry = new new_ryzbk();
			ry.setPid(h.getId());
			List<new_ryzbk> list_ry = new_ryzbkService.find(ry);
			sum = 0;
			if(list_ry.size()>0){
				zbk = new zhibiaoku();
				zbk.setPid(h.getId());
				List<zhibiaoku> list_zbk = zbkservice.findbydept(zbk);
				int num=0;
				for(zhibiaoku z:list_zbk){
					if(Tools.notEmpty(z.getUse_dept())){
						num = Integer.parseInt(z.getNum());
						zbks = new zbkscore();
						zbks.setPsunitid(h.getId());
						zbks.setDept(z.getUse_dept());
						List<zbkscore> list_zbks  =score.findnums(zbks);
						if(list_zbks.size()<num*list_ry.size()){
							sum++;
						}
					}
				}
			}
			h.setNum(sum);
		}
		model.put("list_model", list_model);
		List<CpUser> list_cp = cpService.findcpnums(user.getHosnum());
		model.put("list_cp", list_cp);
		PersonalRecord per = new PersonalRecord();
		per.setUnitid(user.getHosnum());
		List<PersonalRecord> list_per = perService.getppjgper(per);
		List<PersonalRecord> list_pery = perService.getppjgpery(per);
		List<PersonalRecord> list_perw = perService.getppjgperw(per);
		model.put("list_per", list_per);
		model.put("list_pery", list_pery);
		model.put("list_perw", list_perw);
		Map<String,String> map= perService.getTj(per);
		model.put("map", map);
		double sums = 0;
		/*if(map!=null){
			for(int i=1;i<10;i++){
				Object od = map.get("a"+i);
				sums+=Double.parseDouble(od.toString());
			}
		}*/
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<PersonalRecord> list_s=perService.getppjgpers(per);
		for(PersonalRecord h:list_s){
			sums = 0;
			per = new PersonalRecord();
			per.setUnitid(user.getHosnum());
			per.setYear(h.getYear());
			Map<String,String> maps= perService.getTj(per);
			if(maps!=null){
				for(int i=1;i<10;i++){
					Object od = maps.get("a"+i);
					sums+=Double.parseDouble(od.toString());
				}
				maps.put("A10",String.valueOf(sums/Double.parseDouble(h.getNums())));
			}else{
				maps =new HashMap<String,String>(); 
			}
			list.add(maps);
		}
		model.put("Tj", list);
		model.put("list_s", list_s);
		
		
		return "/system/index";
	}
	@RequestMapping(value="deptxiangxi")
	public String  deptxiangxi(HttpServletRequest res,HttpServletResponse rep,ModelMap model,HttpSession session,String modelid){
		BasUser user = (BasUser)session.getAttribute(Const.SESSION_USER);
		Modelm m = new Modelm();
		m.setId(modelid);
		m = modelService.findById(m);
		model.put("model",m);
		new_ryzbk ry = new new_ryzbk();
		zhibiaoku zbk = null;
		zbkscore zbks=null;
		ry.setPid(modelid);
		List<new_ryzbk> list_ry = new_ryzbkService.find(ry);
		List<zhibiaoku> list_zbkss =  new ArrayList<zhibiaoku>();
		if(list_ry.size()>0){
			zbk = new zhibiaoku();
			zbk.setPid(modelid);
			List<zhibiaoku> list_zbk = zbkservice.findbydept(zbk);
			for(zhibiaoku z:list_zbk){
				if(Tools.notEmpty(z.getUse_dept())){
					zbks = new zbkscore();
					zbks.setPsunitid(modelid);
					zbks.setDept(z.getUse_dept());
					List<zbkscore> list_zbks  =score.findnumstj(zbks);
					if(list_zbks!=null && list_zbks.size()>0){
						z.setList_s(list_zbks);
						list_zbkss.add(z);
					}
					
				}
			}
			model.put("list_zbk",list_zbkss);
		}
    	model.put("list_zy",list_ry);
		return "/system/deptxiangxi";
	}
	@RequestMapping(value="xiangxi")
	public String  addtree(HttpServletRequest res,HttpServletResponse rep,ModelMap model,HttpSession session,String year){
		BasUser user = (BasUser)session.getAttribute(Const.SESSION_USER);
		PersonalRecord per = new PersonalRecord();
		per.setUnitid(user.getHosnum());
		per.setYear(year);
		String nlmc="";
    	String nlnum="";
    	List<PersonalRecord> list_zy=perService.getzyry(per);
    	double m = 0;
    	for(PersonalRecord p:list_zy){
    		m  = 0;
    		if(Tools.notEmpty(p.getTyrst())){
    			m = Double.parseDouble(p.getTyrst())/Double.parseDouble(p.getCxrst());
        		p.setZcl(m);
    		}
    		
    	}
    	model.put("list_zy",list_zy);
		return "/system/zyxiangxi";
	}
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping("index")
	public String index(ModelMap model,HttpServletRequest request,HttpSession session,Page<LoginLog> page,String username,String password,String more){	
		//--------   -------加上合同预警----------------------
		BasUser user = (BasUser)session.getAttribute(Const.SESSION_USER);
		if(user==null){
			BasUser u = userService.findFormatByLoginName(username);
			user = u;
			session.setAttribute(Const.SESSION_USER, user);
		}
		String sql  = "select count(p_id) as noht from hr_employee_view t where ht_end_date='' or ht_end_date is null and n_status='在职'  and hosnum = '"+user.getHosnum()+"'";
		List<Map<String,String>> result = null;
		result = sqlService.excut(sql);
		sql  = "select count(p_id) as gqht from hr_employee_view t where ht_end_date<sysdate and n_status='在职'  and hosnum = '"+user.getHosnum()+"'";
		result.addAll(sqlService.excut(sql)) ;
		sql  = "select count(p_id) as kgqht from hr_employee_view t where ht_end_date>sysdate and months_between (ht_end_date,sysdate)<1 and n_status='在职'  and hosnum = '"+user.getHosnum()+"'";
		result.addAll(sqlService.excut(sql)) ;
		model.addAttribute("htyj", result);
		model.addAttribute("currentAccount", user);
	//-----------------------预警----------------------
		Warning wn = new Warning();
		wn.setHosnum(user.getHosnum());
		wn.setForward_role(user.getId());
		List<Warning> warningType = wService.fingByType(wn);
		//用户Id传入只是为了传值入sql判断权限
		//这个用户所在机构下属及其下属机构的预警消息汇总，前提为这个机构拥有对应的权限
		List<Warning> list = wService.findWarning(wn);
		List<Warning> listTemp = null;
		Map<String,List<Warning>> listType=  new HashMap<String,List<Warning>>();
		for(Warning wning:warningType ){
			listTemp = new ArrayList<Warning>();
			String wnType = wning.getWarn_type();
			for(Warning warn:list){
				if(wnType.equals(warn.getWarn_type())){
					String html = warn.getWarn_html();
					html = html.replace("${warn_id}", warn.getId());
					warn.setWarn_html(html);
					listTemp.add(warn);
				}
			}
			listType.put(wning.getWarn_type(),listTemp);
		}
	//--------------调入调出提醒----------------
		Reduction reduction = new Reduction();
		reduction.setApprove_name(user.getName());
		reduction.setApprove_name_hosnum(user.getHosnum());
		List<Reduction> reductions = reductionService.findByNOApprove(reduction);
		model.addAttribute("reductions", reductions);
		
	//-----------------------菜单----------------------
		List<Resources> menus =new ArrayList<Resources>();
		
		String userId = user.getId();
		String path = request.getContextPath();
		menus=menuService.findMenuTree(userId,"root",path);
		//-------按1级节点分----------
		List<Map<String,String>> menu_json = new ArrayList<Map<String,String>>();
		Map<String,String> rootMenu = null;
		if(menus!=null){
			for(Resources menu:menus){
				if(menu.getpId()!=null&&"root".equals(menu.getpId())){
					List<Resources> MenuByRoot =new ArrayList<Resources>();
					MenuByRoot = menuService.findMenuTree(userId,menu.getId(),path);
					rootMenu = new HashMap<String,String>();
					rootMenu.put("id", menu.getId());
					rootMenu.put("name", menu.getName());
					rootMenu.put("menu", JSONArray.fromObject(MenuByRoot).toString());
					rootMenu.put("icon", menu.getIcon());
					menu_json.add(rootMenu);
				}
			}
		}
		LoginLog loginLog = new LoginLog();
		page.setPageNum(1);
		page.setPageSize(30);
		page = loginLogService.findByPage(loginLog, page);
		List<LoginLog> list_loginLog = new ArrayList<LoginLog>();
		list_loginLog=page.getResults();
		if(list_loginLog.size()>10){
			list_loginLog = list_loginLog.subList(0, 10);
		}
		
		model.put("list_loginLog", list_loginLog);
		ComNotice cn = new ComNotice();
		Page<ComNotice> pagecn = new Page<ComNotice>();
		cn.setUserid(userId);
		cn.setHosnum(user.getHosnum());
		List<ComNotice> list_comnotice = new ArrayList<ComNotice>();
		// 通知
		cn.setNoticeclass("T");
		pagecn.setPageSize(5);
		pagecn = NotService.findSysNotice(cn, pagecn);
		list_comnotice = pagecn.getResults();
		if(list_comnotice != null && list_comnotice.size()>5){
			list_comnotice =list_comnotice.subList(0, 5);
		}
		//待办
		ComBacklog cb = new ComBacklog();
		Page<ComBacklog> pagecb = new Page<ComBacklog>();
		cb.setNotice_rule(user.getRoles());
		cb.setNotice_place(user.getPerson_dept());
		cb.setAuditor(user.getId());
		List<ComBacklog> list_combacklog = new ArrayList<ComBacklog>();
		if(more==null||more.length()<=0){
			pagecb.setPageSize(5);
			pagecb = BakService.findBacklog(cb, pagecb);
			list_combacklog = pagecb.getResults();
			if(list_combacklog != null && list_combacklog.size()>5){
				list_combacklog =list_combacklog.subList(0, 5);
			}
		}else{
			list_combacklog=BakService.findMoreBacklog(cb);
		}
		model.put("tzgg", list_comnotice);
		model.put("dbgg", list_combacklog);
		model.put("menuList", menu_json);
		model.addAttribute("warning_index", listType);
		return "/system/index";
	}*/
	

	@RequestMapping(value="menu/getMenu", method=RequestMethod.POST)
	@ResponseBody
	public void getMenu(HttpServletRequest request,HttpServletResponse response){	
		AjaxRes ar=getAjaxRes();
		List<Resources> menus =new ArrayList<Resources>();
		PageData pd = this.getPageData();
		String layer=pd.getString("layer");
		try {
			menus=null;
			HttpSession session= request.getSession();
			BasUser user = (BasUser)session.getAttribute(Const.SESSION_USER);
			String userId = user.getId();
			if(menus==null){
				String path = request.getContextPath();
				menus=menuService.findMenuTree(userId,"root",path);
				session.setAttribute(Const.SESSION_MENULIST, menus);
			}
			if(menus!=null){
				JSONArray jsons = JSONArray.fromObject(menus);
				response.getWriter().print(jsons.toString());
				
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg("获取菜单失败");
		}			
	}
	
	@RequestMapping("adv")
	public String advUI(Model model) {	
		return "/system/adv/adv";
	}
	
	@RequestMapping("404")
	public String errorlistUI(Model model){	
		return "/system/error/404";
	}
	/**
	 * 
	 * 没权限页面
	 * @param model
	 * @return
	 */
	@RequestMapping("noAuthorized")
	public String noAuthorizedUI(Model model){
		return Const.NO_AUTHORIZED_URL;
	}
}
