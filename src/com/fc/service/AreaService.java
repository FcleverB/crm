package com.fc.service;

import java.util.List;
import java.util.TreeMap;

import com.fc.entity.Area;



public interface AreaService {
	/**
	 * 根据pid查询地区信息
	 * @param pid
	 * @return
	 */
	List<Area> getAreaInfoService(String pid);
	/**
	 * 根据编号查找省市区的名称
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
