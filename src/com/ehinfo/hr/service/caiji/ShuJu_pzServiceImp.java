package com.ehinfo.hr.service.caiji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.caiji.ShuJu_pz;
import com.ehinfo.hr.repository.caiji.ShuJu_pzDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("ShuJu_pzService")
public class ShuJu_pzServiceImp extends BaseServiceImp<ShuJu_pz> implements ShuJu_pzService{
	@Autowired
	private ShuJu_pzDao Dao;
	
	@Override
	public int deletelist(List<String> list) {
		int i=Dao.deletelist(list);
		if(i>0){
			return 1;
		}
		return 0;
	}

	@Override
	public List<ShuJu_pz> findByname() {
		return Dao.findByname();
	}

	@Override
	public List<ShuJu_pz> findBysql(ShuJu_pz pz) {
		return Dao.findBysql(pz);
	}

	@Override
	public ShuJu_pz findByParmvalue(String parmvalue) {
		return Dao.findByParmvalue(parmvalue);
	}

	@Override
	public List<ShuJu_pz> findBysql1(ShuJu_pz pz) {
		return Dao.findBysql1(pz);
	}

}
