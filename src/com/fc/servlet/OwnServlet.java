package com.fc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Position;
import com.fc.service.DepartmentService;
import com.fc.service.EmployeeService;
import com.fc.service.PositionService;
import com.fc.service.impl.DepartmentServiceImpl;
import com.fc.service.impl.EmployeeServiceImpl;
import com.fc.service.impl.PositionServiceImpl;

public class OwnServlet extends BaseServlet {
	/**
	 * 员工管理
	 * 		查询个人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//接收当前登录人编号,为查看我的客户做铺垫
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//接收功能标志
		String function=request.getParameter("function");
		//获取所有的部门信息
		DepartmentService deptService=new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		//获取所有的岗位信息
		PositionService posService=new PositionServiceImpl();
		List<Position> posList = posService.findAll();
		request.setAttribute("posList", posList);
		//获取上级员工
		EmployeeService empService=new EmployeeServiceImpl();
		List<Employee> mgrList=empService.findEmpByType(2);//1基层员工  2各级管理人员
		request.setAttribute("mgrList", mgrList);
		
		Employee employee=empService.findById(empid);
		if (function.equals("look")) {
			//跳转到personage/personage_emplist.jsp
			request.setAttribute("emp", employee);
			request.getRequestDispatcher("/personnel/personnel_my.jsp").forward(request, response);
		}else {
			//跳转到personage/personage_emplist.jsp
			request.setAttribute("emp", employee);
			request.getRequestDispatcher("/personnel/personnel_message.jsp").forward(request, response);
		}
	}
	
	/**
	 * 员工管理
	 * 		修改员工信息-更新操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取员工信息
		String empid=request.getParameter("empid");
		String pwd="12345";
		String ename=request.getParameter("ename");
		String sex=request.getParameter("sex");
		String sbirth=request.getParameter("birth");
		String sindate=request.getParameter("indate");
		String sleave=request.getParameter("leave");
		Date birth=null,indate=null,leave=null;
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			birth=sdf.parse(sbirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			indate=sdf.parse(sindate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leave=sdf.parse(sleave);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int onduty=Integer.parseInt(request.getParameter("onduty"));
		int etype= Integer.parseInt(request.getParameter("etype"));
		String phone=request.getParameter("phone");
		String qq=request.getParameter("qq");
		String idcard=request.getParameter("idcard");
		String linkman=request.getParameter("linkman");
		int deptid =Integer.parseInt(request.getParameter("deptid"));
		Department dept=new Department();
		dept.setDeptid(deptid);
		int psosid=Integer.parseInt(request.getParameter("posid"));
		Position position=new Position();
		position.setFpoid(psosid);
		String upid=request.getParameter("upid");
		Employee mgr=new Employee();
		mgr.setEmpid(upid);
		
		//调用业务层完成添加操作
		Employee emp=new Employee(empid,pwd, ename, sex, birth, indate, leave, onduty, etype, phone, qq, linkman, idcard, dept, position, mgr);
		EmployeeService empService=new EmployeeServiceImpl();
		int n=empService.update(emp);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/OwnServlet?method=look&function=look");
		}else {
			request.setAttribute("error", "更新失败");
			response.sendRedirect(request.getContextPath()+"/servlet/OwnServlet?method=look&function=message");
		}
	}
	/**
	 * 员工管理
	 * 		修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uppwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("woshi");
		//接收当前登录人编号,为查看我的客户做铺垫
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		
		String pwd=request.getParameter("pwd2");
		
		EmployeeService empService=new EmployeeServiceImpl();
		int n=empService.updatepwd(empid,pwd);
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/OwnServlet?method=look&function=look");
		}else {
			request.setAttribute("error", "修改失败");
			response.sendRedirect(request.getContextPath()+"/servlet/OwnServlet?method=uppwd");
		}
		
		
		
		
	}
}
