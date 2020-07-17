package com.fc.service.impl;

import java.util.List;
import java.util.TreeMap;

import com.fc.dao.AreaDao;
import com.fc.dao.PaymentDao;
import com.fc.dao.impl.AreaDaoImpl;
import com.fc.dao.impl.PaymentDaoImpl;
import com.fc.entity.Area;
import com.fc.service.AreaService;

public class AreaServiceImpl implements AreaService{
	//创建Dao层对象
	AreaDao ad=new AreaDaoImpl();
	/**
	 * 根据pid查询地区信息
	 */
	public List<Area> getAreaInfoService(String pid) {
		return ad.getAreaInfoDao(pid);
	}
	/**
	 * 根据编号查找省市区的名称
	 */
	@Override
	public String findById(int id) {
		return ad.findById(id);
	}
	/**
	 * 查找不同省客户数量
	 */
	@Override
	public TreeMap<String, Integer> findAreaCounts() {
		return this.ad.findAreaCounts();
	}
}
