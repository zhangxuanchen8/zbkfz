package com.ehinfo.hr.service.zhibiaogl;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.zhibiaokh;
import com.ehinfo.hr.service.base.BaseService;

public interface zhibiaokhService extends BaseService<zhibiaokh>{
		public List<zhibiaokh> findzbktype(String hosnum); 
		public Page<zhibiaokh> findByList(zhibiaokh zbk,Page<zhibiaokh> p);
		public List<zhibiaokh> isDeptCompute(String s,String id);
		public String findDpetname(String s);
}
