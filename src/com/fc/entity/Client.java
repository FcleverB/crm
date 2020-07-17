package com.fc.entity;

import java.util.Date;

/**
 * 客户类
 * @author Administrator
 *
 */
public class Client {
	private String cliid;
	private String cname;
	private String sex;
	private Date birth;
	private String comp;
	private String job;
	private String adrs;
	private String phone;
	private String qq;
	private String email;
	private String remark;
	private String pre;
	private String city;
	private String town;
	private Employee emp;
	private Type ctyp;
	private Status sta;
	private Source sour;
	
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCliid() {
		return cliid;
	}
	public void setCliid(String cliid) {
		this.cliid = cliid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Type getCtyp() {
		return ctyp;
	}
	public void setCtyp(Type ctyp) {
		this.ctyp = ctyp;
	}
	public Status getSta() {
		return sta;
	}
	public void setSta(Status sta) {
		this.sta = sta;
	}
	public Source getSour() {
		return sour;
	}
	public void setSour(Source sour) {
		this.sour = sour;
	}
	
	
	
	@Override
	public String toString() {
		return "Client [cliid=" + cliid + ", cname=" + cname + ", sex=" + sex + ", birth=" + birth + ", comp=" + comp
				+ ", job=" + job + ", adrs=" + adrs + ", phone=" + phone + ", qq=" + qq + ", email=" + email
				+ ", remark=" + remark + ", pre=" + pre + ", city=" + city + ", town=" + town + ", emp=" + emp
				+ ", ctyp=" + ctyp + ", sta=" + sta + ", sour=" + sour + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adrs == null) ? 0 : adrs.hashCode());
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cliid == null) ? 0 : cliid.hashCode());
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((comp == null) ? 0 : comp.hashCode());
		result = prime * result + ((ctyp == null) ? 0 : ctyp.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pre == null) ? 0 : pre.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((sour == null) ? 0 : sour.hashCode());
		result = prime * result + ((sta == null) ? 0 : sta.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adrs == null) {
			if (other.adrs != null)
				return false;
		} else if (!adrs.equals(other.adrs))
			return false;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cliid == null) {
			if (other.cliid != null)
				return false;
		} else if (!cliid.equals(other.cliid))
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (comp == null) {
			if (other.comp != null)
				return false;
		} else if (!comp.equals(other.comp))
			return false;
		if (ctyp == null) {
			if (other.ctyp != null)
				return false;
		} else if (!ctyp.equals(other.ctyp))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (pre == null) {
			if (other.pre != null)
				return false;
		} else if (!pre.equals(other.pre))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (sour == null) {
			if (other.sour != null)
				return false;
		} else if (!sour.equals(other.sour))
			return false;
		if (sta == null) {
			if (other.sta != null)
				return false;
		} else if (!sta.equals(other.sta))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		return true;
	}
	public Client() {
		super();
	}
	public Client(String cliid, String cname, String sex, Date birth, String comp, String job, String adrs, String phone,
			String qq, String email, String remark, Employee emp, Type ctyp, Status sta, Source sour) {
		super();
		this.cliid = cliid;
		this.cname = cname;
		this.sex = sex;
		this.birth = birth;
		this.comp = comp;
		this.job = job;
		this.adrs = adrs;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
		this.remark = remark;
		this.emp = emp;
		this.ctyp = ctyp;
		this.sta = sta;
		this.sour = sour;
	}
	public Client(String cliid, Employee emp) {
		super();
		this.cliid = cliid;
		this.emp = emp;
	}
	public Client(String cliid, String cname, String sex, Date birth, String comp, String job, String adrs,
			String phone, String qq, String email, String remark, String pre, String city, String town, Employee emp,
			Type ctyp, Status sta, Source sour) {
		super();
		this.cliid = cliid;
		this.cname = cname;
		this.sex = sex;
		this.birth = birth;
		this.comp = comp;
		this.job = job;
		this.adrs = adrs;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
		this.remark = remark;
		this.pre = pre;
		this.city = city;
		this.town = town;
		this.emp = emp;
		this.ctyp = ctyp;
		this.sta = sta;
		this.sour = sour;
	}
	
	
	
	
}
