package com.ehinfo.hr.common.utils.tree.entity;

import java.io.Serializable;
import java.util.List;


public class ZNodes implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pId;
	private String name;
	private String hosnum;
	private String checked;
	private String open;
	private String chkDisabled;	
	private String other;
	private String iconSkin;
	private String p_id;
	private String p_no;
	private String nekey; //字典nekey
	
	private String isleaf;//是否末级节点
	private String isdept;//是否末级节点
	
	//private List<ZNodes> sub;//节点下的树
	
	
	public String getNekey() {
		return nekey;
	}
	public void setNekey(String nekey) {
		this.nekey = nekey;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(String chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
/*	public List<ZNodes> getSub() {
		return sub;
	}
	public void setSub(List<ZNodes> sub) {
		this.sub = sub;
	}*/

	public String getIsdept() {
		return isdept;
	}
	public void setIsdept(String isdept) {
		this.isdept = isdept;
	}
	@Override
	public String toString() {
		return "ZNodes [id=" + id + ", pId=" + pId + ", name=" + name + ", hosnum=" + hosnum + ", checked=" + checked
				+ ", open=" + open + ", chkDisabled=" + chkDisabled + ", other=" + other + ", iconSkin=" + iconSkin
				+ ", p_id=" + p_id + ", p_no=" + p_no + ", nekey=" + nekey + ", isleaf=" + isleaf + ", isdept=" + isdept
				+ "]";
	}

}
