package com.ehinfo.hr.entity.system.org;

import org.apache.ibatis.type.Alias;

import com.ehinfo.hr.entity.base.BaseEntity;
/**
 * 院区表
 * @author 20965
 *
 */
@Alias("BaseHospital")
public class Hospital extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String hosnum;
	private String nodecode;
	private String hosname;
	private String distcode;
	private String supunit;
	private String nodetype;
	private String hosdegree;
	private String orgtype;
	private Long empnumber;
	private Long beds;
	private Long doctors;
	private Long nurses;
	private String address;
	private String tel;
	private String introduction;
	private String inputcpy;
	private String inputcwb;
	private String degreelevel;
	private String hosdname;
	private String degreelname;
	private String isenabled;
	private String shortname;
	private String ycentercode;
	private String ncentercode;
	private String itemversion;
	private int organizeno;
	private String dwhyfl;
	private String sydwfl;
	private String jgdm;
	private String ygtlx;
	private String zzppdw;
	private String jgzxid;
	
	public String getZzppdw() {
		return zzppdw;
	}
	public void setZzppdw(String zzppdw) {
		this.zzppdw = zzppdw;
	}
	public String getJgzxid() {
		return jgzxid;
	}
	public void setJgzxid(String jgzxid) {
		this.jgzxid = jgzxid;
	}
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public String getYgtlx() {
		return ygtlx;
	}
	public void setYgtlx(String ygtlx) {
		this.ygtlx = ygtlx;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public String getNodecode() {
		return nodecode;
	}
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	public String getHosname() {
		return hosname;
	}
	public void setHosname(String hosname) {
		this.hosname = hosname;
	}
	public String getDistcode() {
		return distcode;
	}
	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}
	public String getSupunit() {
		return supunit;
	}
	public void setSupunit(String supunit) {
		this.supunit = supunit;
	}
	public String getNodetype() {
		return nodetype;
	}
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	public String getHosdegree() {
		return hosdegree;
	}
	public void setHosdegree(String hosdegree) {
		this.hosdegree = hosdegree;
	}
	public String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	public Long getEmpnumber() {
		return empnumber;
	}
	public void setEmpnumber(Long empnumber) {
		this.empnumber = empnumber;
	}
	public Long getBeds() {
		return beds;
	}
	public void setBeds(Long beds) {
		this.beds = beds;
	}
	public Long getDoctors() {
		return doctors;
	}
	public void setDoctors(Long doctors) {
		this.doctors = doctors;
	}
	public Long getNurses() {
		return nurses;
	}
	public void setNurses(Long nurses) {
		this.nurses = nurses;
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	public String getDegreelevel() {
		return degreelevel;
	}
	public void setDegreelevel(String degreelevel) {
		this.degreelevel = degreelevel;
	}
	public String getHosdname() {
		return hosdname;
	}
	public void setHosdname(String hosdname) {
		this.hosdname = hosdname;
	}
	public String getDegreelname() {
		return degreelname;
	}
	public void setDegreelname(String degreelname) {
		this.degreelname = degreelname;
	}
	public String getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(String isenabled) {
		this.isenabled = isenabled;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getYcentercode() {
		return ycentercode;
	}
	public void setYcentercode(String ycentercode) {
		this.ycentercode = ycentercode;
	}
	public String getNcentercode() {
		return ncentercode;
	}
	public void setNcentercode(String ncentercode) {
		this.ncentercode = ncentercode;
	}
	public String getItemversion() {
		return itemversion;
	}
	public void setItemversion(String itemversion) {
		this.itemversion = itemversion;
	}
	public int getOrganizeno() {
		return organizeno;
	}
	public void setOrganizeno(int organizeno) {
		this.organizeno = organizeno;
	}
	public String getDwhyfl() {
		return dwhyfl;
	}
	public void setDwhyfl(String dwhyfl) {
		this.dwhyfl = dwhyfl;
	}
	public String getSydwfl() {
		return sydwfl;
	}
	public void setSydwfl(String sydwfl) {
		this.sydwfl = sydwfl;
	}
	@Override
	public String toString() {
		return "Hospital [hosnum=" + hosnum + ", nodecode=" + nodecode + ", hosname=" + hosname + ", distcode="
				+ distcode + ", supunit=" + supunit + ", nodetype=" + nodetype + ", hosdegree=" + hosdegree
				+ ", orgtype=" + orgtype + ", empnumber=" + empnumber + ", beds=" + beds + ", doctors=" + doctors
				+ ", nurses=" + nurses + ", address=" + address + ", tel=" + tel + ", introduction=" + introduction
				+ ", inputcpy=" + inputcpy + ", inputcwb=" + inputcwb + ", degreelevel=" + degreelevel + ", hosdname="
				+ hosdname + ", degreelname=" + degreelname + ", isenabled=" + isenabled + ", shortname=" + shortname
				+ ", ycentercode=" + ycentercode + ", ncentercode=" + ncentercode + ", itemversion=" + itemversion
				+ ", organizeno=" + organizeno + ", dwhyfl=" + dwhyfl + ", sydwfl=" + sydwfl + "]";
	}
}
