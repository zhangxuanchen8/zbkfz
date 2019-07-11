package com.ehinfo.hr.entity.zhibiaogl;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("sallot")
public class sallot {
	private String id;
	private Date allot_date;
	private String person;
	private Double reward_money;
	private Double reward_num;
	private String remark;
	private Date appear_date;
//	ID	N	VARCHAR2(40)	Y			
//	ALLOT_DATE	N	DATE	Y			日期
//	PERSON	N	VARCHAR2(40)	Y			人员
//	REWARD_MONEY	N	NUMBER(8,2)	Y			奖金数
//	REWARD_NUM	N	NUMBER	Y			奖惩数
//	REMARK	N	VARCHAR2(200)	Y			备注
//	APPEAR_DATE	N	DATE	Y			上报时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAllot_date() {
		return allot_date;
	}
	public void setAllot_date(Date allot_date) {
		this.allot_date = allot_date;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Double getReward_money() {
		return reward_money;
	}
	public void setReward_money(Double reward_money) {
		this.reward_money = reward_money;
	}
	public Double getReward_num() {
		return reward_num;
	}
	public void setReward_num(Double reward_num) {
		this.reward_num = reward_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAppear_date() {
		return appear_date;
	}
	public void setAppear_date(Date appear_date) {
		this.appear_date = appear_date;
	}
	
}
