package com.fc.dao;

import java.util.List;

import com.fc.entity.Employee;

public interface EmployeeDao {
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	public int save(Employee emp);
	/**
	 * 查找指定类型的员工
	 * @param i
	 * @return
	 */
	public List<Employee> findByType(int etype);
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
	public List<Employee> find(String empid, int deptid, int onduty,int start,int end);
	/**
	 * 删除指定编号的员工
	 * @param empid
	 * @return
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
	 * 		更新员工信息
	 * @param emp
	 * @return
	 */
	public int update(Employee emp);
	/**
	 * 修改密码
	 * @param empid
	 * @param pwd
	 * @return
	 */
	public int updatepwd(String empid, String pwd);
	
	
}
