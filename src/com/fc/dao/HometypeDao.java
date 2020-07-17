package com.fc.dao;

import java.util.List;

import com.fc.entity.Hometype;

public interface HometypeDao {
	/**
	 * 查询所有户型信息
	 * @return
	 */
	List<Hometype> findAll();

}
