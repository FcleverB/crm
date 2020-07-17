package com.fc.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fc.dao.EmployeeDao;
import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Position;
import com.fc.util.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	/**
	 * 添加员工
	 */
	@Override
	public int save(Employee emp) {
		String sql = "insert into emp values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		java.sql.Date leaveDate2 = null;
		Date leaveDate = emp.getLeave();
		if(leaveDate != null){
			leaveDate2 = new java.sql.Date(leaveDate.getTime());
		}
		//new java.sql.Date(emp.getLeave().getTime())
		Object[] params = {emp.getEmpid(), emp.getDept().getDeptid(), emp.getPosition().getFpoid(), emp.getMgr().getEmpid(),
				emp.getEname(), emp.getPwd(), emp.getSex(), new java.sql.Date(emp.getBirth().getTime()),
				new java.sql.Date(emp.getIndate().getTime()), leaveDate2,
				emp.getOnduty(), emp.getEtype(), emp.getPhone(), emp.getQq(), emp.getIdcard(), emp.getLinkman() };
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 查找指定类型员工
	 */
	@Override
	public List<Employee> findByType(int etype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from emp where etype=?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, etype);
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				String empid=rs.getString("empid");
				String ename=rs.getString("ename");
				String sex=rs.getString("sex");
				String phone=rs.getString("phone");
				// 2.将当前行各个字段的值封装到Employee对象中
				Employee emp=new Employee(empid,ename,sex,phone);
				// 3.将user放入userList
				list.add(emp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return list;
	}
	/**
	 * 查询所有员工信息
	 */
	@Override
	public List<Employee> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			String sql="select e.empid,e.ename,e.sex,d.deptname,p.fponame,mgr.empid,mgr.ename,e.indate,e.phone"
					+ " from emp e"
					+ " join dept d"
					+ " on e.deptid=d.deptid"
					+ " join pstn p"
					+ " on e.posid=p.fpoid"
					+ " join emp mgr"
					+ " on e.upid=mgr.empid";
			pstmt = conn.prepareStatement(sql);
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Employee对象中
				Employee emp=new Employee();
				String empid=rs.getString(1);
				String ename=rs.getString(2);
				String sex=rs.getString("sex");
				Date indate=rs.getDate("indate");
				String phone=rs.getString("phone");
				emp.setEmpid(empid);
				emp.setEname(ename);
				emp.setSex(sex);
				emp.setIndate(indate);
				emp.setPhone(phone);
				
				String deptname=rs.getString("deptname");
				Department dept=new Department();
				dept.setDeptname(deptname);
				emp.setDept(dept);
				
				String fponame=rs.getString("fponame");
				Position pos=new Position();
				pos.setFponame(fponame);
				emp.setPosition(pos);
				
				String mgrid=rs.getString(6);
				String mgrname=rs.getString(7);
				Employee mgr=new Employee();
				mgr.setEmpid(mgrid);
				mgr.setEname(mgrname);
				emp.setMgr(mgr);
				// 3.将user放入userList
				list.add(emp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return list;
	}
	/**
	 * 员工管理
	 * 		多条件查询员工
	 */
	@Override
	public List<Employee> find(String empid2, int deptid2, int onduty,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			StringBuilder sql=new StringBuilder("select e.empid,e.ename,e.sex,d.deptname,p.fponame,mgr.empid,mgr.ename,e.indate,e.phone"
					+ " from"
					+ " (select * from "
					+ " (select rownum rn,e2.* from"
					+ " (select e1.* from emp e1 order by empid desc) e2"
					+ " where rownum <="+end+")"
					+ " where rn>"+start+") e"
					+ " join dept d"
					+ " on e.deptid=d.deptid"
					+ " join pstn p"
					+ " on e.posid=p.fpoid"
					+ " join emp mgr"
					+ " on e.upid=mgr.empid where 1=1 ");
			if(empid2!= null && !"".equals(empid2)){
				sql.append(" and e.empid like '%"+empid2+"%'");
			}
			if(deptid2 != 0){
				sql.append(" and e.deptid="+deptid2);
			}
			sql.append(" and e.onduty = "+onduty);
			pstmt = conn.prepareStatement(sql.toString());
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Employee对象中
				Employee emp=new Employee();
				String empid=rs.getString(1);
				String ename=rs.getString(2);
				String sex=rs.getString("sex");
				Date indate=rs.getDate("indate");
				String phone=rs.getString("phone");
				emp.setEmpid(empid);
				emp.setEname(ename);
				emp.setSex(sex);
				emp.setIndate(indate);
				emp.setPhone(phone);
				
				String deptname=rs.getString("deptname");
				Department dept=new Department();
				dept.setDeptname(deptname);
				emp.setDept(dept);
				
				String fponame=rs.getString("fponame");
				Position pos=new Position();
				pos.setFponame(fponame);
				emp.setPosition(pos);
				
				String mgrid=rs.getString(6);
				String mgrname=rs.getString(7);
				Employee mgr=new Employee();
				mgr.setEmpid(mgrid);
				mgr.setEname(mgrname);
				emp.setMgr(mgr);
				// 3.将user放入userList
				list.add(emp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return list;
	}
	/**
	 * 员工管理
	 * 		删除指定员工
	 */
	@Override
	public void delete(String empid) {
		String sql="delete from emp where empid=?";
		Object[] params={empid};
		DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 查询指定编号员工
	 */
	@Override
	public Employee findById(String empid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee emp=null;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			if (empid.equals("tangseng")) {
				pstmt = conn.prepareStatement("select e.* from emp e"
						+ " where e.empid=?");
			}else {
				pstmt = conn.prepareStatement("select e.*,e1.ename upename from emp e"
						+ " join emp e1"
						+ " on e.upid=e1.empid"
						+ " where e.empid=?");
			}
			
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			if (rs.next()) {
				// 1.取出当前行各个字段的值
				String ename=rs.getString("ename");
				String pwd=rs.getString("pwd");
				String sex=rs.getString("sex");
				Date birth=rs.getDate("birth");
				Date indate=rs.getDate("indate");
				Date leave=rs.getDate("leave");
				int onduty=rs.getInt("onduty");
				int etype=rs.getInt("etype");
				String phone=rs.getString("phone");
				String qq=rs.getString("qq");
				String idcard=rs.getString("idcard");
				String linkman=rs.getString("linkman");
				String upename=null;
				if (!empid.equals("tangseng")) {
					upename=rs.getString("upename");
				}
				// 2.将当前行各个字段的值封装到Employee对象中
				
				int deptid=rs.getInt("deptid");
				Department dept=new Department();
				dept.setDeptid(deptid);
				
				int fpoid=rs.getInt("posid");
				Position pos=new Position();
				pos.setFpoid(fpoid);
				
				String mgrid=rs.getString("upid");
				Employee mgr=new Employee();
				mgr.setEmpid(mgrid);
				mgr.setEname(upename);
				
				emp=new Employee(empid, pwd, ename, sex, birth, indate, leave, onduty, etype, phone, qq, linkman, idcard, dept, pos, mgr);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return emp;
	}

	@Override
	public int update(Employee emp) {
		String sql = "update emp set deptid=?,posid=?,upid=?,ename=?,pwd=?,sex=?,birth=?,indate=?,leave=?,onduty=?,etype=?,phone=?,qq=?,idcard=?,linkman=? where empid=? ";
		java.sql.Date leaveDate2 = null;
		Date leaveDate = emp.getLeave();
		if(leaveDate != null){
			leaveDate2 = new java.sql.Date(leaveDate.getTime());
		}
		Object[] params = {emp.getDept().getDeptid(), emp.getPosition().getFpoid(), emp.getMgr().getEmpid(),
				emp.getEname(), emp.getPwd(), emp.getSex(), new java.sql.Date(emp.getBirth().getTime()),
				new java.sql.Date(emp.getIndate().getTime()), leaveDate2,
				emp.getOnduty(), emp.getEtype(), emp.getPhone(), emp.getQq(), emp.getIdcard(), emp.getLinkman(),emp.getEmpid() };
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 修改用户密码
	 */
	@Override
	public int updatepwd(String empid, String pwd) {
		String sql="update emp set pwd=? where empid=?";
		Object [] params={pwd,empid};
		return DBUtil.executeUpdate(sql, params);
	}
	

}
