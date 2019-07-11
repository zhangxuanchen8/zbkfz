package com.ehinfo.hr.repository.system.user;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface CpUserDao extends BaseDao<CpUser> {
	public CpUser findById(String id);
	public int updatebj(CpUser cp);
	public int updatesj(CpUser cp);
	public int updatesj1(CpUser cp);
	public void deletelist(@Param("slist")List<String> list);
	public List<CpUser> findcpnums(@Param("hosnum")String hosnum);
}
