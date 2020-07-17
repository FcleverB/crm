package com.fc.entity;
/**
 * 客户类型类
 * @author Administrator
 *
 */
public class Type {
	private int typid;
	private String cname;
	private String remark;
	public int getTypid() {
		return typid;
	}
	public void setTypid(int typid) {
		this.typid = typid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Type [typid=" + typid + ", cname=" + cname + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + typid;
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
		Type other = (Type) obj;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (typid != other.typid)
			return false;
		return true;
	}
	public Type() {
		super();
	}
	public Type(int typid, String cname, String remark) {
		super();
		this.typid = typid;
		this.cname = cname;
		this.remark = remark;
	}
	
}
