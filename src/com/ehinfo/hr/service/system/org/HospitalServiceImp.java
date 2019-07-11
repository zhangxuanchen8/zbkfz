package com.ehinfo.hr.service.system.org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.org.Hospital;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.repository.system.org.HospitalDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("HospitalService")
public class HospitalServiceImp extends BaseServiceImp<Hospital> implements HospitalService {
	@Autowired
	private HospitalDao dao;

	@Override
	@Transactional
	public List<Hospital> getHospitalTreeNodes(){
		return dao.getHospitalTreeNodes();
	}

	@Override
	public Page<Hospital> findHospitalByPage(Page<Hospital> page, String supunit, String code) {
		page.setResults(dao.findHospitalByPage(page, supunit, code));
		return page;
	}

	@Override
	@Transactional
	public int delHospital(Hospital hospital) {
		int res=0;
		boolean delFlag = true;
		String nodecode = hospital.getNodecode();
		Org org = dao.findDept(hospital);
		String oid = org.getId();
		List<Org> findOrgChilds=new ArrayList<Org>();
		List<Hospital> list = dao.findChild(nodecode);
		if(org!=null){
		 findOrgChilds=dao.findAndChild(oid);
		}
		if(list.size()>0||findOrgChilds.size()>0){
			delFlag = false;
			res=findOrgChilds.size();
		}
		if(delFlag){
			dao.deleteDept(org);
			dao.delete(hospital);
		}
		return res;
	}

	@Override
	public List<BaseDict> selectDict(BaseDict dict) {
		return dao.selectDict(dict);
	}
	@Override
	@Transactional
	public void insert(Hospital hospital) {
		dao.insert(hospital);
		Org parentDept = new Org();
		parentDept = dao.findParentDept(hospital);
		hospital.setSupunit(parentDept.getId());
		dao.insertDept(hospital);
	}
	@Transactional
	public void update(Hospital hospital) {
		dao.update(hospital);
		dao.updateDept(hospital);
	}

	@Override
	public List<Hospital> findAllHospital() {
		return dao.findAllHospital();
	}
	
	@Override
	public Hospital findByHosnum(Hospital hos) {
		return dao.findByHosnum(hos);
	}
	@Override
	public Hospital findByHosname(Hospital hos) {
		return dao.findByHosname(hos);
	}
}
