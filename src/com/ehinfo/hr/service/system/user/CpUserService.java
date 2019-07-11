package com.ehinfo.hr.service.system.user;


import java.util.List;

import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.service.base.BaseService;

public interface CpUserService extends BaseService<CpUser> {
	public CpUser findById(String id);
	public int xgPwd(String opwd,String npwd,String qpwd,CpUser o);
	public int updatebj(CpUser cp);
	public int updatesj(CpUser cp);
	public int updatesj1(CpUser cp);
	public List<CpUser> findcpnums(String hosnum);
}
