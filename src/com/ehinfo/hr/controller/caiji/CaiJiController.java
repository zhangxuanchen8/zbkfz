package com.ehinfo.hr.controller.caiji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.achieve.Achieve;
import com.ehinfo.hr.entity.caiji.CaiJi_pz;
import com.ehinfo.hr.entity.caiji.ShuJu_pz;
import com.ehinfo.hr.entity.caiji.TongJi_log;
import com.ehinfo.hr.entity.caiji.Upload_pz;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
//import com.ehinfo.hr.entity.zhibiaoku.zhibiaoku;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.achieve.AchieveService;
import com.ehinfo.hr.service.caiji.CaiJi_pzService;
import com.ehinfo.hr.service.caiji.ShuJu_pzService;
import com.ehinfo.hr.service.caiji.TongJi_logService;
import com.ehinfo.hr.service.caiji.Upload_pzService;
import com.ehinfo.hr.service.declare.PersonalRecordService;
import com.ehinfo.hr.service.system.dict.SysDictService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.new_ryzbkService;
import com.ehinfo.hr.service.zhibiao.ryzhibiaokuService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;
import com.ehinfo.hr.service.zhibiao.zhibiaoglService;
import com.ehinfo.hr.service.zhibiaoku.zhibiaokuService;
/**
 * 数据采集监控
 * @author admin
 *
 */
