package com.ehinfo.hr.service.declare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.entity.system.user.CpUser;
import com.ehinfo.hr.repository.declare.PersonalRecordDao;
import com.ehinfo.hr.repository.system.user.CpUserDao;
import com.ehinfo.hr.service.base.BaseServiceImp;


@Service("PersonalRecordService")
public class PersonalRecordServiceImp extends BaseServiceImp<PersonalRecord>  implements PersonalRecordService{
	
	@Autowired
	private PersonalRecordDao personalRecordDao;
	@Autowired
	private CpUserDao cpuserdao;
	
	@Override
	public Page<PersonalRecord> getTpZjPersonalList(Page<PersonalRecord> p,String zyz_zj_no,String yearnum,String check,String unitid){
		p.setResults(personalRecordDao.getTpZjPersonalList(p,zyz_zj_no,yearnum,check,unitid));
		return p;
	}
	
	@Override
	public Page<PersonalRecord> getTpPersonalList(Page<PersonalRecord> p,String opercode, String recordno, String zyzbh,String unitid) {
		p.setResults(personalRecordDao.getTpPersonalList(p,opercode, recordno, zyzbh,unitid));
		return p;
	}
	
	@Override
	public List<PersonalRecord> getPersonalRecordList() {
		return personalRecordDao.getPersonalRecordList();
		
	}

	@Override
	public List<PersonalRecord> getPersonList(String recordNo,String xm) {
		return personalRecordDao.getPersonList(recordNo,xm);
	}

	@Override
	public Page<PersonalRecord> repAuditList(Page<PersonalRecord> p,String recordNo, String wsps,String xcszy,String option02,String hosnum) {
		p.setResults(personalRecordDao.repAuditList(p,recordNo, wsps,xcszy,option02,hosnum));
		return p;
	}

	@Override
	public List<PersonalRecord> revSenior(String recordNo, String wsps,String xcszy,String option02,String unitid) {
		return personalRecordDao.revSenior(recordNo, wsps,xcszy,option02,unitid);
	}
		
	@Override
	public List<PersonalRecord> thrReview(String recordNo, String wsps,String xcszy,String option02) {
		return personalRecordDao.thrReview(recordNo, wsps,xcszy,option02);
	}

	@Override
	public List<PersonalRecord> addexpGroup_person(String yearnum, String xcszy,String option02,String recordno) {
		return personalRecordDao.addexpGroup_person(yearnum,xcszy,option02,recordno);
	}

	@Override
	public Page<PersonalRecord> getZyzResRecordList(Page<PersonalRecord> p,String yearnum, String option02) {
		p.setResults(personalRecordDao.getZyzResRecordList(p,yearnum, option02));
		return p;
	}

	@Override
	public List<PersonalRecord> impRecordList(String yearnum, String option02) {
		return personalRecordDao.impRecordList(yearnum, option02);
	}

	@Override
	public List<PersonalRecord> getShouldVote(String option02,String recordno,String unitid) {
		return personalRecordDao.getShouldVote(option02,recordno,unitid);
	}

	@Override
	public List<PersonalRecord> getPersonnelList(String recordno,String unitid) {
		return personalRecordDao.getPersonnelList(recordno,unitid);
	}

	@Override
	public List<PersonalRecord> selPeople(String recordno,String unitid) {
		return personalRecordDao.selPeople(recordno,unitid);
	}

	@Override
	public List<PersonalRecord> voteResultsList(String recordno, String option02,String unitid) {
		return personalRecordDao.voteResultsList(recordno, option02,unitid);
	}

	@Override
	public List<PersonalRecord> getGpetpAsPersonal(String recordno, String zyzbh) {
		return personalRecordDao.getGpetpAsPersonal(recordno, zyzbh);
	}

	@Override
	public List<PersonalRecord> getRevImpList(String yearnum, String wsps) {
		return personalRecordDao.getRevImpList(yearnum, wsps);
	}

	@Override
	public List<PersonalRecord> writRecord_zy(String yearnum, String wsps,String unitid) {
		return personalRecordDao.writRecord_zy(yearnum, wsps,unitid);
	}
	
	@Override
	public List<PersonalRecord> writRecord_zyz(String yearnum, String wsps) {
		return personalRecordDao.writRecord_zyz(yearnum, wsps);
	}
	
