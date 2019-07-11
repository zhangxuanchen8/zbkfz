package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
import com.ehinfo.hr.repository.zhibiao.ryzhibiaokuDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("ryzhibiaokuService")
public class ryzhibiaokuServiceImp extends BaseServiceImp<ryzbk> implements ryzhibiaokuService{
	@Autowired
	private ryzhibiaokuDao dao;
	@Override
	public List<ryzbk> findpeoplelist(String hosnum) {
		return dao.findpeoplelist(hosnum);
	}
	@Override
	public List<ryzbk> findpeoplename(String id) {
		return dao.findpeoplename(id);
	}
	@Override
	public Page<ryzbk> findpeoplepage(ryzbk o, Page<ryzbk> page) {
		page.setResults( dao.findpeoplepage(o, page));
		return page;
	}
	@Override
	public List<ryzbk> findzbkren(String hosnum) {
		return dao.findzbkren(hosnum);
	}
	
}
