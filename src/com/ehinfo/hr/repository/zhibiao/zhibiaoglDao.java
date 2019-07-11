package com.ehinfo.hr.repository.zhibiao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiao.zbkscore;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface zhibiaoglDao extends BaseDao<zbkscore>{
	public int count(String supunit);
	public List<zbkscore> findyear(String year);
	public void updatetjbz(zbkscore score);
	public List<zbkscore> findtjym(@Param("param")zbkscore score,Page<zbkscore> page);
	public List<zbkscore> findtjfszf(@Param("param")zbkscore score);
	public void insert_score(List<zbkscore> list);
	public void delete_score(@Param("id")String id,@Param("clist")List<zbkscore> list);
	public List<zbkscore> findzbkrenyear();
	public List<zbkscore> findnums(zbkscore z);
	public List<zbkscore> findPid(@Param("id")String id,@Param("dept")String dept);
	public List<zbkscore> findmaxscore(@Param("id")String id,@Param("dept")String dept);
	public void updatescrys(@Param("slist")List<String> list,@Param("state")String state);
	public List<zbkscore> findmax(zbkscore z);
	public List<zbkscore> findsum(zbkscore z);
	public void insert_scores(List<zbkscore> list);
	public List<zbkscore> findsmax(zbkscore z);
	public List<zbkscore> findssum(zbkscore z);
	public void delete_scores(zbkscore score);
	public void insert_scoress(List<zbkscore> list);
	public void delete_scoress(zbkscore score);
	public zbkscore findsbyitemid(zbkscore score);
	public void deleteitemid(zbkscore score);
	public zbkscore findsbyitemids(zbkscore score);
	public List<zbkscore> findssumfin(zbkscore z);
	
	public String findsumscore(@Param("itemid")String itemid,@Param("id")String id);
	public void updatess(zbkscore score);
	public zbkscore findsbyname(zbkscore score);
	public List<zbkscore> findsbynames(zbkscore z);
	public List<zbkscore> findsbydept(zbkscore z);
	public List<zbkscore> findnumstj(zbkscore z);
}