@Controller
@RequestMapping("/backstage/shujuljdy/")
public class CaiJiController extends BaseController<ShuJu_pz> implements Job{
	@Autowired
	private TongJi_logService rzservice;
	@Autowired
	private ShuJu_pzService sjpzservice;
	@Autowired
	private CaiJi_pzService cjpzservice;
	@Autowired
	private AchieveService cahservice;
	@Autowired
	private zhibiaokuService zbkservice;
	@Autowired
	private SysDictService sysservice;
	@Autowired
	private Upload_pzService uppzservice;
	@Autowired
	private ryzhibiaokuService ryzbkervice;
	@Autowired
	private zhibiao_sbService sbervice;
	@Autowired
	private zhibiaoglService zbkglervice;
	@Autowired
	private new_ryzbkService new_ryervice;
	@Autowired
	private ModelService modelervice;
	@Autowired
	private PersonalRecordService personal;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}



	@RequestMapping("sjpzindex")
	public String sjpzindex(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,ShuJu_pz shuju_pz,
							String totalcount) {
		Page<ShuJu_pz> page = new Page<ShuJu_pz>();
		if (pageNum == null || pageNum.equals("")) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || numPerPage.equals("")) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		page = sjpzservice.findByPage(shuju_pz, page);
		model.put("sjpz",page.getResults());
		return "/ehr/caiji/shujulypz";
	}

	@RequestMapping("shujubj")
	public String shujubj(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request) {
		String id = request.getParameter("id");
		ShuJu_pz sjpz= new ShuJu_pz();
		if(id!=null&&!"".equals(id)){
			sjpz.setId(id);
			sjpz=sjpzservice.findById(sjpz);
			model.put("sjpz", sjpz);
		}
		return "/ehr/caiji/shujubj";
	}

	@RequestMapping(value="sjsave",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson sjsave(HttpServletRequest request,ModelMap model,ShuJu_pz o,HttpSession session){
		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson resJson = new DwzResJson();
		try {
			if(o.getId()!=null&&!"".equals(o.getId())){
				o.setUpdate_man(user.getHosnum());
				sjpzservice.update(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}else{
				o.setId(get32UUID());
				o.setUpdate_man(user.getHosnum());
				sjpzservice.insert(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resJson;
	}

	@RequestMapping(value="delShujupz")
	@ResponseBody
	public DwzResJson delShujupz(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
		try {
			String[] chk =ids.split(",");
			List<String> idslist = new ArrayList<String>();
			for(int i = 0;i<chk.length;i++){
				idslist.add(chk[i]);
			}
			int res=sjpzservice.deletelist(idslist);
			if(res==1){
				resJson.setMessage("删除成功!");
			}
		} catch (Exception e) {
			resJson.error();
		}
		return resJson;
	}


	@RequestMapping("cjpzindex")
	public String cjpzindex(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,CaiJi_pz cjpz,String code,
							String totalcount) {
		Page<CaiJi_pz> page = new Page<CaiJi_pz>();
		if (pageNum == null || pageNum.equals("")) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || numPerPage.equals("")) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		if(code!=null&&!"".equals(code)){
			cjpz.setCaijimc("%"+code+"%");
		}
		page = cjpzservice.findByPage(cjpz, page);
		model.put("cjpz",page.getResults());
		return "/ehr/caiji/caijipz";
	}

	@RequestMapping("caijibj")
	public String caijibj(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,HttpSession session) {
		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		String id = request.getParameter("id");
		CaiJi_pz cjpz= new CaiJi_pz();
		List<zhibiaoku> zbcj = null;
		List<ShuJu_pz> sjpz = null;
		sjpz=sjpzservice.findByname();
		//zbcj=zbkservice.findzbkcj(user.getHosnum(),"");
		model.put("zblist", zbcj);
		model.put("sjlist", sjpz);
		if(id!=null&&!"".equals(id)){
			cjpz.setId(id);
			cjpz=cjpzservice.findById(cjpz);
			model.put("cjpz", cjpz);
		}
		return "/ehr/caiji/caijibj";
	}


	@RequestMapping("cjpzindex1")
	public String cjpzindex1(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,CaiJi_pz cjpz,String code,
							 String totalcount) {
		Page<CaiJi_pz> page = new Page<CaiJi_pz>();
		if (pageNum == null || pageNum.equals("")) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || numPerPage.equals("")) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		if(code!=null&&!"".equals(code)){
			cjpz.setCaijimc("%"+code+"%");
		}
		page = cjpzservice.findByPage(cjpz, page);
		model.put("cjpz",page.getResults());
		return "/ehr/caiji/caijisdpz";
	}

	@RequestMapping("caijibj1")
	public String caijibj1(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,HttpSession session) {
		String id = request.getParameter("id");
		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		CaiJi_pz cjpz= new CaiJi_pz();
		List<zhibiaoku> zbcj = null;
		List<ShuJu_pz> sjpz = null;
		List<String> month =null;
		sjpz=sjpzservice.findByname();
		//zbcj=zbkservice.findzbkcj(user.getHosnum(),"");
		month=cahservice.getsjd(user.getHosnum());
		model.put("zblist", zbcj);
		model.put("monthcaiji", month);
		model.put("sjlist", sjpz);
		if(id!=null&&!"".equals(id)){
			cjpz.setId(id);
			cjpz=cjpzservice.findById(cjpz);
			model.put("cjpz", cjpz);
		}
		return "/ehr/caiji/caijisdbj";
	}


	@RequestMapping(value="cjsave",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson cjsave(HttpServletRequest request,ModelMap model,CaiJi_pz o,HttpSession session){
//		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson resJson = new DwzResJson();
//		String caitime="";
		try {
			if(o.getId()!=null&&!"".equals(o.getId())){
				//o.setZhouqi(caitime);
				cjpzservice.update(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("修改成功!");
			}else{
				o.setId(get32UUID());
				//o.setZhouqi(caitime);
				cjpzservice.insert(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resJson;
	}

	@RequestMapping(value="sdcjsave",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson sdcjsave(HttpServletRequest request,ModelMap model,CaiJi_pz o,HttpSession session,Date ccdate){
//		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson resJson = new DwzResJson();
		try {
			if(o.getId()!=null&&!"".equals(o.getId())){
				o.setCcdate(ccdate);
				cjpzservice.updatesdsj(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resJson;
	}

	@RequestMapping(value="delCaiJipz")
	@ResponseBody
	public DwzResJson delCaiJipz(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
		try {
			String[] chk =ids.split(",");
			List<String> idslist = new ArrayList<String>();
			for(int i = 0;i<chk.length;i++){
				idslist.add(chk[i]);
			}
			int res=cjpzservice.deletelist(idslist);
			if(res==1){
				resJson.setMessage("删除成功!");
			}
		} catch (Exception e) {
			resJson.error();
		}
		return resJson;
	}

	@RequestMapping(value="sdCaiJi")
	@ResponseBody
	public DwzResJson sdCaiJi(String id,HttpServletRequest req,HttpServletResponse resp,HttpSession session,String ccdate,String month){
		DwzResJson resJson = new DwzResJson();
		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		try {
			Page<CaiJi_pz> page = new Page<CaiJi_pz>();
			List<ShuJu_pz> sj1 = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			CaiJi_pz o = new CaiJi_pz();
			CaiJi_pz o1 = new CaiJi_pz();
			ShuJu_pz sj = new ShuJu_pz();
			List<Achieve> xfrq=null;
			TongJi_log log = new TongJi_log();
			List<TongJi_log> lilo = null;
			String xfsj="";
			o.setId(id);
			o1=cjpzservice.findById(o);
			String str = "";
			if(null!=o1){
				str = o1.getMx_sql();
            }
			if(!"".equals(ccdate)&&ccdate!=null){
				xfrq=cahservice.getxfrq(o1.getCaijimc(),"","");
				for(int i=0;i<xfrq.size();i++){
					if(xfrq.get(i).getXfrq()!=null&!"".equals(xfrq.get(i).getXfrq())){
						xfsj=sdf.format(xfrq.get(i).getXfrq());
						if(xfsj.equals(ccdate)){
							resJson.error("该日期已采集");
						}else{
							cahservice.delete(xfrq.get(i).getId());
							sj.setId(id);
							sj1=sjpzservice.findBysql(sj);
							for(int f=0;f<sj1.size();f++){
								jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), str, sj1.get(f).getPort(),"1",user.getHosnum(),ccdate);
							}
						}
					}else{
						//cahservice.delete(xfrq.get(i).getId());
						sj.setId(id);
						sj1=sjpzservice.findBysql(sj);
						for(int f=0;f<sj1.size();f++){
							jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), str, sj1.get(f).getPort(),"1",user.getHosnum(),ccdate);
						}
					}
				}

			}else if(!"".equals(month)&&month!=null){
				String[] sq = month.split(" ~ ");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date_util = sdf.parse(sq[0]); //转换为util.date
				java.util.Date date_util2 = sdf.parse(sq[1]); //转换为util.date
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date_util);
				while(date_util!=date_util2){
					System.out.println("****************************************");
					System.out.println(sdf2.format(date_util));
					System.out.println("****************************************");
					calendar.add(Calendar.DATE, 1);
					date_util =calendar.getTime();
					ccdate = sdf2.format(date_util);
					if(!Tools.dateCompare(ccdate,sq[0],sq[1])){
						break;
					}
                    boolean zxflag = true;
					if(!id.equals("RoyalNeverGiveUp")){
                        xfrq=cahservice.getxfrq(o1.getCaijimc(),sq[0],sq[1]);
                        for(int i=0;i<xfrq.size();i++){
                            zxflag = false;
                            if(xfrq.get(i).getXfrq()!=null&!"".equals(xfrq.get(i).getXfrq())){
                                xfsj=sdf.format(xfrq.get(i).getXfrq());
                                if(xfsj.equals(ccdate)){
                                    resJson.error("该日期已采集");
                                }else{
                                    cahservice.delete(xfrq.get(i).getId());
                                    sj.setId(id);
                                    sj1=sjpzservice.findBysql(sj);
                                    for(int f=0;f<sj1.size();f++){
                                        jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), str, sj1.get(f).getPort(),"1",user.getHosnum(),ccdate);
                                    }
                                }
                            }else{
                                //cahservice.delete(xfrq.get(i).getId());
                                sj.setId(id);
                                sj1=sjpzservice.findBysql(sj);
                                for(int f=0;f<sj1.size();f++){
                                    jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), str, sj1.get(f).getPort(),"1",user.getHosnum(),ccdate);
                                }
                            }
                        }
                    }

					if(zxflag){
						//cahservice.delete(xfrq.get(i).getId());
						Page<CaiJi_pz> page2 = new Page<CaiJi_pz>();
						CaiJi_pz cjz = new CaiJi_pz();
						page=cjpzservice.findByPage(cjz,page);

						if(id.equals("RoyalNeverGiveUp")){
							for(int i=0;i<page.getResults().size();i++){
								sj.setId(page.getResults().get(i).getId());
								sj1 = sjpzservice.findBysql(sj);
								for (int f = 0; f < sj1.size(); f++) {
									jdbcxz(sj1.get(f).getStype(), sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(), sj1.get(f).getSqlname(), sj1.get(f).getMx_sql(), sj1.get(f).getPort(), "1", user.getHosnum(), ccdate);
								}
							}
						}else {
							sj.setId(id);
							sj1 = sjpzservice.findBysql(sj);
							for (int f = 0; f < sj1.size(); f++) {
								jdbcxz(sj1.get(f).getStype(), sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(), sj1.get(f).getSqlname(), str, sj1.get(f).getPort(), "1", user.getHosnum(), ccdate);
							}
						}
					}


				}

						/*xfrq=cahservice.getxfrq(o1.getCaijimc());
						for(int i=0;i<xfrq.size();i++){
							if(xfrq.get(i).getXfrq()!=null&!"".equals(xfrq.get(i).getXfrq())){
								resJson.error("该日期已采集");
								xfsj=sdf.format(xfrq.get(i).getXfrq());
							}else{
								//cahservice.delete(xfrq.get(i).getId());
								sj.setId(id);
								sj1=sjpzservice.findBysql(sj);
								for(int f=0;f<sj1.size();f++){
									jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), str, sj1.get(f).getPort(),"1",user.getHosnum(),month);
								}
							}
						}*/
			}
			System.out.println("11");
		} catch (Exception e) {
			resJson.error();
			e.printStackTrace();
		}
		return resJson;
	}

	@RequestMapping("cjrzindex")
	public String cjrzindex(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,TongJi_log tj_log,
							Date caiji_date1,Date caiji_date2,String totalcount) {
		Page<TongJi_log> page = new Page<TongJi_log>();
		if (pageNum == null || pageNum.equals("")) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || numPerPage.equals("")) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if((caiji_date1!=null&&!"".equals(caiji_date1))&&(caiji_date2!=null&&!"".equals(caiji_date2))){
			tj_log.setCaiji_date1(sdf.format(caiji_date1));
			tj_log.setCaiji_date2(sdf.format(caiji_date2));
			model.put("caiji_date1", caiji_date1);
			model.put("caiji_date2", caiji_date2);
		}
		page = rzservice.findByPage(tj_log, page);
		model.put("rz_log",page.getResults());


		return "/ehr/caiji/caijirz";
	}

	@RequestMapping(value="caiji",method=RequestMethod.GET)
	@ResponseBody
	public void caiji(HttpServletRequest resq,HttpServletResponse response, String yryr, String pid,ModelMap model,zhibiaoku zhib, String supunit){
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
//			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			ShuJu_pz sj = new ShuJu_pz();
			List<ShuJu_pz> sj1 = null;
			sj.setId("f0026e0af0814924adbcdee181f5182");
			sj1=sjpzservice.findBysql(sj);
			for(int f=0;f<sj1.size();f++){
				jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), sj1.get(f).getMx_sql(), sj1.get(f).getPort(),"1","0000","");
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.fillInStackTrace();
		}
	}
	
	
	//上传配置
	@RequestMapping("uploadindex")
	public String uploadindex(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,Upload_pz uppz,String code,
							String totalcount) {
		Page<Upload_pz> page = new Page<Upload_pz>();
		if (pageNum == null || pageNum.equals("")) {
			page.setPageNum(1);
		} else {
			page.setPageNum(Integer.parseInt(pageNum));
		}
		if (numPerPage == null || numPerPage.equals("")) {
			page.setPageSize(30);
		} else {
			page.setPageSize(Integer.parseInt(numPerPage));
		}
		if(code!=null&&!"".equals(code)){
			uppz.setCaijimc("%"+code+"%");
		}
		page = uppzservice.findByPage(uppz, page);
		model.put("uploadpz",page.getResults());
		model.put("code",code);
		return "/ehr/caiji/uploadpz";
	}
	
	
	@RequestMapping("uploadbj")
	public String uploadbj(ModelMap model, String numPerPage, String pageNum, HttpServletRequest request,HttpSession session) {
		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		String id = request.getParameter("id");
		Upload_pz uppz= new Upload_pz();
		List<zhibiaoku> zbcj = null;
		List<ShuJu_pz> sjpz = null;
		sjpz=sjpzservice.findByname();
		//zbcj=zbkservice.findzbkcj(user.getHosnum(),"");
		model.put("zblist", zbcj);
		model.put("sjlist", sjpz);
		if(id!=null&&!"".equals(id)){
			uppz.setId(id);
			uppz=uppzservice.findById(uppz);
			model.put("uppz", uppz);
		}
		return "/ehr/caiji/uploadbj";
	}

	@RequestMapping(value="uploadsave",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson uploadsave(HttpServletRequest request,ModelMap model,Upload_pz o,HttpSession session){
//		BasUser user  = (BasUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson resJson = new DwzResJson();
		String caitime="";
		try {
			if(o.getId()!=null&&!"".equals(o.getId())){
				//o.setZhouqi(caitime);
				uppzservice.update(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("修改成功!");
			}else{
				o.setId(get32UUID());
				//o.setZhouqi(caitime);
				uppzservice.insert(o);
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resJson;
	}
	
	@RequestMapping(value="delUploadpz")
	@ResponseBody
	public DwzResJson delUploadpz(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
		try {
			String[] chk =ids.split(",");
			List<String> idslist = new ArrayList<String>();
			for(int i = 0;i<chk.length;i++){
				idslist.add(chk[i]);
			}
			int res=uppzservice.deletelist(idslist);
			if(res==1){
				resJson.setMessage("删除成功!");
			}
		} catch (Exception e) {
			resJson.error();
		}
		return resJson;
	}
	//0是没有获取到记录，1是获取到记录
	public String pdhosnum(String hosnum,String id){
		PreparedStatement ps=null;      //预编译的sql对象
		ResultSet rs=null;
		Connection ct=null;
		String s="0";
		try {
			String sql="SELECT * FROM center_pd where hosnum='"+hosnum+"' and center_id='"+id+"'";
			Class.forName("com.mysql.jdbc.Driver");
			ct=DriverManager.getConnection("jdbc:mysql://"+"127.0.0.1"+":"+"3306"+"/"+"zhibiaofz"+"?useUnicode=true&characterEncoding=UTF-8","root","1");
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getString(1)!=null){
					s="1";
					break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Page<CaiJi_pz> page = new Page<CaiJi_pz>();
		Page<Upload_pz> page1 = new Page<Upload_pz>();
		CaiJi_pz cjz = new CaiJi_pz();
		Upload_pz upz = new Upload_pz();
		ShuJu_pz sj = new ShuJu_pz();
		ShuJu_pz sj0 = new ShuJu_pz();
		List<ShuJu_pz> sj1 = null;
		List<ShuJu_pz> sj2 = null;
		Calendar now = Calendar.getInstance();
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		String month=now.get(Calendar.HOUR_OF_DAY)+"";
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String year = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1);
		try {
			Date date0 = df.parse(year);
			page1=uppzservice.findByPage(upz, page1);
			page=cjpzservice.findByPage(cjz,page);
			/*String s = pdhosnum("0000","11111");
			System.out.println(s);*/
			//采集
			for(int i=0;i<page.getResults().size();i++){
				String time = "";
				if("0".equals((page.getResults().get(i).getZhouqi()))){
					time=page.getResults().get(i).getZhouqiday()+page.getResults().get(i).getZhouqixs();
				}else{
					time=day+page.getResults().get(i).getZhouqixs();
				}
				if(time.equals(day+month)){
					System.out.println("11");
					sj.setId(page.getResults().get(i).getId());
					sj1=sjpzservice.findBysql(sj);
					String sql1 = "delete from zc_tp_personal";	
					cahservice.insertday(sql1);
					String sql2 = "delete from zc_gpwtpjg";
					cahservice.insertday(sql2);
					for(int f=0;f<sj1.size();f++){
						jdbcxz(sj1.get(f).getStype(),sj1.get(f).getIp(), sj1.get(f).getUser_account(), sj1.get(f).getPwd(),sj1.get(f).getSqlname(), sj1.get(f).getMx_sql(), sj1.get(f).getPort(),sj1.get(f).getCaijibz(),"0000","");
					}
				}
			}
			//上传
			for(int i=0;i<page1.getResults().size();i++){
				String time = "";
				if("0".equals((page1.getResults().get(i).getZhouqi()))){
					time=page1.getResults().get(i).getZhouqiday()+page1.getResults().get(i).getZhouqixs();
				}else{
					time=day+page1.getResults().get(i).getZhouqixs();
				}
				if(time.equals(day+month)){
					System.out.println("11");
					sj0.setId(page1.getResults().get(i).getId());
					sj2=sjpzservice.findBysql1(sj0);
					for(int f=0;f<sj2.size();f++){
						jdbcsc(sj2.get(f).getStype(),sj2.get(f).getIp(), sj2.get(f).getUser_account(), sj2.get(f).getPwd(),sj2.get(f).getSqlname(), sj2.get(f).getMx_sql(), sj2.get(f).getPort(),sj2.get(f).getCaijibz(),"0000","");
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String jdbcsc(String lx,String ip,String zh,String ma,String km,String sql,String dk,String sd,String name,String sj){
		Connection ct=null;
		PreparedStatement ps=null;      //预编译的sql对象
		ResultSet rs=null;
		ResultSet rs_org=null;
		List<Map<String,String>> list = null;
		List<zhibiaoku> zbk = null;
		TongJi_log log1 = new TongJi_log();
		log1.setId(get32UUID());
		log1.setTongjilx("自动");
		log1.setZxren("0000");
		log1.setTongjilb("上传");
		log1.setName(sd);
		List<Org> org = new ArrayList<Org>();
		int c=0;
		try {
			//1、加载驱动
			if("oracle".equals(lx)){
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if("mysql".equals(lx)){
				Class.forName("com.mysql.jdbc.Driver");
			}else if("sqlservice".equals(lx)){
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			}
			//2、得到连接
			if("oracle".equals(lx)){
				ct=DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":"+dk+":"+km,zh,ma);
			}else if("mysql".equals(lx)){
				ct=DriverManager.getConnection("jdbc:mysql://"+ip+":"+dk+"/"+km+"?useUnicode=true&characterEncoding=UTF-8",zh,ma);
			}else if("sqlservice".equals(lx)){
				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://"+ip+":"+dk+";DatabaseName="+km+"", zh,ma);
			}
			SimpleDateFormat df9 = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
			//3、创建sql对象
			ps=ct.prepareStatement(sql);
			//4、执行
			//rs=
			if("zbk".equals(sd)) {
				List<Map<String,String>> listm = new ArrayList<Map<String,String>>();
				listm=cahservice.find_sqllist(sql);
				for(int i=0;i<listm.size();i++){
					String xuhao = String.valueOf(listm.get(i).get("xuhao"));
					Integer xuhao1 = Integer.parseInt(xuhao);
					String i_id = (listm.get(i).get("i_id")!=null)?listm.get(i).get("i_id"):"";
					String item = (listm.get(i).get("item")!=null)?listm.get(i).get("item"):"";
					String score = (listm.get(i).get("score")!=null)?listm.get(i).get("score"):"";
					String category = (listm.get(i).get("category")!=null)?listm.get(i).get("category"):"";
					String item_desc = (listm.get(i).get("item_desc")!=null)?listm.get(i).get("item_desc"):"";
					String is_max = (listm.get(i).get("is_max")!=null)?listm.get(i).get("is_max"):"";
					String is_add = (listm.get(i).get("is_add")!=null)?listm.get(i).get("is_add"):"";
					String note = (listm.get(i).get("note")!=null)?listm.get(i).get("note"):"";
					String category_id = (listm.get(i).get("category_id")!=null)?listm.get(i).get("category_id"):"";
					String is_using = (listm.get(i).get("is_using")!=null)?listm.get(i).get("is_using"):"";
					String districtid = (listm.get(i).get("districtid")!=null)?listm.get(i).get("districtid"):"";
					String hosnum = (listm.get(i).get("hosnum")!=null)?listm.get(i).get("hosnum"):"";
					String pid = (listm.get(i).get("pid")!=null)?listm.get(i).get("pid"):"";
					String year = (listm.get(i).get("year")!=null)?listm.get(i).get("year"):"";
					String finlscore = (listm.get(i).get("finlscore")!=null)?listm.get(i).get("finlscore"):"";
					String last = (listm.get(i).get("last")!=null)?listm.get(i).get("last"):"";
					String keshi = (listm.get(i).get("keshi")!=null)?listm.get(i).get("keshi"):"";
					String lczt = (listm.get(i).get("lczt")!=null)?listm.get(i).get("lczt"):"";
					String use_dept = (listm.get(i).get("use_dept")!=null)?listm.get(i).get("use_dept"):"";
					String stop_time = (listm.get(i).get("stop_time")!=null)?listm.get(i).get("istop_timed"):"";
					String maxscore = (listm.get(i).get("maxscore")!=null)?listm.get(i).get("maxscore"):"";
					String treename = (listm.get(i).get("treename")!=null)?listm.get(i).get("treename"):"";
					String is_sub = (listm.get(i).get("is_sub")!=null)?listm.get(i).get("is_sub"):"";
					String qzdept = (listm.get(i).get("qzdept")!=null)?listm.get(i).get("qzdept"):"";
					String sql1 = "insert into zbk(i_id,item,score,category,item_desc,is_max,is_add,note,category_id,is_using,"
							+ "districtid,hosnum,pid,year,finlscore,last,keshi,lczt,xuhao,use_dept,stop_time,maxscore,treename,"
							+ "is_sub,qzdept) values('"
							+i_id+"','"+item+"','"+score+"','"
							+category+"','"+item_desc+"','"+is_max+"','"
							+is_add+"','"+note+"','"
							+category_id+"','"+is_using+"','"+districtid+"','"
							+hosnum+"','"+pid+"','"+year+"','"
							+finlscore+"','"+last+"','"+keshi+"','"
							+lczt+"','"+xuhao1+"','"+use_dept+"','"
							+stop_time+"','"+maxscore+"','"+treename+"','"
							+is_sub+"','"
							+qzdept+"')";
					ps.executeUpdate(sql1);
					c++;
					zhibiaoku zzb = new zhibiaoku();
					zzb.setI_id(listm.get(i).get("i_id"));
					zzb.setHosnum(listm.get(i).get("hosnum"));
					zzb.setState("N");
					sbervice.updatesc1(zzb);
				}
			}else if("zbkscore".equals(sd)){
				List<Map<String,String>> listm = new ArrayList<Map<String,String>>();
				listm=cahservice.find_sqllist(sql);
				List<String> list1 = new ArrayList<String>();
				for(int i=0;i<listm.size();i++){
					String year = String.valueOf(listm.get(i).get("year"));
					String year1 = null;
					SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
					if("oracle".equals(lx)){
						if(year!="null"){
							year1 = "to_date('"+formatter.format(formatter.parse(year))+"','yyyy/MM/dd')";
						}else{
							year1 = null;
						}
					}else if("mysql".equals(lx)){
						if(year!="null"){
							year1 = "date_format('"+formatter.format(formatter.parse(year))+"','%Y/%m/%d')";
						}else{
							year1 = null;
						}
					}
					String id = (listm.get(i).get("id")!=null)?listm.get(i).get("id"):"";
					String name1 = (listm.get(i).get("name")!=null)?listm.get(i).get("name"):"";
					String scorep = (listm.get(i).get("scorep")!=null)?listm.get(i).get("scorep"):"";
					String xulieid = (listm.get(i).get("xulieid")!=null)?listm.get(i).get("xulieid"):"";
					String itemid = (listm.get(i).get("itemid")!=null)?listm.get(i).get("itemid"):"";
					String finlscore = (listm.get(i).get("finlscore")!=null)?listm.get(i).get("finlscore"):"";
					String sql1 = 
							"insert into zbkscore(id,name,scorep,xulieid,itemid,year,finlscore) values ('"
							+id+"','"+name1+"','"
							+scorep+"','"+xulieid+"','"
							+itemid+"',"+year1+",'"
							+finlscore+"')";
					ps.executeUpdate(sql1);
					c++;
					list1.add(listm.get(i).get("id"));
				}
				if(list1.size()>0){
					zbkglervice.updatescrys(list1,"N");
				}
			}else if("new_ryzbk".equals(sd)){
				List<Map<String,String>> listm = new ArrayList<Map<String,String>>();
				listm=cahservice.find_sqllist(sql);
				List<String> new_ry = new ArrayList<String>();
				for(int i=0;i<listm.size();i++){
					String pid = (listm.get(i).get("pid")!=null)?listm.get(i).get("pid"):"";
					String leixing = (listm.get(i).get("leixing")!=null)?listm.get(i).get("leixing"):"";
					String recordno = (listm.get(i).get("recordno")!=null)?listm.get(i).get("recordno"):"";
					String xm = (listm.get(i).get("xm")!=null)?listm.get(i).get("xm"):"";
					String unitid = (listm.get(i).get("unitid")!=null)?listm.get(i).get("unitid"):"";
					String idcard = (listm.get(i).get("idcard")!=null)?listm.get(i).get("idcard"):"";
					String year = (listm.get(i).get("year")!=null)?listm.get(i).get("year"):"";
					String contents = (listm.get(i).get("contents")!=null)?listm.get(i).get("contents"):"";
					String sql1 = 
							"insert into new_ryzbk(pid,leixing,recordno,xm,unitid,idcard,year,contents) values('"
									+pid+"','"+leixing+"','"
									+recordno+"','"+xm+"','"
									+unitid+"','"+idcard+"','"
									+year+"','"+contents+"')";
					ps.executeUpdate(sql1);
					c++;
					new_ry.add(listm.get(i).get("recordno"));
				}
				if(new_ry.size()>0){
					new_ryervice.updatescry1(new_ry, "N");
				}
			}else if("model".equals(sd)) {
				List<Map<String,String>> listm = new ArrayList<Map<String,String>>();
				listm=cahservice.find_sqllist(sql);
				for(int i=0;i<listm.size();i++){
					String xh = String.valueOf(listm.get(i).get("xh"));
					Integer xh1 = Integer.parseInt(xh);
					String id = (listm.get(i).get("id")!=null)?listm.get(i).get("id"):"";
					String zblb = (listm.get(i).get("zblb")!=null)?listm.get(i).get("zblb"):"";
					String yq = (listm.get(i).get("yq")!=null)?listm.get(i).get("yq"):"";
					String del = (listm.get(i).get("del")!=null)?listm.get(i).get("del"):"";
					String name1 = (listm.get(i).get("name")!=null)?listm.get(i).get("name"):"";
					String hosnum = (listm.get(i).get("hosnum")!=null)?listm.get(i).get("hosnum"):"";
					String nodecode = (listm.get(i).get("nodecode")!=null)?listm.get(i).get("nodecode"):"";
					String year = (listm.get(i).get("year")!=null)?listm.get(i).get("year"):"";
					String sql1 = 
							"insert into model(id,zblb,yq,xh,del,name,hosnum,nodecode,year) values('"
									+id+"','"+zblb+"','"
									+yq+"','"+xh1+"','"
									+del+"','"+name1+"','"
									+hosnum+"','"+nodecode+"','"
									+year+"')";
					ps.executeUpdate(sql1);
					c++;
					Modelm model = new Modelm();
					model.setId(listm.get(i).get("id"));
					model.setHosnum(listm.get(i).get("hosnum"));
					model.setState("N");
					modelervice.updatescmodel(model);
				}
			}else if("zc_personalrecord".equals(sd)) {
				List<Map<String,String>> listm = new ArrayList<Map<String,String>>();
				listm=cahservice.find_sqllist(sql);
				List<String> person = new ArrayList<String>();
				for(int i=0;i<listm.size();i++){
					//Integer数据
					String mscj = String.valueOf(listm.get(i).get("mscj"));
					Integer mscj1 = 0;
					if(!mscj.equals("null")){
						mscj1 = Integer.parseInt(mscj);
					}
					String xh_int = String.valueOf(listm.get(i).get("xh_int"));
					Integer xh_int1 = 0;
					if(xh_int!="null"){
						xh_int1 = Integer.parseInt(xh_int);
					}
					String logic_pass = String.valueOf(listm.get(i).get("logic_pass"));
					Integer logic_pass1 = 0;
					if(logic_pass!="null"){
						logic_pass1 = Integer.parseInt(logic_pass);
					}
					//date数据
					String tjdwrq1 = null;
					String xyjtjrq1 = null;
					String rsbmsmrq1 =null;
					String person_audit_date1 = null;
					SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
					if("oracle".equals(lx)){
						String tjdwrq = String.valueOf(listm.get(i).get("tjdwrq"));
						if(tjdwrq!="null"){
							tjdwrq1 = "to_date('"+formatter.format(formatter.parse(tjdwrq))+"','yyyy/MM/dd')";
						}else{
							tjdwrq1 = null;
						}
						String xyjtjrq = String.valueOf(listm.get(i).get("xyjtjrq"));
						if(xyjtjrq!="null"){
							xyjtjrq1 = "to_date('"+formatter.format(formatter.parse(xyjtjrq))+"','yyyy/MM/dd')";
						}else {
							xyjtjrq1 = null;
						}
						String rsbmsmrq = String.valueOf(listm.get(i).get("rsbmsmrq"));
						if(rsbmsmrq!="null"){
							rsbmsmrq1 = "to_date('"+formatter.format(formatter.parse(rsbmsmrq))+"','yyyy/MM/dd')";
						}else {
							rsbmsmrq1 = null;
						}
						String person_audit_date = String.valueOf(listm.get(i).get("person_audit_date"));
						if(person_audit_date!="null"){
							person_audit_date1 = "to_date('"+formatter.format(formatter.parse(person_audit_date))+"','yyyy/MM/dd')";
						}else {
							person_audit_date1 = null;
						}
					}else if("mysql".equals(lx)){
						String tjdwrq = String.valueOf(listm.get(i).get("tjdwrq"));
						if(tjdwrq!="null"){
							tjdwrq1 = "date_format('"+formatter.format(formatter.parse(tjdwrq))+"','%Y/%m/%d')";
						}else{
							tjdwrq1 = null;
						}
						String xyjtjrq = String.valueOf(listm.get(i).get("xyjtjrq"));
						if(xyjtjrq!="null"){
							xyjtjrq1 = "date_format('"+formatter.format(formatter.parse(xyjtjrq))+"','%Y/%m/%d')";
						}else {
							xyjtjrq1 = null;
						}
						String rsbmsmrq = String.valueOf(listm.get(i).get("rsbmsmrq"));
						if(rsbmsmrq!="null"){
							rsbmsmrq1 = "date_format('"+formatter.format(formatter.parse(rsbmsmrq))+"','%Y/%m/%d')";
						}else {
							rsbmsmrq1 = null;
						}
						String person_audit_date = String.valueOf(listm.get(i).get("person_audit_date"));
						if(person_audit_date!="null"){
							person_audit_date1 = "date_format('"+formatter.format(formatter.parse(person_audit_date))+"','%Y/%m/%d')";
						}else {
							person_audit_date1 = null;
						}
					}
					
					String xh = (listm.get(i).get("xh")!=null)?listm.get(i).get("xh"):"";
					String xm = (listm.get(i).get("xm")!=null)?listm.get(i).get("xm"):"";
					String xb = (listm.get(i).get("xb")!=null)?listm.get(i).get("xb"):"";
					String csny = (listm.get(i).get("csny")!=null)?listm.get(i).get("csny"):"";
					String zzmm = (listm.get(i).get("zzmm")!=null)?listm.get(i).get("zzmm"):"";
					String xgzdw = (listm.get(i).get("xgzdw")!=null)?listm.get(i).get("xgzdw"):"";
					String xl = (listm.get(i).get("xl")!=null)?listm.get(i).get("xl"):"";
					String xszw = (listm.get(i).get("xszw")!=null)?listm.get(i).get("xszw"):"";
					String xzzw = (listm.get(i).get("xzzw")!=null)?listm.get(i).get("xzzw"):"";
					String hxbyq = (listm.get(i).get("hxbyq")!=null)?listm.get(i).get("hxbyq"):"";
					String hshxhzybyq = (listm.get(i).get("hshxhzybyq")!=null)?listm.get(i).get("hshxhzybyq"):"";
					String hxbyh = (listm.get(i).get("hxbyh")!=null)?listm.get(i).get("hxbyh"):"";
					String zgxl = (listm.get(i).get("zgxl")!=null)?listm.get(i).get("zgxl"):"";
					String sfpg = (listm.get(i).get("sfpg")!=null)?listm.get(i).get("sfpg"):"";
					String cjgzsj = (listm.get(i).get("cjgzsj")!=null)?listm.get(i).get("cjgzsj"):"";
					String xcszy = (listm.get(i).get("xcszy")!=null)?listm.get(i).get("xcszy"):"";
					String cszygznx = (listm.get(i).get("cszygznx")!=null)?listm.get(i).get("cszygznx"):"";
					String xrzzgmc = (listm.get(i).get("xrzzgmc")!=null)?listm.get(i).get("xrzzgmc"):"";
					String xrzzgqdsj = (listm.get(i).get("xrzzgqdsj")!=null)?listm.get(i).get("xrzzgqdsj"):"";
					String xprzw = (listm.get(i).get("xprzw")!=null)?listm.get(i).get("xprzw"):"";
					String xprzwsj = (listm.get(i).get("xprzwsj")!=null)?listm.get(i).get("xprzwsj"):"";
					String dwkhqk1 = (listm.get(i).get("dwkhqk1")!=null)?listm.get(i).get("dwkhqk1"):"";
					String dwkhqk2 = (listm.get(i).get("dwkhqk2")!=null)?listm.get(i).get("dwkhqk2"):"";
					String dwkhqk3 = (listm.get(i).get("dwkhqk3")!=null)?listm.get(i).get("dwkhqk3"):"";
					String tjhrzzg = (listm.get(i).get("tjhrzzg")!=null)?listm.get(i).get("tjhrzzg"):"";
					String wyygwcj = (listm.get(i).get("wyygwcj")!=null)?listm.get(i).get("wyygwcj"):"";
					String jsjyynlkhcj = (listm.get(i).get("jsjyynlkhcj")!=null)?listm.get(i).get("jsjyynlkhcj"):"";
					String ktjx = (listm.get(i).get("ktjx")!=null)?listm.get(i).get("ktjx"):"";
					String sxdj = (listm.get(i).get("sxdj")!=null)?listm.get(i).get("sxdj"):"";
					String pyxs = (listm.get(i).get("pyxs")!=null)?listm.get(i).get("pyxs"):"";
					String ztjz = (listm.get(i).get("ztjz")!=null)?listm.get(i).get("ztjz"):"";
					String qtgz = (listm.get(i).get("qtgz")!=null)?listm.get(i).get("qtgz"):"";
					String ylxf = (listm.get(i).get("ylxf")!=null)?listm.get(i).get("ylxf"):"";
					String elxf = (listm.get(i).get("elxf")!=null)?listm.get(i).get("elxf"):"";
					String ylsg = (listm.get(i).get("ylsg")!=null)?listm.get(i).get("ylsg"):"";
					String jcqk = (listm.get(i).get("jcqk")!=null)?listm.get(i).get("jcqk"):"";
					String pgjsly = (listm.get(i).get("pgjsly")!=null)?listm.get(i).get("pgjsly"):"";
					String dwtjyj = (listm.get(i).get("dwtjyj")!=null)?listm.get(i).get("dwtjyj"):"";
					//String tjdwrq = (listm.get(i).get("tjdwrq")!=null)?listm.get(i).get("tjdwrq"):"";
					String xyjtjyj = (listm.get(i).get("xyjtjyj")!=null)?listm.get(i).get("xyjtjyj"):"";
					//String xyjtjrq = (listm.get(i).get("xyjtjrq")!=null)?listm.get(i).get("xyjtjrq"):"";
					String sdstjrsbmyj = (listm.get(i).get("sdstjrsbmyj")!=null)?listm.get(i).get("sdstjrsbmyj"):"";
					String zgbm = (listm.get(i).get("zgbm")!=null)?listm.get(i).get("zgbm"):"";
					//String rsbmsmrq = (listm.get(i).get("rsbmsmrq")!=null)?listm.get(i).get("rsbmsmrq"):"";
					String pwhzrs = (listm.get(i).get("pwhzrs")!=null)?listm.get(i).get("pwhzrs"):"";
					String cxrs = (listm.get(i).get("cxrs")!=null)?listm.get(i).get("cxrs"):"";
					String zcrs = (listm.get(i).get("zcrs")!=null)?listm.get(i).get("zcrs"):"";
					String fdrs = (listm.get(i).get("fdrs")!=null)?listm.get(i).get("fdrs"):"";
					String qtxysmdwt = (listm.get(i).get("qtxysmdwt")!=null)?listm.get(i).get("qtxysmdwt"):"";
					String sftgscbj = (listm.get(i).get("sftgscbj")!=null)?listm.get(i).get("sftgscbj"):"";
					String dwxz = (listm.get(i).get("dwxz")!=null)?listm.get(i).get("dwxz"):"";
					String dqdm = (listm.get(i).get("dqdm")!=null)?listm.get(i).get("dqdm"):"";
					String tgps = (listm.get(i).get("tgps")!=null)?listm.get(i).get("tgps"):"";
					String zsdw = (listm.get(i).get("zsdw")!=null)?listm.get(i).get("zsdw"):"";
					String cxrs1 = (listm.get(i).get("cxrs1")!=null)?listm.get(i).get("cxrs1"):"";
					String zcrs1 = (listm.get(i).get("zcrs1")!=null)?listm.get(i).get("zcrs1"):"";
					String fdrs1 = (listm.get(i).get("fdrs1")!=null)?listm.get(i).get("fdrs1"):"";
					String jcsc = (listm.get(i).get("jcsc")!=null)?listm.get(i).get("jcsc"):"";
					String zyq = (listm.get(i).get("zyq")!=null)?listm.get(i).get("zyq"):"";
					String zyh = (listm.get(i).get("zyh")!=null)?listm.get(i).get("zyh"):"";
					String xwq = (listm.get(i).get("xwq")!=null)?listm.get(i).get("xwq"):"";
					String xwh = (listm.get(i).get("xwh")!=null)?listm.get(i).get("xwh"):"";
					String xysjq = (listm.get(i).get("xysjq")!=null)?listm.get(i).get("xysjq"):"";
					String xysjh = (listm.get(i).get("xysjh")!=null)?listm.get(i).get("xysjh"):"";
					String xlq = (listm.get(i).get("xlq")!=null)?listm.get(i).get("xlq"):"";
					String xlh = (listm.get(i).get("xlh")!=null)?listm.get(i).get("xlh"):"";
					String yydj = (listm.get(i).get("yydj")!=null)?listm.get(i).get("yydj"):"";
					String dwdj = (listm.get(i).get("dwdj")!=null)?listm.get(i).get("dwdj"):"";
					String idcard = (listm.get(i).get("idcard")!=null)?listm.get(i).get("idcard"):"";
					String p_id = (listm.get(i).get("p_id")!=null)?listm.get(i).get("p_id"):"";
					String wsps = (listm.get(i).get("wsps")!=null)?listm.get(i).get("wsps"):"";
					String charter_code = (listm.get(i).get("charter_code")!=null)?listm.get(i).get("charter_code"):"";
					String xlqd02 = (listm.get(i).get("xlqd02")!=null)?listm.get(i).get("xlqd02"):"";
					String hxby02 = (listm.get(i).get("hxby02")!=null)?listm.get(i).get("hxby02"):"";
					String zy02 = (listm.get(i).get("zy02")!=null)?listm.get(i).get("zy02"):"";
					String xl02 = (listm.get(i).get("xl02")!=null)?listm.get(i).get("xl02"):"";
					String xw02 = (listm.get(i).get("xw02")!=null)?listm.get(i).get("xw02"):"";
					String xysj02 = (listm.get(i).get("xysj02")!=null)?listm.get(i).get("xysj02"):"";
					String xlqd03 = (listm.get(i).get("xlqd03")!=null)?listm.get(i).get("xlqd03"):"";
					String hxby03 = (listm.get(i).get("hxby03")!=null)?listm.get(i).get("hxby03"):"";
					String zy03 = (listm.get(i).get("zy03")!=null)?listm.get(i).get("zy03"):"";
					String xl03 = (listm.get(i).get("xl03")!=null)?listm.get(i).get("xl03"):"";
					String xw03 = (listm.get(i).get("xw03")!=null)?listm.get(i).get("xw03"):"";
					String xysj03 = (listm.get(i).get("xysj03")!=null)?listm.get(i).get("xysj03"):"";
					String txdz = (listm.get(i).get("txdz")!=null)?listm.get(i).get("txdz"):"";
					String lxdh = (listm.get(i).get("lxdh")!=null)?listm.get(i).get("lxdh"):"";
					String zzzt = (listm.get(i).get("zzzt")!=null)?listm.get(i).get("zzzt"):"";
					String xw = (listm.get(i).get("xw")!=null)?listm.get(i).get("xw"):"";
					String yzbm = (listm.get(i).get("yzbm")!=null)?listm.get(i).get("yzbm"):"";
					//String person_audit_date = (listm.get(i).get("person_audit_date")!=null)?listm.get(i).get("person_audit_date"):"";
					String current_manage_unit = (listm.get(i).get("current_manage_unit")!=null)?listm.get(i).get("current_manage_unit"):"";
					String person_password = (listm.get(i).get("person_password")!=null)?listm.get(i).get("person_password"):"";
					String unitid = (listm.get(i).get("unitid")!=null)?listm.get(i).get("unitid"):"";
					String zxf = (listm.get(i).get("zxf")!=null)?listm.get(i).get("zxf"):"";
					String year = (listm.get(i).get("year")!=null)?listm.get(i).get("year"):"";
					String gsqk = (listm.get(i).get("gsqk")!=null)?listm.get(i).get("gsqk"):"";
					//String xh_int = (listm.get(i).get("xh_int")!=null)?listm.get(i).get("xh_int"):"";
					String zcfw = (listm.get(i).get("zcfw")!=null)?listm.get(i).get("zcfw"):"";
					String ks = (listm.get(i).get("ks")!=null)?listm.get(i).get("ks"):"";
					String sfsympdx = (listm.get(i).get("sfsympdx")!=null)?listm.get(i).get("sfsympdx"):"";
					String mply = (listm.get(i).get("mply")!=null)?listm.get(i).get("mply"):"";
					String pwhxz = (listm.get(i).get("pwhxz")!=null)?listm.get(i).get("pwhxz"):"";
					String tjpm = (listm.get(i).get("tjpm")!=null)?listm.get(i).get("tjpm"):"";
					//String logic_pass = (listm.get(i).get("logic_pass")!=null)?listm.get(i).get("logic_pass"):"";
					String zrs = (listm.get(i).get("zrs")!=null)?listm.get(i).get("zrs"):"";
					String sxsrs = (listm.get(i).get("sxsrs")!=null)?listm.get(i).get("sxsrs"):"";
					String gpsrs = (listm.get(i).get("gpsrs")!=null)?listm.get(i).get("gpsrs"):"";
					String yjsrs = (listm.get(i).get("yjsrs")!=null)?listm.get(i).get("yjsrs"):"";
					String bssrs = (listm.get(i).get("bssrs")!=null)?listm.get(i).get("bssrs"):"";
					String sssrs = (listm.get(i).get("sssrs")!=null)?listm.get(i).get("sssrs"):"";
					String jxcj = (listm.get(i).get("jxcj")!=null)?listm.get(i).get("jxcj"):"";
					String xrznx = (listm.get(i).get("xrznx")!=null)?listm.get(i).get("xrznx"):"";
					String sfsbwry = (listm.get(i).get("sfsbwry")!=null)?listm.get(i).get("sfsbwry"):"";
					
					

					String sql1 = 
							"insert into zc_personalrecord(recordno,xh,xm,xb,csny,zzmm,xgzdw,xl,xszw,xzzw,hxbyq,hshxhzybyq,"
							+ "hxbyh,zgxl,sfpg,cjgzsj,xcszy,cszygznx,xrzzgmc,xrzzgqdsj,xprzw,xprzwsj,dwkhqk1,dwkhqk2,dwkhqk3,"
							+ "tjhrzzg,wyygwcj,jsjyynlkhcj,mscj,ktjx,sxdj,pyxs,ztjz,qtgz,ylxf,elxf,ylsg,jcqk,pgjsly,dwtjyj,"
							+ "tjdwrq,xyjtjyj,xyjtjrq,sdstjrsbmyj,zgbm,rsbmsmrq,pwhzrs,cxrs,zcrs,fdrs,qtxysmdwt,sftgscbj,dwxz,"
							+ "dqdm,tgps,zsdw,cxrs1,zcrs1,fdrs1,jcsc,zyq,zyh,xwq,xwh,xysjq,xysjh,xlq,xlh,yydj,dwdj,idcard,"
							+ "p_id,wsps,charter_code,xlqd02,hxby02,zy02,xl02,xw02,xysj02,xlqd03,hxby03,zy03,xl03,xw03,xysj03,"
							+ "txdz,lxdh,zzzt,xw,yzbm,person_audit_date,current_manage_unit,person_password,unitid,zxf,year,"
							+ "gsqk,xh_int,zcfw,ks,sfsympdx,mply,pwhxz,tjpm,logic_pass,zrs,sxsrs,gpsrs,yjsrs,bssrs,sssrs,jxcj,"
							+ "xrznx,sfsbwry) values('"
							+listm.get(i).get("recordno")+"','"
							+xh+"','"+xm+"','"
									+xb+"','"+csny+"','"+zzmm+"','"
									+xgzdw+"','"
									+xl+"','"+xszw+"','"+xzzw+"','"
									+hxbyq+"','"+hshxhzybyq+"','"+hxbyh+"','"
									+zgxl+"','"+sfpg+"','"+cjgzsj+"','"
									+xcszy+"','"+cszygznx+"','"+xrzzgmc+"','"
									+xrzzgqdsj+"','"+xprzw+"','"+xprzwsj+"','"
									+dwkhqk1+"','"+dwkhqk2+"','"+dwkhqk3+"','"
									+tjhrzzg+"','"+wyygwcj+"','"+jsjyynlkhcj+"','"
									+mscj1+"','"+ktjx+"','"+sxdj+"','"
									+pyxs+"','"+ztjz+"','"+qtgz+"','"
									+ylxf+"','"+elxf+"','"+ylsg+"','"
									+jcqk+"','"+pgjsly+"','"+dwtjyj+"',"
									+tjdwrq1+",'"+xyjtjyj+"',"+xyjtjrq1+",'"
									+sdstjrsbmyj+"','"+zgbm+"',"+rsbmsmrq1+",'"
									+pwhzrs+"','"+cxrs+"','"+zcrs+"','"
									+fdrs+"','"+qtxysmdwt+"','"+sftgscbj+"','"
									+dwxz+"','"+dqdm+"','"+tgps+"','"
									+zsdw+"','"+cxrs1+"','"+zcrs1+"','"
									+fdrs1+"','"+jcsc+"','"+zyq+"','"
									+zyh+"','"+xwq+"','"+xwh+"','"
									+xysjq+"','"+xysjh+"','"+xlq+"','"
									+xlh+"','"+yydj+"','"+dwdj+"','"
									+idcard+"','"+p_id+"','"+wsps+"','"
									+charter_code+"','"+xlqd02+"','"+hxby02+"','"
									+zy02+"','"+xl02+"','"+xw02+"','"
									+xysj02+"','"+xlqd03+"','"+hxby03+"','"
									+zy03+"','"+xl03+"','"+xw03+"','"
									+xysj03+"','"+txdz+"','"+lxdh+"','"
									+zzzt+"','"+xw+"','"+yzbm+"',"
									+person_audit_date1+",'"+current_manage_unit+"','"+person_password+"','"
									+unitid+"','"+zxf+"','"+year+"','"
									+gsqk+"','"+xh_int1+"','"+zcfw+"','"
									+ks+"','"+sfsympdx+"','"+mply+"','"
									+pwhxz+"','"+tjpm+"','"+logic_pass1+"','"
									+zrs+"','"+sxsrs+"','"+gpsrs+"','"
									+yjsrs+"','"+bssrs+"','"+sssrs+"','"
									+jxcj+"','"+xrznx+"','"+sfsbwry+"')";
					ps.executeUpdate(sql1);
					c++;
					person.add(listm.get(i).get("recordno"));
				}
				if(person.size()>0){
					personal.updatescryp(person, "N");
				}
			}
			
			//循环取出结果集中的内容
			log1.setZxnum(c+"");
			if(c>0){
				log1.setSfcw("正确");
			}else if(c==0){
				log1.setSfcw("数据为0");
			}
		} catch (Exception e) {
			log1.setSfcw(e+"");
			e.printStackTrace();
		}finally{
			rzservice.insert(log1);
			//关闭资源
			if(rs!=null){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs=null;
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps=null;
			}
			if(ct!=null){
				try {
					ct.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ct=null;
			}
		}
		return null;
	}

	public String jdbcxz(String lx,String ip,String zh,String ma,String km,String sql,String dk,String sd,String name,String sj){
		Connection ct=null;
		PreparedStatement ps=null;      //预编译的sql对象
		ResultSet rs=null;
		ResultSet rs_org=null;
		List<Map<String,String>> list = null;
		List<zhibiaoku> zbk = null;
		TongJi_log log1 = new TongJi_log();
		log1.setId(get32UUID());
		log1.setTongjilx("自动");
		log1.setZxren("0000");
		log1.setTongjilb("采集");
		log1.setName(sd);
		int c=0;
		List<Org> org = new ArrayList<Org>();
		try {
			//1、加载驱动
			if("oracle".equals(lx)){
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if("mysql".equals(lx)){
				Class.forName("com.mysql.jdbc.Driver");
			}else if("sqlservice".equals(lx)){
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			}
			//2、得到连接
			if("oracle".equals(lx)){
				ct=DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":"+dk+":"+km,zh,ma);
			}else if("mysql".equals(lx)){
				ct=DriverManager.getConnection("jdbc:mysql://"+ip+":"+dk+"/"+km+"?useUnicode=true&characterEncoding=UTF-8",zh,ma);
			}else if("sqlservice".equals(lx)){
				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://"+ip+":"+dk+";DatabaseName="+km+"", zh,ma);
			}
			SimpleDateFormat df9 = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
			//3、创建sql对象
			ps=ct.prepareStatement(sql);
			//4、执行
			rs=ps.executeQuery();

			//循环取出结果集中的内容
			Calendar now = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String year = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1);
			year += "-"+now.get(Calendar.DAY_OF_MONTH);
			Date date0 = df.parse(year);
			List<zhibiaoku> zbk1 = null;
			List<ryzbk> ryzbk = new ArrayList<ryzbk>();
			System.out.println(date0);
			while(rs.next()){
				/*ResultSetMetaData rsmd = rs.getMetaData() ; 
				int columnCount = rsmd.getColumnCount();*/
				if(sd.equals("zc_gpwtpjg")){
					GPWTPJG(rs);
				}else if(sd.equals("zc_tp_personal")){
					TP_PERSONAL(rs);
				}else if(sd.equals("zc_personalrecord")){
					ryzbk=ryzbkervice.findpeoplename(rs.getString(1));
					if(ryzbk.size()>0){
						PERSONALRECORD(rs);
					}
				}
			}
			log1.setZxnum(c+"");
			if(c>0){
				log1.setSfcw("正确");
			}
		} catch (Exception e) {
			log1.setSfcw(e+"");
			e.printStackTrace();
		}finally{
			//关闭资源
			rzservice.insert(log1);
			if(rs!=null){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs=null;
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps=null;
			}
			if(ct!=null){
				try {
					ct.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ct=null;
			}
		}
		return null;
	}
	
	public void GPWTPJG(ResultSet rs) throws SQLException{
		String sql1="";
		String recordno="";
		String zybh="";
		String sftg="";
		String unitid="";
		if(rs.getString(1)!=null&&!"".equals(rs.getString(1))){
			recordno=rs.getString(1);
		}
		if(rs.getString(2)!=null&&!"".equals(rs.getString(2))){
			zybh=rs.getString(2);
		}
		if(rs.getString(6)!=null&&!"".equals(rs.getString(6))){
			sftg=rs.getString(6);
		}
		if(rs.getString(7)!=null&&!"".equals(rs.getString(7))){
			unitid=rs.getString(7);
		}
		sql1 = "insert into ZC_GPWTPJG (recordno,zybh,cxrs,tyrs,fdrs,sftg,unitid"+
				")values('"+recordno+"','"+zybh+"',	'"+rs.getInt(3)+"','"+
				""+rs.getInt(4)+"','"+rs.getInt(5)+"','"+sftg+"','"+unitid+"')";	
		cahservice.insertday(sql1);
	}
	
	
	
	public void TP_PERSONAL(ResultSet rs) throws SQLException{
		String sql1="";
		String recordno="";
		String zyzbh="";
		String unitid="";
		if(rs.getString(1)!=null&&!"".equals(rs.getString(1))){
			recordno=rs.getString(1);
		}
		if(rs.getString(2)!=null&&!"".equals(rs.getString(2))){
			zyzbh=rs.getString(2);
		}
		if(rs.getString(3)!=null&&!"".equals(rs.getString(3))){
			unitid=rs.getString(3);
		}
		sql1 = "insert into ZC_TP_PERSONAL (recordno,zyzbh,unitid"+
				")values('"+recordno+"','"+zyzbh+"','"+unitid+"')";	
		cahservice.insertday(sql1);
	}
	
	public void PERSONALRECORD(ResultSet rs) throws SQLException{
		String sql1="";
		sql1 = "update ZC_PERSONALRECORD t set t.prqx='"+rs.getString(3)+"',t.pqsj='"+rs.getDate(4)+"' where t.recordno='"+rs.getString(1)+"'";	
		cahservice.insertday(sql1);
	}
}
