package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fc.dao.HomeDao;
import com.fc.entity.Client;
import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Home;
import com.fc.entity.Homestatus;
import com.fc.entity.Hometype;
import com.fc.entity.Position;
import com.fc.entity.Source;
import com.fc.entity.Status;
import com.fc.entity.Type;
import com.fc.util.DBUtil;

import oracle.net.aso.h;

public class HomeDaoImpl implements HomeDao {
	/**
	 * 添加房屋信息
	 */
	@Override
	public int save(Home home) {
		String sql = "insert into home values(seq_hom.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { home.getHtyp().getHtypid(), home.getAddress(), home.getPrice(), home.getArea(),
				home.getSprice(), home.getEnvir(), home.getSta().getHstaid(), home.getRemark(),home.getRealname(),home.getPhotoname(),home.getPhototype() };
		return DBUtil.executeUpdate(sql, params);
	}

	/**
	 * 多条件查询房屋信息
	 */
	@Override
	public List<Home> findHom(String address2, int tyid, int stas,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Home> list = new ArrayList<Home>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			StringBuilder sql = new StringBuilder(
					"select h.homid,h.address,hty.name,h.area,h.price,h.sprice,hst.name,h.envir,h.tyid,h.sta,h.photoname"
							+ " from"
							+ " (select * from "
							+ " (select rownum rn,h2.* from"
							+ " (select h1.* from home h1 order by homid desc) h2"
							+ " where rownum <="+end+")"
							+ " where rn> "+start+") h" 
							+ " join htyp hty"
							+ " on h.tyid=hty.htypid" 
							+ " join hsta hst"
							+ " on h.sta=hst.hstaid" + " where 1=1 ");
			if (address2 != null && !"".equals(address2)) {
				sql.append(" and h.address like '%" + address2 + "%'");
			}
			if (tyid != 0) {
				sql.append(" and h.tyid=" + tyid);
			}
			if (stas != 0) {
				sql.append(" and h.sta=" + stas);
			}
			pstmt = conn.prepareStatement(sql.toString());
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Home对象中
				int homid = rs.getInt("homid");
				String address = rs.getString("address");
				int area = rs.getInt("area");
				int price = rs.getInt("price");
				int sprice = rs.getInt("sprice");
				String envir = rs.getString("envir");
				String photoname=rs.getString("photoname");

				Hometype hty = new Hometype();
				hty.setName(rs.getString(3));
				Homestatus hsta = new Homestatus();
				hsta.setName(rs.getString(7));

				Home home=new Home(homid, address, price, area, sprice, envir, hsta, hty, photoname);
				// 3.将user放入userList
				list.add(home);
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
	 * 修改指定编号的房屋信息
	 */
	@Override
	public Home findById(int homid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Home home=null;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from home where homid= ?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, homid);
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			if (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Home对象中
				String address = rs.getString("address");
				int area = rs.getInt("area");
				int price = rs.getInt("price");
				int sprice = rs.getInt("sprice");
				String envir = rs.getString("envir");
				String remark=rs.getString("remark");
				String realname=rs.getString("realname");
				String photoname=rs.getString("photoname");
				String phototype=rs.getString("phototype");

				Hometype hty = new Hometype();
				hty.setName(rs.getString(3));
				Homestatus hsta = new Homestatus();
				hsta.setName(rs.getString(7));

				home = new Home(homid, address, price, area, sprice, envir, remark, hsta, hty,realname,photoname,phototype);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return home;
	}
	/**
	 * 删除指定编号房屋信息
	 */
	@Override
	public void delete(int homid) {
		String sql="delete from home where homid=?";
		Object[] params={homid};
		DBUtil.executeUpdate(sql, params);
		
	}
	/**
	 * 更新指定编号房屋信息
	 */
	@Override
	public int update(Home home) {
		String sql = "update home set tyid=?,address=?,price=?,area=?,sprice=?,envir=?,sta=?,remark=? where homid=?";
		Object[] params = {home.getHtyp().getHtypid(), home.getAddress(), home.getPrice(), home.getArea(),
				home.getSprice(), home.getEnvir(), home.getSta().getHstaid(), home.getRemark(),home.getHomid()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Home> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Home> list = new ArrayList<Home>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			String sql="select * from home";
			pstmt = conn.prepareStatement(sql);
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Employee对象中
				Home home=new Home();
				int homid=rs.getInt("homid");
				home.setHomid(homid);
				// 3.将user放入userList
				list.add(home);

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
