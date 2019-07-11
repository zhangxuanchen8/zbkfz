package com.ehinfo.hr.entity.system.org;

import org.apache.ibatis.type.Alias;

@Alias("BaseRolenew")
public class BaseRole {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String remark;
	private int index_no;
	private String lvl;
	private String sts;
	
	private String checked;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIndex_no() {
		return index_no;
	}
	public void setIndex_no(int index_no) {
		this.index_no = index_no;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "BaseRole [id=" + id + ", code=" + code + ", name=" + name + ", remark=" + remark + ", index_no="
				+ index_no + ", lvl=" + lvl + ", sts=" + sts + ", checked=" + checked + "]";
	}


	
	
}
