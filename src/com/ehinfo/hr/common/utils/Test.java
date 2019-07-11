package com.ehinfo.hr.common.utils;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Test implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Date date= new Date();
		System.out.println(date.toString());
	}
}
