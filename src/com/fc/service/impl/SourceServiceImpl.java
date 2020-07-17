package com.fc.service.impl;

import java.util.List;

import com.fc.dao.SourceDao;
import com.fc.dao.impl.SourceDaoImpl;
import com.fc.entity.Source;
import com.fc.service.SourceService;

public class SourceServiceImpl implements SourceService{
	private SourceDao sourDao=new SourceDaoImpl();
	/**
	 * 查询所有客户来源
	 */
	@Override
	public List<Source> findAll() {
		return this.sourDao.findAll();
	}

}
