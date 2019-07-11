package com.ehinfo.hr.service.zhibiaogl;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.cost;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;
import com.ehinfo.hr.service.base.BaseService;

import net.sf.json.JSONArray;

public interface costService extends BaseService<fenpeiks>{
//		public Page<cost> detailFind(String openmonth, String ksname, Page<cost> p);
//		public Page<cost> FindLastCost(String deptid,Page<cost> p);
		public List<cost> findDeptList(String hosnum);
		public String saveEmps(JSONArray json);
		public void affirm(String person_dept);
		public void returnaffirm(String person_dept);
		public List<cost> ifaffirm(String person_dept);
		
		
		public List<String> findHead();
		public List<String> findOption();
		public Page<fenpeiks> findOptionValue(String openmonth,String ksname,String hosnum, Page<fenpeiks> p);
		
		public Page<fenpeiks> findLastValue(String person_dept,String tjrqs,String tjrqe, Page<fenpeiks> p);
		
		
		//绩效管理
		//绩效查看
		public List<String> findHead_jx();
		public List<String> findOption_jx();
		
}
