package com.ehinfo.hr.service.system.sysmenu;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.service.base.BaseService;

public interface SysMenuService extends BaseService<BaseMenu>{
	public List<BaseMenu> system_menu_tree();

	public Page<BaseMenu> searchbypage(Page<BaseMenu> page,BaseMenu bm);

	public List<BaseMenu> searchbypid(BaseMenu bm);

	public List<BaseMenu> searchbyid(BaseMenu bm);

	public void addnew(BaseMenu bm);
	
	public int delsysmenu(List<String> ids);
}
