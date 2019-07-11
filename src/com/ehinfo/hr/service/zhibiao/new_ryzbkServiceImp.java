package com.ehinfo.hr.service.zhibiao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.repository.declare.PersonalRecordDao;
import com.ehinfo.hr.repository.zhibiao.new_ryzbkDao;
import com.ehinfo.hr.repository.zhibiao.zhibiaoglDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("new_ryzbkService")
public class new_ryzbkServiceImp extends BaseServiceImp<new_ryzbk> implements new_ryzbkService{
	@Autowired
	private new_ryzbkDao dao;
	@Autowired
	private zhibiaoglDao sdao;
	@Autowired
	private PersonalRecordDao personalRecordDao;
	@Override
	@Transactional
	public void updatescry(List<String> list,String state) {
		dao.updatescry(list,state);
		sdao.updatescrys(list, state);
		personalRecordDao.updatescryp(list, state);
	}
	@Override
	public void updatescry1(List<String> list, String state) {
		dao.updatescry(list,state);
	}
}
