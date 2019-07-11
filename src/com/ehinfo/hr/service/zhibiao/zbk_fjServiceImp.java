package com.ehinfo.hr.service.zhibiao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.repository.zhibiao.zbk_fjDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("zbk_fjService")
public class zbk_fjServiceImp extends BaseServiceImp<zbk_fj> implements zbk_fjService{
	@Autowired
	private zbk_fjDao dao;

	@Override
	public List<zbk_fj> findByid(zbk_fj fj) {
		return dao.findByid(fj);
	}

	@Override
	public int update1(zbk_fj fj) {
		if(dao.update1(fj)>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int insert1(zbk_fj fj) {
		if(dao.insert1(fj)>0){
			return 1;
		}
		return 0;
	}
	@Override
	public List<zbk_fj> findPid(String id,String dept) {
		return dao.findPid(id,dept);
	}
	@Override
	public List<zbk_fj> findmaxscore(String id,String dept) {
		return dao.findmaxscore(id,dept);
	}
}

