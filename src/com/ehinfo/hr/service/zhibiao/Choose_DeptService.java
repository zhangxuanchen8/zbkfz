package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import com.ehinfo.hr.entity.zhibiao.Choose_Dept;
import com.ehinfo.hr.service.base.BaseService;

public interface Choose_DeptService extends BaseService<Choose_Dept>{
	public int checkZ_name(Choose_Dept o);//验证科室名称
	public int deleteDept(Choose_Dept o);//删除群组科室
	public List<Choose_Dept> getZhiBiaoGroupTree(Choose_Dept o);//获取群组科室名称
}
