package com.fc.entity;
/**
 * 客户来源类
 * @author Administrator
 *
 */
public class Source {
	private int sourid;
	private String sname;
	private String remark;
	public int getSourid() {
		return sourid;
	}
	public void setSourid(int sourid) {
		this.sourid = sourid;
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
		return "Source [sourid=" + sourid + ", sname=" + sname + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + sourid;
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
		Source other = (Source) obj;
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
		if (sourid != other.sourid)
			return false;
		return true;
	}
	public Source() {
		super();
	}
	public Source(int sourid, String sname, String remark) {
		super();
		this.sourid = sourid;
		this.sname = sname;
		this.remark = remark;
	}
	
}
