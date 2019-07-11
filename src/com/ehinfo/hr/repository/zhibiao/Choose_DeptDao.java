package com.ehinfo.hr.repository.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface Choose_DeptDao  extends BaseDao<Choose_Dept>{
	public int checkZ_name(Choose_Dept o);//验证科室名称
	public int deleteDept(Choose_Dept o);//删除群组科室
	public List<Choose_Dept> getZhiBiaoGroupTree(@Param("param")Choose_Dept o);//获取群组科室名称
}
