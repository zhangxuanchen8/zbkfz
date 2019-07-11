package com.ehinfo.hr.service.declare;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.entity.declare.Declare;
import com.ehinfo.hr.repository.declare.DeclareDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("DeclareService")
public class DeclareServiceImpl extends BaseServiceImp<Declare> implements DeclareService {

	@Autowired
	DeclareDao dao;

	@Override
	@Transactional
	public List<Declare> addList(List<Declare> emps) {

		// 判断是否存在于数据
		Iterator<Declare> iterator = emps.iterator();
		while (iterator.hasNext()) {
			Declare declare = iterator.next();
			Declare decl = dao.getByPidYear(declare);
			if (decl != null) {
				if (decl.getDeclare_state().equals(Declare.DECLARE_STATE1)
						|| decl.getDeclare_state().equals(Declare.DECLARE_STATE2))
					iterator.remove();
				else
					declare.setDeclare_state(Declare.DECLARE_STATE2);
					declare.setD_id(decl.getD_id());
					dao.updateDate(declare);
			} else {
				dao.insert(declare);
			}
		}
		return emps;
	}
	@Override
	public Declare getByPidYear(Declare declare){
		return dao.getByPidYear(declare);}
	
	@Override
	public Declare addOne(Declare declare) {
		Declare decl = dao.getByPidYear(declare);
		if (decl != null) {
			if (decl.getDeclare_state().equals(Declare.DECLARE_STATE1)
					|| decl.getDeclare_state().equals(Declare.DECLARE_STATE2))
				return null;
			else
				declare.setDeclare_state(Declare.DECLARE_STATE2);
				declare.setD_id(decl.getD_id());
				dao.updateDate(declare);
		} else {
			dao.insert(declare);
		}
		return declare;
	}
	/**
	 * 获取数据数据库的年份
	 */
	@Override
	public List<Declare> getYear() {
		return dao.getYear();
	}

    

}
