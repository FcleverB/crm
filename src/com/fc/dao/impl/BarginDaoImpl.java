package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;


import com.fc.dao.BarginDao;
import com.fc.entity.Bargin;
import com.fc.entity.Client;
import com.fc.entity.Employee;
import com.fc.entity.Home;
import com.fc.entity.Source;
import com.fc.entity.Status;
import com.fc.entity.Type;
import com.fc.util.DBUtil;
import com.fc.util.DBUtil2;

import oracle.net.aso.b;

public class BarginDaoImpl implements BarginDao{
	/**
	 * 添加成交项
	 */
	@Override
	public int save(Bargin bargin) {
		String sql = "insert into bar values(seq_bar.nextval,?,?,?,?,?,?,?,?)";
		Object[] params = {bargin.getEmp().getEmpid(),bargin.getClient().getCname(),bargin.getHome().getHomid(),bargin.getPrice(),new java.sql.Date((bargin.getBardate()).getTime()),bargin.getNextemp(),bargin.getRemark(),"待审核"};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 多条件查询成交项
	 */
	@Override
	public List<Bargin> findByAuditorId(String auditorId, int barid2,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bargin> list = new ArrayList<Bargin>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			StringBuilder sql=new StringBuilder("select b.barid,e.ename,b.cliname,h.homid,b.price,b.bardate,b.remark,b.status"
					+ " from"
					+ " (select * from "
					+ " (select rownum rn,b2.* from"
					+ " (select b1.* from bar b1 where nextemp=? order by barid desc) b2"
					+ " where rownum <="+end+")"
					+ " where rn>"+start+") b"
					+ " join emp e"
					+ " on b.empid=e.empid"
					+ " join home h"
					+ " on b.homeid=h.homid");
			if (barid2!=0) {
				sql.append(" and b.barid="+barid2);
			}
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, auditorId);
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				int barid=rs.getInt("barid");
				String ename = rs.getString("ename");//??
				String cliname = rs.getString("cliname");
				int homid=rs.getInt("homid");
				int price=rs.getInt("price");
				Date bardate=rs.getDate("bardate");
				String remark=rs.getString("remark");
				String status=rs.getString("status");
				
				//2.将当前行各个字段的值封装到Employee对象中
				Bargin bargin=new Bargin();
				bargin.setBarid(barid);
				bargin.setPrice(price);
				bargin.setBardate(bardate);
				bargin.setRemark(remark);
				bargin.setStatus(status);
				
				Employee emp=new Employee();
				emp.setEname(ename);
				bargin.setEmp(emp);
				
				
				Client client=new Client();
				client.setCname(cliname);
				bargin.setClient(client);
				
				Home home=new Home();
				home.setHomid(homid);
				bargin.setHome(home);
				
				//3.将user放入userList
				list.add(bargin);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return list;
	}
	@Override
	public void update(Bargin bargin) {
		String sql="update bar set nextemp=?,status=? where barid=?";
		Object params [] ={bargin.getNextemp(),bargin.getStatus(),bargin.getBarid()};
		DBUtil2.executeUpdate(sql, params);
		
	}
	@Override
	public Bargin findById(int barid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bargin bar=null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from bar where barid=?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）			
			pstmt.setInt(1, barid);
			rs = pstmt.executeQuery();
			//5.处理结果（封装到List中）
			if(rs.next()){
				//1.取出当前行各个字段的值
				//int expId = rs.getInt("expId");
				int price=rs.getInt("price");
				Date bardate=rs.getDate("bardate");
				String nextemp=rs.getString("nextemp");
				String remark=rs.getString("remark");
				String status=rs.getString("status");
				
				Employee emp=new Employee();
				emp.setEmpid(rs.getString("empid"));
				
				Client cli=new Client();
				cli.setCname(rs.getString("cliname"));
				
				Home home=new Home();
				home.setHomid(rs.getInt("homeid"));
				
				//2.将当前行各个字段的值封装到Employee对象中
				bar=new Bargin(price, bardate, nextemp, remark, emp, cli, home);
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return bar;
	}
	/**
	 * 查看我的交易单
	 */
	@Override
	public List<Bargin> findMy(int barid2, String empid,String ename,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bargin> list = new ArrayList<Bargin>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			StringBuilder sql=new StringBuilder("select * from"
					+ " (select * from "
					+ " (select rownum rn,b2.* from"
					+ " (select b1.* from bar b1 order by barid desc) b2"
					+ " where rownum <="+end+")"
					+ " where rn> "+start+")"
					+ " where empid=?");
			if (barid2!=0) {
				sql.append(" and barid="+barid2);
			}
			
			// 3.创建SQL命令发器（手枪）
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, empid);
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				int barid=rs.getInt("barid");
				int price=rs.getInt("price");
				Date bardate=rs.getDate("bardate");
				String nextemp=rs.getString("nextemp");
				String remark=rs.getString("remark");
				String status=rs.getString("status");
				
				Employee emp=new Employee();
				emp.setEmpid(rs.getString("empid"));
				emp.setEname(ename);
				
				Client cli=new Client();
				cli.setCname(rs.getString("cliname"));
				
				Home home=new Home();
				home.setHomid(rs.getInt("homeid"));
				
				//2.将当前行各个字段的值封装到Employee对象中
				Bargin bar=new Bargin(barid, price, bardate, nextemp, remark, status, emp, cli, home);
				// 3.将user放入userList
				list.add(bar);
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
	public int findsize() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select count(*) from bar");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）			
			rs = pstmt.executeQuery();
			//5.处理结果（封装到List中）
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return count;
		
	}
	@Override
	public int findsize(String empid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select count(*) from bar where empid=?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）		
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			//5.处理结果（封装到List中）
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return count;
	}
}
