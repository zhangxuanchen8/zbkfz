package com.ehinfo.hr.service.hr.warning;

import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.repository.hr.warning.WarningConDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("WarningConService")
public class WarningConServiceImp extends BaseServiceImp<WarningCon> implements WarningConService {

	@Autowired
	WarningConDao dao;
	
	@Override
	public List<WarningCon> fondGroup(WarningCon wc) {
		return dao.fondGroup(wc);
	}

	@Override
	public void updateStatus(WarningCon con) {
		dao.updateStatus(con);
	}
	
}
