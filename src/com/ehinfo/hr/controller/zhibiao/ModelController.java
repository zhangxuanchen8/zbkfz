package com.ehinfo.hr.controller.zhibiao;


import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.system.tool.EhUtil;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.system.org.OrgServiceImp;
import com.ehinfo.hr.service.system.user.BasUserService;
import com.ehinfo.hr.service.zhibiao.ModelService;
import com.ehinfo.hr.service.zhibiao.zhibiao_sbService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LHB
 * @date 2018/10/11 10:15
 */
@Controller
@RequestMapping("/backstage/model/")
public class ModelController {
    @Autowired
    private OrgServiceImp deptService;
    @Autowired
    private BasUserService userService;
    @Autowired
    private ModelService modelService;
    @Autowired
    public zhibiao_sbService zbkService;

    @RequestMapping("/modelIndex")
    public String index(ModelMap model, HttpServletRequest request, String dept_baseUser, String pageNum, String numPerPage, String modelName){
        Page<Modelm> page = new Page<Modelm>();
        Modelm m = new Modelm();
        //--------------------------------------加医院限制条件-------------------------------------
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        m.setHosnum(a.getHosnum());
//			String hosnum = a.getID();
        /*if("300000".equals(a.getID()) || "0000".equals(a.getID())){
            user.setHosnum("330000");
            user.setId("330000");
        }else{
            user.setId(a.getID());
            user.setHosnum(a.getID());
        }*/
        //--1、分页查询

        if(modelName!=null||!"".equals(modelName)){
            m.setName(modelName);
        }
        if(pageNum==null || "".equals(pageNum)){
            page.setPageNum(1);
        }else{
            page.setPageNum(Integer.parseInt(pageNum));
        }
        if(numPerPage==null || "".equals(numPerPage)){
            page.setPageSize(30);
        }else{
            page.setPageSize(Integer.parseInt(numPerPage));
        }

            page = modelService.findByPage(m, page);

        //--2、+查询条件
        model.put("page", page);
        model.put("modelName", modelName==null?"":modelName);
        //model.put("thedept_baseUser", thedept_baseUser==null?"":thedept_baseUser);
        //model.put("thedeptname_baseUser", thedeptname_baseUser==null?"":thedeptname_baseUser);
        //model.put("dept_baseUser", dept_baseUser==null?"":dept_baseUser);
        return "ehr/zhibiao/table/modelList";
    }

    @RequestMapping("/baseUserIndexN")
    public String indexN(ModelMap model, HttpServletRequest request, String dept_baseUser, String pageNum,
                         String numPerPage, String modelName) {
        Page<Modelm> page = new Page<Modelm>();
        Modelm m = new Modelm();
        // --------------------------------------加医院限制条件-------------------------------------
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = a.getHosnum();
        m.setHosnum(hosnum);
        /*if("300000".equals(a.getID()) || "0000".equals(a.getID())){
            user.setId("330000");
        }else{
            user.setId(a.getID());
        }*/
        // --1、分页查询
        m.setName(modelName);
        if (pageNum == null || "".equals(pageNum)) {
            page.setPageNum(1);
        } else {
            page.setPageNum(Integer.parseInt(pageNum));
        }
        if (numPerPage == null || "".equals(numPerPage)) {
            page.setPageSize(30);
        } else {
            page.setPageSize(Integer.parseInt(numPerPage));
        }
            page = modelService.findByPage(m, page);

        // --2、+查询条件
            
        model.put("page", page);
        model.put("modelName", modelName == null ? "" : modelName);
        return "ehr/zhibiao/table/modelTable";
    }


    @RequestMapping("/addForm")
    public String addForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String modelId = request.getParameter("modelID");
        Modelm m = new Modelm();
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = a.getHosnum();
        m.setHosnum(hosnum);
        if (modelId != null && !"".equals(modelId)) {
            m.setId(modelId);
            m = modelService.findById(m);
            model.put("m",m);
        }

