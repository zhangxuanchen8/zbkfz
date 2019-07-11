package com.ehinfo.hr.repository.zhibiaogl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.zhibiaogl.OptionName;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface optionDao extends BaseDao<OptionName>{

	List<OptionName> getOption(@Param("option")String option,@Param("hosnum")String hosnum);

	void deleteOriginal(@Param("hosnum")String hosnum,@Param("option_name")String option_name);

	void insertMulti(@Param("chk")String[] chk, @Param("option_name")String option_name);

}
