package com.fc.dao;

import java.util.List;

import com.fc.entity.Department;
import com.fc.entity.Duty;

public interface DutyDao {
	/**
	 * ��ѯ������Ϣ
	 * @param empId		���ڱ��
	 * @param today		��������
	 * @return
	 */
	boolean find(String empid, java.sql.Date today);
	/**
	 * ��ӿ�����Ϣ
	 * @param duty
	 * @return
	 */
	int save(Duty duty);
	/**
	 * ǩ�˹���
	 * @param duty
	 * @return
	 */
	int update(Duty duty);
	/**
	 * ��ѯ������Ϣ
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, java.sql.Date dtDate,int start,int end);
	/**
	 * ��ѯ�ҵĿ�����Ϣ
	 * @param empid
	 * @return
	 */
	List<Duty> findmy(String empid);
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	int findAll();
	/**
	 * ��ѯ������Ϣ
	 * @param empid
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> find(String empid, int deptno, java.sql.Date dtDate);
	
	
}
