package com.ehinfo.hr.entity.zhibiaoku;

import org.apache.ibatis.type.Alias;

@Alias("zhibiaoku")
public class zhibiaoku {
	private String id;
	private String hosnum;
	private String zbksource;
	private String zbktype;
	private String zbktypeid;
	private String zbkname;
	private String isuse;
	private String isshow;
	private Integer zbkindex;
	private String deptid;
	private String compute;
	private String gather_grade;
	private String compute_grade;
	private String ziduan;
	private String his_deptid;
	public String getHis_deptid() {
		return his_deptid;
	}
	public void setHis_deptid(String his_deptid) {
		this.his_deptid = his_deptid;
	}
	public String getZiduan() {
		return ziduan;
	}
	public void setZiduan(String ziduan) {
		this.ziduan = ziduan;
	}
	public String getZbktypeid() {
		return zbktypeid;
	}
	public void setZbktypeid(String zbktypeid) {
		this.zbktypeid = zbktypeid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getZbksource() {
		return zbksource;
	}
	public void setZbksource(String zbksource) {
		this.zbksource = zbksource;
	}
	public String getZbktype() {
		return zbktype;
	}
	public void setZbktype(String zbktype) {
		this.zbktype = zbktype;
	}
	public String getZbkname() {
		return zbkname;
	}
	public void setZbkname(String zbkname) {
		this.zbkname = zbkname;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public Integer getZbkindex() {
		return zbkindex;
	}
	public void setZbkindex(Integer zbkindex) {
		this.zbkindex = zbkindex;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getCompute() {
		return compute;
	}
	public void setCompute(String compute) {
		this.compute = compute;
	}
	public String getGather_grade() {
		return gather_grade;
	}
	public void setGather_grade(String gather_grade) {
		this.gather_grade = gather_grade;
	}
	public String getCompute_grade() {
		return compute_grade;
	}
	public void setCompute_grade(String compute_grade) {
		this.compute_grade = compute_grade;
	}
}
