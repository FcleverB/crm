package com.fc.dao;

import java.util.List;

import com.fc.entity.Department;
import com.fc.entity.Duty;

public interface DutyDao {
	/**
	 * 查询考勤信息
	 * @param empId		考勤编号
	 * @param today		考勤日期
	 * @return
	 */
	boolean find(String empid, java.sql.Date today);
	/**
	 * 添加考勤信息
	 * @param duty
	 * @return
	 */
	int save(Duty duty);
	/**
	 * 签退功能
	 * @param duty
	 * @return
	 */
	int update(Duty duty);
	/**
	 * 查询考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, java.sql.Date dtDate,int start,int end);
	/**
	 * 查询我的考勤信息
	 * @param empid
	 * @return
	 */
	List<Duty> findmy(String empid);
	/**
	 * 分页查询
	 * @return
	 */
	int findAll();
	/**
	 * 查询考勤信息
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, java.sql.Date dtDate);
	
	
}
