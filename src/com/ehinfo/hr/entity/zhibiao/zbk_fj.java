package com.ehinfo.hr.entity.zhibiao;

import java.util.List;

import com.ehinfo.hr.entity.file.BaseFile;

public class zbk_fj {
	private String i_id;
	private String item;
	private String score;
	private String category;
	private String item_desc;
	private String is_max;
	private String is_add;
	private String note;
	private String category_id;
	private String is_using;
	private String districtid;
	private String hosnum;
	private String pid;
	private String year;
	private String finlscore;
	private String last;
	private String keshi;
	private String lczt;
	private String xuhao;
	private String use_dept;
	private String stop_time;
	private String maxscore;
	private String user_id;
	private String zbk_fj;
	private String qzdept;
	private String zipingfen;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipingfen() {
		return zipingfen;
	}
	public void setZipingfen(String zipingfen) {
		this.zipingfen = zipingfen;
	}
	private List<BaseFile> list_file;//存放附件
	public String getI_id() {
		return i_id;
	}
	public void setI_id(String i_id) {
		this.i_id = i_id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public String getIs_max() {
		return is_max;
	}
	public void setIs_max(String is_max) {
		this.is_max = is_max;
	}
	public String getIs_add() {
		return is_add;
	}
	public void setIs_add(String is_add) {
		this.is_add = is_add;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getIs_using() {
		return is_using;
	}
	public void setIs_using(String is_using) {
		this.is_using = is_using;
	}
	public String getDistrictid() {
		return districtid;
	}
	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFinlscore() {
		return finlscore;
	}
	public void setFinlscore(String finlscore) {
		this.finlscore = finlscore;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getKeshi() {
		return keshi;
	}
	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}
	public String getLczt() {
		return lczt;
	}
	public void setLczt(String lczt) {
		this.lczt = lczt;
	}
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getUse_dept() {
		return use_dept;
	}
	public void setUse_dept(String use_dept) {
		this.use_dept = use_dept;
	}
	public String getStop_time() {
		return stop_time;
	}
	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}
	public String getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(String maxscore) {
		this.maxscore = maxscore;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getZbk_fj() {
		return zbk_fj;
	}
	public void setZbk_fj(String zbk_fj) {
		this.zbk_fj = zbk_fj;
	}
	public String getQzdept() {
		return qzdept;
	}
	public void setQzdept(String qzdept) {
		this.qzdept = qzdept;
	}
	
	public List<BaseFile> getList_file() {
		return list_file;
	}
	public void setList_file(List<BaseFile> list_file) {
		this.list_file = list_file;
	}
	@Override
	public String toString() {
		return "zbk_fj [i_id=" + i_id + ", item=" + item + ", score=" + score + ", category=" + category
				+ ", item_desc=" + item_desc + ", is_max=" + is_max + ", is_add=" + is_add + ", note=" + note
				+ ", category_id=" + category_id + ", is_using=" + is_using + ", districtid=" + districtid + ", hosnum="
				+ hosnum + ", pid=" + pid + ", year=" + year + ", finlscore=" + finlscore + ", last=" + last
				+ ", keshi=" + keshi + ", lczt=" + lczt + ", xuhao=" + xuhao + ", use_dept=" + use_dept + ", stop_time="
				+ stop_time + ", maxscore=" + maxscore + ", user_id=" + user_id + ", zbk_fj=" + zbk_fj + ", qzdept="
				+ qzdept + ", list_file=" + list_file + "]";
	}
	
}
