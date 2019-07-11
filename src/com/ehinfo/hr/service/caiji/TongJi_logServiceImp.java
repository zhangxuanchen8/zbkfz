package com.ehinfo.hr.service.caiji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.caiji.TongJi_log;
import com.ehinfo.hr.repository.caiji.TongJi_logDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("TongJi_logService")
public class TongJi_logServiceImp extends BaseServiceImp<TongJi_log> implements TongJi_logService{

	@Autowired
	private TongJi_logDao Dao;
	
	@Override
	public List<TongJi_log> finddate(TongJi_log log) {
		
		return Dao.finddate(log);
	}

}
