package com.ehinfo.hr.entity.caiji;

public class ShuJu_pz {
	private String id;
	private String s_name;//数据源名称
	private String sources;//来源
	private String ip;//ip
	private String user_account;//用户账号
	private String pwd;//密码
	private String port;//端口号
	private String stype;//类型
	private String remark;//备注
	private String sqlname;//库名
	//private String backup1;
	//private String backup2;
	private String update_man;//修改人
	private String update_time;//最后修改时间
	
	//临时
	private String mx_sql;//sql明细
	private String caijibz;
	
	public String getCaijibz() {
		return caijibz;
	}
	public void setCaijibz(String caijibz) {
		this.caijibz = caijibz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getSources() {
		return sources;
	}
	public void setSources(String sources) {
		this.sources = sources;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSqlname() {
		return sqlname;
	}
	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpdate_man() {
		return update_man;
	}
	public void setUpdate_man(String update_man) {
		this.update_man = update_man;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getMx_sql() {
		return mx_sql;
	}
	public void setMx_sql(String mx_sql) {
		this.mx_sql = mx_sql;
	}
	@Override
	public String toString() {
		return "ShuJu_pz [id=" + id + ", s_name=" + s_name + ", sources=" + sources + ", ip=" + ip + ", user_account="
				+ user_account + ", pwd=" + pwd + ", port=" + port + ", stype=" + stype + ", remark=" + remark
				+ ", sqlname=" + sqlname + ", update_man=" + update_man + ", update_time=" + update_time + ", mx_sql="
				+ mx_sql + ", caijibz=" + caijibz + "]";
	}
	
}
