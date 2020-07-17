package com.fc.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.formula.functions.Substitute;

import com.fc.dao.HomeDao;
import com.fc.dao.impl.HomeDaoImpl;
import com.fc.entity.Client;
import com.fc.entity.Department;
import com.fc.entity.Employee;
import com.fc.entity.Home;
import com.fc.entity.Homestatus;
import com.fc.entity.Hometype;
import com.fc.entity.Source;
import com.fc.entity.Status;
import com.fc.entity.Type;
import com.fc.service.ClientService;
import com.fc.service.DepartmentService;
import com.fc.service.EmployeeService;
import com.fc.service.HomeService;
import com.fc.service.HomestatusService;
import com.fc.service.HometypeService;
import com.fc.service.SourceService;
import com.fc.service.StatusService;
import com.fc.service.TypeService;
import com.fc.service.impl.ClientServiceImpl;
import com.fc.service.impl.DepartmentServiceImpl;
import com.fc.service.impl.EmployeeServiceImpl;
import com.fc.service.impl.HomeServiceImpl;
import com.fc.service.impl.HomestatusServiceImpl;
import com.fc.service.impl.HometypeServiceImpl;
import com.fc.service.impl.SourceServiceImpl;
import com.fc.service.impl.StatusServiceImpl;
import com.fc.service.impl.TypeServiceImpl;
import com.fc.util.PageBean;

import oracle.net.aso.h;

public class HomeServlet extends BaseServlet {
	private HomeDao homDao=new HomeDaoImpl();
	/**
	 * 动态显示房屋户型,交易状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所有的房屋户型
		HometypeService typeService=new HometypeServiceImpl();
		List<Hometype> typeList = typeService.findAll();
		request.setAttribute("typeList", typeList);
		//获取所有的房屋交易状态
		HomestatusService staService=new HomestatusServiceImpl();
		List<Homestatus> staList = staService.findAll();
		request.setAttribute("staList", staList);
		//跳转到/personage/personage_empadd.jsp
		request.getRequestDispatcher("/staff/staff_add.jsp").forward(request, response);
	}
	/**
	 * 添加房屋信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建FileItemFactory对象
		FileItemFactory factory=new DiskFileItemFactory();
		//创建ServletfileUpload对象
		ServletFileUpload upload=new ServletFileUpload(factory);
		//完善5:限制上传的单个和所有文件的大小:建议使用该方式
		upload.setHeaderEncoding("utf-8");//解决file表单项的文件名中文乱码问题
		upload.setFileSizeMax(150*1024);//单个文件的上限
		upload.setSizeMax(5*16*1024);//所有文件总和上限
		//通过ServletfileUpload对象实现上传操作,将客户端一个个表单项分装到一个个FileItem中
		List<FileItem> itemList=null;
		try {
			 itemList= upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "文件不能大于150kb");
			request.getRequestDispatcher("/staff/staff_add.jsp").forward(request, response);
			return;
		}
		String address=null;
		String envir=null;
		String remark=null;
		String realname=null;
		String photoname=null;
		String phototype=null;
		
		int area=0;
		int price=0;
		int sprice=0;
		int tyid=0;
		int sta=0;
		
		//遍历各个FileItem,(相当于对各个表单项进行处理)
		for (int i = 0; i < itemList.size(); i++) {
			//取出第i个表单项
			FileItem item=itemList.get(i);
			//对各个表单项进行处理(普通表单项和文件表单项要分开处理)
			String fileName=item.getFieldName();
			if (item.isFormField()) {//普通表单项
				//address
				if ("address".equals(fileName)) {
					address=item.getString("utf-8");
				}
				//envir
				if ("envir".equals(fileName)) {
					envir=item.getString("utf-8");
				}
				///remark
				if ("remark".equals(fileName)) {
					remark=item.getString("utf-8");
				}
				//area
				if ("area".equals(fileName)) {
					area=Integer.parseInt(item.getString());
				}
				//price
				if ("price".equals(fileName)) {
					price=Integer.parseInt(item.getString());
				}
				//sprice
				if ("sprice".equals(fileName)) {
					sprice=Integer.parseInt(item.getString());
				}
				//tyid
				if ("tyid".equals(fileName)) {
					tyid=Integer.parseInt(item.getString());
				}
				//sta
				if ("sta".equals(fileName)) {
					sta=Integer.parseInt(item.getString());
				}
			}else {//文件表单项
				//photo
				if ("photo".equals(fileName)) {
					//限制上传文件大小  不合适  此时图片已经传到服务器临时目录
					/*long size=item.getSize();
					if (size>150*1024) {
						request.setAttribute("error", "文件不能大于150kb");
						request.getRequestDispatcher("/staff/staff_add.jsp").forward(request, response);
						return;
					}*/
					
