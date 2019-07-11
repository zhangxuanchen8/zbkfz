package com.ehinfo.hr.controller.system.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.service.system.org.OrgServiceImp;
import com.ehinfo.hr.service.system.user.BasUserService;

@Controller
@RequestMapping("/backstage/baseUser")

public class BasUserController extends BaseController<BasUser>{		
	@Autowired
	private OrgServiceImp deptService;
	@Autowired
	private BasUserService userService;

	@RequestMapping("/baseUserIndex")	
	public String index(ModelMap model,HttpServletRequest request,String dept_baseUser,String pageNum,String numPerPage,String baseUserName,String thedept_baseUser,String thedeptname_baseUser){
			Page<BasUser> page = new Page<BasUser>();
			BasUser user = new BasUser();
			//--------------------------------------加医院限制条件-------------------------------------
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			String hosnum = a.getHosnum();
			user.setHosnum(hosnum);
			//--1、分页查询
			if(dept_baseUser!=null||!"".equals(dept_baseUser)){
				user.setPerson_dept(dept_baseUser);
			}
			if(baseUserName!=null||!"".equals(baseUserName)){
				user.setName(baseUserName);
			}
			if(pageNum==null || "".equals(pageNum)){
				page.setPageNum(1);
			}else{
				page.setPageNum(Integer.parseInt(pageNum));
			}
			if(numPerPage==null || "".equals(numPerPage)){
				page.setPageSize(30);
			}else{
				page.setPageSize(Integer.parseInt(numPerPage));
			}
			if(dept_baseUser==null||"".equals(dept_baseUser)){
				page = userService.findByPage(user, page);
			}else if(dept_baseUser.indexOf(",")<0){
				
				List<BasUser> dept = new ArrayList<BasUser>();
				BasUser css = new BasUser();
				css.setPerson_dept(dept_baseUser);
				dept.add(css);
				
				page = userService.findByPageDWZ(user, page,dept);
			}else{
				String depts [] = dept_baseUser.split(",");
				List<BasUser> dept = new ArrayList<BasUser>();
				
				for(int i =0;i<depts.length;i++){
					BasUser css = new BasUser();
					css.setPerson_dept(depts[i]);
					dept.add(css);
				}
				page = userService.findByPageDWZ(user, page,dept);		
			}
			//--2、+查询条件
			model.put("page", page);
			model.put("baseUserName", baseUserName==null?"":baseUserName);
			model.put("thedept_baseUser", thedept_baseUser==null?"":thedept_baseUser);
			model.put("thedeptname_baseUser", thedeptname_baseUser==null?"":thedeptname_baseUser);
			model.put("dept_baseUser", dept_baseUser==null?"":dept_baseUser);
			return "/system/user/baseUserList";
	}

