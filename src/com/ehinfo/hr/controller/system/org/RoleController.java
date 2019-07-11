package com.ehinfo.hr.controller.system.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ehinfo.hr.common.ajax.AjaxRes;
import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.EhException;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.common.utils.webpage.PageData;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.BasDept;
import com.ehinfo.hr.entity.system.org.BaseRole;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.entity.system.org.Role;
import com.ehinfo.hr.entity.system.resources.BaseRight;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.service.system.dict.BaseDictService;
import com.ehinfo.hr.service.system.org.OrgService;
import com.ehinfo.hr.service.system.org.RoleService;

import net.sf.json.JSONArray;
/*
 * 角色管理
 */
@Controller
@RequestMapping("/backstage/org/role/")
public class RoleController extends BaseController<Role>{
	
	@Autowired
	public OrgService orgService;
	
	@Autowired
	public RoleService roleService;
	@Autowired
	public BaseDictService dictService;
	
	
	/**
	 * 角色首页
	 */
	@RequestMapping("orgindex")
	public String orgindex(ModelMap model,HttpServletRequest request,String numPerPage,String pageNum,String deptid,String code) throws UnsupportedEncodingException{	
			String thedept = request.getParameter("thedept");
			if(deptid==null){
				if(thedept!=null&&!"".equals(thedept)){
					deptid = thedept;
				}
			}
			
			Page<Org> p=new Page<Org>();
			Org org = new Org();
			  
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			String hosnum = a.getHosnum();
			org.setHosnum(hosnum);
			if(pageNum==null||"".equals(pageNum)){
				p.setPageNum(1);
			}else{
				p.setPageNum(Integer.parseInt(pageNum));
			}
			if(numPerPage==null||"".equals(numPerPage)){
				p.setPageSize(20);
			}else{
				p.setPageSize(Integer.parseInt(numPerPage));
			}
			if(deptid!=null&&!"".equals(deptid)){
				Page<Org> page_org = new Page<Org>();
				List<Org> orgs= new ArrayList<Org>();
				page_org =(Page<Org>) findOrgByPage(p,org,deptid,code);
				orgs = page_org.getResults();
				model.put("pageNum", page_org.getPageNum());
				model.put("totalcount", page_org.getTotalRecord());
				model.put("numPerPage", page_org.getPageSize());
				model.put("deptlist", orgs);
				model.put("code", code);
				model.put("deptid", deptid);
			}
			
			String relFlag = request.getParameter("relFlag");
			if(relFlag!=null&&!"".equals(relFlag)){
				return "/system/org/role/orgTable";
			}
			return "/system/org/role/orglist";
			
		
	}
	
	/**
	 * 科室组织结构树
	 */
	@RequestMapping("orgTreeindex")
	public String orgTreeindex(ModelMap model,HttpServletRequest request) {	
		return "/system/org/role/orgTree";
	}
	
	
	@RequestMapping(value="getDeptOrg", method=RequestMethod.POST)
	@ResponseBody
	public void getDeptOrg(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		
			try {
				List<ZNodes> r=orgService.getDeptTreeByid(hosnum);
				JSONArray jsons = JSONArray.fromObject(r);
				response.getWriter().print(jsons.toString());
			} catch (Exception e) {
				logger.error(e.toString(),e);
				
			}
	}
	
	@RequestMapping(value="findOrgByPage",method=RequestMethod.POST)
	@ResponseBody
	public Page<Org> findOrgByPage(Page<Org> page,Org org,String deptid,String code){
		Page<Org> org_page = new Page<Org>();
		try{
		org_page = orgService.findOrgByPage(page,org,deptid,code);
		}catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return org_page;
	}
	
