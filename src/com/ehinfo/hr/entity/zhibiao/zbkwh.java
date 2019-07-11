package com.ehinfo.hr.entity.zhibiao;

public class zbkwh {
	private String id;
	private String hosnum;
	private String category;
	private String zbkmc;
	private String yjfl;
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	private String ejfl;
	private String score;
	private String item_desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getZbkmc() {
		return zbkmc;
	}
	public void setZbkmc(String zbkmc) {
		this.zbkmc = zbkmc;
	}
	public String getYjfl() {
		return yjfl;
	}
	public void setYjfl(String yjfl) {
		this.yjfl = yjfl;
	}
	public String getEjfl() {
		return ejfl;
	}
	public void setEjfl(String ejfl) {
		this.ejfl = ejfl;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	@Override
	public String toString() {
		return "zbkwh [id=" + id + ", hosnum=" + hosnum + ", category=" + category + ", zbkmc=" + zbkmc + ", yjfl="
				+ yjfl + ", ejfl=" + ejfl + ", score=" + score + ", item_desc=" + item_desc + "]";
	}
	
}
