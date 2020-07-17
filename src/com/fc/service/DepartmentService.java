package com.fc.service;

import java.util.List;

import com.fc.entity.Department;

public interface DepartmentService {
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int add(Department dept);
	/**
	 * 部门管理
	 * 		查询所有的部门信息
	 * @return
	 */
	public List<Department> findAll();
	/**
	 * 部门管理
	 * 		删除指定部门
	 * @param deptno
	 */
	public int delete(int deptid);
	/**
	 * 部门管理
	 * 		修改指定部门
	 * @param deptid
	 * @return
	 */
	public Department findById(int deptid);
	/**
	 * 部门管理
	 * 		更新指定部门信息
	 * @param dept
	 * @return
	 */
	public int update(Department dept);
}
