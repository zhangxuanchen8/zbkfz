package com.ehinfo.hr.repository.system.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.Employee;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface HospitalDao extends BaseDao<Hospital> {

	public List<Hospital> getHospitalTreeNodes();
	
	public List<Hospital> findHospitalByPage(Page<Hospital> page,@Param("supunit")String supunit, @Param("code")String code);

	public List<Hospital> findChild(String hosnum);

	public List<BaseDict> selectDict(BaseDict dict);

	public void insertDept(Hospital hospital);

	public void updateDept(Hospital hospital);

	public Org findDept(Hospital hospital);

	public List<Org> findAndChild(@Param("id")String oid);

	public void deleteDept(Org org);

	public Org findParentDept(Hospital hospital);

	public List<Hospital> findAllHospital();

	public Hospital findByHosnum(Hospital emp);
	public Hospital findByHosname(Hospital emp);
}
