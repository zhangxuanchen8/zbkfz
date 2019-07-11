package com.ehinfo.hr.service.system.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.repository.file.BaseFileDao;
import com.ehinfo.hr.repository.system.org.HospitalDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("BaseFileService")
public class BaseFileServiceImp extends BaseServiceImp<BaseFile> implements BaseFileService {

	
	
	@Autowired
	private BaseFileDao dao;
	
	
	
	@Override
	public BaseFile findById(BaseFile file) {
		return dao.findById(file);
	}
	
	@Override
	public BaseFile getById(BaseFile file) {
		return dao.getById(file);
	}
}
