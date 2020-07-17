package com.fc.entity;
/**
 * 房屋类型类
 * @author Administrator
 *
 */
public class Hometype {
	private int htypid;
	private String name;
	private String remark;
	public int getHtypid() {
		return htypid;
	}
	public void setHtypid(int htypid) {
		this.htypid = htypid;
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
		return "Hometype [htypid=" + htypid + ", name=" + name + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + htypid;
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
		Hometype other = (Hometype) obj;
		if (htypid != other.htypid)
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
	public Hometype() {
		super();
	}
	public Hometype(String name) {
		super();
		this.name = name;
	}
	public Hometype(int htypid, String name, String remark) {
		super();
		this.htypid = htypid;
		this.name = name;
		this.remark = remark;
	}
	
}
