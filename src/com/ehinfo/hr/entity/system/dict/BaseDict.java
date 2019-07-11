package com.ehinfo.hr.entity.system.dict;

import org.apache.ibatis.type.Alias;

/**
 * 基础字典
 */
@Alias("BaseDict")
public class BaseDict {
	private String dictid;
	private String hosnum;
	private String sysname;
	private Integer nekey;
	private String nevalue;
	private String contents;
	private String isdefault;
	private String option01;
	private String option02;
	private String option03;
	private String option04;
	private String option05;
	private String option06;
	private String option07;
	private String option08;
	private String option09;
	private String option10;
	private Integer canedit;
	private String comments;
	private String inputcpy;
	private String inputcwb;
	private String stdinfo;
	private String isdeleted;
	private String option11;
	private String isgdb;
	
	private String bj;
	
	public String getIsgdb() {
		return isgdb;
	}
	public void setIsgdb(String isgdb) {
		this.isgdb = isgdb;
	}
	public String getOption11() {
		return option11;
	}
	public void setOption11(String option11) {
		this.option11 = option11;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getDictid() {
		return dictid;
	}
	public void setDictid(String dictid) {
		this.dictid = dictid;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public Integer getNekey() {
		return nekey;
	}
	public void setNekey(Integer nekey) {
		this.nekey = nekey;
	}
	public String getNevalue() {
		return nevalue;
	}
	public void setNevalue(String nevalue) {
		this.nevalue = nevalue;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	public String getOption01() {
		return option01;
	}
	public void setOption01(String option01) {
		this.option01 = option01;
	}
	public String getOption02() {
		return option02;
	}
	public void setOption02(String option02) {
		this.option02 = option02;
	}
	public String getOption03() {
		return option03;
	}
	public void setOption03(String option03) {
		this.option03 = option03;
	}
	public String getOption04() {
		return option04;
	}
	public void setOption04(String option04) {
		this.option04 = option04;
	}
	public String getOption05() {
		return option05;
	}
	public void setOption05(String option05) {
		this.option05 = option05;
	}
	public String getOption06() {
		return option06;
	}
	public void setOption06(String option06) {
		this.option06 = option06;
	}
	public String getOption07() {
		return option07;
	}
	public void setOption07(String option07) {
		this.option07 = option07;
	}
	public String getOption08() {
		return option08;
	}
	public void setOption08(String option08) {
		this.option08 = option08;
	}
	public String getOption09() {
		return option09;
	}
	public void setOption09(String option09) {
		this.option09 = option09;
	}
	public String getOption10() {
		return option10;
	}
	public void setOption10(String option10) {
		this.option10 = option10;
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
	public String getInputcpy() {
		return inputcpy;
	}
	public void setInputcpy(String inputcpy) {
		this.inputcpy = inputcpy;
	}
	public String getInputcwb() {
		return inputcwb;
	}
	public void setInputcwb(String inputcwb) {
		this.inputcwb = inputcwb;
	}
	public String getStdinfo() {
		return stdinfo;
	}
	public void setStdinfo(String stdinfo) {
		this.stdinfo = stdinfo;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	@Override
	public String toString() {
		return "BaseDict [dictid=" + dictid + ", hosnum=" + hosnum + ", sysname=" + sysname + ", nekey=" + nekey
				+ ", nevalue=" + nevalue + ", contents=" + contents + ", isdefault=" + isdefault + ", option01="
				+ option01 + ", option02=" + option02 + ", option03=" + option03 + ", option04=" + option04
				+ ", option05=" + option05 + ", option06=" + option06 + ", option07=" + option07 + ", option08="
				+ option08 + ", option09=" + option09 + ", option10=" + option10 + ", canedit=" + canedit
				+ ", comments=" + comments + ", inputcpy=" + inputcpy + ", inputcwb=" + inputcwb + ", stdinfo="
				+ stdinfo + ", isdeleted=" + isdeleted + ", option11=" + option11 + ", isgdb=" + isgdb + ", bj=" + bj
				+ "]";
	}
	
}
