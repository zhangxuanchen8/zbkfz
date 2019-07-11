package com.ehinfo.hr.repository.zhibiaogl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.cost;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface costDao extends BaseDao<fenpeiks>{
//	public List<cost> detailFind(@Param("openmonth")String openmonth,@Param("ksname")String ksname,@Param("page")Page<cost> p);
//	public List<cost> FindLastCost(@Param("deptid")String deptid,@Param("page")Page<cost> page);
	public List<cost> findDeptList(@Param("hosnum")String hosnum);
	public void deletekslast(@Param("costdate")Date costdate,@Param("deptid")String deptid);
	public void affirm(@Param("person_dept")String person_dept);
	public void returnaffirm(@Param("person_dept")String person_dept);
	public List<cost> isaffirm(@Param("person_dept")String person_dept);
	
	
	public List<String> findHead();
	public List<String> findOption();
	public List<fenpeiks> findOptionValue(@Param("openmonth")String openmonth,@Param("ksname")String ksname,@Param("hosnum")String hosnum,@Param("list")List<String> list,@Param("page")Page<fenpeiks> p);
	public List<fenpeiks> findLastValue(@Param("person_dept")String person_dept,@Param("tjrqs")String tjrqs,@Param("tjrqe")String tjrqe, @Param("list")List<String> list,@Param("page")Page<fenpeiks> p);
	public void updateoption(@Param("param")List<fenpeiks> value, @Param("fenpei")fenpeiks fenpeiks);
	
	//绩效查看
	public List<String> findHead_jx();
	public List<String> findOption_jx();
	
}
