package com.ehinfo.hr.repository.zhibiaogl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.sallot;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface sallotDao extends BaseDao<sallot>{
	public List<sallot> findList(@Param("openmonth")String openmonth,@Param("ksname")String ksname,@Param("page")Page<sallot> page);
}
