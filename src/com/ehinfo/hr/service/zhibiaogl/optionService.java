package com.ehinfo.hr.service.zhibiaogl;

import java.util.List;

import com.ehinfo.hr.entity.zhibiaogl.OptionName;
import com.ehinfo.hr.service.base.BaseService;

public interface optionService extends BaseService<OptionName> {

	List<OptionName> getOption(String option, String hosnum);

	void deleteOriginal(String hosnum, String option_name);

	void insertMulti(String[] chk, String option_name);

}
