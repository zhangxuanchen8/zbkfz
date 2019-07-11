package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.base.BaseService;

public interface zhibiao_sbService extends BaseService<zhibiaoku>{
		public List<zhibiaoku> findlist(String hosnum);
		public int deletelist(List<String> list);
		public void updatescore(zhibiaoku o);
		public void yinru(zhibiaoku o);
		public void delcf(zhibiaoku o);
		public List<zhibiaoku> findpeoplelist(String hosnum);
		public List<zhibiaoku> findtwo(String id,String hosnum);
		public List<zhibiaoku> findpid(String id,String hosnum);
		public void updateusing(zhibiaoku o);
		public Double findzj(String hosnum,String pid);
		public void updatedinggao(zhibiaoku o);
		public List<zhibiaoku> findyearlist1(String hosnum);
		public List<zhibiaoku> findyearlist(String hosnum,String pid);
		public Double countmax(String hosnum,String pid);
		public List<zhibiaoku> findbgxl(zhibiaoku zbk );
		public void upscorehj(zhibiaoku o);
		public List<zhibiaoku>dayinpx(zhibiaoku zbk);
		public List<zhibiaoku> findpid1(String hosnum,String pid);
		public List<zhibiaoku> findpeoplename(String id,String hosnum);
		public List<zhibiaoku>yearscore(zhibiaoku zbk);
		public void deldinggao(zhibiaoku o);
		public Page<zhibiaoku>findpeoplepage(zhibiaoku string,Page<zhibiaoku> page);
		public List<ZNodes> getZhiBiaoTree(String id);
		public int findoutyy(zhibiaoku zbk);
		public List<zhibiaoku>findzbkipid(zhibiaoku zbk);
		public List<zhibiaoku> findlistscore_yy(zhibiaoku zbk);
		public List<zhibiaoku> findlistscore_ks(zhibiaoku zbk);
		public int insertlist(List<zbkwh> list,String pid,String hosnum);
		public int isdinggao(zhibiaoku zbk);
		public int isxietong(zhibiaoku zbk);
		public List<zhibiaoku>yearscore22(zhibiaoku zbk);
		public void insertsilei(String i_id,String hosnum);
		public void deletesilei(String i_id);
		public int updatelistTree(List<String> list,String dept_id,String g_dept);
		public List<zhibiaoku> allindept(zhibiaoku zbk);
		public void updatesynergy(zhibiaoku zbk);
		String getZbkseq();
		public void updatezhibiaostoptime(zhibiaoku zbk);
		public List<zhibiaoku> findzbkpid(String id,String hosnum);
		//拼接树功能
				public int updatepjs(zhibiaoku o);
				public List<zhibiaoku>findlistthree(String hosnum);
				public List<zhibiaoku>findidpid(zhibiaoku zbk);
				public int inserttree(zhibiaoku zbk);
				public List<zhibiaoku>findpidid(zhibiaoku zbk);
				public List<zhibiaoku>findtreelist(String hosnum);
				public List<zhibiaoku>treeyl(zhibiaoku zbk);
				public List<zhibiaoku>treename(String hosnum,String i_id,String item);
				public int updatettree(zhibiaoku o);
				public List<zhibiaoku>findjcb(String string);
				public List<zhibiaoku>findlistpidthree(String hosnum);
				public int delete(String id,String hosnum);
				public List<zhibiaoku>yearscore33(zhibiaoku zbk);
				public List<zhibiaoku>findjcb1(zhibiaoku o);
				
		//选定群组科室
		public int updateGroupTree(List<String> list,String qzDept);
		
		public int updatejiucuo(zhibiaoku o);
		public List<zhibiaoku>findjydinggao(zhibiaoku zbk);
		public void shouhui(zhibiaoku zbk);
		public Page<zhibiaoku> findlistlast(zhibiaoku zbk,Page<zhibiaoku> page);
		public List<ZNodes> finduserdept(zhibiaoku z);
		public List<zhibiaoku> findlistlasts(zhibiaoku z);
		public List<zhibiaoku> findlistbydept(zhibiaoku z);
		public List<zhibiaoku> findpidbydept(zhibiaoku z);
		public List<zhibiaoku> findbydept(zhibiaoku z);
		public void updatesc(zhibiaoku zbk);
		public void updatesc1(zhibiaoku zbk);
		public void insertzbkf(List<zhibiaoku> o);
		public zhibiaoku findzhkzh(zhibiaoku zbk);
		public List<zhibiaoku>findjydinggao1(zhibiaoku zbk);
		public int updatejiucuo1(zhibiaoku o);
		String getpid(String pid);
		String getpids(String pid);
		public zhibiaoku findsjzbk(zhibiaoku zbk);
}
