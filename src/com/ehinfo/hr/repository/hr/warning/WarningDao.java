package com.ehinfo.hr.repository.hr.warning;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface WarningDao extends  BaseDao<Warning> {

	List<Warning> findByType(Warning wn);

	List<Warning> findWarning(Warning wn);

	List<WarningCon> findWarnName(@Param("warn_ids")String[] warn_ids);
	
}