	@RequestMapping(value="selectDict", method=RequestMethod.POST)
	@ResponseBody
	public List<BaseDict> selectDict(int nekey){
		List<BaseDict> bds = null;
		bds=orgService.selectDict(nekey);
		List<BaseDict> dictList = new ArrayList<BaseDict>();// �������
		for (BaseDict bd : bds) {
			if (bd.getNekey() == nekey) {
				dictList.add(bd);
			}
		}
		return dictList;
	}
	/**
	 * 
	 */
	
	
	@RequestMapping("editdeptfrom")
	public String editdeptfrom(HttpServletRequest request,ModelMap model,String id,String pid) {	
		//------------如果有Id则修改---------------
		if(Tools.notEmpty(id)){
			Org org = new Org();
			org.setId(id);
			//--------取本科室------------
			org=orgService.findById(org);
			Org o = new Org();
			o.setId(org.getpId());
			o=orgService.findById(o);
			//-------放父级科室的科室名-------
			org.setpName(o.getName());
			//-----------取考勤科室的信息----
			Org kq = new Org();
			kq.setId(org.getKq_deptid());
			kq = orgService.findById(kq);
			
			model.put("dept",org);
			model.put("kq_deptname", kq.getName());
		}else{
			if(Tools.isEmpty(pid)){
				return "500.jsp";
			}
			//--------------新增
			Org p = new Org();
			p.setId(pid);
			p=orgService.findById(p);
			String organizeno = orgService.findmaxorganizeno();
			Org org = new Org();
			org.setIshospital(p.getIshospital());
			org.setpId(p.getId());
			org.setOrganizeno(organizeno);
			org.setpName(p.getName());
			org.setHosnum(p.getHosnum());
			model.put("dept",org);
		}
			List<BaseDict> depttypeList = selectDict(13);
			model.put("depttypeList", depttypeList);
			model.put("deptid", pid);
			//获取核算类型
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			List<BaseDict> histypelist = null;
			BaseDict dict = new BaseDict();
			dict.setNekey(91);
			dict.setHosnum(a.getHosnum());
			histypelist=dictService.selectDictByNekey(dict);
			model.put("histypelist", histypelist);
			return "/system/org/role/orgform";
	}
	
