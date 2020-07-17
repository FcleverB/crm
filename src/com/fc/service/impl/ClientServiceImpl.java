package com.fc.service.impl;

import java.util.List;

import javax.enterprise.inject.spi.PassivationCapable;

import com.fc.dao.ClientDao;
import com.fc.dao.impl.ClientDaoImpl;
import com.fc.entity.Client;
import com.fc.service.ClientService;
import com.fc.util.PageBean;

import oracle.net.aso.p;
import oracle.net.aso.s;

public class ClientServiceImpl implements ClientService{
	private ClientDao cliDao=new ClientDaoImpl();
	/**
	 * 添加客户
	 */
	@Override
	public int add(Client client) {
		return cliDao.save(client);
	}
	/**
	 * 多条件查询客户信息
	 */
	@Override
	public List<Client> findCli(String cname,String function,String empid,int start,int end) {
		return this.cliDao.findCli(cname,function,empid,start,end);
	}
	/**
	 * 查找指定客户信息
	 */
	@Override
	public Client findById(String cliid) {
		return this.cliDao.findById(cliid);
	}
	/**
	 * 更新客户信息
	 */
	@Override
	public int update(Client client) {
		return this.cliDao.update(client);
	}
	/**
	 * 删除指定客户信息
	 */
	@Override
	public void delete(String cliid) {
		this.cliDao.delete(cliid);
		
	}
	/**
	 * 分配客户信息
	 */
	@Override
	public int allot(Client client) {
		return this.cliDao.allot(client);
	}
	@Override
	public List<Client> findAll() {
		return this.cliDao.findAll();
	}
	

}
