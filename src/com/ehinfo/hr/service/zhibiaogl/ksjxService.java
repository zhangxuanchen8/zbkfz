package com.ehinfo.hr.service.zhibiaogl;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.ksjx;
import com.ehinfo.hr.service.base.BaseService;

public interface ksjxService extends BaseService<ksjx>{
		public Page<ksjx> findList(String openmonth, String ksname, Page<ksjx> p);
}
