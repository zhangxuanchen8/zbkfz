package com.ehinfo.hr.service.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.ShuJu_pz;
import com.ehinfo.hr.service.base.BaseService;

public interface ShuJu_pzService extends BaseService<ShuJu_pz>{
	public int deletelist(List<String> list);
	public List<ShuJu_pz> findByname();
	public List<ShuJu_pz> findBysql(ShuJu_pz pz);
	public List<ShuJu_pz> findBysql1(ShuJu_pz pz);
	public ShuJu_pz findByParmvalue(String parmvalue);
}
