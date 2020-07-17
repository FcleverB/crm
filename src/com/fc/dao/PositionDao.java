package com.fc.dao;

import java.util.List;

import com.fc.entity.Position;

public interface PositionDao {
	/**
	 * 添加岗位
	 * @param pos
	 * @return
	 */
	int save(Position pos);
	/**
	 * 岗位管理
	 * 		查询所有岗位信息
	 * @return
	 */
	List<Position> findAll();
	/**
	 * 岗位管理
	 * 		修改指定岗位信息
	 * @param fpoid
	 * @return
	 */
	Position findById(int fpoid);
	/**
	 * 岗位管理
	 * 		更新指定岗位信息
	 * @param pos
	 * @return
	 */
	int update(Position pos);
	/**
	 * 岗位管理
	 * 		删除指定岗位信息
	 * @param fpoid
	 * @return
	 */
	int delete(int fpoid);

}
