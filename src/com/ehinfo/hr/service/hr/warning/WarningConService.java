package com.ehinfo.hr.service.hr.warning;

import java.util.List;

import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.service.base.BaseService;

public interface WarningConService extends BaseService<WarningCon> {

	List<WarningCon> fondGroup(WarningCon wc);

	void updateStatus(WarningCon con);
	
	
	
}