	@RequestMapping("/baseUserIndexN")
	public String indexN(ModelMap model, HttpServletRequest request, String dept_baseUser, String pageNum,
			String numPerPage, String baseUserName, String thedept_baseUser, String thedeptname_baseUser) {
		Page<BasUser> page = new Page<BasUser>();
		BasUser user = new BasUser();
		// --------------------------------------加医院限制条件-------------------------------------
		String dept_baseUser_new = request.getParameter("depts");
		String thedept_baseUser_new = request.getParameter("thedept");
		String thedeptname_baseUser_new = request.getParameter("deptname");
		if (dept_baseUser_new != null && !"".equals(dept_baseUser_new)) {
			thedept_baseUser = thedept_baseUser_new;
			dept_baseUser = dept_baseUser_new;
			thedeptname_baseUser = thedeptname_baseUser_new;
		}
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		user.setHosnum(hosnum);
		// --1、分页查询
		if (dept_baseUser != null || !"".equals(dept_baseUser)) {
			user.setPerson_dept(dept_baseUser);
		}
		if (baseUserName != null || !"".equals(baseUserName)) {
			user.setName(baseUserName);
		}
		if (pageNum == null || "".equals(pageNum)) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || "".equals(numPerPage)) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		if (dept_baseUser == null || "".equals(dept_baseUser)) {
			page = userService.findByPage(user, page);
		} else if (dept_baseUser.indexOf(",") < 0) {
			List<BasUser> dept = new ArrayList<BasUser>();
			BasUser css = new BasUser();
			css.setPerson_dept(dept_baseUser);
			dept.add(css);
			page = userService.findByPageDWZ(user, page, dept);
		} else {
			String depts[] = dept_baseUser.split(",");
			List<BasUser> dept = new ArrayList<BasUser>();
			for (int i = 0; i < depts.length; i++) {
				BasUser css = new BasUser();
				css.setPerson_dept(depts[i]);
				dept.add(css);
			}
			page = userService.findByPageDWZ(user, page, dept);
		}
		// --2、+查询条件
		model.put("page", page);
		model.put("baseUserName", baseUserName == null ? "" : baseUserName);
		model.put("thedept_baseUser", thedept_baseUser == null ? "" : thedept_baseUser);
		model.put("thedeptname_baseUser", thedeptname_baseUser == null ? "" : thedeptname_baseUser);
		model.put("dept_baseUser", dept_baseUser == null ? "" : dept_baseUser);
		return "/system/user/baseUserTable";
	}
	
	
	@RequestMapping("/addForm")
	public String addForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String thedept_baseUser = request.getParameter("thedept_baseUser");
		String thedeptname_baseUser = request.getParameter("deptname");
		String userId = request.getParameter("userID");
		BasUser user = new BasUser();
		if (userId != null && !"".equals(userId)) {
			user.setId(userId);
			user = userService.findById(user);
			user.setRoles(userService.getAllRolesId(user));
		}
		model.put("theEditUser", user);
		model.put("thedept_baseUser", thedept_baseUser == null ? "" : thedept_baseUser);
		model.put("thedeptname_baseUser", thedeptname_baseUser == null ? "" : thedeptname_baseUser);
		return "/system/user/baseUserAddForm";
	}
	@RequestMapping("/addUser")
	public void addUser(
			String id,
			String hosnum,
			String user_key,
			String password,
			String name,
			String idcard,
			String sex,
			String birthdate,
			String phone,
			String mobile,
			String short_mobile,
			String email,
			String post,
			String post_code,
			String index_no,
			String reg_date,
			String stop_sign,
			String del_sign,
			String remark,
			String input_cpy,
			String input_cwb,
			String input_custom,
			String job_no,
			String nodecode,
			String person_dept,
			String ehruser_key,
			String ehrpassword,
			String ehrrole,
			String clcpower,
			String deptname,
			String roleSelects,
			String zc,
			HttpServletResponse response,HttpServletRequest request){
		DwzResJson ar=new DwzResJson();	
			try {
				//------------修改以delete+insert来完成-------------
				BasUser user = new BasUser(id, hosnum, user_key, password, name, idcard, sex, null, phone, mobile, short_mobile, email, post, post_code, Integer.parseInt(index_no), null, stop_sign, del_sign, remark, input_cpy, input_cwb, input_custom, job_no, nodecode, person_dept, ehruser_key, ehrpassword, ehrrole, clcpower, zc);
				//---------获取部门节点ID对应的hosnum---作为hosnum
				Org dept = new Org();
				dept.setId(person_dept);
				dept = deptService.findById( dept);
				hosnum = dept.getHosnum();
				user.setStop_sign("N");
				user.setDel_sign("N");
				user.setHosnum(hosnum);
				user.setReg_date(new Date());
				if(birthdate!=null&&!"".equals(birthdate)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date bdate=sdf.parse(birthdate);
					user.setBirthdate(bdate);
				}
				//--------------去重复-------------
				List<BasUser> list = userService.find(user);
				if((id==null||"".equals(id))&&list.size()>0){
					ar.error("用户名重复！");
				}else{
					user.setRoles(roleSelects);
					String msg = userService.insert1(user);
					if(msg=="0"){
						ar.error("用户名重复！");
					}else{
						ar.setRel("userAddForm");
						ar.setCallbackType("closeCurrent");
						ar.success(Const.SAVE_SUCCEED);
					}
				}
				response.getWriter().print(ar.toJson());
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.error(Const.SAVE_FAIL);
				try {
					response.getWriter().print(ar.toJson());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
	
	@RequestMapping("changePsd")	
	public String changePsd(Model model,HttpServletRequest request){
		return "system/changepwd";
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson delBatch(String ids){
		DwzResJson ar=new DwzResJson();
		try {
				userService.deleteBatchBasUser(ids);
				ar.setRel("userAddForm");
				ar.success(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.error(Const.DEL_FAIL);
			}
		return ar;
	}
	
	
	@RequestMapping(value="preResetPWD", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson resetPWD(String pwschange_oldpassword,String pwschange_newpassword,String pwschange_secpassword,HttpServletRequest request){
		HttpSession session = request.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson dwz = new DwzResJson();
		try {
			int res=userService.preResetPwd(pwschange_oldpassword,pwschange_newpassword,pwschange_secpassword,a);
			if     (res==1){
				
				dwz.setStatusCode("200");
				dwz.setMessage("密码修改成功！");
				dwz.setCallbackType("closeCurrent");
				return dwz;
			}
			else if(res==2){
				dwz.setStatusCode("300");
				dwz.setMessage("旧密码输入错误！");
				return dwz;
			}			
			else if(res==3){
				dwz.setStatusCode("300");
				dwz.setMessage("旧密输入重复！");
				return dwz;
			}		
		} catch (Exception e) {
			logger.error(e.toString(),e);
			dwz.setStatusCode("300");
			dwz.setMessage("密码修改失败！");
			return dwz;
		}
		return dwz;
	}
}
