package com.ehinfo.hr.entity.zhibiaogl;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("fenpeiec")
public class fenpeiec {
	private String id;
	private String p_id;
	private String c_name;
	private String deptid;
	private String deptname;
	private String hosnum;
	private Date jxrq;
	private double jjs;
	private double jcs;
	private String bz;
	private Date sbrq;
	private Date tjrqs;
	private Date tjrqe;
	private String fenpei_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Date getJxrq() {
		return jxrq;
	}
	public void setJxrq(Date jxrq) {
		this.jxrq = jxrq;
	}
	public double getJjs() {
		return jjs;
	}
	public void setJjs(double jjs) {
		this.jjs = jjs;
	}
	public double getJcs() {
		return jcs;
	}
	public void setJcs(double jcs) {
		this.jcs = jcs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Date getSbrq() {
		return sbrq;
	}
	public void setSbrq(Date sbrq) {
		this.sbrq = sbrq;
	}
	public String getFenpei_id() {
		return fenpei_id;
	}
	public void setFenpei_id(String fenpei_id) {
		this.fenpei_id = fenpei_id;
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

	public String getHosnum() {
		return hosnum;
	}

	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}

	public Date getTjrqs() {
		return tjrqs;
	}

	public void setTjrqs(Date tjrqs) {
		this.tjrqs = tjrqs;
	}

	public Date getTjrqe() {
		return tjrqe;
	}

	public void setTjrqe(Date tjrqe) {
		this.tjrqe = tjrqe;
	}

	@Override
	public String toString() {
		return "fenpeiec [id=" + id + ", p_id=" + p_id + ", c_name=" + c_name + ", jxrq=" + jxrq + ", jjs=" + jjs
				+ ", jcs=" + jcs + ", bz=" + bz + ", sbrq=" + sbrq + ", fenpei_id=" + fenpei_id + "]";
	}
	
}
