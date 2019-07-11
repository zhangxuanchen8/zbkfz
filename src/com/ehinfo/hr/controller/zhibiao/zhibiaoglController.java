package com.ehinfo.hr.controller.zhibiao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ehinfo.hr.common.utils.base.EhException;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.controller.caiji.CaiJiController;
import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.finlScore;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku2;
import com.ehinfo.hr.service.system.dict.BaseDictService;
import com.ehinfo.hr.service.system.file.BaseFileService;
import com.ehinfo.hr.service.system.org.HospitalService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.finlScoreService;
import com.ehinfo.hr.service.zhibiao.new_ryzbkService;
import com.ehinfo.hr.service.zhibiao.ryzhibiaokuService;
import com.ehinfo.hr.service.zhibiao.zbk_fjService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;
import com.ehinfo.hr.service.zhibiao.zhibiaoglService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/zhibiaogl/")
public class zhibiaoglController extends BaseController<zhibiaoku2>{
	
@Autowired
public zhibiao_sbService Service;
@Autowired
public ryzhibiaokuService ryService;
@Autowired
public zhibiaoglService score;
@Autowired
public BaseDictService dictService;
@Autowired
public finlScoreService finlService;
@Autowired
public new_ryzbkService new_ryzbkService;
@Autowired
public ModelService modelService;
@Autowired
private BaseFileService serviceFile;
@Autowired
public HospitalService hosservice;
@Autowired
private zbk_fjService fjService;

	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String relFlag,String name){
		zbkscore t = new zbkscore();
		Page<zhibiaoku> p = new Page<zhibiaoku>();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		List<zbkscore> zhibiao = null;
		o.setHosnum(a.getHosnum());
		if(name!=null&&!"".equals(name)){
			o.setItem("%"+name+"%");
			
			p=Service.findByPage(o, p);
			model.put("zhibiao", p.getResults());
			return "/ehr/zhibiao/zhibiaoku2";
		}
		if(supunit!=null||"".equals(supunit)){
			o.setI_id(supunit);
			p=Service.findByPage(o, p);
			//zhibiao = Service.findByPid(supunit);
			model.put("zhibiao", p.getResults());
			model.put("supunit", "00");
		}
		if(relFlag !=null && !"".equals(relFlag)){
			return "/ehr/zhibiao/zhibiaoku2";
		}
		
		return "/ehr/zhibiao/zhibiaogllist";
	}
	
	@RequestMapping("index1")
	public String index1(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String relFlag,String name,String type,String ismodel,String supunits){
		Page<zbkscore> p = new Page<zbkscore>();
		if(Tools.notEmpty(pageNum)){
			p.setPageNum(Integer.parseInt(pageNum));
		}
		if(Tools.notEmpty(numPerPage)){
			p.setPageSize(Integer.parseInt(numPerPage));
		}else{
			p.setPageSize(1000);
		}
		List<zbkscore> pfs = null;
		zbkscore t = new zbkscore();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		List<zbkscore> zhibiao = null;
		List<ryzbk> zhibiao1 = null;
		List<zbkscore> zbkscore = null;
		o.setHosnum(a.getHosnum());
		Modelm mod = new Modelm();
		if(Tools.notEmpty(supunit) && "zxc".equals(supunits)){
			
			model.put("dinggaoflag", "Y");
		}
		o.setI_id(supunit);
		int isdinggao=Service.isdinggao(o);
		if(isdinggao>0){
			model.put("isdinggao", isdinggao);
		}
		zhibiaoku zbk = new zhibiaoku();
		zbk.setHosnum(a.getHosnum());
		//mod.setHosnum(a.getHosnum());
		if("zxc".equals(supunits)){
			mod.setId(supunit);
			zbk.setPid(supunit);
			String allpid = Service.getpid(zbk.getPid());
			zbk.setPid(allpid);
			List<zhibiaoku> list_s = Service.findlistlasts(zbk);
			if(list_s!=null && list_s.size()>0){
				model.put("list_s", list_s.get(0));
			}
			mod = modelService.findById(mod);
			zhibiaoku sjzbk = new zhibiaoku();
			sjzbk.setI_id(supunit);
			sjzbk = Service.findsjzbk(sjzbk);
			model.put("sjzbk", sjzbk);
		}else if(Tools.notEmpty(supunits)){
			mod.setId(supunits);
			mod = modelService.findById(mod);
			zbk.setPid(supunits);
			String allpid = Service.getpid(zbk.getPid());
			zbk.setPid(allpid);
			List<zhibiaoku> list_s = Service.findlistlasts(zbk);
			if(list_s!=null && list_s.size()>0){
				model.put("list_s", list_s.get(0));
			}
		}
		model.put("mode", mod);
		model.put("supunit2", supunit);
		model.put("type", type);
			if(supunit!=null||"".equals(supunit)){
				zhibiao=score.findByPid(supunit);
				model.put("zbkscore", zhibiao);
			}
			if(Tools.isEmpty(type)){
				t.setDept(a.getPerson_dept());
			}
			if(!Tools.isNullOrEmpty(ismodel)){
				if(relFlag==null||"".equals(relFlag)){
					t.setItemid(a.getHosnum());
					p=score.findtjym(t, p);
					model.put("tjymsx", supunit);
					model.put("score", p.getResults());
					return "ehr/zhibiao/zhibiaoglrenyuan";
				}else{
					
					List<zhibiaoku> zbkus = Service.findpid(supunit, a.getHosnum());
					t.setId(supunit);
					t.setItemid(a.getHosnum());
					p=score.findtjym(t, p);
					if(Tools.notEmpty(type)){
						if(p.getResults()!=null){
							finlScore f = null;
							zbkscore z = null;
							for(zbkscore h:p.getResults()){
								z = new zbkscore();
								z.setName(supunit);
								z.setId(h.getId());
								f = new finlScore();
								f.setId(h.getId());
								List<zbkscore> list_fins = score.findsbynames(z);
								h.setZbks(list_fins);
							/*	List<finlScore> list_fin = finlService.find(f);
								if(list_fin!=null && list_fin.size()>0){
									if(list_fin.get(0)!=null){
										h.setFinlscore(list_fin.get(0).getNum());
									}
								}*/
							}
						}
					}
					model.put("zbkus", zbkus);
					model.put("tjymsx", supunit);
					model.put("score", p.getResults());
					model.put("numPerPage", p.getPageSize());
					model.put("totalcount", p.getTotalRecord());
					model.put("pageNum", p.getPageNum());
					return "ehr/zhibiao/rytjym";
				}
				
			} 
		
			
			if(relFlag !=null && !"".equals(relFlag)){
				finlScore fins = new finlScore();
				fins.setDept(a.getPerson_dept());
				fins.setHosnum(a.getHosnum());
				fins.setId(supunit);
				fins = finlService.getValidByPid(fins);
				if(fins!=null){
					model.put("tjsx", "1");
				}
				zhibiao1=ryService.findpeoplename(supunit);
				
				zbkscore = score.findByPid(supunit);
				
				if(zbkscore.size()>0){
				//model.put("tjsx", zbkscore.get(0).getXulieid());
				//	model.put("tjsj", zbkscore.get(0).getYear());
				}
				zbkscore zbks = new zbkscore();
				zbks.setId(supunit);
				zbks.setDept(a.getPerson_dept());
				List<zbkscore> list_zbks = score.findsbydept(zbks);
				if(list_zbks!=null && list_zbks.size()>0){
					model.put("tjsx", zbkscore.get(0).getXulieid());
				}
				t.setItemid(a.getHosnum());
				t.setId(supunit);
				if(Tools.notEmpty(type)){
					t.setXulieid("1");
				}
				/*pfs=score.findtjfszf(t);
				if(pfs!=null){
					if(pfs.size()>0){
						model.put("zjfs",pfs.get(0).getScorep());
					}else{
						model.put("zjfs",0);
					}
				}else{
					model.put("zjfs",0);
				}*/
				List<finlScore>fscore = new ArrayList<finlScore>();
				if(Tools.notEmpty(type)){
					fscore=finlService.findByPid(supunit,"");
				}else{
					fscore=finlService.findByPid(supunit,a.getPerson_dept());
				}
				if(fscore.size()>0){
					if(fscore.get(0)!=null){
						model.put("zjfs",fscore.get(0).getNum());
					}else{
						model.put("zjfs",0);
					}
				}else{
					model.put("zjfs",0);
				}
				model.put("name",zhibiao1.get(0).getXm());
				model.put("pid", zhibiao1.get(0).getPid());
				model.put("leixing", zhibiao1.get(0).getLeixing());
				model.put("contents", zhibiao1.get(0).getContents());
				zhibiaoku sjzbk = new zhibiaoku();
				sjzbk.setI_id(supunit);
				sjzbk = Service.findsjzbk(sjzbk);
				model.put("sjzbk", sjzbk);
				return "/ehr/zhibiao/zhibiaoglform";
			}
		//}
		return "/ehr/zhibiao/zhibiaoglrenyuan";
	}
	
	@RequestMapping("index2")
	public String index2(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String parentname,String relFlag,String name,String year){
		zbkscore t = new zbkscore();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		List<zbkscore> zhibiao = null;
		o.setHosnum(a.getHosnum());
					
			if(supunit!=null||"".equals(supunit)){
				
				zhibiao=score.findByPid(supunit);
				model.put("zbkscore", zhibiao);
			}
			
			model.put("supunit", supunit);
			model.put("year", year);
			if("0000".equals(year)){
				model.put("lbxm", "医师类");
			}else if("0001".equals(year)){
				model.put("lbxm", "药师类");
			}else if("0002".equals(year)){
				model.put("lbxm", "护理类");
			}else if("0003".equals(year)){
				model.put("lbxm", "技师类");
			}
			Modelm mod = new Modelm();
			if(Tools.notEmpty(year)){
				mod.setId(year);
				mod = modelService.findById(mod);
			}
			model.put("mode", mod);
			if(relFlag !=null && !"".equals(relFlag)){
				return "/ehr/zhibiao/zhibiaoglyearform";
			}
		//}
		return "/ehr/zhibiao/zhibiaoglyear";
	}
	@RequestMapping("xddept")
	public String xddept(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,String supunit,String supunits,String pid,String parentname,String relFlag){
		Page<zhibiaoku> p = new Page<zhibiaoku>();
		zbkscore t = new zbkscore();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		zhibiaoku zbk = new zhibiaoku();
		zbk.setHosnum(a.getHosnum());
		model.put("supunit", supunit);
		String remark ="";
		if(Tools.isEmpty(pageNum)){
			p.setPageNum(1);
		}else{
			p.setPageNum(Integer.parseInt(pageNum));
		}
		if(Tools.isEmpty(numPerPage)){
			p.setPageSize(20);
		}else{
			p.setPageSize(Integer.parseInt(numPerPage));
		}
		List<zhibiaoku> list_zbk = new ArrayList<zhibiaoku>();
		if(Tools.notEmpty(pid) && "zxc".equals(pid)){
			model.put("dinggaoflag", "Y");
		}
		if(relFlag !=null && !"".equals(relFlag)){
			if("zxc".equals(pid) || Tools.isEmpty(pid)){
				zbk.setPid(supunit);
			}else{
				zbk.setPid(pid);
			}
			String allpid = Service.getpid(zbk.getPid());
			zbk.setPid(allpid);
			List<zhibiaoku> list_s = Service.findlistlasts(zbk);
			model.put("list_s", list_s.size());
			zbk.setUse_dept("ss");
			List<zhibiaoku> list_c = Service.findlistlasts(zbk);
			zbk.setUse_dept("");
			if(!"zxc".equals(pid) && Tools.notEmpty(pid)){
				zbk.setUse_dept(supunit);
			}
			
			model.put("list_c", list_c.size());
				p = Service.findlistlast(zbk,p);
				list_zbk  =p.getResults();
				zhibiaoku zbk1 = null;
				for(zhibiaoku h:list_zbk){
					zbk1 = new zhibiaoku();
					zbk1.setI_id(h.getI_id());
					zbk1.setHosnum(a.getHosnum());
					zbk1 = Service.findzhkzh(zbk1);
					if(zbk1!=null){
						h.setItem(zbk1.getItem());
					}else{
						if(Tools.notEmpty(h.getPid())){
							remark  = allitem(h.getPid(),h.getItem(),a.getHosnum());
							h.setItem(remark);
						}
					}
				}
				model.put("totalcount", p.getTotalRecord());
				model.put("pageNum", p.getPageNum());
				model.put("numPerPage", p.getPageSize());
				model.put("list_zbk", list_zbk);
			return "/ehr/zhibiao/zhibiaogldepttable";
		}
		return "/ehr/zhibiao/zhibiaogldept";
	}
	public String allitem(String pid,String s,String hosnum){
		zhibiaoku zbk = new zhibiaoku();
		zbk.setI_id(pid);
		zbk.setHosnum(hosnum);
		zbk = Service.findById(zbk);
		if(zbk!=null){
			s=zbk.getItem()+"-"+s;
			if(Tools.notEmpty(zbk.getPid())){
				s = allitem(zbk.getPid(),s,hosnum);
			}
		}
		return s;
	}
	@RequestMapping("yulan")
	public String yulan(HttpServletRequest resq,ModelMap model,String supunit,String parentname,String pid){
		Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
        model.put("year1", supunit);
        zhibiaoku zbk = new zhibiaoku();
        zhibiaoku zbk1 = new zhibiaoku();
        zbk.setI_id(supunit);
        zbk.setHosnum(a.getHosnum());
        List<zhibiaoku> list = Service.findpid(supunit,a.getHosnum());
        zbk1=Service.findById(zbk);
		model.put("supunit1", year);
		Modelm mod = new Modelm();
		mod.setId(supunit);
		mod = modelService.findById(mod);
		model.put("lbxm2", mod.getName());
		model.put("list", list);
		model.put("mode", mod);
		model.put("pidid", supunit);
		return "/ehr/zhibiao/zhibiaoylform";
	}
	
	@RequestMapping(value="yulanmz",method=RequestMethod.GET)
	@ResponseBody
	public void yulanmz(HttpServletRequest resq,HttpServletResponse response){
		PrintWriter pw = null;
		Modelm mo = new Modelm();
        Modelm mo1 = new Modelm();
		String id = resq.getParameter("id");
		try {
			pw=response.getWriter();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			mo.setHosnum(a.getId());
		    mo.setId(id);
		    mo1=modelService.findById(mo);
		    pw.print(mo1.getName());
		} catch (IOException e) {
			e.getStackTrace();
		}finally{
			pw.flush();
			pw.close();
		}
	}
	
	
	@RequestMapping("yulan1")
	public String yulan1(HttpServletRequest resq,ModelMap model,String supunit,String parentname,String pid){
		Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
        model.put("year1", supunit);
        zhibiaoku zbk = new zhibiaoku();
        zhibiaoku zbk1 = new zhibiaoku();
        zbk.setI_id(supunit);
        zbk.setHosnum(a.getHosnum());
        List<zhibiaoku> list = Service.findpid(supunit,a.getHosnum());
        zbk1=Service.findById(zbk);
		model.put("supunit1", year);
		if("0000".equals(zbk1.getPid().substring(0,4))){
			model.put("lbxm2", "医师类");
			model.put("pidid", supunit);
		}else if("0001".equals(zbk1.getPid().substring(0,4))){
			model.put("lbxm2", "药师类");
			model.put("pidid", supunit);
		}else if("0002".equals(zbk1.getPid().substring(0,4))){
			model.put("lbxm2", "护理类");
			model.put("pidid", supunit);
		}else if("0003".equals(zbk1.getPid().substring(0,4))){
			model.put("lbxm2", "技师类");
			model.put("pidid", supunit);
		}
		model.put("list", list);
		return "/ehr/zhibiao/zhibiaoylform1";
	}
	
	
	
	@RequestMapping("addhospitalform")
	public String addhospitalform(ModelMap model,String supunit,String parentname,String pid){
		List<zhibiaoku> o = new ArrayList<zhibiaoku>();
		
		if(supunit!=null&&!"".equals(supunit)){
			o=Service.findByPid(supunit);
				model.put("supunit1", o.get(0).getItem());
				model.put("supunit",  o.get(0).getI_id());
				model.put("category", o.get(0).getCategory());
				model.put("pid", o.get(0).getPid());
		}
		return "/ehr/zhibiao/addzhibiaoku";
	}
	@RequestMapping(value="savescore",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson savescore(zhibiaoku zhibiao,String id,String supunit){
		DwzResJson resJson = new DwzResJson();
		if(id!=null&&!"".equals(id)){
			zhibiao.setI_id(id);
			Service.updatescore(zhibiao);	
			resJson.setRel("menuref2");
			resJson.setCallbackType("closeCurrent");
			resJson.setMessage("保存成功!");
		}else{
			resJson.setRel("menuref2");
			resJson.setCallbackType("closeCurrent");
			resJson.setMessage("保存失败!");
		}
		
		return resJson;
	}
	
	@RequestMapping("/xgzhibiao")
	public String xgzhibiao(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model,zhibiaoku zhib){
		List<zhibiaoku> o = new ArrayList<zhibiaoku>();
		BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
		List<zhibiaoku> o1 = new ArrayList<zhibiaoku>();
		o = Service.findByPid(id);
		o1=Service.findByPid(o.get(0).getPid());
		model.put("supunit1", o1.get(0).getItem());
		model.put("zhibiao", o.get(0));
		model.put("item", o.get(0).getItem());
		model.put("score",o.get(0).getScore());
		model.put("districtid",o.get(0).getDistrictid());
		model.put("note",o.get(0).getNote());
		model.put("category", o.get(0).getCategory());
		if(o.get(0).getHosnum()!="0000"&&!"0000".equals(o.get(0).getHosnum())){
			return "/ehr/zhibiao/addzhibiaoku2";
		}
		return "/ehr/zhibiao/addzhibiaoku";
	}
	
	
	@RequestMapping(value="delzhibiao")
	@ResponseBody
	public DwzResJson delzhibiao(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res=Service.deletelist(idslist);
				if(res==1){
					resJson.setRel("menuref1");
					resJson.setMessage("删除成功!");
				}
				else      resJson.error("请先删除所有其子组织或角色");
			} catch (Exception e) {
				resJson.error();
			}
		return resJson;
		}
	
	//原来的方法只是保存第一个的值，后面的指标值打分没有保存
	@RequestMapping(value="save1",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson save(HttpServletRequest request,String supunit2,String tableAllValue){
		DwzResJson resJson = new DwzResJson();
		JSONArray jsonArray = JSONArray.fromObject(tableAllValue);
		List<zbkscore> list = (List<zbkscore>) jsonArray.toCollection(jsonArray, zbkscore.class);
		if(list.size()>0) {
			int d=score.delete_score(supunit2,list);
			int i=score.insert_score(list);
			if(d==0){
				resJson.setMessage("旧数据清除出了点小问题");
			}else if(i==0){
				resJson.setMessage("原数据已经清除，新插入数据。。。");
			}else{
				resJson.setMessage("保存成功!");
			}
		}else {
			resJson.error("没有需要保存的新数据!");
		}
		return resJson;
	}
	
	public List<zbkscore> allscore(Boolean flag,zbkscore s,String pid,List<zbkscore> allscores,String hosnum){
		flag = true;
		List<zbkscore> scoresums = score.findssum(s);
		List<zbkscore> scoremaxs = score.findsmax(s);
		List<zbkscore> allscore = new ArrayList<zbkscore>();
		List<zbkscore> allscoresss = new ArrayList<zbkscore>();
		allscore.addAll(scoresums);
		allscore.addAll(scoremaxs);
		for(zbkscore h:allscore){
			if(Tools.notEmpty(h.getIs_sub())){
				if(Double.parseDouble(h.getScorep())<0){
					if((Double.parseDouble(h.getScorep())+Double.parseDouble(h.getIs_sub()))<0){
						h.setScorep("0");
					}else{
						h.setScorep(String.valueOf(Double.parseDouble(h.getScorep())+Double.parseDouble(h.getIs_sub())));
					}
				}else{
					h.setScorep(String.valueOf(Double.parseDouble(h.getScorep())+Double.parseDouble(h.getIs_sub())));
				}
			}
			if(Tools.notEmpty(h.getMaxscore())){
				if(Double.parseDouble(h.getMaxscore())<Double.parseDouble(h.getScorep())){
					s.setScorep(h.getMaxscore());
				}
			}
			if(pid.equals(h.getName())){
				allscores.add(h);
			}else{
				allscoresss.add(h);
				flag=false;
			}
		}
		if(allscoresss.size()>0){
			zbkscore zbk1 =null;
			zhibiaoku zbk2 = null;
			for(zbkscore z:allscoresss){
				zbk1 = new zbkscore();
				zbk1.setItemid(z.getItemid());
				zbk1.setId(s.getId());
				zbk1 = score.findsbyitemid(zbk1);
				if(zbk1!=null){
					zbk2 = new zhibiaoku();
					zbk2.setI_id(zbk1.getItemid());
					zbk2.setHosnum(hosnum);
					zbk2 = Service.findById(zbk2);
					if("1".equals(zbk2.getIs_max())){
						if(Double.parseDouble(zbk1.getScorep())>Double.parseDouble(z.getScorep())){
							z.setScorep(zbk1.getScorep());
						}
					}else{
						z.setScorep(String.valueOf(Double.parseDouble(zbk1.getScorep())+Double.parseDouble(z.getScorep())));
					}
					score.deleteitemid(zbk1);
				}
				if(Tools.notEmpty(z.getMaxscore())){
					if(Double.parseDouble(z.getMaxscore())<Double.parseDouble(z.getScorep())){
						z.setScorep(z.getMaxscore());
					}
				}
			}
			score.insert_scoress(allscoresss);
		}
		if(!flag){
			score.delete_scores(s);
			score.insert_scores(allscoresss);
			allscore(flag,s,pid,allscores,hosnum);
		}
		return allscores;
	}
	
	@RequestMapping(value="save2",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson save(HttpServletRequest request,zhibiaoku zhibiao,String id,String supunit2,String finlscore,String item,String zjfs,String type,String mail_flag){
		DwzResJson resJson = new DwzResJson();
		List<zhibiaoku> zhibiaoku = new ArrayList<zhibiaoku>();
		List<ryzbk> o1 = null;	
		String heji="";
		zhibiaoku zbk = new zhibiaoku();
		try {
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			finlScore fin = new finlScore();
			finlScore fin1 = new finlScore();
			zbkscore o= new zbkscore();
			o1 =ryService.findpeoplename(supunit2);
			/*if(o1.size()>0){
				fin1.setId(o1.get(0).getRecordno());
				fin1.setXulieid(o1.get(0).getPid());
				fin1.setYear(o1.get(0).getYear());
				fin1.setHosnum(a.getHosnum());
				fin1.setDept(a.getPerson_dept());
				fin1=finlService.findById(fin1);
			}
			if(fin1==null||"".equals(fin1)){
				fin.setId(o1.get(0).getRecordno());
				fin.setFinlscore(zjfs);
				fin.setHosnum(a.getHosnum());
				fin.setNameid(o1.get(0).getXm());
				fin.setYear(o1.get(0).getYear());
				fin.setXulieid(o1.get(0).getPid());
				fin.setDept(a.getPerson_dept());
				finlService.insert(fin);
			}else{
				fin.setId(o1.get(0).getRecordno());
				fin.setFinlscore(zjfs);
				fin.setHosnum(a.getHosnum());
				fin.setNameid(o1.get(0).getXm());
				fin.setYear(o1.get(0).getYear());
				fin.setXulieid(o1.get(0).getPid());
				fin.setDept(a.getPerson_dept());
				finlService.update(fin);
			}*/
			zbk.setPid(o1.get(0).getPid());
			zbk.setHosnum(a.getHosnum());
			if(Tools.isEmpty(type)){
				zbk.setUse_dept(a.getPerson_dept());
			}
			String allids = Service.getpid(o1.get(0).getPid());
			zbk.setPid(allids);
			zhibiaoku=Service.findbgxl(zbk);
			zbk.setPid(o1.get(0).getPid());
			List<zbkscore> alls =  new ArrayList<zbkscore>();
			for(zhibiaoku h:zhibiaoku){
				String colid = request.getParameter("finlscore"+h.getI_id());
				o= new zbkscore();
				o.setScorep(colid);
				o.setName(h.getPid());
				o.setId(supunit2);
				o.setItemid(h.getI_id());
				if(Tools.notEmpty(type)){
					o.setXulieid("1");
					o.setYear(new Date());
				}
				alls.add(o);
			}
			if(Tools.isEmpty(mail_flag)){
				score.delete_score(supunit2,alls);
				score.insert_score(alls);
			}else{
				if(Tools.notEmpty(type)){
					
					zbkscore zbkscore = new zbkscore();
					zbkscore.setId(supunit2);
					score.delete_scoress(zbkscore);
					List<zbkscore> scoresum = score.findsum(zbkscore);
					List<zbkscore> scoremax = score.findmax(zbkscore);
					List<zbkscore> allscore = new ArrayList<zbkscore>();
					List<zbkscore> allscores = new ArrayList<zbkscore>();
					List<zbkscore> allscoresss = new ArrayList<zbkscore>();
					allscore.addAll(scoremax);
					allscore.addAll(scoresum);
					List<new_ryzbk> list_ry = new_ryzbkService.findByPid(supunit2);
					boolean flags = true;
					if(allscore!=null && allscore.size()>0){
						
					
					for(zbkscore s:allscore){
						if(Tools.notEmpty(s.getIs_sub())){
							if(Double.parseDouble(s.getScorep())<0){
								if((Double.parseDouble(s.getScorep())+Double.parseDouble(s.getIs_sub()))<0){
									s.setScorep("0");
								}else{
									s.setScorep(String.valueOf(Double.parseDouble(s.getScorep())+Double.parseDouble(s.getIs_sub())));
								}
							}else{
								s.setScorep(String.valueOf(Double.parseDouble(s.getScorep())+Double.parseDouble(s.getIs_sub())));
							}
						}
						if(Tools.notEmpty(s.getMaxscore())){
							if(Double.parseDouble(s.getMaxscore())<Double.parseDouble(s.getScorep())){
								s.setScorep(s.getMaxscore());
							}
						}
						
						//allscoresss.add(s);
						if(list_ry.get(0).getPid().equals(s.getName())){
							allscores.add(s);
						}else{
							allscoresss.add(s);
							flags=false;
						}
					}
					
					List<zbkscore> all =  new ArrayList<zbkscore>();
					if(!flags){
						score.insert_scoress(allscoresss);
						score.insert_scores(allscoresss);
						all = allscore(flags,zbkscore,list_ry.get(0).getPid(),allscores,a.getHosnum());
					}
					//score.insert_scoress(allscores);
					score.delete_scores(zbkscore);
					score.insert_scores(allscores);
					zbkscore zs = new zbkscore();
					zs.setName(o1.get(0).getPid());
					List<zbkscore> allsss = score.findssumfin(zs);
					score.delete_scores(zbkscore);
					double sums = 0;
					for(zbkscore z:allsss){
						if(Tools.notEmpty(z.getScorep())){
							if(Tools.notEmpty(z.getMaxscore())){
								if(Double.parseDouble(z.getMaxscore())<Double.parseDouble(z.getScorep())){
									z.setScorep(z.getMaxscore());
								}
							}
							sums+=Double.parseDouble(z.getScorep());
						}
					}
					score.insert_scoress(allsss);
					finlScore fscores = new finlScore();
					fscores.setId(supunit2);
					fscores.setXulieid(list_ry.get(0).getPid());
					fscores.setHosnum(a.getHosnum());
					finlScore fscores1 = new finlScore();
					fscores1=finlService.findById(fscores);
					if(fscores1!=null){
						fscores.setFinlscore(sums+"");
						finlService.update(fscores);
					}else{
						fscores.setFinlscore(sums+"");
						finlService.insert(fscores);
					}
				}else{
					heji="1";
				}
				}
			}
				if(Tools.isEmpty(heji)){
					resJson.setMessage("保存成功!");
				}else{
					resJson.setMessage("未有职能科室提交分数!");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			resJson.setMessage("保存失败!");
			e.getStackTrace();
		}
	return resJson;
	}
	
	//树形表格
	@RequestMapping(value="getHospitalTree1",method=RequestMethod.POST)
	@ResponseBody
	public void  getHospitalTree(HttpServletRequest request,HttpServletResponse response,String year,String supunit){
		
		List<zhibiaoku> zhibiaoku = null;
		List<ryzbk> ryzbk = null;
		zhibiaoku zbk = new zhibiaoku();
		zbkscore o= new zbkscore();
		List<zbkscore> zbkscore = null;
			try {
				supunit = request.getParameter("supunit");
				String supunit2 = request.getParameter("supunit2");
				year = request.getParameter("year");
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				zbk.setHosnum(a.getHosnum());
				if(year!=null&&!"".equals(year)){
					zbkscore = score.findyear(supunit);
					zbk.setYear(supunit);
					zbk.setPid(year);
					zhibiaoku = Service.yearscore(zbk);
				}else{
					zbkscore = score.findByPid(supunit2);
					ryzbk=ryService.findpeoplename(supunit2);
					o.setId(supunit2);
					zbk.setPid(ryzbk.get(0).getPid());
					if(zbkscore.size()>0){
						zbk.setIs_add(supunit);
					}else{
						zbk.setIs_add("1");
					}
					int is_yy=Service.findoutyy(zbk);
					String allpid = Service.getpid(zbk.getPid());
					zbk.setPid(allpid);
					if(is_yy>0){
						zhibiaoku=Service.findlistscore_yy(zbk);
					}else{
						zhibiaoku = Service.findlistscore_ks(zbk);
					}
				}
				List<String> listTree = new ArrayList<String>();
				String temp = "";
				for (zhibiaoku h : zhibiaoku) {
					if(h.getScorep()==null||"".equals(h.getScorep())){
						h.setScorep("0");
					}
					if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
						h.setItem_desc("无");
					}
						temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
								+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
										+ h.getScorep() + "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) { 
				e.toString();
			}
	}
	
	//ren树形表格
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
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					zbk.setUse_dept(a.getPerson_dept());
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
						/*if(Tools.notEmpty(type) && !"183706".equals(a.getHosnum())){
							zbk.setType("1");
						}*/
						if(Tools.notEmpty(type)){
							zbk.setType("1");
						}
						zbk.setUser_id(supunit2);
						//int is_yy=Service.findoutyy(zbk);
						//zhibiaoku=Service.findlistscore_yy(zbk);
						if("all".equals(type)){
							String allpid = Service.getpid(zbk.getPid());
							zbk.setPid(allpid);
							zhibiaoku=Service.findlistscore_yy(zbk);
						}else{
							String remark="";
							String allpid = Service.getpid(zbk.getPid());
							zbk.setPid(allpid);
							List<zhibiaoku> list_zbk = Service.findlistbydept(zbk);
							zhibiaoku z = null;
							for(zhibiaoku h:list_zbk){
								remark+=h.getI_id()+",";
								z = new zhibiaoku();
								//z.setI_id(h.getI_id());
								z.setPid(ryzbk.get(0).getPid());	
								String allpids = Service.getpids(h.getI_id());
								z.setI_id(allpids);
								List<zhibiaoku> list_z = Service.findpidbydept(z);
								for(zhibiaoku n:list_z){
									remark+=n.getPid()+",";
								}
							}
							zbk.setPid(remark);
							zhibiaoku = Service.findlistscore_ks(zbk);
						}
					List<String> listTree = new ArrayList<String>();
					String temp = "";
					zbkscore s = null;
					for (zhibiaoku h : zhibiaoku) {
						s = new zbkscore();
						s.setItemid(h.getI_id());
						s.setId(supunit2);
						s = score.findsbyitemids(s);
						if(s!=null){
							h.setHeji(s.getScorep());
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
									+ "heji:\""+ h.getHeji()+ "\","
									+ "zpf:\""+ h.getZpf()+ "\","
									+ "dept:\""+ h.getUse_dept_name()+ "\","
									+ "zbk_fj:\"" + h.getZbk_fj() + "\"}";
						listTree.add(temp);
					}
					System.out.println(JSONArray.fromObject(listTree).toString());
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
					e.toString();
				}
		}
		@RequestMapping(value="getrysxbgTree1",method=RequestMethod.POST)
		@ResponseBody
		public void  getrysxbgTree1(HttpServletRequest request,HttpServletResponse response,String type){
			
			List<zhibiaoku> zhibiaoku = null;
			List<ryzbk> ryzbk = null;
			zhibiaoku zbk = new zhibiaoku();
			zbkscore o= new zbkscore();
			List<zbkscore> zbkscore = null;
				try {
					String supunit2 = request.getParameter("supunit2");
					String year = request.getParameter("year");
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
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
						//zhibiaoku = Service.findlistscore(zbk);
						String allpid = Service.getpid(zbk.getPid());
						zbk.setPid(allpid);
						if("all".equals(type)){
							zhibiaoku=Service.findlistscore_yy(zbk);
						}else{
							zhibiaoku = Service.findlistscore_ks(zbk);
						}
					List<String> listTree = new ArrayList<String>();
					String temp = "";
					for (zhibiaoku h : zhibiaoku) {
						if(h.getScorep()==null||"".equals(h.getScorep())){
							h.setScorep("0");
						}
						if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
							h.setItem_desc("");
						}
							temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
									+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  
											// +"lczt:\"" + h.getLczt() + "\","
									+ "finlscore:\""
											+ h.getScorep() + "\"}";
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
					e.toString();
				}
		}
	//树形表格
		@RequestMapping(value="getylTree",method=RequestMethod.POST)
		@ResponseBody
		public void  getylTree(HttpServletRequest request,HttpServletResponse response,String year1,String supunit1){
			
			List<zhibiaoku> zhibiaoku = null;
			zhibiaoku zbk = new zhibiaoku();
				try {
					Calendar date = Calendar.getInstance();
					String year = String.valueOf(date.get(Calendar.YEAR));
					supunit1=year;
					//supunit1 = request.getParameter("supunit1");
					year1 = request.getParameter("year1");
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					zbk.setHosnum(a.getHosnum());
						zbk.setYear(supunit1);
						zbk.setPid(year1);
						zhibiaoku = Service.yearscore(zbk);
					List<String> listTree = new ArrayList<String>();
					String temp = "";
					for (zhibiaoku h : zhibiaoku) {
						if(h.getScorep()==null||"".equals(h.getScorep())){
							h.setScorep("0");
						}
						if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
							h.setItem_desc("无");
						}
							temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
									+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
											+ h.getScorep() + "\"}";
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
					e.toString();
				}
		}
		
		//树形表格
		@RequestMapping(value="getylTreeyll",method=RequestMethod.POST)
		@ResponseBody
		public void  getylTreeyll(HttpServletRequest request,HttpServletResponse response,String year1,String supunit1){
			
			List<zhibiaoku> zhibiaoku = new ArrayList<zhibiaoku>();
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> zhibiaoku1 =new ArrayList<zhibiaoku>();
				try {
					Calendar date = Calendar.getInstance();
					String year = String.valueOf(date.get(Calendar.YEAR));
					//supunit1 = request.getParameter("supunit1");
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					/*zbk.setHosnum(a.getHosnum());
						zbk.setYear(supunit1);
						zbk.setPid(year1);
						zhibiaoku = Service.yearscore22(zbk);*/
					
					Modelm mo = new Modelm();
					Modelm mo1 = new Modelm();
					mo.setId(year1);
					mo.setHosnum(a.getHosnum());
					mo1=modelService.findById(mo);
					if(mo1!=null){
						zhibiaoku=Service.findpid(year1, a.getId());
						zbk.setPid(year1);
						zbk.setHosnum(a.getId());
						zhibiaoku1=Service.dayinpx(zbk);
						for(int i=0;i<zhibiaoku1.size();i++){
							zhibiaoku.add(zhibiaoku1.get(i));
						}
					}else{
						supunit1=year;
						year1 = request.getParameter("year1");
						zbk.setHosnum(a.getHosnum());
						zbk.setYear(supunit1);
						zbk.setPid(year1);
						String relFlag ="";
						zhibiaoku = Service.yearscore22(zbk);
						if(year1.length()<30){
							for(int k=0;k<zhibiaoku.size();k++){
								if("1".equals(zhibiaoku.get(k).getLast())){
									relFlag="T";
								}
							}
							if("T".equals(relFlag)||zhibiaoku.size()==0){
								zhibiaoku1 = Service.yearscore33(zbk);
								zhibiaoku.add(0, zhibiaoku1.get(0));
								for(int i=0;i<zhibiaoku.size();i++){
									/*"1".equals(zhibiaoku.get(i).getLast())&&*/
									if(year1.equals(zhibiaoku.get(i).getPid())){
										zhibiaoku.get(i).setPid(year1+supunit1+"a");
									}else if(year1.equals(zhibiaoku.get(i).getI_id())){
										zhibiaoku.get(i).setPid(year1);
										zhibiaoku.get(i).setI_id(year1+supunit1+"a");
									}
								}
							}
						}
					}
					
					List<String> listTree = new ArrayList<String>();
					String temp = "";
					for (zhibiaoku h : zhibiaoku) {
						if(h.getMaxscore()==null||"".equals(h.getMaxscore())){
							h.setMaxscore("0");
						}
						if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
							h.setItem_desc("无");
						}
							temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
									+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
											+ h.getMaxscore() + "\"}";
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
					e.toString();
				}
		}
		//树形表格
				@RequestMapping(value="getylTreeyll1",method=RequestMethod.POST)
				@ResponseBody
				public void  getylTreeyll1(HttpServletRequest request,HttpServletResponse response,String year1,String supunit1){
					
					List<zhibiaoku> zhibiaoku = null;
					zhibiaoku zbk = new zhibiaoku();
						try {
							Calendar date = Calendar.getInstance();
							String year = String.valueOf(date.get(Calendar.YEAR));
							supunit1=year;
							//supunit1 = request.getParameter("supunit1");
							year1 = request.getParameter("year1");
							BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
							zbk.setHosnum(a.getHosnum());
								zbk.setYear(supunit1);
								zbk.setPid(year1);
								zhibiaoku = Service.yearscore22(zbk);
							List<String> listTree = new ArrayList<String>();
							String temp = "";
							for (zhibiaoku h : zhibiaoku) {
								if(h.getMaxscore()==null||"".equals(h.getMaxscore())){
									h.setMaxscore("0");
								}
								if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
									h.setItem_desc("无");
								}
									temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
											+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
													+ h.getMaxscore() + "\"}";
								listTree.add(temp);
							}
							response.getWriter().print(JSONArray.fromObject(listTree).toString());
						} catch (Exception e) {
							e.toString();
						}
				}
	//市级
	@RequestMapping("getpeopleTree1")
	public void  getpeopleTree1(HttpServletRequest request,HttpServletResponse response){
		
		List<zhibiaoku> zhibiaoku = null;
		try {
			
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			zhibiaoku = Service.findlist("0001");
			List<String> listTree = new ArrayList<String>();
			String temp = "";
			for (zhibiaoku h : zhibiaoku) {
				temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
						+ h.getItem() + "\"}";
				listTree.add(temp);
			}
			response.getWriter().print(JSONArray.fromObject(listTree).toString());
		} catch (Exception e) {
		}
	}
	
	//人员
	@RequestMapping("getpeopleTree")
	public void  getpeopleTree(HttpServletRequest request,HttpServletResponse response,String type){
		
		List<ryzbk> zhibiaoku = null;
		List<BaseDict> BaseDict = null;
			try {
				
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				zhibiaoku = ryService.findpeoplelist(a.getHosnum());
				BaseDict dict = new BaseDict();
				dict.setNekey(103);
				dict.setHosnum(a.getHosnum());
				BaseDict=dictService.selectDictByNekey(dict);
				List<String> listTree = new ArrayList<String>();
				String temp = "";
				for (BaseDict h : BaseDict) {
					temp = "{id:\"" + h.getNevalue()+ "\"," + "pId:\"" + "root" + "\"," + "name:\""
							+ h.getContents() + "\"}";
					listTree.add(temp);
				}
				for (ryzbk h : zhibiaoku) {
					temp = "{id:\"" + h.getRecordno()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
							+ h.getXm() + "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
			}
	}
	@RequestMapping("getyearTree1")
	public void  getyearTree1(HttpServletRequest request,HttpServletResponse response){
		
		List<zhibiaoku> zhibiaoku = null;
		List<zhibiaoku> year = null;
		List<BaseDict> BaseDict = null;
			try {
				
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				zhibiaoku = Service.findlist(a.getHosnum());
				year = Service.findyearlist1(a.getHosnum());
				BaseDict dict = new BaseDict();
				dict.setNekey(103);
				dict.setHosnum(a.getHosnum());
				BaseDict=dictService.selectDictByNekey(dict);
				List<String> listTree = new ArrayList<String>();
				String temp = "";
				temp = "{id:\"" + "0"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
						+ "量化指标模板" + "\"}";
				listTree.add(temp);
				for (zhibiaoku t : year) {
					List<Modelm> listModel = new ArrayList<Modelm>();
					listModel = modelService.findbyyear(a.getHosnum(),t.getYear());
					for (Modelm h : listModel) {
						temp = "{id:\"" + h.getId()+ "\"," + "pId:\"" +"0" + "\"," + "name:\""
								+ h.getName() + "\"}";
						listTree.add(temp);
					}
				}
				for (zhibiaoku h : zhibiaoku) {
					if("".equals(h.getPid())){
						temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
								+ h.getItem() + "\"}";
						listTree.add(temp);
					}
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
			}
	}
	@RequestMapping("getyearTree")
	public void  getyearTree(HttpServletRequest request,HttpServletResponse response){
		
		List<zhibiaoku> zhibiaoku = null;
		List<zhibiaoku> year = null;
		List<Modelm> yearmodel = null;
		List<BaseDict> BaseDict = null;
			try {
				Modelm m = new Modelm();
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				zhibiaoku = Service.findlist(a.getHosnum());
				year = Service.findyearlist1(a.getHosnum());
				m.setHosnum(a.getHosnum());
				yearmodel = modelService.findyearmodel(m);
				BaseDict dict = new BaseDict();
				dict.setNekey(103);
				dict.setHosnum(a.getHosnum());
				BaseDict=dictService.selectDictByNekey(dict);
				List<String> listTree = new ArrayList<String>();
				String temp = "";
				temp = "{id:\"" + "0"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
						+ "年份" + "\"}";
				listTree.add(temp);
				for (Modelm h : yearmodel) {
					temp = "{id:\"" + h.getYear()+ "\"," + "pId:\"" + "0" + "\"," + "name:\""
							+ h.getYear() + "\"}";
					listTree.add(temp);
					List<Modelm> listModel = new ArrayList<Modelm>();
					listModel = modelService.findbyyear(a.getHosnum(),h.getYear());
					for (Modelm n : listModel) {
						temp = "{id:\"" + n.getId()+ "\"," + "pId:\"" +n.getYear() + "\"," + "name:\""
								+ n.getName() + "\"}";
						listTree.add(temp);
					}
				}
				
				/*for (zhibiaoku t : year) {
					temp = "{id:\"" + t.getYear()+ "\"," + "pId:\"" + "0" + "\"," + "name:\""
							+ t.getYear() + "\"}";
					listTree.add(temp);
					List<Modelm> listModel = new ArrayList<Modelm>();
					listModel = modelService.findbyyear(a.getHosnum(),t.getYear());
					for (Modelm h : listModel) {
						temp = "{id:\"" + h.getId()+ "\"," + "pId:\"" +t.getYear() + "\"," + "name:\""
								+ h.getName() + "\"}";
						listTree.add(temp);
					}
				}*/
				for (zhibiaoku h : zhibiaoku) {
					if("".equals(h.getPid())){
						temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
								+ h.getItem() + "\"}";
						listTree.add(temp);
					}
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
			}
	}
	
	String idlist(String pid , List<zhibiaoku> bsms)   {
		
		
		String cId = "";
		String tPid = pid;
		
		if("0000".equals(pid))
			tPid = "";
		if(bsms.size()>0){
			cId = bsms.get(0).getDistrictid();
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
	
	@RequestMapping("/tijiao")
	public String tijiao(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model,zhibiaoku o,String pageNum,String numPerPage){
		Page<zhibiaoku> p = new Page<zhibiaoku>();
		List<zhibiaoku> o1 = null;		
		BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
		//o.setHosnum(a.getHosnum());
		p =Service.findpeoplepage(o,p);
		if (pageNum == null) {
			p.setPageNum(1);
		} else {
			p.setPageNum(Integer.parseInt(pageNum));
		}
		if(numPerPage==null){
			p.setPageSize(30);
		}else{
			p.setPageSize(Integer.parseInt(numPerPage));
		}
		model.put("pageNum", "1");
		model.put("totalcount", p.getTotalRecord());
		model.put("numPerPage", p.getPageSize());
		model.put("rymd", p.getResults());
		return "/ehr/zhibiao/rybmtj";
	}
	
	
	@RequestMapping(value="qrtj")
	@ResponseBody
	public DwzResJson qrtj(HttpServletRequest resq, HttpServletResponse resp,String ids){
		DwzResJson resJson = new DwzResJson();
		BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
		try {
			String[] chk =ids.split(",");
			zbkscore t = new zbkscore();
			t.setDept(a.getPerson_dept());
			finlScore fin = null;
			for(int i = 0;i<chk.length;i++){
				t.setId(chk[i]);
				score.updatetjbz(t);
				fin = new finlScore();
				fin.setId(chk[i]);
				fin.setDept(a.getPerson_dept());
				finlService.updatestatu(fin);
			}
			resJson.setMessage("提交成功!");
		} catch (Exception e) {
			resJson.error();
		}
	return resJson;
	}
	@RequestMapping(value="qrtjs")
	@ResponseBody
	public DwzResJson qrtjs(HttpServletRequest resq, HttpServletResponse resp,String ids,String type){
		CaiJiController caiji = new CaiJiController();
		DwzResJson resJson = new DwzResJson();
		BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
		try {
			zhibiaoku o = new zhibiaoku();
			o.setHosnum(a.getHosnum());
			List<zhibiaoku> list_zb = null;
			String[] chk =ids.split(",");
			zbkscore t = null;
			List<ryzbk> ry = null;
			List<zbkscore> zbkscoreyear = null;
			List<new_ryzbk> newlist = null;
			Boolean flag=true;
			List<String> idslist = new ArrayList<String>();
			for(int i = 0;i<chk.length;i++){
				idslist.add(chk[i]);
				t = new zbkscore();
				t.setId(chk[i]);
				newlist = new_ryzbkService.findByPid(chk[i]);
				o.setPid(newlist.get(0).getPid());
				String allids = Service.getpid(newlist.get(0).getPid());
				o.setPid(allids);
				list_zb = Service.findbgxl(o);
				o.setPid(newlist.get(0).getPid());
				zbkscoreyear = score.find(t);
				if(zbkscoreyear!=null){
					if(zbkscoreyear.size()<list_zb.size()){
						ry = ryService.findpeoplename(chk[i]);
						flag = false;
						break;
					}
				}else{
					flag = false;
					break;
				}
			}
			Hospital hos  =new Hospital();
			hos.setHosnum(a.getHosnum());
			hos  = hosservice.findById(hos);
			String ss = "";
			if(Tools.isEmpty(hos.getJgzxid())){
				resJson.setStatusCode("300");
				resJson.setMessage("监管中心ID未维护");
			}else{
				ss = caiji.pdhosnum(hos.getHosnum(), hos.getJgzxid());
				if("1".equals(ss)){
					if(flag){
						new_ryzbkService.updatescry(idslist, "Y");
						resJson.setMessage("上传成功!");
					}else{
						resJson.setStatusCode("300");
						resJson.setMessage("上传失败!"+ry.get(0).getXm()+"填报项为完全提交");
					}
				}else{
					resJson.setStatusCode("300");
					resJson.setMessage("监管中心ID不匹配，上传失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resJson.error();
		}
	return resJson;
	}
	//人员历史
	@RequestMapping("getzbkren")
	public void  getzbkren(HttpServletRequest request,HttpServletResponse response){
		List<zbkscore> zbkscoreyear = null;
		List<ryzbk> ryzbkyear = null;
		List<Modelm> yearmodel = null;
		List<BaseDict> BaseDict = null;
			try {
				
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				ryzbkyear = ryService.findzbkren(a.getHosnum());
				zbkscoreyear = score.findzbkrenyear();
				Modelm m = new Modelm();
				m.setHosnum(a.getHosnum());
				yearmodel = modelService.findyearmodel(m);
				BaseDict dict = new BaseDict();
				dict.setNekey(103);
				dict.setHosnum(a.getHosnum());
				BaseDict=dictService.selectDictByNekey(dict);

				List<String> listTree = new ArrayList<String>();
				String temp = "";
				for (Modelm t : yearmodel) {
					temp = "{id:\"" + t.getYear()+ "\"," + "pId:\"" + "" + "\"," + "name:\""
							+ t.getYear() + "\"}";
					listTree.add(temp);
					List<Modelm> listModel = new ArrayList<Modelm>();
					listModel = modelService.findbyyear(a.getHosnum(),t.getYear());
					for (Modelm h : listModel) {
						temp = "{id:\"" + h.getId()+ "\"," + "pId:\"" + h.getYear() + "\"," + "name:\""
								+ h.getName()+ "\","+"p_id:\""+h.getYq()+"\"}";
						listTree.add(temp);
					}
				}
				
			/*	for (zbkscore t : zbkscoreyear) {
					temp = "{id:\"" + t.getYears()+ "\"," + "pId:\"" + "" + "\"," + "name:\""
							+ t.getYears() + "\"}";
					listTree.add(temp);
					List<Modelm> listModel = new ArrayList<Modelm>();
						listModel = modelService.findbyyear(a.getHosnum(),t.getYears());
						for (Modelm h : listModel) {
							temp = "{id:\"" + h.getId()+ "\"," + "pId:\"" + t.getYears() + "\"," + "name:\""
									+ h.getName()+ "\","+"p_id:\""+h.getYq()+"\"}";
							listTree.add(temp);
						}
				}*/
				for (ryzbk h : ryzbkyear) {
					temp = "{id:\"" + h.getRecordno()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
							+ h.getXm() + "\"}";
					listTree.add(temp);
				}
				if(listTree.size()<1){
					temp = "{id:\"" + "0000"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
							+ "无" + "\"}";
					listTree.add(temp);
				}
				response.getWriter().print(JSONArray.fromObject(listTree).toString());
			} catch (Exception e) {
			}
	}


	@RequestMapping("renyear")
	public String renyear(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String relFlag,String name,String ismodel,String type,String supunits){
		Page<zbkscore> p = new Page<zbkscore>();
		List<zbkscore> pfs = null;
		zbkscore t = new zbkscore();
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		List<zbkscore> zhibiao = null;
		List<ryzbk> zhibiao1 = null;
		List<zbkscore> zbkscore = null;
		o.setHosnum(a.getHosnum());
					
			if(supunit!=null||"".equals(supunit)){
				
				zhibiao=score.findByPid(supunit);
				model.put("zbkscore", zhibiao);
			}
			
			if(!Tools.isNullOrEmpty(ismodel) && !"undefined".equals(ismodel)){
				if(relFlag==null||"".equals(relFlag)){
					t.setItemid(a.getHosnum());
					p=score.findtjym(t, p);
					model.put("tjymsx", supunit);
					model.put("score", p.getResults());
					return "ehr/zhibiao/zhibiaoglrenyuan";
				}else{
					t.setId(supunit);
					t.setItemid(a.getHosnum());
					p=score.findtjym(t, p);
					model.put("tjymsx", supunit);
					model.put("flag", "Y");
					model.put("score", p.getResults());
					return "ehr/zhibiao/rytjym";
				}
				//return "ehr/zhibiao/zhibiaorenlist";
			}
			model.put("type", type);
			model.put("supunit2", supunit);
			Modelm mod = new Modelm();
			if(relFlag !=null && !"".equals(relFlag)){
				if("undefined".equals(ismodel)){
					mod.setId(supunits);
					mod = modelService.findById(mod);
				}
				model.put("mode", mod);
				zhibiao1=ryService.findpeoplename(supunit);
				zbkscore = score.findByPid(supunit);
				if(zbkscore.size()>0){
					model.put("tjsx", zbkscore.get(0).getXulieid());
					model.put("tjsj", zbkscore.get(0).getYear());
				}
				t.setItemid(a.getHosnum());
				t.setId(supunit);
				if(zhibiao1.size()>0){
					model.put("name",zhibiao1.get(0).getXm());
					model.put("pid", zhibiao1.get(0).getPid());
					model.put("leixing", zhibiao1.get(0).getLeixing());
					finlScore fin = new finlScore();
					fin.setId(zhibiao1.get(0).getRecordno());
					fin.setHosnum(a.getHosnum());
					fin.setXulieid(zhibiao1.get(0).getPid());
					fin.setYear(zhibiao1.get(0).getYear());
					finlScore fin1 =null;
					fin1=finlService.findById(fin);
					t.setItemid(a.getHosnum());
					t.setId(supunit);
					if(Tools.notEmpty(type)){
						t.setXulieid("1");
					}
					pfs=score.findtjfszf(t);
					
					if(pfs.size()>0){
						model.put("zjfs",pfs.get(0).getScorep());
					}else{
						model.put("zjfs",0);
					}
					/*if(fin1!=null){
						model.put("zjfs", fin1.getFinlscore());
					}else{
						model.put("zjfs", "");
					}*/
				}
				return "ehr/zhibiao/zhibiaorenlist";
			}
		//}
		return "ehr/zhibiao/zhibiaoren";
	}

			//人员更换类别
			@RequestMapping("new_ryzbk")
			public void  new_ryzbk(HttpServletRequest request,HttpServletResponse response){
				String id = request.getParameter("id");
				String pid = request.getParameter("a");
				List<new_ryzbk> newlist = null;
				new_ryzbk o = new new_ryzbk();
					try {
						String tt = "";
						BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
						newlist=new_ryzbkService.findByPid(id);
						o.setPid(pid);
						if("0000".equals(pid)){
							o.setLeixing("医师类");
						}else if("0001".equals(pid)){
							o.setLeixing("药师类");
						}else if("0002".equals(pid)){
							o.setLeixing("护理类");
						}else if("0003".equals(pid)){
							o.setLeixing("技师类");
						}
						o.setRecordno(newlist.get(0).getRecordno());
						o.setUnitid(newlist.get(0).getUnitid());
						o.setXm(newlist.get(0).getXm());
						o.setYear(newlist.get(0).getYear());
						o.setIdcard(newlist.get(0).getIdcard());
						o.setContents(newlist.get(0).getContents());
						new_ryzbkService.insert(o);
						
						/*if(rycp1.size()==0){v
							tt="0";
						}else{
							tt="1";
						}*/
						response.getWriter().print(1);
					} catch (Exception e) {
					}
			}
			
			@RequestMapping("/addtree")
			public String addtree(HttpServletRequest resq, HttpServletResponse resp,ModelMap model){
				String id = resq.getParameter("id");
				String goal = resq.getParameter("a");
				List<zhibiaoku> o = new ArrayList<zhibiaoku>();
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				model.put("category", id);
				List<zhibiaoku> o1 = new ArrayList<zhibiaoku>();
				List<zhibiaoku> o2 = new ArrayList<zhibiaoku>();
				o = Service.findtwo(id,a.getHosnum());
				o1=Service.findlistthree(a.getHosnum());
				o2=Service.findlistpidthree(a.getHosnum());
				if(o.size()>0){
					model.put("i_id", o.get(0).getI_id());
					model.put("item", o.get(0).getItem());
				}
				model.put("threelist", o1);
				model.put("threepidlist", o2);
				return "/ehr/zhibiao/zhibiaotree/threeaddzhibiaoku1";
			}
			
			@RequestMapping("/lookFj")
			public String lookFj(HttpServletRequest resq, HttpServletResponse resp,ModelMap model,String zbk_fj){
				String[] fjs = zbk_fj.split(",");
				List<BaseFile> list_fj = new ArrayList<BaseFile>();
				BaseFile bf = null;
				for(int i=0;i<fjs.length;i++){
					if(Tools.notEmpty(fjs[i])){
						bf = new BaseFile();
						bf.setId(fjs[i]);
						bf=serviceFile.getById(bf);
						list_fj.add(bf);
					}
				}
				model.put("list_fj", list_fj);
				return "ehr/zhibiao/zbk_lookfj";
			}
			@RequestMapping("/deptchoose")
			public String deptchoose(HttpServletRequest resq, HttpServletResponse resp,ModelMap model,String supunit,String flag){
				model.put("supunit", supunit);
				model.put("flag", flag);
				return "ehr/zhibiao/deptchoose";
			}
			@RequestMapping("deptchooses")
			public void  deptchooses(HttpServletRequest request,HttpServletResponse response,String itemdept_add,String zb_dept_id,String zb_qzdept_id,String g_dept){
				PrintWriter pw = null;
		        HttpSession session = request.getSession();
		        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		        try {
		            pw = response.getWriter();
		            List<String> idslist = new ArrayList<String>();
		            if(Tools.isEmpty(zb_dept_id) &&Tools.isEmpty(zb_qzdept_id)){
		            	new EhException().error("未选择科室或群组！");
		            }
		            if(Tools.isEmpty(itemdept_add)){
		            	new EhException().error("未选择指标项！");
		            }
	            	String[] chk =itemdept_add.split(",");
	            	for(int i = 0;i<chk.length;i++){
						idslist.add(chk[i]);
					}
	            	if(Tools.notEmpty(zb_dept_id)){
	            		int res1=Service.updatelistTree(idslist,zb_dept_id,g_dept);
	            	}else{
	            		int res1=Service.updateGroupTree(idslist, zb_qzdept_id);
	            	}
		            pw.write(EhUtil.createcallbackjson("200", "保存成功","", "menurefdept","closeCurrent", "","",""));
		        }catch (EhException error){
					logger.error(error.toString(),error);
					pw.print(EhUtil.createcallbackjson("300", error.getMessage().toString(), "", "", "", "", "", ""));
				} catch (Exception e) {
					logger.error(e.toString(),e);
					pw.print(EhUtil.createcallbackjson("300", "保存失败", "", "", "", "", "", ""));
		        }
			}
}
