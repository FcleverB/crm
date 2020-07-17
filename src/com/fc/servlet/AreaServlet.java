package com.fc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.entity.Area;
import com.fc.service.AreaService;
import com.fc.service.impl.AreaServiceImpl;
import com.google.gson.Gson;

public class AreaServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		String pid=req.getParameter("parentid");
		//处理请求
			//获取业务层对象
			AreaService as=new AreaServiceImpl();
			List<Area> la=as.getAreaInfoService(pid);
		//响应处理结果
			//直接响应
			resp.getWriter().write(new Gson().toJson(la));
	}
}
