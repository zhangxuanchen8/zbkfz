package com.ehinfo.hr.service.system.org;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.service.base.BaseService;

public interface HospitalService extends BaseService<Hospital> {
   public List<Hospital> getHospitalTreeNodes();
   
   public Page<Hospital> findHospitalByPage(Page<Hospital> page,String supunit, String code);

   public int delHospital(Hospital hospital);

public List<BaseDict> selectDict(BaseDict dict);

public List<Hospital> findAllHospital();

public Hospital findByHosnum(Hospital hos);
public Hospital findByHosname(Hospital hos);
}
