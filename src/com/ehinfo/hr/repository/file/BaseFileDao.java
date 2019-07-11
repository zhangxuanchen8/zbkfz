package com.ehinfo.hr.repository.file;

import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface BaseFileDao extends BaseDao<BaseFile> {

	BaseFile findById(BaseFile file);
	
	BaseFile getById(BaseFile file);

}
