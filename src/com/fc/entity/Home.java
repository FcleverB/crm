package com.fc.entity;
/**
 * 房屋类
 * @author Administrator
 *
 */
public class Home {
	private int homid;
	private String address;
	private int price;
	private int area;
	private int sprice;
	private String envir;
	private String remark;
	private String realname;
	private String photoname;
	private String phototype;

	
	private Homestatus sta;
	private Hometype htyp;
	public int getHomid() {
		return homid;
	}
	public void setHomid(int homid) {
		this.homid = homid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public String getEnvir() {
		return envir;
	}
	public void setEnvir(String envir) {
		this.envir = envir;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Homestatus getSta() {
		return sta;
	}
	public void setSta(Homestatus sta) {
		this.sta = sta;
	}
	public Hometype getHtyp() {
		return htyp;
	}
	public void setHtyp(Hometype htyp) {
		this.htyp = htyp;
	}
	
	public Home(int homid, String address, int price, int area, int sprice, String envir, String remark,
			String realname, String photoname, String phototype, Homestatus sta, Hometype htyp) {
		super();
		this.homid = homid;
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.remark = remark;
		this.realname = realname;
		this.photoname = photoname;
		this.phototype = phototype;
		this.sta = sta;
		this.htyp = htyp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + area;
		result = prime * result + ((envir == null) ? 0 : envir.hashCode());
		result = prime * result + homid;
		result = prime * result + ((htyp == null) ? 0 : htyp.hashCode());
		result = prime * result + ((photoname == null) ? 0 : photoname.hashCode());
		result = prime * result + ((phototype == null) ? 0 : phototype.hashCode());
		result = prime * result + price;
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + sprice;
		result = prime * result + ((sta == null) ? 0 : sta.hashCode());
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
		Home other = (Home) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area != other.area)
			return false;
		if (envir == null) {
			if (other.envir != null)
				return false;
		} else if (!envir.equals(other.envir))
			return false;
		if (homid != other.homid)
			return false;
		if (htyp == null) {
			if (other.htyp != null)
				return false;
		} else if (!htyp.equals(other.htyp))
			return false;
		if (photoname == null) {
			if (other.photoname != null)
				return false;
		} else if (!photoname.equals(other.photoname))
			return false;
		if (phototype == null) {
			if (other.phototype != null)
				return false;
		} else if (!phototype.equals(other.phototype))
			return false;
		if (price != other.price)
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sprice != other.sprice)
			return false;
		if (sta == null) {
			if (other.sta != null)
				return false;
		} else if (!sta.equals(other.sta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Home [homid=" + homid + ", address=" + address + ", price=" + price + ", area=" + area + ", sprice="
				+ sprice + ", envir=" + envir + ", remark=" + remark + ", realname=" + realname + ", photoname="
				+ photoname + ", phototype=" + phototype + ", sta=" + sta + ", htyp=" + htyp + "]";
	}
	public Home() {
		super();
	}
	public Home(String address, int price, int area, int sprice, String envir, String remark, Homestatus sta,
			Hometype htyp) {
		super();
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.remark = remark;
		this.sta = sta;
		this.htyp = htyp;
	}
	public Home(int homid, String address, int price, int area, int sprice, String envir, Homestatus sta,
			Hometype htyp,String photoname) {
		super();
		this.homid = homid;
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.sta = sta;
		this.htyp = htyp;
		this.photoname=photoname;
	}
	public Home(int homid, String address, int price, int area, int sprice, String envir, String remark, Homestatus sta,
			Hometype htyp,String realname,String photoname,String phototype) {
		super();
		this.homid = homid;
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.remark = remark;
		this.sta = sta;
		this.htyp = htyp;
		this.realname=realname;
		this.photoname=photoname;
		this.phototype=phototype;
	}
	public Home(int homid, String address, int price, int area, int sprice, String envir, String remark, Homestatus sta,
			Hometype htyp) {
		super();
		this.homid = homid;
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.remark = remark;
		this.sta = sta;
		this.htyp = htyp;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getPhototype() {
		return phototype;
	}
	public void setPhototype(String phototype) {
		this.phototype = phototype;
	}
	public Home(String address, int price, int area, int sprice, String envir, String remark,  Homestatus sta, Hometype htyp,String realname,
			String photoname, String phototype) {
		super();
		this.address = address;
		this.price = price;
		this.area = area;
		this.sprice = sprice;
		this.envir = envir;
		this.remark = remark;
		this.realname = realname;
		this.photoname = photoname;
		this.phototype = phototype;
		this.sta = sta;
		this.htyp = htyp;
	}
	
	
	
	
}
