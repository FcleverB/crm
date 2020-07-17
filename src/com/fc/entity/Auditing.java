package com.fc.entity;

import java.util.Date;

public class Auditing {
	private int auid;//审核编号
	private String aresult;//审核结果
	private String advice;//审核意见
	private Date time;//审核时间
	
	private Bargin bargin;//报销单编号
	private Employee emp;//审核人
	public int getAuid() {
		return auid;
	}
	public void setAuid(int auid) {
		this.auid = auid;
	}
	public String getAresult() {
		return aresult;
	}
	public void setAresult(String aresult) {
		this.aresult = aresult;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Bargin getBargin() {
		return bargin;
	}
	public void setBargin(Bargin bargin) {
		this.bargin = bargin;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "Auditing [auid=" + auid + ", aresult=" + aresult + ", advice=" + advice + ", time=" + time + ", bargin="
				+ bargin + ", emp=" + emp + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advice == null) ? 0 : advice.hashCode());
		result = prime * result + ((aresult == null) ? 0 : aresult.hashCode());
		result = prime * result + auid;
		result = prime * result + ((bargin == null) ? 0 : bargin.hashCode());
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Auditing other = (Auditing) obj;
		if (advice == null) {
			if (other.advice != null)
				return false;
		} else if (!advice.equals(other.advice))
			return false;
		if (aresult == null) {
			if (other.aresult != null)
				return false;
		} else if (!aresult.equals(other.aresult))
			return false;
		if (auid != other.auid)
			return false;
		if (bargin == null) {
			if (other.bargin != null)
				return false;
		} else if (!bargin.equals(other.bargin))
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	public Auditing() {
		super();
	}
	public Auditing(int auid, String aresult, String advice, Date time, Bargin bargin, Employee emp) {
		super();
		this.auid = auid;
		this.aresult = aresult;
		this.advice = advice;
		this.time = time;
		this.bargin = bargin;
		this.emp = emp;
	}
	public Auditing(String aresult, String advice, Date time, Bargin bargin, Employee emp) {
		super();
		this.aresult = aresult;
		this.advice = advice;
		this.time = time;
		this.bargin = bargin;
		this.emp = emp;
	}
	
	
	
	
}
