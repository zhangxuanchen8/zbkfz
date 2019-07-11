package com.ehinfo.hr.service.weixin.user;

import com.ehinfo.hr.entity.weixin.user.WxFollower;
import com.ehinfo.hr.service.weixin.base.WxBaseService;

public interface WxFollowerService extends WxBaseService<WxFollower>{

	/**
	 * 同步微信服务器关注者数据
	 */
	public void syncFollower();
}
