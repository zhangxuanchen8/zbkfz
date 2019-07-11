package com.ehinfo.hr.controller.zhibiao;

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

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.BaseRole;
import com.ehinfo.hr.entity.system.org.Role;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.zhibiao.Choose_DeptService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/choose_dept/")
public class Choose_DeptController extends BaseController<Choose_Dept>{
    @Autowired
    private Choose_DeptService service;
	
	@RequestMapping("index")	
	public String index(ModelMap model,String numPerPage,String pageNum,String z_name,HttpServletRequest request){
			Page<Choose_Dept> p = new Page<Choose_Dept>();
			Choose_Dept c1 = new Choose_Dept();
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			c1.setHosnum(a.getHosnum());
			
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
			
			Page<Choose_Dept> page_dept = new Page<Choose_Dept>();
			List<Choose_Dept> list_dept = new ArrayList<Choose_Dept>();
			if(z_name!=null){
				if(!"".equals(z_name)){
					c1.setZ_name(z_name);
				}
			}
			
			page_dept = service.findByPage(c1, p);
			list_dept = page_dept.getResults();
			if(list_dept==null){
				model.put("pageNum", "1");
				model.put("totalcount", page_dept.getTotalRecord());
				model.put("numPerPage", page_dept.getPageSize());
				model.put("dept_list", list_dept);
				return "/ehr/zhibiao/choose_deptTable";
			}
			model.put("pageNum", page_dept.getPageNum());
			model.put("totalcount", page_dept.getTotalRecord());
			model.put("numPerPage", page_dept.getPageSize());
			model.put("dept_list", list_dept);
			model.put("z_name", z_name);
			
			return "/ehr/zhibiao/choose_deptTable";
	}
	
	
	 @RequestMapping("addForm")	
		public String addForm(ModelMap model,HttpServletRequest req,HttpServletResponse res,Choose_Dept o){
			if(o.getKey_id()!=null&&!"".equals(o.getKey_id())){
				o=service.findById(o);
				model.put("dept", o);
				return "/ehr/zhibiao/choose_deptAddForm";
			}
			HttpSession session = req.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			o.setHosname(a.getHosname());
			o.setHosnum(a.getHosnum());
			model.put("dept", o);
			return "/ehr/zhibiao/choose_deptAddForm";
	 }
	 
	 
	@RequestMapping(value="addorUpDept",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson exactQuerySave(HttpServletRequest request,ModelMap model,Choose_Dept o){
		DwzResJson resJson = new DwzResJson();
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			try {
				if(o.getKey_id()!=null&&!"".equals(o.getKey_id())){
					service.update(o);
					resJson.setCallbackType("closeCurrent");
					resJson.setMessage("修改成功!");
				 }else{
					o.setKey_id(UuidUtil.get32UUID());
					o.setHosnum(a.getHosnum());
					service.insert(o);
					resJson.setCallbackType("closeCurrent");
					resJson.setMessage("保存成功!");
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 return resJson;
		}
	
	@RequestMapping(value="delDept")
	@ResponseBody
	public DwzResJson delDept(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
		int j = 0;
			try {
				String[] chk =ids.split(",");
				for(int i = 0;i<chk.length;i++){
					Choose_Dept o = new Choose_Dept();
					o.setKey_id(chk[i].trim());
					j = service.deleteDept(o);
				}
				if(j == 1) {
					resJson.setMessage("删除成功!");
				}else {
					resJson.setMessage("删除失败!");
				}
			} catch (Exception e) {
				resJson.error();
			}
		return resJson;
	}
	
	@RequestMapping(value="checkZ_name")
	@ResponseBody
	public DwzResJson checkZ_name(Choose_Dept o,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
			try {
				int i=service.checkZ_name(o);
				if(i>0) {
					resJson.setMessage("fail");
				}else {
					resJson.setMessage("success");
				}
			} catch (Exception e) {
				resJson.setMessage("fail");
				resJson.error();
			}
		return resJson;
	}
	
	
	@RequestMapping("getZhiBiaoGroupTree")
	public void  getZhiBiaoGroupTree(HttpServletRequest request,HttpServletResponse response){
		List<Choose_Dept> g_dept = null;
			try {
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				Choose_Dept o = new Choose_Dept();
				o.setHosnum(a.getHosnum());
				g_dept = service.getZhiBiaoGroupTree(o);
				String temp = "{id:\"" +"1"+ "\"," + "pId:\"" + "0" + "\"," + "name:\""+ "全部" + "\"}";
				List<String> listTree = new ArrayList<String>();
				listTree.add(temp);
				for (Choose_Dept h : g_dept) {
					temp = "{id:\"" +h.getKey_id()+ "\"," + "pId:\"" + "1" + "\"," + "name:\""
							+ h.getZ_name() + "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
			}
	}
	
}
