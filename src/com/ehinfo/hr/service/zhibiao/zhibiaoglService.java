package com.ehinfo.hr.service.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.service.base.BaseService;

public interface zhibiaoglService extends BaseService<zbkscore>{
	public List<zbkscore> findyear(String year);
	public int updatetjbz(zbkscore score);
	public Page<zbkscore> findtjym(zbkscore score,Page<zbkscore> page);
	public List<zbkscore> findtjfszf(zbkscore score);
	public int insert_score(List<zbkscore> list);
	public int delete_score(String id,List<zbkscore> list);
	public List<zbkscore> findzbkrenyear();
	public List<zbkscore> findnums(zbkscore z);
	public List<zbkscore> findmaxscore(String id,String dept);
	public List<zbkscore> findPid(String id,String dept);
	public void updatescrys(List<String> list, String state);
	public List<zbkscore> findmax(zbkscore z);
	public List<zbkscore> findsum(zbkscore z);
	public void insert_scores(List<zbkscore> list);
	public void delete_scores(zbkscore z);
	public void insert_scoress(List<zbkscore> list);
	public void delete_scoress(zbkscore z);
	public List<zbkscore> findsmax(zbkscore z);
	public List<zbkscore> findssum(zbkscore z);
	public zbkscore findsbyitemid(zbkscore z);
	public void deleteitemid(zbkscore z);
	public zbkscore findsbyitemids(zbkscore z);
	
	public List<zbkscore> findssumfin(zbkscore z);
	
	public String findsumscore(String itemid,String id);
	
	public zbkscore findsbyname(zbkscore z);
	public void updatess(zbkscore zbk);
	
	public List<zbkscore> findsbynames(zbkscore z);
	
	public List<zbkscore> findsbydept(zbkscore z);
	
	public List<zbkscore> findnumstj(zbkscore z);
}
