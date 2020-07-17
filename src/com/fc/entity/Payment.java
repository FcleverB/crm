package com.fc.entity;

import java.util.Date;

public class Payment {
	private int payid;
	private int totalamount;
	private Date paytime;
	
	private Bargin bargin;
	private Auditing auditing;
	private Employee employee;
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public Bargin getBargin() {
		return bargin;
	}
	public void setBargin(Bargin bargin) {
		this.bargin = bargin;
	}
	public Auditing getAuditing() {
		return auditing;
	}
	public void setAuditing(Auditing auditing) {
		this.auditing = auditing;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Payment() {
		super();
	}
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	@Override
	public String toString() {
		return "Payment [payid=" + payid + ", totalamount=" + totalamount + ", paytime=" + paytime + ", bargin="
				+ bargin + ", auditing=" + auditing + ", employee=" + employee + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auditing == null) ? 0 : auditing.hashCode());
		result = prime * result + ((bargin == null) ? 0 : bargin.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + payid;
		result = prime * result + ((paytime == null) ? 0 : paytime.hashCode());
		result = prime * result + totalamount;
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
		Payment other = (Payment) obj;
		if (auditing == null) {
			if (other.auditing != null)
				return false;
		} else if (!auditing.equals(other.auditing))
			return false;
		if (bargin == null) {
			if (other.bargin != null)
				return false;
		} else if (!bargin.equals(other.bargin))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (payid != other.payid)
			return false;
		if (paytime == null) {
			if (other.paytime != null)
				return false;
		} else if (!paytime.equals(other.paytime))
			return false;
		if (totalamount != other.totalamount)
			return false;
		return true;
	}
	public Payment(int totalamount, Date paytime, Bargin bargin, Auditing auditing, Employee employee) {
		super();
		this.totalamount = totalamount;
		this.paytime = paytime;
		this.bargin = bargin;
		this.auditing = auditing;
		this.employee = employee;
	}
	
	
	
	
}
