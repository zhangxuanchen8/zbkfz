package com.ehinfo.hr.controller.system.verifyCode;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.verifyCode.VerifyCodeUtil;
import com.ehinfo.hr.controller.base.BaseController;


/** 
 * 生成验证码
 * @version
 */
@Controller
@RequestMapping("/verifyCode")
public class VerifyCodeController extends BaseController<Object>{

	/**
	 * 生成登录验证码
	 * @return
	 */
	@RequestMapping(value="/slogin")
	public void slogin(HttpServletResponse response,HttpServletRequest request){
	       //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //为了手机客户端方便使用数字验证码
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);		
		  
		HttpSession session = request.getSession();
		session.setAttribute(Const.SESSION_SECURITY_CODE, verifyCode);	
		try {
	        //设置输出的内容的类型为JPEG图像
	        response.setContentType("image/jpeg");
	        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null,null);
			 //写给浏览器
	        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.toString(),e);
		}
	}
	
}
