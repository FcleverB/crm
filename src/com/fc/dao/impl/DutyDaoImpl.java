package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.DutyDao;
import com.fc.entity.Department;
import com.fc.entity.Duty;
import com.fc.entity.Employee;
import com.fc.util.DBUtil;

public class DutyDaoImpl implements DutyDao {
	/**
	 * ǩ������
	 */
	@Override
	public boolean find(String empid, Date today) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept = null;
		boolean flag = false;
		try {
			// 2.���������ݿ�����ӣ�url��user��password��
			conn = DBUtil.getConnection();

			// 3.����SQL�����������ǹ��
			pstmt = conn.prepareStatement("select * from duty  where empid=?and dtdate=?");
			// 4.ʹ��SQL�����������SQL��������ݿ⣬���õ����صĽ�����ӵ���
			pstmt.setString(1, empid);
			pstmt.setDate(2, today);

			rs = pstmt.executeQuery();

			// 5.����������װ��List�У�
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.�ر���Դ
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.��������
		return flag;
	}

	/**
	 * ǩ������
	 */
	@Override
	public int save(Duty duty) {
		String sql = "insert into duty values (seq_duty.nextval,?,?,?,null)";
		Object[] params = { duty.getEmpId(), duty.getDtDate(), duty.getSigninTime() };
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * ǩ�˹���
	 */
	@Override
	public int update(Duty duty) {
		String sql = "update duty set signouttime =? where empid=? and dtdate=?";
		Object[] params = { duty.getSignoutTime(), duty.getEmpId(), duty.getDtDate() };
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * ��ѯ������Ϣ
	 */
	@Override
	public List<Duty> find(String empid, int deptno, Date dtDate,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Duty> list = new ArrayList<Duty>();
		boolean flag = false;
		try {
			// 2.���������ݿ�����ӣ�url��user��password��
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder("select dt.*,e.deptid,e.ename,d.deptname "
					+ " from duty dt"
					/*+ " (select * from"
					+ " (select rownum rn,d2.* from"
					+ " (select d1.* from duty d1 order by empid desc) d2"
					+ " where rownum <="+end+")"
					+ " where rn> "+start+") dt"*/
					+ " join emp e on dt.empid=e.empid" 
					+ " join dept d on e.deptid = d.deptid where 1=1");
			if (empid != null & !"".equals(empid)) {
				sql.append(" and dt.empid like '%"+empid+"%'");
			}
			if (deptno != 0) {
				sql.append(" and e.deptid =" + deptno);
			}

			if (dtDate != null) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sdtDate = sdf.format(dtDate);
				sql.append(" and to_char(dt.dtDate,'YYYY-MM-DD') >='" + sdtDate + "'");
			}
			// 3.����SQL�����������ǹ��
			pstmt = conn.prepareStatement(sql.toString());
			// 4.ʹ��SQL�����������SQL��������ݿ⣬���õ����صĽ�����ӵ���
			rs = pstmt.executeQuery();

			// 5.����������װ��List�У�
			while (rs.next()) {
				// ��ȡ�����ֶε�ֵ
				int dtID = rs.getInt("dtid");
				java.sql.Date dtDate2 = rs.getDate("dtDate");
				String signinTime = rs.getString("signinTime");
				String signoutTime = rs.getString("signoutTime");

				String empId2 = rs.getString("empid");
				String realName = rs.getString("ename");

				int deptid = rs.getInt("deptid");
				String deptname = rs.getString("deptname");
				Department dept = new Department(deptid, deptname);

				Employee emp = new Employee();
				emp.setEmpid(empId2);
				emp.setEname(realName);
				emp.setDept(dept);
				//
				Duty duty = new Duty(dtID, dtDate2, signinTime, signoutTime, emp);

				//
				list.add(duty);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.�ر���Դ
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.��������
		return list;
	}
	/**
	 * ��ѯ�ҵĿ�����Ϣ
	 */
	@Override
	public List<Duty> findmy(String empid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Duty> list = new ArrayList<Duty>();
		try {
			// 2.���������ݿ�����ӣ�url��user��password��
			conn = DBUtil.getConnection();

			// 3.����SQL�����������ǹ��
			pstmt = conn.prepareStatement("select * from duty where empid=?");
			// 4.ʹ��SQL�����������SQL��������ݿ⣬���õ����صĽ�����ӵ���
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();

			// 5.����������װ��List�У�
			while (rs.next()) {
				// 1.ȡ����ǰ�и����ֶε�ֵ
				java.sql.Date dtDate2 = rs.getDate("dtDate");
				String signinTime = rs.getString("signinTime");
				String signoutTime = rs.getString("signoutTime");
				// 2.����ǰ�и����ֶε�ֵ��װ��Employee������
				Duty duty=new Duty(dtDate2, signinTime, signoutTime);
				// 3.��user����userList
				list.add(duty);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.�ر���Դ
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.��������
		return list;
	}

	@Override
	public int findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			// 2.���������ݿ�����ӣ�url��user��password��
			conn = DBUtil.getConnection();

			// 3.����SQL�����������ǹ��
			pstmt = conn.prepareStatement("select count(*) from duty");
			// 4.ʹ��SQL�����������SQL��������ݿ⣬���õ����صĽ�����ӵ���
			rs = pstmt.executeQuery();

			// 5.����������װ��List�У�
			while (rs.next()) {
				// 1.ȡ����ǰ�и����ֶε�ֵ
				count=rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.�ر���Դ
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.��������
		return count;
	}

	@Override
	public List<Duty> find(String empid, int deptno, Date dtDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Duty> list = new ArrayList<Duty>();
		boolean flag = false;
		try {
			// 2.���������ݿ�����ӣ�url��user��password��
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder("select dt.*,e.deptid,e.ename,d.deptname "
					+ " from duty dt"
					+ " join emp e on dt.empid=e.empid" 
					+ " join dept d on e.deptid = d.deptid where 1=1");
			if (empid != null & !"".equals(empid)) {
				sql.append(" and dt.empid like '%"+empid+"%'");
			}
			if (deptno != 0) {
				sql.append(" and e.deptid =" + deptno);
			}

			if (dtDate != null) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sdtDate = sdf.format(dtDate);
				sql.append(" and to_char(dt.dtDate,'YYYY-MM-DD') >='" + sdtDate + "'");
			}
			// 3.����SQL�����������ǹ��
			pstmt = conn.prepareStatement(sql.toString());
			// 4.ʹ��SQL�����������SQL��������ݿ⣬���õ����صĽ�����ӵ���
			rs = pstmt.executeQuery();

			// 5.����������װ��List�У�
			while (rs.next()) {
				// ��ȡ�����ֶε�ֵ
				int dtID = rs.getInt("dtid");
				java.sql.Date dtDate2 = rs.getDate("dtDate");
				String signinTime = rs.getString("signinTime");
				String signoutTime = rs.getString("signoutTime");

				String empId2 = rs.getString("empid");
				String realName = rs.getString("ename");

				int deptid = rs.getInt("deptid");
				String deptname = rs.getString("deptname");
				Department dept = new Department(deptid, deptname);

				Employee emp = new Employee();
				emp.setEmpid(empId2);
				emp.setEname(realName);
				emp.setDept(dept);
				//
				Duty duty = new Duty(dtID, dtDate2, signinTime, signoutTime, emp);

				//
				list.add(duty);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.�ر���Դ
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.��������
		return list;
	}

}
