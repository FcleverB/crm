package com.fc.service.impl;

import javax.enterprise.inject.New;

import com.fc.dao.AuditingDao;
import com.fc.dao.impl.AuditingDaoImpl;
import com.fc.entity.Auditing;
import com.fc.service.AuditingService;

public class AuditingServiceImpl implements AuditingService{
	private AuditingDao audDao=new AuditingDaoImpl();
	/**
	 * 查找指定审核单编号
	 */
	@Override
	public int findId(int barid) {
		return this.audDao.findId(barid);
	}

	

}
