package com.ehinfo.hr.service.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.repository.base.BaseDao;

public class BaseServiceImp<T> implements BaseService<T>{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public void insert(T o) {
		baseDao.insert(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}
	
	@Override
	public void deleteBatch(List<T> os){
		baseDao.deleteBatch(os);
	}
	
	@Override
	public void insertBatch(List<T> os){
		baseDao.deleteBatch(os);
	}
	
	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		page.setResults(baseDao.findByPage(o, page));
		return page;
	}

	@Override
	public int count(T o) {
		return baseDao.count(o);
	}

	@Override
	public T findById(T o) {
		return baseDao.findById(o);
	}

	@Override
	public List<T> findByPid(String  o) {
		return baseDao.findByPid(o);
	}

	

	@Override
	public List<T> findAduitByPid(String o) {
		return baseDao.findAduitByPid(o);
	}

	@Override
	public T getValidByPid(T o) {
		return baseDao.getValidByPid(o);
	}

	@Override
	public String minActDate(T o) {
		// TODO Auto-generated method stub
		return baseDao.minActDate(o);
	}
	
}
