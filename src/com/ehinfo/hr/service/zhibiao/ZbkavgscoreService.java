package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import com.ehinfo.hr.entity.zhibiao.Zbkavgscore;
import com.ehinfo.hr.service.base.BaseService;

public interface ZbkavgscoreService extends BaseService<Zbkavgscore>{
	public int deletelist(List<String> list);
}
