package com.ehinfo.hr.repository.weixin.user;

import java.util.List;

import com.ehinfo.hr.entity.weixin.user.WxFollower;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;


/**
 * 微信关注者数据层
 */
@JYBatis
public interface WxFollowerDao  extends BaseDao<WxFollower>{
 
    public void clearFollower();
    
    public void insertFollowers(List<WxFollower> o);
}
