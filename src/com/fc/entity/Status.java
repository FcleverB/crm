package com.fc.entity;
/**
 * 客户状态类
 * @author Administrator
 *
 */
public class Status {
	private int staid;
	private String sname;
	private String remark;
	public int getStaid() {
		return staid;
	}
	public void setStaid(int staid) {
		this.staid = staid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Status [staid=" + staid + ", sname=" + sname + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + staid;
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
		Status other = (Status) obj;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (staid != other.staid)
			return false;
		return true;
	}
	public Status() {
		super();
	}
	public Status(int staid, String sname, String remark) {
		super();
		this.staid = staid;
		this.sname = sname;
		this.remark = remark;
	}
	
}