	@RequestMapping(value="getDeptTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDeptTree(HttpServletRequest request){
		AjaxRes ar=getAjaxRes();
		  
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		
			try {
				List<ZNodes> r=orgService.getDeptTree(hosnum);
				for(int i=1;i<r.size();i++){
					if(r.get(i).getId()==null){
						r.get(i).setIconSkin("person");
					}
				}
				String maxhosnum = orgService.getmaxhosnum();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("list", r);
				m.put("maxhosnum", Integer.parseInt(maxhosnum)+1);
				ar.setSucceed(m);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}

	@RequestMapping(value="getHISTree", method=RequestMethod.POST)
	@ResponseBody
	public void getHISTree(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		List<BasDept> deptlist=orgService.getHISTree(hosnum);
			try {
				List<String> listTree = new ArrayList<String>();
				for (BasDept h : deptlist) {
					String temp = "";
					//只是借用这个对象来存储参数，实际值并不对应
					temp = "{id:\"" + h.getId()+ "\"," + "pId:\"" + h.getHosnum() + "\"," + "name:\""
							+ h.getHis_deptid()+ "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
	}

	
	@RequestMapping("deptmanage")
	public String copy(Model model) throws UnsupportedEncodingException{	
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/org/role/deptmanage";
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map findByPage(Page<Role> page,Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		AjaxRes ar=getAjaxRes();
		
			try {
				Page<Role> roles=roleService.findByPage(o, page);
				
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",roles);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return p;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Role o){
		AjaxRes ar=getAjaxRes();		
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				o.setOrgId("1");
				roleService.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		return ar;
	}
	
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String ids){
		AjaxRes ar=getAjaxRes();	
			try {
				String[] chk =ids.split(",");
				List<Role> list=new ArrayList<Role>();
				for(String s:chk){
					Role sd=new Role();
					sd.setId(s);
					list.add(sd);
				}
				roleService.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Role o){
		AjaxRes ar=getAjaxRes();
		
			try {
				List<Role> list=roleService.find(o);
				Role role=list.get(0);
				ar.setSucceed(role);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		return ar;
	}
		
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Role o){
		AjaxRes ar=getAjaxRes();
			try {
				roleService.delete(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value="listAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAuthorized(){
		AjaxRes ar=getAjaxRes();	
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=roleService.listAuthorized(roleId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value="saveAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveAuthorized(){
		AjaxRes ar=getAjaxRes();
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				roleService.saveAuthorized(roleId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value="getOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgTree(HttpServletRequest request,String hosnum1,String flag){
		AjaxRes ar=getAjaxRes();
		String hosnum = null;
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		if(Tools.notEmpty(flag) && flag.equals("Y")){
			 hosnum = hosnum1;
		}else{
			 hosnum = a.getHosnum();
		}
			
			try {
				List<ZNodes> r=orgService.getOrgTree(hosnum);
				for(int i=1;i<r.size();i++){
					if(r.get(i).getId()==null){
						r.get(i).setIconSkin("person");
					}
				}
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("list", r);
				ar.setSucceed(m);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}
	
	
	@RequestMapping(value="getOrgTree_dwz", method=RequestMethod.POST)
	@ResponseBody
	public void  getOrgTree_dwz(HttpServletRequest request,HttpServletResponse response){
		
		
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		
			try {
				List<ZNodes> r=orgService.getOrgTree(hosnum);
				JSONArray jsons = JSONArray.fromObject(r);
				response.getWriter().print(jsons.toString());
			} catch (Exception e) {
				logger.error(e.toString(),e);
				
			}
		}
		
	
	
	@RequestMapping(value="getOrgTreeWithUser", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgTreeWithUser(HttpServletRequest request){
		AjaxRes ar=getAjaxRes();
		  
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
			
			try {
				List<ZNodes> r=orgService.getOrgTreeWithUser(hosnum);
				for(int i=1;i<r.size();i++){
					if(r.get(i).getId()==null){
						r.get(i).setIconSkin("person");
					}
				}
				String maxhosnum = orgService.getmaxhosnum();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("list", r);
				m.put("maxhosnum", Integer.parseInt(maxhosnum)+1);
				ar.setSucceed(m);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}

	
	@RequestMapping(value="getEntryWithUser", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getEntryWithUser(HttpServletRequest request){
		AjaxRes ar=getAjaxRes();
		  
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
			
			try {
				List<ZNodes> r=orgService.getEntryWithUser(hosnum);
				for(int i=1;i<r.size();i++){
					if(r.get(i).getId()==null){
						r.get(i).setIconSkin("person");
					}
				}
				String maxhosnum = orgService.getmaxhosnum();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("list", r);
				m.put("maxhosnum", Integer.parseInt(maxhosnum)+1);
				ar.setSucceed(m);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}

	@RequestMapping(value="getDatadictTree", method=RequestMethod.POST)
	@ResponseBody
	public void getDatadictTree(HttpServletResponse resp,HttpServletRequest request){
		AjaxRes ar=getAjaxRes();
		 
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		List<String> lstTree = null;
			
			try {
				lstTree = new ArrayList<String>(); 
				int i=1;
                String temp="";
                String s1 = "{id:1, pId:0, name:\"基础字典表维护\" , open:true}";
                lstTree.add(s1);
				List<ZNodes> r=orgService.getDictSysname(hosnum);
				for (ZNodes zNodes : r) {
					i++;
                    String id = String.valueOf(i);
                    String name=zNodes.getName();
                    temp="{id:"+id+",pId:1,"+"name:\""+name+"\",open:false}";
                    lstTree.add(temp);
                    List<ZNodes> r2=orgService.getDictSysnameNode(name);
                    for (ZNodes zNodes2 : r2) {
                    	i++;
                    	 String id2= zNodes2.getId();
                         String name2=zNodes2.getName();
                         String nekey = String.valueOf(zNodes2.getNekey());
                         String nameandkey =nekey+"_"+name2;
                         temp="{id:"+id2+",pId:"+id+","+"name:"+nameandkey+",nekey:"+nekey+"}";
                         lstTree.add(temp);
					}
				}
				resp.getWriter().print(JSONArray.fromObject(lstTree).toString());
				Map<String, Object> m = new HashMap<String, Object>();
				ar.setSucceed(m);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		//return ar;
	}
	
	@RequestMapping(value="getPreOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreOrgTree(HttpServletRequest request){
		AjaxRes ar=getAjaxRes();
		  
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
			
			try {
				List<ZNodes> r=orgService.getPreOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}
	
	@RequestMapping(value="addOrg", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson addOrg(Org o,String oldname,String deptid,String his_deptid,Double coefficient,String his_type){
		AjaxRes ar=getAjaxRes();
		DwzResJson resJson = new DwzResJson();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU))) {
			resJson = checkBh(o,oldname);
			o.setHis_deptid(his_deptid);
			o.setCoefficient(coefficient);
			o.setHis_type(his_type);
			if("200".equals(resJson.getStatusCode())){
					try {
						if(o.getId()!=null&&o.getId()!=""){
							if(Tools.isEmpty(o.getKq_deptid())){
								o.setKq_deptid(o.getId());
							}
							orgService.update(o);
							resJson.setRel("deptListRef");
							//resJson.closeCurrentAndRefresh("","修改成功");
							resJson.success("修改成功");
						}else{
							o.setCreateTime(new Date());
							int maxid = orgService.findmaxid();
							o.setId(String.valueOf(maxid+1));
							if(Tools.isEmpty(o.getKq_deptid())){
								o.setKq_deptid(o.getId());
							}
							orgService.insert(o);
							resJson.setRel("deptListRef");
							//resJson.closeCurrentAndRefresh("","添加成功");
							resJson.success("添加成功");
						}
					} catch (Exception e) {
						e.printStackTrace();
						resJson.error();
					}
				}
			}
			return resJson;
		}
	
	private DwzResJson checkBh(Org o,String oldname){
		DwzResJson msg = new DwzResJson();
		try{
			if(Tools.isEmpty(o.getId())){ //添加
					List<Org> r = orgService.findByName(o.getName(),o.getHosnum());
					if(r.size()>0){
						msg.error("科室名称不可重复");
					}else{
						msg.success();
					}
				}else{
					if(o.getName().equals(oldname)){
						msg.success();	
					}else{
						List<Org> r = orgService.findByName(o.getName(),o.getHosnum());
						if(r.size()>0){
							msg.error("科室名称不可重复");
						}else{
							msg.success();
						}
					}
				}
		}catch(Exception e){
			msg.error();
		}
		return msg;
	}
	/*@RequestMapping(value="addOrg",method=RequestMethod.POST)
	@ResponseBody
	public void addOrg(Org o,HttpServletRequest request,HttpServletResponse response){
		PrintWriter pw=null;
		try {
			pw = response.getWriter();
			if(o.getId()!=null&&o.getId()!=""){
				if(Tools.isEmpty(o.getKq_deptid())){
					o.setKq_deptid(o.getId());
				}
				String name = o.getName();
				List<Org> r = orgService.findByName(name,o.getHosnum());
				if(r.size()>0){
					new EhException().error("科室名称不可相同");
				}else{
					orgService.update(o);
				}
			}else{
				String name = o.getName();
				List<Org> r = orgService.findByName(name,o.getHosnum());
				if(r.size()>0){
					new EhException().error("科室名称不可相同");
				}else{
					o.setCreateTime(new Date());
					int maxid = orgService.findmaxid();
					o.setId(String.valueOf(maxid+1));
					if(Tools.isEmpty(o.getKq_deptid())){
						o.setKq_deptid(o.getId());
					}
					orgService.insert(o);
				}
			}
			response.getWriter().write(EhUtil.createcallbackjson("200", "添加成功", "", "deptListRef", "closeCurrent", "", "", ""));
		} catch (EhException error){
			pw.print(EhUtil.createcallbackjson("300", error.getMessage().toString(), "", "", "", "", "", ""));
		}catch (Exception e) {
			pw.write(EhUtil.createcallbackjson("300", "保存失败","","","","","",""));
		}
	}*/
	@RequestMapping(value="updateOrg", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson updateOrg(Org o){
		AjaxRes ar=getAjaxRes();
		DwzResJson resJson =new DwzResJson();
		
			try {
				o.setUpdateTime(new Date());
				orgService.update(o);
				resJson.setRel("deptListRef");
				resJson.closeCurrentAndRefresh("","修改成功");
			} catch (Exception e) {
				resJson.error();
			}
		
		return resJson;
	}
	
	@RequestMapping(value="findOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOrg(Org o){
		AjaxRes ar=getAjaxRes();
			
			try {
				List<Org> list=orgService.find(o);
				Org org=list.get(0);
				ar.setSucceed(org);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}
	
	@RequestMapping(value="delOrg", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson delOrg(String ids){
		AjaxRes ar=getAjaxRes();
		DwzResJson resJson = new DwzResJson();
		int res=0;
			try {
				String[] chk =ids.split(",");
				for(String h:chk){
					Org o = new Org();
					o.setId(h);
					res+=orgService.delOrg(o);
				}
				if(res==0){
					resJson.setMessage("删除成功");
					resJson.setRel("deptListRef");
					resJson.setCallbackType("forward");
					resJson.setForwardUrl("backstage/org/role/orgindex");
				}
				else  resJson.error("还有"+res+"个组织未删除成功,请先删除其所有子组织或角色");
			} catch (Exception e) {
				resJson.error();
			}
		
		return resJson;
	}
	
	
	@RequestMapping(value="orglistAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes orglistAuthorized(){
		AjaxRes ar=getAjaxRes();
			
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=orgService.listAuthorized(orgId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
			
		return ar;
	}
	
	@RequestMapping(value="saveOrgAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveOrgAuthorized(){
		AjaxRes ar=getAjaxRes();
			
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				orgService.saveAuthorized(orgId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		
		return ar;
	}
	
	/**
	 * 检查hosnum
	 * @param o
	 * @return
	 */
	@RequestMapping(value="checkHosnum", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes checkHosnum(Org o){
		AjaxRes ar=getAjaxRes();
			
			try {
				Org org = orgService.checkHosnum(o);
				if(org.getCount()>0){
					//存在
					ar.setSucceedMsg("Y");
				}else{
					//不存在
					ar.setSucceedMsg("N");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		
		return ar;
	}
	
	@RequestMapping("index")	
	public String index(ModelMap model,String numPerPage,String pageNum,String name,String isValid,HttpServletRequest request){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			Page p = new Page();
			Role e1 = new Role();
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			if(pageNum==null){
				p.setPageNum(1);
			}else{
				p.setPageNum(Integer.parseInt(pageNum));
			}
			if(numPerPage==null){
				p.setPageSize(30);
			}else{
				p.setPageSize(Integer.parseInt(numPerPage));
			}
			Page<Role> page_role = new Page<Role>();
			List<Role> list_role = new ArrayList<Role>();
			if(name!=null){
				if(!"".equals(name)){
					String name1="%"+name+"%";
					e1.setName(name1);
				}
				
			}
			if(isValid!=null){
				if(!"".equals(isValid)){
					e1.setIsValid(Integer.valueOf(isValid));
				}
			}
			page_role = (Page<Role>) findByPage(p,e1).get("list");
			list_role = page_role.getResults();
			if(list_role==null){
				model.put("pageNum", "1");
				model.put("totalcount", page_role.getTotalRecord());
				model.put("numPerPage", page_role.getPageSize());
				model.put("role_listlist", list_role);
				return "/system/org/role/roleList";
			}
			model.put("pageNum", page_role.getPageNum());
			model.put("totalcount", page_role.getTotalRecord());
			model.put("numPerPage", page_role.getPageSize());
			model.put("role_listlist", list_role);
			model.put("name", name);
			model.put("isValid", isValid);
			
			return "/system/org/role/roleList";
	}
	
	
	 
	 @RequestMapping("addForm")	
		public String addForm(ModelMap model,HttpServletRequest req,HttpServletResponse res,String id){
			if(Tools.isEmpty(id)){
				return "/system/org/role/roleForm";
			}
			List<BaseRole> rolelist = roleService.rolesearchbyid(id);
			List<String> menu = roleService.rightsearchbyid(id);
			model.put("role", rolelist.get(0));
			String menus = "";
			for(int i=0;i<menu.size();i++){
				if(i == menu.size()-1){
					menus+=menu.get(i);
				}else{
					menus+=menu.get(i)+",";
				}
			}
			model.put("menus", menus);
			return "/system/org/role/roleForm";
	 }
	 
	 @RequestMapping(value="update")
		@ResponseBody
		public void update(Role o,HttpServletRequest req,HttpServletResponse resp){
			AjaxRes ar = getAjaxRes();
				try{
					if(o.getName()==null||"".equals(o.getName().trim())){
						throw new Exception();
					}
					if(o.getId()==null||o.getId()==""){
						add(o);
					}else{
						o.setUpdateTime(new Date());
						roleService.update(o);
					}
					resp.getWriter().write(createcallbackjson("200","保存成功!","","","closeCurrent","",""));
				}catch(Exception e){
					logger.error(e.toString(),e);
					ar.setFailMsg(Const.DATA_FAIL);
				}
		}
	
	 
	 public String createcallbackjson(String status,String message,String navTabId,String rel, String callbacktype, String forwardUrl, String confirMsg){
	    	String callbackjson = "";
	    	if(Tools.isEmpty(message)){
	    		message = "";
	    	}
	    	if(Tools.isEmpty(navTabId)){
	    		navTabId = "";
	    	}
	    	if(Tools.isEmpty(rel)){
	    		rel = "";
	    	}
	    	if(Tools.isEmpty(callbacktype)){
	    		callbacktype = "";
	    	}
	    	if(Tools.isEmpty(forwardUrl)){
	    		forwardUrl = "";
	    	}
	    	if(Tools.isEmpty(confirMsg)){
	    		confirMsg = "";
	    	}
	    	callbackjson = "{\"statusCode\":\""+status+"\",\"message\":\""+message+"\",\"navTabId\":\""+navTabId+"\",\"rel\":\""+rel+"\",\"callbackType\":\""+callbacktype+"\",\"forwardUrl\":\""+forwardUrl+"\",\"confirmMsg\":\""+confirMsg+"\"}";
	    	return callbackjson;

	    }
	 
	 @RequestMapping("save")
		@ResponseBody
		public void save(BaseRole o,HttpServletRequest req,HttpServletResponse resp,String rolemenus){
		 		PrintWriter pw = null;
				try{
					pw =  resp.getWriter();
					if(Tools.isEmpty(o.getId())){
						o.setId(get32UUID());
					}
					String hosnum = "";//BAS_USER_ROLE_RIGHT表里有hosnum的字段  但是不知道用不用得着分医院  暂时传空值   
					List<BaseRight> rolemenuslist = new ArrayList();
					if(Tools.notEmpty(rolemenus)){
						String[] chk =rolemenus.split(",");
						for(int i = 0;i<chk.length;i++){
							if(chk[i].equals("root")){
								continue;
							}
							BaseRight bri = new BaseRight();
							bri.setUser_role_id(o.getId());
							bri.setHosnum(hosnum);
							bri.setMenu_id(chk[i]);
							rolemenuslist.add(bri);
						}
					}
					roleService.save(o,rolemenuslist,hosnum);
					pw.write(createcallbackjson("200","保存成功!","","","closeCurrent","",""));
				}catch(Exception e){
					
					logger.error(e.toString(),e);
					pw.write(createcallbackjson("300","保存失败!","","","","",""));
				}
		}
	 
	 
	 @RequestMapping(value="delrole")
		@ResponseBody
		public DwzResJson delsysmenu(String ids,HttpServletRequest req,HttpServletResponse resp){
			DwzResJson resJson = new DwzResJson();
				try {
					String[] chk =ids.split(",");
					List<String> idslist = new ArrayList();
					for(int i = 0;i<chk.length;i++){
						idslist.add(chk[i]);
					}
					roleService.delrole(idslist);
				} catch (Exception e) {
					resJson.error();
				}
			return resJson;
			}
	 
	 @RequestMapping("/getRleTree")
		public void getmenutree(HttpServletRequest resq, HttpServletResponse resp){
			List<BaseRole> menutree = roleService.get_role_tree();
			try {
				PrintWriter pw = resp.getWriter();
				JSONArray jsons = JSONArray.fromObject(menutree);
				pw.write(jsons.toString());
				pw.close();
			} catch (IOException e) {
				logger.error(e.toString(),e);
			}
		}
	 
	 @RequestMapping("getOrgTreeWithosnum")
	 @ResponseBody
	 public AjaxRes getOrgTreeWithosnum(HttpServletRequest request,String hosnum){
			AjaxRes ar=getAjaxRes();
				try {
					List<ZNodes> r=orgService.getOrgTree(hosnum);
					for(int i=1;i<r.size();i++){
						if(r.get(i).getId()==null){
							r.get(i).setIconSkin("person");
						}
					}
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("list", r);
					ar.setSucceed(m);
				} catch (Exception e) {
					logger.error(e.toString(),e);
					ar.setFailMsg(Const.DATA_FAIL);
				}
			return ar;
		}	 
}
