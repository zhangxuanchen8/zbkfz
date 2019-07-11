package com.ehinfo.hr.entity.system.tool;

import org.apache.ibatis.type.Alias;

@Alias("ErroMsg")
public class ErroMsg {

	private String erroid;
	private String erromsg;
	private String checkfield;
	private String checkvalue;
	private String hosnum;
	private String returnvalue;
	
	public String getErroid() {
		return erroid;
	}
	public void setErroid(String erroid) {
		this.erroid = erroid;
	}
	public String getCheckfield() {
		return checkfield;
	}
	public void setCheckfield(String checkfield) {
		this.checkfield = checkfield;
	}
	public String getCheckvalue() {
		return checkvalue;
	}
	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}
	public String getErromsg() {
		return erromsg;
	}
	public void setErromsg(String erromsg) {
		this.erromsg = erromsg;
	}
	
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	
	public String getReturnvalue() {
		return returnvalue;
	}
	public void setReturnvalue(String returnvalue) {
		this.returnvalue = returnvalue;
	}
	public ErroMsg(String erroid, String erromsg, String checkfield, String checkvalue, String hosnum) {
		super();
		this.erroid = erroid;
		this.erromsg = erromsg;
		this.checkfield = checkfield;
		this.checkvalue = checkvalue;
		this.hosnum = hosnum;
	}
	public ErroMsg(String erroid, String erromsg, String checkvalue) {
		super();
		this.erroid = erroid;
		this.erromsg = erromsg;
		this.checkvalue = checkvalue;
	}
	public ErroMsg(){
		super();
	}
}
