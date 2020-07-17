package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fc.dao.AreaDao;
import com.fc.entity.Area;
import com.fc.entity.Department;
import com.fc.util.DBUtil;

public class AreaDaoImpl implements AreaDao{
	//根据pid查询地区信息
		public List<Area> getAreaInfoDao(String pid) {
			//声明jdbc变量
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			//声明变量
			ArrayList<Area> la=null;
			try {
				//获取连接
				conn=DBUtil.getConnection();
				//创建SQL命令
				String sql="select * from area where parentid=?";
				//创建SQL命令对象
				ps=conn.prepareStatement(sql);
				//给占位符赋值
				ps.setString(1, pid);
				//执行SQL
				rs=ps.executeQuery();
				la=new ArrayList<Area>();
				//遍历结果
				while(rs.next()){
					//创建实体类对象
					Area a=new Area();
					a.setAreaid(rs.getInt("areaid"));
					a.setAreanameString(rs.getString("areaname"));
					a.setParentid(rs.getInt("parentid"));
					a.setArealevel(rs.getInt("arealevel"));
					a.setStatus(rs.getInt("status"));
					la.add(a);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//关闭资源
				DBUtil.closeAll(rs, ps, conn);
			}
			//返回
			return la;
		}

		@Override
		public String findById(int id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String areaname=null;
			try {
				// 2.建立和数据库的连接（url，user、password）
				conn = DBUtil.getConnection();

				// 3.创建SQL命令发送器（手枪）
				pstmt = conn.prepareStatement("select areaname from area where areaid=?");
				// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				// 5.处理结果（封装到List中）
				if (rs.next()) {
					// 1.取出当前行各个字段的值
					areaname=rs.getString("areaname");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 6.关闭资源
				DBUtil.closeAll(rs, pstmt, conn);
			}

			// 7.返回数据
			return areaname;
		}

		@Override
		public TreeMap<String, Integer> findAreaCounts() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			TreeMap<String, Integer> map = null;
			try {
				for(int i=0;i<5;i++){
					// 2.建立和数据库的连接（url，user、password）
					conn = DBUtil.getConnection();
					// 3.创建SQL命令发送器（手枪）
					pstmt = conn.prepareStatement("select pre,count(*) count from client  group by pre");
					// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
					rs = pstmt.executeQuery();

					// 5.处理结果（封装到List中）
					map = new TreeMap<String, Integer>();
					while (rs.next()) {
						// 1.取出当前行各个字段的值
						String pre=rs.getString(1);
						int count=rs.getInt(2);
						map.put(pre, count);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 6.关闭资源
				DBUtil.closeAll(rs, pstmt, conn);
			}

			// 7.返回数据
			return map;
		}

		
}
