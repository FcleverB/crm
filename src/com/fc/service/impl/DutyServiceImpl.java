package com.fc.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fc.dao.DutyDao;
import com.fc.dao.impl.DutyDaoImpl;
import com.fc.entity.Duty;
import com.fc.service.DutyService;

public class DutyServiceImpl implements DutyService{
	private DutyDao dutyDao=new DutyDaoImpl();
	/**
	 * ǩ������
	 */
	@Override
	public int signin(String empid) {
		//�ж��û��Ƿ��Ѿ�ǩ��
		Date now=new Date();//yyyyMMdd hhmmss
		java.sql.Date today=new java.sql.Date(now.getTime());
		boolean flag=dutyDao.find(empid,today);
		//����Ѿ�ǩ��,����2;���û��ǩ��,����ǩ��
		int n=0;
		if (flag) {
			return 2;
		}else {
			//���ǩ��
			Duty duty=new Duty();
			//duty.seDtID(dtID);//��������
			duty.setEmpId(empid);//Ա�����
			//duty.seEmp(emp);//�˴�ʹ��̫����
			duty.setDtDate(today);
			DateFormat sdf=new SimpleDateFormat("hh:mm:ss");
			String signinTime=sdf.format(now);
			duty.setSigninTime(signinTime);
			n=dutyDao.save(duty);
			return n;
		}
	}
	/**
	 * ǩ�˹���
	 */
	@Override
	public int signout(String empid) {
		//�ж��û��Ƿ��Ѿ�ǩ��
		Date now=new Date();//yyyyMMdd hhmmss
		java.sql.Date today=new java.sql.Date(now.getTime());
		boolean flag=dutyDao.find(empid,today);
		//�����δǩ��,����2;����Ѿ�ǩ��,����ǩ��
		int n=0;
		if (!flag) {//��δǩ��
			return 2;
		}else {
			//���ǩ��
			Duty duty=new Duty();
			//duty.seDtID(dtID);//��������
			duty.setEmpId(empid);//Ա�����
			//duty.seEmp(emp);//�˴�ʹ��̫����
			duty.setDtDate(today);
			DateFormat sdf=new SimpleDateFormat("hh:mm:ss");
			String signoutTime=sdf.format(now);
			duty.setSignoutTime(signoutTime);
			n=dutyDao.update(duty);
			return n;
		}
	}
	/**
	 * ��ѯ������Ϣ
	 */
	@Override
	public List<Duty> findDuty(String empid, int deptno, java.sql.Date dtDate,int start,int end) {
		return this.dutyDao.find(empid, deptno,dtDate,start,end);
	}
	/**
	 * ��ѯ�ҵĿ�����Ϣ
	 */
	@Override
	public List<Duty> findmy(String empid) {
		return this.dutyDao.findmy(empid);
	}
	@Override
	public List<Duty> find(String empid, int deptno, java.sql.Date dtDate) {
		return this.dutyDao.find(empid, deptno,dtDate);
	}
	

}
