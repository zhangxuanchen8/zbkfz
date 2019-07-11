package com.ehinfo.hr.entity.caiji;

import java.util.Date;

public class TongJi_log {
	private String id;
	private String name;//名称
	private String tongjilx;//统计类型
	private String tongjilb;//统计类别
	private String zxren;//执行人
	private Date zxdate;//执行时间
	private String zxnum;//采集条数
	private String sfcw;//是否错误
	private Date caijidate;//采集时间
	
	//临时
	private String caiji_date1;
	private String caiji_date2;
	
	
	public String getCaiji_date1() {
		return caiji_date1;
	}
	public void setCaiji_date1(String caiji_date1) {
		this.caiji_date1 = caiji_date1;
	}
	public String getCaiji_date2() {
		return caiji_date2;
	}
	public void setCaiji_date2(String caiji_date2) {
		this.caiji_date2 = caiji_date2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTongjilx() {
		return tongjilx;
	}
	public void setTongjilx(String tongjilx) {
		this.tongjilx = tongjilx;
	}
	public String getTongjilb() {
		return tongjilb;
	}
	public void setTongjilb(String tongjilb) {
		this.tongjilb = tongjilb;
	}
	public String getZxren() {
		return zxren;
	}
	public void setZxren(String zxren) {
		this.zxren = zxren;
	}
	public Date getZxdate() {
		return zxdate;
	}
	public void setZxdate(Date zxdate) {
		this.zxdate = zxdate;
	}
	public String getZxnum() {
		return zxnum;
	}
	public void setZxnum(String zxnum) {
		this.zxnum = zxnum;
	}
	public String getSfcw() {
		return sfcw;
	}
	public void setSfcw(String sfcw) {
		this.sfcw = sfcw;
	}
	public Date getCaijidate() {
		return caijidate;
	}
	public void setCaijidate(Date caijidate) {
		this.caijidate = caijidate;
	}
	@Override
	public String toString() {
		return "TongJi_log [id=" + id + ", name=" + name + ", tongjilx=" + tongjilx + ", tongjilb=" + tongjilb
				+ ", zxren=" + zxren + ", zxdate=" + zxdate + ", zxnum=" + zxnum + ", sfcw=" + sfcw + ", caijidate="
				+ caijidate + ", caiji_date1=" + caiji_date1 + ", caiji_date2=" + caiji_date2 + "]";
	}
	
}
