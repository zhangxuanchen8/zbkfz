package com.ehinfo.hr.repository.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface ryzhibiaokuDao extends BaseDao<ryzbk>{
	public List<ryzbk> findpeoplelist(String hosnum);
	public List<ryzbk> findpeoplename(@Param("param")String id);
	public List<ryzbk>findpeoplepage(@Param("param")ryzbk o,Page<ryzbk> page);
	public List<ryzbk> findzbkren(@Param("hosnum")String hosnum);
}
