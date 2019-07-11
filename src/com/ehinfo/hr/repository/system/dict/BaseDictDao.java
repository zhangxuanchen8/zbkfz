package com.ehinfo.hr.repository.system.dict;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.dict.BaseWordDict;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
/*
 * 基础字典
 */
@JYBatis
public interface BaseDictDao extends BaseDao<BaseDict> {
	/**
	 * 获取字典数据列名
	 * @return
	 */
	public List<BaseDict> getColumneName(@Param(value="nekey")String nekey);
	
	/**
	 * 获取详细数据
	 * @param hosnum 
	 * @return
	 */
	public List<BaseDict> getdDictDetails(@Param(value="nekey")String nekey,@Param(value="contents")String contents,@Param(value="hosnum") String hosnum, @Param(value="indexStart")int indexStart,@Param(value="indexEnd")int indexEnd);
	
	/**
	 * 获取count
	 * @param hosnum 
	 * @return
	 */
	public int getdDictDetailsCount(@Param(value="nekey")String nekey,@Param(value="contents")String contents,@Param(value="hosnum") String hosnum);
	
	/**
	 * 获取defaultNekey
	 * @return
	 */
	public String getdefaultNekey();

	/**
	 * 根据dictid查详细信息
	 * @param dictid
	 * @return
	 */
	public BaseDict findByDictId(@Param("dictid")String dictid);
	
	/**
	 * 根据dictid删除字典
	 * @param dictid
	 * @return
	 */
	public int deleteByDictId(@Param("dictid")String dictid);
	
	/**
	 * 修改字典
	 * @param dictid
	 * @return
	 */
	public int updateByDictId(BaseDict baseDict);
	
	/**
	 * 增加字典
	 * @param dictid
	 * @return
	 */
	public int add(BaseDict baseDict);
	/**
	 * 检查nevalue是否存在
	 * @param nevalue
	 * @param hosnum 
	 * @return
	 */
	public BaseDict checkNevalue(@Param("nevalue")String nevalue,@Param("nekey")String nekey,@Param("dictid")String dictid, @Param("hosnum") String hosnum);
	
	/**
	 * 根据汉字查找拼音跟五笔
	 * @param nevalue
	 * @return
	 */
	public List<BaseWordDict> findPyWb(List<String> cnchar);

	public List<BaseDict> selectDictByNekey(BaseDict nekey);

	public List<BaseDict> isInDictByNekey(@Param("nevalue")String str, @Param("nekey")String nekey);

	public List<BaseDict> getDictByContents(@Param("nekey")int nekey, @Param("contents")String contents);
	
	public void updatexinziwh(BaseDict nekey);
	
	public BaseDict  findnevalue(BaseDict nekey);
	
	public List<BaseDict>finddengji(BaseDict nekey);
	
	public List<BaseDict> findNevalue(BaseDict nekey);

	public List<BaseDict> selectDictByNekeys(BaseDict nekey);
}
