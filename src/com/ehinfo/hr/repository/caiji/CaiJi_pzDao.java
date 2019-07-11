package com.ehinfo.hr.repository.caiji;

import java.util.List;

import com.ehinfo.hr.entity.caiji.CaiJi_pz;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface CaiJi_pzDao extends BaseDao<CaiJi_pz>{
	public int deletelist(List<String> list);
	public void updatetime(String name);
	public int updatesdsj(CaiJi_pz pz);
}
