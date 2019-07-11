package com.ehinfo.hr.repository.zhibiao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.entity.zhibiao.Zbkavgscore;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface ZbkavgscoreDao  extends BaseDao<Zbkavgscore>{

	void deletelist(List<String> list);
	
}
