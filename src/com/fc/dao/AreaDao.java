package com.fc.dao;

import java.util.List;
import java.util.TreeMap;

import com.fc.entity.Area;



public interface AreaDao {
	/**
	 * 根据pid查询地区信息
	 * @param pid
	 * @return
	 */
	List<Area> getAreaInfoDao(String pid);
	/**
	 * 根据编号查询省市县
	 * @param id
	 * @return
	 */
	String findById(int id);
	/**
	 * 查找不同省客户数量
	 * @return
	 */
	TreeMap<String, Integer> findAreaCounts();
	
}
