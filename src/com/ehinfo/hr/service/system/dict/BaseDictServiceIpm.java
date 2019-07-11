package com.ehinfo.hr.service.system.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.dict.BaseWordDict;
import com.ehinfo.hr.repository.system.dict.BaseDictDao;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("BaseDictService")
public class BaseDictServiceIpm extends BaseServiceImp<BaseDict> implements BaseDictService {

	@Autowired
	protected BaseDictDao dao;
	
	@Override
	public List<BaseDict> getColumneName( String nekey) {
		return dao.getColumneName( nekey);
	}

	@Override
	public int getdDictDetailsCount(String nekey, String contents , String hosnum) {
		return dao.getdDictDetailsCount(nekey, contents, hosnum);
	}
	
	@Override
	public List<BaseDict> getdDictDetails(String nekey, String contents,String hosnum,int indexStart, int indexEnd) {
		return dao.getdDictDetails(nekey, contents, hosnum,indexStart, indexEnd);
	}

	@Override
	public String getdefaultNekey() {
		return dao.getdefaultNekey();
	}

	@Override
	public BaseDict findByDictId(String dictid) {
		return dao.findByDictId(dictid);
	}

	@Override
	public int updateByDictId(BaseDict baseDict) {
		return dao.updateByDictId(baseDict);
	}

	@Override
	public int add(BaseDict baseDict) {
		return dao.add(baseDict);
	}

	@Override
	public int deleteByDictId(String dictid) {
		return dao.deleteByDictId(dictid);
	}

	@Override
	public BaseDict checkNevalue(String nevalue, String nekey,String dictid,String hosnum) {
		return dao.checkNevalue(nevalue, nekey,dictid,hosnum);
	}

	@Override
	public List<BaseWordDict> findPyWb(List<String> cnchar) {
		return dao.findPyWb(cnchar);
	}

	@Override
	public List<BaseDict> selectDictByNekey(BaseDict nekey) {
		return dao.selectDictByNekey(nekey);
	}

	@Override
	public List<BaseDict> isInDictByNekey(String str, String nekey) {
		return dao.isInDictByNekey(str,nekey);
	}

	@Override
	public List<BaseDict> getDictByContents(int nekey, String contents) {
		return dao.getDictByContents(nekey,contents);
	}

	@Override
	public void updatexinziwh(BaseDict nekey) {
		dao.updatexinziwh(nekey);
	}

	@Override
	public BaseDict findnevalue(BaseDict nekey) {
		return dao.findnevalue(nekey);
	}

	@Override
	public List<BaseDict> finddengji(BaseDict nekey) {
		return dao.finddengji(nekey);
	}

	@Override
	public List<BaseDict> findNevalue(BaseDict nekey) {
		return dao.findNevalue(nekey);
	}
	@Override
	public List<BaseDict> selectDictByNekeys(BaseDict nekey) {
		return dao.selectDictByNekeys(nekey);
	}
}
