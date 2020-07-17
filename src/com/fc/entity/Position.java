package com.fc.entity;
/**
 * 岗位类
 * @author Administrator
 *
 */
public class Position {
	private int fpoid;
	private String fponame;
	private String fporemark;
	public int getFpoid() {
		return fpoid;
	}
	public void setFpoid(int fpoid) {
		this.fpoid = fpoid;
	}
	public String getFponame() {
		return fponame;
	}
	public void setFponame(String fponame) {
		this.fponame = fponame;
	}
	public String getFporemark() {
		return fporemark;
	}
	public void setFporemark(String fporemark) {
		this.fporemark = fporemark;
	}
	@Override
	public String toString() {
		return "Position [fpoid=" + fpoid + ", fponame=" + fponame + ", fporemark=" + fporemark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fpoid;
		result = prime * result + ((fponame == null) ? 0 : fponame.hashCode());
		result = prime * result + ((fporemark == null) ? 0 : fporemark.hashCode());
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
		Position other = (Position) obj;
		if (fpoid != other.fpoid)
			return false;
		if (fponame == null) {
			if (other.fponame != null)
				return false;
		} else if (!fponame.equals(other.fponame))
			return false;
		if (fporemark == null) {
			if (other.fporemark != null)
				return false;
		} else if (!fporemark.equals(other.fporemark))
			return false;
		return true;
	}
	public Position() {
		super();
	}
	public Position(int fpoid, String fponame, String fporemark) {
		super();
		this.fpoid = fpoid;
		this.fponame = fponame;
		this.fporemark = fporemark;
	}
	
}
