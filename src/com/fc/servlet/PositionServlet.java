package com.fc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.entity.Department;
import com.fc.entity.Position;
import com.fc.service.DepartmentService;
import com.fc.service.PositionService;
import com.fc.service.impl.DepartmentServiceImpl;
import com.fc.service.impl.PositionServiceImpl;

public class PositionServlet extends BaseServlet {
	/**
	 * 添加岗位
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收视图层的表单数据
		int fpoid = Integer.parseInt(request.getParameter("fpoid"));
		String fponame = request.getParameter("fponame");
		String fporemark = request.getParameter("fporemark");
		// 调用业务层完成添加操作
		PositionService deptService = new PositionServiceImpl();
		Position pos = new Position(fpoid, fponame, fporemark);
		int n = deptService.add(pos);
		// 根据结果跳转到不同页面
		if (n > 0) {
			// 如果是表单的提交,成功之后建议使用重定向,避免表单的重复提交
			// request.getRequestDispatcher("/personage/personage_deptlist.html").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/servlet/PositionServlet?method=findAll");
		} else {
			request.setAttribute("error", "添加失败");
			// 此时必须使用转发,因为要复用保存在request中的数据
			request.getRequestDispatcher("/personage/personage_positionadd.jsp").forward(request, response);

		}
	}

	/**
	 * 岗位管理 查询所有岗位信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用业务层完成添加操作
		PositionService posService = new PositionServiceImpl();
		List<Position> posList = posService.findAll();
		// 跳转到personage/personage_deptlist.jsp
		request.setAttribute("posList", posList);
		request.getRequestDispatcher("/personage/personage_positionlist.jsp").forward(request, response);
	}

	/**
	 * 岗位管理 修改指定岗位
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收要更新的部门的编号
		int fpoid = Integer.parseInt(request.getParameter("fpoid"));
		// 调用业务层完成查询操作
		PositionService posService = new PositionServiceImpl();
		Position pos = posService.findById(fpoid);
		// 跳转到
		request.setAttribute("pos", pos);
		request.getRequestDispatcher("/personage/personage_positionupdate.jsp").forward(request, response);
	}
	/**
	 * 岗位管理
	 * 		更新指定岗位信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收视图层的表单数据
		int fpoid = Integer.parseInt(request.getParameter("fpoid"));
		String fponame = request.getParameter("fponame");
		String fporemark = request.getParameter("fporemark");
		// 调用业务层完成添加操作
		PositionService posService = new PositionServiceImpl();
		Position pos = new Position(fpoid, fponame, fporemark);
		int n = posService.update(pos);
		// 根据结果跳转到不同页面
		if (n > 0) {
			// 如果是表单的提交,成功之后建议使用重定向,避免表单的重复提交
			// request.getRequestDispatcher("/personage/personage_deptlist.html").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/servlet/PositionServlet?method=findAll");
		} else {
			request.setAttribute("error", "修改失败");
			// 此时必须使用转发,因为要复用保存在request中的数据
			request.getRequestDispatcher("/personage/personage_deptupdate.jsp").forward(request, response);

		}
	}
	/**
	 * 部门管理
	 * 		删除指定部门
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收要删除的部门的编号
		int fpoid = Integer.parseInt(request.getParameter("fpoid"));
		// 调用业务层完成删除操作
		PositionService posService = new PositionServiceImpl();
		posService.delete(fpoid);
		// 跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findAll() ,先查询再显示
		request.getRequestDispatcher("/servlet/PositionServlet?method=findAll").forward(request, response);
	}
}
