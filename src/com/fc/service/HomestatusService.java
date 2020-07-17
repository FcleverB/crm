package com.fc.service;

import java.util.List;

import com.fc.entity.Homestatus;

public interface HomestatusService {
	/**
	 * 查询所有房屋交易状态信息
	 * @return
	 */
	List<Homestatus> findAll();


}
