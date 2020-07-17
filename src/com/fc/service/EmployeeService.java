package com.fc.service;

import java.util.List;

import com.fc.entity.Employee;

public interface EmployeeService {
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	public int add(Employee emp);
	/**
	 * 查询指定类型的员工
	 * @param i
	 * @return
	 */
	public List<Employee> findEmpByType(int etype);
	/**
	 * 员工管理
	 * 		查询所有员工信息
	 * @return
	 */
	public List<Employee> findAll();
	/**
	 * 员工管理
	 * 		多条件查询员工信息
	 * @param empid
	 * @param deptid
	 * @param onduty
	 * @return
	 */
	public List<Employee> findEmp(String empid, int deptid, int onduty,int start,int end);
	/**
	 * 员工管理
	 * 		删除指定员工
	 * @param empid
	 */
	public void delete(String empid);
	/**
	 * 员工管理
	 * 		查询指定编号员工
	 * @param empid
	 * @return
	 */
	public Employee findById(String empid);
	/**
	 * 员工管理
	 * 		更新员工
	 * @param emp
	 * @return
	 */
	public int update(Employee emp);
	/**
	 * 登录操作
	 * @param pwd
	 * @param pwd2
	 * @return
	 */
	public Employee login(String empid, String pwd);
	/**
	 * 修改密码
	 * @param empid
	 * @param pwd
	 * @return
	 */
	public int updatepwd(String empid, String pwd);
}
