package com.ehinfo.hr.entity.system.employee;

import java.util.Date;

public class BasDept {
	private String hosnum;
	private String deptcode;
	private String name;
	private String shortname;
	private String pid;
	private String isleaf;
	private String isdept;
	private String clinicaltype;
	private String isaccdept;
	private String depttype;
	private String clcflag;
	private String emcflag;
	private String inpflag;
	private String matflag;
	private String herbsflag;
	private String cnmflag;
	private String wmflag;
	private double prepay;
	private String location;
	private String isdeleted;
	private String inputcpy;
	private String inputcwb;
	private String materialflag;
	private String mrid;
	private String storetype;
	private String isybzlfree;
	private String id;
	private String isvalid;
	private Date createtime;
	private String description;
	private String orgtype;
	private String distcode;
	private Integer empnumber;
	private Integer beds;
	private String address;
	private String tel;
	private String degreelevel;
	private String organizeno;
	private String contact;
	private String kq_deptid;
	private String ishospital;
	private Integer pnum;
	
	private String his_deptid;
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
	
	private String hosname;//医院名称
	
	public String getHosname() {
		return hosname;
	}
	public void setHosname(String hosname) {
		this.hosname = hosname;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getIsdept() {
		return isdept;
	}
	public void setIsdept(String isdept) {
		this.isdept = isdept;
	}
	public String getClinicaltype() {
		return clinicaltype;
	}
	public void setClinicaltype(String clinicaltype) {
		this.clinicaltype = clinicaltype;
	}
	public String getIsaccdept() {
		return isaccdept;
	}
	public void setIsaccdept(String isaccdept) {
		this.isaccdept = isaccdept;
	}
	public String getDepttype() {
		return depttype;
	}
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}
	public String getClcflag() {
		return clcflag;
	}
	public void setClcflag(String clcflag) {
		this.clcflag = clcflag;
	}
	public String getEmcflag() {
		return emcflag;
	}
	public void setEmcflag(String emcflag) {
		this.emcflag = emcflag;
	}
	public String getInpflag() {
		return inpflag;
	}
	public void setInpflag(String inpflag) {
		this.inpflag = inpflag;
	}
	public String getMatflag() {
		return matflag;
	}
	public void setMatflag(String matflag) {
		this.matflag = matflag;
	}
	public String getHerbsflag() {
		return herbsflag;
	}
	public void setHerbsflag(String herbsflag) {
		this.herbsflag = herbsflag;
	}
	public String getCnmflag() {
		return cnmflag;
	}
	public void setCnmflag(String cnmflag) {
		this.cnmflag = cnmflag;
	}
	public String getWmflag() {
		return wmflag;
	}
	public void setWmflag(String wmflag) {
		this.wmflag = wmflag;
	}
	public double getPrepay() {
		return prepay;
	}
	public void setPrepay(double prepay) {
		this.prepay = prepay;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getInputcpy() {
		return inputcpy;
	}
	public void setInputcpy(String inputcpy) {
		this.inputcpy = inputcpy;
	}
	public String getInputcwb() {
		return inputcwb;
	}
	public void setInputcwb(String inputcwb) {
		this.inputcwb = inputcwb;
	}
	public String getMaterialflag() {
		return materialflag;
	}
	public void setMaterialflag(String materialflag) {
		this.materialflag = materialflag;
	}
	public String getMrid() {
		return mrid;
	}
	public void setMrid(String mrid) {
		this.mrid = mrid;
	}
	public String getStoretype() {
		return storetype;
	}
	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}
	public String getIsybzlfree() {
		return isybzlfree;
	}
	public void setIsybzlfree(String isybzlfree) {
		this.isybzlfree = isybzlfree;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getEmpnumber() {
		return empnumber;
	}
	public void setEmpnumber(Integer empnumber) {
		this.empnumber = empnumber;
	}
	public Integer getBeds() {
		return beds;
	}
	public void setBeds(Integer beds) {
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getKq_deptid() {
		return kq_deptid;
	}
	public void setKq_deptid(String kq_deptid) {
		this.kq_deptid = kq_deptid;
	}
	public String getIshospital() {
		return ishospital;
	}
	public void setIshospital(String ishospital) {
		this.ishospital = ishospital;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	@Override
	public String toString() {
		return "BasDept [hosnum=" + hosnum + ", deptcode=" + deptcode + ", name=" + name + ", shortname=" + shortname
				+ ", pid=" + pid + ", isleaf=" + isleaf + ", isdept=" + isdept + ", clinicaltype=" + clinicaltype
				+ ", isaccdept=" + isaccdept + ", depttype=" + depttype + ", clcflag=" + clcflag + ", emcflag="
				+ emcflag + ", inpflag=" + inpflag + ", matflag=" + matflag + ", herbsflag=" + herbsflag + ", cnmflag="
				+ cnmflag + ", wmflag=" + wmflag + ", prepay=" + prepay + ", location=" + location + ", isdeleted="
				+ isdeleted + ", inputcpy=" + inputcpy + ", inputcwb=" + inputcwb + ", materialflag=" + materialflag
				+ ", mrid=" + mrid + ", storetype=" + storetype + ", isybzlfree=" + isybzlfree + ", id=" + id
				+ ", isvalid=" + isvalid + ", createtime=" + createtime + ", description=" + description + ", orgtype="
				+ orgtype + ", distcode=" + distcode + ", empnumber=" + empnumber + ", beds=" + beds + ", address="
				+ address + ", tel=" + tel + ", degreelevel=" + degreelevel + ", organizeno=" + organizeno
				+ ", contact=" + contact + ", kq_deptid=" + kq_deptid + ", ishospital=" + ishospital + ", pnum=" + pnum
				+ ", hosname=" + hosname + "]";
	}

	
}
