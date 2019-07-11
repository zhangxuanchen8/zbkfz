package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.service.base.BaseService;

public interface zbkwhService extends BaseService<zbkwh>{
	public int deletelist(List<String> list);
	public List<zbkwh> findlist(String[] list);
}
