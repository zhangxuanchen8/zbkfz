package com.ehinfo.hr.service.zhibiao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.repository.zhibiao.ModelDao;
import com.ehinfo.hr.repository.zhibiao.zhibiao_sbDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("zhibiao_sbService")
public class zhibiao_sbServiceImp extends BaseServiceImp<zhibiaoku> implements zhibiao_sbService{
	@Autowired
	private zhibiao_sbDao dao;
	@Autowired
	private ModelDao modeldao;
	@Override
	public List<zhibiaoku> findlist(String hosnum) {
		return dao.findlist(hosnum);
	}
	@Override
	public int deletelist(List<String> list) {
		if(dao.haveson(list)>0){
			return 0;
		}
		dao.deletelist(list);
		return 1;
	}
	@Override
	public void updatescore(zhibiaoku o) {
		 dao.updatescore(o);
	}
	@Override
	public void yinru(zhibiaoku o) {
		dao.yinru(o);
	}
	@Override
	public void delcf(zhibiaoku o) {
		dao.delcf(o);
	}
	@Override
	public List<zhibiaoku> findpeoplelist(String hosnum) {
		return dao.findpeoplelist(hosnum);
	}
	@Override
	public List<zhibiaoku> findtwo(String id, String hosnum) {
		return dao.findtwo(id, hosnum);
	}
	@Override
	public List<zhibiaoku> findpid(String id,String hosnum) {
		return dao.findpid(id,hosnum);
	}
	@Override
	public void updateusing(zhibiaoku o) {
			dao.updateusing(o);
	}
	@Override
	public Double findzj(String hosnum,String pid) {
		return dao.findzj(hosnum,pid);
	}
	@Override
	public void updatedinggao(zhibiaoku o) {
		dao.updatedinggao(o);
	}
	@Override
	public List<zhibiaoku> findyearlist1(String hosnum) {
		return dao.findyearlist1(hosnum);
	}
	@Override
	public List<zhibiaoku> findyearlist(String hosnum,String pid) {
		return dao.findyearlist(hosnum,pid);
	}
	@Override
	public Double countmax(String hosnum,String pid) {
		return dao.countmax(hosnum,pid);
	}
	@Override
	public List<zhibiaoku> findbgxl(zhibiaoku zbk ) {
		return dao.findbgxl(zbk);
	}
	@Override
	public void upscorehj(zhibiaoku o) {
		dao.upscorehj(o);
	}
	@Override
	public List<zhibiaoku> findpeoplename(String id, String hosnum) {
		return dao.findpeoplename(id, hosnum);
	}
	@Override
	public List<zhibiaoku> yearscore(zhibiaoku zbk) {
		return dao.yearscore(zbk);
	}
	@Override
	public void deldinggao(zhibiaoku o) {
		dao.deldinggao(o);
		//dao.delzbkzh(o);
	}
	@Override
	public Page<zhibiaoku> findpeoplepage(zhibiaoku o, Page<zhibiaoku> page) {
		page.setResults( dao.findpeoplepage(o, page));
		return page;
	}
	@Override
	public List<ZNodes> getZhiBiaoTree(String id){
		return dao.getZhiBiaoTree(id);
	}
	@Override
	public int findoutyy(zhibiaoku zbk){
		return dao.findoutyy(zbk);
	}
	@Override
	public List<zhibiaoku> findlistscore_yy(zhibiaoku zbk) {
		return dao.findlistscore_yy(zbk);
	}
	@Override
	public List<zhibiaoku> findlistscore_ks(zhibiaoku zbk) {
		return dao.findlistscore_ks(zbk);
	}
	@Override
	public int insertlist(List<zbkwh> list,String pid,String hosnum) {
		int res=0;
		zhibiaoku zbk = null;
		zhibiaoku o = new zhibiaoku();
		o.setI_id(pid);
		o.setHosnum(hosnum);
		o = dao.findById(o);
		String no ="";
		for(zbkwh w:list){
			zhibiaoku z = new zhibiaoku();
			z.setItem(w.getZbkmc());
			z.setHosnum(hosnum);
			z.setCategory(w.getCategory());
			z = dao.findById(z);
			zbk = new zhibiaoku();
			zbk.setItem(w.getZbkmc());
			zbk.setPid(pid);
			zbk.setHosnum(hosnum);
			zbk.setCategory(w.getCategory());
			zbk.setScore(w.getScore());
			if(z!=null){
				no = z.getI_id();
			}else{
				no = dao.getZbkseq();
				zbk.setI_id(no);
				zbk.setIs_using("1");
				zbk.setIs_max("1");
				zbk.setLast("0");
				dao.insert(zbk);
			}
			if(Tools.notEmpty(w.getItem_desc())){
				zbk = new zhibiaoku();
				String no1 = dao.getZbkseq();
				zbk.setItem(w.getItem_desc());
				zbk.setI_id(no1);
				zbk.setHosnum(hosnum);
				zbk.setPid(no);
				zbk.setCategory(w.getCategory());
				zbk.setScore(w.getScore());
				zbk.setIs_using("1");
				zbk.setIs_max("1");
				zbk.setLast("1");
				dao.insert(zbk);
			}
		}
		//dao.insertlist(list,pid,hosnum);
		res=1;
		return res;
	}
	@Override
	public int isdinggao(zhibiaoku zbk){
		return dao.isdinggao(zbk);
	}
	@Override
	public int isxietong(zhibiaoku zbk){
		return dao.isxietong(zbk);
	}
	@Override
	public List<zhibiaoku> yearscore22(zhibiaoku zbk) {
		return dao.yearscore22(zbk);
	}
	@Override
	public void insertsilei(String i_id,String hosnum){
		dao.insertsilei(i_id,hosnum);
	}
	@Override
	public void deletesilei(String i_id){
		dao.deletesilei(i_id);
	}
	@Override
	public int updatelistTree(List<String> list,String dept_id,String g_dept){
		int res=0;
		dao.updatelistTree(list,dept_id,g_dept);
		dao.delqzdept(list);
		res=1;
		return res;
	}
	@Override
	public List<zhibiaoku> allindept(zhibiaoku zbk){
		return dao.allindept(zbk);
	}
	@Override
	public void updatesynergy(zhibiaoku zbk){
		dao.updatex(zbk);
		dao.updatesynergy(zbk);
	}
	@Override
	public String getZbkseq() {
		return  dao.getZbkseq();
	}
	@Override
	public void updatezhibiaostoptime(zhibiaoku zbk){
		dao.updatezhibiaostoptime(zbk);
	}
	
