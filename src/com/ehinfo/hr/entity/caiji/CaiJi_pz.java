package com.ehinfo.hr.entity.caiji;

import java.util.Date;

public class CaiJi_pz {
	private String id;
	private String caijimc;
	private Date sccjsj;
	private String zhouqi;
	private String mx_sql;
	private String zxren;
	private String cjsj;
	private Date ccdate;
	private String date_sources;
	private String caijibz;
	private String zhouqiday;
	private String zhouqixs;
	
	public String getZhouqiday() {
		return zhouqiday;
	}
	public void setZhouqiday(String zhouqiday) {
		this.zhouqiday = zhouqiday;
	}
	public String getZhouqixs() {
		return zhouqixs;
	}
	public void setZhouqixs(String zhouqixs) {
		this.zhouqixs = zhouqixs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaijimc() {
		return caijimc;
	}
	public void setCaijimc(String caijimc) {
		this.caijimc = caijimc;
	}
	public Date getSccjsj() {
		return sccjsj;
	}
	public void setSccjsj(Date sccjsj) {
		this.sccjsj = sccjsj;
	}
	public String getZhouqi() {
		return zhouqi;
	}
	public void setZhouqi(String zhouqi) {
		this.zhouqi = zhouqi;
	}
	public String getMx_sql() {
		return mx_sql;
	}
	public void setMx_sql(String mx_sql) {
		this.mx_sql = mx_sql;
	}
	public String getZxren() {
		return zxren;
	}
	public void setZxren(String zxren) {
		this.zxren = zxren;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public Date getCcdate() {
		return ccdate;
	}
	public void setCcdate(Date ccdate) {
		this.ccdate = ccdate;
	}
	public String getDate_sources() {
		return date_sources;
	}
	public void setDate_sources(String date_sources) {
		this.date_sources = date_sources;
	}
	public String getCaijibz() {
		return caijibz;
	}
	public void setCaijibz(String caijibz) {
		this.caijibz = caijibz;
	}
	@Override
	public String toString() {
		return "CaiJi_pz [id=" + id + ", caijimc=" + caijimc + ", sccjsj=" + sccjsj + ", zhouqi=" + zhouqi + ", mx_sql="
				+ mx_sql + ", zxren=" + zxren + ", cjsj=" + cjsj + ", ccdate=" + ccdate + ", date_sources="
				+ date_sources + ", caijibz=" + caijibz + ", zhouqiday=" + zhouqiday + ", zhouqixs=" + zhouqixs + "]";
	}
	
}
