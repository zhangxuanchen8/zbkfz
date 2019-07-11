package com.ehinfo.hr.entity.como;

import java.util.Date;
import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
@Alias("ComNoticeReader")
public class ComNoticeReader extends com.ehinfo.hr.entity.base.BaseEntity {
	private String id;
	private String noticeid;
	private String userid;
	private int reads;
	private Date fistreadtime;
	private Date lastreadtime;
	private String upper;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReads() {
		return reads;
	}
	public void setReads(int reads) {
		this.reads = reads;
	}
	public Date getFistreadtime() {
		return fistreadtime;
	}
	public void setFistreadtime(Date fistreadtime) {
		this.fistreadtime = fistreadtime;
	}
	public Date getLastreadtime() {
		return lastreadtime;
	}
	public void setLastreadtime(Date lastreadtime) {
		this.lastreadtime = lastreadtime;
	}
	public String getUpper() {
		return upper;
	}
	public void setUpper(String upper) {
		this.upper = upper;
	}
	
}
