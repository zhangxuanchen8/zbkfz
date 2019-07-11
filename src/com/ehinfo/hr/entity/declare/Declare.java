package com.ehinfo.hr.entity.declare;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;

@Alias("Declare")
public class Declare extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4016717711184054699L;
	private String d_id;// 编码
	private String p_id;// 用户id
	private String c_name;// 用户名字
	private String p_no;// 用户工号
	private String id_card;// 用户身份证
	private String tjhrzzg;//推荐任职资格
	private String declare_state;// 上传状态
	private String declare_result;// 申报结果
	private String declare_date;// 申报时间
	private String delflag;// 删除标记（目前不用）
	private String deldate;// 删除时间（目前不用）

	private String year;// 年份
	private String hosnum;// 医院编号
	private String hosname;// 医院民称
	private String deptname;// 部门医
	private String keyWord;// 关键字

	public static final String DECLARE_STATE0 = "0"; // 申请不通过
	public static final String DECLARE_STATE1 = "1"; // 申请通过
	public static final String DECLARE_STATE2 = "2"; // 审请中...（时间差）

	public static final String DECLARE_RESULT0 = "0";// 申核不通过
	public static final String DECLARE_RESULT1 = "1";// 申核通过
	public static final String DECLARE_RESULT2 = "2";// 审核中

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getDeclare_state() {
		return declare_state;
	}

	public void setDeclare_state(String declare_state) {
		this.declare_state = declare_state;
	}

	public String getDeclare_result() {
		return declare_result;
	}

	public void setDeclare_result(String declare_result) {
		this.declare_result = declare_result;
	}

	public String getDeclare_date() {
		return declare_date;
	}

	public void setDeclare_date(String declare_date) {
		this.declare_date = declare_date;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getDeldate() {
		return deldate;
	}

	public void setDeldate(String deldate) {
		this.deldate = deldate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHosnum() {
		return hosnum;
	}

	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}

	public String getHosname() {
		return hosname;
	}

	public void setHosname(String hosname) {
		this.hosname = hosname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTjhrzzg() {
		return tjhrzzg;
	}

	public void setTjhrzzg(String tjhrzzg) {
		this.tjhrzzg = tjhrzzg;
	}

	@Override
	public String toString() {
		return "Declare [d_id=" + d_id + ", p_id=" + p_id + ", c_name=" + c_name + ", p_no=" + p_no + ", id_card="
				+ id_card + ", tjhrzzg=" + tjhrzzg + ", declare_state=" + declare_state + ", declare_result="
				+ declare_result + ", declare_date=" + declare_date + ", delflag=" + delflag + ", deldate=" + deldate
				+ ", year=" + year + ", hosnum=" + hosnum + ", hosname=" + hosname + ", deptname=" + deptname
				+ ", keyWord=" + keyWord + "]";
	}
	
}
