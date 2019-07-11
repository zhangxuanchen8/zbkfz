package com.ehinfo.hr.entity.system.sysmenu;

import org.apache.ibatis.type.Alias;

/**
 * 基础菜单
 */
@Alias("BaseMenu")
public class BaseMenu {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pId;
	private String name;
	private String url;
	private String image;
	private String menu_type;
	private String open_type;
	private String index_no;
	private String hotkeys;
	private String default_open;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getOpen_type() {
		return open_type;
	}
	public void setOpen_type(String open_type) {
		this.open_type = open_type;
	}
	public String getIndex_no() {
		return index_no;
	}
	public void setIndex_no(String index_no) {
		this.index_no = index_no;
	}
	public String getHotkeys() {
		return hotkeys;
	}
	public void setHotkeys(String hotkeys) {
		this.hotkeys = hotkeys;
	}
	public String getDefault_open() {
		return default_open;
	}
	public void setDefault_open(String default_open) {
		this.default_open = default_open;
	}
	@Override
	public String toString() {
		return "BaseMenu [id=" + id + ", pId=" + pId + ", name=" + name + ", url=" + url + ", image=" + image
				+ ", menu_type=" + menu_type + ", open_type=" + open_type + ", index_no=" + index_no + ", hotkeys="
				+ hotkeys + ", default_open=" + default_open + "]";
	}
	
	
}
