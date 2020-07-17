package com.fc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 员工类
 * @author Administrator
 *
 */
public class Employee {
	private String empid;//员工编号
	private String pwd;//密码
	private String ename;//真实姓名
	private String sex;//性别
	private Date birth;//出生日期
	private Date indate;//入职日期
	private Date leave;//离职日期
	private int onduty;//是否在职  0-离职  1-在职  
	private int etype;//员工类型  1.普通员工  2.管理人员 含经理、总监、总裁等  3.管理员
	private String phone;//联系方式
	private String qq;
	private String linkman;//紧急联系人信息
	private String idcard;//身份证号码
	
	
	private Department dept; //员工所属部门  不仅包含部门的编号，还包含其他信息
	private Position position;
	private Employee mgr;//上级领导的信息
	private List<Employee> empList = new ArrayList<Employee>();//下级的信息，可能多个
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public Date getLeave() {
		return leave;
	}
	public void setLeave(Date leave) {
		this.leave = leave;
	}
	public int getOnduty() {
		return onduty;
	}
	public void setOnduty(int onduty) {
		this.onduty = onduty;
	}
	public int getEtype() {
		return etype;
	}
	public void setEtype(int etype) {
		this.etype = etype;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Employee getMgr() {
		return mgr;
	}
	public void setMgr(Employee mgr) {
		this.mgr = mgr;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public Employee() {
		super();
	}
	
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", pwd=" + pwd + ", ename=" + ename + ", sex=" + sex + ", birth=" + birth
				+ ", indate=" + indate + ", leave=" + leave + ", onduty=" + onduty + ", etype=" + etype + ", phone="
				+ phone + ", qq=" + qq + ", linkman=" + linkman + ", idcard=" + idcard + ", dept=" + dept
				+ ", position=" + position + ", mgr=" + mgr + ", empList=" + empList + "]";
	}
	public Employee(String pwd, String ename, String sex, Date birth, Date indate, Date leave, int onduty, int etype,
			String phone, String qq, String linkman, String idcard, Department dept, Position position, Employee mgr) {
		super();
		this.pwd = pwd;
		this.ename = ename;
		this.sex = sex;
		this.birth = birth;
		this.indate = indate;
		this.leave = leave;
		this.onduty = onduty;
		this.etype = etype;
		this.phone = phone;
		this.qq = qq;
		this.linkman = linkman;
		this.idcard = idcard;
		this.dept = dept;
		this.position = position;
		this.mgr = mgr;
	}
	public Employee(String empid, String ename, String sex, String phone) {
		super();
		this.empid = empid;
		this.ename = ename;
		this.sex = sex;
		this.phone = phone;
	}
	public Employee(String empid, String pwd, String ename, String sex, Date birth, Date indate, Date leave, int onduty,
			int etype, String phone, String qq, String linkman, String idcard, Department dept, Position position,
			Employee mgr) {
		super();
		this.empid = empid;
		this.pwd = pwd;
		this.ename = ename;
		this.sex = sex;
		this.birth = birth;
		this.indate = indate;
		this.leave = leave;
		this.onduty = onduty;
		this.etype = etype;
		this.phone = phone;
		this.qq = qq;
		this.linkman = linkman;
		this.idcard = idcard;
		this.dept = dept;
		this.position = position;
		this.mgr = mgr;
	}
	
	
	
	
}
