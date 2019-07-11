package com.ehinfo.hr.repository.caiji;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.caiji.ShuJu_pz;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface ShuJu_pzDao extends BaseDao<ShuJu_pz>{
	public int deletelist(List<String> list);
	public List<ShuJu_pz> findByname();
	public List<ShuJu_pz> findBysql(ShuJu_pz pz);
	public List<ShuJu_pz> findBysql1(ShuJu_pz pz);
	public ShuJu_pz findByParmvalue(@Param("parmvalue")String parmvalue);
}
