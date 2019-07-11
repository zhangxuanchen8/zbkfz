package com.ehinfo.hr.entity.system.resources;

import org.apache.ibatis.type.Alias;

@Alias("BaseRight")
public class BaseRight {
	private static final long serialVersionUID = 1L;
	private String user_role_id;
	private String dept_id;
	private String menu_id;
	private String hosnum;
	private String nodecode;
	public String getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(String user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getNodecode() {
		return nodecode;
	}
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	@Override
	public String toString() {
		return "BaseRight [user_role_id=" + user_role_id + ", dept_id=" + dept_id + ", menu_id=" + menu_id + ", hosnum="
				+ hosnum + ", nodecode=" + nodecode + "]";
	}
	
	
}
