package com.fc.service.impl;


import java.util.List;

import com.fc.dao.DepartmentDao;
import com.fc.dao.impl.DepartmentDaoImpl;
import com.fc.entity.Department;
import com.fc.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao deptDao= new DepartmentDaoImpl();
	/**
	 * 添加部门
	 */
	@Override
	public int add(Department dept) {
		return this.deptDao.save(dept);
	}
	/**
	 * 部门管理
	 * 		查询所有部门信息
	 */
	@Override
	public List<Department> findAll() {
		return this.deptDao.findAll();
	}
	/**
	 * 部门管理
	 * 		删除指定部门
	 */
	@Override
	public int delete(int deptid) {
		return this.deptDao.delete(deptid);
	}
	/**
	 * 部门管理
	 * 		修改指定部门
	 */
	@Override
	public Department findById(int deptid) {
		return this.deptDao.findById(deptid);
	}
	/**
	 * 部门管理
	 * 		更新指定部门信息
	 */
	@Override
	public int update(Department dept) {
		return this.deptDao.update(dept);
	}
	
}
