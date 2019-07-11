package com.ehinfo.hr.entity.system.user;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;

@Alias("CpUser")
public class CpUser{
	
	/**
	 * 
	 */
	private String id;
	private String hosnum;
	private String password;
	private String user_key;
	private String name;
	private String idcard;
	private String user_id;
	private String del_sign;
	private String scbj;
	private String tjdate;
	private String kqdate;
	private String nums;
	private String modelname;
	private String modelid;
	private String modelnames;
	
	public String getModelnames() {
		return modelnames;
	}
	public void setModelnames(String modelnames) {
		this.modelnames = modelnames;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getKqdate() {
		return kqdate;
	}
	public void setKqdate(String kqdate) {
		this.kqdate = kqdate;
	}
	public String getTjdate() {
		return tjdate;
	}
	public void setTjdate(String tjdate) {
		this.tjdate = tjdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getDel_sign() {
		return del_sign;
	}
	public void setDel_sign(String del_sign) {
		this.del_sign = del_sign;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getScbj() {
		return scbj;
	}
	public void setScbj(String scbj) {
		this.scbj = scbj;
	}
	@Override
	public String toString() {
		return "CpUser [id=" + id + ", hosnum=" + hosnum + ", password=" + password + ", user_key=" + user_key
				+ ", name=" + name + ", idcard=" + idcard + ", user_id=" + user_id + ", del_sign=" + del_sign
				+ ", scbj=" + scbj + ", tjdate=" + tjdate + ", kqdate=" + kqdate + "]";
	}
	
}
