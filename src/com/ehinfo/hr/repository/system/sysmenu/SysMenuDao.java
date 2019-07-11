package com.ehinfo.hr.repository.system.sysmenu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes_Menu;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface SysMenuDao extends BaseDao<BaseMenu>{
	public List<BaseMenu> system_menu_tree();
	
	public List<BaseMenu> searchbypage(Page<BaseMenu> page,@Param("param")BaseMenu bm);

	public List<BaseMenu> searchbyid(@Param("param")BaseMenu bm);

	public List<BaseMenu> searchbypid(@Param("param")BaseMenu bm);

	public void addnew(@Param("param")BaseMenu bm);

	public Integer haveson(List<String> ids);
	
	public Integer idhaveson(@Param("param")BaseMenu bm);

	public void delsysmenu(List<String> ids);

	public void delrolemenus(List<String> ids);

	public void delsinglemenu(@Param("param")BaseMenu bm);

	
}
