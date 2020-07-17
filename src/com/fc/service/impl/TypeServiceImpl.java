package com.fc.service.impl;

import java.util.List;

import com.fc.dao.TypeDao;
import com.fc.dao.impl.TypeDaoImpl;
import com.fc.entity.Type;
import com.fc.service.TypeService;

public class TypeServiceImpl implements TypeService{
	private TypeDao typeDao=new TypeDaoImpl();
	/**
	 * 查询所有客户类型
	 */
	@Override
	public List<Type> findAll() {
		return this.typeDao.findAll();
	}

}
