package com.fc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.dao.EmployeeDao;
import com.fc.dao.impl.EmployeeDaoImpl;
import com.fc.entity.Bargin;
import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Position;
import com.fc.service.DepartmentService;
import com.fc.service.EmployeeService;
import com.fc.service.PositionService;
import com.fc.service.impl.DepartmentServiceImpl;
import com.fc.service.impl.EmployeeServiceImpl;
import com.fc.service.impl.PositionServiceImpl;
import com.fc.util.PageBean;

import oracle.net.aso.p;

public class EmployeeServlet extends BaseServlet {
	private EmployeeDao empDao=new EmployeeDaoImpl();
	/**
	 * 添加员工
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int n=empService.add(emp);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/EmployeeServlet?method=findEmp");
		}else {
			request.setAttribute("error", "添加失败");
			request.getRequestDispatcher("/personage/personage_empadd.jsp").forward(request, response);
		}
		
	}
	/**
	 * 动态显示部门,岗位,上级
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		//跳转到/personage/personage_empadd.jsp
		request.getRequestDispatcher("/personage/personage_empadd.jsp").forward(request, response);
	}
	/**
	 * 员工管理
	 * 		查询所有员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层获取所有的员工信息
		EmployeeService  empService = new EmployeeServiceImpl();
		List<Employee> empList = empService.findAll();
		//获取所有的部门信息
		DepartmentService deptService=new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		
		//跳转到personage/personage_emplist.jsp
		request.setAttribute("empList", empList);
		request.getRequestDispatcher("/personage/personage_emplist.jsp").forward(request, response);
	}
	/**
	 * 员工管理
	 * 		多条件查询员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收从页面传入的页码index
		String sindex=request.getParameter("index");
		int index=1;
		try {
			index=Integer.parseInt(sindex);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String ssize=request.getParameter("size");
		int size=5;
		try {
			size=Integer.parseInt(ssize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		PageBean<Employee> pageBean=new PageBean<Employee>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		//接收查询条件
		String empid=request.getParameter("empid");
		
		int deptid=0;
		String sdeptid=request.getParameter("deptid");
		try {
			deptid=Integer.parseInt(sdeptid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		int onduty=1;
		String sonduty=request.getParameter("onduty");
		try {
			onduty=Integer.parseInt(sonduty);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//调用业务层获取所有的员工信息
		EmployeeService  empService = new EmployeeServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.empDao.findAll().size();
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		//条件较少没必要封装
		List<Employee> empList=empService.findEmp(empid,deptid,onduty,start,end);
		pageBean.setList(empList);
		//获取所有的部门信息
		DepartmentService deptService=new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		
		//跳转到personage/personage_emplist.jsp
		request.setAttribute("empid", empid);
		request.setAttribute("deptid", deptid);
		request.setAttribute("onduty", onduty);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/personage/personage_emplist.jsp").forward(request, response);
	}
	/**
	 * 员工管理
	 * 		删除指定员工
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收要删除的员工的编号
		String empid = request.getParameter("empid2");
		// 调用业务层完成删除操作
		EmployeeService empService = new EmployeeServiceImpl();
		empService.delete(empid);
		// 跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findAll() ,先查询再显示
		request.getRequestDispatcher("/servlet/EmployeeServlet?method=findEmp").forward(request, response);
	}
	/**
	 * 员工管理
	 * 		修改员工信息-预更新操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收要修改的员工的编号
		String empid = request.getParameter("empid");
		//调用业务层获取该员工的信息
		EmployeeService empService = new EmployeeServiceImpl();
		Employee emp = empService.findById(empid);
		request.setAttribute("emp", emp);
		
		//获取所有的部门信息
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		//获取所有的岗位信息
		PositionService posService = new PositionServiceImpl();
		List<Position> posList = posService.findAll();
		request.setAttribute("posList", posList);
		
		//获取上级员工		
		List<Employee> mgrList = empService.findEmpByType(2);//1  基层员工  2 各级管理人员
		request.setAttribute("mgrList",mgrList);
		
		
		//页面跳转 system/empUpdate.jsp
		request.getRequestDispatcher("/personage/personage_empupdate.jsp").forward(request, response);
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
			request.getRequestDispatcher("/personage/personage_emplist.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "更新失败");
			request.getRequestDispatcher("/personage/personage_empupdate.jsp").forward(request, response);
		}
		
		
	}
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取登录信息
		String empid=request.getParameter("empid");
		String pwd=request.getParameter("pwd");
		String verifyCode =request.getParameter("verifyCode");//用户输入的验证码
		String randStr=(String) request.getSession().getAttribute("randStr");
		
		if (verifyCode == null || !verifyCode.equals(randStr)) {
			request.setAttribute("error", "验证码输入错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//调用业务层完成登录操作
		EmployeeService empService=new EmployeeServiceImpl();
		Employee emp=empService.login(empid,pwd);
		//页面跳转
		if (emp!=null) {
			//将员工信息保存在session中
			request.getSession().setAttribute("emp", emp);
			response.sendRedirect(request.getContextPath()+"/frame/main.html");
		}else {
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}
	/**
	 * 退出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//结束当前的session
		request.getSession().invalidate();
		//跳转到登录页面,注销之后建议使用重定向
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
}
