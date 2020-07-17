package com.fc.dao;

import java.util.List;

import com.fc.entity.Homestatus;

public interface HomestatusDao {
	/**
	 * 查询房屋状态信息
	 * @return
	 */
	List<Homestatus> findAll();


}
