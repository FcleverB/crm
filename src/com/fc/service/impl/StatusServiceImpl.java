package com.fc.service.impl;

import java.util.List;

import com.fc.dao.StatusDao;
import com.fc.dao.impl.StatusDaoImpl;
import com.fc.entity.Status;
import com.fc.service.StatusService;

public class StatusServiceImpl implements StatusService{
	private StatusDao staDao=new StatusDaoImpl();
	/**
	 * 查询所有的员工状态
	 */
	@Override
	public List<Status> findAll() {
		return this.staDao.findAll();
	}

}
