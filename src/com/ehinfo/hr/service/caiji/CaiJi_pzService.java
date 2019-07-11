package com.ehinfo.hr.service.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.CaiJi_pz;
import com.ehinfo.hr.service.base.BaseService;

public interface CaiJi_pzService extends BaseService<CaiJi_pz>{
	public int deletelist(List<String> list);
	public void updatetime(String name);
	public int updatesdsj(CaiJi_pz pz);
}
