package com.ehinfo.hr.repository.declare;

import java.util.List;

import com.ehinfo.hr.entity.declare.Declare;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface DeclareDao extends BaseDao<Declare>{

	List<Declare> getYear();
	
	Declare getByPidYear(Declare declare);
	
	void updateDate(Declare declare);
}
