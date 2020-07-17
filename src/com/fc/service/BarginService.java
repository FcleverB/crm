package com.fc.service;

import java.util.List;

import com.fc.entity.Auditing;
import com.fc.entity.Bargin;

public interface BarginService {
	/**
	 * 添加成交项
	 * @param bargin
	 * @return
	 */
	int add(Bargin bargin);
	/**
	 * 查询当前用户的待审成交表
	 * @param auditorId
	 * @return
	 */
	List<Bargin> getToAudit(String auditorId, int barid,int start,int end);
	/**
	 * 审核交易单
	 * @param audit
	 */
	void audit(Auditing audit);
	/**
	 * 查看我的交易单
	 * @param barid
	 * @param empid
	 * @return
	 */
	List<Bargin> findMy(int barid, String empid,String ename,int start,int end);

}
