package com.ehinfo.hr.repository.achieve;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.achieve.Achieve;
import com.ehinfo.hr.entity.zhibiaogl.fenpeiks;
import com.ehinfo.hr.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author LHB
 * @date 2018/8/16 9:56
 */
@JYBatis
public interface AchieveDao{
    public List<Map<String,String>> findth(String hosnum);

    public List<Map<String,String>> findth_model(String hosnum);

    public List<Map<String,String>> findth_all(String hosnum);

    public List<Map<String,String>> findth_first(String hosnum);

    public List<Map<String,String>> findtd(String hosnum);

    public List<Map<String,String>> findjx_gongshi(@Param("hosnum")String hosnum,@Param("deptid")String deptid);

    public String lasttjrqe(@Param("hosnum")String hosnum);

    public double find_sql(@Param("sql")String sql);

    public List<Map<String,String>> find_sqllist(@Param("sql")String sql);

    public int insert(Achieve ach);
    
    public int update(Achieve ach);

    public int update_sql(@Param("sql")String sql);

    public void insert_sql(@Param("sql")String sql);

    public List<Map<String,String>> doornot(@Param("hosnum")String hosnum,@Param("startdate")String startdate,@Param("enddate")String enddate);

    public List<Map<String,String>> doornot2(@Param("hosnum")String hosnum,@Param("startdate")String startdate,@Param("enddate")String enddate);

    public Map<String,String> daytomonth(@Param("hosnum")String hosnum,@Param("startdate")String startdate,@Param("enddate")String enddate,@Param("deptid")String deptid,@Param("deptid_jx")String deptid_jx,@Param("deptname")String deptname);

    public void chushihua(@Param("startdate")String startdate,@Param("enddate")String enddate);

    public List<String> getsjd(@Param("hosnum")String hosnum);

    public List<Achieve> getxfrq(@Param("option")String option,@Param("sdate")String sdate,@Param("edate")String edate);
    
    public int insertday(@Param("sql")String ach);
    
    public void delete(@Param("id")String id);
    
    public List<String> getsfupd(@Param("time")String time);

    public List<Map<String,String>> findHead_sflb();

    public List<String> findOption_sflb();

    public List<fenpeiks> findLastValue_sflb(@Param("person_dept")String person_dept, @Param("tjrqs")String tjrqs, @Param("tjrqe")String tjrqe, @Param("list")List<String> list, @Param("page")Page<fenpeiks> p);

}
