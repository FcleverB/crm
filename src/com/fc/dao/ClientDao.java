package com.fc.dao;

import java.util.List;

import com.fc.entity.Client;
import com.fc.entity.Type;

public interface ClientDao {
	/**
	 * 添加员工
	 * @param client
	 * @return
	 */
	int save(Client client);
	/**
	 * 多条件查询
	 * @param cname
	 * @return
	 */
	List<Client> findCli(String cname,String function,String empid,int start,int end);
	/**
	 * 查询指定客户信息
	 * @param cliid
	 * @return
	 */
	Client findById(String cliid);
	/**
	 * 更新客户信息
	 * @param client
	 * @return
	 */
	int update(Client client);
	/**
	 * 删除指定客户信息
	 * @param cliid
	 */
	void delete(String cliid);
	/**
	 * 分配客户信息
	 * @param client
	 * @return
	 */
	int allot(Client client);
	/**
	 * 查询所有客户姓名
	 * @return
	 */
	List<Client> findAll();
	/**
	 * 分页多条件查询记录总数
	 * @param cname
	 * @param function
	 * @param empid
	 * @return
	 */
	List<Client> findAll(String cname, String function, String empid);

}
