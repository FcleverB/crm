package com.fc.service;

import java.util.List;

import com.fc.entity.Type;

public interface TypeService {
	/**
	 * 查询所有客户类型
	 * @return
	 */
	List<Type> findAll();

}