	//拼接树功能
		@Override
		public int updatepjs(zhibiaoku o) {
			int i=0;
			i=dao.updatepjs(o);
			if(i>0){
				return 1;
			}
			return 0;
		}
		@Override
		public List<zhibiaoku> findlistthree(String hosnum) {
			
			return dao.findlistthree(hosnum);
		}
		@Override
		public List<zhibiaoku> findidpid(zhibiaoku zbk) {
			return dao.findidpid(zbk);
		}
		@Override
		public int inserttree(zhibiaoku zbk) {
			int i=0;
			i=dao.inserttree(zbk);
			if(i>0){
				return 1;
			}
			return 0;
		}
		@Override
		public List<zhibiaoku> findpidid(zhibiaoku zbk) {
			return dao.findpidid(zbk);
		}
		@Override
		public List<zhibiaoku> findtreelist(String hosnum) {
			return dao.findtreelist(hosnum);
		}
		@Override
		public List<zhibiaoku> treeyl(zhibiaoku zbk) {
			return dao.treeyl(zbk);
		}
		@Override
		public List<zhibiaoku> treename(String hosnum, String i_id, String item) {
			return dao.treename(hosnum, i_id, item);
		}
		@Override
		public int updatettree(zhibiaoku o) {
			int i=0;
			i=dao.updatettree(o);
			if(i>0){
				return 1;
			}
			return 0;
		}
		@Override
		public List<zhibiaoku> findjcb(String o) {
			return dao.findjcb(o);
		}
		@Override
		public int updateGroupTree(List<String> list, String qzDept) {
			int res=0;
			dao.updateGroupTree(list, qzDept);
			dao.deldept(list);
			res=1;
			return res;
		}
		@Override
		public List<zhibiaoku> findlistpidthree(String hosnum) {
			return dao.findlistpidthree(hosnum);
		}
		@Override
		public int delete(String id, String hosnum) {
			int i=0;
			i=dao.delete(id,hosnum);
			if(i>0){
				return 1;
			}
			return 0;
		}
		@Override
		public List<zhibiaoku> findzbkpid(String id, String hosnum) {
			return dao.findzbkpid(id, hosnum);
		}
		@Override
		public List<zhibiaoku> findzbkipid(zhibiaoku zbk) {
			return dao.findzbkipid(zbk);
		}
		@Override
		public List<zhibiaoku> yearscore33(zhibiaoku zbk) {
			return dao.yearscore33(zbk);
		}
		@Override
		public List<zhibiaoku> findjcb1(zhibiaoku o) {
			return dao.findjcb1(o);
		}
		
