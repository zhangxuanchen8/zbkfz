package com.ehinfo.hr.service.declare;

import java.util.List;

import com.ehinfo.hr.entity.declare.Declare;
import com.ehinfo.hr.service.base.BaseService;

public interface DeclareService extends BaseService<Declare>{

	List<Declare> addList(List<Declare> decl);

	List<Declare> getYear();

	Declare addOne(Declare declare);

	Declare getByPidYear(Declare declare);

}
