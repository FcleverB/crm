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
	 * 签到功能
	 */
	@Override
	public int signin(String empid) {
		//判断用户是否已经签到
		Date now=new Date();//yyyyMMdd hhmmss
		java.sql.Date today=new java.sql.Date(now.getTime());
		boolean flag=dutyDao.find(empid,today);
		//如果已经签到,返回2;如果没有签到,进行签到
		int n=0;
		if (flag) {
			return 2;
		}else {
			//完成签到
			Duty duty=new Duty();
			//duty.seDtID(dtID);//序列自增
			duty.setEmpId(empid);//员工编号
			//duty.seEmp(emp);//此处使用太繁琐
			duty.setDtDate(today);
			DateFormat sdf=new SimpleDateFormat("hh:mm:ss");
			String signinTime=sdf.format(now);
			duty.setSigninTime(signinTime);
			n=dutyDao.save(duty);
			return n;
		}
	}
	/**
	 * 签退功能
	 */
	@Override
	public int signout(String empid) {
		//判断用户是否已经签到
		Date now=new Date();//yyyyMMdd hhmmss
		java.sql.Date today=new java.sql.Date(now.getTime());
		boolean flag=dutyDao.find(empid,today);
		//如果尚未签到,返回2;如果已经签到,进行签到
		int n=0;
		if (!flag) {//尚未签到
			return 2;
		}else {
			//完成签退
			Duty duty=new Duty();
			//duty.seDtID(dtID);//序列自增
			duty.setEmpId(empid);//员工编号
			//duty.seEmp(emp);//此处使用太繁琐
			duty.setDtDate(today);
			DateFormat sdf=new SimpleDateFormat("hh:mm:ss");
			String signoutTime=sdf.format(now);
			duty.setSignoutTime(signoutTime);
			n=dutyDao.update(duty);
			return n;
		}
	}
	/**
	 * 查询考勤信息
	 */
	@Override
	public List<Duty> findDuty(String empid, int deptno, java.sql.Date dtDate,int start,int end) {
		return this.dutyDao.find(empid, deptno,dtDate,start,end);
	}
	/**
	 * 查询我的考勤信息
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
