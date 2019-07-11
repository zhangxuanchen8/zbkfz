package com.ehinfo.hr.service.zhibiaogl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.zhibiaokh;
import com.ehinfo.hr.repository.zhibiaogl.zhibiaokhDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("zhibiaokhService")
public class zhibiaokhServiceImp extends BaseServiceImp<zhibiaokh> implements zhibiaokhService{
	@Autowired
	private zhibiaokhDao dao;
	
	@Override
	public List<zhibiaokh> findzbktype(String hosnum) {
		return dao.findzbktype(hosnum);
	}
	@Override
	public Page<zhibiaokh> findByList(zhibiaokh zbk,Page<zhibiaokh> p){
		p.setResults(dao.findByList(zbk));
		return p;
	}
	@Override
	public List<zhibiaokh> isDeptCompute(String s,String id) {
		return dao.isDeptCompute(s,id);
	}
	@Override
	public String findDpetname(String s) {
		return dao.findDeptname(s);
	}

}
