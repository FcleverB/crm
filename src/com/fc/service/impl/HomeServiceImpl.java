package com.fc.service.impl;



import java.util.List;

import com.fc.dao.HomeDao;
import com.fc.dao.impl.HomeDaoImpl;
import com.fc.entity.Home;
import com.fc.service.HomeService;
import com.sun.mail.handlers.image_gif;

public class HomeServiceImpl implements HomeService{
	private HomeDao homDao=new HomeDaoImpl();
	/**
	 * 添加房屋信息
	 */
	@Override
	public int add(Home home) {
		return this.homDao.save(home);
	}
	/**
	 * 多条件查询房屋信息
	 */
	@Override
	public List<Home> findHom(String address, int tyid, int stas,int start,int end) {
		return this.homDao.findHom(address,tyid,stas,start,end);
	}
	/**
	 * 修改指定编号的房屋信息
	 */
	@Override
	public Home findById(int homid) {
		return this.homDao.findById(homid);
	}
	/**
	 * 删除指定房屋信息
	 */
	@Override
	public void delete(int homid) {
		this.homDao.delete(homid);
		
	}
	/**
	 * 更新操作
	 */
	@Override
	public int update(Home home) {
		return this.homDao.update(home);
	}
	/**
	 * 查询所有房屋信息
	 */
	@Override
	public List<Home> findAll() {
		return this.homDao.findAll();
	}
	

}
