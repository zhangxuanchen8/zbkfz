package com.ehinfo.hr.service.zhibiaogl;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.sallot;
import com.ehinfo.hr.service.base.BaseService;

public interface sallotService extends BaseService<sallot>{
		public Page<sallot> findList(String openmonth, String ksname,Page<sallot> p);
}
