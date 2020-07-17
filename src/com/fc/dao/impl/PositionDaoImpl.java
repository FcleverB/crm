package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.PositionDao;
import com.fc.entity.Department;
import com.fc.entity.Position;
import com.fc.util.DBUtil;

public class PositionDaoImpl implements PositionDao{
	/**
	 * 添加岗位
	 */
	@Override
	public int save(Position pos) {
		String sql = "insert into pstn values(?,?,?)";
		Object[] params = {pos.getFpoid(),pos.getFponame(),pos.getFporemark()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Position> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Position> list = new ArrayList<Position>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from pstn");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int fpoid = rs.getInt("fpoid");
				String fponame = rs.getString("fponame");
				String fporemark = rs.getString("fporemark");
				// 2.将当前行各个字段的值封装到Employee对象中
				Position pos=new Position(fpoid, fponame, fporemark);
				// 3.将user放入userList
				list.add(pos);

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

	@Override
	public Position findById(int fpoid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Position pos=null;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from pstn where fpoid=?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, fpoid);
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			if (rs.next()) {
				// 1.取出当前行各个字段的值
				String fponame = rs.getString("fponame");
				String fporemark = rs.getString("fporemark");
				// 2.将当前行各个字段的值封装到Employee对象中
				pos = new Position(fpoid, fponame, fporemark);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return pos;
	}

	@Override
	public int update(Position pos) {
		String sql = "update pstn set fponame=?,fporemark=? where fpoid=?";
		Object[] params = {pos.getFponame(),pos.getFporemark(),pos.getFpoid()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int delete(int fpoid) {
		String sql = "delete from pstn where fpoid = ?";
		Object [] params = {fpoid};
		return DBUtil.executeUpdate(sql, params);
	}

}
