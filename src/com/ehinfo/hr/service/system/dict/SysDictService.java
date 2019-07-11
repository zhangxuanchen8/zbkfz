package com.ehinfo.hr.service.system.dict;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.SysDict;
import com.ehinfo.hr.service.base.BaseService;

public interface SysDictService extends BaseService<SysDict>{

	public List<SysDict> findScopeToTree(String hosnum, String nodecode);

	public Page<SysDict> findSysDictByPage(Page<SysDict> page, String scope, String code, String type, String hosnum, String nodecode);

	public List<String> getSysnames(String hosnum);

	public List<String> getScopes();

	public List<SysDict> checkSysDicts(SysDict dict);

	public int delSysdict(SysDict dict);
	/**
	 * @see 通过name和hosnum取唯一的系统参数
	 * */
	public SysDict findByName(SysDict dict);

}
