package com.fc.service;

import com.fc.entity.Auditing;

public interface AuditingService {
	/**
	 * 查询审核单编号
	 * @param barid
	 * @return
	 */
	int findId(int barid);
	
}
