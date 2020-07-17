package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.HometypeDao;
import com.fc.entity.Hometype;
import com.fc.entity.Status;
import com.fc.util.DBUtil;

public class HometypeDaoImpl implements HometypeDao {
	/**
	 * 查询所有户型信息
	 */
	@Override
	public List<Hometype> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Hometype> list = new ArrayList<Hometype>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from htyp");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int htypid = rs.getInt("htypid");
				String name = rs.getString("name");
				String remark=rs.getString("remark");
				// 2.将当前行各个字段的值封装到Employee对象中
				Hometype hometype=new Hometype(htypid, name, remark);
				// 3.将user放入userList
				list.add(hometype);

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

}
