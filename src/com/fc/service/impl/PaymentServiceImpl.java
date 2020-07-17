package com.fc.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import com.fc.dao.PaymentDao;
import com.fc.dao.impl.PaymentDaoImpl;
import com.fc.service.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	/**
	 * 返回收入的柱状图
	 */
	@Override
	public String getBarData() {
		//调用DAO层获取收入数据(List)
		PaymentDao payDao=new PaymentDaoImpl();
		List<Object []> list=payDao.findStaticsData();
		//将List转换成jsonStr
		StringBuilder payrange=new StringBuilder("[");
		StringBuilder count=new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			Object[] arr = list.get(i);
			if (i<list.size()-1) {
				payrange.append("\""+arr[0]+"\",");
				count.append(arr[1]+",");
			}else {
				payrange.append("\""+arr[0]+"\"");
				count.append(arr[1]);
			}
		}
		payrange.append("]");
		count.append("]");
		String jsonStr="["+payrange.toString()+","+count.toString()+"]";
		//返回jsonStr
		return  jsonStr;
	}

}
