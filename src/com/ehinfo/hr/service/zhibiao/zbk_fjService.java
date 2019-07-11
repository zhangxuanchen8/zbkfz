package com.ehinfo.hr.service.zhibiao;


import java.util.List;

import com.ehinfo.hr.entity.zhibiao.zbk_fj;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.service.base.BaseService;

public interface zbk_fjService extends BaseService<zbk_fj>{
	public List<zbk_fj>findByid(zbk_fj fj);
	public int update1(zbk_fj fj);
	public int insert1(zbk_fj fj);
	public List<zbk_fj> findPid(String id,String dept);
	public List<zbk_fj> findmaxscore(String id,String dept);
}
