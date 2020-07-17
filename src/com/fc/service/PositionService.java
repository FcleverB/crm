package com.fc.service;

import java.util.List;

import com.fc.entity.Position;

public interface PositionService {
	/**
	 * 添加岗位
	 * @param pos
	 * @return
	 */
	public int add(Position pos);
	/**
	 * 岗位管理
	 * 		查询所有岗位信息
	 * @return
	 */
	public List<Position> findAll();
	/**
	 * 岗位管理
	 * 		修改指定岗位信息
	 * @param fpoid
	 * @return
	 */
	public Position findById(int fpoid);
	/**
	 * 岗位管理
	 * 		更新指定岗位信息
	 * @param pos
	 * @return
	 */
	public int update(Position pos);
	/**
	 * 岗位管理
	 * 		删除指定部门
	 * @param fpoid
	 */
	public int delete(int fpoid);

}
