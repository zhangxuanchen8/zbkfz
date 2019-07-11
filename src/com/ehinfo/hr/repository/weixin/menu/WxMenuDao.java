package com.ehinfo.hr.repository.weixin.menu;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.weixin.menu.WxMenu;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

/**
 * 微信菜单数据层
 */
@JYBatis
public interface WxMenuDao  extends BaseDao<WxMenu>{
	/**
	 * 统计菜单数目
	 * @param o 对象      
	 * @return long
	 */
	public int menuCount(WxMenu o);
	
	public WxMenu getWxMenuByKeyId(@Param("keyId")String keyId);
}
