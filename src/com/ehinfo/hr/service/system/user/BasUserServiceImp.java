package com.ehinfo.hr.service.system.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.user.BasUser;
import com.ehinfo.hr.repository.system.org.RoleDao;
import com.ehinfo.hr.repository.system.user.BasUserDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("BasUserService")
public class BasUserServiceImp extends BaseServiceImp<BasUser> implements BasUserService  {

	@Autowired
	private BasUserDao dao;
	
	
	@Override
	@Transactional
	public String insert1(BasUser o) {
		
		
		//----------------------------判断是add还是修改------
		if(o.getId()==null||"".endsWith(o.getId())){
			o.setId(UUID.randomUUID().toString());
		}else{
			dao.delete(o);
		}
		
		List<BasUser> list = dao.find(o);
		if(list.size()>0){
			
			return "0";
		}
		
		//---------------------这里把账户和角色的关联加上去---------------------------
		if(o.getRoles()!=null&&!"".equals(o.getRoles())){
			String [] roles = o.getRoles().split(",");
			BasUser user =  new BasUser();
			user.setId(o.getId());
			user.setPerson_dept(o.getPerson_dept());
			dao.deleteRole(user);
			
			
			
			
			
			
			for(int i=0;i<roles.length;i++){
				user = new BasUser();
				user.setId(o.getId());
				user.setPerson_dept(o.getPerson_dept());
				user.setRoles(roles[i]);
				user.setHosnum(o.getHosnum());
				dao.insertRole(user);
			}
		}
		dao.insert(o);
		
		return "1";
	}
	
	
	
  
	
	 public BasUser findFormatByLoginName(String loginName){
			BasUser a = null;
			try {
				a= dao.findFormatByLoginName(loginName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return a;
		}
	 
	

	
	
	
	//----------------------重置密码前的密码核对--------------------------
	@Override
	public int preResetPwd(String opwd,String npwd,String qpwd,BasUser o) {
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

	
	
	
	
	//------------------------删除用户，不允许删除，置个删除标记-------------------------------
	@Override
	@Transactional
	public void deleteBasUser(BasUser o) {
		//事务删除
		dao.delete(o);
		
	}

	
	//------------------------批量删除用户，不允许删除，置个删除标记-------------------------------
	@Override	
	@Transactional
	public void deleteBatchBasUser(String chks) {
		//事务删除	
		if(StringUtils.isNotBlank(chks)){
			String[] chk =chks.split(",");
			List<BasUser> list=new ArrayList<BasUser>();
			for(String s:chk){
				BasUser sd=new BasUser();
				sd.setId(s);
				list.add(sd);
			}
			dao.deleteBatch(list);
			
		}
	
	}



	@Override
	public BasUser findById(BasUser user) {
		// TODO Auto-generated method stub
		return dao.findById(user);
	}



	@Override
	public String getAllRolesId(BasUser user) {
		// TODO Auto-generated method stub
		return dao.getAllRolesId(user);
	}



	@Override
	public Page<BasUser> findByPageDWZ(BasUser user, Page<BasUser> page, List<BasUser> dept) {
		// TODO Auto-generated method stub
		
		page.setResults(dao.findByPageDWZ(user, page,dept));
		
		return page;
	}





	@Override
	@Transactional
	public List<BasUser> findUserByRoleAndHos(String RoleName, String hosnum){
		return dao.findUserByRoleAndHos(RoleName,hosnum);
	}	
}
