package com.fc.entity;

import java.util.Date;

/**
 * 成交类
 * @author Administrator
 *
 */
public class Bargin {
	private int barid;
	private int price;
	private Date bardate;
	private String nextemp;
	private String remark;
	private String status;
	
	private Employee emp;
	private Client client;
	private Home home;
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getBardate() {
		return bardate;
	}
	public void setBardate(Date bardate) {
		this.bardate = bardate;
	}
	public String getNextemp() {
		return nextemp;
	}
	public void setNextemp(String nextemp) {
		this.nextemp = nextemp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}
	@Override
	public String toString() {
		return "Bargin [barid=" + barid + ", price=" + price + ", bardate=" + bardate + ", nextemp=" + nextemp
				+ ", remark=" + remark + ", status=" + status + ", emp=" + emp + ", client=" + client + ", home=" + home
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bardate == null) ? 0 : bardate.hashCode());
		result = prime * result + barid;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((nextemp == null) ? 0 : nextemp.hashCode());
		result = prime * result + price;
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Bargin other = (Bargin) obj;
		if (bardate == null) {
			if (other.bardate != null)
				return false;
		} else if (!bardate.equals(other.bardate))
			return false;
		if (barid != other.barid)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (nextemp == null) {
			if (other.nextemp != null)
				return false;
		} else if (!nextemp.equals(other.nextemp))
			return false;
		if (price != other.price)
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public Bargin() {
		super();
	}
	public Bargin(int price, Date bardate, String nextemp, String remark, Employee emp, Client client,
			Home home) {
		super();
		this.price = price;
		this.bardate = bardate;
		this.nextemp = nextemp;
		this.remark = remark;
		this.emp = emp;
		this.client = client;
		this.home = home;
	}
	public Bargin(int barid, int price, Date bardate, String nextemp, String remark, String status, Employee emp,
			Client client, Home home) {
		super();
		this.barid = barid;
		this.price = price;
		this.bardate = bardate;
		this.nextemp = nextemp;
		this.remark = remark;
		this.status = status;
		this.emp = emp;
		this.client = client;
		this.home = home;
	}
	
}
