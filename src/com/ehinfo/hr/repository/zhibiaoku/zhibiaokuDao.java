package com.ehinfo.hr.repository.zhibiaoku;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaoku.zhibiaoku;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface zhibiaokuDao extends BaseDao<zhibiaoku> {
	public List<zhibiaoku> findzbktype(String hosnum);

	public List<zhibiaoku> findByList(@Param("zbk") zhibiaoku zbk, @Param("page") Page<zhibiaoku> page);

	public void softdelete(@Param("list") List<zhibiaoku> list);

	public List<String> isUseOption(@Param("hosnum")String hosnum);
	
	public List<zhibiaoku> findzbkcj(@Param("hosnum")String hosnum,@Param("name")String name);
}
