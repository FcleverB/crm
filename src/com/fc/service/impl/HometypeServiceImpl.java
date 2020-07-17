package com.fc.service.impl;

import java.util.List;

import javax.enterprise.inject.New;

import com.fc.dao.HometypeDao;
import com.fc.dao.impl.HometypeDaoImpl;
import com.fc.entity.Hometype;
import com.fc.service.HometypeService;



public class HometypeServiceImpl implements HometypeService{
	private HometypeDao typeDao=new HometypeDaoImpl();
	/**
	 * 查询所有的户型信息
	 */
	@Override
	public List<Hometype> findAll() {
		return this.typeDao.findAll();
	}

}
