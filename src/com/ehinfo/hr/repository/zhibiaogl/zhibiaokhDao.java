package com.ehinfo.hr.repository.zhibiaogl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiaogl.zhibiaokh;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface zhibiaokhDao extends BaseDao<zhibiaokh>{
	public List<zhibiaokh> findzbktype(String hosnum);
	public List<zhibiaokh> findByList(@Param("zbk")zhibiaokh zbk);
	public List<zhibiaokh> isDeptCompute(@Param("deptid")String s,@Param("id")String id);
	public String findDeptname(@Param("deptid")String s);
}
