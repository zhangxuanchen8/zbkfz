package com.ehinfo.hr.service.system.org;

import java.util.List;

import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.system.org.BaseRole;
import com.ehinfo.hr.entity.system.org.Role;
import com.ehinfo.hr.entity.system.resources.BaseRight;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.service.base.BaseService;

public interface RoleService extends BaseService<Role>{

	 /**
     * 权限列表包含按钮
     * @param roleId 角色Id
     * @param layer  显示层级
     * @return
     */
	public List<ZNodes> listAuthorized(String roleId,String layer);
	 /**
     * 根据角色Id保存权限列表
     * @param roleId 角色Id
     * @param auss 权限数组
     * @return
     */
	public void saveAuthorized(String roleId,String auss,String layer);
	 /**
     * 移植his新添保存方法
     * @param br 角色对象
     * @param rolemenuslist 菜单数组
     * @return
     */
	public void save(BaseRole br, List<BaseRight> rolemenuslist,String hosnum);

	public List<BaseRole> rolesearchbyid(String id);

	public List<String> rightsearchbyid(String id);
	
	public void delrole(List<String> ids);
	public List<BaseRole> get_role_tree();
}
