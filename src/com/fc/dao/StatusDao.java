package com.fc.dao;

import java.util.List;

import com.fc.entity.Status;

public interface StatusDao {
	/**
	 * 查询所有员工状态
	 * @return
	 */
	List<Status> findAll();

}
