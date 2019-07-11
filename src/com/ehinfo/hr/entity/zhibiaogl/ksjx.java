package com.ehinfo.hr.entity.zhibiaogl;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("ksjx")
public class ksjx {
	private String id;
	private Date ksdate;
	private String deptname;
	private String deptid;
	private Double income;
	private Double expenditure;
	private Double depreciation;
	private Double surplus;
	private Double ticketnum;
	private Double clinic;
	private Double technology;
	private Double jx_num;
	private Double ks_num;
	private Double base_jx;
	private Double zy_jx;
	private Double other_jx;
	private Double ought_jx;
	private Double reward;
	private Double reality_jx;
	private Double person_num;
	private Double average_jx;
	private Date issue_date;
//	ID	N	VARCHAR2(40)	Y			
//	KSDATE	N	DATE	Y			日期
//	DEPTNAME	N	VARCHAR2(40)	Y			科室名称
//	DEPTID	N	VARCHAR2(40)	Y			科室id,一般数据库存的都是id吧
//	INCOME	N	NUMBER(8,2)	Y			收入
//	EXPENDITURE	N	NUMBER(8,2)	Y			支出
//	DEPRECIATION	N	NUMBER(8,2)	Y			折旧
//	SURPLUS	N	NUMBER(8,2)	Y			结余
//	TICKETNUM	N	NUMBER(2,2)	Y			开单率
//	CLINIC	N	NUMBER(8,2)	Y			临床科室提留
//	TECHNOLOGY	N	NUMBER(8,2)	Y			医技科室提留
//	JX_NUM	N	NUMBER(8,2)	Y			绩效基数
//	KS_NUM	N	NUMBER(8,2)	Y			科室系数
//	BASE_JX	N	NUMBER(8,2)	Y			基础绩效工资
//	ZY_JX	N	NUMBER(8,2)	Y			中医项目绩效
//	OTHER_JX	N	NUMBER(8,2)	Y			其他绩效项目
//	OUGHT_JX	N	NUMBER(8,2)	Y			应发绩效工资
//	REWARD	N	NUMBER(8,2)	Y			奖惩
//	REALITY_JX	N	NUMBER(8,2)	Y			实发绩效工资
//	PERSON_NUM	N	NUMBER	Y			人数
//	AVERAGE_JX	N	NUMBER(8,2)	Y			人均绩效
//	ISSUE_DATE	N	DATE	Y			下发时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getKsdate() {
		return ksdate;
	}
	public void setKsDate(Date ksdate) {
		this.ksdate = ksdate;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}
	public Double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}
	public Double getSurplus() {
		return surplus;
	}
	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}
	public Double getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(Double ticketnum) {
		this.ticketnum = ticketnum;
	}
	public Double getClinic() {
		return clinic;
	}
	public void setClinic(Double clinic) {
		this.clinic = clinic;
	}
	public Double getTechnology() {
		return technology;
	}
	public void setTechnology(Double technology) {
		this.technology = technology;
	}
	public Double getJx_num() {
		return jx_num;
	}
	public void setJx_num(Double jx_num) {
		this.jx_num = jx_num;
	}
	public Double getKs_num() {
		return ks_num;
	}
	public void setKs_num(Double ks_num) {
		this.ks_num = ks_num;
	}
	public Double getBase_jx() {
		return base_jx;
	}
	public void setBase_jx(Double base_jx) {
		this.base_jx = base_jx;
	}
	public Double getZy_jx() {
		return zy_jx;
	}
	public void setZy_jx(Double zy_jx) {
		this.zy_jx = zy_jx;
	}
	public Double getOther_jx() {
		return other_jx;
	}
	public void setOther_jx(Double other_jx) {
		this.other_jx = other_jx;
	}
	public Double getOught_jx() {
		return ought_jx;
	}
	public void setOught_jx(Double ought_jx) {
		this.ought_jx = ought_jx;
	}
	public Double getReward() {
		return reward;
	}
	public void setReward(Double reward) {
		this.reward = reward;
	}
	public Double getReality_jx() {
		return reality_jx;
	}
	public void setReality_jx(Double reality_jx) {
		this.reality_jx = reality_jx;
	}
	public Double getPerson_num() {
		return person_num;
	}
	public void setPerson_num(Double person_num) {
		this.person_num = person_num;
	}
	public Double getAverage_jx() {
		return average_jx;
	}
	public void setAverage_jx(Double average_jx) {
		this.average_jx = average_jx;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

}
