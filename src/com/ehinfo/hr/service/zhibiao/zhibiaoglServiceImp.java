package com.ehinfo.hr.service.zhibiao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.repository.zhibiao.zhibiaoglDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("zhibiaoglService")
public class zhibiaoglServiceImp extends BaseServiceImp<zbkscore> implements zhibiaoglService{
	@Autowired
	private zhibiaoglDao dao;

	@Override
	public List<zbkscore> findyear(String year) {
		return dao.findyear(year);
	}

	@Override
	public int updatetjbz(zbkscore score) {
		dao.updatetjbz(score);
		return 1;
	}

	@Override
	public Page<zbkscore> findtjym(zbkscore score, Page<zbkscore> page) {
		page.setResults( dao.findtjym(score, page));
		return page;
	}

	@Override
	public List<zbkscore> findtjfszf(zbkscore score) {
		
		return dao.findtjfszf(score);
	}
	@Override
	public int insert_score(List<zbkscore> list) {
		int res=0;
	    dao.insert_score(list);
	    res=1;
	    return res;
	}
	@Override
	public int delete_score(String id,List<zbkscore> list) {
		int res=0;
	    dao.delete_score(id,list);
	    res=1;
	    return res;
	}

	@Override
	public List<zbkscore> findzbkrenyear() {
		return dao.findzbkrenyear();
	}
	@Override
	public List<zbkscore> findnums(zbkscore z) {
		return dao.findnums(z);
	}
	@Override
	public void updatescrys(List<String> list,String state) {
		dao.updatescrys(list, state);
	}

	@Override
	public List<zbkscore> findPid(String id,String dept) {
		return dao.findPid(id,dept);
	}

	@Override
	public List<zbkscore> findmaxscore(String id,String dept) {
		return dao.findmaxscore(id,dept);
	}
	@Override
	public List<zbkscore> findmax(zbkscore z) {
		return dao.findmax(z);
	}
	@Override
	public List<zbkscore> findsum(zbkscore z) {
		return dao.findsum(z);
	}
	@Override
	public List<zbkscore> findsmax(zbkscore z) {
		return dao.findsmax(z);
	}
	@Override
	public List<zbkscore> findssum(zbkscore z) {
		return dao.findssum(z);
	}
	@Override
	public void insert_scores(List<zbkscore> list) {
	    dao.insert_scores(list);
	}

	@Override
	public void delete_scores(zbkscore score) {
		dao.delete_scores(score);
	}
	@Override
	public void insert_scoress(List<zbkscore> list) {
	    dao.insert_scoress(list);
	}

	@Override
	public void delete_scoress(zbkscore score) {
		dao.delete_scoress(score);
	}

	@Override
	public zbkscore findsbyitemid(zbkscore score) {
		return dao.findsbyitemid(score);
	}
	@Override
	public void deleteitemid(zbkscore score) {
		dao.deleteitemid(score);
	}
	@Override
	public zbkscore findsbyitemids(zbkscore score) {
		return dao.findsbyitemids(score);
	}
	@Override
	public List<zbkscore> findssumfin(zbkscore z) {
		return dao.findssumfin(z);
	}

	@Override
	public String findsumscore(String itemid, String id) {
		return dao.findsumscore(itemid, id);
	}
	@Override
	public void updatess(zbkscore score) {
		dao.updatess(score);
	}
	@Override
	public zbkscore findsbyname(zbkscore score) {
		return dao.findsbyname(score);
	}
	@Override
	public List<zbkscore> findsbynames(zbkscore z) {
		return dao.findsbynames(z);
	}
	@Override
	public List<zbkscore> findsbydept(zbkscore z) {
		return dao.findsbydept(z);
	}
	@Override
	public List<zbkscore> findnumstj(zbkscore z) {
		return dao.findnumstj(z);
	}
}
