package com.ehinfo.hr.entity.zhibiaogl;

import org.apache.ibatis.type.Alias;

@Alias("zhibiaokh")
public class zhibiaokh {
	private String id;
	private String khname;
	private String hosnum;
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	private String kstype;
	private String gongshi;
	private String deptid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKhname() {
		return khname;
	}
	public void setKhname(String khname) {
		this.khname = khname;
	}
	public String getKstype() {
		return kstype;
	}
	public void setKstype(String kstype) {
		this.kstype = kstype;
	}
	public String getGongshi() {
		return gongshi;
	}
	public void setGongshi(String gongshi) {
		this.gongshi = gongshi;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
}
