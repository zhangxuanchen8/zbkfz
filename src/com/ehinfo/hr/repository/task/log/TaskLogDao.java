package com.ehinfo.hr.repository.task.log;

import com.ehinfo.hr.entity.task.log.TaskLog;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
/**
 * 动态任务数据层
 */
@JYBatis
public interface TaskLogDao extends BaseDao<TaskLog>{
	
}
