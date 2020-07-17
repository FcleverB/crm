package com.fc.dao;

import java.util.List;

import com.fc.entity.Payment;

public interface PaymentDao {
	/**
	 * 保存收入信息
	 * @param pm
	 */
	void save(Payment pm);
	/**
	 * 返回柱状图需要的数据
	 * @return
	 */
	List<Object[]> findStaticsData();

}
