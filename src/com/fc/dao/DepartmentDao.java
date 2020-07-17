package com.fc.dao;

import java.util.List;

import com.fc.entity.Department;

public interface DepartmentDao {
	/**
	 * 添加部门
	 */
	public int save(Department dept);
	/**
	 * 部门管理
	 * 		查询所有部门信息
	 * @return
	 */
	public List<Department> findAll();
	/**
	 * 部门管理
	 * 		删除指定部门
	 * @param deptno
	 * @return
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
