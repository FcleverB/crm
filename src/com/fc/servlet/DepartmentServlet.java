package com.fc.servlet;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.entity.Department;
import com.fc.service.DepartmentService;
import com.fc.service.impl.DepartmentServiceImpl;

public class DepartmentServlet extends BaseServlet {
	/**
	 * 添加部门
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收视图层的表单数据
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String deptname = request.getParameter("deptname");
		String deptloc = request.getParameter("deptloc");
		String remark = request.getParameter("remark");
		// 调用业务层完成添加操作
		DepartmentService deptService = new DepartmentServiceImpl();
		Department dept = new Department(deptid, deptname, deptloc, remark);
		int n = deptService.add(dept);
		// 根据结果跳转到不同页面
		if (n > 0) {
			// 如果是表单的提交,成功之后建议使用重定向,避免表单的重复提交
			// request.getRequestDispatcher("/personage/personage_deptlist.html").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/servlet/DepartmentServlet?method=findAll");
		} else {
			request.setAttribute("error", "添加失败");
			// 此时必须使用转发,因为要复用保存在request中的数据
			request.getRequestDispatcher("/personage/personage_deptadd.jsp").forward(request, response);

		}
	}

	/**
	 * 部门管理 查询所有部门信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用业务层完成添加操作
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		// 跳转到personage/personage_deptlist.jsp
		request.setAttribute("deptList", deptList);
		request.getRequestDispatcher("/personage/personage_deptlist.jsp").forward(request, response);
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
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		// 调用业务层完成删除操作
		DepartmentService deptService = new DepartmentServiceImpl();
		deptService.delete(deptid);
		// 跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findAll() ,先查询再显示
		request.getRequestDispatcher("/servlet/DepartmentServlet?method=findAll").forward(request, response);
	}
	/**
	 * 部门管理
	 * 		修改指定部门
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收要更新的部门的编号
		int deptid = Integer.parseInt(request.getParameter("deptid"));		
		//调用业务层完成查询操作
		DepartmentService  deptService = new DepartmentServiceImpl();
		Department dept = deptService.findById(deptid);
		//跳转到
		request.setAttribute("dept", dept);
		request.getRequestDispatcher("/personage/personage_deptupdate.jsp").forward(request, response);
	}
	/**
	 * 部门管理
	 * 		更新指定部门信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收视图层的表单数据
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String deptname = request.getParameter("deptname");
		String deptloc = request.getParameter("deptloc");
		String remark = request.getParameter("remark");
		// 调用业务层完成添加操作
		DepartmentService deptService = new DepartmentServiceImpl();
		Department dept = new Department(deptid, deptname, deptloc, remark);
		int n = deptService.update(dept);
		// 根据结果跳转到不同页面
		if (n > 0) {
			// 如果是表单的提交,成功之后建议使用重定向,避免表单的重复提交
			// request.getRequestDispatcher("/personage/personage_deptlist.html").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/servlet/DepartmentServlet?method=findAll");
		} else {
			request.setAttribute("error", "修改失败");
			// 此时必须使用转发,因为要复用保存在request中的数据
			request.getRequestDispatcher("/personage/personage_deptupdate.jsp").forward(request, response);

		}
	}
}
