package com.fc.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import com.fc.dao.EmployeeDao;
import com.fc.dao.impl.EmployeeDaoImpl;
import com.fc.entity.Employee;
import com.fc.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDao empDao=new EmployeeDaoImpl();
	/**
	 * 添加员工
	 */
	@Override
	public int add(Employee emp) {
		return this.empDao.save(emp);
	}
	/**
	 * 查询指定类型员工
	 */
	@Override
	public List<Employee> findEmpByType(int etype) {
		return this.empDao.findByType(etype);
	}
	/**
	 * 员工管理
	 * 		查询所有员工信息
	 */
	@Override
	public List<Employee> findAll() {
		return this.empDao.findAll();
	}
	/**
	 * 员工管理
	 * 		多条件查询员工信息
	 */
	@Override
	public List<Employee> findEmp(String empid, int deptid, int onduty,int start,int end) {
		return this.empDao.find(empid, deptid, onduty,start,end);
	}
	/**
	 * 员工管理
	 * 		删除指定员工
	 */
	@Override
	public void delete(String empid) {
		this.empDao.delete(empid);
	}
	/**
	 * 员工管理
	 * 		查询指定编号员工
	 */
	@Override
	public Employee findById(String empid) {
		return this.empDao.findById(empid);
	}
	/**
	 * 员工管理
	 * 		更新员工信息
	 */
	@Override
	public int update(Employee emp) {
		return this.empDao.update(emp);
	}
	/**
	 * 登录操作
	 */
	@Override
	public Employee login(String empid, String pwd) {
		//判断登录是否成功
		//1.先判断用户名是否正确  不正确,登录失败   正确,再继续验证密码
		Employee emp=this.findById(empid);
		if (emp==null) {
			return null;
		}else {
			if (pwd!=null&pwd.equals(emp.getPwd())) {
				return emp;
			}else {
				return null;
			}
		}
	}
	@Override
	public int updatepwd(String empid, String pwd) {
		return this.empDao.updatepwd(empid,pwd);
	}
	

}
