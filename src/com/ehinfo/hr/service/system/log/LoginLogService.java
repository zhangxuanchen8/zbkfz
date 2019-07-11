package com.ehinfo.hr.service.system.log;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.log.LoginLog;

public interface LoginLogService {

	public Page<LoginLog> findByPage(LoginLog o,Page<LoginLog> page);	
	
	public void saveLoginLog(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
	

}
