package com.ehinfo.hr.repository.zhibiao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface zbk_fjDao extends BaseDao<zbk_fj>{
	public List<zbk_fj>findByid(@Param("param")zbk_fj fj);
	public int update1(zbk_fj fj);
	public int insert1(zbk_fj fj);
	public List<zbk_fj> findPid(@Param("id")String id,@Param("dept")String dept);
	public List<zbk_fj> findmaxscore(@Param("id")String id,@Param("dept")String dept);
}
