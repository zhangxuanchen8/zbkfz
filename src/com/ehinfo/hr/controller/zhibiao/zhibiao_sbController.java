package com.ehinfo.hr.controller.zhibiao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.AjaxRes;
import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.EhException;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.Role;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.Zbkavgscore;
import com.ehinfo.hr.entity.zhibiao.finlScore;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.system.dict.BaseDictService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.ZbkavgscoreService;
import com.ehinfo.hr.service.zhibiao.finlScoreService;
import com.ehinfo.hr.service.zhibiao.zbkwhService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;
import com.ehinfo.hr.service.zhibiao.zhibiaoglService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage/zhibiao/")
public class zhibiao_sbController extends BaseController<Role> {
		
	@Autowired
	public zhibiaoglService zbkglService;
	@Autowired
	public zhibiao_sbService Service;
	@Autowired
	public zbkwhService zbkwhService;
	@Autowired
	public BaseDictService dictService;
	@Autowired
	public ModelService modelService;
	@Autowired
	public finlScoreService finlService;
	@Autowired
	public ZbkavgscoreService ZbkavgService;
	
		@RequestMapping("index")
		public String index(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String shangji,String relFlag,String name,String yryear,String supunits){
			Page<zhibiaoku> p = new Page<zhibiaoku>();
			List<zhibiaoku> year = null;
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			List<zhibiaoku> zhibiao = null;
			o.setI_id(supunit);
			if(Tools.notEmpty(supunit) && "zxc".equals(supunits)){
				model.put("dinggaoflag", "Y");
			}
			//判断是否已经定稿，如果已经定稿则不允许增删,后来想想，好像修改没有问题，
			int isdinggao=Service.isdinggao(o);
			if(isdinggao>0){
				model.put("isdinggao", isdinggao);
			}
			int isxt = Service.isxietong(o);
			if(isxt>0){
				model.put("isxt", isxt);
			}
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
			//判断是不是末尾节点
			List<zhibiaoku> is_last=Service.findtwo(supunit,a.getHosnum());
			if(is_last.size()>0&&is_last.get(0).getLast()!=null&&is_last.get(0).getLast().equals("1")){
				model.put("lower_level",0);
			}
			/*if(supunit!=null&&!"".equals(supunit)){
				if(!"root".equals(supunit)){
				zhibiao = Service.findpid(supunit,a.getHosnum());
				if(zhibiao.size()>0){
			if("0".equals(zhibiao.get(0).getIs_max())){
				Double zj = Service.findzj(a.getHosnum(),supunit);
				o.setScore(zj+"");
				model.put("zj",zj);
			}else{
				Double max = Service.countmax(a.getHosnum(),supunit);
				o.setScore(max+"");
				o.setHosnum(a.getHosnum());
				model.put("zj",max);
			}
			Service.upscorehj(o);
				}
				}
			}*/
			//if(yryear!=null&&!"".equals(yryear)){
			if(Tools.notEmpty(supunit)){
				year = Service.findyearlist(a.getHosnum(),supunit);
			}
				model.put("yryear", year);
				 Modelm m = new Modelm();
				 m.setHosnum(a.getHosnum());
				 List<Modelm> list_model = modelService.findyearmodels(m);
				 model.put("list_model", list_model);
			//}
				if("1".equals(shangji)){
				if(name!=null&&!"".equals(name)){
					o.setItem(name);
					
					p=Service.findByPage(o, p);
					model.put("zhibiao", p.getResults());
					model.put("totalcount", p.getTotalRecord());
					model.put("pageNum", p.getPageNum());
					model.put("numPerPage", p.getPageSize());
					if(p.getResults()!=null){
						model.put("year", p.getResults().get(0).getYear());
					}
					model.put("name",name);
					return "/ehr/zhibiao/zhibiaoku";
				}
				if(supunit!=null&&!"".equals(supunit)){
					if(!"root".equals(supunit)){
					o.setI_id(supunit);
					o.setHosnum(a.getHosnum());
					p=Service.findByPage(o, p);
					model.put("zhibiao", p.getResults());
					model.put("totalcount", p.getTotalRecord());
					model.put("pageNum", p.getPageNum());
					model.put("numPerPage", p.getPageSize());
					if(!"".equals(p.getResults())&&p.getResults()!=null){
						model.put("year", p.getResults().get(0).getYear());
					}
					model.put("supunit", supunit);
					}
				}
				if(relFlag !=null && !"".equals(relFlag)){
					return "/ehr/zhibiao/zhibiaoku";
				}
			}
			return "/ehr/zhibiao/zhibiaolist";
		}
		
