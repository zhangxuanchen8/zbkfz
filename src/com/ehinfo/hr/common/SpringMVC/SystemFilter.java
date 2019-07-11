package com.ehinfo.hr.common.SpringMVC;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.entity.system.user.BasUser;

public class SystemFilter implements Filter {

	static String [] PASS=new String[]{"/backstage/loginIndex","/backstage/system_login","/backstage/index"};
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = request.getSession(true);
		BasUser user =(BasUser) session.getAttribute(Const.SESSION_USER);
		boolean flag = false;
		System.out.println("\n---start------------------------"+request.getRequestURI()+"-------------------------------\n");
		for(int i=0;i<PASS.length;i++){
			if((request.getRequestURI()).indexOf(PASS[i])>=0){
				flag = true;
			}
		}
		if(!flag){
			if(user==null){
				String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/backstage/loginIndex";
				response.sendRedirect(path);
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else{
			try {
				arg2.doFilter(arg0, arg1);	
			} catch (Exception e) {
				
				logger.error(e.toString(),e);
				String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/backstage/loginIndex";
				response.sendRedirect(path);
			}
		}
		System.out.println("\n--end-------------------------"+request.getRequestURI()+"-------------------------------\n");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {	
		
	}
}