	@Override
	public List<PersonalRecord> reviewResults(String recordno, String wsps,String unitid) {
		return personalRecordDao.reviewResults(recordno, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> perStatistical_zy(String yearnum, String wsps,String unitid) {
		return personalRecordDao.perStatistical_zy(yearnum, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> perStatistical_zy2ByZy(String recordno, String wsps,String unitid) {
		return personalRecordDao.perStatistical_zy2ByZy(recordno, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> perStatistical_zy2ByZyz(String recordno, String wsps,String unitid) {
		return personalRecordDao.perStatistical_zy2ByZyz(recordno, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> perStatistical_sbdwByZy(String recordno, String wsps,String unitid) {
		return personalRecordDao.perStatistical_sbdwByZy(recordno, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> perStatistical_sbdwByZyz(String recordno, String wsps,String unitid) {
		return personalRecordDao.perStatistical_sbdwByZyz(recordno, wsps,unitid);
	}

	@Override
	public List<PersonalRecord> reviewStatisticalByZy(String recordno,String unitid) {
		return personalRecordDao.reviewStatisticalByZy(recordno,unitid);
	}

	@Override
	public List<PersonalRecord> reviewStatisticalByZyz(String recordno,String unitid) {
		return personalRecordDao.reviewStatisticalByZyz(recordno, unitid);
	}

	@Override
	public List<PersonalRecord> reviewAnalysis(String recordno,String unitid) {
		return personalRecordDao.reviewAnalysis(recordno,unitid);
	}
	
	@Override
	public List<PersonalRecord> writtenPercent(String recordno, String wsps,String unitid) {
		return personalRecordDao.writtenPercent(recordno, wsps,unitid);
	}
	
	@Override
	public List<PersonalRecord> reviewPercent(String recordno, String wsps,String unitid) {
		return personalRecordDao.reviewPercent(recordno, wsps,unitid);
	}
	
	@Override
	public List<PersonalRecord> analysis(String recordno, String wsps,String unitid) {
		return personalRecordDao.analysis(recordno, wsps,unitid);
	}
	
	@Override
	public List<PersonalRecord> getPerList(String recordno) {
		return personalRecordDao.getPerList(recordno);
	}

	@Override
	public void upPerWsps(String wsps, String recordno) {
		personalRecordDao.upPerWsps(wsps, recordno);
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 导入excel数据--导入笔试成绩
	 * @param xm 姓名
	 * @param mscj 成绩
	 * @return
	 */
	@Override
	public void upPersonalRecordByXm(String xm, Integer mscj) {
		personalRecordDao.upPersonalRecordByXm(xm, mscj);
	}

	@Override
	public PersonalRecord findByNo(String recordno) {
		return personalRecordDao.findByNo(recordno);
	}

	@Override
	public List<PersonalRecord> getNewYearPersonList(String year, String option02, String option03,String unitid) {
		return personalRecordDao.getNewYearPersonList(year, option02, option03,unitid);
	}

	@Override
	public PersonalRecord findZyzbh(String recordno) {
		return personalRecordDao.findZyzbh(recordno);
	}

	@Override
	public void upPerZjTpjg(String cxrs, String zcrs, String fdrs, String recordno) {
		personalRecordDao.upPerZjTpjg(cxrs, zcrs, fdrs, recordno);
	}

	@Override
	public void upPerGpwTpjg(String zcrs1, String fdrs1, String recordno) {
		personalRecordDao.upPerGpwTpjg(zcrs1, fdrs1, recordno);
		
	}

	@Override
	public void upZjPwhzrs(String pwhzrs, String recordno) {
		personalRecordDao.upZjPwhzrs(pwhzrs, recordno); 
	}

	@Override
	public void upGpwCxrs(String recordno, String cxrs1) {
		personalRecordDao.upGpwCxrs(recordno, cxrs1);
	}

	@Override
	public List<PersonalRecord> getNoZyfzPer(String year, String unitid) {
		return personalRecordDao.getNoZyfzPer(year, unitid);
	}

	@Override
	public String getPerseq() {
		return  personalRecordDao.getPerseq();
	}

	@Override
	@Transactional
	public List<String> insertBatch1(List<PersonalRecord> list) {
		CpUser cp = new CpUser();
		List<String> name = new ArrayList();
		UuidUtil uuid = new UuidUtil();
		for(PersonalRecord o:list){
			
				List<PersonalRecord> p = new ArrayList<PersonalRecord>();
				p=personalRecordDao.findByid(o);
				if(p.size()>0){
					name.add(o.getXm());
				}else{
					cp.setHosnum(o.getUnitid());
					cp.setId(uuid.get32UUID());
					cp.setUser_key(o.getIdcard());
					cp.setUser_id(o.getRecordno());
					cp.setName(o.getXm());
					cp.setPassword(o.getIdcard().substring(o.getIdcard().length()-6));
					cp.setIdcard(o.getIdcard());
					cp.setDel_sign("0");
					cp.setScbj("N");
					cpuserdao.insert(cp);
					personalRecordDao.insert1(o);
				}
			
			
		}
		return name;
	}
	@Override
	public Page<PersonalRecord> findByPage1(PersonalRecord o, Page<PersonalRecord> page) {
		page.setResults(personalRecordDao.findByPage1(o, page));
		return page;
	}
	public int deletelist(List<String> list){
		int res=0;
		personalRecordDao.deletelist(list);
		cpuserdao.deletelist(list);
		res=1;
		return res;
	}
	
	@Override
	@Transactional
	public void updatescryp(List<String> list,String state) {
		personalRecordDao.updatescryp(list,state);
	}
	
	@Override
	public List<PersonalRecord> getppjgper(PersonalRecord p) {
		return personalRecordDao.getppjgper(p);
	}
	@Override
	public List<PersonalRecord> getppjgpers(PersonalRecord p) {
		return personalRecordDao.getppjgpers(p);
	}
	@Override
	public Map<String, String> getTj(PersonalRecord o) {
		return personalRecordDao.getTj(o);
	}
	@Override
	public List<PersonalRecord> getzyry(PersonalRecord p) {
		return personalRecordDao.getzyry(p);
	}
	@Override
	public List<PersonalRecord> getppjgpery(PersonalRecord p) {
		return personalRecordDao.getppjgpery(p);
	}
	@Override
	public List<PersonalRecord> getppjgperw(PersonalRecord p) {
		return personalRecordDao.getppjgperw(p);
	}
}
