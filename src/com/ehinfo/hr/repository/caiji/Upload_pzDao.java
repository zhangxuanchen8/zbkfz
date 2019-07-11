package com.ehinfo.hr.repository.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.Upload_pz;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface Upload_pzDao extends BaseDao<Upload_pz> {
	public int deletelist(List<String> list);
	public void updatetime(String name);
	public int updatesdsj(Upload_pz pz);
}