					//完善4:只上传jpg,jpeg和gif文件
					String contentType=item.getContentType();
					phototype=item.getContentType();
					if (!"image/jpeg".equals(contentType)&&!"image/gif".equals(contentType)) {
						request.setAttribute("error", "只能上传jpg和gif文件");
						request.getRequestDispatcher("/staff/staff_add.jsp").forward(request, response);
						return;
					}
					//指定上传的文件夹(Tomcat的webApps目录下,Tomcat的webApps目录之外)
					//File dir=new File("d:/upload");
					//System.out.println(request.getServletContext().getRealPath("/")+"upload");
					//File dir=new File("G:\\Tomcat\\apache-tomcat-9.0.10\\webapps\\crm/upload");
					//逻辑路径改为物理路径
					File dir=new File(request.getServletContext().getRealPath("/upload"));
					//完善1:如果文件夹不存在,就创建
					if (!dir.exists()) {
						dir.mkdirs();
					}
					//指定上传的文件名
					realname=item.getName();
					//完善2:为了防止文件的同名覆盖,上传到服务器端的文件需要重新命名
					UUID uuid=UUID.randomUUID();
					String extName=realname.substring(realname.lastIndexOf("."));
					photoname=uuid.toString()+extName;
					//指定上传的的文件夹和文件名
					File file=new File(dir,photoname);
					//上传图片到指定位置
					try {
						item.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		Hometype hty=new Hometype();
		hty.setHtypid(tyid);
		Homestatus hsta=new Homestatus();
		hsta.setHstaid(sta);
		
		//调用业务层完成添加操作
		Home home=new Home(address, price, area, sprice, envir, remark, hsta, hty,realname,photoname,phototype);
		HomeService homService=new HomeServiceImpl();
		int n=homService.add(home);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/HomeServlet?method=findHom&function=5");
			//response.sendRedirect(request.getContextPath()+"/client/client_manage.jsp");
		}else {
			request.setAttribute("error", "添加失败");
			request.getRequestDispatcher("/staff/staff_add.jsp").forward(request, response);
		}
	}
	/**
	 * 房屋管理
	 * 		多条件查询房屋信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findHom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		PageBean<Home> pageBean=new PageBean<Home>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		
		int function=Integer.parseInt(request.getParameter("function"));
		//接收查询条件
		String address=request.getParameter("address");
		int tyid=0;
		String styid=request.getParameter("tyid");
		try {
			tyid=Integer.parseInt(styid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		int stas=1;
		String sstas=request.getParameter("sta");
		try {
			stas=Integer.parseInt(sstas);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//调用业务层获取所有的员工信息
		HomeService  homService = new HomeServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.homDao.findAll().size();
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		//条件较少没必要封装
		List<Home> homList=homService.findHom(address,tyid,stas,start,end);
		pageBean.setList(homList);
		//获取所有的房屋户型
		HometypeService typeService=new HometypeServiceImpl();
		List<Hometype> typeList = typeService.findAll();
		request.setAttribute("typeList", typeList);
		//获取所有的房屋交易状态
		HomestatusService staService=new HomestatusServiceImpl();
		List<Homestatus> staList = staService.findAll();
		request.setAttribute("staList", staList);
		
		//跳转到personage/personage_emplist.jsp
		request.setAttribute("address", address);
		request.setAttribute("tyid", tyid);
		request.setAttribute("stas", stas);
		request.setAttribute("pageBean", pageBean);
		if(function==2) {
			request.getRequestDispatcher("/staff/staff_message.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/staff/staff_manage.jsp").forward(request, response);
		}
		
	}
	/**
	 * 修改指定编号的房屋信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收要修改的房屋的编号
		int homid = Integer.parseInt(request.getParameter("homid"));
		
		//调用业务层获取该房屋的信息
		HomeService homService = new HomeServiceImpl();
		Home home = homService.findById(homid);
		request.setAttribute("home", home);
		
		//获取所有的房屋户型
		HometypeService typeService=new HometypeServiceImpl();
		List<Hometype> typeList = typeService.findAll();
		request.setAttribute("typeList", typeList);
		//获取所有的房屋交易状态
		HomestatusService staService=new HomestatusServiceImpl();
		List<Homestatus> staList = staService.findAll();
		request.setAttribute("staList", staList);
		request.setAttribute("homid", homid);
		//页面跳转 system/empUpdate.jsp
		request.getRequestDispatcher("/staff/staff_update.jsp").forward(request, response);
	}
	/**
	 * 更新房屋信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int homid=Integer.parseInt(request.getParameter("homid"));
		//获取房屋信息
		String address=request.getParameter("address");
		String envir=request.getParameter("envir");
		String remark=request.getParameter("remark");
		
		int area=Integer.parseInt(request.getParameter("area"));
		int price=Integer.parseInt(request.getParameter("price"));
		int sprice=Integer.parseInt(request.getParameter("sprice"));
		
		Hometype hty=new Hometype();
		hty.setHtypid(Integer.parseInt(request.getParameter("tyid")));
		Homestatus hsta=new Homestatus();
		hsta.setHstaid(Integer.parseInt(request.getParameter("sta")));
		
		
		//调用业务层完成添加操作
		Home home=new Home(homid,address, price, area, sprice, envir, remark, hsta, hty);
		HomeService homService=new HomeServiceImpl();
		int n=homService.update(home);
		//根据结果进行页面跳转
		if (n>0) {
			response.sendRedirect(request.getContextPath()+"/servlet/HomeServlet?method=findHom&function=1");
			//response.sendRedirect(request.getContextPath()+"/client/client_manage.jsp");
		}else {
			request.setAttribute("error", "更新失败");
			request.getRequestDispatcher("/staff/staff_update.jsp").forward(request, response);
		}
	}
	
	/**
	 * 删除指定房屋
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收要删除的员工的编号
		int homid = Integer.parseInt(request.getParameter("homid2"));
		// 调用业务层完成删除操作
		HomeService homService = new HomeServiceImpl();
		homService.delete(homid);
		// 跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findAll() ,先查询再显示
		request.getRequestDispatcher("/servlet/HomeServlet?method=findHom&function=4").forward(request, response);
	}
	/**
	 * 下载房屋照片
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取学生的编号
		int homid=Integer.parseInt(request.getParameter("homid"));
		//根据编号调用业务层查询到该学生的所有信息(包括realName,photoName,photoType)
		HomeService homService=new HomeServiceImpl();
		Home home=homService.findById(homid);
		//3通过IO流实现下载照片(从服务器端到客户端)
		//3.1创建输入流和输出流
		String realPath=this.getServletContext().getRealPath("/upload");
		//String fileName="G:\\Tomcat\\apache-tomcat-9.0.10\\webapps\\updownload1\\upload\\"+stu.getPhotoName();
		String fileName=realPath+"/"+home.getPhotoname();
		File file=new File(fileName);
		response.setContentLength((int)file.length());//文件的长度
		response.setContentType(home.getPhototype());//MIME类型
		//response.setHeader("Content-disposition", "inline");默认相当于查看
		
		String realName= home.getRealname();//可能中文
		String userAgent=request.getHeader("User-Agent").toLowerCase();
		if (userAgent.indexOf("msie")>=0) {
			realName=URLEncoder.encode(realName,"utf-8");
		}else {
			realName=new String(realName.getBytes("utf-8"),"iso-8859-1");
		}
		
		
		
		response.setHeader("Content-disposition", "attachment;filename="+home.getRealname());
		InputStream is=new FileInputStream(file);//服务器端一个文件
		OutputStream os=response.getOutputStream();//写到客户端
		//3.2使用输入流和输出流完成复制操作(服务器端->客户端)
		IOUtils.copy(is, os);//commons-io.jar包下
		//3.3关闭流
		is.close();
		os.close();
		 
		//表单method=  get   表单提交的中文乱码解决
		//request.setCharacterEncoding("utf-8");//post
		//byte [] bytes=name.getBytes("iso-8859-1");
		//name=new String(bytes,"utf-8");
		//name=new String(name.getBytes("iso-8859-1"),"utf-8");//客户端到服务器
		//name=new String(name.getBytes("utf-8"),"iso-8859-1");//服务器到客户端
		
		
	}
}
