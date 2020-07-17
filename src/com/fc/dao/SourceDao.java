package com.fc.dao;

import java.util.List;

import com.fc.entity.Source;

public interface SourceDao {
	/**
	 * 查询所有客户来源
	 * @return
	 */
	List<Source> findAll();

}
