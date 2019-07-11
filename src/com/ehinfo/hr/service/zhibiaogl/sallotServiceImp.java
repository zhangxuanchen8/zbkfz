package com.ehinfo.hr.service.zhibiaogl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.sallot;
import com.ehinfo.hr.repository.zhibiaogl.sallotDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("sallotService")
public class sallotServiceImp extends BaseServiceImp<sallot> implements sallotService{
	@Autowired
	private sallotDao dao;
	
	@Override
	public Page<sallot> findList(String openmonth, String ksname, Page<sallot> p){
		p.setResults(dao.findList(openmonth,ksname,p));
		return p;
	}
}
