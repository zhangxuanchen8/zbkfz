package com.ehinfo.hr.entity.hr.warning;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;
@Alias("Warning")
public class Warning extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String warn_type;
	private String warn_name;
	private String warn_forward;
	private String warn_cyc;
	private String getwarn_cyc;
	private String getwarn_sql;
	private String forward_role;
	private String warn_html;
	private String hosnum;
	private String operator;
	private Date operatdate;
	private String warn_jobid;
	private int num;
	private String count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWarn_type() {
		return warn_type;
	}
	public void setWarn_type(String warn_type) {
		this.warn_type = warn_type;
	}
	public String getWarn_name() {
		return warn_name;
	}
	public void setWarn_name(String warn_name) {
		this.warn_name = warn_name;
	}
	public String getWarn_forward() {
		return warn_forward;
	}
	public void setWarn_forward(String warn_forward) {
		this.warn_forward = warn_forward;
	}
	public String getWarn_cyc() {
		return warn_cyc;
	}
	public void setWarn_cyc(String warn_cyc) {
		this.warn_cyc = warn_cyc;
	}
	public String getGetwarn_cyc() {
		return getwarn_cyc;
	}
	public void setGetwarn_cyc(String getwarn_cyc) {
		this.getwarn_cyc = getwarn_cyc;
	}
	public String getGetwarn_sql() {
		return getwarn_sql;
	}
	public void setGetwarn_sql(String getwarn_sql) {
		this.getwarn_sql = getwarn_sql;
	}
	public String getForward_role() {
		return forward_role;
	}
	public void setForward_role(String forward_role) {
		this.forward_role = forward_role;
	}
	public String getWarn_html() {
		return warn_html;
	}
	public void setWarn_html(String warn_html) {
		this.warn_html = warn_html;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatdate() {
		return operatdate;
	}
	public void setOperatdate(Date operatdate) {
		this.operatdate = operatdate;
	}
	public String getWarn_jobid() {
		return warn_jobid;
	}
	public void setWarn_jobid(String warn_jobid) {
		this.warn_jobid = warn_jobid;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Warning [id=" + id + ", warn_type=" + warn_type + ", warn_name=" + warn_name + ", warn_forward="
				+ warn_forward + ", warn_cyc=" + warn_cyc + ", getwarn_cyc=" + getwarn_cyc + ", getwarn_sql="
				+ getwarn_sql + ", forward_role=" + forward_role + ", warn_html=" + warn_html + ", hosnum=" + hosnum
				+ ", operator=" + operator + ", operatdate=" + operatdate + ", warn_jobid=" + warn_jobid + ", num="
				+ num + ", count=" + count + "]";
	}
}
