package com.fc.service.impl;

import java.util.List;

import com.fc.dao.PositionDao;
import com.fc.dao.impl.PositionDaoImpl;
import com.fc.entity.Position;
import com.fc.service.PositionService;

import oracle.net.aso.f;

public class PositionServiceImpl implements PositionService{
	private PositionDao posDao= new PositionDaoImpl();
	/**
	 * 添加岗位
	 */
	@Override
	public int add(Position pos) {
		return this.posDao.save(pos);
	}
	/**
	 * 岗位管理
	 * 		查询所有岗位信息
	 */
	@Override
	public List<Position> findAll() {
		return this.posDao.findAll();
	}
	/**
	 * 岗位管理
	 * 		修改指定岗位信息
	 */
	@Override
	public Position findById(int fpoid) {
		return this.posDao.findById(fpoid);
	}
	/**
	 * 岗位管理
	 * 		更新指定岗位信息
	 */
	@Override
	public int update(Position pos) {
		return this.posDao.update(pos);
	}
	@Override
	public int delete(int fpoid) {
		return this.posDao.delete(fpoid);
	}

}
