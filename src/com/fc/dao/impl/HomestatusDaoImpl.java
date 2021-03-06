package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.HomeDao;
import com.fc.dao.HomestatusDao;
import com.fc.entity.Homestatus;
import com.fc.entity.Hometype;
import com.fc.util.DBUtil;

public class HomestatusDaoImpl implements HomestatusDao{
	/**
	 * 查询房屋状态信息
	 */
	@Override
	public List<Homestatus> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Homestatus> list = new ArrayList<Homestatus>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from hsta");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int hstaid = rs.getInt("hstaid");
				String name = rs.getString("name");
				String remark=rs.getString("remark");
				// 2.将当前行各个字段的值封装到Employee对象中
				Homestatus homestatus=new Homestatus(hstaid, name, remark);
				// 3.将user放入userList
				list.add(homestatus);
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
