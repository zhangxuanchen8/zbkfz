package com.ehinfo.hr.entity.system.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;
/**
 * 组织机构表
 */
@Alias("BaseOrg")
public class Org extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/**ID*/
	private String id;
	/**上级ID*/
	private String pId;
	/**上级名*/
	private String pName;
	/**名称*/
	private String name;	
	/**状态*/
	private Integer isValid;
	/**描述*/
	private String description;	
	/**创建时间*/
	private Date createTime;	
	/**修改时间*/
	private Date updateTime;
	/**子机构*/
	private List<Org> orgs=new ArrayList<Org>();
	/**子角色*/
	private List<Role> roles=new ArrayList<Role>();
	
	private String orgtype;	
	
	private String distcode;	
	
	private String hosnum;
	
	private String empnumber;
	
	private String beds;
	
	private String address;
	
	private String tel;
	
	private String contact;
	private String isleaf;
	
	private String kq_dept;
	
	private String kq_deptid;
	private String degreelevel;
	
	private String organizeno;
	
	private String clinicaltype;
	
	private String depttype;
	private String ishospital;
	private String deptcode;
	private int pnum;
	
	private String his_deptid;
	private String gwyy;
	public String getHis_deptid() {
		return his_deptid;
	}
	public void setHis_deptid(String his_deptid) {
		this.his_deptid = his_deptid;
	}
	public Double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}
	public String getHis_type() {
		return his_type;
	}
	public void setHis_type(String his_type) {
		this.his_type = his_type;
	}
	private Double coefficient;
	private String his_type;
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getKq_deptid() {
		return kq_deptid;
	}
	public void setKq_deptid(String kq_deptid) {
		this.kq_deptid = kq_deptid;
	}
	public String getKq_dept() {
		return kq_dept;
	}
	public void setKq_dept(String kq_dept) {
		this.kq_dept = kq_dept;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	private String shortname;
	
	
	public String getIshospital() {
		return ishospital;
	}
	public void setIshospital(String ishospital) {
		this.ishospital = ishospital;
	}
	//用来检查hosnum时辅助传参
	private Integer count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<Org> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	public String getDistcode() {
		return distcode;
	}
	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}
	
	public String getEmpnumber() {
		return empnumber;
	}
	public void setEmpnumber(String empnumber) {
		this.empnumber = empnumber;
	}
	public String getBeds() {
		return beds;
	}
	public void setBeds(String beds) {
		this.beds = beds;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDegreelevel() {
		return degreelevel;
	}
	public void setDegreelevel(String degreelevel) {
		this.degreelevel = degreelevel;
	}
	public String getOrganizeno() {
		return organizeno;
	}
	public void setOrganizeno(String organizeno) {
		this.organizeno = organizeno;
	}

	public String getClinicaltype() {
		return clinicaltype;
	}
	public void setClinicaltype(String clinicaltype) {
		this.clinicaltype = clinicaltype;
	}
	public String getDepttype() {
		return depttype;
	}
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}
	public String getGwyy() {
		return gwyy;
	}
	public void setGwyy(String gwyy) {
		this.gwyy = gwyy;
	}
	@Override
	public String toString() {
		return "Org [id=" + id + ", pId=" + pId + ", pName=" + pName + ", name=" + name + ", isValid=" + isValid
				+ ", description=" + description + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", orgs=" + orgs + ", roles=" + roles + ", orgtype=" + orgtype + ", distcode=" + distcode
				+ ", hosnum=" + hosnum + ", empnumber=" + empnumber + ", beds=" + beds + ", address=" + address
				+ ", tel=" + tel + ", contact=" + contact + ", isleaf=" + isleaf + ", kq_dept=" + kq_dept
				+ ", kq_deptid=" + kq_deptid + ", degreelevel=" + degreelevel + ", organizeno=" + organizeno
				+ ", clinicaltype=" + clinicaltype + ", depttype=" + depttype + ", ishospital=" + ishospital
				+ ", deptcode=" + deptcode + ", pnum=" + pnum + ", his_deptid=" + his_deptid + ", gwyy=" + gwyy
				+ ", coefficient=" + coefficient + ", his_type=" + his_type + ", shortname=" + shortname + ", count="
				+ count + "]";
	}
	

		
}
