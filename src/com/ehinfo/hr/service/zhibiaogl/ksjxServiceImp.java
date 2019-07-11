package com.ehinfo.hr.service.zhibiaogl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.ksjx;
import com.ehinfo.hr.repository.zhibiaogl.ksjxDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("ksjxService")
public class ksjxServiceImp extends BaseServiceImp<ksjx> implements ksjxService{
	@Autowired
	private ksjxDao dao;
	
	@Override
	public Page<ksjx> findList(String openmonth, String ksname, Page<ksjx> p) {
		p.setResults(dao.findList(openmonth,ksname,p));
		return p;
	}

}
