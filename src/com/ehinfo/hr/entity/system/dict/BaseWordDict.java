package com.ehinfo.hr.entity.system.dict;

import org.apache.ibatis.type.Alias;

@Alias("BaseWordDict")
public class BaseWordDict {
	private Integer sn;
	private String cnchar;
	private String pychar;
	private String wbchar;
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getCnchar() {
		return cnchar;
	}
	public void setCnchar(String cnchar) {
		this.cnchar = cnchar;
	}
	public String getPychar() {
		return pychar;
	}
	public void setPychar(String pychar) {
		this.pychar = pychar;
	}
	public String getWbchar() {
		return wbchar;
	}
	public void setWbchar(String wbchar) {
		this.wbchar = wbchar;
	}
	
}
