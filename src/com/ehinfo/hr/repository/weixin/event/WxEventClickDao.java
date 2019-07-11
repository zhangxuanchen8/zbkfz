package com.ehinfo.hr.repository.weixin.event;

import java.util.List;

import com.ehinfo.hr.entity.weixin.event.WxEventClick;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
/**
 * 微信点击事件数据层
 */
@JYBatis
public interface WxEventClickDao  extends BaseDao<WxEventClick>{

	/**
	* 批量插入微信点击事件
	* @param o 微信点击事件集合
	*/
	public void insertItems(List<WxEventClick> o);
	
}
