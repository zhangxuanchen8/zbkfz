package com.ehinfo.hr.repository.system.dict;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.SysDict;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
/**
 * 系统字典数据层
 */
@JYBatis
public interface SysDictDao extends BaseDao<SysDict>{

	List<SysDict> findScopeToTree(@Param("hosnum")String hosnum, @Param("nodecode")String nodecode);

	List<SysDict> findSysDictByPage(Page<SysDict> page,@Param("param")SysDict dict, @Param("code")String code);

	List<String> getSysnames(@Param("hosnum")String hosnum);

	List<String> getScopes();

	List<SysDict> checkSysDicts(SysDict dict);

	SysDict findByName(SysDict dict);
	
}
