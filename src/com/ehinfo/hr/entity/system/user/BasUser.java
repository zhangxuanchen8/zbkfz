package com.ehinfo.hr.entity.system.user;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;

@Alias("BaseUser")
public class BasUser extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String hosnum;
	private String user_key;
	private String password;
	private String name;
	private String idcard;
	private String sex;
	private Date birthdate;
	private String phone;
	private String mobile;
	private String short_mobile;
	private String email;
	private String post;
	private String post_code;
	private int index_no;
	private Date reg_date;
	private String stop_sign;
	private String del_sign;
	private String remark;
	private String input_cpy;
	private String input_cwb;
	private String input_custom;
	private String job_no;
	private String nodecode;
	private String person_dept;
	private String ehruser_key;
	private String ehrpassword;
	private String ehrrole;
	private String clcpower;
	private String zc;
	//----------------------------------------------------------
	private String roles ;
	//*--------表以外的-----
	private String dept_name;
	private String hosname;
	public BasUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BasUser(String id, String hosnum, String user_key, String password, String name, String idcard, String sex,
			Date birthdate, String phone, String mobile, String short_mobile, String email, String post,
			String post_code, int index_no, Date reg_date, String stop_sign, String del_sign, String remark,
			String input_cpy, String input_cwb, String input_custom, String job_no, String nodecode, String person_dept,
			String ehruser_key, String ehrpassword, String ehrrole, String clcpower,String zc) {
		super();
		this.id = id;
		this.hosnum = hosnum;
		this.user_key = user_key;
		this.password = password;
		this.name = name;
		this.idcard = idcard;
		this.sex = sex;
		this.birthdate = birthdate;
		this.phone = phone;
		this.mobile = mobile;
		this.short_mobile = short_mobile;
		this.email = email;
		this.post = post;
		this.post_code = post_code;
		this.index_no = index_no;
		this.reg_date = reg_date;
		this.stop_sign = stop_sign;
		this.del_sign = del_sign;
		this.remark = remark;
		this.input_cpy = input_cpy;
		this.input_cwb = input_cwb;
		this.input_custom = input_custom;
		this.job_no = job_no;
		this.nodecode = nodecode;
		this.person_dept = person_dept;
		this.ehruser_key = ehruser_key;
		this.ehrpassword = ehrpassword;
		this.ehrrole = ehrrole;
		this.clcpower = clcpower;
		this.zc = zc;
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
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShort_mobile() {
		return short_mobile;
	}
	public void setShort_mobile(String short_mobile) {
		this.short_mobile = short_mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public int getIndex_no() {
		return index_no;
	}
	public void setIndex_no(int index_no) {
		this.index_no = index_no;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getStop_sign() {
		return stop_sign;
	}
	public void setStop_sign(String stop_sign) {
		this.stop_sign = stop_sign;
	}
	public String getDel_sign() {
		return del_sign;
	}
	public void setDel_sign(String del_sign) {
		this.del_sign = del_sign;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInput_cpy() {
		return input_cpy;
	}
	public void setInput_cpy(String input_cpy) {
		this.input_cpy = input_cpy;
	}
	public String getInput_cwb() {
		return input_cwb;
	}
	public void setInput_cwb(String input_cwb) {
		this.input_cwb = input_cwb;
	}
	public String getInput_custom() {
		return input_custom;
	}
	public void setInput_custom(String input_custom) {
		this.input_custom = input_custom;
	}
	public String getJob_no() {
		return job_no;
	}
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	public String getNodecode() {
		return nodecode;
	}
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	public String getPerson_dept() {
		return person_dept;
	}
	public void setPerson_dept(String person_dept) {
		this.person_dept = person_dept;
	}
	public String getEhruser_key() {
		return ehruser_key;
	}
	public void setEhruser_key(String ehruser_key) {
		this.ehruser_key = ehruser_key;
	}
	public String getEhrpassword() {
		return ehrpassword;
	}
	public void setEhrpassword(String ehrpassword) {
		this.ehrpassword = ehrpassword;
	}
	public String getEhrrole() {
		return ehrrole;
	}
	public void setEhrrole(String ehrrole) {
		this.ehrrole = ehrrole;
	}
	public String getClcpower() {
		return clcpower;
	}
	public void setClcpower(String clcpower) {
		this.clcpower = clcpower;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
	
	public String getHosname() {
		return hosname;
	}
	public void setHosname(String hosname) {
		this.hosname = hosname;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	@Override
	public String toString() {
		return "BasUser [id=" + id + ", hosnum=" + hosnum + ", user_key=" + user_key + ", password=" + password
				+ ", name=" + name + ", idcard=" + idcard + ", sex=" + sex + ", birthdate=" + birthdate + ", phone="
				+ phone + ", mobile=" + mobile + ", short_mobile=" + short_mobile + ", email=" + email + ", post="
				+ post + ", post_code=" + post_code + ", index_no=" + index_no + ", reg_date=" + reg_date
				+ ", stop_sign=" + stop_sign + ", del_sign=" + del_sign + ", remark=" + remark + ", input_cpy="
				+ input_cpy + ", input_cwb=" + input_cwb + ", input_custom=" + input_custom + ", job_no=" + job_no
				+ ", nodecode=" + nodecode + ", person_dept=" + person_dept + ", ehruser_key=" + ehruser_key
				+ ", ehrpassword=" + ehrpassword + ", ehrrole=" + ehrrole + ", clcpower=" + clcpower + ", zc=" + zc
				+ ", roles=" + roles + ", dept_name=" + dept_name + ", hosname=" + hosname + "]";
	}
}
