package com.ehinfo.hr.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehinfo.hr.entity.task.base.ScheduleJob;
import com.ehinfo.hr.task.utils.ScheduleUtils;
import com.ehinfo.hr.task.utils.TaskLogUtil;
/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
public class Test implements Job{
	
	   /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    
	@Override
	public void execute(JobExecutionContext context){	
	        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);  		   	     
	        String jobName=scheduleJob.getJobName();
			String jobGroup=scheduleJob.getJobGroup();
			String jobClass=scheduleJob.getJobClass();
			LOG.info("任务[" + jobName + "]成功运行");
	    	try {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("-------test----");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				// 保存日志
				TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.NORMAL, "测试任务正常运行");
			} catch (Exception e) {
				LOG.error("任务[" + jobName + "]异常",e);
				// 保存异常日志
				TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.EXCEPTION,e.toString());
			}
	}

	
}
