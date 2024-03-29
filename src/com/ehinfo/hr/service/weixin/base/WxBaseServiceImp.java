package com.ehinfo.hr.service.weixin.base;

import javax.annotation.PostConstruct;

import com.ehinfo.hr.common.utils.weixin.api.WechatAPI;
import com.ehinfo.hr.common.utils.weixin.api.WechatAPIImpl;
import com.ehinfo.hr.common.utils.weixin.util.ConfigReader;
import com.ehinfo.hr.common.utils.weixin.vo.MPAccount;
import com.ehinfo.hr.service.base.BaseServiceImp;


public class WxBaseServiceImp<T> extends BaseServiceImp<T> implements WxBaseService<T>{

	protected static WechatAPI ua;
	
	protected static ConfigReader _cr;
	
    protected MPAccount mpAct;
	
    protected static String accessToken;
    
    @PostConstruct
	public void init() {
		_cr = new ConfigReader("/weixin/mp.properties");
		mpAct = new MPAccount();
		mpAct.setMpId(_cr.get("mpId"));
		mpAct.setAppId(_cr.get("appId"));
		mpAct.setAppSecret(_cr.get("appSecret"));
		mpAct.setAESKey(_cr.get("aseKey"));
		accessToken = _cr.get("accessToken");
		ua = WechatAPIImpl.create(mpAct);
	}
}
