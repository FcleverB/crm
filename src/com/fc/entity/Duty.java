package com.fc.entity;

import java.util.Date;

/**
 * 考勤类
 * @author Administrator
 *
 */
public class Duty {
	private int dtID;
	private String empId;
	private Date dtDate;
	private String signinTime;//签到时间
	private String signoutTime;//签退时间
	private Employee emp;//员工信息
	public int getDtID() {
		return dtID;
	}
	public void setDtID(int dtID) {
		this.dtID = dtID;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Date getDtDate() {
		return dtDate;
	}
	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	public String getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}
	public String getSignoutTime() {
		return signoutTime;
	}
	public void setSignoutTime(String signoutTime) {
		this.signoutTime = signoutTime;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Duty() {
		super();
	}
	public Duty(int dtID, Date dtDate, String signinTime, String signoutTime, Employee emp) {
		super();
		this.dtID = dtID;
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
		this.emp = emp;
	}
	public Duty(String empId, Date dtDate, String signinTime, String signoutTime) {
		super();
		this.empId = empId;
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
	}
	@Override
	public String toString() {
		return "Duty [dtID=" + dtID + ", empId=" + empId + ", dtDate=" + dtDate + ", signinTime=" + signinTime
				+ ", signoutTime=" + signoutTime + ", emp=" + emp + "]";
	}
	public Duty(Date dtDate, String signinTime, String signoutTime) {
		super();
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
	}
	
}
