package com.ehinfo.hr.controller.system.org;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.service.system.org.HospitalService;
import com.ehinfo.hr.service.system.org.OrgService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/org/hospital/")
public class HospitalController extends BaseController<Hospital>{
	@Autowired
	public HospitalService service;
	@Autowired
	public OrgService orgService;
	
	@RequestMapping("index")
	public String index(ModelMap model,String pageNum,String numPerPage,String supunit,String code,String parentname) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			Page<Hospital> p = new Page<Hospital>();
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
			if(supunit!=null&&!supunit.equals("")){
			Page<Hospital> page_hospital = new Page<Hospital>();
			List<Hospital> hospitals = new ArrayList<Hospital>();
			page_hospital=(Page<Hospital>)findHospitalByPage(p, supunit, code);
			hospitals= page_hospital.getResults();
			model.put("pageNum", page_hospital.getPageNum());
			model.put("totalcount", page_hospital.getTotalRecord());
			model.put("numPerPage", page_hospital.getPageSize());
			model.put("hospitallist", hospitals);
			model.put("code", code);
			model.put("supunit", supunit);
			model.put("parentname", parentname);
			return "/system/org/hospital/hospitallist";
			}else{
			/*model.put("supunit", "0000");*/
			return "/system/org/hospital/hospitallist";
			}
		}
		return Const.NO_AUTHORIZED_URL;
	}
	@RequestMapping("addhospitalform")
	public String addhospitalform(ModelMap model,String supunit,String parentname,int showtype){
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			Hospital hospital = new Hospital();
			hospital.setNodetype("医院");
			model.put("supunit", supunit);
			if(Tools.isEmpty(parentname)){
				Hospital hos = new Hospital();
				hos.setHosnum(supunit);
				hos =  service.findByHosnum(hos);
				model.put("parentname", hos.getHosname());
			}else{
				model.put("parentname", parentname);
			}
			
			model.put("showtype", showtype);
			model.put("hospital", hospital);
			return "/system/org/hospital/hospitalform";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	@RequestMapping("edithospitalfrom")
	public String edithospitalfrom(ModelMap model,String hosnum){
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			int showtype=0;
			Hospital o = new Hospital();
			o.setHosnum(hosnum);
			Hospital hospital = new Hospital();
			List<Hospital> list =service.find(o);
			hospital=list.get(0);
			o.setHosnum(hospital.getSupunit());
			String parentname =service.find(o).get(0).getHosname();
			BaseDict dict = new BaseDict();
			dict.setNevalue(hospital.getDistcode());
			dict.setNekey(1);
			List<BaseDict> bds = selectDict(dict);
			if(bds.size()>0){model.put("distname", bds.get(0).getContents());}
			model.put("supunit", hospital.getSupunit());
			model.put("parentname", parentname);
			model.put("showtype", showtype);
			model.put("hospital", hospital);
			return "/system/org/hospital/hospitalform";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson save(Hospital hosptial,int showtype,String oldhosnum,String oldnodecode){
		AjaxRes ar=getAjaxRes();
		DwzResJson resJson = new DwzResJson();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU))) {
			resJson = checkBh(hosptial,showtype,oldhosnum,oldnodecode);
			if("200".equals(resJson.getStatusCode())){
				try {
					if(1==showtype){
						if(Tools.isEmpty(hosptial.getNodecode())){
							hosptial.setNodecode(hosptial.getHosnum());
						}
						service.insert(hosptial);
						resJson.closeCurrentAndRefresh("","添加成功");
					}else if(0==showtype){
						service.update(hosptial);
						resJson.closeCurrentAndRefresh("","修改成功");
					}
				} catch (Exception e) {
					resJson.error();
				}
			}
		}
		return resJson;
	}
	
	@RequestMapping(value="delBasHospitals",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson delBasHospitals(String ids){
		AjaxRes ar=getAjaxRes();
		DwzResJson resJson = new DwzResJson();
		int num=0;
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU))){	
			try {
				String[] hos=ids.split(",");
				for(String h:hos){
					num=0;
					String[] hosp=h.split(";");
					String hosnum = hosp[0];
					String nodecode = hosp[1];
					Hospital hospital = new Hospital();
					hospital.setHosnum(hosnum);
					hospital.setNodecode(nodecode);
					int res=service.delHospital(hospital);
					num=num+res;
					if(num!=0){
						resJson.error(num+"个卫生站存在子组织，请先删除子组织！");
						return resJson;
					}
				}
				if(num==0){
					resJson.setMessage("删除成功");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				resJson.error();
			}
		}
		return resJson;
	}
	
	@RequestMapping("getHospitalTree")
	public void  getHospitalTree(HttpServletRequest request,HttpServletResponse response){
		
		List<Hospital> hoslist = null;
			try {
				
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				
				hoslist = service.findByPid(a.getHosnum());
				List<String> listTree = new ArrayList<String>();
				String temp = "";
				for (Hospital h : hoslist) {
					temp = "{id:\"" + h.getHosnum() + "\"," + "pId:\"" + h.getSupunit() + "\"," + "name:\""
							+ h.getHosname() + "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
				logger.error(e.toString(),e);
				
			}
	}
	
	@RequestMapping("findHospitalByPage")
	public Page<Hospital> findHospitalByPage(Page<Hospital> page,String supunit, String code){
		Page<Hospital> hospital_page = new Page<Hospital>();
		try{
			hospital_page = service.findHospitalByPage(page,supunit,code);
			}catch (Exception e) {
				logger.error(e.toString(),e);
			}
			return hospital_page;
	}
	
	@RequestMapping(value="selectDict", method=RequestMethod.POST)
	@ResponseBody
	public List<BaseDict> selectDict(BaseDict dict){
		List<BaseDict> bds = null;
		int nekey = dict.getNekey();
		bds=service.selectDict(dict);
		List<BaseDict> dictList = new ArrayList<BaseDict>();
		for (BaseDict bd : bds) {
			if (bd.getNekey() == nekey) {
				dictList.add(bd);
			}
		}
		return dictList;
	}
	private DwzResJson checkBh(Hospital hospital,int showtype,String oldhosnum,String oldnodecode){
		DwzResJson msg = new DwzResJson();
		try{
			if(1 == showtype){ //添加
				if("医院".equals(hospital.getNodetype())){
					//医院只要判断 hosnum 是否已经存在就可以
					Hospital hos = new Hospital();
					hos.setHosnum(hospital.getHosnum());
					List<Hospital> l = service.find(hos);
					if(l.size() > 0 ){	msg.error("医院编码已存在");
					}else{	msg.success();	}
				}else if("院区".equals(hospital.getNodetype())){
					//院区 就判断nodecode 是否已经存在
					Hospital hos = new Hospital();
					hos.setNodecode(hospital.getNodecode());
					List<Hospital> l = service.find(hos);
					if(l.size() > 0 ){	msg.error("节点编码已存在");
					}else{	msg.success();	}
				}
			}else if(0 == showtype){ // 保存
				if("医院".equals(hospital.getNodetype())){
					//医院只要判断 hosnum 是否已经存在  并且不包含原始的值
					if(hospital.getHosnum().equals(oldhosnum)){
						msg.success();	
					}else{
						Hospital hos = new Hospital();
						hos.setHosnum(hospital.getHosnum());
						List<Hospital> l = service.find(hos);
						if(l.size() > 0 ){	msg.error("医院编码已存在");
						}else{	msg.success();}
					}
				}else if("院区".equals(hospital.getNodetype())){
					if(hospital.getNodecode().equals(oldnodecode)){
						msg.success();	
					}else{
						List<Hospital> l = service.find(hospital);
						if(l.size() > 0 ){	msg .error("节点编码已存在");
						}else{	msg.success();	}
					}
				}
			}
		}catch(Exception e){
			msg.error();
		}
		return msg;
	}
	@RequestMapping(value="findHospitalAsSelect", method=RequestMethod.POST)
	@ResponseBody
	public List<Hospital> findHospitalAsSelect(){
		List<Hospital> hospoitallist = service.findAllHospital();
		return hospoitallist;
	}
}
