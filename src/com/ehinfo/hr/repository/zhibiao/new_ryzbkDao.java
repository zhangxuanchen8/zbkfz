package com.ehinfo.hr.repository.zhibiao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface new_ryzbkDao extends BaseDao<new_ryzbk>{

	void updatescry(@Param("slist")List<String> list,@Param("state")String state);
}
