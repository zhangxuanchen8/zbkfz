package com.ehinfo.hr.repository.zhibiao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.finlScore;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface finlScoreDao extends BaseDao<finlScore>{

	List<finlScore> findByPid(@Param("id")String id,@Param("dept")String dept);

	void updatestatu(finlScore o);

}
