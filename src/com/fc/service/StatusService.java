package com.fc.service;

import java.util.List;

import com.fc.entity.Status;

public interface StatusService {
	/**
	 * 查询所有员工状态
	 * @return
	 */
	List<Status> findAll();

}
