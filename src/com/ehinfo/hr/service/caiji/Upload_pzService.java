package com.ehinfo.hr.service.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.Upload_pz;
import com.ehinfo.hr.service.base.BaseService;

public interface Upload_pzService  extends BaseService<Upload_pz>{
	public int deletelist(List<String> list);
	public void updatetime(String name);
	public int updatesdsj(Upload_pz pz);
}
