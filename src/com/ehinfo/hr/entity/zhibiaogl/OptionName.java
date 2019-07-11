package com.ehinfo.hr.entity.zhibiaogl;

import org.apache.ibatis.type.Alias;

@Alias("optionName")
public class OptionName {
	private String id;
	private String zbkname;
	private String option_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public String getZbkname() {
		return zbkname;
	}
	public void setZbkname(String zbkname) {
		this.zbkname = zbkname;
	}
}
