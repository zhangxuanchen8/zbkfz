package com.ehinfo.hr.entity.zhibiao;

public class ryzbk {
	private String pid;
	private String recordno;
	private String xm;
	private String unitid;
	private String idcard;
	private String year;
	private String leixing;
	private String contents;
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRecordno() {
		return recordno;
	}
	public void setRecordno(String recordno) {
		this.recordno = recordno;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "ryzbk [pid=" + pid + ", recordno=" + recordno + ", xm=" + xm + ", unitid=" + unitid + ", idcard="
				+ idcard + ", year=" + year + "]";
	}
}
