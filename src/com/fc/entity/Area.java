package com.fc.entity;

public class Area {
	private int areaid;
	private String areaname;
	private int parentid;
	private int arealevel;
	private int status;
	public int getAreaid() {
		return areaid;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public String getAreanameString() {
		return areaname;
	}
	public void setAreanameString(String areanameString) {
		this.areaname = areanameString;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getArealevel() {
		return arealevel;
	}
	public void setArealevel(int arealevel) {
		this.arealevel = arealevel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Area [areaid=" + areaid + ", areanameString=" + areaname + ", parentid=" + parentid
				+ ", arealevel=" + arealevel + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaid;
		result = prime * result + arealevel;
		result = prime * result + ((areaname == null) ? 0 : areaname.hashCode());
		result = prime * result + parentid;
		result = prime * result + status;
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
		Area other = (Area) obj;
		if (areaid != other.areaid)
			return false;
		if (arealevel != other.arealevel)
			return false;
		if (areaname == null) {
			if (other.areaname != null)
				return false;
		} else if (!areaname.equals(other.areaname))
			return false;
		if (parentid != other.parentid)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	public Area(int areaid, String areanameString, int parentid, int arealevel, int status) {
		super();
		this.areaid = areaid;
		this.areaname = areanameString;
		this.parentid = parentid;
		this.arealevel = arealevel;
		this.status = status;
	}
	public Area() {
		super();
	}
	
}
