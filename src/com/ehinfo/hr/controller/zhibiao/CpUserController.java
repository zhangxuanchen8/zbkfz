package com.ehinfo.hr.controller.zhibiao;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.PropertyUtil;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.declare.PersonalRecordService;
import com.ehinfo.hr.service.system.file.BaseFileService;
import com.ehinfo.hr.service.system.user.CpUserService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.new_ryzbkService;
import com.ehinfo.hr.service.zhibiao.ryzhibiaokuService;
import com.ehinfo.hr.service.zhibiao.zbk_fjService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;
import com.ehinfo.hr.service.zhibiao.zhibiaoglService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/cpuser/")
public class CpUserController extends BaseController<zhibiaoku>{
	
	@Autowired
	private BaseFileService serviceFile;
	@Autowired
	public zhibiao_sbService zbkService;
	@Autowired
	private zbk_fjService fjService;
	@Autowired
	private new_ryzbkService ryzbkService;
	@Autowired
	private CpUserService userService;
	@Autowired
	private PersonalRecordService perService;
	@Autowired
	public ModelService modelService;
	@Autowired
	public zhibiaoglService score;
	@Autowired
	public ryzhibiaokuService ryService;
	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,String sccx){
		CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
		List<new_ryzbk> list_ry = ryzbkService.findByPid(a.getUser_id());
		Modelm mod = new Modelm();
		mod.setHosnum(a.getHosnum());
		mod.setId(list_ry.get(0).getPid());
		mod= modelService.findById(mod);
		model.put("pid", mod.getId());
		model.put("supunit2", a.getUser_id());
		model.put("lhmbym", mod.getName());
		if(!"".equals(a.getTjdate())&&a.getTjdate()!=null){
			model.put("sjdate", a.getTjdate());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String sj1=df.format(new Date());
			int dae =Integer.parseInt(sj1);
			int dae1=Integer.parseInt(a.getTjdate().replaceAll("-", ""));
			model.put("sjdate1", (dae1-dae));
		}
		model.put("lhmbym", mod.getName());
		zhibiaoku zbk = new zhibiaoku();
		List<zhibiaoku> zbk1=null;
		zbk.setHosnum(a.getHosnum());
		zbk.setPid(list_ry.get(0).getPid());
		String allids = zbkService.getpid(list_ry.get(0).getPid());
		zbk.setPid(allids);
		zbk1=zbkService.findbgxl(zbk);
		String ids = "";
		for(zhibiaoku z:zbk1){
			ids+=z.getI_id()+",";
		}
		model.put("ids", ids);
		CpUser user = new CpUser();
		user = userService.findById(a.getIdcard());
		model.put("user", user);
		return "/ceshi/scfjperindex";
		/*CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
		zbk_fj fj = new zbk_fj();
		List<new_ryzbk> ryzbk = null;
		List<zbk_fj> o =null;
		zhibiaoku zbk = new zhibiaoku();
		List<zhibiaoku> zbk1=null;
		fj.setHosnum(a.getHosnum());
		fj.setUser_id(a.getUser_id());
		String fjsize="";
		ryzbk=ryzbkService.findByPid(a.getUser_id());
		zbk.setHosnum(a.getHosnum());
		zbk.setPid(ryzbk.get(0).getPid());
		zbk1=zbkService.findbgxl(zbk);
		String ids = "";
		for(zhibiaoku z:zbk1){
			ids+=z.getI_id()+",";
		}
		model.put("ids", ids);
		model.put("lhmbym", zbk1.get(0).getCategory());
		String remark="";
		for(zhibiaoku z:zbk1){
			if(Tools.notEmpty(zbk.getPid())){
				remark  = allitem(z.getPid(),z.getItem());
				z.setItem(remark);
			}
		}
		o=fjService.findByid(fj);
		if("2".equals(sccx)){
			model.put("sccx", 2);
			List<zhibiaoku> zbk2= new ArrayList<zhibiaoku>();
			if(o.size()>0){
				for(int i=0;i<zbk1.size();i++){
					int q=0;
					for(int k=0;k<o.size();k++){
						if((zbk1.get(i).getI_id()).equals(o.get(k).getI_id())){
							q=1;
						}
					}
					if(q==0){
						zbk1.get(i).setZpf("0");
						zbk1.get(i).setFj_size("0");
						zbk2.add(zbk1.get(i));
					}
				}
			}else{
				for(int i=0;i<zbk1.size();i++){
					zbk1.get(i).setFj_size("0");
					zbk1.get(i).setZpf("0");
					zbk2.add(zbk1.get(i));
				}
			}
			model.put("zbkfj", zbk2);
		}else if("1".equals(sccx)){
			model.put("sccx", 1);
			List<zhibiaoku> zbk2= new ArrayList<zhibiaoku>();
			if(o.size()>0){
				for(int i=0;i<zbk1.size();i++){
					for(int k=0;k<o.size();k++){
						if((zbk1.get(i).getI_id()).equals(o.get(k).getI_id())){
							fjsize=o.get(k).getZbk_fj();
							if(Tools.notEmpty(fjsize)){
								String[] chk =fjsize.split(",");
								zbk1.get(i).setFj_size(chk.length+"");
								zbk1.get(i).setZpf(Tools.isEmpty(o.get(k).getZipingfen())?"0":o.get(k).getZipingfen());
								break;
							}else{
								zbk1.get(i).setZpf("0");
								zbk1.get(i).setFj_size("0");
							}
						}else{
							zbk1.get(i).setZpf("0");
							zbk1.get(i).setFj_size("0");
						}
					}
				}
			}
			model.put("zbkfj", zbk2);
		}else{
			model.put("sccx", 0);
			if(o.size()>0){
				for(int i=0;i<zbk1.size();i++){
					for(int k=0;k<o.size();k++){
						if((zbk1.get(i).getI_id()).equals(o.get(k).getI_id())){
							fjsize=o.get(k).getZbk_fj();
							if(Tools.notEmpty(fjsize)){
								String[] chk =fjsize.split(",");
								zbk1.get(i).setFj_size(chk.length+"");
							}else{
								zbk1.get(i).setFj_size("0");
							}
							zbk1.get(i).setZpf(Tools.isEmpty(o.get(k).getZipingfen())?"0":o.get(k).getZipingfen());
							break;
						}else{
							zbk1.get(i).setZpf("0");
							zbk1.get(i).setFj_size("0");
						}
					}
				}
			}else{
				for(int i=0;i<zbk1.size();i++){
					zbk1.get(i).setFj_size("0");
					zbk1.get(i).setZpf("0");
				}
			}
			model.put("zbkfj", zbk1);
		}
		
		if(!"".equals(a.getTjdate())&&a.getTjdate()!=null){
			model.put("sjdate", a.getTjdate());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String sj1=df.format(new Date());
			int dae =Integer.parseInt(sj1);
			int dae1=Integer.parseInt(a.getTjdate().replaceAll("-", ""));
			model.put("sjdate1", (dae1-dae));
		}
		model.put("name", a.getName());*/
		//return "/ceshi/scfjIndex";
		
	}
	
	public String allitem(String pid,String s){
		zhibiaoku zbk = new zhibiaoku();
		zbk.setI_id(pid);
		zbk = zbkService.findById(zbk);
		if(zbk!=null){
			s=zbk.getItem()+"-"+s;
			if(Tools.notEmpty(zbk.getPid())){
				s = allitem(zbk.getPid(),s);
			}
		}
		return s;
	}
	@RequestMapping(value="add_fj")
	public String add_fj(ModelMap model,HttpServletRequest request, HttpSession session){
		CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
		String i_id= request.getParameter("id");
		
		model.put("userid", a.getUser_id());
		model.put("i_id", i_id);
		return "/ceshi/sfindex";
	}
	

	
	@RequestMapping(value="save_fj")
	@ResponseBody
	public void upLicence(HttpServletRequest request, HttpServletResponse response,
			String[] ddIns,String[] imgurl,String i_id,String userid){
	   	PrintWriter pw = null;
		try {
			pw = response.getWriter();
			String remark="";
			int filesize=0;
			if(imgurl!=null){
				for(int i=0;i<imgurl.length;i++){
					if(imgurl[i].indexOf(".")>0) {
						Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
						String path=request.getSession().getServletContext().getRealPath("/");
						File baseFile = new File(path);
						File targetFile = new File(baseFile, imgurl[i]);
						FileInputStream inputStream=new FileInputStream(targetFile);
						String suffix =  imgurl[i].substring(imgurl[i].lastIndexOf(".")+1);
						byte[] b = FileCopyUtils.copyToByteArray(inputStream);
						BaseFile bf = new BaseFile();
						filesize=+ b.length;
						bf.setFiles(b);
			            bf.setFilename(ddIns[i]);
			            bf.setFiletype(suffix);
			            bf.setId(get32UUID());
			            serviceFile.insert(bf);
			            remark+=bf.getId()+",";
					}else{
						remark+=imgurl[i]+",";
					}
				}
				
			}
			if(filesize>5000000){
				pw.write(EhUtil.createcallbackjson("300", "上传内容超过最大限制5M", "", "", "", "", "", ""));
			}else{
				CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
				zbk_fj o = new zbk_fj();
				zhibiaoku zbk = new zhibiaoku();
				zbk.setI_id(i_id);
				zbk.setHosnum(a.getHosnum());
				zbk = zbkService.findById(zbk);
				List<zbk_fj> o1 =null;
				o.setI_id(i_id);
				o.setUser_id(userid);
				o.setHosnum(a.getHosnum());
				o.setPid(zbk.getPid());
				o1=fjService.findByid(o);
				o.setZbk_fj(remark);
			if(o1.size()>0) {
					int i = fjService.update1(o);
					if(i==1) {
						pw.write(EhUtil.createcallbackjson("200", "修改成功","", "","closeCurrent","","",""));
					}else {
						pw.write(EhUtil.createcallbackjson("300", "修改失败", "", "", "", "", "", ""));
					}
				}else {
					int i = fjService.insert1(o);
					if(i==1) {
						pw.write(EhUtil.createcallbackjson("200", "保存成功","", "","closeCurrent","","",""));
					}else {
						pw.write(EhUtil.createcallbackjson("300", "保存失败", "", "", "", "", "", ""));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.write(EhUtil.createcallbackjson("300", "操作失败", "", "", "", "", "", ""));
		}
	}
	
	@RequestMapping("changePsd")	
	public String changePsd(Model model,HttpServletRequest request){
		return "/ceshi/cpuserpd";
	}
	
	@RequestMapping(value="zbkfjxgpd", method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson resetPWD(String pwschange_oldpassword,String pwschange_newpassword,String pwschange_secpassword,HttpServletRequest request){
		HttpSession session = request.getSession();
		CpUser a = (CpUser) session.getAttribute(Const.SESSION_USER);
		DwzResJson dwz = new DwzResJson();
		try {
			int res=userService.xgPwd(pwschange_oldpassword,pwschange_newpassword,pwschange_secpassword,a);
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
	
	@RequestMapping(value="licence_details")
	public String licence_details(ModelMap model,HttpServletRequest request, HttpSession session
			){
		CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
		String i_id= request.getParameter("id");
		zbk_fj o = new zbk_fj();
		
			o.setI_id(i_id);
			
		
		o.setHosnum(a.getHosnum());
		o.setUser_id(a.getUser_id());
		List<zbk_fj> licenceInfo = new ArrayList<zbk_fj>();
		licenceInfo = fjService.findByid(o);
		if(licenceInfo.size()>0){
		BaseFile bf = null;
		List<BaseFile> list_file = new ArrayList<BaseFile>();
		if(Tools.notEmpty(licenceInfo.get(0).getZbk_fj())){
			String str[] = licenceInfo.get(0).getZbk_fj().split(",");
			for(int i=0;i<str.length;i++){
				if(Tools.notEmpty(str[i])){
					bf = new BaseFile();
					bf.setId(str[i]);
					bf=serviceFile.findById(bf);
//					String saveUrl = bf.byteToFile(request);
//					bf.setSaveUrl(saveUrl);
					list_file.add(bf);
				}
			}
			
		}
		
		licenceInfo.get(0).setList_file(list_file);
		model.put("licenceInfo", licenceInfo);
		}
		CpUser user = new CpUser();
		user = userService.findById(a.getIdcard());
		model.put("user", user);
		model.put("scbj", user.getScbj());
		
		model.put("userid", a.getUser_id());
		model.put("i_id", i_id);
		return "/ceshi/sfindex";
	}
	
    @RequestMapping("/cpuser_tc")
    public String logout_zj(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
			
    	session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_MENULIST);
		
		return "redirect:/";
			
  //      return "redirect:/backstage/loginIndex";
    }
    
    @RequestMapping(value="fjdate",method=RequestMethod.POST)
	@ResponseBody
	public void fjdate(HttpServletRequest resq,HttpServletResponse response,String sjdate,String statu){
		PrintWriter pw = null;
		String gb = resq.getParameter("gbbj");
		try {
			pw=response.getWriter();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			if("N".equals(gb)){
				CpUser cp = new CpUser();
				cp.setHosnum(a.getHosnum());
				int t = userService.updatesj1(cp);
				if(t==1){
					pw.print(1);
				}else{
					pw.print(2);
				}
			}else{
				Page<PersonalRecord> p = new Page<PersonalRecord>();
				List<String> list = new ArrayList();
				PersonalRecord per = new PersonalRecord();
				per.setUnitid(a.getHosnum());
				per.setStatu(statu);
				p = perService.findByPage1(per, p);
				
				for(int i=0;i<p.getResults().size();i++){
					if("".equals(p.getResults().get(i).getName())||p.getResults().get(i).getName()==null){
						list.add(p.getResults().get(i).getXm());
					}
				}
				if(list.size()==0){
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					CpUser cp = new CpUser();
					cp.setTjdate(sjdate);
					cp.setKqdate(sdf.format(date));
					cp.setHosnum(a.getHosnum());
					int t = userService.updatesj(cp);
					if(t==1){
						pw.write(EhUtil.createcallbackjson("200", "保存成功","", "","","","",""));
					}else{
						pw.write(EhUtil.createcallbackjson("300", "保存失败", "", "", "", "", "", ""));
					}
				}else{
					pw.write(EhUtil.createcallbackjson("300", list+"没有指定模板", "", "", "", "", "", ""));
				}
			}
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			pw.flush();
			pw.close();
		}
	}
    
    @RequestMapping(value="zpfbc")
	@ResponseBody
	public void zpfbc(String ids,HttpServletRequest req,HttpServletResponse resp,String zpfids){
    	PrintWriter pw = null;
			try {
				pw=resp.getWriter();
				CpUser a = (CpUser) req.getSession().getAttribute(Const.SESSION_USER);
				int t=0;
				String id[] = ids.split(",");
				String zpf[] = zpfids.split(",");
				zbk_fj o = null;
				zhibiaoku zbk =null;
					for(int i=0;i<id.length;i++){
						zbk =new zhibiaoku();
						zbk.setI_id(id[i]);
						zbk.setHosnum(a.getHosnum());
						zbk = zbkService.findById(zbk);
						o = new zbk_fj();
						o.setI_id(id[i]);
						o.setPid(zbk.getPid());
						o.setUser_id(a.getUser_id());
						o.setHosnum(a.getHosnum());
						o.setZipingfen(zpf[i]);
						o = fjService.findById(o);
						if(o!=null){
							o.setZipingfen(zpf[i]);
							fjService.update(o);
						}else{
							o = new zbk_fj();
							o.setI_id(id[i]);
							o.setPid(zbk.getPid());
							o.setUser_id(a.getUser_id());
							o.setHosnum(a.getHosnum());
							o.setZipingfen(zpf[i]);
							fjService.insert1(o);
						}
					}
					pw.write("ok");
			} catch (Exception e) {
				pw.write("no");
				e.getStackTrace();
			}finally{
				pw.flush();
				pw.close();
			}
		}
    @RequestMapping(value="scbj")
	@ResponseBody
	public void scbj(String ids,HttpServletRequest req,HttpServletResponse resp){
    	PrintWriter pw = null;
			try {
				pw=resp.getWriter();
				CpUser a = (CpUser) req.getSession().getAttribute(Const.SESSION_USER);
				int t=0;
					CpUser cp = new CpUser();
					cp.setScbj("Y");
					cp.setHosnum(a.getHosnum());
					cp.setUser_id(a.getUser_id());
					t = userService.updatebj(cp);
					if(t==1){
						pw.print(1);
					}else{
						pw.print(2);
					}
			} catch (Exception e) {
				e.getStackTrace();
			}finally{
				pw.flush();
				pw.close();
			}
		}
    
	@RequestMapping(value="getrysxbgTree",method=RequestMethod.POST)
	@ResponseBody
	public void  getrysxbgTree(HttpServletRequest request,HttpServletResponse response,String type){
		
		List<zhibiaoku> zhibiaoku = null;
		List<ryzbk> ryzbk = null;
		zhibiaoku zbk = new zhibiaoku();
		zbkscore o= new zbkscore();
		List<zbkscore> zbkscore = null;
			try {
				//String type = request.getParameter("type");
				String supunit2 = request.getParameter("supunit2");
				String year = request.getParameter("year");
				CpUser a = (CpUser) request.getSession().getAttribute(Const.SESSION_USER);
				zbk.setHosnum(a.getHosnum());
					zbkscore = score.findByPid(supunit2);
					ryzbk=ryService.findpeoplename(supunit2);
					o.setId(supunit2);
					zbk.setPid(ryzbk.get(0).getPid());
					if(zbkscore.size()>0){
						zbk.setIs_add(supunit2);
					}else{
						zbk.setIs_add("1");
					}
					//医院账号跟科室账号能看到的打分指标不同，先找出医院来
					if(Tools.notEmpty(type)){
						zbk.setType("1");
					}
					zbk.setUser_id(supunit2);
						String allpid = zbkService.getpid(zbk.getPid());
						zbk.setPid(allpid);
						zhibiaoku=zbkService.findlistscore_yy(zbk);
				List<String> listTree = new ArrayList<String>();
				String temp = "";
		
				for (zhibiaoku h : zhibiaoku) {
					if(Tools.notEmpty(h.getZbk_fj())){
						String[] chk =h.getZbk_fj().split(",");
						h.setFjnum(chk.length+"");
					}else{
						h.setFjnum("0");
					}
					if(h.getScorep()==null||"".equals(h.getScorep())){
						h.setScorep("0");
					}
					if(h.getZpf()==null||"".equals(h.getZpf())){
						h.setZpf("0");
					}
					if(h.getUse_dept_name()==null||"".equals(h.getUse_dept_name())){
						h.setUse_dept_name("");
					}
					if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
						h.setItem_desc("无");
					}
						temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
								+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  
										 +"lczt:\"" + h.getLczt() + "\","
								+ "finlscore:\""+ h.getScorep() + "\"," 
								+ "maxscore:\""+ h.getMaxscore() + "\","	
								+ "zpf:\""+ h.getZpf()+ "\","
								+ "dept:\""+ h.getUse_dept_name()+ "\","
								+ "fjnum:\""+ h.getFjnum()+ "\","
								+ "zbk_fj:\"" + h.getZbk_fj() + "\"}";
					listTree.add(temp);
				}
				System.out.println(JSONArray.fromObject(listTree).toString());
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
				e.toString();
			}
	}
}
