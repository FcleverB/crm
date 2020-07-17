package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.PngUtils;


import com.fc.dao.PaymentDao;
import com.fc.entity.Department;
import com.fc.entity.Payment;
import com.fc.util.DBUtil;
import com.fc.util.DBUtil2;

import oracle.net.aso.i;

public class PaymentDaoImpl implements PaymentDao{
	/**
	 * 保存收入信息
	 */
	@Override
	public void save(Payment pm) {
		String sql="insert into payment values(seq_pay.nextval,?,?,?,?,?)";
		Object[] params={pm.getTotalamount(),pm.getBargin().getBarid(),pm.getAuditing().getAuid(),pm.getEmployee().getEmpid(),new java.sql.Date(pm.getPaytime().getTime())};
		DBUtil2.executeUpdate(sql, params);
		
	}
	/**
	 * 返回柱状图需要的数据
	 */
	@Override
	public List<Object[]> findStaticsData() {
		String [] payrange={"[0-5000]", "[5001-10000]", "[10001-15000]", "[15001-20000]", "[20001-25000]"};
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object []> list = new ArrayList<Object []>();
		int min=1;//0 5001 10001 15001 20001 
		int max=5000;//5000 10000 15000 20000 25000
		try {
			for(int i=0;i<5;i++){
				// 2.建立和数据库的连接（url，user、password）
				conn = DBUtil.getConnection();
				// 3.创建SQL命令发送器（手枪）
				pstmt = conn.prepareStatement("select count(*) c from payment where totalamount between ? and ?");
				// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
				pstmt.setInt(1, min);
				pstmt.setInt(2, max);
				rs = pstmt.executeQuery();

				// 5.处理结果（封装到List中）
				if (rs.next()) {
					// 1.取出当前行各个字段的值
					int count=rs.getInt(1);
					Object [] arr={payrange[i],count};
					// 3.将user放入userList
					list.add(arr);
				}
				min+=5000;
				max+=5000;
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
