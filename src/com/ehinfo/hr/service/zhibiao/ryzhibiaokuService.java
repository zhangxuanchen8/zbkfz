package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.ryzbk;
import com.ehinfo.hr.service.base.BaseService;

public interface ryzhibiaokuService extends BaseService<ryzbk>{
		public List<ryzbk> findpeoplelist(String hosnum);
		public List<ryzbk> findpeoplename(String id);
		public Page<ryzbk>findpeoplepage(ryzbk string,Page<ryzbk> page);
		public List<ryzbk> findzbkren(String hosnum);
}
