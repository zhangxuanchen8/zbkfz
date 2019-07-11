package com.ehinfo.hr.service.system.org;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.system.org.BaseRole;
import com.ehinfo.hr.entity.system.org.Role;
import com.ehinfo.hr.entity.system.resources.BaseRight;
import com.ehinfo.hr.entity.system.resources.RoleResources;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.repository.system.org.RoleDao;
import com.ehinfo.hr.repository.system.sysmenu.SysMenuDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("RoleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService{
	@Autowired
	private RoleDao dao;
	
	@Override
	public List<ZNodes> listAuthorized(String roleId,String layer) {
		return ((RoleDao) baseDao).listAuthorized(roleId,layer);
	}

	@Override
	@Transactional
	public void saveAuthorized(String roleId, String aus,String layer) {
		List<RoleResources> roleAuth=new ArrayList<RoleResources>();
		String[] auss=aus.split(",");
		for(String s:auss){
			if(StringUtils.isNotBlank(s))
				roleAuth.add(new RoleResources(roleId,s));
		}
		RoleDao dao=(RoleDao)baseDao;
		dao.delAuthorizedByRoleIdAndLayer(roleId, layer);
		if(roleAuth.size()>0)dao.insertAuthorizedByRoleId(roleAuth);
	}

	@Override
	@Transactional
	public void delete(Role o) {	
		//进行事务删除，删除角色还删除角色资源关系表
		RoleDao dao=(RoleDao)baseDao;
		dao.delete(o);
		dao.delAuthorizedByRoleId(o.getId());
	}

	@Override
	@Transactional
	public void deleteBatch(List<Role> os) {
		//进行事务删除，删除角色还删除角色资源关系表
		RoleDao dao=(RoleDao)baseDao;
		dao.deleteBatch(os);
		dao.deleteBatchAuthorizedByRoleId(os);
	}

	@Override
	@Transactional
	public void save(BaseRole br, List<BaseRight> rolemenuslist,String hosnum) {
		if(dao.rolehavethis(br.getId())>0){
			dao.roledelthis(br.getId());
			dao.rightdelthis(br.getId());
		}
		dao.roleaddnew(br);
		if(rolemenuslist!=null&&rolemenuslist.size()>0){
			dao.rightaddnew(rolemenuslist);
		}
		
	}

	@Override
	public List<BaseRole> rolesearchbyid(String id) {
		return dao.rolesearchbyid(id);
	}

	@Override
	public List<String> rightsearchbyid(String id) {
		return dao.rightsearchbyid(id);
	}

	@Override
	@Transactional
	public void delrole(List<String> ids) {
		 dao.delroleright(ids);
		 dao.delrole(ids);
	}

	@Override
	public List<BaseRole> get_role_tree() {
		// TODO Auto-generated method stub
		return dao.get_role_tree();
	}

}
