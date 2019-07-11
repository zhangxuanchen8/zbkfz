package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.repository.zhibiao.Choose_DeptDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("Choose_DeptService")
public class Choose_DeptServiceImp extends BaseServiceImp<Choose_Dept> implements Choose_DeptService{
    @Autowired
    private Choose_DeptDao dao;
	
	@Override
	public int checkZ_name(Choose_Dept o) {
		int i = dao.checkZ_name(o);
		return i;
	}

	@Override
	public int deleteDept(Choose_Dept o) {
		int i = dao.deleteDept(o);
		return i;
	}

	@Override
	public List<Choose_Dept> getZhiBiaoGroupTree(Choose_Dept o) {
		return dao.getZhiBiaoGroupTree(o);
	}


}
