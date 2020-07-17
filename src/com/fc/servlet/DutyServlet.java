package com.fc.servlet;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.print.attribute.standard.PagesPerMinute;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.util.CellRangeAddress;

import com.fc.dao.DutyDao;
import com.fc.dao.impl.DutyDaoImpl;
import com.fc.entity.Department;
import com.fc.entity.Duty;
import com.fc.entity.Employee;
import com.fc.entity.Home;
import com.fc.service.DepartmentService;
import com.fc.service.DutyService;
import com.fc.service.impl.DepartmentServiceImpl;
import com.fc.service.impl.DutyServiceImpl;
import com.fc.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class DutyServlet extends BaseServlet {
	private DutyDao dutyDao=new DutyDaoImpl();
	/**
	 * 签到签退
	 * 		签到功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//调用业务层完成签到操作
		DutyService dutyService=new DutyServiceImpl();
		int n=dutyService.signin(empid);//1  成功  0  失败  2 已经签到
		//不需要页面跳转,直接返回内容
		response.getWriter().println(n);
	}
	/**
	 * 签到签退
	 * 		签退功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前用户的empID
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//调用业务层完成签到操作
		DutyService dutyService=new DutyServiceImpl();
		int n=dutyService.signout(empid);//1  成功  0  失败  2 已经签到
		//不需要页面跳转,直接返回内容
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if (n==1) {
			out.println("签退成功");
		}else if (n==0) {
			out.println("签退成功");
		}else {
			out.println("尚未签到");
		}
	}
	/**
	 * 查询所有部门
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用业务层获取所有部门
		DepartmentService  deptService = new DepartmentServiceImpl();		
		List<Department> deptList = deptService.findAll();
		//不需要页面跳转,直接返回内容
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(new Gson().toJson(deptList));
	}
	/**
	 * 查询签到签退信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findDuty(HttpServletRequest request, HttpServletResponse response)
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
		PageBean<Duty> pageBean=new PageBean<Duty>();
		pageBean.setIndex(index);
		pageBean.setSize(size);
		
		//获取三个查询条件
		String empid = request.getParameter("empid");
		String sdeptno = request.getParameter("deptno");//null  ""
		int deptno = 0;
		try{
			deptno = Integer.parseInt(sdeptno);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		String sdtDate = request.getParameter("dtDate");//234r1324  null
		java.sql.Date dtDate = null;
		try{
			dtDate = java.sql.Date.valueOf(sdtDate);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		//调用业务层完成查询操作
		DutyService dutyService=new DutyServiceImpl();
		//查询数据库表获取记录总数
		int totalCount=this.dutyDao.findAll();
		//使用记录总数计算pagebean的其他属性
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		
		List<Duty> dutyList=dutyService.findDuty(empid,deptno,dtDate,start,end);
		pageBean.setList(dutyList);
		//返回json字符串
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//Gson gson=new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonStr=gson.toJson(dutyList);
		request.setAttribute("pageBean", pageBean);
		out.println(jsonStr);
	}
	/**
	 * 导出文档
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exportXls(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取三个查询条件
		String empid = request.getParameter("empid");
		String sdeptno = request.getParameter("deptno");//null  ""
		int deptno = 0;
		try{
			deptno = Integer.parseInt(sdeptno);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		String sdtDate = request.getParameter("dtDate");//234r1324  null
		java.sql.Date dtDate = null;
		try{
			dtDate = java.sql.Date.valueOf(sdtDate);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		//调用业务层完成查询操作
		DutyService dutyService=new DutyServiceImpl();
		List<Duty> dutyList=dutyService.find(empid,deptno,dtDate);
		//返回outputStream
		createExcel(dutyList,response);
		
	}
	/**
	 * 完成创建xls表格存储数据
	 * @param list
	 * @param response
	 */
	private static void createExcel(List<Duty> list,HttpServletResponse response) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("考勤信息表");
        //HSSFSheet创建一个工作表
        
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                5 // last column
        );
        sheet.addMergedRegion(region);
        //HSSFRow创建一行
        HSSFRow hssfRow = sheet.createRow(0);//得到第一行
        //HSSFCell一个单元格
        HSSFCell headCell = hssfRow.createCell(0);//第一行创建第一个单元格
        headCell.setCellValue("考勤信息记录");//单元格内容赋值
        
        // 设置单元格格式居中
        //HSSFCellStyle单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     
        headCell.setCellStyle(cellStyle);
        
        
        // 添加表头行
        hssfRow = sheet.createRow(1);//创建一行
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("所属部门");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出勤日期");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(4);
        headCell.setCellValue("签到时间");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(5);
        headCell.setCellValue("签退时间");
        headCell.setCellStyle(cellStyle);
        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmp().getEmpid());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmp().getEname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getEmp().getDept().getDeptname());
            cell.setCellStyle(cellStyle);
            
            cell = hssfRow.createCell(3);
            cell.setCellValue(duty.getDtDate().toString().substring(0, 10));
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSigninTime());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignoutTime());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
        	response.setContentType("application/vnd.ms-excel");//返回一个xls表格
        	response.setHeader("Content-disposition", "attachment;filename=duty.xls");//附件形式下载,文件名叫duty.xls
            //OutputStream outputStream = new FileOutputStream("D:/duty.xls");//这是保存到本地(服务器端)
            OutputStream outputStream=response.getOutputStream();//写到客户端
        	workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * 查询我的考勤信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findmy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//调用业务层完成签到操作
		DutyService dutyService=new DutyServiceImpl();
		List<Duty> dutyList=dutyService.findmy(empid);
		//不需要页面跳转,直接返回内容
		response.setContentType("text/html;charset=utf-8");
		//修复通过ajax传输数据中date数据类型 格式问题  
		PrintWriter out=response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonStr=gson.toJson(dutyList);
		out.println(jsonStr);
	}
	
}
