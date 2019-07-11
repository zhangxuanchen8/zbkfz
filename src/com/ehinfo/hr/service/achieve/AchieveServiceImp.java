package com.ehinfo.hr.service.achieve;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.achieve.Achieve;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;
import com.ehinfo.hr.repository.achieve.AchieveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author LHB
 * @date 2018/8/15 18:56
 */
@Service("AchieveService")
public class AchieveServiceImp implements AchieveService {

    @Autowired
    private AchieveDao dao;

    @Override
    public List<Map<String, String>> findth(String hosnum) {
        return dao.findth(hosnum);
    }

    @Override
    public List<Map<String, String>> findth_model(String hosnum) {
        return dao.findth_model(hosnum);
    }

    @Override
    public List<Map<String, String>> findth_all(String hosnum) {
        return dao.findth_all(hosnum);
    }

    @Override
    public List<Map<String, String>> findth_first(String hosnum) {
        return dao.findth_first(hosnum);
    }

    @Override
    public List<Map<String, String>> doornot(String startdate , String enddate,String hosnum) {
        return dao.doornot(hosnum, startdate ,  enddate);
    }

    @Override
    public String lasttjrqe(String hosnum) {
        return dao.lasttjrqe(hosnum);
    }

    @Override
    public List<Map<String, String>> findjx_gongshi(String hosnum, String deptid) {
        deptid = "%"+deptid+"%";
        return dao.findjx_gongshi(hosnum,deptid);
    }

    @Override
    public List<Map<String, String>> findtd(String hosnum) {
        return dao.findtd(hosnum);
    }

    @Override
    public double find_sql(String sql) {
        return dao.find_sql(sql);
    }

	@Override
	public int insert(Achieve ach) {
		int i=0;
		i=dao.insert(ach);
		if(i>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int update(Achieve ach) {
		int i=0;
		i=dao.update(ach);
		if(i>0){
			return 1;
		}
		return 0;
	}

	@Override
	public int insertday(String ach) {
		int i=0;
		i=dao.insertday(ach);
		if(i>0){
			return 1;
		}

		return 0;
	}

    @Override
    public int update_sql(String sql) {
        int i=0;
    	i=dao.update_sql(sql);
        return i;
    }

    @Override
    public void insert_sql(String sql) {
        dao.insert_sql(sql);
    }

    @Override
    public void chushihua(String startdate , String enddate,String hosnum) {

        List<Map<String, String>> map_res = dao.doornot(hosnum, startdate ,  enddate);
        map_res = dao.doornot2(hosnum, startdate ,  enddate);
        if(map_res==null || map_res.size()==0){

            List<Map<String, String>> map_l = new ArrayList<Map<String, String>>();
            String sql = "select * from his_dept order by INDEXNUM";
            List<Map<String,String>> jxdept = dao.find_sqllist(sql);
            for(Map<String , String> m:jxdept){
                if(m.get("HISCODE").indexOf(",")==-1){
                    map_l.add(dao.daytomonth(hosnum, startdate ,  enddate,"'"+m.get("HISCODE")+"'",m.get("DEPTCODE"),m.get("HISNAME")));
                }else{
                    String jx_dept = m.get("HISCODE");
                    jx_dept = "'"+jx_dept.replaceAll(",","','")+"'";
                    map_l.add(dao.daytomonth(hosnum, startdate ,  enddate,jx_dept,m.get("DEPTCODE"),m.get("HISNAME")));
                }
            }
            List<Map<String, String>> map_l_th = dao.findth_all(hosnum);



            for(Map<String,String> map2:map_l){
                String insert = "insert into fenpei_ks_month (rs,id, hosnum,tjrqs,tjrqe,deptid,deptname";
                ArrayList<String> str_l = new ArrayList<String>();
                for(Map<String,String> map1:map_l_th){
                    str_l.add(map1.get("ZIDUAN"));
                    insert+=","+map1.get("ZIDUAN");
                }
                insert+=") values (";
                insert+="'"+String.valueOf(map_l.get(0).get("RS"))+"',";
                insert+="'"+ String.valueOf(UUID.randomUUID())+"',";
                insert+="'"+map_l.get(0).get("HOSNUM")+"',";
                insert+="to_date('"+startdate+"','yyyy-mm-dd hh24:mi'),";
                insert+="to_date('"+enddate+"','yyyy-mm-dd hh24:mi')";
                insert+=",'"+map2.get("DEPTID")+"'";
                insert+=",'"+map2.get("DEPTNAME")+"'";
                for(int i=0;i<str_l.size();i++){
                    String option = str_l.get(i);
                    insert+=",'"+String.valueOf(map2.get(option))+"'";
                }
                dao.insert_sql(insert+")");
            }


        }else{
            dao.chushihua(startdate , enddate);
        }
    }

    @Override
    public List<String> getsjd(String hosnum) {
        return dao.getsjd(hosnum);
    }

	@Override
	public List<Achieve> getxfrq(String option,String sdate,String edate) {
		return dao.getxfrq(option,sdate,edate);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public List<String> getsfupd(String time) {
		return dao.getsfupd(time);
	}

    @Override
    public List<Map<String, String>> findHead_sflb() {
        return dao.findHead_sflb();
    }

    @Override
    public List<String> findOption_sflb() {
        return dao.findOption_sflb();
    }

    @Override
    public Page<fenpeiks> findLastValue_sflb(String person_dept, String tjrqs, String tjrqe, Page<fenpeiks> p) {
        List<String> list=dao.findOption_sflb();
        p.setResults(dao.findLastValue_sflb(person_dept,tjrqs,tjrqe,list,p));
        return p;
    }

	@Override
	public List<Map<String,String>> find_sqllist(String sql) {
		return dao.find_sqllist(sql);
	}
}
