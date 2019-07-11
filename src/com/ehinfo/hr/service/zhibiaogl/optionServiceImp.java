package com.ehinfo.hr.service.zhibiaogl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiaogl.OptionName;
import com.ehinfo.hr.repository.zhibiaogl.optionDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("optionService")
public class optionServiceImp extends BaseServiceImp<OptionName> implements optionService{
	@Autowired
	private optionDao dao;

	@Override
	public List<OptionName> getOption(String option,String hosnum) {
		return dao.getOption(option,hosnum);
	}

	@Override
	public void deleteOriginal(String hosnum, String option_name) {
		dao.deleteOriginal(hosnum,option_name);
	}

	@Override
	public void insertMulti(String[] chk, String option_name) {
		dao.insertMulti(chk,option_name);
	}
	
}
