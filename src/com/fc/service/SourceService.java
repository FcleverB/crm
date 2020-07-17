package com.fc.service;

import java.util.List;

import com.fc.entity.Source;

public interface SourceService {
	/**
	 * 查询所有客户来源
	 * @return
	 */
	List<Source> findAll();

}
