package com.ehinfo.hr.controller.system.dict;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import com.ehinfo.hr.entity.system.user.BasUser;
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
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.WordUtil;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.EhException;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.dict.Colum;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.service.system.dict.BaseDictService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 数据字典
 */
@Controller
@RequestMapping("/backstage/baseDict/")
public class BaseDictController extends BaseController<BaseDict> {
	@Autowired
	public BaseDictService service;

	@RequestMapping(value="getColumneName",method=RequestMethod.POST)
	@ResponseBody
	public void getColumneName(HttpServletResponse hsResponse,HttpServletRequest hRequest){
		AjaxRes ar=getAjaxRes();	
		 
		
		
		HttpSession session = hRequest.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String nekey = "";
		BaseDict bd;
		List<String> headlist = new ArrayList<>();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				nekey = hRequest.getParameter("nekey");
				List<BaseDict> blist = service.getColumneName( nekey);
				bd = blist.get(0);
				if (blist != null && blist.size() > 0) {
					Field[] field = bd.getClass().getDeclaredFields();
					for (Field field2 : field) {
						String fieldName = field2.getName();
						Object ob = org.apache.commons.beanutils.BeanUtils.getProperty(bd, fieldName);
						if (ob != null) {
							// 如果类型是Integer
							if (field2.getGenericType().toString().equals("class java.lang.Integer")) {
								Method m = (Method) bd.getClass().getMethod("get" + getMethodName(fieldName));
								Integer val = (Integer) m.invoke(bd);
								headlist.add(val.toString());
							}
							// 如果类型是String
							if (field2.getGenericType().toString().equals("class java.lang.String")) {
								Method m = (Method) bd.getClass().getMethod("get" + getMethodName(fieldName));
								String val = (String) m.invoke(bd);
								headlist.add(val);
							}
						}
					}
				}
				PrintWriter pw = hsResponse.getWriter();
				pw.print(JSONArray.fromObject(bd).toString());
				pw.flush();
		        pw.close();
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
	}
	@RequestMapping(value="getdDictDetails")
	public String getdDictDetails(ModelMap model, String nekey, String contents, String pageNum, String numPerPage, HttpServletRequest resquest){

		String nodeid = resquest.getParameter("nodeid");
		if(nodeid!=null&&!"".equals(nodeid)){
			nekey = nodeid;
		}
		HttpSession session = resquest.getSession();
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		BaseDict bd;
		Colum column;
		List<Colum> bdlist;
			try {
				if(nekey==null || nekey ==""){
					nekey = service.getdefaultNekey();
				}
				if(pageNum==null || pageNum ==""){
					pageNum = "1";
				}
				if(numPerPage==null || numPerPage ==""){
					numPerPage = "20";
				}
				List<BaseDict> blist = service.getColumneName(nekey);
				bd = blist.get(0);
				bdlist = new ArrayList<Colum>();
				if (blist != null && blist.size() > 0) {
					Field[] field = bd.getClass().getDeclaredFields();
					for (Field field2 : field) {
						String fieldName = field2.getName();
						Object ob = org.apache.commons.beanutils.BeanUtils.getProperty(bd, fieldName);
						if (ob != null) {
							if(fieldName.indexOf("option")>-1){
								column = new Colum();
								column.setProperty(fieldName);
								// 如果类型是Integer
								if (field2.getGenericType().toString().equals("class java.lang.Integer")) {
									Method m = (Method) bd.getClass().getMethod("get" + getMethodName(fieldName));
									Integer val = (Integer) m.invoke(bd);
									if (val!=null) {
										column.setPropertyvalue(val.toString());
									}
								}
								// 如果类型是String
								if (field2.getGenericType().toString().equals("class java.lang.String")) {
									Method m = (Method) bd.getClass().getMethod("get" + getMethodName(fieldName));
									String val = (String) m.invoke(bd);
									column.setPropertyvalue(val);
								}
								bdlist.add(column);
							}
						}
					}
				}
				if (bdlist!=null) {
					Collections.sort(bdlist);
					model.put("bdoptionlist", bdlist);
				}
				int count = service.getdDictDetailsCount(nekey, contents , hosnum);
				List<BaseDict> result=service.getdDictDetails(nekey, contents, hosnum, (Integer.parseInt(pageNum)-1)*Integer.parseInt(numPerPage)+1, (Integer.parseInt(pageNum))*Integer.parseInt(numPerPage));
				model.put("pageNum", pageNum);
				model.put("totalcount",count);
				model.put("numPerPage", numPerPage);
				model.put("list",result);
				model.put("columName", bd);
				model.put("contents", contents);
				model.put("hosnum",hosnum);
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
			
			String relFlag = resquest.getParameter("relFlag");
			if(relFlag!=null&&!"".equals(relFlag)){
				return "/system/dict/data/dictTable";
			}
			return "/system/dict/data/dictlist";
	}
	@RequestMapping(value="deleteByDictId")
	@ResponseBody
	public void deleteByDictId(HttpServletRequest request,HttpServletResponse response){
		String dictid = request.getParameter("dictid");
		PrintWriter pw =null;
		try {
			int result = service.deleteByDictId(dictid);
			pw = response.getWriter();
			if (result>0) {
				pw.print(EhUtil.createcallbackjson("200","删除成功!","","","","","",""));
			}else{
				pw.print(EhUtil.createcallbackjson("200","删除失败!","","","","","",""));
			}
		} catch (Exception e) {
			pw.print(EhUtil.createcallbackjson("500","删除失败!"+e.getMessage(),"","","","","",""));
			logger.error(e.toString(),e);
		}
		pw.flush();
        pw.close();
	}
	
	@RequestMapping(value="saveOrUpdate")
	@ResponseBody
	public void saveOrUpdate(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String dlist = request.getParameter("dlist");
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		String sysname = request.getParameter("sysname");
		String nekey = request.getParameter("nekey");
		JSONArray dlists; 
		BaseDict bd;
		PrintWriter pw =null;
		try {
			pw = response.getWriter();
			dlists = JSONArray.fromObject(dlist);
			Iterator<Object> it = dlists.iterator();
			while(it.hasNext()){
				bd = new BaseDict();
				JSONObject ob = (JSONObject) it.next();
				bd = (BaseDict)JSONObject.toBean(ob,BaseDict.class);
				bd.setHosnum(hosnum);
				bd.setSysname(sysname);
				bd.setNekey(Integer.parseInt(nekey));
				bd.setInputcpy(WordUtil.trans2PyCode(bd.getContents()));
				bd.setInputcwb(WordUtil.trans2WbCode(bd.getContents()));
				String obdictid = ob.getString("dictid");
				if(ob.getString("dictid")!=null&&!("").equals(ob.getString("dictid"))){
					 service.updateByDictId(bd);
				}else {
					 bd.setDictid(UuidUtil.get32UUID());
					 service.add(bd); 
				}
				
			}
			/*if("65".equals(nekey)){
				xinzi.deletexinzi();
				xinzi.insertxinzi();
			}*/
			pw.print(EhUtil.createcallbackjson("200","保存成功!","","dictListRef","","","",""));
		} catch (Exception e) {
			logger.error(e.toString(),e);
			pw.print(EhUtil.createcallbackjson("500","保存失败!"+e.getMessage(),"","","","","",""));
		}
	}
	
	@RequestMapping(value="checkNevalue")
	@ResponseBody
	public void checkNevalue(HttpServletRequest request,HttpServletResponse response){
		String nevalue = request.getParameter("nevalue");
		String nekey = request.getParameter("nekey");
		String dictid = request.getParameter("dictid");
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		String hosnum = a.getHosnum();
		PrintWriter pw =null;
		try {
			pw = response.getWriter();
			BaseDict baseDict = service.checkNevalue(nevalue, nekey,dictid,hosnum);
			if (baseDict!=null) {
				pw.print(EhUtil.createcallbackjson("200","当前项目序号已存在!","","","","","",""));
			}			
		} catch (Exception e) {
			pw.print(EhUtil.createcallbackjson("500","服务器内部错误!"+e.getMessage(),"","","","","",""));
			logger.error(e.toString(),e);
		}
		pw.flush();
        pw.close();
	}
	
	private static String getMethodName(String fildeName) throws Exception{
		  byte[] items = fildeName.getBytes();
		  items[0] = (byte) ((char) items[0] - 'a' + 'A');
		  return new String(items);
	}
	@RequestMapping(value="selectDictByNekey")
	@ResponseBody
	public void selectDictByNekey(HttpServletRequest request,HttpServletResponse response){
		String nekey = request.getParameter("nekey");
		PrintWriter pw =null;
		try {
			pw= response.getWriter();
			
			BaseDict dict = new BaseDict();
			dict.setNekey(Integer.parseInt(nekey));
			BasUser user =(BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			dict.setHosnum(user.getHosnum());
			List<BaseDict> dicts = service.selectDictByNekey(dict);
			pw.print(JSONArray.fromObject(dicts).toString());
			pw.flush();
	        pw.close();
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}
	
	@RequestMapping(value="selectDictByContents")
	@ResponseBody
	public void selectDictByNevalue(HttpServletRequest request,HttpServletResponse response){
		String nekey = request.getParameter("nekey");
		String contents = request.getParameter("contents");
		String option = request.getParameter("option");
		PrintWriter pw =null;
		try {
			pw= response.getWriter();
			BaseDict dict = new BaseDict();
			dict.setNekey(Integer.parseInt(nekey));
			dict.setContents(contents);
			BasUser user =(BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			dict.setHosnum(user.getHosnum());
			dict.setOption01(option);
			List<BaseDict> dicts = service.find(dict);
			pw.print(JSONArray.fromObject(dicts).toString());
			pw.flush();
	        pw.close();
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}

	@RequestMapping(value="getDictByNekeyToTree")
	@ResponseBody
	public void getDictByNekeyToTree(HttpServletRequest request,HttpServletResponse response){
		String nekey = request.getParameter("nekey");
		PrintWriter pw ;
		try {
			pw= response.getWriter();
			BaseDict dict = new BaseDict();
			dict.setNekey(Integer.parseInt(nekey));
			BasUser user =(BasUser) request.getSession().getAttribute(Const.SESSION_USER);
			dict.setHosnum(user.getHosnum());
			List<BaseDict> dicts = service.selectDictByNekey(dict);
			String json = JSONArray.fromObject(dicts).toString();
			json = json.replaceAll("nevalue","id");
			json = json.replaceAll("contents","name");
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}

	@RequestMapping("xinjiwh")
	public String qyzddzwh(ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,String option01,Page<BaseDict> p,String numPerPage) {
		BaseDict dict = new BaseDict();
		if(Tools.notEmpty(numPerPage)){
			p.setPageSize(Integer.parseInt(numPerPage));
		}
		dict.setOption01(option01);
		dict.setNekey(66);
		dict.setNevalue("Y");
		p = service.findByPage(dict, p);
		model.put("bict",  p.getResults());
		model.put("option01",  option01);
		model.put("pageNum", p.getPageNum());
		model.put("numPerPage", p.getPageSize());
		model.put("totalcount", p.getTotalRecord());
		return "/system/dict/data/dictTablexinjiwh";
	}
	@RequestMapping("adddictxinji")
	public String adddictqydz(ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,String id){
		BaseDict dict = new BaseDict();
		if(Tools.notEmpty(id)){
			dict.setDictid(id);
			dict = service.findById(dict);
		}
		model.put("dict",  dict);
		return "/system/dict/data/dictTablexjwhForm";
	}
	@RequestMapping(value = "dictxjwhadd", method = RequestMethod.POST)
	@ResponseBody
	public void dictxjwhadd(HttpServletResponse response, HttpServletRequest request,BaseDict dict,HttpSession session) {
		PrintWriter pw = null;
		BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
		List<BaseDict> blist = service.getColumneName("66");
		try {
			pw = response.getWriter();
			BaseDict d = new BaseDict();
			d.setHosnum(a.getHosnum());
			d.setNekey(66);
			d.setContents(dict.getContents());
			d.setDictid(dict.getDictid());
			d.setOption01(dict.getOption01());
			List<BaseDict> list_dict = service.find(d);
			if(list_dict!=null && list_dict.size()>0){
				new EhException().error("该薪级已存在！！！");
			}
			if(Tools.isEmpty(dict.getDictid())){
				dict.setDictid(get32UUID());
				dict.setHosnum(a.getHosnum());
				dict.setSysname(blist.get(0).getSysname());
				dict.setNekey(66);
				dict.setInputcpy(WordUtil.trans2PyCode(dict.getContents()));
				dict.setInputcwb(WordUtil.trans2WbCode(dict.getContents()));
				service.insert(dict);
			}else{
				BaseDict dicts = new BaseDict();
				dicts.setDictid(dict.getDictid());
				dicts = service.findById(dict);
				dicts.setContents(dict.getContents());
				dicts.setOption01(dict.getOption01());
				dicts.setOption02(dict.getOption02());
				dicts.setOption03(dict.getOption03());
				dicts.setNevalue(dict.getNevalue());
				service.updateByDictId(dicts);
		}
		pw.print(EhUtil.createcallbackjson("200", "保存成功", "", "", "closeCurrent", "", "", ""));
		}catch (EhException error) {
			pw.print(EhUtil.createcallbackjson("300", error.getMessage().toString(), "", "", "", "", "", ""));
		}catch (Exception e) {
			try {
				response.getWriter().write(EhUtil.createcallbackjson("300", "保存失败", "", "", "", "", "", ""));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			logger.error(e.toString(), e);
		}
	}
}
