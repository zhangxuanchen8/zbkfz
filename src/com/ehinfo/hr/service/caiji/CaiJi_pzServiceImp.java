package com.ehinfo.hr.service.caiji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.caiji.CaiJi_pz;
import com.ehinfo.hr.repository.caiji.CaiJi_pzDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("CaiJi_pzService")
public class CaiJi_pzServiceImp extends BaseServiceImp<CaiJi_pz> implements CaiJi_pzService{
	@Autowired
	private CaiJi_pzDao Dao;
	
	@Override
	public int deletelist(List<String> list) {
		int i=Dao.deletelist(list);
		if(i>0){
			return 1;
		}
		return 0;
	}

	@Override
	public void updatetime(String name) {
		Dao.updatetime(name);
		
	}

	@Override
	public int updatesdsj(CaiJi_pz pz) {
		int i=Dao.updatesdsj(pz);
		if(i>0){
			return 1;
		}
		return 0;
	}
}
