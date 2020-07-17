package com.fc.dao;

import java.util.List;

import com.fc.entity.Type;

public interface TypeDao {
	/**
	 * 查询所有客户类型
	 * @return
	 */
	List<Type> findAll();

}
