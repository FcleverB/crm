package com.fc.service;

import java.util.List;

import com.fc.entity.Client;
import com.fc.util.PageBean;

public interface ClientService {
	/**
	 * 添加客户
	 * @param client
	 * @return
	 */
	int add(Client client);
	/**
	 * 多条件查询客户信息
	 * @param cname
	 * @return
	 */
	List<Client> findCli(String cname,String function,String empid,int start,int end);
	/**
	 * 查找指定客户信息
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

}
