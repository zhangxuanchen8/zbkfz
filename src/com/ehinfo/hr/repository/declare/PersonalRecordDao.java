package com.ehinfo.hr.repository.declare;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.declare.PersonalRecord;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface PersonalRecordDao extends BaseDao<PersonalRecord>  {
	
	public List<PersonalRecord> getTpZjPersonalList(Page<PersonalRecord> p,@Param("zyz_zj_no")String zyz_zj_no,@Param("yearnum")String yearnum,@Param("check")String check,@Param("unitid")String unitid);
	public List<PersonalRecord> getTpPersonalList(Page<PersonalRecord> p,@Param("opercode")String opercode,@Param("recordno")String recordno,@Param("zyzbh")String zyzbh,@Param("unitid")String unitid);
	
	public List<PersonalRecord> getPersonalRecordList();
	
	public List<PersonalRecord> getPersonList(@Param("recordno")String recordno,@Param("xm")String xm);
	
	public List<PersonalRecord> repAuditList(Page<PersonalRecord> p,@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("xcszy")String xcszy,@Param("option02")String option02,@Param("hosnum")String hosnum);
	public List<PersonalRecord> revSenior(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("xcszy")String xcszy,@Param("option02")String option02,@Param("unitid")String unitid);
	public List<PersonalRecord> thrReview(@Param("recordno")String recordno, @Param("wsps")String wsps,@Param("xcszy")String xcszy,@Param("option02")String option02);
	public List<PersonalRecord> addexpGroup_person(@Param("yearnum")String yearnum, @Param("xcszy")String xcszy,@Param("option02")String option02,@Param("recordno")String recordno);
	public List<PersonalRecord> getZyzResRecordList(Page<PersonalRecord> p,@Param("yearnum")String yearnum,@Param("option02")String option02);
	public List<PersonalRecord> impRecordList(@Param("yearnum")String yearnum,@Param("option02")String option02);

	public List<PersonalRecord> getShouldVote(@Param("option02")String option02,@Param("recordno")String recordno,@Param("unitid")String unitid);
	public List<PersonalRecord> getPersonnelList(@Param("recordno")String recordno,@Param("unitid")String unitid);
	public List<PersonalRecord> selPeople(@Param("recordno")String recordno,@Param("unitid")String unitid);

	public List<PersonalRecord> getGpetpAsPersonal(@Param("recordno")String recordno,@Param("zyzbh")String zyzbh);
	public List<PersonalRecord> voteResultsList(@Param("recordno")String recordno,@Param("option02")String option02,@Param("unitid")String unitid);
	public List<PersonalRecord> getRevImpList(@Param("yearnum")String yearnum,@Param("wsps")String wsps);

	public List<PersonalRecord> writRecord_zy(@Param("yearnum")String yearnum,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> writRecord_zyz(@Param("yearnum")String yearnum,@Param("wsps")String wsps);
	public List<PersonalRecord> reviewResults(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	
	public List<PersonalRecord> perStatistical_zy(@Param("yearnum")String yearnum,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> perStatistical_zy2ByZy(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> perStatistical_zy2ByZyz(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> perStatistical_sbdwByZy(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> perStatistical_sbdwByZyz(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> reviewStatisticalByZy(@Param("recordno")String recordno,@Param("unitid")String unitid);
	public List<PersonalRecord> reviewStatisticalByZyz(@Param("recordno")String recordno,@Param("unitid")String unitid);
	public List<PersonalRecord> reviewAnalysis(@Param("recordno")String recordno,@Param("unitid")String unitid);
	public List<PersonalRecord> writtenPercent(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> reviewPercent(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	public List<PersonalRecord> analysis(@Param("recordno")String recordno,@Param("wsps")String wsps,@Param("unitid")String unitid);
	
	public List<PersonalRecord> getPerList(@Param("recordno")String recordno);
	public void upPerWsps(@Param("wsps")String wsps,@Param("recordno")String recordno);
	
	public PersonalRecord findByNo(@Param("recordno")String recordno);
	
	/**
	 * 导入excel数据--导入笔试成绩
	 * @param xm 姓名
	 * @param mscj 成绩
	 * @return
	 */
	public void upPersonalRecordByXm(@Param("xm")String xm,@Param("mscj")Integer mscj);
	
	public List<PersonalRecord> getNewYearPersonList(@Param("year")String year,@Param("option02")String option02,@Param("option03")String option03,@Param("unitid")String unitid);
	
	PersonalRecord findZyzbh(@Param("recordno")String recordno);
	
	public void upPerZjTpjg(@Param("cxrs")String cxrs,@Param("zcrs")String zcrs,@Param("fdrs")String fdrs,@Param("recordno")String recordno);
	public void upPerGpwTpjg(@Param("zcrs1")String zcrs1,@Param("fdrs1")String fdrs1,@Param("recordno")String recordno);

	public void upZjPwhzrs(@Param("pwhzrs")String pwhzrs, @Param("recordno")String recordno);
	
	public void upGpwCxrs(@Param("recordno")String recordno,  @Param("cxrs1")String cxrs1);
	
	
	public List<PersonalRecord> getNoZyfzPer(@Param("year")String year,@Param("unitid")String unitid);
	public String getPerseq();
	public void insert1(PersonalRecord o);
	public List<PersonalRecord> findByPage1(@Param("param")PersonalRecord o,@Param("page")Page<PersonalRecord> page);
	
	public List<PersonalRecord>findByid(PersonalRecord p);
	public void deletelist(@Param("slist")List<String> list);
	public void updatescryp(@Param("slist")List<String> list,@Param("state")String state);
	public List<PersonalRecord> getppjgper(PersonalRecord p);
	public Map<String, String> getTj(PersonalRecord o);
	public List<PersonalRecord> getppjgpers(PersonalRecord p);
	public List<PersonalRecord> getzyry(PersonalRecord p);
	public List<PersonalRecord> getppjgpery(PersonalRecord p);
	public List<PersonalRecord> getppjgperw(PersonalRecord p);
	
	public void updategdper(@Param("pid") String pid);
}