		@RequestMapping("addhospitalform")
		public String addhospitalform(HttpServletRequest request,ModelMap model,String supunit,String parentname,String pid){
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<BaseDict> BaseDict = null;
			BaseDict dict = new BaseDict();
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			model.put("zhibiao_dept_id","");
			model.put("zhibiao",zbk);
			if(supunit!=null&&!"".equals(supunit)){
					o=Service.findtwo(supunit,a.getHosnum());
					if(o.size()>0){
						
						model.put("category", o.get(0).getCategory());
						model.put("pid", o.get(0).getPid());
						model.put("supunit1", o.get(0).getItem());
					}else{
						dict.setNekey(100);
						dict.setHosnum(a.getHosnum());
						BaseDict=dictService.selectDictByNekey(dict);
						/*for (BaseDict h : BaseDict) {
							if(h.getNevalue().equals(supunit)){
								model.put("category", h.getContents());
								model.put("supunit1",h.getContents());
							}
							
						}*/
						Modelm m = new Modelm();
						m.setHosnum(a.getHosnum());
						m.setId(supunit);
						m = modelService.findById(m);
						model.put("category", m.getName());
						model.put("supunit1", m.getName());
					}
				model.put("supunit", supunit);
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
		public String xgzhibiao(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model,zhibiaoku zhib,String isdinggao){
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<BaseDict> BaseDict = null;
			BaseDict dict = new BaseDict();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			List<zhibiaoku> o1 = new ArrayList<zhibiaoku>();
			o = Service.findtwo(id,a.getHosnum());
			dict.setNekey(100);
			dict.setHosnum(a.getHosnum());
			BaseDict=dictService.selectDictByNekey(dict);
			//if(o.get(0).getPid()=="0000"||"0000".equals(o.get(0).getPid())){
			//	o1=Service.findByPid(o.get(0).getPid());
			//}else{
			o1=Service.findtwo(o.get(0).getI_id(),a.getHosnum());
			model.put("isdinggao",isdinggao);
			//}
			if(o1.size()<1){
				for (BaseDict h : BaseDict) {
					if(h.getNevalue().equals(o.get(0).getPid())){
						model.put("supunit1",h.getContents());
						model.put("zhibiao", o.get(0));
						model.put("item", o.get(0).getItem());
						model.put("score",o.get(0).getScore());
						model.put("districtid",o.get(0).getDistrictid());
						model.put("note",o.get(0).getNote());
						model.put("category", o.get(0).getCategory());
						model.put("zhibiao_dept_id", o.get(0).getUse_dept());
					}
				}
			}else{
				model.put("supunit1", o1.get(0).getItem());
				model.put("zhibiao", o.get(0));
				model.put("item", o.get(0).getItem());
				model.put("score",o.get(0).getScore());
				model.put("districtid",o.get(0).getDistrictid());
				model.put("note",o.get(0).getNote());
				model.put("category", o.get(0).getCategory());
				model.put("zhibiao_dept_id", o.get(0).getUse_dept());
			}
			
			/*if(o.get(0).getHosnum()!="0000"&&!"0000".equals(o.get(0).getHosnum())){
				return "/zhibiao/addzhibiaoku2";
			}*/
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
		
		@RequestMapping(value="save",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson save(HttpServletRequest request,zhibiaoku zhibiao,String id,String supunit,String category1,String zhibiao_dept_id,String stop_time){
			DwzResJson resJson = new DwzResJson();
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
			Date stop_time_change;
			//	stop_time_change = format.parse(stop_time);
			if(id!=null&&!"".equals(id)){
				o = Service.findtwo(id,a.getHosnum());
				zhibiao.setI_id(id);
				zhibiao.setPid(o.get(0).getPid());
			}
			if(zhibiao.getI_id()==null||"".equals(zhibiao.getI_id())){
				String no = Service.getZbkseq();
				zhibiao.setI_id(no);
				zhibiao.setPid(supunit);
				zhibiao.setHosnum(a.getHosnum());
				zhibiao.setCategory(category1);
				zhibiao.setUse_dept(zhibiao_dept_id);
			//	zhibiao.setStop_time(stop_time);
				Service.insert(zhibiao);
				resJson.setRel("menuref1");
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}else{
				zhibiao.setI_id(id);
				zhibiao.setCategory(category1);
				zhibiao.setHosnum(a.getHosnum());
				zhibiao.setUse_dept(zhibiao_dept_id);
			//	zhibiao.setStop_time(stop_time);
				zbk.setPid(id);
				zbk.setHosnum(a.getHosnum());
				Service.update(zhibiao);	
				if(!"1".equals(zhibiao.getIs_using())){
					Service.updateusing(zbk);
				}
				resJson.setCallbackType("closeCurrent");
				resJson.setRel("menuref1");
				resJson.setMessage("保存成功!");
			}
			resJson.refresh("zhibiaoTree");
			return resJson;
		}
		
		@RequestMapping("getHospitalTree")
		public void  getHospitalTree(HttpServletRequest request,HttpServletResponse response){
			
			List<zhibiaoku> zhibiaoku = null;
			List<BaseDict> BaseDict = null;
				try {
					
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					zhibiaoku = Service.findlist(a.getHosnum());
					BaseDict dict = new BaseDict();
					dict.setNekey(102);
					dict.setHosnum(a.getHosnum());
					BaseDict=dictService.selectDictByNekey(dict);
					String temp = "";
					List<String> listTree = new ArrayList<String>();
					/*temp = "{id:\"" + "root"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
							+ "指标模板" + "\"}";
					listTree.add(temp);*/
					for (BaseDict h : BaseDict) {
						temp = "{id:\"" + h.getNevalue()+ "\"," + "pId:\"" + "root" + "\"," + "name:\""
								+ h.getContents() + "\"}";
						listTree.add(temp);
					}
					
					for (zhibiaoku h : zhibiaoku) {
						if(h.getPid()==null||"".equals(h.getPid())){
							temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getIs_sub() + "\"," + "name:\""
									+ h.getItem() + "\"}";
						}else{
							temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
									+ h.getItem() + "\"}";
						}
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
				}
		}
		
		
		@RequestMapping("getHospitalTree10")
		public void  getHospitalTree10(HttpServletRequest request,HttpServletResponse response){
			
			List<zhibiaoku> zhibiaoku = null;
			List<BaseDict> BaseDict = null;
				try {
					
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					zhibiaoku = Service.findjcb(a.getHosnum());
					BaseDict dict = new BaseDict();
					dict.setNekey(101);
					dict.setHosnum(a.getHosnum());
					BaseDict=dictService.selectDictByNekey(dict);
					String temp = "";
					List<String> listTree = new ArrayList<String>();
					
					for (zhibiaoku h : zhibiaoku) {
						temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
								+ h.getItem() + "\"}";
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
				}
		}
	    @RequestMapping(value = "getZhiBiaoTreess", method = RequestMethod.POST)
	    @ResponseBody
	    public void getquyuTreeJson(HttpServletResponse response,String nekey,HttpSession session,String tag) {
	        PrintWriter pw = null;
	        BasUser user = (BasUser) session.getAttribute(Const.SESSION_USER);
	        try {
	            pw = response.getWriter();
	            String hosnum = user.getPerson_dept();
	            List<ZNodes> r = Service.getZhiBiaoTree(hosnum);
	            JSONArray subMsgs = JSONArray.fromObject(r);
	            String ssss = subMsgs.toString();
	            pw.print(ssss);
	        } catch (Exception e) {
	            pw.print("error");
	        }
	    }
		@RequestMapping(value="getZhiBiaoTree", method=RequestMethod.POST)
		@ResponseBody
		public AjaxRes getZhiBiaoTree(HttpServletRequest request,String hosnum1,String flag){
			AjaxRes ar=getAjaxRes();
			String hosnum = null;
			HttpSession session = request.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			String id=a.getPerson_dept();
				try {
					List<ZNodes> r=Service.getZhiBiaoTree(id);
					for(int i=1;i<r.size();i++){
						if(r.get(i).getId()==null){
							r.get(i).setIconSkin("person");
						}
					}
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("list", r);
					ar.setSucceed(m);
				} catch (Exception e) {
					logger.error(e.toString(),e);
					ar.setFailMsg(Const.DATA_FAIL);
				}
			
			return ar;
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
		
		@RequestMapping(value="dinggao",method=RequestMethod.GET)
		@ResponseBody
		public void dinggao(HttpServletRequest resq,HttpServletResponse response, String id, String pId,ModelMap model,zhibiaoku zhib){
			PrintWriter pw = null;
			String i = resq.getParameter("dinggao");
			try {
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				zhib.setHosnum(a.getHosnum());
				//if("0000".equals(i)||"0001".equals(i)||"0002".equals(i)||"0003".equals(i)){
					Calendar date = Calendar.getInstance();
				    String year = String.valueOf(date.get(Calendar.YEAR));
				    zhib.setPid(i);
				    zhib.setYear(year);
				    //四大类是写死了的，在运用的表中不存在数据，导致我们做起来不好做，这里把大类也插入数据库，单纯的只是用来给后买呢判断他是不是后已经定稿
				    Service.insertsilei(i,a.getHosnum());
				    String allids = Service.getpid(i);
				    zhib.setPid(allids);
				    Service.deldinggao(zhib);
				    
					Service.updatedinggao(zhib);
					/*Page<zhibiaoku> p = new Page<zhibiaoku>();
					p.setPageSize(1000);
					zhib.setPid(i);
					List<zhibiaoku> list_zbk = new ArrayList<zhibiaoku>();
					p = Service.findlistlast(zhib,p);
					list_zbk = p.getResults();
					String remark ="";
					for(zhibiaoku h:list_zbk){
						if(Tools.notEmpty(h.getPid())){
							remark  = allitem(h.getPid(),h.getItem(),a.getHosnum());
							h.setItem(remark);
						}
					}
					Service.insertzbkf(list_zbk);*/
				//}
				pw=response.getWriter();
				pw.flush();
				pw.close();
			} catch (IOException e) {
			}
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
		@RequestMapping(value="dinggao23",method=RequestMethod.GET)
		@ResponseBody
		public void dinggao23(HttpServletRequest resq,HttpServletResponse response, String id, String pId,ModelMap model,zhibiaoku zhib){
			PrintWriter pw = null;
			zhibiaoku ku = new zhibiaoku();
			List<zhibiaoku> list = new ArrayList<zhibiaoku>();
			List<zhibiaoku> list1 = new ArrayList<zhibiaoku>();
			String i = resq.getParameter("dinggao1");
			String lg = "0";
			try {
				pw=response.getWriter();
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				ku.setPid(i);
				ku.setHosnum(a.getHosnum());
				list=Service.findjydinggao(ku);
				for(int k=0;k<list.size();k++){
					if(!"1".equals(list.get(k).getLast())){
						pw.print("1");
						break;
					}
				}
				list1=Service.findjydinggao1(ku);
				for(int q=0;q<list1.size();q++){
					if(!"0".equals(list1.get(q).getLast())){
						lg="1";
						break;
					}
				}
			} catch (IOException e) {
				e.getStackTrace();
			}finally{
				pw.print(lg);
				pw.flush();
				pw.close();
			}
		}
		
		@RequestMapping(value="deldinggao",method=RequestMethod.GET)
		@ResponseBody
		public void deldinggao(HttpServletRequest resq,HttpServletResponse response, String id, String pId,ModelMap model,zhibiaoku zhib){
			PrintWriter pw = null;
			String i = resq.getParameter("dinggao");
			try {
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				zhib.setHosnum(a.getHosnum());
				//if("0000".equals(i)||"0001".equals(i)||"0002".equals(i)||"0003".equals(i)){
					Calendar date = Calendar.getInstance();
				    String year = String.valueOf(date.get(Calendar.YEAR));
				    zhib.setPid(i);
				    zhib.setYear(year);
				    Service.deletesilei(i);
				    String allids = Service.getpid(i);
				    zhib.setPid(allids);
				    Service.deldinggao(zhib);
				    
				//}
				pw=response.getWriter();
				pw.flush();
				pw.close();
			} catch (IOException e) {
			}
		}
		@RequestMapping(value="beforeSynergy")
		public String  beforeSynergy(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String dinggao){
			model.put("dinggao",dinggao);
			return "/ehr/zhibiao/addzhibiaostopmark";
		}
		@RequestMapping(value="synergy",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson synergy(HttpServletRequest res,HttpServletResponse rep,String dinggao,String stop_time){
			DwzResJson resJson = new DwzResJson();
			zhibiaoku zhib=new zhibiaoku();
			BasUser a = (BasUser) res.getSession().getAttribute(Const.SESSION_USER);
			zhib.setHosnum(a.getHosnum());
			//if("0000".equals(dinggao)||"0001".equals(dinggao)||"0002".equals(dinggao)||"0003".equals(dinggao)){
				Calendar date = Calendar.getInstance();
			    String year = String.valueOf(date.get(Calendar.YEAR));
			    zhib.setPid(dinggao);
			    zhib.setYear(year);
			    List<zhibiaoku> zhibiaoku=Service.allindept(zhib);
			    if(zhibiaoku.size()==0){
			    	//更改了逻辑，指标添加的时候不在单一指定打分截止时间，而是现在协同的时候添加打分截止时间,两个表不一样
			    	if(Tools.notEmpty(stop_time)){
			    		 Modelm m = new Modelm();
			    		 m.setId(dinggao);
			    		 m.setStoptime(stop_time);
			    		 m.setHosnum(a.getHosnum());
			    		zhib.setStop_time(stop_time);
				    	Service.updatezhibiaostoptime(zhib);
				    	Service.updatesynergy(zhib);
				    	modelService.updatesynergy(m);
				    	resJson.setMessage("协同成功");
			    	}else{
			    		resJson.setMessage("200");
			    	}
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
			    }else{
			    	String mess="";
			    	for(int i=0;i<zhibiaoku.size();i++){
			    		mess+=zhibiaoku.get(i).getItem()+",";
			    	}
			    	mess+="没有指定科室或群组";
			    	resJson.error(mess);
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
			    }
			//}
			return resJson;
		}
//		@RequestMapping(value="synergy",method=RequestMethod.GET)
//		@ResponseBody
//		public void  synergy(HttpServletRequest resq,HttpServletResponse response, String id, String pId,ModelMap model,zhibiaoku zhib){
//			PrintWriter pw = null;
//			String i = resq.getParameter("dinggao");
//			try {
//				pw=response.getWriter();
//				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
//				zhib.setHosnum(a.getHosnum());
//				if("0000".equals(i)||"0001".equals(i)||"0002".equals(i)||"0003".equals(i)){
//					Calendar date = Calendar.getInstance();
//				    String year = String.valueOf(date.get(Calendar.YEAR));
//				    zhib.setPid(i);
//				    zhib.setYear(year);
//				    List<zhibiaoku> zhibiaoku=Service.allindept(zhib);
//				    if(zhibiaoku.size()==0){
//				    	Service.updatesynergy(zhib);
//				    }else{
//				    	JSONArray json = JSONArray.fromObject(zhibiaoku);     
//				    	pw.write(json.toString());
//				    }
//				}
//				pw.flush();
//				pw.close();
//			} catch (IOException e) {
//			}
//		}
		
		@RequestMapping(value="yinru",method=RequestMethod.POST)
		@ResponseBody
		public void yinru(HttpServletRequest resq,HttpServletResponse response, String yryr, String pid,ModelMap model,zhibiaoku zhib, String supunit){
			PrintWriter pw = null;
			try {
				pw=response.getWriter();
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				if(Tools.notEmpty(yryr)){
					zhib.setHosnum(a.getHosnum());
					zhib.setPid(pid);
					zhib.setYear(yryr);
					String allids = Service.getpid(pid);
					zhib.setPid(allids);
					Service.delcf(zhib);
					Modelm m = new Modelm();
					m.setId(pid);
					m = modelService.findById(m);
					zhibiaoku zbk = new zhibiaoku();
					zbk.setHosnum(a.getHosnum());
					zbk.setI_id(yryr);
					List<zhibiaoku> list_zbk = Service.findidpid(zbk);
					String i_id="";
					for(int i=0;i<list_zbk.size();i++){
						if(list_zbk.get(i).getPid().equals(yryr)){
							list_zbk.get(i).setPid(pid);
						}
						i_id = Service.getZbkseq();
						for(int k=0;k<list_zbk.size();k++){
							if(list_zbk.get(i).getI_id().equals(list_zbk.get(k).getPid())){
								list_zbk.get(k).setPid(i_id);
							}
						}
						list_zbk.get(i).setI_id(i_id);
					}
					for(zhibiaoku z:list_zbk){
						z.setCategory(m.getName());
						Service.insert(z);
					}
					//Service.yinru(zhib);
				}else{
					pw.print("0");
				}
				pw.flush();
				pw.close();
			} catch (IOException e) {
				pw.print("3");
			}
		}
		@RequestMapping(value="commonzhibiao")
		public String  commonzhibiao(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String supunit,String pageNum,String numPerPage,String category,String name){
			Page<zbkwh> p = new Page<zbkwh>();
			if(Tools.notEmpty(pageNum)){
				p.setPageNum(Integer.parseInt(pageNum));
			}else{
				p.setPageNum(1);
			}
			if(Tools.notEmpty(numPerPage)){
				p.setPageSize(Integer.parseInt(numPerPage));
			}else{
				p.setPageSize(10);
			}
			zbkwh zbk=new zbkwh();
			zbk.setCategory(category);
			zbk.setZbkmc(name);
			p=zbkwhService.findByPage(zbk, p);
			model.put("zhibiao_wh_list", p.getResults());
			model.put("numPerPage",p.getPageSize());
			model.put("pageNum", p.getPageNum());
			model.put("totalcount", p.getTotalRecord());
			model.put("supunit",supunit);
			model.put("name",name);
			model.put("category",category);
			return "/ehr/zhibiao/commonzhibiao";
		}
		@RequestMapping(value="zhibiaoyinru",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson  zhibiaoyinru(HttpServletRequest req,HttpServletResponse rep,String list,String pid){
			DwzResJson resJson=new DwzResJson();
			HttpSession session = req.getSession();
			BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
			String[] idlist=list.split(",");
			List<zbkwh> zbklist=zbkwhService.findlist(idlist);
			if(zbklist!=null&&zbklist.size()>0){
				int res=Service.insertlist(zbklist,pid,a.getHosnum());
				if(res==1){
					resJson.setMessage("引入成功");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
				}else{
					resJson.setMessage("引入失败");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
				}
			}
			return resJson;
		}
		
		@RequestMapping(value="addtree")
		public String  addtree(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String ids){
			model.put("ids",ids.substring(0, ids.length()-1));
			return "/ehr/zhibiao/addzhibiaokuTree";
		}
		@RequestMapping(value="saveTree",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson saveTree(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String ids,String zhibiao_dept_id){
			DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res1=Service.updatelistTree(idslist,zhibiao_dept_id,"");
				if(res1==1){
					resJson.setMessage("保存成功");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menurefdept");
				}else{
					resJson.setMessage("保存失败");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menurefdept");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
//				resJson.setStatusCode("300");
				resJson.setMessage("出错了。。。");
				resJson.setCallbackType("closeCurrent");
				resJson.setRel("menurefdept");
			}
			return resJson;
		}
		
		@RequestMapping("getrenyyear")
		public String  getrenyyear(HttpServletRequest request,HttpServletResponse response,ModelMap model){
			
			return "/ehr/zhibiao/zhibiaoren";
		}
		
		/**
		 * 拼接功能	
		 * @return
		 */
		@RequestMapping("getthreepj")
		public String getthreepj(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String shangji,String relFlag,String name,String yryear){
				Page<zhibiaoku> p = new Page<zhibiaoku>();
				BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
				o.setI_id(supunit);
/*				if(supunit!=null&&!"".equals(supunit)){
					model.put("supunit", supunit);
					return "/ehr/zhibiao/zhibiaotree/threepj";
				}*/
				if(relFlag !=null && !"".equals(relFlag)){
					if(supunit!=null&&!"".equals(supunit)){
						model.put("supunit", supunit);
						return "/ehr/zhibiao/zhibiaotree/threepj";
					}
				}
					
			return "/ehr/zhibiao/zhibiaotree/threelist";
		}
				
		@RequestMapping("/threexg")
		public String threexg(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model,zhibiaoku zhib){
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<BaseDict> BaseDict = null;
			BaseDict dict = new BaseDict();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			List<zhibiaoku> o1 = new ArrayList<zhibiaoku>();
			o = Service.findtwo(id,a.getHosnum());
			o1=Service.findlistthree(a.getHosnum());
			if(o.size()>0){
				model.put("i_id", o.get(0).getI_id());
				model.put("item", o.get(0).getItem());
			}
			model.put("threelist", o1);
			return "/ehr/zhibiao/zhibiaotree/threexg";
		}
				
		@RequestMapping(value="savepj",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson savepj(HttpServletRequest request,ModelMap model,String id,String pid,String name,String sftree){
			DwzResJson resJson = new DwzResJson();
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<zhibiaoku> teee=null;
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			o=Service.findtwo(id, a.getHosnum());
			if("0".equals(sftree)){
				List<zhibiaoku> idtree =null;
				List<zhibiaoku> pidtree =null;
				zbk.setI_id(pid);
				zbk.setHosnum(a.getHosnum());
				pidtree=Service.findpidid(zbk);
				int treeint=0;
				for(int k=0;k<pidtree.size();k++){
					zhibiaoku pidzbk = new zhibiaoku();
					pidzbk.setI_id(pidtree.get(k).getI_id());
					pidzbk.setItem(pidtree.get(k).getItem());
					pidzbk.setScore(pidtree.get(k).getScore());
					pidzbk.setCategory(pidtree.get(k).getCategory());
					pidzbk.setItem_desc(pidtree.get(k).getItem_desc());
					pidzbk.setPid(pidtree.get(k).getPid());
					pidzbk.setKeshi(pidtree.get(k).getKeshi());
					pidzbk.setIs_max(pidtree.get(k).getIs_max());
					pidzbk.setIs_using(pidtree.get(k).getIs_using());
					pidzbk.setHosnum(pidtree.get(k).getHosnum());
					pidzbk.setFinlscore(pidtree.get(k).getFinlscore());
					pidzbk.setLast(pidtree.get(k).getLast());
					pidzbk.setXuhao(k+"");
					pidzbk.setIs_sub(pidtree.get(k).getIs_sub());
					pidzbk.setTreename(name);
					teee=Service.treename(a.getHosnum(), pidtree.get(k).getI_id(), name);
					if(teee.size()>0){
						Service.update(pidzbk);
					}else{
						Service.insert(pidzbk);
					}
					if(pid.equals(pidtree.get(k).getI_id())){
						treeint++;
						zbk.setI_id(id);
						zbk.setHosnum(a.getHosnum());
						idtree=Service.findidpid(zbk);
						for(int i=0;i<idtree.size();i++){
							zhibiaoku idzbk = new zhibiaoku();
							if(idtree.get(i).getPid()==null||"".equals(idtree.get(i).getPid())){
								idzbk.setPid(pid);
							}else{
								idzbk.setPid(idtree.get(i).getPid());
							}
							idzbk.setI_id(idtree.get(i).getI_id());
							idzbk.setItem(idtree.get(i).getItem());
							idzbk.setScore(idtree.get(i).getScore());
							idzbk.setCategory(idtree.get(i).getCategory());
							idzbk.setItem_desc(idtree.get(i).getItem_desc());
							idzbk.setIs_max(idtree.get(i).getIs_max());
							idzbk.setIs_using(idtree.get(i).getIs_using());
							idzbk.setHosnum(idtree.get(i).getHosnum());
							idzbk.setFinlscore(idtree.get(i).getFinlscore());
							idzbk.setLast(idtree.get(i).getLast());
							idzbk.setKeshi(idtree.get(i).getKeshi());
							idzbk.setXuhao(treeint+"");
							idzbk.setIs_sub("0");
							idzbk.setTreename(name);
							teee=Service.treename(a.getHosnum(), idtree.get(i).getI_id(), name);
							if(teee.size()>0){
								Service.update(idzbk);
							}else{
								Service.insert(idzbk);
							}
							treeint++;
						}
					}
				}
			}
			
			
			if(o.get(0).getPid()!=null&&!"".equals(o.get(0).getPid())){
				resJson.error("11111");
			}else{
				List<zhibiaoku> idtree =null;
				List<zhibiaoku> pidtree =null;
				zbk.setI_id(pid);
				zbk.setHosnum(a.getHosnum());
				pidtree=Service.findpidid(zbk);
				int treeint=0;
				for(int k=0;k<pidtree.size();k++){
					zhibiaoku pidzbk = new zhibiaoku();
					pidzbk.setI_id(pidtree.get(k).getI_id());
					pidzbk.setItem(pidtree.get(k).getItem());
					pidzbk.setScore(pidtree.get(k).getScore());
					pidzbk.setCategory(pidtree.get(k).getCategory());
					pidzbk.setItem_desc(pidtree.get(k).getItem_desc());
					pidzbk.setPid(pidtree.get(k).getPid());
					pidzbk.setKeshi(pidtree.get(k).getKeshi());
					pidzbk.setIs_max(pidtree.get(k).getIs_max());
					pidzbk.setIs_using(pidtree.get(k).getIs_using());
					pidzbk.setHosnum(pidtree.get(k).getHosnum());
					pidzbk.setFinlscore(pidtree.get(k).getFinlscore());
					pidzbk.setLast(pidtree.get(k).getLast());
					pidzbk.setXuhao(k+"");
					pidzbk.setIs_sub(pidtree.get(k).getIs_sub());
					pidzbk.setTreename(name);
					teee=Service.treename(a.getHosnum(), pidtree.get(k).getI_id(), name);
					if(teee.size()>0){
						Service.updatettree(pidzbk);
					}else{
						Service.inserttree(pidzbk);
					}
					treeint++;
					if(pid.equals(pidtree.get(k).getI_id())){
						zbk.setI_id(id);
						zbk.setHosnum(a.getHosnum());
						idtree=Service.findidpid(zbk);
						for(int i=0;i<idtree.size();i++){
							zhibiaoku idzbk = new zhibiaoku();
							if(idtree.get(i).getPid()==null||"".equals(idtree.get(i).getPid())){
								idzbk.setPid(pid);
							}else{
								idzbk.setPid(idtree.get(i).getPid());
							}
							idzbk.setI_id(idtree.get(i).getI_id());
							idzbk.setItem(idtree.get(i).getItem());
							idzbk.setScore(idtree.get(i).getScore());
							idzbk.setCategory(idtree.get(i).getCategory());
							idzbk.setItem_desc(idtree.get(i).getItem_desc());
							idzbk.setIs_max(idtree.get(i).getIs_max());
							idzbk.setIs_using(idtree.get(i).getIs_using());
							idzbk.setHosnum(idtree.get(i).getHosnum());
							idzbk.setFinlscore(idtree.get(i).getFinlscore());
							idzbk.setLast(idtree.get(i).getLast());
							idzbk.setKeshi(idtree.get(i).getKeshi());
							idzbk.setXuhao(treeint+"");
							idzbk.setIs_sub("0");
							idzbk.setTreename(name);
							teee=Service.treename(a.getHosnum(), idtree.get(i).getI_id(), name);
							if(teee.size()>0){
								Service.updatettree(idzbk);
							}else{
								Service.inserttree(idzbk);
							}
							treeint++;
						}
					}
				}
			}
			return resJson;
		}
		
		@RequestMapping("getTreegroup")
		public void  getTreegroup(HttpServletRequest request,HttpServletResponse response){
			List<zhibiaoku> zhibiaoku = null;
			List<BaseDict> BaseDict = null;
				try {
					BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
					zhibiaoku = Service.findtreelist(a.getHosnum());
					BaseDict dict = new BaseDict();
					dict.setNekey(91);
					dict.setHosnum(a.getHosnum());
					BaseDict=dictService.selectDictByNekey(dict);
					String temp = "";
					List<String> listTree = new ArrayList<String>();
					temp = "{id:\"" + "0"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
					+ "全院" + "\"}";
					listTree.add(temp);
					temp = "{id:\"" + "1"+ "\"," + "pId:\"" + "" + "\"," + "name:\""
							+ "分院" + "\"}";
					listTree.add(temp);
					for (zhibiaoku h : zhibiaoku) {
						if(h.getPid()==null||"".equals(h.getPid())){
							temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getKeshi() + "\"," + "name:\""
									+ h.getItem() + "\"}";
						}else{
							temp = "{id:\"" + h.getI_id()+ "\"," + "pId:\"" + h.getPid() + "\"," + "name:\""
									+ h.getItem() + "\"}";
						}
						listTree.add(temp);
					}
					response.getWriter().print(JSONArray.fromObject(listTree).toString());
				} catch (Exception e) {
				}
				}
				
				@RequestMapping(value="getTreeyyl",method=RequestMethod.POST)
				@ResponseBody
				public void  getTreeyyl(HttpServletRequest request,HttpServletResponse response,String pid){
					
					List<zhibiaoku> zhibiaoku = null;
					zhibiaoku zbk = new zhibiaoku();
						try {
							Calendar date = Calendar.getInstance();
//							String year = String.valueOf(date.get(Calendar.YEAR));
//							supunit1=year;
							//supunit1 = request.getParameter("supunit1");
							pid = request.getParameter("year1");
							BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
							zbk.setHosnum(a.getHosnum());
							zbk.setPid(pid);
							zhibiaoku = Service.treeyl(zbk);
							List<String> listTree = new ArrayList<String>();
							String temp = "";
							for (zhibiaoku h : zhibiaoku) {
								if(h.getScorep()==null||"".equals(h.getScorep())){
									h.setScorep("0");
								}
								if(h.getFinlscore()==null||"".equals(h.getFinlscore())){
									h.setFinlscore("0");
								}
								if(h.getItem_desc()==null||"".equals(h.getItem_desc())){
									h.setItem_desc("");
								}
								if(h.getPid()==null||"".equals(h.getPid())){
									temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getKeshi() + "\"," + "name:\""
											+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
											+ h.getFinlscore() + "\"}";
								}else{
									temp = "{id:\"" + h.getI_id()+ "\"," + "parentId:\"" + h.getPid() + "\"," + "name:\""
											+ h.getItem() + "\"," + "score:\"" + h.getScore() + "\","+ "last:\"" + h.getLast() + "\","+ "item_desc:\"" + h.getItem_desc() + "\","  + "finlscore:\""
											+ h.getFinlscore() + "\"}";
								}
								
								listTree.add(temp);
							}
							response.getWriter().print(JSONArray.fromObject(listTree).toString());
						} catch (Exception e) {
							e.toString();
						}
				}
		
		
		
		
//		指标库模板
		@RequestMapping("index2")
		public String index2(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,zhibiaoku o,String supunit,String shangji,String relFlag,String name,String yryear){
			Page<zhibiaoku> p = new Page<zhibiaoku>();
			List<zhibiaoku> year = null;
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			List<zhibiaoku> zhibiao = null;
			o.setI_id(supunit);
			//判断是否已经定稿，如果已经定稿则不允许增删,后来想想，好像修改没有问题，
			int isdinggao=Service.isdinggao(o);
			if(isdinggao>0){
				model.put("isdinggao", isdinggao);
			}
			int isxt = Service.isxietong(o);
			if(isxt>0){
				model.put("isxt", isxt);
			}
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
			//判断是不是末尾节点
			List<zhibiaoku> is_last=Service.findtwo(supunit,a.getHosnum());
			if(is_last.size()>0&&is_last.get(0).getLast()!=null&&is_last.get(0).getLast().equals("1")){
				model.put("lower_level",0);
			}
			if(supunit!=null&&!"".equals(supunit)){
				if(!"root".equals(supunit)){
				zhibiao = Service.findpid(supunit,a.getHosnum());
				if(zhibiao.size()>0){
			if("0".equals(zhibiao.get(0).getIs_max())){
				Double zj = Service.findzj(a.getHosnum(),supunit);
				o.setScore(zj+"");
				model.put("zj",zj);
			}else{
				Double max = Service.countmax(a.getHosnum(),supunit);
				o.setScore(max+"");
				o.setHosnum(a.getHosnum());
				model.put("zj",max);
			}
			Service.upscorehj(o);
				}
				}
			}
			if(Tools.notEmpty(supunit)){
				year = Service.findyearlist(a.getHosnum(),supunit);
			}
				 model.put("yryear", year);
				if("1".equals(shangji)){
				if(name!=null&&!"".equals(name)){
					o.setItem(name);
					
					p=Service.findByPage(o, p);
					model.put("zhibiao", p.getResults());
					model.put("totalcount", p.getTotalRecord());
					model.put("pageNum", p.getPageNum());
					model.put("numPerPage", p.getPageSize());
					if(p.getResults()!=null){
						model.put("year", p.getResults().get(0).getYear());
					}
					model.put("name",name);
					return "/ehr/zhibiao/zhibiaotree/threezhibiaoku";
				}
				if(supunit!=null&&!"".equals(supunit)){
					if(!"root".equals(supunit)){
					o.setI_id(supunit);
					o.setHosnum(a.getHosnum());
					p=Service.findByPage(o, p);
					model.put("zhibiao", p.getResults());
					model.put("totalcount", p.getTotalRecord());
					model.put("pageNum", p.getPageNum());
					model.put("numPerPage", p.getPageSize());
					if(!"".equals(p.getResults())&&p.getResults()!=null){
						model.put("year", p.getResults().get(0).getYear());
					}
					model.put("supunit", supunit);
					}
				}
				if(relFlag !=null && !"".equals(relFlag)){
					return "/ehr/zhibiao/zhibiaotree/threezhibiaoku";
				}
			}
			return "/ehr/zhibiao/zhibiaotree/threezhibiaolist";
		}
		
		
		@RequestMapping("addhospitalform2")
		public String addhospitalform2(HttpServletRequest request,ModelMap model,String supunit,String parentname,String pid){
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<BaseDict> BaseDict = null;
			BaseDict dict = new BaseDict();
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			model.put("zhibiao_dept_id","");
			model.put("zhibiao",zbk);
			if(supunit!=null&&!"".equals(supunit)){
					o=Service.findtwo(supunit,a.getHosnum());
					if(o.size()>0){
						
						model.put("category", o.get(0).getCategory());
						model.put("pid", o.get(0).getPid());
						model.put("supunit1", o.get(0).getItem());
					}else{
						dict.setNekey(100);
						dict.setHosnum(a.getHosnum());
						BaseDict=dictService.selectDictByNekey(dict);
						for (BaseDict h : BaseDict) {
							if(h.getNevalue().equals(supunit)){
								model.put("category", h.getContents());
								model.put("supunit1",h.getContents());
							}
							
						}
					}
				model.put("supunit", supunit);
			}else{
				return "/ehr/zhibiao/zhibiaotree/threeaddzhibiaoku";
			}
			return "/ehr/zhibiao/zhibiaotree/threeaddzhibiaokumb";
		}
		
		
		@RequestMapping(value="save2",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson save2(HttpServletRequest request,zhibiaoku zhibiao,String id,String supunit,String category1,String zhibiao_dept_id,String stop_time,String pid){
			DwzResJson resJson = new DwzResJson();
			zhibiaoku zbk = new zhibiaoku();
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
			Date stop_time_change;
			//	stop_time_change = format.parse(stop_time);
			if(id!=null&&!"".equals(id)){
				o = Service.findtwo(id,a.getHosnum());
				zhibiao.setI_id(id);
				zhibiao.setPid(o.get(0).getPid());
			}
			if(zhibiao.getI_id()==null||"".equals(zhibiao.getI_id())){
				o=null;
				String no = Service.getZbkseq();
				o=Service.findtwo(pid, a.getHosnum());
				zhibiao.setI_id(no);
				zhibiao.setPid(pid);
				zhibiao.setHosnum(a.getHosnum());
				if(zhibiao.getKeshi()==null||"".equals(zhibiao.getKeshi())){
					zhibiao.setKeshi(o.get(0).getKeshi());
					zhibiao.setIs_sub(o.get(0).getIs_sub());
				}
				zhibiao.setCategory(category1);
				zhibiao.setUse_dept(zhibiao_dept_id);
			//	zhibiao.setStop_time(stop_time);
				Service.insert(zhibiao);
				resJson.setRel("menuref2");
				resJson.setCallbackType("closeCurrent");
				resJson.setMessage("保存成功!");
			}else{
				zhibiao.setI_id(id);
				zhibiao.setCategory(category1);
				zhibiao.setHosnum(a.getHosnum());
				zhibiao.setUse_dept(zhibiao_dept_id);
			//	zhibiao.setStop_time(stop_time);
				//zbk.setPid(id);
				zbk.setHosnum(a.getHosnum());
				Service.update(zhibiao);	
				if(!"1".equals(zhibiao.getIs_using())){
					Service.updateusing(zbk);
				}
				resJson.setCallbackType("closeCurrent");
				resJson.setRel("menuref2");
				resJson.setMessage("保存成功!");
			}
			resJson.refresh("zhibiaoTree2");
			return resJson;
		}
		
		@RequestMapping("/xgzhibiao2")
		public String xgzhibiao2(HttpServletRequest resq, HttpServletResponse resp, String id, String pId,ModelMap model,zhibiaoku zhib,String isdinggao){
			List<zhibiaoku> o = new ArrayList<zhibiaoku>();
			List<BaseDict> BaseDict = null;
			BaseDict dict = new BaseDict();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			List<zhibiaoku> o1 = new ArrayList<zhibiaoku>();
			o = Service.findtwo(id,a.getHosnum());
			dict.setNekey(100);
			dict.setHosnum(a.getHosnum());
			BaseDict=dictService.selectDictByNekey(dict);
			//if(o.get(0).getPid()=="0000"||"0000".equals(o.get(0).getPid())){
			//	o1=Service.findByPid(o.get(0).getPid());
			//}else{
			o1=Service.findtwo(o.get(0).getPid(),a.getHosnum());
			model.put("isdinggao",isdinggao);
			//}
			if(o1.size()<1){
				for (BaseDict h : BaseDict) {
					if(h.getNevalue().equals(o.get(0).getPid())){
						model.put("supunit1",h.getContents());
						model.put("zhibiao", o.get(0));
						model.put("item", o.get(0).getItem());
						model.put("score",o.get(0).getScore());
						model.put("districtid",o.get(0).getDistrictid());
						model.put("note",o.get(0).getNote());
						model.put("category", o.get(0).getCategory());
						model.put("zhibiao_dept_id", o.get(0).getUse_dept());
					}
				}
			}else{
				model.put("supunit1", o1.get(0).getItem());
				model.put("zhibiao", o.get(0));
				model.put("item", o.get(0).getItem());
				model.put("score",o.get(0).getScore());
				model.put("districtid",o.get(0).getDistrictid());
				model.put("note",o.get(0).getNote());
				model.put("category", o.get(0).getCategory());
				model.put("zhibiao_dept_id", o.get(0).getUse_dept());
			}
			
			/*if(o.get(0).getHosnum()!="0000"&&!"0000".equals(o.get(0).getHosnum())){
				return "/zhibiao/addzhibiaoku2";
			}*/
			return "/ehr/zhibiao/zhibiaotree/threeaddzhibiaoku";
		}
		
		
		@RequestMapping(value="delzhibiao2")
		@ResponseBody
		public DwzResJson delzhibiao2(String ids,HttpServletRequest req,HttpServletResponse resp){
			DwzResJson resJson = new DwzResJson();
				try {
					String[] chk =ids.split(",");
					List<String> idslist = new ArrayList<String>();
					for(int i = 0;i<chk.length;i++){
						idslist.add(chk[i]);
					}
					int res=Service.deletelist(idslist);
					if(res==1){
						resJson.setRel("menuref2");
						resJson.setMessage("删除成功!");
					}
					else      resJson.error("请先删除所有其子组织或角色");
				} catch (Exception e) {
					resJson.error();
				}
			return resJson;
			}
		
		
		@RequestMapping(value="addgrouptree")
		public String  addgrouptree(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String ids){
			model.put("ids",ids.substring(0, ids.length()-1));
			return "/ehr/zhibiao/addzhibiaokuGroupTree";
		}
		@RequestMapping(value="saveGroupTree",method=RequestMethod.POST)
		@ResponseBody
		public DwzResJson saveGroupTree(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String ids,String qzDept){
			DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res1=Service.updateGroupTree(idslist, qzDept);
				if(res1==1){
					resJson.setMessage("保存成功");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
				}else{
					resJson.setMessage("保存失败");
					resJson.setCallbackType("closeCurrent");
					resJson.setRel("menuref1");
				}
			}catch(Exception e){
				resJson.setMessage("出错了。。。");
				resJson.setCallbackType("closeCurrent");
				resJson.setRel("menuref1");
			}
			return resJson;
		}

		@RequestMapping(value="shouhui")
		@ResponseBody
		public DwzResJson shouhui(HttpServletRequest resq, HttpServletResponse resp,String id){
			DwzResJson resJson = new DwzResJson();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			try {
				zhibiaoku zbk = new zhibiaoku();
				
				String allids = Service.getpid(id);
				zbk.setPid(allids);
				Service.shouhui(zbk);
				resJson.setMessage("收回成功!");
			} catch (Exception e) {
				resJson.error();
			}
			return resJson;
		}
		@RequestMapping(value="xiugaizf")
		@ResponseBody
		public DwzResJson xiugaizf(HttpServletRequest resq, HttpServletResponse resp,String id,String zjfs,String pid){
			DwzResJson resJson = new DwzResJson();
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			try {
				finlScore fin = new finlScore();
				fin.setId(id);
				fin.setFinlscore(zjfs);
				fin.setXulieid(pid);
				fin.setHosnum(a.getHosnum());
				finlScore fin1 = new finlScore();
				fin1 =  finlService.findById(fin);
				if(fin1!=null){
					finlService.update(fin);
				}else{
					finlService.insert(fin);
				}
				resJson.setMessage("修改成功!");
			} catch (Exception e) {
				resJson.error();
			}
			return resJson;
		}
		
		@RequestMapping(value="jiucuo23",method=RequestMethod.GET)
		@ResponseBody
		public void jiucuo23(HttpServletRequest resq,HttpServletResponse response, String id, String pId,ModelMap model,zhibiaoku zhib){
			PrintWriter pw = null;
			zhibiaoku ku = new zhibiaoku();
			zhibiaoku ku1 = new zhibiaoku();
			zhibiaoku ku2 = new zhibiaoku();
			List<zhibiaoku> list = new ArrayList<zhibiaoku>();
			List<zhibiaoku> list1 = new ArrayList<zhibiaoku>();
			String i = resq.getParameter("dinggao2");
			try {
				pw=response.getWriter();
				BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
				ku.setPid(i);
				ku.setHosnum(a.getHosnum());
				list=Service.findjydinggao(ku);
				int kk = 0;
				for(int k=0;k<list.size();k++){
					if(!"1".equals(list.get(k).getLast())){
						kk++;
						ku1.setI_id(list.get(k).getI_id());
						ku1.setHosnum(list.get(k).getHosnum());
						Service.updatejiucuo(ku1);
					}
				}
				list1=Service.findjydinggao1(ku);
				for(int k=0;k<list1.size();k++){
					if(!"0".equals(list1.get(k).getLast())){
						kk++;
						ku2.setI_id(list1.get(k).getI_id());
						ku2.setHosnum(list1.get(k).getHosnum());
						Service.updatejiucuo1(ku2);
					}
				}
				pw.print(kk);
			} catch (IOException e) {
				e.getStackTrace();
			}finally{
				pw.flush();
				pw.close();
			}
		}

		@RequestMapping(value="shangchuanzbk",method=RequestMethod.GET)
		@ResponseBody
		public void shangchuanzbk(HttpServletRequest resq,HttpServletResponse response,String supunit){
			BasUser a = (BasUser) resq.getSession().getAttribute(Const.SESSION_USER);
			PrintWriter pw = null;
			zhibiaoku ku = new zhibiaoku();
			String i = resq.getParameter("supunit");
			Modelm m = new Modelm();
			try {
				pw=response.getWriter();
				ku.setPid(supunit);
				ku.setState("Y");
				ku.setHosnum(a.getHosnum());
				m.setId(supunit);
				Service.updatesc(ku);
			}catch(Exception e){
				pw.flush();
				pw.close();
			}
		}
		
		
		
		@RequestMapping("avgindex")
		public String avgindex(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,Zbkavgscore o,String itemid,String relFlag,String name,String yryear,String itempid){
			Page<Zbkavgscore> p = new Page<Zbkavgscore>();
			BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			o.setItemid(itemid);
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
			
			p=ZbkavgService.findByPage(o, p);
				if(relFlag !=null && !"".equals(relFlag)){
					model.put("avgscorelist", p.getResults());
					return "/ehr/zhibiao/zhibiaoavg";
				}
			return "/ehr/zhibiao/zhibiaoavglist";
		}
		
		 @RequestMapping(value="getzbkPersonal", method=RequestMethod.POST)
		    @ResponseBody
		    public void  getzbkPersonal(HttpServletRequest request,HttpServletResponse response,String pid,String now_major_mc_main_model,ModelMap model){
		        HttpSession session = request.getSession();
		        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		        String hosnum = "";
		        hosnum = a.getHosnum();
		        try {
		        	String pids = Service.getpids(pid);
		        	String[] chk =pids.split(",");
		            List<ZNodes> r=modelService.getzbkPersonal(hosnum,chk[chk.length-1],pid);
		            ZNodes zn = new ZNodes();
		            zn.setId("0");
		            zn.setpId("");
		            zn.setName("全部");
		            r.add(zn);
		            JSONArray jsons = JSONArray.fromObject(r);
		            response.getWriter().print(jsons.toString());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		 
		 @RequestMapping("/matchForm")
		    public String matchForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		        Modelm m = new Modelm();
		        HttpSession session = request.getSession();
		        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		        String hosnum = a.getHosnum();
		        m.setHosnum(hosnum);
		        return "ehr/zhibiao/zhibiaoavgForm";
		    }
		 
		 @RequestMapping("/match")
		    public void match(ModelMap model, HttpServletRequest request, HttpServletResponse response,String itemid,String nameid) throws Exception {
		        PrintWriter pw = null;
		        HttpSession session = request.getSession();
		        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		        DecimalFormat df = new DecimalFormat("#0.00");
		        try {
		            pw = response.getWriter();
		            String hosnum =a.getHosnum();
		            if(Tools.isEmpty(nameid)){
		            	new EhException().error("未选择人员！");
		            }
		            String[] chk =nameid.split(",");
		            zbkscore zbks = null;
		            double m = 0;
		            for(int j = 0;j<chk.length;j++){
		            	zbks = new zbkscore();
		            	zbks.setId(chk[j]);
		            	zbks.setItemid(itemid);
		            	zbks = zbkglService.findsbyitemids(zbks);
		            	if(zbks!=null){
		            		m+=Double.parseDouble(zbks.getScorep());
		            	}
		            }
		            Zbkavgscore score= new Zbkavgscore();
		            String itemscore="";
		            String avgscore = "";

		            score.setItemscore(String.valueOf(m));
		            avgscore=String.valueOf(df.format(m/chk.length));
		            score.setId(get32UUID());
		            score.setItemid(itemid);
		            score.setHosnum(hosnum);
		            score.setNameid(nameid);
		            score.setNumber(chk.length+"");
		            score.setAvgscore(avgscore);
		            String pids = Service.getpids(itemid);
		        	String[] chk1 =pids.split(",");
		        	Modelm mod = new Modelm();
		        	Modelm mod1 = new Modelm();
		        	mod.setId(chk1[chk1.length-1]);
		        	mod1=modelService.findById(mod);
		            score.setZbkname(mod1.getName());
		            score.setZbkid(mod1.getId());
		            ZbkavgService.insert(score);
		            pw.write(EhUtil.createcallbackjson("200", "保存成功","modelavgForm", "","closeCurrent", "","","backstage/zhibiao/matchForm"));
		        }catch (EhException error){
					logger.error(error.toString(),error);
					pw.print(EhUtil.createcallbackjson("300", error.getMessage().toString(), "", "", "", "", "", ""));
				} catch (Exception e) {
		        	e.printStackTrace();
		            pw.write(EhUtil.createcallbackjson("300", "操作失败","", "","", "","",""));
		        }

		    }
		 @RequestMapping(value="delzhibiaopj")
			@ResponseBody
			public DwzResJson delzhibiaopj(String ids,HttpServletRequest req,HttpServletResponse resp){
				DwzResJson resJson = new DwzResJson();
					try {
						String[] chk =ids.split(",");
						List<String> idslist = new ArrayList<String>();
						for(int i = 0;i<chk.length;i++){
							idslist.add(chk[i]);
						}
						int res=ZbkavgService.deletelist(idslist);
						if(res==1){
							resJson.setRel("avgscore");
							resJson.setMessage("删除成功!");
						}
						else {
							resJson.error("删除失败");
						}
					} catch (Exception e) {
						resJson.error();
					}
				return resJson;
				}
		 @RequestMapping(value="fuzhi")
			public String  fuzhi(HttpServletRequest res,HttpServletResponse rep,ModelMap model, String id){
			 	Zbkavgscore score= new Zbkavgscore();
			 	score.setId(id);
			 	score = ZbkavgService.findById(score);
				model.put("id",id);
				model.put("modelid",score.getZbkid());
				model.put("score",score);
				return "/ehr/zhibiao/zbkpjffz";
			}
		 
			@RequestMapping(value="savepjf",method=RequestMethod.POST)
			@ResponseBody
			public void savepjf(HttpServletRequest res,HttpServletResponse rep,ModelMap model,String fzrenyuan,String itemid,String modelid,String score){
				 PrintWriter pw = null;
				 BasUser a = (BasUser)res.getSession().getAttribute(Const.SESSION_USER);
				try {
					 pw = rep.getWriter();
					if(Tools.isEmpty(fzrenyuan)){
						new EhException().error("未选择人员！");
					}
					finlScore fin = new finlScore();
					fin.setXulieid(modelid);
					fin.setId(fzrenyuan);
					fin.setHosnum(a.getHosnum());
					fin = finlService.findById(fin);
					if(fin==null){
						new EhException().error("该人员未算总分！");
					}else{
						zbkscore zbk = new zbkscore();
						zbk.setItemid(itemid);
						zbk.setName(modelid);
						zbk.setId(fzrenyuan);
						zbk.setScorep(score);
						zbkglService.updatess(zbk);
						zbkscore zbk1 = new zbkscore();
						zbk1 = zbkglService.findsbyname(zbk);
						fin.setFinlscore(zbk1.getScorep());
						finlService.update(fin);
					}
					 pw.write(EhUtil.createcallbackjson("200", "保存成功","avgscore", "","closeCurrent", "","",""));
				}catch (EhException error){
					logger.error(error.toString(),error);
					pw.print(EhUtil.createcallbackjson("300", error.getMessage().toString(), "", "", "", "", "", ""));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
}
