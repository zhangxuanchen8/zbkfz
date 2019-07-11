package com.ehinfo.hr.repository.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface zhibiao_sbDao extends BaseDao<zhibiaoku>{
	public List<zhibiaoku> findlist(String hosnum);
	public void deletelist(List<String> list);
	public Integer haveson(List<String> ids);
	public void updatescore(zhibiaoku o);
	public void yinru(zhibiaoku o);
	public void delcf(zhibiaoku o);
	public List<zhibiaoku> findpeoplelist(String hosnum);
	public void insertscore(zbkscore score);
	public int updatejiucuo1(zhibiaoku o);
	public List<zhibiaoku>findjydinggao1(@Param("param")zhibiaoku zbk);
	public List<zhibiaoku> findtwo(@Param("param")String id,@Param("param1")String hosnum);
	public List<zhibiaoku> findpid(@Param("param")String id,@Param("param1")String hosnum);
	public List<zhibiaoku> findpid1(@Param("hosnum")String hosnum,@Param("pid")String pid);
	public List<zhibiaoku> findzbkpid(@Param("param")String id,@Param("param1")String hosnum);
	public void updateusing(zhibiaoku o);
	public Double findzj(@Param("param")String hosnum,@Param("param1")String pid);
	public void updatedinggao(zhibiaoku o);
	public List<zhibiaoku> findyearlist(@Param("hosnum")String hosnum, @Param("pid")String pid);
	public Double countmax(@Param("param")String hosnum,@Param("param1")String pid);
	public List<zhibiaoku> findbgxl(zhibiaoku zbk );
	public void upscorehj(zhibiaoku o);
	public List<zhibiaoku> findpeoplename(@Param("param")String id,@Param("param1")String hosnum);
	public List<zhibiaoku>yearscore(@Param("param")zhibiaoku zbk);
	
	public void deldinggao(zhibiaoku o);
	public List<zhibiaoku>dayinpx(@Param("param")zhibiaoku zbk);
	public List<zhibiaoku>findpeoplepage(@Param("param")zhibiaoku o,Page<zhibiaoku> page);
	
	public List<ZNodes> getZhiBiaoTree(@Param("param")String id);
	
	public int findoutyy(@Param("param")zhibiaoku zbk);
	
	public List<zhibiaoku> findlistscore_yy(@Param("param")zhibiaoku zbk);
	public List<zhibiaoku> findlistscore_ks(@Param("param")zhibiaoku zbk);
	public void insertlist(@Param("zbklist")List<zbkwh> list,@Param("pid")String pid,@Param("hosnum")String hosnum);
	public int isdinggao(@Param("param")zhibiaoku zbk);
	public List<zhibiaoku>yearscore22(@Param("param")zhibiaoku zbk);
	public List<zhibiaoku>yearscore33(@Param("param")zhibiaoku zbk);
	public void insertsilei(@Param("i_id")String i_id, @Param("hosnum")String hosnum);
	public void deletesilei(String i_id);
	public void updatelistTree(@Param("slist")List<String> list,@Param("dept_id")String dept_id,@Param("g_dept")String g_dept);
	public List<zhibiaoku> allindept(zhibiaoku zbk);
	public void updatesynergy(zhibiaoku zbk);
	public List<zhibiaoku> findyearlist1(@Param("hosnum")String hosnum);
	public int isxietong(@Param("param")zhibiaoku zbk);
	public void updatex(zhibiaoku zbk);
	public String getZbkseq();
	public void updatezhibiaostoptime(zhibiaoku zbk);
	
	//拼接树功能
		public int updatepjs(zhibiaoku o);
		
		public List<zhibiaoku>findlistthree(@Param("hosnum")String hosnum);
		
		public List<zhibiaoku>findlistpidthree(@Param("hosnum")String hosnum);
		
		public List<zhibiaoku>findidpid(zhibiaoku zbk); 
		
		public List<zhibiaoku>findzbkipid(zhibiaoku zbk);
		
		public int inserttree(zhibiaoku zbk);
		
		public List<zhibiaoku>findpidid(zhibiaoku zbk);
		
		public List<zhibiaoku>findtreelist(@Param("hosnum")String hosnum);
		
		public List<zhibiaoku>treeyl(zhibiaoku zbk);
		
		public List<zhibiaoku>treename(@Param("hosnum")String hosnum,@Param("i_id")String i_id,@Param("item")String item);
		
		public int updatettree(zhibiaoku o);
		
		public List<zhibiaoku>findjcb(@Param("hosnum")String o);
		
		public List<zhibiaoku>findjcb1(zhibiaoku o);
		
		public int delete(@Param("id")String id,@Param("hosnum")String hosnum);
		
	//选定群组科室
		public int updateGroupTree(@Param("slist")List<String> list,@Param("qzDept")String qzDept);
		public List<zhibiaoku>findjydinggao(@Param("param")zhibiaoku zbk);
		public int updatejiucuo(zhibiaoku o);
		public void shouhui(zhibiaoku zbk);
		public List<zhibiaoku> findlistlast(@Param("param")zhibiaoku o, Page<zhibiaoku> page);
		public List<ZNodes> finduserdept(zhibiaoku z);
		public List<zhibiaoku> findlistlasts(zhibiaoku o);
		public void delqzdept(@Param("slist")List<String> list);
		public void deldept(@Param("slist")List<String> list);
		public List<zhibiaoku> findlistbydept(zhibiaoku zbk);
		public List<zhibiaoku> findpidbydept(zhibiaoku zbk);
		public List<zhibiaoku> findbydept(zhibiaoku zbk);
		public void updatesc(zhibiaoku zbk);
		public void updatesc1(zhibiaoku zbk);
		public void insertzbkf(zhibiaoku o);
		public void delzbkzh(zhibiaoku o);
		public zhibiaoku findzhkzh(zhibiaoku o);
		public String getpid(@Param("pid")String pid);
		public String getpids(@Param("pid")String pid);
		
		public void updategdzbk(@Param("pid") String pid);
		public zhibiaoku findsjzbk(zhibiaoku o);
}
