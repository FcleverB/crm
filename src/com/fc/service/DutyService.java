package com.fc.service;

import java.sql.Date;
import java.util.List;

import com.fc.entity.Duty;

public interface DutyService {
	/**
	 * ǩ������
	 * @param empId
	 * @return
	 */
	int signin(String empid);
	/**
	 * ǩ�˹���
	 * @param empid
	 * @return
	 */
	int signout(String empid);
	/**
	 * ��ѯ������Ϣ
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> findDuty(String empid, int deptno, Date dtDate,int start,int end);
	/**
	 * ��ѯ�ҵĿ�����Ϣ
	 * @param empid
	 * @return
	 */
	List<Duty> findmy(String empid);
	/**
	 * ��ѯ������Ϣ
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, Date dtDate);
	

}
