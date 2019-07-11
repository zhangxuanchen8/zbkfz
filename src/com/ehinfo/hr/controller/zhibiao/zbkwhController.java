package com.ehinfo.hr.controller.zhibiao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehinfo.hr.common.ajax.DwzResJson;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.service.zhibiao.zbkwhService;

@Controller
@RequestMapping("/backstage/zbkwh/")
public class zbkwhController extends BaseController<zbkwh>{
	@Autowired
	public zbkwhService Service;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap model,String pageNum,String numPerPage,String category){
		Page<zbkwh> p = new Page<zbkwh>();
		if(Tools.notEmpty(pageNum)){
			p.setPageNum(Integer.parseInt(pageNum));
		}else{
			p.setPageNum(1);
		}
		if(Tools.notEmpty(numPerPage)){
			p.setPageSize(Integer.parseInt(numPerPage));
		}else{
			p.setPageSize(20);
		}
		zbkwh zbk = new zbkwh();
		zbk.setCategory(category);
		p=Service.findByPage(zbk, p);
		model.put("zhibiao_wh_list", p.getResults());
		model.put("category",category);
		model.put("numPerPage",p.getPageSize());
		model.put("pageNum", p.getPageNum());
		model.put("totalcount", p.getTotalRecord());
		return "/ehr/zhibiao/zbkwh/zbkwhtable";
	}
	
	@RequestMapping("form")
	public String form(HttpServletRequest request,ModelMap model,String id){
		if(id!=null&&!id.equals("")){
			zbkwh zbkwh=new zbkwh();
			zbkwh.setId(id);
			zbkwh=Service.findById(zbkwh);
			model.put("zbkwh", zbkwh);
		}
		return "/ehr/zhibiao/zbkwh/zbkwhform";
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public DwzResJson save(HttpServletRequest request,String id,String zbkmc,String score,String category,String yjfl,String ejfl,String item_desc){
		DwzResJson resJson = new DwzResJson();
		zbkwh zbkwh = new zbkwh();
		zbkwh.setZbkmc(zbkmc);
		zbkwh.setCategory(category);
		zbkwh.setYjfl(yjfl);
		zbkwh.setEjfl(ejfl);
		zbkwh.setScore(score);
		zbkwh.setItem_desc(item_desc);
		BasUser a = (BasUser) request.getSession().getAttribute(Const.SESSION_USER);
		zbkwh.setHosnum(a.getHosnum());
		try{
			if(id==null||id.equals("")){
				zbkwh.setId(get32UUID());
				Service.insert(zbkwh);
			}else {
				zbkwh.setId(id);
				Service.update(zbkwh);
			}
			resJson.closeCurrentAndRefresh("zhibiaotable");
			resJson.setMessage("保存成功!");
		} catch (Exception e) {
			resJson.closeCurrentAndRefresh("zhibiaotable");
			resJson.setMessage("保存失败");
		}
		return resJson;
	}
	@RequestMapping(value="del")
	@ResponseBody
	public DwzResJson del(String ids,HttpServletRequest req,HttpServletResponse resp){
		DwzResJson resJson = new DwzResJson();
			try {
				String[] chk =ids.split(",");
				List<String> idslist = new ArrayList<String>();
				for(int i = 0;i<chk.length;i++){
					idslist.add(chk[i]);
				}
				int res=Service.deletelist(idslist);
				if(res==1){
					resJson.success(Const.DEL_SUCCEED);
				}
			} catch (Exception e) {
				resJson.error(Const.DEL_FAIL);
			}
		return resJson;
		}
}
