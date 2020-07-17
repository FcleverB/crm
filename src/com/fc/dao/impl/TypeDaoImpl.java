package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.TypeDao;
import com.fc.entity.Department;
import com.fc.entity.Type;
import com.fc.util.DBUtil;

public class TypeDaoImpl implements TypeDao{
	/**
	 * 查询所有客户类型
	 */
	@Override
	public List<Type> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Type> list = new ArrayList<Type>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from ctyp");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int typid = rs.getInt("typid");
				String cname = rs.getString("cname");
				String remark=rs.getString("remark");
				// 2.将当前行各个字段的值封装到Employee对象中
				Type type=new Type(typid, cname, remark);
				// 3.将user放入userList
				list.add(type);

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
