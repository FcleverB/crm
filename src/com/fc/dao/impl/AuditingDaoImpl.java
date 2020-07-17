package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fc.dao.AuditingDao;
import com.fc.entity.Auditing;
import com.fc.entity.Employee;
import com.fc.util.DBUtil;
import com.fc.util.DBUtil2;

public class AuditingDaoImpl implements AuditingDao{

	@Override
	public void save(Auditing auditing) {
		String sql="insert into auditing values(seq_audit.nextval,?,?,?,?,?)";
		Object params[]={auditing.getBargin().getBarid(),auditing.getEmp().getEmpid(),auditing.getAresult(),auditing.getAdvice(),new java.sql.Timestamp((auditing.getTime()).getTime())};
		//年月日时分秒
		DBUtil2.executeUpdate(sql, params);
		
	}
	/**
	 * 查询指定审核单编号
	 */
	@Override
	public int findId(int barid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int auid=0;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();

			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from auditing where barid=?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, barid);
			rs = pstmt.executeQuery();

			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int auid2=rs.getInt("auid");
				// 2.将当前行各个字段的值封装到Employee对象中
				auid=auid2;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return auid;
		
	}

}
