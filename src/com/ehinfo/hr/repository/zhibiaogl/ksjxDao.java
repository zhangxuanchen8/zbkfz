package com.ehinfo.hr.repository.zhibiaogl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.ksjx;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface ksjxDao extends BaseDao<ksjx>{
	public List<ksjx> findList(@Param("openmonth")String openmonth,@Param("ksname")String ksname,@Param("page")Page<ksjx> page);
}
