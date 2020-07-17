package com.fc.service.impl;

import java.util.List;

import com.fc.dao.HomestatusDao;
import com.fc.dao.impl.HomeDaoImpl;
import com.fc.dao.impl.HomestatusDaoImpl;
import com.fc.entity.Homestatus;
import com.fc.service.HomestatusService;

public class HomestatusServiceImpl implements HomestatusService{
	private HomestatusDao staDao=new HomestatusDaoImpl();
	/**
	 * 查询房屋状态信息
	 */
	@Override
	public List<Homestatus> findAll() {
		return this.staDao.findAll();
	}
	

}
