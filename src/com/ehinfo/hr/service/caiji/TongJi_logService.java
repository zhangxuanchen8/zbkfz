package com.ehinfo.hr.service.caiji;


import java.util.List;

import com.ehinfo.hr.entity.caiji.TongJi_log;
import com.ehinfo.hr.service.base.BaseService;


public interface TongJi_logService extends BaseService<TongJi_log>{
	public List<TongJi_log>finddate(TongJi_log log);
}
