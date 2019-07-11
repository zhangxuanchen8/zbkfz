package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiao.zbkwh;
import com.ehinfo.hr.repository.zhibiao.zbkwhDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("zbkwhService")
public class zbkwhServiceImp extends BaseServiceImp<zbkwh> implements zbkwhService{
	@Autowired
	private zbkwhDao dao;
	public int deletelist(List<String> list){
		int res=0;
		dao.deletelist(list);
		res=1;
		return res;
	}
	public List<zbkwh> findlist(String[] list){
		return dao.findlist(list);
	}
}
