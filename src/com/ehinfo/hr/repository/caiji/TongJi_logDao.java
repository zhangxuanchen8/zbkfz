package com.ehinfo.hr.repository.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.TongJi_log;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface TongJi_logDao extends BaseDao<TongJi_log>{
	public List<TongJi_log>finddate(TongJi_log log);
}
