package com.ehinfo.hr.entity.zhibiao;

import org.apache.ibatis.type.Alias;

/**
 * @author LHB
 * @date 2018/10/11 10:05
 */
@Alias("model")
public class Modelm {
    private String id;
    private String name;
    private String hosnum;
    private String nodecode;
    private String zblb;
    private String yq;
    private String zblbname;
    private String yqname;
    private int xh;
    private String del;
    private String stoptime;
    private String year;
    private int num;
    private String state;
    private String shortname;
    
    
    public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getZblb() {
        return zblb;
    }

    public void setZblb(String zblb) {
        this.zblb = zblb;
    }

    public String getYq() {
        return yq;
    }

    public void setYq(String yq) {
        this.yq = yq;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getZblbname() {
        return zblbname;
    }

    public void setZblbname(String zblbname) {
        this.zblbname = zblbname;
    }

    public String getYqname() {
        return yqname;
    }

    public void setYqname(String yqname) {
        this.yqname = yqname;
    }

    @Override
    public String toString() {
        return "Modelm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hosnum='" + hosnum + '\'' +
                ", nodecode='" + nodecode + '\'' +
                ", zblb='" + zblb + '\'' +
                ", yq='" + yq + '\'' +
                ", zblbname='" + zblbname + '\'' +
                ", yqname='" + yqname + '\'' +
                ", xh=" + xh +
                ", del='" + del + '\'' +
                '}';
    }
}
