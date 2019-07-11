package com.ehinfo.hr.entity.zhibiao;

import java.util.Date;

public class Choose_Dept {
	private String key_id;//主键id
	private String hosnum;//医院编码
	private String z_name;//选定多个科室后的总名称
	private String g_dept;//多个科室数组
	private Date create_time;//创建时间
	private String remark;//备注
	private String index_no;//排序号
	
	private String hosname;//医院名称
	
	public String getKey_id() {
		return key_id;
	}
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getZ_name() {
		return z_name;
	}
	public void setZ_name(String z_name) {
		this.z_name = z_name;
	}
	public String getG_dept() {
		return g_dept;
	}
	public void setG_dept(String g_dept) {
		this.g_dept = g_dept;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIndex_no() {
		return index_no;
	}
	public void setIndex_no(String index_no) {
		this.index_no = index_no;
	}
	public String getHosname() {
		return hosname;
	}
	public void setHosname(String hosname) {
		this.hosname = hosname;
	}
	@Override
	public String toString() {
		return "Choose_Dept [key_id=" + key_id + ", hosnum=" + hosnum + ", z_name=" + z_name + ", g_dept=" + g_dept
				+ ", create_time=" + create_time + ", remark=" + remark + ", index_no=" + index_no + ", hosname="
				+ hosname + "]";
	}
	
}
