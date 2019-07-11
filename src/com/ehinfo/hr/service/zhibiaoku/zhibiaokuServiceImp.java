package com.ehinfo.hr.service.zhibiaoku;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaoku.zhibiaoku;
import com.ehinfo.hr.repository.zhibiaoku.zhibiaokuDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("ZhibiaokuService")
public class zhibiaokuServiceImp extends BaseServiceImp<zhibiaoku> implements zhibiaokuService{
	@Autowired
	private zhibiaokuDao dao;
	
	@Override
	public List<zhibiaoku> findzbktype(String hosnum) {
		return dao.findzbktype(hosnum);
	}
	@Override
	public Page<zhibiaoku> findByList(zhibiaoku zbk,Page<zhibiaoku> p){
		p.setResults(dao.findByList(zbk,p));
		return p;
	}
	@Override
	public void softdelete(List<zhibiaoku> list) {
		dao.softdelete(list);
	}
	@Override
	public List<String> isUseOption(String hosnum) {
		return dao.isUseOption(hosnum);
	}
	@Override
	public List<zhibiaoku> findzbkcj(String hosnum,String name) {
		return dao.findzbkcj(hosnum,name);
	}

}
