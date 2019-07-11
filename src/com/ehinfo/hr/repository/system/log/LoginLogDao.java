package com.ehinfo.hr.repository.system.log;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.log.LoginLog;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface LoginLogDao {

	public List<LoginLog> findByPage(@Param("param")LoginLog o,Page<LoginLog> page);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
