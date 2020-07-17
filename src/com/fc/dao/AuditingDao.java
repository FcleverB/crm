package com.fc.dao;

import com.fc.entity.Auditing;

public interface AuditingDao {
	/**
	 * 添加审核记录
	 * @param auditing
	 */
	public void save(Auditing auditing);
	/**
	 * 查询指定审核单编号
	 * @param barid
	 * @return
	 */
	public int findId(int barid);
}
