package com.ehinfo.hr.service.system.file;

import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.service.base.BaseService;

public interface BaseFileService extends BaseService<BaseFile> {

	BaseFile findById(BaseFile file);
	BaseFile getById(BaseFile file);

}
