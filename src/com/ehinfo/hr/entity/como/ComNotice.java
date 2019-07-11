package com.ehinfo.hr.entity.como;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.ehinfo.hr.entity.base.BaseEntity;

@SuppressWarnings("serial")
@Alias("ComNotice")
public class ComNotice extends BaseEntity {
	private String noticeclass;
	private String noticecontent;
	private String fb_flag;
	private String author;
	private Date createtime;
	private String auditor;
	private Date audittime;
	private String noticetitle;
	private int readers;
	private int reads;
	private String fileid;
	private String filename;
	private String fileurl;
	private String hosnum;
	private String userid;
	private String audit;
	private String authorid;
	private String id;
	public String getNoticeclass() {
		return noticeclass;
	}
	public void setNoticeclass(String noticeclass) {
		this.noticeclass = noticeclass;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public String getFb_flag() {
		return fb_flag;
	}
	public void setFb_flag(String fb_flag) {
		this.fb_flag = fb_flag;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAudittime() {
		return audittime;
	}
	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public int getReaders() {
		return readers;
	}
	public void setReaders(int readers) {
		this.readers = readers;
	}
	public int getReads() {
		return reads;
	}
	public void setReads(int reads) {
		this.reads = reads;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}