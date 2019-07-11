package com.ehinfo.hr.entity.system.employee;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import com.ehinfo.hr.entity.base.BaseEntity;

/**
 * 员工信息表（HR_EMPLOYEE）
 * 
 * @author ringerjiang
 *
 */
@Alias("BaseEmployee")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String p_id;
	private String c_name;
	private int age;
	private String maternity_ins;
	private int p_num;
	private String sex;
	private Date birthday;
	private String polity_face;
	private String polity_face_status;
	private Date join_party_date;
	private String id_card;
	private String nation;
	private String religion;
	private String native_place;
	private String home_address;
	private String home_tel;
	private String mobile_tel;
	private String departmentid;
	private String office_tel;
	private String email;
	private String health;
	private String job_kind;
	private String laborage_kind;
	private String p_status;
	private String hosnum;
	private String edu_level_f;
	private String science_headship;
	private Date first_work_date;
	private Date join_unit_date;
	private Date bargain_end_date;
	private String engage_headship;
	private Date engage_headship_date;
	private String ismarried;
	private Date level_unit_date;
	private String remark;
	private Integer work_years_before;
	private Integer work_year;
	private Integer probation;
	private String lixiu;
	private String treatment;
	public String getLixiu() {
		return lixiu;
	}
	public void setLixiu(String lixiu) {
		this.lixiu = lixiu;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	private Integer unit_work_year;
	private String deptname;
	private String p_no;
	private String graduate_school;
	public Integer getProbation() {
		return probation;
	}
	public void setProbation(Integer probation) {
		this.probation = probation;
	}
	private String s_type;
	private Integer s_salary;
	private Date s_date;
	private String user01;
	private String user02;
	private String user03;
	private String user04;
	private String user05;
	private String user06;
	private String user07;
	private String user08;
	private String user09;
	private String user10;
	private String p_source;
	private String zc_name;
	private String edu_major_f;
	private String edu_major_t;
	private String edu_school_t;
	private String zc_type;
	private String zc_level;
	private String edu_level_t;
	private String image_path;
	private String image_url;
	private String post_type;
	private String attendance_departmentid;// 考勤科室  
	private String kq_deptname;
    private String n_status;
    private String personnel_reduction;
	private String technical_job;
	private String technical_classify1;
	private String technical_classify2;
	private String practice_name;
	private String practice_type;
	private String practice_area;
	private String paixv;//用于前台分页传排序参数
	private String edu_top_id;
	private String edu_init_id;
	private String polity_id;
	private String zc_id;
	private String zg_id;
	private String post_id;
	private String work_js_id;
	private String work_gl_id;
	private String work_gq_id;
	private String ht_id;
	private String salary_id;
	private String deptinfo_id;
	private String technical_classify3;
	private String manage_job;
	private String manage_classify1;
	private String security_job;
	private String security_classify1;
	private String level_exe_date;
	private String post_level;
	private String salary_type;
	private String post_scale;
	private String salary_source;
	private String base_salary;
	private String bdid;
	private String keyWord;
	private List<String> searchbypstatus;
	private List<String> list;
	private String ys_flag;
	private String gz_flag;
	private String post_rank_sjt;
	private String manager_flag;
	private String isinjob;
	private String warn_id;
	private String[] warn_idlist;
	private String warn_name;
	private String warn_status;
	private Date warn_gettime;
	private String warnCon_id;
	private String come_source;
	private String p_gone;
	private String post_status;
	//-----------工资--------------
	//si.POST_SALARY,si.SCALE_SALARY,si.NURSE10,si.GWJT,si.BASE_SALARY
	private double post_salary;
	private double scale_salary;
	private double nurse10_salary;
	private double gwjt;
	private String p_status_t;//事业编制分类 干部 合同制工人  全民固定工
	
	
	private String hosname;
	private String remarka;
	
	private String selected = "0" ; //是否选中1-选中，在人员申报模块中使用
	
	
	//科室，岗位，工作调动
	private String id;//主键id
	private Date apply_time;//申请时间
	private String state;//状态（1.申请调动2.审核通过3.调动成功）
	private String after_technical_job;//专技工作
	private String after_manage_job;//管理工作
	private String after_security_job;//保障工作
	private String after_id_card;//被调动人员的身份证号
	private String dept_redeploy_before_id;//当前科室id
	private String post_redeploy_before;//当前岗位
	private String work_redeploy_before;//当前工作
	private String dept_redeploy_after_id;//目标科室id
	private Date dept_redeploy_time;//科室调动时间
	private String post_redeploy_after_classify;//目标岗位分类
	private String post_redeploy_after_mc;//目标岗位名称
	private Date post_redeploy_time;//岗位调动时间
	private String after_technical_classify1;//专技分类1
	private String after_technical_classify2;//专技分类2
	private String after_technical_classify3;//专技分类3
	private String after_manage_classify1;//管理分类1
	private String after_security_classify1;//保障分类1
	private Date work_redeploy_time;//工作调动时间
	private String dept_redeploy_after_mc;//目标科室名称
	

	public String getP_status_t() {
		return p_status_t;
	}
	public void setP_status_t(String p_status_t) {
		this.p_status_t = p_status_t;
	}
	public String getRemarka() {
		return remarka;
	}
	public void setRemarka(String remarka) {
		this.remarka = remarka;
	}
	public String getHosname() {
		return hosname;
	}
	public void setHosname(String hosname) {
		this.hosname = hosname;
	}
	public double getGwjt() {
		return gwjt;
	}
	public void setGwjt(double gwjt) {
		this.gwjt = gwjt;
	}
	public double getPost_salary() {
		return post_salary;
	}
	public void setPost_salary(double post_salary) {
		this.post_salary = post_salary;
	}
	public double getScale_salary() {
		return scale_salary;
	}
	public void setScale_salary(double scale_salary) {
		this.scale_salary = scale_salary;
	}
	public double getNurse10_salary() {
		return nurse10_salary;
	}
	public void setNurse10_salary(double nurse10_salary) {
		this.nurse10_salary = nurse10_salary;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsinjob() {
		return isinjob;
	}
	public void setIsinjob(String isinjob) {
		this.isinjob = isinjob;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMaternity_ins() {
		return maternity_ins;
	}
	public void setMaternity_ins(String maternity_ins) {
		this.maternity_ins = maternity_ins;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPolity_face() {
		return polity_face;
	}
	public void setPolity_face(String polity_face) {
		this.polity_face = polity_face;
	}
	public String getPolity_face_status() {
		return polity_face_status;
	}
	public void setPolity_face_status(String polity_face_status) {
		this.polity_face_status = polity_face_status;
	}
	public Date getJoin_party_date() {
		return join_party_date;
	}
	public void setJoin_party_date(Date join_party_date) {
		this.join_party_date = join_party_date;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNative_place() {
		return native_place;
	}
	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getHome_tel() {
		return home_tel;
	}
	public void setHome_tel(String home_tel) {
		this.home_tel = home_tel;
	}
	public String getMobile_tel() {
		return mobile_tel;
	}
	public void setMobile_tel(String mobile_tel) {
		this.mobile_tel = mobile_tel;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getOffice_tel() {
		return office_tel;
	}
	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getJob_kind() {
		return job_kind;
	}
	public void setJob_kind(String job_kind) {
		this.job_kind = job_kind;
	}
	public String getLaborage_kind() {
		return laborage_kind;
	}
	public void setLaborage_kind(String laborage_kind) {
		this.laborage_kind = laborage_kind;
	}
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getEdu_level_f() {
		return edu_level_f;
	}
	public void setEdu_level_f(String edu_level_f) {
		this.edu_level_f = edu_level_f;
	}
	public String getScience_headship() {
		return science_headship;
	}
	public void setScience_headship(String science_headship) {
		this.science_headship = science_headship;
	}
	public Date getFirst_work_date() {
		return first_work_date;
	}
	public void setFirst_work_date(Date first_work_date) {
		this.first_work_date = first_work_date;
	}
	public Date getJoin_unit_date() {
		return join_unit_date;
	}
	public void setJoin_unit_date(Date join_unit_date) {
		this.join_unit_date = join_unit_date;
	}
	public Date getBargain_end_date() {
		return bargain_end_date;
	}
	public void setBargain_end_date(Date bargain_end_date) {
		this.bargain_end_date = bargain_end_date;
	}
	public String getEngage_headship() {
		return engage_headship;
	}
	public void setEngage_headship(String engage_headship) {
		this.engage_headship = engage_headship;
	}
	public Date getEngage_headship_date() {
		return engage_headship_date;
	}
	public void setEngage_headship_date(Date engage_headship_date) {
		this.engage_headship_date = engage_headship_date;
	}
	public String getIsmarried() {
		return ismarried;
	}
	public void setIsmarried(String ismarried) {
		this.ismarried = ismarried;
	}
	public Date getLevel_unit_date() {
		return level_unit_date;
	}
	public void setLevel_unit_date(Date level_unit_date) {
		this.level_unit_date = level_unit_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getWork_years_before() {
		return work_years_before;
	}
	public void setWork_years_before(Integer work_years_before) {
		this.work_years_before = work_years_before;
	}
	public Integer getWork_year() {
		return work_year;
	}
	public void setWork_year(Integer work_year) {
		this.work_year = work_year;
	}
	public Integer getUnit_work_year() {
		return unit_work_year;
	}
	public void setUnit_work_year(Integer unit_work_year) {
		this.unit_work_year = unit_work_year;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getGraduate_school() {
		return graduate_school;
	}
	public void setGraduate_school(String graduate_school) {
		this.graduate_school = graduate_school;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public Integer getS_salary() {
		return s_salary;
	}
	public void setS_salary(Integer s_salary) {
		this.s_salary = s_salary;
	}
	public Date getS_date() {
		return s_date;
	}
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	public String getUser01() {
		return user01;
	}
	public void setUser01(String user01) {
		this.user01 = user01;
	}
	public String getUser02() {
		return user02;
	}
	public void setUser02(String user02) {
		this.user02 = user02;
	}
	public String getUser03() {
		return user03;
	}
	public void setUser03(String user03) {
		this.user03 = user03;
	}
	public String getUser04() {
		return user04;
	}
	public void setUser04(String user04) {
		this.user04 = user04;
	}
	public String getUser05() {
		return user05;
	}
	public void setUser05(String user05) {
		this.user05 = user05;
	}
	public String getUser06() {
		return user06;
	}
	public void setUser06(String user06) {
		this.user06 = user06;
	}
	public String getUser07() {
		return user07;
	}
	public void setUser07(String user07) {
		this.user07 = user07;
	}
	public String getUser08() {
		return user08;
	}
	public void setUser08(String user08) {
		this.user08 = user08;
	}
	public String getUser09() {
		return user09;
	}
	public void setUser09(String user09) {
		this.user09 = user09;
	}
	public String getUser10() {
		return user10;
	}
	public void setUser10(String user10) {
		this.user10 = user10;
	}
	public String getP_source() {
		return p_source;
	}
	public void setP_source(String p_source) {
		this.p_source = p_source;
	}
	public String getZc_name() {
		return zc_name;
	}
	public void setZc_name(String zc_name) {
		this.zc_name = zc_name;
	}
	public String getEdu_major_f() {
		return edu_major_f;
	}
	public void setEdu_major_f(String edu_major_f) {
		this.edu_major_f = edu_major_f;
	}
	public String getEdu_major_t() {
		return edu_major_t;
	}
	public void setEdu_major_t(String edu_major_t) {
		this.edu_major_t = edu_major_t;
	}
	public String getEdu_school_t() {
		return edu_school_t;
	}
	public void setEdu_school_t(String edu_school_t) {
		this.edu_school_t = edu_school_t;
	}
	public String getZc_type() {
		return zc_type;
	}
	public void setZc_type(String zc_type) {
		this.zc_type = zc_type;
	}
	public String getZc_level() {
		return zc_level;
	}
	public void setZc_level(String zc_level) {
		this.zc_level = zc_level;
	}
	public String getEdu_level_t() {
		return edu_level_t;
	}
	public void setEdu_level_t(String edu_level_t) {
		this.edu_level_t = edu_level_t;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public String getAttendance_departmentid() {
		return attendance_departmentid;
	}
	public void setAttendance_departmentid(String attendance_departmentid) {
		this.attendance_departmentid = attendance_departmentid;
	}
	public String getKq_deptname() {
		return kq_deptname;
	}
	public void setKq_deptname(String kq_deptname) {
		this.kq_deptname = kq_deptname;
	}
	public String getN_status() {
		return n_status;
	}
	public void setN_status(String n_status) {
		this.n_status = n_status;
	}
	public String getPersonnel_reduction() {
		return personnel_reduction;
	}
	public void setPersonnel_reduction(String personnel_reduction) {
		this.personnel_reduction = personnel_reduction;
	}
	public String getTechnical_job() {
		return technical_job;
	}
	public void setTechnical_job(String technical_job) {
		this.technical_job = technical_job;
	}
	public String getTechnical_classify1() {
		return technical_classify1;
	}
	public void setTechnical_classify1(String technical_classify1) {
		this.technical_classify1 = technical_classify1;
	}
	public String getTechnical_classify2() {
		return technical_classify2;
	}
	public void setTechnical_classify2(String technical_classify2) {
		this.technical_classify2 = technical_classify2;
	}
	public String getPractice_name() {
		return practice_name;
	}
	public void setPractice_name(String practice_name) {
		this.practice_name = practice_name;
	}
	public String getPractice_type() {
		return practice_type;
	}
	public void setPractice_type(String practice_type) {
		this.practice_type = practice_type;
	}
	public String getPractice_area() {
		return practice_area;
	}
	public void setPractice_area(String practice_area) {
		this.practice_area = practice_area;
	}
	public String getPaixv() {
		return paixv;
	}
	public void setPaixv(String paixv) {
		this.paixv = paixv;
	}
	public String getEdu_top_id() {
		return edu_top_id;
	}
	public void setEdu_top_id(String edu_top_id) {
		this.edu_top_id = edu_top_id;
	}
	public String getEdu_init_id() {
		return edu_init_id;
	}
	public void setEdu_init_id(String edu_init_id) {
		this.edu_init_id = edu_init_id;
	}
	public String getPolity_id() {
		return polity_id;
	}
	public void setPolity_id(String polity_id) {
		this.polity_id = polity_id;
	}
	public String getZc_id() {
		return zc_id;
	}
	public void setZc_id(String zc_id) {
		this.zc_id = zc_id;
	}
	public String getZg_id() {
		return zg_id;
	}
	public void setZg_id(String zg_id) {
		this.zg_id = zg_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getWork_js_id() {
		return work_js_id;
	}
	public void setWork_js_id(String work_js_id) {
		this.work_js_id = work_js_id;
	}
	public String getWork_gl_id() {
		return work_gl_id;
	}
	public void setWork_gl_id(String work_gl_id) {
		this.work_gl_id = work_gl_id;
	}
	public String getWork_gq_id() {
		return work_gq_id;
	}
	public void setWork_gq_id(String work_gq_id) {
		this.work_gq_id = work_gq_id;
	}
	public String getHt_id() {
		return ht_id;
	}
	public void setHt_id(String ht_id) {
		this.ht_id = ht_id;
	}
	public String getSalary_id() {
		return salary_id;
	}
	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
	}
	public String getDeptinfo_id() {
		return deptinfo_id;
	}
	public void setDeptinfo_id(String deptinfo_id) {
		this.deptinfo_id = deptinfo_id;
	}
	public String getTechnical_classify3() {
		return technical_classify3;
	}
	public void setTechnical_classify3(String technical_classify3) {
		this.technical_classify3 = technical_classify3;
	}
	public String getManage_job() {
		return manage_job;
	}
	public void setManage_job(String manage_job) {
		this.manage_job = manage_job;
	}
	public String getManage_classify1() {
		return manage_classify1;
	}
	public void setManage_classify1(String manage_classify1) {
		this.manage_classify1 = manage_classify1;
	}
	public String getSecurity_job() {
		return security_job;
	}
	public void setSecurity_job(String security_job) {
		this.security_job = security_job;
	}
	public String getSecurity_classify1() {
		return security_classify1;
	}
	public void setSecurity_classify1(String security_classify1) {
		this.security_classify1 = security_classify1;
	}
	public String getLevel_exe_date() {
		return level_exe_date;
	}
	public void setLevel_exe_date(String level_exe_date) {
		this.level_exe_date = level_exe_date;
	}
	public String getPost_level() {
		return post_level;
	}
	public void setPost_level(String post_level) {
		this.post_level = post_level;
	}
	public String getSalary_type() {
		return salary_type;
	}
	public void setSalary_type(String salary_type) {
		this.salary_type = salary_type;
	}
	public String getPost_scale() {
		return post_scale;
	}
	public void setPost_scale(String post_scale) {
		this.post_scale = post_scale;
	}
	public String getSalary_source() {
		return salary_source;
	}
	public void setSalary_source(String salary_source) {
		this.salary_source = salary_source;
	}
	public String getBase_salary() {
		return base_salary;
	}
	public void setBase_salary(String base_salary) {
		this.base_salary = base_salary;
	}
	public String getBdid() {
		return bdid;
	}
	public void setBdid(String bdid) {
		this.bdid = bdid;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public List<String> getSearchbypstatus() {
		return searchbypstatus;
	}
	public void setSearchbypstatus(List<String> searchbypstatus) {
		this.searchbypstatus = searchbypstatus;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getYs_flag() {
		return ys_flag;
	}
	public void setYs_flag(String ys_flag) {
		this.ys_flag = ys_flag;
	}
	public String getGz_flag() {
		return gz_flag;
	}
	public void setGz_flag(String gz_flag) {
		this.gz_flag = gz_flag;
	}
	public String getPost_rank_sjt() {
		return post_rank_sjt;
	}
	public void setPost_rank_sjt(String post_rank_sjt) {
		this.post_rank_sjt = post_rank_sjt;
	}
	public String getManager_flag() {
		return manager_flag;
	}
	public void setManager_flag(String manager_flag) {
		this.manager_flag = manager_flag;
	}
	public String getWarn_id() {
		return warn_id;
	}
	public void setWarn_id(String warn_id) {
		this.warn_id = warn_id;
	}
	public String getWarn_status() {
		return warn_status;
	}
	public void setWarn_status(String warn_status) {
		this.warn_status = warn_status;
	}
	public Date getWarn_gettime() {
		return warn_gettime;
	}
	public void setWarn_gettime(Date warn_gettime) {
		this.warn_gettime = warn_gettime;
	}
	public String getWarnCon_id() {
		return warnCon_id;
	}
	public void setWarnCon_id(String warnCon_id) {
		this.warnCon_id = warnCon_id;
	}
	public String getCome_source() {
		return come_source;
	}
	public void setCome_source(String come_source) {
		this.come_source = come_source;
	}
	public String getP_gone() {
		return p_gone;
	}
	public void setP_gone(String p_gone) {
		this.p_gone = p_gone;
	}
	public String getPost_status() {
		return post_status;
	}
	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}
	public String getWarn_name() {
		return warn_name;
	}
	public void setWarn_name(String warn_name) {
		this.warn_name = warn_name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAfter_technical_job() {
		return after_technical_job;
	}
	public void setAfter_technical_job(String after_technical_job) {
		this.after_technical_job = after_technical_job;
	}
	public String getAfter_manage_job() {
		return after_manage_job;
	}
	public void setAfter_manage_job(String after_manage_job) {
		this.after_manage_job = after_manage_job;
	}
	public String getAfter_security_job() {
		return after_security_job;
	}
	public void setAfter_security_job(String after_security_job) {
		this.after_security_job = after_security_job;
	}
	public String getAfter_id_card() {
		return after_id_card;
	}
	public void setAfter_id_card(String after_id_card) {
		this.after_id_card = after_id_card;
	}
	public String getDept_redeploy_before_id() {
		return dept_redeploy_before_id;
	}
	public void setDept_redeploy_before_id(String dept_redeploy_before_id) {
		this.dept_redeploy_before_id = dept_redeploy_before_id;
	}
	public String getPost_redeploy_before() {
		return post_redeploy_before;
	}
	public void setPost_redeploy_before(String post_redeploy_before) {
		this.post_redeploy_before = post_redeploy_before;
	}
	public String getWork_redeploy_before() {
		return work_redeploy_before;
	}
	public void setWork_redeploy_before(String work_redeploy_before) {
		this.work_redeploy_before = work_redeploy_before;
	}
	public String getDept_redeploy_after_id() {
		return dept_redeploy_after_id;
	}
	public void setDept_redeploy_after_id(String dept_redeploy_after_id) {
		this.dept_redeploy_after_id = dept_redeploy_after_id;
	}
	public Date getDept_redeploy_time() {
		return dept_redeploy_time;
	}
	public void setDept_redeploy_time(Date dept_redeploy_time) {
		this.dept_redeploy_time = dept_redeploy_time;
	}
	public String getPost_redeploy_after_classify() {
		return post_redeploy_after_classify;
	}
	public void setPost_redeploy_after_classify(String post_redeploy_after_classify) {
		this.post_redeploy_after_classify = post_redeploy_after_classify;
	}
	public String getPost_redeploy_after_mc() {
		return post_redeploy_after_mc;
	}
	public void setPost_redeploy_after_mc(String post_redeploy_after_mc) {
		this.post_redeploy_after_mc = post_redeploy_after_mc;
	}
	public Date getPost_redeploy_time() {
		return post_redeploy_time;
	}
	public void setPost_redeploy_time(Date post_redeploy_time) {
		this.post_redeploy_time = post_redeploy_time;
	}
	public String getAfter_technical_classify1() {
		return after_technical_classify1;
	}
	public void setAfter_technical_classify1(String after_technical_classify1) {
		this.after_technical_classify1 = after_technical_classify1;
	}
	public String getAfter_technical_classify2() {
		return after_technical_classify2;
	}
	public void setAfter_technical_classify2(String after_technical_classify2) {
		this.after_technical_classify2 = after_technical_classify2;
	}
	public String getAfter_technical_classify3() {
		return after_technical_classify3;
	}
	public void setAfter_technical_classify3(String after_technical_classify3) {
		this.after_technical_classify3 = after_technical_classify3;
	}
	public String getAfter_manage_classify1() {
		return after_manage_classify1;
	}
	public void setAfter_manage_classify1(String after_manage_classify1) {
		this.after_manage_classify1 = after_manage_classify1;
	}
	public String getAfter_security_classify1() {
		return after_security_classify1;
	}
	public void setAfter_security_classify1(String after_security_classify1) {
		this.after_security_classify1 = after_security_classify1;
	}
	public Date getWork_redeploy_time() {
		return work_redeploy_time;
	}
	public void setWork_redeploy_time(Date work_redeploy_time) {
		this.work_redeploy_time = work_redeploy_time;
	}
	public String getDept_redeploy_after_mc() {
		return dept_redeploy_after_mc;
	}
	public void setDept_redeploy_after_mc(String dept_redeploy_after_mc) {
		this.dept_redeploy_after_mc = dept_redeploy_after_mc;
	}
	public String[] getWarn_idlist() {
		return warn_idlist;
	}
	public void setWarn_idlist(String[] warn_idlist) {
		this.warn_idlist = warn_idlist;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "Employee [p_id=" + p_id + ", c_name=" + c_name + ", age=" + age + ", maternity_ins=" + maternity_ins
				+ ", p_num=" + p_num + ", sex=" + sex + ", birthday=" + birthday + ", polity_face=" + polity_face
				+ ", polity_face_status=" + polity_face_status + ", join_party_date=" + join_party_date + ", id_card="
				+ id_card + ", nation=" + nation + ", religion=" + religion + ", native_place=" + native_place
				+ ", home_address=" + home_address + ", home_tel=" + home_tel + ", mobile_tel=" + mobile_tel
				+ ", departmentid=" + departmentid + ", office_tel=" + office_tel + ", email=" + email + ", health="
				+ health + ", job_kind=" + job_kind + ", laborage_kind=" + laborage_kind + ", p_status=" + p_status
				+ ", hosnum=" + hosnum + ", edu_level_f=" + edu_level_f + ", science_headship=" + science_headship
				+ ", first_work_date=" + first_work_date + ", join_unit_date=" + join_unit_date + ", bargain_end_date="
				+ bargain_end_date + ", engage_headship=" + engage_headship + ", engage_headship_date="
				+ engage_headship_date + ", ismarried=" + ismarried + ", level_unit_date=" + level_unit_date
				+ ", remark=" + remark + ", work_years_before=" + work_years_before + ", work_year=" + work_year
				+ ", unit_work_year=" + unit_work_year + ", deptname=" + deptname + ", p_no=" + p_no
				+ ", graduate_school=" + graduate_school + ", s_type=" + s_type + ", s_salary=" + s_salary + ", s_date="
				+ s_date + ", user01=" + user01 + ", user02=" + user02 + ", user03=" + user03 + ", user04=" + user04
				+ ", user05=" + user05 + ", user06=" + user06 + ", user07=" + user07 + ", user08=" + user08
				+ ", user09=" + user09 + ", user10=" + user10 + ", p_source=" + p_source + ", zc_name=" + zc_name
				+ ", edu_major_f=" + edu_major_f + ", edu_major_t=" + edu_major_t + ", edu_school_t=" + edu_school_t
				+ ", zc_type=" + zc_type + ", zc_level=" + zc_level + ", edu_level_t=" + edu_level_t + ", image_path="
				+ image_path + ", image_url=" + image_url + ", post_type=" + post_type + ", attendance_departmentid="
				+ attendance_departmentid + ", kq_deptname=" + kq_deptname + ", n_status=" + n_status
				+ ", personnel_reduction=" + personnel_reduction + ", technical_job=" + technical_job
				+ ", technical_classify1=" + technical_classify1 + ", technical_classify2=" + technical_classify2
				+ ", practice_name=" + practice_name + ", practice_type=" + practice_type + ", practice_area="
				+ practice_area + ", paixv=" + paixv + ", edu_top_id=" + edu_top_id + ", edu_init_id=" + edu_init_id
				+ ", polity_id=" + polity_id + ", zc_id=" + zc_id + ", zg_id=" + zg_id + ", post_id=" + post_id
				+ ", work_js_id=" + work_js_id + ", work_gl_id=" + work_gl_id + ", work_gq_id=" + work_gq_id
				+ ", ht_id=" + ht_id + ", salary_id=" + salary_id + ", deptinfo_id=" + deptinfo_id
				+ ", technical_classify3=" + technical_classify3 + ", manage_job=" + manage_job + ", manage_classify1="
				+ manage_classify1 + ", security_job=" + security_job + ", security_classify1=" + security_classify1
				+ ", level_exe_date=" + level_exe_date + ", post_level=" + post_level + ", salary_type=" + salary_type
				+ ", post_scale=" + post_scale + ", salary_source=" + salary_source + ", base_salary=" + base_salary
				+ ", bdid=" + bdid + ", keyWord=" + keyWord + ", searchbypstatus=" + searchbypstatus + ", list=" + list
				+ ", ys_flag=" + ys_flag + ", gz_flag=" + gz_flag + ", post_rank_sjt=" + post_rank_sjt
				+ ", manager_flag=" + manager_flag + ", isinjob=" + isinjob + ", warn_id=" + warn_id + ", warn_idlist="
				+ Arrays.toString(warn_idlist) + ", warn_name=" + warn_name + ", warn_status=" + warn_status
				+ ", warn_gettime=" + warn_gettime + ", warnCon_id=" + warnCon_id + ", come_source=" + come_source
				+ ", p_gone=" + p_gone + ", post_status=" + post_status + ", post_salary=" + post_salary
				+ ", scale_salary=" + scale_salary + ", nurse10_salary=" + nurse10_salary + ", gwjt=" + gwjt
				+ ", hosname=" + hosname + ", remarka=" + remarka + ", selected="
				+ selected + ", id=" + id + ", apply_time=" + apply_time + ", state=" + state + ", after_technical_job="
				+ after_technical_job + ", after_manage_job=" + after_manage_job + ", after_security_job="
				+ after_security_job + ", after_id_card=" + after_id_card + ", dept_redeploy_before_id="
				+ dept_redeploy_before_id + ", post_redeploy_before=" + post_redeploy_before + ", work_redeploy_before="
				+ work_redeploy_before + ", dept_redeploy_after_id=" + dept_redeploy_after_id + ", dept_redeploy_time="
				+ dept_redeploy_time + ", post_redeploy_after_classify=" + post_redeploy_after_classify
				+ ", post_redeploy_after_mc=" + post_redeploy_after_mc + ", post_redeploy_time=" + post_redeploy_time
				+ ", after_technical_classify1=" + after_technical_classify1 + ", after_technical_classify2="
				+ after_technical_classify2 + ", after_technical_classify3=" + after_technical_classify3
				+ ", after_manage_classify1=" + after_manage_classify1 + ", after_security_classify1="
				+ after_security_classify1 + ", work_redeploy_time=" + work_redeploy_time + ", dept_redeploy_after_mc="
				+ dept_redeploy_after_mc +"]";
	}
	
}
