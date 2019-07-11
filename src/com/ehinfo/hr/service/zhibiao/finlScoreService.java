package com.ehinfo.hr.service.zhibiao;



import java.util.List;

import com.ehinfo.hr.entity.zhibiao.finlScore;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.service.base.BaseService;

public interface finlScoreService extends BaseService<finlScore>{
	public List<finlScore> findByPid(String id,String dept);
	public void updatestatu(finlScore o);
}
