package com.ehinfo.hr.service.system.tool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.tool.ErroMsg;
import com.ehinfo.hr.repository.system.tool.ErroMsgDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("ErroMsgService")
public class ErroMsgServiceImp extends BaseServiceImp<ErroMsg> implements ErroMsgService {
	@Autowired
	private ErroMsgDao ErroMsgDao;
	@Override
	public List<ErroMsg> getErroMsg(List<ErroMsg> erlist) {
		
		return ErroMsgDao.getErroMsg(erlist);
	}

	@Override
	public void insert(ErroMsg o) {
		
	}

	@Override
	public void delete(ErroMsg o) {
		
	}

	@Override
	public void deleteBatch(List<ErroMsg> os) {
		
	}

	@Override
	public void update(ErroMsg o) {
		
	}

	@Override
	public List<ErroMsg> find(ErroMsg o) {
		return null;
	}

	@Override
	public Page<ErroMsg> findByPage(ErroMsg o, Page<ErroMsg> page) {
		return null;
	}

	@Override
	public int count(ErroMsg o) {
		return 0;
	}

	@Override
	public List<ErroMsg> getErroMsg2(List<ErroMsg> erlist) {
		return ErroMsgDao.getErroMsg2(erlist);
	}

}
