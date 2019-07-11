package com.ehinfo.hr.entity.system.dict;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;

@Alias("SysDict")
public class SysDict extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String parmid;
	private String hosnum;
	private String scope;
	private String parmname;
	private String parmvalue;
	private Integer canedit;
	private String comments;
	private String sysname;
	private String descriptions;
	private String defaultparms;
	private String userid;
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getParmid() {
		return parmid;
	}
	public void setParmid(String parmid) {
		this.parmid = parmid;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getParmname() {
		return parmname;
	}
	public void setParmname(String parmname) {
		this.parmname = parmname;
	}
	public String getParmvalue() {
		return parmvalue;
	}
	public void setParmvalue(String parmvalue) {
		this.parmvalue = parmvalue;
	}
	public Integer getCanedit() {
		return canedit;
	}
	public void setCanedit(Integer canedit) {
		this.canedit = canedit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public void setDefaultparms(String defaultparms) {
		this.defaultparms = defaultparms;
	}
	public String getDefaultparms() {
		return defaultparms;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "SysDict [parmid=" + parmid + ", hosnum=" + hosnum + ", scope=" + scope + ", parmname=" + parmname
				+ ", parmvalue=" + parmvalue + ", canedit=" + canedit + ", comments=" + comments + ", sysname="
				+ sysname + ", descriptions=" + descriptions + ", defaultparms=" + defaultparms + ", userid=" + userid
				+ "]";
	}
	
}
