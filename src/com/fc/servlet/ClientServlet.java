package com.fc.servlet;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.dao.ClientDao;
import com.fc.dao.impl.ClientDaoImpl;
import com.fc.entity.Client;
import com.fc.entity.Employee;
import com.fc.entity.Source;
import com.fc.entity.Status;
import com.fc.entity.Type;
import com.fc.service.AreaService;
import com.fc.service.ClientService;
import com.fc.service.SourceService;
import com.fc.service.StatusService;
import com.fc.service.TypeService;
import com.fc.service.impl.AreaServiceImpl;
import com.fc.service.impl.ClientServiceImpl;
import com.fc.service.impl.SourceServiceImpl;
import com.fc.service.impl.StatusServiceImpl;
import com.fc.service.impl.TypeServiceImpl;
import com.fc.util.PageBean;
import com.google.gson.Gson;

public class ClientServlet extends BaseServlet {
	private ClientDao cliDao=new ClientDaoImpl();
	/**
	 * 添加客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取客户信息
		String cliid=request.getParameter("cliid");
		String cname=request.getParameter("cname");
		String sex=request.getParameter("sex");
		String comp=request.getParameter("comp");
		String job=request.getParameter("job");
		String email=request.getParameter("email");
		String remark=request.getParameter("remark");
		/**
		 * 需要进行字符串拼接为地址
		 */
		
		int pre1=Integer.parseInt(request.getParameter("pre"));
		int city1=Integer.parseInt(request.getParameter("city"));
		int town1=Integer.parseInt(request.getParameter("town"));
		
		AreaService areService=new AreaServiceImpl();
		String pre=areService.findById(pre1);
		String city=areService.findById(city1);
		String town=areService.findById(town1);
		String adrs=pre+city+town;
		
