package com.ehinfo.hr.controller.system.login;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ehinfo.hr.common.utils.IPUtil;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.webpage.PageData;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.employee.Employee;
import com.ehinfo.hr.entity.system.log.LoginLog;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.service.system.log.LoginLogService;
import com.ehinfo.hr.service.system.user.BasUserService;
import com.ehinfo.hr.service.system.user.CpUserService;


@Controller
@RequestMapping("/backstage")
public class LoginController extends BaseController<Object>{
	
	@Autowired
	private BasUserService userService;
	@Autowired
	private LoginLogService loginLogService;

	@Autowired
	private CpUserService cpService;
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/loginIndex")
	public String toLogin(ModelMap model,HttpServletRequest request,HttpServletResponse response ){
		
		String forgin = request.getHeader("Referer");
		
		String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
		
		
		
		
		if(forgin!=null&&forgin.indexOf("/backstage/")>-1){
			url = url.replace(".htm", "");
			forgin = forgin.replace(".htm", "");
			if(forgin!=null&&forgin.equals(url)){
				model.put("login_flag", "1");
			}else{
				model.put("login_flag", "0");
			}
		}else{
			model.put("login_flag", "1");
		}
		return "system/login/login";
	}
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/system_login" ,produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").split(",jy,");
		HttpSession session = request.getSession();
		String username = KEYDATA[0];
		String cpuser="";
		String password  = KEYDATA[1];	
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			errInfo = "nullup";	//缺少用户名或密码
		}else{
			try {
				BasUser u = userService.findFormatByLoginName(username);
				if (u != null) {
					if(u.getPassword().equals(password)){
						session.setAttribute(Const.SESSION_USER, u);
					}else{
						errInfo = "mmcw"; /*错误认证异常*/
					}    	
				} else {
					CpUser cp =cpService.findById(username);
					
					if(!"".equals(cp.getKqdate())&&cp.getKqdate()!=null){
								if(cp!=null){
									if(cp.getPassword().equals(password)){
										session.setAttribute(Const.SESSION_USER, cp);
										cpuser = "cp";	
									}else{
										errInfo = "mmcw"; /*错误认证异常*/
									}
								}
					}else{
						errInfo = "inactive";//未上传
					}
					
				}	
			}catch (Exception e) {
				logger.error(e.toString(),e);
				errInfo = "codeerror";// 验证未通过
			}
			if(StringUtils.isEmpty(errInfo)){
				errInfo = "success";					//验证成功
					//记录登录日志
				String accountId;
				String loginIP=IPUtil.getIpAddr(getRequest());//获取用户登录IP
				if(StringUtils.isEmpty(cpuser)){
					accountId=((BasUser)session.getAttribute(Const.SESSION_USER)).getId();
				}else{
					accountId=((CpUser)session.getAttribute(Const.SESSION_USER)).getUser_key();
				}
				LoginLog loginLog=new LoginLog(accountId,loginIP);
				loginLogService.saveLoginLog(loginLog);
			}
		}
		map.put("result", errInfo);
		map.put("cpuser", cpuser);
		return map;
	}		
	  /**
     * 帐号注销
     * @return
     */
    @RequestMapping("/system_logout")
    public String logout(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
        
    	
		
	
			session.removeAttribute(Const.SESSION_USER);
			session.removeAttribute(Const.SESSION_MENULIST);
			return "redirect:/";
        //return "redirect:loginIndex.htm";
    }
    
   
}
