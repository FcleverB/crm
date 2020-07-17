package com.fc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.service.PaymentService;
import com.fc.service.impl.PaymentServiceImpl;

public class PaymentServlet extends BaseServlet {
	//['[0-5000]', '[5001-10000]', '[10001-15000]', '[15001-20000]', '[20001-25000]' ]
	//[ 2, 4, 6, 4, 2]
	public void getBarData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用业务层获取jsonStr
		//String jsonStr="[['[0-5000]', '[5001-10000]', '[10001-15000]', '[15001-20000]', '[20001-25000]' ],[ 2, 4, 6, 4, 2]]";
		PaymentService payService=new PaymentServiceImpl();
		String jsonStr=payService.getBarData();
		//返回jsonStr
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(jsonStr);
	}
}
