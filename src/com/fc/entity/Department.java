package com.fc.entity;

import java.io.Serializable;
/**
 * 部门类
 * @author Administrator
 *
 */
public class Department implements Serializable,Comparable<Department>{
	
	private static final long serialVersionUID = -4897964297001625565L;
	private int deptid;
	private String deptname;
	private String deptloc;
	private String remark;
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptloc() {
		return deptloc;
	}
	public void setDeptloc(String deptloc) {
		this.deptloc = deptloc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", deptname=" + deptname + ", deptloc=" + deptloc + ", remark=" + remark
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptid;
		result = prime * result + ((deptloc == null) ? 0 : deptloc.hashCode());
		result = prime * result + ((deptname == null) ? 0 : deptname.hashCode());
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
		Department other = (Department) obj;
		if (deptid != other.deptid)
			return false;
		if (deptloc == null) {
			if (other.deptloc != null)
				return false;
		} else if (!deptloc.equals(other.deptloc))
			return false;
		if (deptname == null) {
			if (other.deptname != null)
				return false;
		} else if (!deptname.equals(other.deptname))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}
	public Department(int deptid, String deptname, String deptloc, String remark) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.deptloc = deptloc;
		this.remark = remark;
	}
	public Department() {
		super();
	}
	
	public Department(int deptid, String deptname) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
	}
	@Override
	public int compareTo(Department other) {
		return this.deptid-other.deptid;
	}
	
}
