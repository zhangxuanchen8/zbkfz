package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.entity.zhibiao.Zbkavgscore;
import com.ehinfo.hr.repository.zhibiao.Choose_DeptDao;
import com.ehinfo.hr.repository.zhibiao.ZbkavgscoreDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("ZbkavgscoreService")
public class ZbkavgscoreServiceImp extends BaseServiceImp<Zbkavgscore> implements ZbkavgscoreService{
    @Autowired
    private ZbkavgscoreDao dao;
	
	@Override
	public int deletelist(List<String> list) {
		dao.deletelist(list);
		return 1;
	}


}
