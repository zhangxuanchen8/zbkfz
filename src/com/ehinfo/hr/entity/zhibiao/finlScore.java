package com.ehinfo.hr.entity.zhibiao;

import java.util.Date;

public class finlScore {
	private String id;
	private String nameid;
	private String hosnum;
	private String xulieid;
	private String itemid;
	private String year;
	private String finlscore;
	private String dept;
	private String num;
	private String statu;
	private String zipingfen;
	
	public String getZipingfen() {
		return zipingfen;
	}
	public void setZipingfen(String zipingfen) {
		this.zipingfen = zipingfen;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameid() {
		return nameid;
	}
	public void setNameid(String nameid) {
		this.nameid = nameid;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getXulieid() {
		return xulieid;
	}
	public void setXulieid(String xulieid) {
		this.xulieid = xulieid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFinlscore() {
		return finlscore;
	}
	public void setFinlscore(String finlscore) {
		this.finlscore = finlscore;
	}
	@Override
	public String toString() {
		return "finlScore [id=" + id + ", nameid=" + nameid + ", hosnum=" + hosnum + ", xulieid=" + xulieid
				+ ", itemid=" + itemid + ", year=" + year + ", finlscore=" + finlscore + "]";
	}
}
