package com.fc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.runner.manipulation.Sortable;

import com.fc.dao.ClientDao;
import com.fc.entity.Client;
import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Position;
import com.fc.entity.Source;
import com.fc.entity.Status;
import com.fc.entity.Type;
import com.fc.util.DBUtil;

public class ClientDaoImpl implements ClientDao{
	/**
	 * 添加客户
	 */
	@Override
	public int save(Client client) {
		String sql = "insert into client values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {client.getCliid(),null,client.getCtyp().getTypid(),client.getSta().getStaid(),client.getSour().getSourid(),
							client.getCname(),client.getSex(),new java.sql.Date(client.getBirth().getTime()),client.getComp(),client.getJob(),
							client.getPre(),client.getCity(),client.getTown(),client.getAdrs(),client.getPhone(),client.getQq(),client.getEmail(),client.getRemark()};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 多条件查询客户信息
	 */
	@Override
	public List<Client> findCli(String cname,String function,String empid2,int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Client> list = new ArrayList<Client>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			StringBuilder sql=null;
			if (function.equals("manage")) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from"
						+ "	(select * from"
						+ "	(select rownum rn,c2.* from"
						+ " (select c1.* from client c1 order by cliid desc) c2"
						+ " where rownum <="+end+")"
						+ " where rn> "+start+") c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid");
				if(cname!= null && !"".equals(cname)){
					sql.append(" where c.cname like '%"+cname+"%'");
				}
			}else if (function.equals("public")) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from"
						+ "	(select * from"
						+ "	(select rownum rn,c2.* from"
						+ " (select c1.* from client c1 where c1.empid is null order by cliid desc ) c2"
						+ " where rownum <="+end+")"
						+ " where rn> "+start+") c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid");
				if(cname!= null && !"".equals(cname)){
					sql.append(" and c.cname like '%"+cname+"%'");
				}
			}else if (function.equals("my")) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from"
						+ "	(select * from"
						+ "	(select rownum rn,c2.* from"
						+ " (select c1.* from client c1 order by cliid desc) c2"
						+ " where rownum <="+end+")"
						+ " where rn> "+start+") c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid");
				sql.append(" where c.empid = '"+empid2+"'");
				if(cname!= null && !"".equals(cname)){
					sql.append(" and c.cname like '%"+cname+"%'");
				}
			}
			
			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sql.toString());
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				String staname=rs.getString("sname");
				String sourname=rs.getString(4);
				String empid=rs.getString("empid");
				String tyname=rs.getString(6);
				String sex=rs.getString("sex");
				String phone=rs.getString("phone");
				String comp=rs.getString("comp");
				String job=rs.getString("job");
				String cname2=rs.getString("cname");
				// 2.将当前行各个字段的值封装到Client对象中
				Client client=new Client();
				Type type=new Type();
				type.setCname(tyname);
				client.setCtyp(type);
				Source source=new Source();
				source.setSname(sourname);
				client.setSour(source);
				Status status=new Status();
				status.setSname(staname);
				client.setSta(status);
				Employee emp=new Employee();
				emp.setEmpid(empid);
				client.setEmp(emp);
				client.setCname(cname2);
				client.setSex(sex);
				client.setPhone(phone);
				client.setComp(comp);
				client.setJob(job);
				client.setCliid(rs.getString("cliid"));
				// 3.将user放入userList
				list.add(client);
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
	 * 查询指定客户信息
	 */
	@Override
	public Client findById(String cliid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Client cli=null;
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from client where cliid= ?");
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setString(1, cliid);
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			if (rs.next()) {
				// 1.取出当前行各个字段的值
				String cname=rs.getString("cname");
				String sex=rs.getString("sex");
				Date birth=rs.getDate("birth");
				String comp=rs.getString("comp");
				String job=rs.getString("job");
				String adrs=rs.getString("adrs");
				String phone=rs.getString("phone");
				String qq=rs.getString("qq");
				String email=rs.getString("email");
				String remark=rs.getString("remark");
				String pre=rs.getString("pre");
				String city=rs.getString("city");
				String town=rs.getString("town");
				// 2.将当前行各个字段的值封装到Employee对象中
				Employee emp=new Employee();
				emp.setEmpid(rs.getString("empid"));
				Type type=new Type();
				type.setTypid(rs.getInt("tyid"));
				Source source=new Source();
				source.setSourid(rs.getInt("srcid"));
				Status status=new Status();
				status.setStaid(rs.getInt("staid"));
				cli=new Client(cliid, cname, sex, birth, comp, job, adrs, phone, qq, email, remark, pre, city, town, emp, type, status, source);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}

		// 7.返回数据
		return cli;
	}
	/**
	 * 更新客户信息
	 */
	@Override
	public int update(Client client) {
		String sql = "update client set empid=?,tyid=?,staid=?,srcid=?,cname=?,sex=?,birth=?,comp=?,job=?,adrs=?,phone=?,qq=?,email=?,remark=? where cliid=? ";
		Object[] params = {null,client.getCtyp().getTypid(),client.getSta().getStaid(),client.getSour().getSourid(),
				client.getCname(),client.getSex(),new java.sql.Date(client.getBirth().getTime()),client.getComp(),client.getJob(),
				client.getAdrs(),client.getPhone(),client.getQq(),client.getEmail(),client.getRemark(),client.getCliid()};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 删除指定客户信息
	 */
	@Override
	public void delete(String cliid) {
		String sql="delete from client where cliid=?";
		Object[] params={cliid};
		DBUtil.executeUpdate(sql, params);
		
	}
	/**
	 * 分配指定客户
	 */
	@Override
	public int allot(Client client) {
		String sql = "update client set empid=? where cliid=? ";
		Object[] params = {client.getEmp().getEmpid(),client.getCliid()};
		return DBUtil.executeUpdate(sql, params);
	}
	/**
	 * 查询所有客户信息
	 */
	@Override
	public List<Client> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Client> list = new ArrayList<Client>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			// 3.创建SQL命令发送器（手枪）
			String sql="select * from client";
			pstmt = conn.prepareStatement(sql);
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				// 2.将当前行各个字段的值封装到Employee对象中
				Client client=new Client();
				String cliid=rs.getString("cliid");
				String cname=rs.getString("cname");
				client.setCname(cname);
				client.setCliid(cliid);
				// 3.将user放入userList
				list.add(client);

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
	public List<Client> findAll(String cname, String function, String empid2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Client> list = new ArrayList<Client>();
		try {
			// 2.建立和数据库的连接（url，user、password）
			conn = DBUtil.getConnection();
			StringBuilder sql=null;
			if (function.equals("manage")||function==null||"".equals(function)) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from client c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid");
				if(cname!= null && !"".equals(cname)){
					sql.append(" where c.cname like '%"+cname+"%'");
				}
			}else if (function.equals("public")) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from client c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid"
						+ " where c.empid is null");
				if(cname!= null && !"".equals(cname)){
					sql.append(" and c.cname like '%"+cname+"%'");
				}
			}else if (function.equals("my")) {
				sql=new StringBuilder("select c.cliid,c.cname,st.sname,so.sname,c.empid,ty.cname,c.sex,c.phone,c.comp,c.job"
						+ " from client c"
						+ " join ctyp ty"
						+ " on ty.typid=c.tyid"
						+ " join sta st"
						+ " on c.staid=st.staid"
						+ " join sour so"
						+ " on c.srcid=so.sourid");
				sql.append(" where c.empid = '"+empid2+"'");
				if(cname!= null && !"".equals(cname)){
					sql.append(" and c.cname like '%"+cname+"%'");
				}
			}
			
			// 3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sql.toString());
			// 4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			// 5.处理结果（封装到List中）
			while (rs.next()) {
				// 1.取出当前行各个字段的值
				String staname=rs.getString("sname");
				String sourname=rs.getString(4);
				String empid=rs.getString("empid");
				String tyname=rs.getString(6);
				String sex=rs.getString("sex");
				String phone=rs.getString("phone");
				String comp=rs.getString("comp");
				String job=rs.getString("job");
				String cname2=rs.getString("cname");
				// 2.将当前行各个字段的值封装到Client对象中
				Client client=new Client();
				Type type=new Type();
				type.setCname(tyname);
				client.setCtyp(type);
				Source source=new Source();
				source.setSname(sourname);
				client.setSour(source);
				Status status=new Status();
				status.setSname(staname);
				client.setSta(status);
				Employee emp=new Employee();
				emp.setEmpid(empid);
				client.setEmp(emp);
				client.setCname(cname2);
				client.setSex(sex);
				client.setPhone(phone);
				client.setComp(comp);
				client.setJob(job);
				client.setCliid(rs.getString("cliid"));
				// 3.将user放入userList
				list.add(client);
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
