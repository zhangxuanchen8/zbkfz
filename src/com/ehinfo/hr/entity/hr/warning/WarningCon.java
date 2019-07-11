package com.ehinfo.hr.entity.hr.warning;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;
@Alias("WarningCon")
public class WarningCon extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String id;
	private String warn_id;
	private String warn_forward;
	private String warn_role;
	private String warn_type;
	private String warn_name;
	private String warn_status;
	private Date warn_get_time;
	private String forwardid;
	private String valid;
	private String hosnum;
	private String counts;
	private String warn_html;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWarn_id() {
		return warn_id;
	}
	public void setWarn_id(String warn_id) {
		this.warn_id = warn_id;
	}
	public String getWarn_forward() {
		return warn_forward;
	}
	public void setWarn_forward(String warn_forward) {
		this.warn_forward = warn_forward;
	}
	public String getWarn_role() {
		return warn_role;
	}
	public void setWarn_role(String warn_role) {
		this.warn_role = warn_role;
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
	public String getWarn_status() {
		return warn_status;
	}
	public void setWarn_status(String warn_status) {
		this.warn_status = warn_status;
	}
	public Date getWarn_get_time() {
		return warn_get_time;
	}
	public void setWarn_get_time(Date warn_get_time) {
		this.warn_get_time = warn_get_time;
	}
	public String getForwardid() {
		return forwardid;
	}
	public void setForwardid(String forwardid) {
		this.forwardid = forwardid;
	}
	
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getWarn_html() {
		return warn_html;
	}
	public void setWarn_html(String warn_html) {
		this.warn_html = warn_html;
	}
	@Override
	public String toString() {
		return "WarningCon [id=" + id + ", warn_id=" + warn_id + ", warn_forward=" + warn_forward + ", warn_role="
				+ warn_role + ", warn_type=" + warn_type + ", warn_name=" + warn_name + ", warn_status=" + warn_status
				+ ", warn_get_time=" + warn_get_time + ", forwardid=" + forwardid + ", valid=" + valid + ", hosnum="
				+ hosnum + ", counts=" + counts + ", warn_html=" + warn_html + "]";
	}
}