		String sbirth=request.getParameter("birth");
		Date birth=null;
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			birth=sdf.parse(sbirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String phone=request.getParameter("phone");
		String qq=request.getParameter("qq");
		
		int staid=Integer.parseInt(request.getParameter("staid"));
		Status status=new Status();
		status.setStaid(staid);
		int tyid=Integer.parseInt(request.getParameter("tyid"));
		Type type=new Type();
		type.setTypid(tyid);
		int srcid=Integer.parseInt(request.getParameter("srcid"));
		Source source=new Source();
		source.setSourid(srcid);
		
		//调用业务层完成添加操作
		Client client=new Client(cliid, cname, sex, birth, comp, job, adrs, phone, qq, email, remark, pre, city, town, null, type, status, source);
		ClientService cliService=new ClientServiceImpl();
		int n=cliService.add(client);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?method=findCli&function=manage");
			//response.sendRedirect(request.getContextPath()+"/client/client_manage.jsp");
		}else {
			request.setAttribute("error", "添加失败");
			request.getRequestDispatcher("/client/client_add.jsp").forward(request, response);
		}
	}
	
	/**
	 * 动态显示客户类型,状态,来源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所有的员工类型
		TypeService typeService=new TypeServiceImpl();
		List<Type> typeList = typeService.findAll();
		request.setAttribute("typeList", typeList);
		//获取所有的员工状态
		StatusService staService=new StatusServiceImpl();
		List<Status> staList = staService.findAll();
		request.setAttribute("staList", staList);
		//获取员工来源
		SourceService sourService=new SourceServiceImpl();
		List<Source> sourList=sourService.findAll();
		request.setAttribute("sourList", sourList);
		//跳转到/personage/personage_empadd.jsp
		request.getRequestDispatcher("/client/client_add.jsp").forward(request, response);
	}
	
	/**
	 * 客户相关
	 * 		多条件查询客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		PageBean<Client> pageBean=new PageBean<Client>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		
		
		//接收当前登录人编号,为查看我的客户做铺垫
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//接收功能判别标志
		String function=request.getParameter("function");
		//接收查询条件
		String cname=request.getParameter("cname");
		//调用业务层获取所有的员工信息
		ClientService  cliService = new ClientServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.cliDao.findAll(cname,function,empid).size();
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		//条件较少没必要封装
		List<Client> cliList=cliService.findCli(cname,function,empid,start,end);
		pageBean.setList(cliList);
		//跳转到personage/personage_emplist.jsp
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("function", function);
		request.getRequestDispatcher("/client/client_manage.jsp").forward(request, response);
		
	}
	/**
	 * 客户管理
	 * 		修改客户信息-预更新操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("function").equals("1")) {
			//接收要修改的客户的编号
			String cliid = request.getParameter("cliid");
			
			//调用业务层获取该客户的信息
			ClientService cliService = new ClientServiceImpl();
			Client cli = cliService.findById(cliid);
			request.setAttribute("cli", cli);
			
			//获取所有的员工类型
			TypeService typeService=new TypeServiceImpl();
			List<Type> typeList = typeService.findAll();
			request.setAttribute("typeList", typeList);
			//获取所有的员工状态
			StatusService staService=new StatusServiceImpl();
			List<Status> staList = staService.findAll();
			request.setAttribute("staList", staList);
			//获取员工来源
			SourceService sourService=new SourceServiceImpl();
			List<Source> sourList=sourService.findAll();
			request.setAttribute("sourList", sourList);
			
			//页面跳转 system/empUpdate.jsp
			request.getRequestDispatcher("/client/client_update.jsp").forward(request, response);
		}else if (request.getParameter("function").equals("2")) {
			//接收要更新的客户的编号
			String cliid = request.getParameter("cliid");		
			//调用业务层完成查询操作
			ClientService  cliService = new ClientServiceImpl();
			Client cli = cliService.findById(cliid);
			//跳转到
			request.setAttribute("cli", cli);
			//response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?method=findCli&function=manage");
			request.getRequestDispatcher("/client/client_cliallot.jsp").forward(request, response);
		}
		
	}
	/**
	 * 客户管理
	 * 		更新操作
	 * 		分配客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户信息
		String cliid=request.getParameter("cliid");
		String cname=request.getParameter("cname");
		String sex=request.getParameter("sex");
		String comp=request.getParameter("comp");
		String job=request.getParameter("job");
		String email=request.getParameter("email");
		String remark=request.getParameter("remark");
		/**
		 * 需要进行字符串拼接为地址
		 */
		String pre=request.getParameter("pre");
		String city=request.getParameter("city");
		String town=request.getParameter("town");
		String adrs=pre+city+town;
		
		String sbirth=request.getParameter("birth");
		Date birth=null;
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			birth=sdf.parse(sbirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String phone=request.getParameter("phone");
		String qq=request.getParameter("qq");
		
		int staid=Integer.parseInt(request.getParameter("staid"));
		Status status=new Status();
		status.setStaid(staid);
		int tyid=Integer.parseInt(request.getParameter("tyid"));
		Type type=new Type();
		type.setTypid(tyid);
		int srcid=Integer.parseInt(request.getParameter("srcid"));
		Source source=new Source();
		source.setSourid(srcid);
		//调用业务层完成添加操作
		Client client=new Client(cliid, cname, sex, birth, comp, job, adrs, phone, qq, email, remark, null, type, status, source);
		ClientService cliService=new ClientServiceImpl();
		int n=cliService.update(client);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?method=findCli&function=manage");
		}else {
			request.setAttribute("error", "更新失败");
			request.getRequestDispatcher("/client/client_update.jsp").forward(request, response);
		}
	}
	/**
	 * 删除指定客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收要删除的员工的编号
		String cliid = request.getParameter("cliid2");
		// 调用业务层完成删除操作
		ClientService cliService = new ClientServiceImpl();
		cliService.delete(cliid);
		// 跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findAll() ,先查询再显示
		request.getRequestDispatcher("/servlet/ClientServlet?method=findCli&function=manage").forward(request, response);
	}
	
	/**
	 * 客户管理
	 * 		分配客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户信息
		String cliid=request.getParameter("cliid");
		String empid=request.getParameter("empid");
		Employee emp=new Employee();
		emp.setEmpid(empid);
		//调用业务层完成添加操作
		Client client=new Client(cliid, emp);
		ClientService cliService=new ClientServiceImpl();
		int n=cliService.allot(client);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?method=findCli&function=manage");
		}else {
			request.setAttribute("error", "分配失败");
			request.getRequestDispatcher("/client/client_update.jsp").forward(request, response);
		}
		
	}
	/**
	 * 查看指定客户详细信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收指定客户编号
		String cliid=request.getParameter("cliid");
		
		ClientService cliService=new ClientServiceImpl();
		Client client=cliService.findById(cliid);
		//获取所有的员工类型
		TypeService typeService=new TypeServiceImpl();
		List<Type> typeList = typeService.findAll();
		request.setAttribute("typeList", typeList);
		//获取所有的员工状态
		StatusService staService=new StatusServiceImpl();
		List<Status> staList = staService.findAll();
		request.setAttribute("staList", staList);
		//获取员工来源
		SourceService sourService=new SourceServiceImpl();
		List<Source> sourList=sourService.findAll();
		request.setAttribute("sourList", sourList);
		
		request.setAttribute("cli", client);
		request.getRequestDispatcher("/client/client_look.jsp").forward(request, response);
		
	}
	public void getBarData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AreaService areService=new AreaServiceImpl();
		TreeMap<String, Integer> map=areService.findAreaCounts();
		//[{"湖南":"30","福建":"40","江苏":"50" }]  
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(jsonStr);
	}
}
