package com.fc.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.fc.dao.AuditingDao;
import com.fc.dao.BarginDao;
import com.fc.dao.PaymentDao;
import com.fc.dao.impl.AuditingDaoImpl;
import com.fc.dao.impl.BarginDaoImpl;
import com.fc.dao.impl.PaymentDaoImpl;
import com.fc.entity.Auditing;
import com.fc.entity.Bargin;
import com.fc.entity.Payment;
import com.fc.service.BarginService;
import com.fc.util.Constants;
import com.fc.util.DBUtil2;
import com.fc.util.MyException;

import oracle.net.aso.a;
import oracle.net.aso.b;

public class BarginServiceImpl implements BarginService{
	private BarginDao barDao=new BarginDaoImpl();
	/**
	 * 添加成交项
	 */
	@Override
	public int add(Bargin bargin) {
		return this.barDao.save(bargin);
	}
	/**
	 * 查询当前用户的待审成交表
	 */
	@Override
	public List<Bargin> getToAudit(String auditorId, int barid,int start,int end) {
		return this.barDao.findByAuditorId(auditorId, barid,start,end);
	}
	/**
	 * 审核交易单
	 */
	@Override
	public void audit(Auditing audit) {
		AuditingDao auditingDao=new AuditingDaoImpl();
		BarginDao barDao=new BarginDaoImpl();
		// 开启事务
		Connection conn = DBUtil2.getConnection();
		try {
			conn.setAutoCommit(false);// 事务不再自动结束(提交,回滚),事务并没有在此开始
			//审核通过吗
			String result=audit.getAresult();
			
			Bargin bar=new Bargin();
			bar.setBarid(audit.getBargin().getBarid());
			bar.setStatus(audit.getAresult());
			
			if ("通过".equals(result)) {//审核通过
				//是财务吗
				if (audit.getEmp().getPosition().getFpoid()==2) {//是财务
					//添加收入信息
					Payment pm=new Payment();
					
					Bargin bar2=barDao.findById(audit.getBargin().getBarid());
					
					pm.setTotalamount(bar2.getPrice());//收入金额
					pm.setBargin(bar);//成交单编号
					pm.setAuditing(audit);//审核单编号
					pm.setEmployee(audit.getEmp());//审核人
					pm.setPaytime(new Date());
					
					PaymentDao pmDao=new PaymentDaoImpl();
					pmDao.save(pm);
					//修改报销单状态
					bar.setStatus(Constants.EXPENSE_STATUS_CASHED);
					bar.setNextemp(null);//审核结束
				}else {//不是财务
					//金额大于2500吗  auditing.getExp().getTotalAmount()>2500 空指针异常
					//获取报销单金额
					int barId=audit.getBargin().getBarid();
					Bargin bar2=barDao.findById(barId);
					if (bar2.getPrice()>15000) {//大于20000
						//是总裁吗
						if (Constants.POSITION_CEOID.equals(audit.getEmp().getEmpid())) {//是总裁
							//添加审核记录
							auditingDao.save(audit);
							//修改报销单状态
							bar.setStatus(Constants.EXPENSE_STATUS_PASS);
							bar.setNextemp(Constants.POSITION_CFOID);//下个审核人  财务
						}else {//不是总裁
							//添加审核记录
							auditingDao.save(audit);
							//修改报销单状态
							bar.setStatus(Constants.EXPENSE_STATUS_AUDITING);
							bar.setNextemp(Constants.POSITION_CEOID);//下个审核人  总裁
							//exp.setNextAuditorId(auditing.getAuditor().getMgr().getEmpId());
						}
					}else {//小于等于20000
						//添加审核记录
						auditingDao.save(audit);
						//修改报销单状态  下个处理人财务  状态:审核通过
						//此处有剪切
//						Expense exp=new Expense();
//						exp.setExpId(auditing.getExpId());
//						exp.setLastResult(auditing.getResult());
						bar.setStatus(Constants.EXPENSE_STATUS_PASS);
						bar.setNextemp(Constants.POSITION_CFOID);//下个审核人  财务
					}
				}
			}else {//审核不通过,拒绝或者打回
				//添加审核记录
				auditingDao.save(audit);
				//修改报销单状态
				if ("打回".equals(audit.getAresult())) {
					bar.setStatus(Constants.EXPENSE_STATUS_BACK);
				}else {
					bar.setStatus(Constants.EXPENSE_STATUS_REJECT);
				}
				bar.setEmp(null);//拒绝打回  没有下个审核人
				
			}
			barDao.update(bar);
			// 提交或回滚事务
			conn.commit();
		} catch (Exception e) {// 所有异常 包括运行时异常
			e.printStackTrace();
			try {
				conn.rollback();// 回滚数据
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MyException(e.toString());
			}
			throw new MyException(e.toString());// 向上一层抛出异常信息
		} finally {
			DBUtil2.closeAll(null, null, conn);
		}
		
	}
	/**
	 * 查看我的交易单
	 */
	@Override
	public List<Bargin> findMy(int barid, String empid,String ename,int start,int end) {
		return this.barDao.findMy(barid,empid,ename,start,end);
	}

}
