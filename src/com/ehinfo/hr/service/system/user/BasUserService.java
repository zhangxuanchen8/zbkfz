package com.ehinfo.hr.service.system.user;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.service.base.BaseService;

public interface BasUserService extends BaseService<BasUser> {
	
   
	/**
     * 根据登录帐号查找loginName和BasUserType，正常只有一条数据
     * and a.isvalid='1' and a.BasUser_type='1'需要该条件
     * @param loginName
     * @return
     */
    public BasUser findFormatByLoginName(String loginName);
   
   
	public int preResetPwd(String opwd,String npwd,String qpwd,BasUser o);
	 /**
     * 删除人员
     * @param BasUser 
     * @return
     */
	public void deleteBasUser(BasUser o);
	 /**
     * 批量删除人员
     * @param chks 人员id 
     * @return
     */
	public void deleteBatchBasUser(String chks);
	public BasUser findById(BasUser user);
	public String getAllRolesId(BasUser user);
	String insert1(BasUser o);
	public Page<BasUser> findByPageDWZ(BasUser user, Page<BasUser> page, List<BasUser> dept);
	public List<BasUser> findUserByRoleAndHos(String Role, String hosnum);
}
