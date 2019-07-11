package com.ehinfo.hr.repository.hr.warning;

import java.util.List;

import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface WarningConDao extends  BaseDao<WarningCon> {
	List<WarningCon> fondGroup(WarningCon wc);
	
	void updateStatus(WarningCon con);
	
	List<WarningCon> findByNameId(WarningCon wn);
	/*根据用户id和错误明*/
	void updateByNameId(WarningCon wn);

	void deleteByNameId(WarningCon warningCon);

	void insertByNameId(WarningCon warningCon);
	
}
