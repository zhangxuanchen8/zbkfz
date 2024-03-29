package com.ehinfo.hr.task.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.ehinfo.hr.common.utils.SpringWebContextUtil;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.entity.task.log.TaskLog;
import com.ehinfo.hr.service.task.log.TaskLogService;

public class TaskLogUtil {
	/** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskLogUtil.class);
	
	private static TaskLogService service;
	
	public final static int NORMAL=1;
	
	public final static int EXCEPTION=2;
	
	public static TaskLogService getTaskLogService(){
		if(service==null){
			ApplicationContext ac =SpringWebContextUtil.getApplicationContext();
			service = (TaskLogService) ac.getBean("TaskLogService");
		}
		return service;
	}
	
	public static void saveTaskLog(String name,String className,int type,String dec){
		TaskLog o =new TaskLog(UuidUtil.get32UUID(),name,className,type,dec,new Date());
		saveTaskLog(o);
	}
	
	public static void saveTaskLog(TaskLog o){
		try {
			getTaskLogService().insert(o);
		} catch (Exception e) {
			LOG.error("保存任务日志失败",e);
		}
	}
	
}
