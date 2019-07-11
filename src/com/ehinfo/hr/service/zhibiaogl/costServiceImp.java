package com.ehinfo.hr.service.zhibiaogl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.cost;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;
import com.ehinfo.hr.repository.zhibiaogl.costDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

@Service("costService")
public class costServiceImp extends BaseServiceImp<fenpeiks> implements costService{
	@Autowired
	private costDao dao;
	
//	@Override
//	public Page<cost> detailFind(String openmonth,String ksname, Page<cost> p) {
//		p.setResults(dao.detailFind(openmonth,ksname,p));
//		return p;
//	}
//	@Override
//	public Page<cost> FindLastCost(String deptid, Page<cost> p) {
//		p.setResults(dao.FindLastCost(deptid,p));
//		return p;
//	}
	@Override
	public List<cost> findDeptList(String hosnum) {
		return dao.findDeptList(hosnum);
	}
	@Override
	public String saveEmps(JSONArray array) {
		int	num=0;
		fenpeiks o = new fenpeiks();
		Iterator it = array.iterator();
		SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
		List<String> option=dao.findOption();
		while(it.hasNext()){
			JSONObject ob = (JSONObject)it.next();
			Date costdate;
			try {
//				costdate = ft.parse(ob.getString("jxrq"));
//				o.setId(com.ehinfo.hr.common.utils.base.UuidUtil.get32UUID());
//				o.setJxrq(costdate);
//				o.setDeptid(ob.getString("deptid"));
				//一个科室一个月我觉得只能有一个结果，所以导入的时候，我先把旧的清掉
//				dao.deletekslast(costdate,ob.getString("deptid"));
				JsonConfig jsonc=new JsonConfig();
				jsonc.setRootClass(fenpeiks.class);
				JSONUtils.getMorpherRegistry().registerMorpher((new DateMorpher(new String[]{"yyyy-MM-dd HH:m:ss"})));
				fenpeiks fenpeiks=(fenpeiks) JSONObject.toBean(ob,jsonc);
//				fenpeiks.setId(com.ehinfo.hr.common.utils.base.UuidUtil.get32UUID());
//				dao.insert(fenpeiks);
				//方法修改，导入时已经保证数据有这个科室的其他数据，我们只需要更新他的字段,想当然的方法实现有困难，先用麻烦的反射机制在代码逻辑里实现动态更新列
				ArrayList<fenpeiks> value=new ArrayList<fenpeiks>();
				Class class1=fenpeiks.getClass();
				Field[] fields=class1.getDeclaredFields();
				for(String col:option){
					for(int i=0;i<fields.length;i++){
						if(fields[i].getName().equals(col)){
							fields[i].setAccessible(true);
							String deptname=(String) fields[i].get(fenpeiks);
							fenpeiks fenpeiks2=new fenpeiks();
							fenpeiks2.setDeptid(col);
							fenpeiks2.setDeptname(deptname);
							value.add(fenpeiks2);
						}
					}
				}
				if(value.size()<1){
					return null;
				}
				dao.updateoption(value,fenpeiks);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public void affirm(String person_dept) {
		dao.affirm(person_dept);
	}
	@Override
	public void returnaffirm(String person_dept) {
		dao.returnaffirm(person_dept);
	}
	@Override
	public List<cost> ifaffirm(String person_dept) {
		return dao.isaffirm(person_dept);
	}
	
	
	@Override
	public List<String> findHead() {
		return dao.findHead();
	}
	@Override
	public List<String> findOption() {
		return dao.findOption();
	}
	@Override
	public Page<fenpeiks> findOptionValue(String openmonth,String ksname,String hosnum,Page<fenpeiks> p) {
		List<String> list=dao.findOption();
		p.setResults(dao.findOptionValue(openmonth,ksname,hosnum,list,p));
		return p;
	}
	@Override
	public Page<fenpeiks> findLastValue(String person_dept,String tjrqs,String tjrqe, Page<fenpeiks> p) {
		List<String> list=dao.findOption();
		p.setResults(dao.findLastValue(person_dept,tjrqs,tjrqe,list,p));
		return p;
	}
	
	@Override
	public List<String> findHead_jx() {
		return dao.findHead_jx();
	}
	@Override
	public List<String> findOption_jx() {
		return dao.findOption_jx();
	}
}
