package com.ehinfo.hr.controller.system.sysmenu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.service.system.sysmenu.SysMenuServiceImp;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/sysmenu/")
public class SysMenuController extends BaseController<BaseMenu>{
	@Autowired
	public SysMenuServiceImp service;
	
	@RequestMapping("/sysmenuList")
    public String show(ModelMap model,HttpServletRequest request,String numPerPage,String pageNum,String menuAddUrl,String pId, String name,String id){
		String theId = request.getParameter("theId");
		String thePid = request.getParameter("thePid");
		String addUrl = request.getParameter("addUrl");
		String relFlag = request.getParameter("relFlag");
		if(theId !=null && !"".equals(theId)){
			id = theId;
		}
		if(thePid !=null && !"".equals(thePid)){
			pId = thePid;
		}
		if(addUrl !=null && !"".equals(addUrl)){
			model.put("addUrl", addUrl+"?pId="+id);
		}else{
			model.put("addUrl", menuAddUrl);
		}
		Page p = new Page();
		if(pageNum==null){
			p.setPageNum(1);
		}else{
			p.setPageNum(Integer.parseInt(pageNum));
		}
		if(numPerPage==null){
			p.setPageSize(20);
		}else{
			p.setPageSize(Integer.parseInt(numPerPage));
		}
		BaseMenu menu = new BaseMenu();
		if(Tools.isEmpty(pId)){
			menu.setpId("root");
		}else{
			menu.setpId(id);
		}
		//menu.setPid(pid);
		if(Tools.isEmpty(name)){
			menu.setName("");
		}else{
			menu.setpId("");
			menu.setId("");
			menu.setName("%"+name+"%");
		}
		Page<BaseMenu> bm = searchbypage(p,menu);
		if(bm==null){
			model.put("pageNum", "1");
			model.put("totalcount", bm.getTotalRecord());
			model.put("numPerPage", bm.getPageSize());
			model.put("pid", pId);
			model.put("id", id);
			model.put("name", name);
			if(relFlag !=null && !"".equals(relFlag)){
				return "/system/sysmenu/menuTable";
			}
			return "/system/sysmenu/menulist";
		}
		model.put("pageNum", bm.getPageNum());
		model.put("totalcount", bm.getTotalRecord());
		model.put("numPerPage", bm.getPageSize());
		model.put("menus", bm.getResults());
		model.put("pId", pId);
		model.put("id", id);
		model.put("name", name);
		if(relFlag !=null && !"".equals(relFlag)){
			return "/system/sysmenu/menuTable";
		}
		return "/system/sysmenu/menulist";
    }
	
	@RequestMapping("/getmenutree")
	public void getmenutree(HttpServletRequest resq, HttpServletResponse resp){
		List<BaseMenu> menutree = service.system_menu_tree();
		try {
			PrintWriter pw = resp.getWriter();
			JSONArray jsons = JSONArray.fromObject(menutree);
			pw.write(jsons.toString());
			pw.close();
		} catch (IOException e) {
			logger.error(e.toString(),e);
		}
	}

	@RequestMapping("/sysmenuform")
	public String sysmenuform(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model){
		BaseMenu bm = new BaseMenu();
		bm.setpId(pId);
		List<BaseMenu> bms = new ArrayList<BaseMenu>();
		if(Tools.isEmpty(id)){
			bms = service.searchbypid(bm);
			model.put("id", CreateTheChildId(pId,bms));
			model.put("pId", pId);
			return "/system/sysmenu/menuform";
		}
		bm.setId(id);
		bms = service.searchbyid(bm);
		bm = bms.get(0);
		model.put("id", bm.getId());
		model.put("pId", bm.getpId());
		model.put("name", bm.getName());
		model.put("url", bm.getUrl());
		model.put("image", bm.getImage());
		model.put("index_no", bm.getIndex_no());
		return "/system/sysmenu/menuform";
	}
	
	
	@RequestMapping(value="delsysmenu")
	@ResponseBody
	public DwzResJson delsysmenu(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res=service.delsysmenu(idslist);
				if(res==1){
					resJson.setRel("menuref");
					resJson.setMessage("删除成功!");
				}
				else      resJson.error("请先删除所有其子组织或角色");
			} catch (Exception e) {
				resJson.error();
			}
		return resJson;
		}

	@RequestMapping(value="addnew")
	@ResponseBody
	public void addnew(BaseMenu bm,HttpServletRequest req,HttpServletResponse resp){
		try{
			service.addnew(bm);
			resp.getWriter().write(EhUtil.createcallbackjson("200","保存成功!","","menuref","closeCurrent","","","backstage/sysmenu/index?pId="+bm.getpId()));
		}catch(Exception e){
			logger.error(e.toString(),e);
		}
	}


	
	
	public Page<BaseMenu> searchbypage(Page p,BaseMenu bm){
		Page<BaseMenu> menus = service.searchbypage(p,bm);
		return menus;
	}
	
	/***
	 * 通过PID 创建ID
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	String CreateTheChildId(String pid , List<BaseMenu> bsms)   {
		
		String cId = "";
		String tPid = pid;
		
		if("root".equals(pid))
			tPid = "";
		if(bsms.size()>0){
			cId = bsms.get(0).getId();
			cId = cId.substring(tPid.length(),cId.length());
			int cIdInt = Integer.parseInt(cId) + 1;
			if(cIdInt < 10){
				cId = tPid + "0" + cIdInt;
			}else{
				cId = tPid + cIdInt;
			}
		}else{
			cId = tPid + "01";
		}
		return cId;
	}
	
}
