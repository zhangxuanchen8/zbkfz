package com.ehinfo.hr.controller.base;


import com.ehinfo.hr.common.ajax.AjaxRes;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.common.utils.webpage.PageData;
import com.ehinfo.hr.entity.system.resources.Resources;
import com.ehinfo.hr.service.system.resources.ResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;



public class BaseController<T> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ResourcesService resourcesService;
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	public AjaxRes getAjaxRes(){
		return new AjaxRes();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();	
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){	
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	public <T> Page<T> getPage(){	
		return new Page<T>();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	/**
	 * 资源的权限（显示级别）
	 * @param type 资源类别
	 * @return
	 */
	public List<Resources> getPermitBtn(String type){
		List<Resources> perBtns=new ArrayList<Resources>();	
		/*try {
			String menu=getPageData().getString("menu");
			if(StringUtils.isNotBlank(menu)){
				String menuNum=menu.replaceAll("menu","");
				String userId=AccountShiroUtil.getCurrentUser().getAccountId();
				perBtns=resourcesService.findBtn(type,menuNum,userId);
			}	
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}*/
		return perBtns;
	}
	/**
	 * 资源的权限（URl级别）
	 * @param type 资源类别(优化速度)
	 * @return
	 */
	protected boolean doSecurityIntercept(String type){
		/*try {
			String servletPath = getRequest().getServletPath();
			servletPath = StringUtils.substringBeforeLast(servletPath,".");// 去掉后面的后缀
			String userId=AccountShiroUtil.getCurrentUser().getAccountId();
			List<Resources> authorized=resourcesService.resAuthorized(userId,type);
			for(Resources r:authorized){
				
				if(r!=null&&StringUtils.isNotBlank(r.getResUrl())){
					if(StringUtils.equals(r.getResUrl(),servletPath)){
						return true;
					}
				}
				
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return false;*/
		return true;
	}
	
	/**
	 * 资源的权限（URl级别,拥有第一级资源权限，这资源才能访问）
	 * @param type 资源类别(优化速度)
	 * @param url 第一级资源
	 * @return
	 */
	protected boolean doSecurityIntercept(String type,String url){
		/*try {
			String userId=AccountShiroUtil.getCurrentUser().getAccountId();
			List<Resources> authorized=resourcesService.resAuthorized(userId,type);
			for(Resources r:authorized){
				if(r!=null&&StringUtils.isNotBlank(r.getResUrl())){
					if(StringUtils.equals(r.getResUrl(),url)){
						return true;
					}
				}		
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}*/
		return false;
	}
}
