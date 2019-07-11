package com.ehinfo.hr.repository.task.base;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.task.base.ScheduleJob;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
/**
 * 动态任务数据层
 */
@JYBatis
public interface ScheduleJobDao extends BaseDao<ScheduleJob>{
	/**
	 * 根据Id获取任务
	 */
	public ScheduleJob getScheduleJobById(@Param("scheduleJobId")String scheduleJobId);
	
}
