package com.ehinfo.hr.repository.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface zbkwhDao extends BaseDao<zbkwh>{
	public void deletelist(@Param("slist")List<String> list);
	public List<zbkwh> findlist(@Param("clist")String[] list);
}
