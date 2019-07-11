package com.ehinfo.hr.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.service.system.dict.BaseDictService;
@Component
public class DictUtil {
	@Autowired
	private BaseDictService service;
	private static DictUtil dictUtil;
	public BaseDictService getService() {
		return service;
	}
	public void setService(BaseDictService service) {
		this.service = service;
	}
	@PostConstruct
	public void init(){
		dictUtil= this;
		dictUtil.service=this.service;
	}
	public static boolean IsInDict(String str,String nekey){
		List<BaseDict> list = new ArrayList<BaseDict>();
		try {
			list=dictUtil.service.isInDictByNekey(str, nekey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
}
