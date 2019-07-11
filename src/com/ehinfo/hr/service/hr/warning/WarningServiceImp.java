package com.ehinfo.hr.service.hr.warning;

import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.entity.hr.warning.Warning;
import com.ehinfo.hr.entity.hr.warning.WarningCon;
import com.ehinfo.hr.entity.task.base.ScheduleJob;
import com.ehinfo.hr.repository.hr.warning.WarningDao;
import com.ehinfo.hr.repository.task.base.ScheduleJobDao;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("WarningService")
public class WarningServiceImp extends BaseServiceImp<Warning> implements WarningService {
	@Autowired
	WarningDao dao;
	@Autowired
	ScheduleJobDao task;
	
	@Override
	@Transactional
	public void insert(Warning o) {
		ScheduleJob job = new ScheduleJob();
		job.setJobGroup(o.getWarn_type());
		job.setJobName(o.getWarn_name());
		job.setCronExpression(o.getGetwarn_cyc());//-----执行周期-----
		job.setStatus(1);//-----1是启用的意思
		job.setCreateTime(new Date());
		String uuid = UuidUtil.get32UUID();
		job.setScheduleJobId(uuid);
		job.setDescription(o.getId());//----先用他存预警的定义ID，用来获取sql语句
		job.setJobClass("com.ehinfo.hr.controller.hr.warning.WarningJobController");//-------------先用一个固定的类
		job.setAliasName("预警");
		task.insert(job);
		o.setWarn_jobid(uuid);
		dao.insert(o);
	}
	@Override
	@Transactional
	public void update(Warning o) {
		Warning old = new Warning();
		old.setId(o.getId());
		old = dao.findById(old);
		if(old!=null&&Tools.notEmpty(old.getWarn_jobid())){
			ScheduleJob job = new ScheduleJob();
			job.setScheduleJobId(old.getWarn_jobid());
			job.setJobGroup(o.getWarn_type());
			job.setJobName(o.getWarn_name());
			job.setCronExpression(o.getGetwarn_cyc());//-----执行周期-----
			job.setStatus(1);//-----1是启用的意思
			job.setUpdateTime(new Date());
			job.setDescription(o.getId());//----先用他存预警的定义ID，用来获取sql语句
			job.setJobClass("com.ehinfo.hr.controller.hr.warning.WarningJobController");//-------------先用一个固定的类
			job.setAliasName("预警");
			task.update(job);
		}
		
		dao.update(o);
	}
	@Override
	@Transactional
	public void deleteBatch(List<Warning> os){
		for(Warning w:os){
			w  = dao.findById(w);
			ScheduleJob job = new ScheduleJob();
			if(w!=null&&Tools.notEmpty(w.getWarn_jobid())){
				job.setScheduleJobId(w.getWarn_jobid());
				task.delete(job);
			}
		}
		dao.deleteBatch(os);
	}
	@Override
	public List<Warning> fingByType(Warning wn) {
		// TODO Auto-generated method stub
		return dao.findByType(wn);
	}
	@Override
	public List<Warning> findWarning(Warning wn) {
		return dao.findWarning(wn);
	}
	@Override
	public List<WarningCon> findWarnName(String[] warn_ids) {
		return dao.findWarnName(warn_ids);
	}
}
