package com.fc.dao;

import java.util.List;

import com.fc.entity.Bargin;

public interface BarginDao {
	/**
	 * 添加成交项
	 * @param bargin
	 * @return
	 */
	int save(Bargin bargin);
	/**
	 * 查询当前用户的待审成交表
	 * @param auditorId
	 * @return
	 */
	List<Bargin> findByAuditorId(String auditorId, int barid,int start,int end);
	/**
	 * 修改报销单
	 * @param bargin
	 */
	void update(Bargin bargin);
	/**
	 * 按照编号查询报销单
	 * @param empid
	 * @return
	 */
	Bargin findById(int barid);
	/**
	 * 查看我的交易单
	 * @param barid
	 * @param empid
	 * @return
	 */
	List<Bargin> findMy(int barid, String empid,String ename,int start,int end);
	/**
	 * 查找成交单记录数
	 * @return
	 */
	int findsize();
	/**
	 * 查看我的成交单交易数
	 * @param empid
	 * @return
	 */
	int findsize(String empid);

}
