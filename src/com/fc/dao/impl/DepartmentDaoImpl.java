package com.fc.dao.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.DepartmentDao;
import com.fc.entity.Department;
import com.fc.util.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	/**
	 * 添加部门
	 */
	@Override
	public int save(Department dept) {
		String sql = "insert into dept values(?,?,?,?)";
		Object[] params = { dept.getDeptid(), dept.getDeptname(), dept.getDeptloc(), dept.getRemark() };
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 部门管理 
	 * 		查询所有部门信息
	 */
	@Override
	public List<Department> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from dept");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int deptid = rs.getInt("deptid");
				String deptname = rs.getString("deptname");
				String deptloc = rs.getString("deptloc");
				String remark=rs.getString("remark");
				// 2.将当前行各个字段的值封装到Employee对象中
				Department dept = new Department(deptid, deptname, deptloc, remark);
				// 3.将user放入userList
				list.add(dept);

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
	 * 部门管理
	 * 		删除指定部门
	 */
	@Override
	public int delete(int deptid) {
		String sql = "delete from dept where deptid = ?";
		Object [] params = {deptid};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 部门管理
	 * 		修改指定部门信息
	 */
	@Override
	public Department findById(int deptid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept=null;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from dept where deptid=?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, deptid);
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			if (rs.next()) {
				// 1.取出当前行各个字段的值
				String deptname = rs.getString("deptname");
				String deptloc = rs.getString("deptloc");
				String remark=rs.getString("remark");
				// 2.将当前行各个字段的值封装到Employee对象中
				dept = new Department(deptid, deptname, deptloc, remark);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return dept;
	}
	/**
	 * 部门管理
	 * 		更新指定部门信息
	 */
	@Override
	public int update(Department dept) {
		String sql = "update dept set deptname=?,deptloc=?,remark=? where deptid=?";
		Object[] params = {dept.getDeptname(), dept.getDeptloc(), dept.getRemark(), dept.getDeptid()};
		return DBUtil.executeUpdate(sql, params);
	}

}
