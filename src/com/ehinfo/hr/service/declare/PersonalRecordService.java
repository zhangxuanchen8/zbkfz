package com.ehinfo.hr.service.declare;

import java.util.List;
import java.util.Map;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.service.base.BaseService;


public interface PersonalRecordService extends BaseService<PersonalRecord>  {
	
	public Page<PersonalRecord> getTpZjPersonalList(Page<PersonalRecord> p,String zyz_zj_no,String yearnum,String check,String unitid);
	public Page<PersonalRecord> getTpPersonalList(Page<PersonalRecord> p,String opercode,String recordno,String zyzbh,String unitid); 
	
	public List<PersonalRecord> getPersonalRecordList();
	public List<PersonalRecord> getPersonList(String recordno,String xm);
	public Page<PersonalRecord> repAuditList(Page<PersonalRecord> p,String recordno,String wsps,String xcszy,String option02,String unitid);
	public List<PersonalRecord> revSenior(String recordno,String wsps,String xcszy,String option02,String unitid);
	public List<PersonalRecord> thrReview(String recordno,String wsps,String xcszy,String option02);
	public List<PersonalRecord> addexpGroup_person(String yearnum, String xcszy,String option02,String recordno);
	public Page<PersonalRecord> getZyzResRecordList(Page<PersonalRecord> p,String yearnum,String option02);
	public List<PersonalRecord> impRecordList(String yearnum,String option02);
	
	public List<PersonalRecord> getShouldVote(String option02,String recordno,String unitid);
	/**
	 * 閲嶆姇浜哄憳閫夋嫨锛屾煡璇㈡墍鏈変汉鍛樹緵閫夋嫨
	 * @return
	 */
	public List<PersonalRecord> getPersonnelList(String recordno,String unitid);
	
	public List<PersonalRecord> selPeople(String recordno,String unitid);
	
	public List<PersonalRecord> getGpetpAsPersonal(String recordno,String zyzbh);
	public List<PersonalRecord> voteResultsList(String recordno,String option02,String unitid);
	public List<PersonalRecord> getRevImpList(String yearnum,String wsps);
	public List<PersonalRecord> writRecord_zy(String yearnum,String wsps,String unitid);
	public List<PersonalRecord> writRecord_zyz(String yearnum,String wsps);
	public List<PersonalRecord> reviewResults(String recordno,String wsps,String unitid);
	public List<PersonalRecord> perStatistical_zy(String yearnum,String wsps,String unitid);
	public List<PersonalRecord> perStatistical_zy2ByZy(String recordno,String wsps,String unitid);
	public List<PersonalRecord> perStatistical_zy2ByZyz(String recordno,String wsps,String unitid);
	public List<PersonalRecord> perStatistical_sbdwByZy(String recordno,String wsps,String unitid);
	public List<PersonalRecord> perStatistical_sbdwByZyz(String recordno,String wsps,String unitid);
	public List<PersonalRecord> reviewStatisticalByZy(String recordno,String unitid);
	public List<PersonalRecord> reviewStatisticalByZyz(String recordno,String unitid);
	public List<PersonalRecord> reviewAnalysis(String recordno,String unitid);
	public List<PersonalRecord> writtenPercent(String recordno,String wsps,String unitid);
	public List<PersonalRecord> reviewPercent(String recordno,String wsps,String unitid);
	public List<PersonalRecord> analysis(String recordno,String wsps,String unitid);
		
	public List<PersonalRecord> getPerList(String recordno);
	public void upPerWsps(String wsps,String recordno);

	PersonalRecord findByNo(String recordno);
	
	/**
	 * 瀵煎叆excel鏁版嵁--瀵煎叆绗旇瘯鎴愮哗
	 * @param xm 濮撳悕
	 * @param mscj 鎴愮哗
	 * @return
	 */
	public void upPersonalRecordByXm(String xm,Integer mscj);
	
	public List<PersonalRecord> getNewYearPersonList(String year,String option02,String option03,String unitid);
	
	PersonalRecord findZyzbh(String recordno);
	
	/**
	 * 鏇存柊涓撳鎶曠エ缁撴灉
	 */
	public void upPerZjTpjg(String cxrs,String zcrs,String fdrs,String recordno);
	/**
	 * 鏇存柊楂樿瘎濮旀姇绁ㄧ粨鏋�
	 */
	public void upPerGpwTpjg(String zcrs1,String fdrs1,String recordno);
	
	public void upZjPwhzrs(String pwhzrs,String recordno);
	
	public void upGpwCxrs(String recordno,String cxrs1);
	
	
	/**
	 * 鏌ヨ涓撲笟鍒嗙粍涓撲笟娌℃湁鍖呭惈鐨勪汉鍛�
	 * @param year
	 * @param unitid
	 * @return
	 */
	public List<PersonalRecord> getNoZyfzPer(String year,String unitid);
	
	String getPerseq();
	
	public Page<PersonalRecord> findByPage1(PersonalRecord p,Page<PersonalRecord> page);
	List<String> insertBatch1(List<PersonalRecord> list);
	public int deletelist(List<String> list);
	public void updatescryp(List<String> list,String state);
	public List<PersonalRecord> getppjgper(PersonalRecord p);
	public List<PersonalRecord> getppjgpers(PersonalRecord p);
	Map<String, String> getTj(PersonalRecord o);
	public List<PersonalRecord> getzyry(PersonalRecord p);
	
	public List<PersonalRecord> getppjgpery(PersonalRecord p);
	public List<PersonalRecord> getppjgperw(PersonalRecord p);
}
