package com.fc.entity;

public class Homestatus {
	private int hstaid;
	private String name;
	private String remark;
	public int getHstaid() {
		return hstaid;
	}
	public void setHstaid(int hstaid) {
		this.hstaid = hstaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Homestatus [hstaid=" + hstaid + ", name=" + name + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hstaid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		Homestatus other = (Homestatus) obj;
		if (hstaid != other.hstaid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}
	public Homestatus() {
		super();
	}
	public Homestatus(int hstaid, String name, String remark) {
		super();
		this.hstaid = hstaid;
		this.name = name;
		this.remark = remark;
	}
	
}
