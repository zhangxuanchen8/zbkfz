package com.ehinfo.hr.entity.zhibiao;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("zhibiao_sb")
public class zhibiaoku {
	private String i_id;
	private String item;
	private String score;
	private String category;
	private String item_desc;
	private String is_max;
	private String is_add;
	private String note;
	private String category_id;
	private String districtid;
	private String is_using;
	private String hosnum;
	private String pid;
	private String finlscore;
	private String year;
	private String scorep;
	private String last;
	private String keshi;
	private String lczt;
	private String xuhao;
	private String use_dept;
	private String use_dept_name;
	private String maxscore;
	private String qzDept;//群组科室
	private String qzname;//群组科室
	private String g_dept;
	private String fzdept;
	
	private String type;
	//树拼接
	private String treename;
	private String is_sub;//是否为子级（0：是   1：否）
	private String is_qy;//是否为全院（0：是   1：否）
	
	private String zbk_fj;//附件
	private String fj_size;//附件数
	//附件
	private String user_id;
	private String num;
	 private String state;
	 private String zpf;
	 private String fjnum;
	 private String heji;
	 private String synergy;
	 private List<zbkscore> list_s;
	 
	public List<zbkscore> getList_s() {
		return list_s;
	}
	public void setList_s(List<zbkscore> list_s) {
		this.list_s = list_s;
	}
	public String getSynergy() {
		return synergy;
	}
	public void setSynergy(String synergy) {
		this.synergy = synergy;
	}
	public String getHeji() {
		return heji;
	}
	public void setHeji(String heji) {
		this.heji = heji;
	}
	public String getFjnum() {
		return fjnum;
	}
	public void setFjnum(String fjnum) {
		this.fjnum = fjnum;
	}
	public String getZpf() {
		return zpf;
	}
	public void setZpf(String zpf) {
		this.zpf = zpf;
	}
	public String getFzdept() {
		return fzdept;
	}
	public void setFzdept(String fzdept) {
		this.fzdept = fzdept;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getFj_size() {
		return fj_size;
	}
	public void setFj_size(String fj_size) {
		this.fj_size = fj_size;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getG_dept() {
		return g_dept;
	}
	public void setG_dept(String g_dept) {
		this.g_dept = g_dept;
	}
	public String getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(String maxscore) {
		this.maxscore = maxscore;
	}
	public String getUse_dept_name() {
		return use_dept_name;
	}
	public void setUse_dept_name(String use_dept_name) {
		this.use_dept_name = use_dept_name;
	}
	private String stop_time;
	public String getUse_dept() {
		return use_dept;
	}
	public String getStop_time() {
		return stop_time;
	}
	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}
	public void setUse_dept(String use_dept) {
		this.use_dept = use_dept;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getScorep() {
		return scorep;
	}
	public void setScorep(String scorep) {
		this.scorep = scorep;
	}
	public String getFinlscore() {
		return finlscore;
	}
	public void setFinlscore(String finlscore) {
		this.finlscore = finlscore;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
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
	public String getTreename() {
		return treename;
	}
	public void setTreename(String treename) {
		this.treename = treename;
	}
	public String getIs_sub() {
		return is_sub;
	}
	public void setIs_sub(String is_sub) {
		this.is_sub = is_sub;
	}
	public String getIs_qy() {
		return is_qy;
	}
	public void setIs_qy(String is_qy) {
		this.is_qy = is_qy;
	}
	public String getQzDept() {
		return qzDept;
	}
	public void setQzDept(String qzDept) {
		this.qzDept = qzDept;
	}
	
	public String getQzname() {
		return qzname;
	}
	public void setQzname(String qzname) {
		this.qzname = qzname;
	}
	public String getZbk_fj() {
		return zbk_fj;
	}
	public void setZbk_fj(String zbk_fj) {
		this.zbk_fj = zbk_fj;
	}
	@Override
	public String toString() {
		return "zhibiaoku [i_id=" + i_id + ", item=" + item + ", score=" + score + ", category=" + category
				+ ", item_desc=" + item_desc + ", is_max=" + is_max + ", is_add=" + is_add + ", note=" + note
				+ ", category_id=" + category_id + ", districtid=" + districtid + ", is_using=" + is_using + ", hosnum="
				+ hosnum + ", pid=" + pid + ", finlscore=" + finlscore + ", year=" + year + ", scorep=" + scorep
				+ ", last=" + last + ", keshi=" + keshi + ", lczt=" + lczt + ", xuhao=" + xuhao + ", use_dept="
				+ use_dept + ", use_dept_name=" + use_dept_name + ", maxscore=" + maxscore + ", qzDept=" + qzDept
				+ ", qzname=" + qzname + ", g_dept=" + g_dept + ", type=" + type + ", treename=" + treename
				+ ", is_sub=" + is_sub + ", is_qy=" + is_qy + ", zbk_fj=" + zbk_fj + ", fj_size=" + fj_size
				+ ", user_id=" + user_id + ", stop_time=" + stop_time + "]";
	}
	
	
	
}
