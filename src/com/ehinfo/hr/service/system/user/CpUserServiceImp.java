package com.ehinfo.hr.service.system.user;



import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.repository.system.user.CpUserDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("CpUserService")
public class CpUserServiceImp extends BaseServiceImp<CpUser> implements CpUserService  {

	@Autowired
	private CpUserDao dao;

	@Override
	public CpUser findById(String id) {
		return dao.findById(id);
	}

	@Override
	public int xgPwd(String opwd, String npwd, String qpwd, CpUser o) {
		int res=0;
		if(StringUtils.isNotBlank(opwd)&&StringUtils.isNotBlank(npwd)){	
			if(StringUtils.equals(npwd, qpwd)){     //------------------------------两次新密码一样----------------------
				if(StringUtils.equals(o.getPassword(), opwd)){   //-----------------输入的原密码正确---------------------	
					o.setPassword(npwd);	
					dao.update(o);
					res=1;
				}else{
					res=2;//密码不正确
				}
			}else{
				res=3;//两次密码不一致
			}
		}
		return res;
	}

	@Override
	public int updatebj(CpUser cp) {
		if(dao.updatebj(cp)>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int updatesj(CpUser cp) {
		if(dao.updatesj(cp)>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int updatesj1(CpUser cp) {
		if(dao.updatesj1(cp)>0){
			return 1;
		}
		return 0;
	}
	@Override
	public List<CpUser> findcpnums(String hosnum) {
		return dao.findcpnums(hosnum);
	}
}
