package com.ehinfo.hr.service.achieve;

import java.util.List;
import java.util.Map;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;

import com.ehinfo.hr.entity.achieve.Achieve;

/**
 * @author LHB
 * @date 2018/8/15 18:56
 */
public interface AchieveService {

    public List<Map<String,String>> findth(String hosnum);

    public List<Map<String,String>> findth_model(String hosnum);

    public List<Map<String,String>> findth_all(String hosnum);

    public List<Map<String,String>> findth_first(String hosnum);

    public List<Map<String,String>> findjx_gongshi(String hosnum,String deptid);

    public String lasttjrqe(String hosnum);

    public List<Map<String,String>> findtd(String hosnum);

    public List<Map<String,String>> doornot(String startdate , String enddate,String hosnum);

    public double find_sql(String sql);

    public int insert(Achieve ach);
    
    public int update(Achieve ach);

    public int update_sql(String sql);

    public void insert_sql(String sql);

    public void chushihua(String startdate,String enddate,String hosnum);

    public List<String>getsjd(String hosnum);

    public int insertday(String ach);
    
    public List<Achieve>getxfrq(String option,String sdate,String edate);
    
    public void delete(String id);
    
    public List<String> getsfupd(String time);

    public List<Map<String,String>> findHead_sflb();

    public List<String> findOption_sflb();
    
    
    public List<Map<String,String>> find_sqllist(String sql);

    public Page<fenpeiks> findLastValue_sflb(String person_dept,String tjrqs,String tjrqe, Page<fenpeiks> p);
}
