package com.ehinfo.hr.service.system.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.SysDict;
import com.ehinfo.hr.repository.system.dict.SysDictDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("SysDictService")
public class SysDictServiceImp extends BaseServiceImp<SysDict> implements SysDictService {
	@Autowired
	private SysDictDao dao;
	
	@Override
	public List<SysDict> findScopeToTree(String hosnum, String nodecode) {
		return dao.findScopeToTree(hosnum,nodecode);
	}

	@Override
	@Transactional
	public Page<SysDict> findSysDictByPage(Page<SysDict> page,String scope, String code, String type, String hosnum, String nodecode) {
		SysDict dict = new SysDict();
		dict.setHosnum(hosnum);
	
		if("1".equals(type)){
			page.setResults(dao.findSysDictByPage(page,dict,code));
		}else{
			dict.setScope(scope);
			page.setResults(dao.findSysDictByPage(page,dict,code));
		}
		return page;
	}

	@Override
	public List<String> getSysnames(String hosnum) {
		return dao.getSysnames(hosnum);
	}

	@Override
	public List<String> getScopes() {
		return dao.getScopes();
	}

	@Override
	public List<SysDict> checkSysDicts(SysDict dict) {
		return dao.checkSysDicts(dict);
	}

	@Override
	public int delSysdict(SysDict dict) {
		int res=0;
		try {
			dao.delete(dict);
		} catch (Exception e) {
			res=1;
		}
		return res;
	}

	@Override
	public SysDict findByName(SysDict dict) {
		// TODO Auto-generated method stub
		return dao.findByName(dict);
	}

}
