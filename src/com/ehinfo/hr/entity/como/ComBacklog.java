package com.ehinfo.hr.entity.como;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.ehinfo.hr.entity.base.BaseEntity;

@SuppressWarnings("serial")
@Alias("ComBacklog")
public class ComBacklog extends BaseEntity {
	private String id;
	private String backlog_type;
	private String proposer;
	private String auditor;
	private Date proposer_time;
	private Integer backlog_status;
	private String notice_rule;
	private String notice_place;
	private String be_dispose;
	public String getBe_dispose() {
		return be_dispose;
	}
	public void setBe_dispose(String be_dispose) {
		this.be_dispose = be_dispose;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBacklog_type() {
		return backlog_type;
	}
	public void setBacklog_type(String backlog_type) {
		this.backlog_type = backlog_type;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getProposer_time() {
		return proposer_time;
	}
	public void setProposer_time(Date proposer_time) {
		this.proposer_time = proposer_time;
	}
	public Integer getBacklog_status() {
		return backlog_status;
	}
	public void setBacklog_status(Integer backlog_status) {
		this.backlog_status = backlog_status;
	}
	public String getNotice_rule() {
		return notice_rule;
	}
	public void setNotice_rule(String notice_rule) {
		this.notice_rule = notice_rule;
	}
	public String getNotice_place() {
		return notice_place;
	}
	public void setNotice_place(String notice_place) {
		this.notice_place = notice_place;
	}
}