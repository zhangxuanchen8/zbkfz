package com.ehinfo.hr.service.zhibiaoku;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaoku.zhibiaoku;
import com.ehinfo.hr.service.base.BaseService;

public interface zhibiaokuService extends BaseService<zhibiaoku>{
		public List<zhibiaoku> findzbktype(String hosnum);
		public Page<zhibiaoku> findByList(zhibiaoku zbk,Page<zhibiaoku> p);
		public void softdelete(List<zhibiaoku> list);
		public List<String> isUseOption(String hosnum);
		public List<zhibiaoku> findzbkcj(String hosnum,String name);
}
