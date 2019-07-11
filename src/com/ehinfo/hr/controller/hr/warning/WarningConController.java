package com.ehinfo.hr.controller.hr.warning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.service.hr.warning.WarningConService;

@Controller
@RequestMapping("/backstage/warningCon/")
public class WarningConController extends BaseController<WarningCon> {
	@Autowired
	private WarningConService warnConService;

	@RequestMapping("hulue")
	@ResponseBody
	public void hulue(HttpServletRequest request,HttpServletResponse response,String id,String pageid){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			WarningCon con = new WarningCon();
			con.setId(id);
			con.setWarn_status("H");
			warnConService.updateStatus(con);
			pw.write(EhUtil.createcallbackjson("200", "此条预警将不再提示！", "", "empRef"+pageid, "", "", "", ""));
		} catch (Exception e) {
			logger.error(e.toString(),e);
			pw.write(EhUtil.createcallbackjson("300", "操作失败！", "", "", "", "", "", ""));
		}
	}
	@RequestMapping("yidu")
	@ResponseBody
	public void yidu(HttpServletRequest request,HttpServletResponse response,String id,String pageid){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			WarningCon con = new WarningCon();
			con.setId(id);
			con.setWarn_status("Y");
			warnConService.updateStatus(con);
			pw.write(EhUtil.createcallbackjson("200", "已读！", "", "empRef"+pageid, "", "", "", ""));
		} catch (Exception e) {
			logger.error(e.toString(),e);
			pw.write(EhUtil.createcallbackjson("300", "操作失败！", "", "", "", "", "", ""));
		}
	}
}
