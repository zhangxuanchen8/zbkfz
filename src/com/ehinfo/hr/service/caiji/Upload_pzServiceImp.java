package com.ehinfo.hr.service.caiji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.caiji.Upload_pz;
import com.ehinfo.hr.repository.caiji.Upload_pzDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("Upload_pzService")
public class Upload_pzServiceImp extends BaseServiceImp<Upload_pz> implements Upload_pzService{
	@Autowired
	private Upload_pzDao Dao;
	
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
	public int updatesdsj(Upload_pz pz) {
		int i=Dao.updatesdsj(pz);
		if(i>0){
			return 1;
		}
		return 0;
	}
}
