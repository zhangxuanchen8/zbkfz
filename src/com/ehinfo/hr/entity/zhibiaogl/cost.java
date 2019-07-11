package com.ehinfo.hr.entity.zhibiaogl;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("cost")
public class cost {
	private String id;
	private Date costdate;
	private String deptid;
	private String deptname;
	private Double medical;
	private Double logistics;
	private Double depreciation;
	private Double waterelectric;
	private Double other;
	private Double allcount;
	private String affirm;
	public String getAffirm() {
		return affirm;
	}
	public void setAffirm(String affirm) {
		this.affirm = affirm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCostdate() {
		return costdate;
	}
	public void setCostdate(Date costdate) {
		this.costdate = costdate;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public Double getMedical() {
		return medical;
	}
	public void setMedical(Double medical) {
		this.medical = medical;
	}
	public Double getLogistics() {
		return logistics;
	}
	public void setLogistics(Double logistics) {
		this.logistics = logistics;
	}
	public Double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}
	public Double getWaterelectric() {
		return waterelectric;
	}
	public void setWaterelectric(Double waterelectric) {
		this.waterelectric = waterelectric;
	}
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	public Double getAllcount() {
		return allcount;
	}
	public void setAllcount(Double allcount) {
		this.allcount = allcount;
	}
	
}
