package com.ehinfo.hr.repository.system.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface BasUserDao extends BaseDao<BasUser> {
    public BasUser findFormatByLoginName(String loginName);
	public void insertRole(BasUser user);
	public void deleteRole(BasUser user);
	public String getAllRolesId(BasUser user);
	public List<BasUser> findByPageDWZ(@Param("param")BasUser user, Page<BasUser> page, @Param("list")List<BasUser> list);
	public List<BasUser> findUserByRoleAndHos(@Param("rolename")String RoleName, @Param("hosnum")String hosnum);


}
