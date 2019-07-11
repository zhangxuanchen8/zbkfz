package com.ehinfo.hr.entity.zhibiao;

import java.sql.Date;

public class Zbkavgscore {
	private String id;//项目id
	private String itemid;//项目id
	private String hosnum;//医院编码
	private String itemscore;//项目分数
	private String number;//人数
	private String zbkname;//模板名称
	private String zbkid;//模板名称
	private String avgscore;//平均分数
	private Date tiemname;//时间
	private String item;
	private String nameid;//人员名单
	
	public String getZbkid() {
		return zbkid;
	}
	public void setZbkid(String zbkid) {
		this.zbkid = zbkid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTiemname() {
		return tiemname;
	}
	public void setTiemname(Date tiemname) {
		this.tiemname = tiemname;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getItemscore() {
		return itemscore;
	}
	public void setItemscore(String itemscore) {
		this.itemscore = itemscore;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getZbkname() {
		return zbkname;
	}
	public void setZbkname(String zbkname) {
		this.zbkname = zbkname;
	}
	public String getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(String avgscore) {
		this.avgscore = avgscore;
	}

	
	public String getNameid() {
		return nameid;
	}
	public void setNameid(String nameid) {
		this.nameid = nameid;
	}
	@Override
	public String toString() {
		return "Zbkavgscore [itemid=" + itemid + ", hosnum=" + hosnum + ", itemscore=" + itemscore + ", number="
				+ number + ", zbkname=" + zbkname + ", avgscore=" + avgscore + ", tiemname=" + tiemname + ", item="
				+ item + ", nameid=" + nameid + "]";
	}
	
	
}
