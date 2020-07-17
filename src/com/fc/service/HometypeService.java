package com.fc.service;

import java.util.List;

import com.fc.entity.Hometype;

public interface HometypeService {
	/**
	 * 查询所有户型信息
	 * @return
	 */
	List<Hometype> findAll();

}