		@Override
		public int updatejiucuo(zhibiaoku o) {
			int i=0;
			i=dao.updatejiucuo(o);
			if(i>0){
				return 1;
			}
			return 0;
		}
		
		@Override
		public void shouhui(zhibiaoku zbk){
			dao.shouhui(zbk);
		}
		@Override
		public Page<zhibiaoku> findlistlast(zhibiaoku o,Page<zhibiaoku> page) {
			page.setResults(dao.findlistlast(o, page));
			return page;
		}
		@Override
		public List<ZNodes> finduserdept(zhibiaoku z){
			return dao.finduserdept(z);
		}
		@Override
		public List<zhibiaoku> findlistlasts(zhibiaoku o) {
			return dao.findlistlasts(o);
		}
		@Override
		public List<zhibiaoku> findjydinggao(zhibiaoku zbk) {
			return dao.findjydinggao(zbk);
		}
		@Override
		public List<zhibiaoku> findlistbydept(zhibiaoku zbk) {
			return dao.findlistbydept(zbk);
		}
		@Override
		public List<zhibiaoku> findpidbydept(zhibiaoku zbk) {
			return dao.findpidbydept(zbk);
		}
		@Override
		public List<zhibiaoku> findbydept(zhibiaoku zbk) {
			return dao.findbydept(zbk);
		}
		@Override
		public void updatesc(zhibiaoku zbk){
			Modelm m = new Modelm();
			m.setState(zbk.getState());
			m.setId(zbk.getPid());
			m.setHosnum(zbk.getHosnum());
			modeldao.updatescmodel(m);
			dao.updatesc(zbk);
		
			
					
		}
		@Override
		public void updatesc1(zhibiaoku zbk) {
			dao.updatesc1(zbk);
		}
		@Override
		public List<zhibiaoku> findpid1(String hosnum, String pid) {
			return dao.findpid1(hosnum, pid);
		}
		
		@Override
		public void insertzbkf(List<zhibiaoku> o) {
			for(zhibiaoku h:o){
				 dao.insertzbkf(h);
			}
		}
		@Override
		public zhibiaoku findzhkzh(zhibiaoku o) {
			return dao.findzhkzh(o);
		}

		@Override
		public String getpid(String pid) {
			return  dao.getpid(pid);
		}
		@Override
		public String getpids(String pid) {
			return  dao.getpids(pid);
		}

		@Override
		public List<zhibiaoku> dayinpx(zhibiaoku zbk) {
			return dao.dayinpx(zbk);
		}
		
		@Override
		public int updatejiucuo1(zhibiaoku o) {
			int i=0;
			i=dao.updatejiucuo1(o);
			if(i>0){
				return 1;
			}
			return 0;
		}
		
		@Override
		public List<zhibiaoku> findjydinggao1(zhibiaoku zbk) {
			return dao.findjydinggao1(zbk);
		}
		@Override
		public zhibiaoku findsjzbk(zhibiaoku o) {
			return dao.findsjzbk(o);
		}

}
