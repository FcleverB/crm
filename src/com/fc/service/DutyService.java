package com.fc.service;

import java.sql.Date;
import java.util.List;

import com.fc.entity.Duty;

public interface DutyService {
	/**
	 * 签到功能
	 * @param empId
	 * @return
	 */
	int signin(String empid);
	/**
	 * 签退功能
	 * @param empid
	 * @return
	 */
	int signout(String empid);
	/**
	 * 查询考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> findDuty(String empid, int deptno, Date dtDate,int start,int end);
	/**
	 * 查询我的考勤信息
	 * @param empid
	 * @return
	 */
	List<Duty> findmy(String empid);
	/**
	 * 查询考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, Date dtDate);
	

}
