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

import com.fc.dao.BarginDao;
import com.fc.dao.impl.BarginDaoImpl;
import com.fc.entity.Auditing;
import com.fc.entity.Bargin;
import com.fc.entity.Client;
import com.fc.entity.Employee;
import com.fc.entity.Home;
import com.fc.service.AuditingService;
import com.fc.service.BarginService;
import com.fc.service.ClientService;
import com.fc.service.HomeService;
import com.fc.service.impl.AuditingServiceImpl;
import com.fc.service.impl.BarginServiceImpl;
import com.fc.service.impl.ClientServiceImpl;
import com.fc.service.impl.HomeServiceImpl;
import com.fc.util.PageBean;

public class BarginServlet extends BaseServlet {
	private BarginDao barDao=new BarginDaoImpl();
	/**
	 * 动态获取 客户姓名,房屋编号
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所有的客户姓名
		ClientService cliService=new ClientServiceImpl();
		List<Client> cliList = cliService.findAll();
		request.setAttribute("cliList", cliList);
		//获取所有的房屋编号
		HomeService homService=new HomeServiceImpl();
		List<Home> homList = homService.findAll();
		request.setAttribute("homList", homList);
		//跳转到/personage/personage_empadd.jsp
		request.getRequestDispatcher("/deal/deal_add.jsp").forward(request, response);
	}
	/**
	 * 添加成功项
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户信息
		String nextemp=request.getParameter("nextemp");
		System.out.println(nextemp);
		String remark=request.getParameter("remark");
		int price=Integer.parseInt(request.getParameter("price"));
		
		String sbardate=request.getParameter("bardate");
		Date bardate=null;
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			bardate=sdf.parse(sbardate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String empid=request.getParameter("empid");
		Employee emp=new Employee();
		emp.setEmpid(empid);
		String cname=request.getParameter("cliname");
		Client client=new Client();
		client.setCname(cname);
		int homeid=Integer.parseInt(request.getParameter("homeid"));
		Home home=new Home();
		home.setHomid(homeid);
		
		//调用业务层完成添加操作
		Bargin bargin=new Bargin(price, bardate, nextemp, remark, emp, client, home);
		BarginService barService=new BarginServiceImpl();
		int n=barService.add(bargin);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/BarginServlet?method=findmy");
			//response.sendRedirect(request.getContextPath()+"/client/client_manage.jsp");
		}else {
			request.setAttribute("error", "添加失败");
			request.getRequestDispatcher("/deal/deal_add.jsp").forward(request, response);
		}
	}
	
	/**
	 * 待审成交
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toaudit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		PageBean<Bargin> pageBean=new PageBean<Bargin>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		int barid = 0;
		String sbarid = request.getParameter("barid"); //null
		try{
			barid = Integer.parseInt(sbarid);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		//从session中获取当前用户编号
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String auditorId=emp.getEmpid();
		//调用业务层,查询到当前用户需要审核的报销单列表
		BarginService barService=new BarginServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.barDao.findsize();
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		List<Bargin> barList=barService.getToAudit(auditorId,barid,start,end);//参数是当前用户的编号
		pageBean.setList(barList);
		//跳转到指定页面expense/myAudit.jsp
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/deal/deal_look.jsp").forward(request, response);
		
	}
	/**
	 * 审核交易单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void audit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取审核表单的值
		//expId:expId,result:result,auditDesc:auditDesc
		
		int barid=Integer.parseInt(request.getParameter("barid"));//成交项
		
		Bargin bargin=new Bargin();
		bargin.setBarid(barid);
		
		String result=request.getParameter("result");
		String auditDesc=request.getParameter("auditDesc");
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		Date auditTime=new Date();
		//调用业务层完成审核操作
		AuditingService audService=new AuditingServiceImpl();
		int  auid=audService.findId(barid);
		Auditing audit=new Auditing(auid, result, auditDesc, auditTime, bargin, emp);
		BarginService barService=new BarginServiceImpl();
		try {
			barService.audit(audit);
			//成功
			response.getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
			//失败
			response.getWriter().print("error");
			
		}
	}
	/**
	 * 查看我的交易单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findmy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		PageBean<Bargin> pageBean=new PageBean<Bargin>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		//接收当前登录人编号,为查看我的客户做铺垫
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		String  ename=emp.getEname();
		//接收查询条件
		int barid = 0;
		String sbarid = request.getParameter("barid"); //null
		try{
			barid = Integer.parseInt(sbarid);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		//调用业务层获取所有的员工信息
		BarginService barService = new BarginServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.barDao.findsize(empid);
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		//条件较少没必要封装
		List<Bargin> barList=barService.findMy(barid,empid,ename,start,end);
		pageBean.setList(barList);
		//跳转到personage/personage_emplist.jsp
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/deal/deal_my.jsp").forward(request, response);
		
	}
}
