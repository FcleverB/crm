package com.fc.service;

import java.util.List;

import com.fc.entity.Home;

public interface HomeService {
	/**
	 * 添加房屋信息
	 * @param home
	 * @return
	 */
	int add(Home home);
	/**
	 * 多条件查询房屋信息
	 * @param address
	 * @param tyid
	 * @param stas
	 * @return
	 */
	List<Home> findHom(String address, int tyid, int stas,int start,int end);
	/**
	 * 修改指定编号的房屋信息
	 * @param homid
	 * @return
	 */
	Home findById(int homid);
	/**
	 * 删除指定房屋信息
	 * @param homid
	 */
	void delete(int homid);
	/**
	 * 更新操作
	 * @param home
	 * @return
	 */
	int update(Home home);
	/**
	 * 查询所有房屋信息
	 * @return
	 */
	List<Home> findAll();
	

}
