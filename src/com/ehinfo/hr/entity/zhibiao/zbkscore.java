package com.ehinfo.hr.entity.zhibiao;

import java.util.Date;
import java.util.List;

public class zbkscore {
	private String id;
	private String name;
	private String scorep;
	private String xulieid;
	private String itemid;
	private Date year;
	private String finlscore;
	private String item;
	private String xm;
	private String psunitid;
	private String years;
	private String idcard;
	private String leixing;
	private String dept;
	 private String state;
	 private String maxscore;
	 private String is_sub;
	 private String is_max;
	 private List<zbkscore> zbks;
	 
	public List<zbkscore> getZbks() {
		return zbks;
	}
	public void setZbks(List<zbkscore> zbks) {
		this.zbks = zbks;
	}
	public String getIs_max() {
		return is_max;
	}
	public void setIs_max(String is_max) {
		this.is_max = is_max;
	}
	public String getIs_sub() {
		return is_sub;
	}
	public void setIs_sub(String is_sub) {
		this.is_sub = is_sub;
	}
	public String getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(String maxscore) {
		this.maxscore = maxscore;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getPsunitid() {
		return psunitid;
	}
	public void setPsunitid(String psunitid) {
		this.psunitid = psunitid;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
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
	public String getScorep() {
		return scorep;
	}
	public void setScorep(String scorep) {
		this.scorep = scorep;
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
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
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
		return "zbkscore [id=" + id + ", name=" + name + ", scorep=" + scorep + ", xulieid=" + xulieid + ", itemid="
				+ itemid + ", year=" + year + ", finlscore=" + finlscore + ", item=" + item + ", xm=" + xm
				+ ", psunitid=" + psunitid + "]";
	}
	
}