        return "ehr/zhibiao/table/modelAddForm";
    }

    @RequestMapping("/matchForm")
    public String matchForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String modelId = request.getParameter("modelID");
        Modelm m = new Modelm();
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = a.getHosnum();
        m.setHosnum(hosnum);
        /*if (modelId != null && !"".equals(modelId)) {
            m.setId(modelId);
            m = modelService.findById(m);
            model.put("m",m);
        }
*/
        return "ehr/zhibiao/table/modelMatchForm";
    }

    @RequestMapping("/match")
    public void match(ModelMap model, HttpServletRequest request, HttpServletResponse response,String match_m,String match_p) throws Exception {
        PrintWriter pw = null;
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        try {
            pw = response.getWriter();
            String hosnum =a.getHosnum();
            modelService.match(match_m,match_p,hosnum);
            pw.write(EhUtil.createcallbackjson("200", "保存成功","modelMatchForm", "","", "","","backstage/model/matchForm"));
        } catch (Exception e) {
        	e.printStackTrace();
            pw.write(EhUtil.createcallbackjson("300", "操作失败","", "","", "","",""));
        }

    }

    @RequestMapping("/addModel")
    public void addModel(
            Modelm m,
            HttpServletResponse response,HttpServletRequest request){
        DwzResJson ar=new DwzResJson();
        try {
            HttpSession session = request.getSession();
            BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
            String hosnum =a.getHosnum();
            m.setHosnum(hosnum);
            //--------------去重复-------------
            /*List<Modelm> list = modelService.find(m);
            if(list.size()>0){
                ar.error("用户名重复！");
            }else{*/
                String msg = modelService.insert1(m);
                if(msg=="0"){
                    ar.error("用户名重复！");
                }else{
                    ar.setRel("modelAddForm");
                    ar.setCallbackType("closeCurrent");
                    ar.success(Const.SAVE_SUCCEED);
                }
            //}
            response.getWriter().print(ar.toJson());
        } catch (Exception e) {
            //logger.error(e.toString(),e);
            ar.error(Const.SAVE_FAIL);
            try {
                response.getWriter().print(ar.toJson());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



    @RequestMapping("changePsd")
    public String changePsd(Model model, HttpServletRequest request){
        return "system/changepwd";
    }

    @RequestMapping(value="delBatch", method=RequestMethod.POST)
    @ResponseBody
    public DwzResJson delBatch(String ids){
        DwzResJson ar=new DwzResJson();
        try {
            modelService.deleteBatchBasUser(ids);
            ar.setRel("modelAddForm");
            ar.success(Const.DEL_SUCCEED);
        } catch (Exception e) {
            //logger.error(e.toString(),e);
            ar.error(Const.DEL_FAIL);
        }
        return ar;
    }


    @RequestMapping(value="preResetPWD", method=RequestMethod.POST)
    @ResponseBody
    public DwzResJson resetPWD(String pwschange_oldpassword,String pwschange_newpassword,String pwschange_secpassword,HttpServletRequest request){
        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        DwzResJson dwz = new DwzResJson();
        try {
            int res=userService.preResetPwd(pwschange_oldpassword,pwschange_newpassword,pwschange_secpassword,a);
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
            //logger.error(e.toString(),e);
            dwz.setStatusCode("300");
            dwz.setMessage("密码修改失败！");
            return dwz;
        }
        return dwz;
    }

    @RequestMapping(value="getModelTree", method=RequestMethod.POST)
    @ResponseBody
    public void  getModelTree(HttpServletRequest request,HttpServletResponse response){


        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = "";
		hosnum = a.getHosnum();
		String nodecode = a.getNodecode();
        /*if("300000".equals(a.getID()) || "0000".equals(a.getID())){
            hosnum = "330000";
        }else{
            hosnum = a.getID();
        }*/
        try {
            List<ZNodes> r=modelService.getModelTree(hosnum,nodecode);
            JSONArray jsons = JSONArray.fromObject(r);
            response.getWriter().print(jsons.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.toString(),e);

        }
    }

    @RequestMapping(value="getModelTree_withzbk", method=RequestMethod.POST)
    @ResponseBody
    public void  getModelTree_withzbk(HttpServletRequest request,HttpServletResponse response,String supunit){


        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = "";
		hosnum = a.getHosnum();
		String nodecode = a.getNodecode();
        
        try {
        	String allids = zbkService.getpid(supunit);
        	List<ZNodes> r=modelService.getModelTree_withzbk(hosnum,supunit,allids);
          //  List<ZNodes> r=modelService.getModelTree_withzbk(hosnum,supunit);
            JSONArray jsons = JSONArray.fromObject(r);
            response.getWriter().print(jsons.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.toString(),e);

        }
    }
    @RequestMapping(value="getModelTree_withzbkdept", method=RequestMethod.POST)
    @ResponseBody
    public void  getModelTree_withzbkdept(HttpServletRequest request,HttpServletResponse response){


        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = "";
		hosnum = a.getHosnum();
		String nodecode = a.getNodecode();
        try {
            List<ZNodes> r=modelService.getModelTree_withzbkdept(hosnum,nodecode);
            zhibiaoku zbk = null;
            List<ZNodes> m = new ArrayList<ZNodes>();
          
            for(ZNodes h:r){
            	if(!"zxc".equals(h.getId())){
            		zbk  = new zhibiaoku();
                	zbk.setPid(h.getId());
                	zbk.setHosnum(hosnum);
                	String allids = zbkService.getpid(h.getId());
                	zbk.setTreename(allids);
                	List<ZNodes> n  = zbkService.finduserdept(zbk);
                	for(ZNodes l:n){
                		if(Tools.notEmpty(l.getId())){
                			m.add(l);
                		}
                	}
            	}
            }
            r.addAll(m);
            JSONArray jsons = JSONArray.fromObject(r);
            response.getWriter().print(jsons.toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @RequestMapping(value="getModelTree_withPersonalRecord", method=RequestMethod.POST)
    @ResponseBody
    public void  getModelTree_withPersonalRecord(HttpServletRequest request,HttpServletResponse response,String type){


        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = "";
		hosnum =a.getHosnum();
		String nodecode = a.getNodecode();
        try {
        	List<ZNodes> r = null;
        	String remarks = "";
        	if(Tools.notEmpty(type)){
        		 r=modelService.getModelTree_withPersonalRecord(hosnum,nodecode);
        	}else{
        		zhibiaoku zbk = new zhibiaoku();
        		zbk.setUse_dept(a.getPerson_dept());
        		List<zhibiaoku> list_zbk = zbkService.findlistbydept(zbk);
        		String remark ="";
        		for(zhibiaoku h:list_zbk){
        			zbk = new zhibiaoku();
        			zbk.setI_id(h.getI_id());
        			String allpids = zbkService.getpids(h.getI_id());
        			zbk.setI_id(allpids);
        			List<zhibiaoku> list_z = zbkService.findpidbydept(zbk);
        			remark+=list_z.get(0).getPid()+",";
        		}
        		if(Tools.notEmpty(remark)){
        			String m[] = remark.split(",");
        			List<String> list = new ArrayList<String>();
        			 for (int i=0; i<m.length; i++) {    
        			        if(!list.contains(m[i])) {    
        			            list.add(m[i]);    
        			        }    
        			    }
        			 for(int i=0;i<list.size();i++){
        				 remarks+=list.get(i)+",";
        			 }
        			 
        		}
        		r=modelService.getModelTree_withPersonalRecords(remarks,nodecode);
        	}
            JSONArray jsons = JSONArray.fromObject(r);
            response.getWriter().print(jsons.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.toString(),e);

        }
    }

    @RequestMapping(value="getPersonalRecordTree", method=RequestMethod.POST)
    @ResponseBody
    public void  getPersonalRecordTree(HttpServletRequest request,HttpServletResponse response,String xcszy,String now_major_mc_main_model,ModelMap model){


        HttpSession session = request.getSession();
        BasUser a = (BasUser) session.getAttribute(Const.SESSION_USER);
        String hosnum = "";
        hosnum = a.getHosnum();
        String nodecode = a.getNodecode();
        model.put("xcszy_model",xcszy);
        model.put("now_major_mc_main_model",now_major_mc_main_model);
        /*if("300000".equals(a.getID()) || "0000".equals(a.getID())){
            hosnum = "330000";
        }else{
            hosnum = a.getID();
        }*/
        try {
            List<ZNodes> r=modelService.getPersonalRecordTree(hosnum,nodecode,xcszy);
            JSONArray jsons = JSONArray.fromObject(r);
            response.getWriter().print(jsons.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.toString(),e);

        }
    }
    
	@RequestMapping(value="guidang")
	@ResponseBody
	public DwzResJson guidang(HttpServletRequest resq, HttpServletResponse resp,String ids){
		DwzResJson resJson = new DwzResJson();
		try {
			String[] chk =ids.split(",");
			Modelm m = new Modelm();
			modelService.modelguidang(ids);
			resJson.setMessage("归档成功!");
		} catch (Exception e) {
			e.printStackTrace();
			resJson.error();
		}
	return resJson;
	}
}
