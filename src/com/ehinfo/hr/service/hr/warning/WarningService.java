package com.ehinfo.hr.service.hr.warning;


import java.util.List;

import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.service.base.BaseService;

public interface WarningService extends BaseService<Warning> {

	List<Warning> fingByType(Warning wn);

	List<Warning> findWarning(Warning wn);

	List<WarningCon> findWarnName(String[] warn_ids);
	
	
	
}
