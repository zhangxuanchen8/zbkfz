package com.ehinfo.hr.service.zhibiao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiao.finlScore;
import com.ehinfo.hr.repository.zhibiao.finlScoreDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("finlScoreService")
public class finlScoreServiceImp extends BaseServiceImp<finlScore> implements finlScoreService{
	@Autowired
	private finlScoreDao dao;

	@Override
	public List<finlScore> findByPid(String id,String dept) {
		return dao.findByPid(id,dept);
	}
	
	@Override
	public void updatestatu(finlScore o) {
		dao.updatestatu(o);
	}
}
