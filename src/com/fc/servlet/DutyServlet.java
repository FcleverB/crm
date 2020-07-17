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
	 * ǩ��ǩ��
	 * 		ǩ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//����ҵ������ǩ������
		DutyService dutyService=new DutyServiceImpl();
		int n=dutyService.signin(empid);//1  �ɹ�  0  ʧ��  2 �Ѿ�ǩ��
		//����Ҫҳ����ת,ֱ�ӷ�������
		response.getWriter().println(n);
	}
	/**
	 * ǩ��ǩ��
	 * 		ǩ�˹���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void signout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ��ǰ�û���empID
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//����ҵ������ǩ������
		DutyService dutyService=new DutyServiceImpl();
		int n=dutyService.signout(empid);//1  �ɹ�  0  ʧ��  2 �Ѿ�ǩ��
		//����Ҫҳ����ת,ֱ�ӷ�������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if (n==1) {
			out.println("ǩ�˳ɹ�");
		}else if (n==0) {
			out.println("ǩ�˳ɹ�");
		}else {
			out.println("��δǩ��");
		}
	}
	/**
	 * ��ѯ���в���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����ҵ����ȡ���в���
		DepartmentService  deptService = new DepartmentServiceImpl();		
		List<Department> deptList = deptService.findAll();
		//����Ҫҳ����ת,ֱ�ӷ�������
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(new Gson().toJson(deptList));
	}
	/**
	 * ��ѯǩ��ǩ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findDuty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���մ�ҳ�洫���ҳ��index
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
		
		//��ȡ������ѯ����
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
		//����ҵ�����ɲ�ѯ����
		DutyService dutyService=new DutyServiceImpl();
		//��ѯ���ݿ���ȡ��¼����
		int totalCount=this.dutyDao.findAll();
		//ʹ�ü�¼��������pagebean����������
		pageBean.setTotalCount(totalCount);
		int start=pageBean.getStartRow();
		int end=pageBean.getEndRow();
		
		List<Duty> dutyList=dutyService.findDuty(empid,deptno,dtDate,start,end);
		pageBean.setList(dutyList);
		//����json�ַ���
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//Gson gson=new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonStr=gson.toJson(dutyList);
		request.setAttribute("pageBean", pageBean);
		out.println(jsonStr);
	}
	/**
	 * �����ĵ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exportXls(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ������ѯ����
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
		//����ҵ�����ɲ�ѯ����
		DutyService dutyService=new DutyServiceImpl();
		List<Duty> dutyList=dutyService.find(empid,deptno,dtDate);
		//����outputStream
		createExcel(dutyList,response);
		
	}
	/**
	 * ��ɴ���xls���洢����
	 * @param list
	 * @param response
	 */
	private static void createExcel(List<Duty> list,HttpServletResponse response) {
        // ����һ��Excel�ļ�
        HSSFWorkbook workbook = new HSSFWorkbook();
        // ����һ��������
        HSSFSheet sheet = workbook.createSheet("������Ϣ��");
        //HSSFSheet����һ��������
        
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                5 // last column
        );
        sheet.addMergedRegion(region);
        //HSSFRow����һ��
        HSSFRow hssfRow = sheet.createRow(0);//�õ���һ��
        //HSSFCellһ����Ԫ��
        HSSFCell headCell = hssfRow.createCell(0);//��һ�д�����һ����Ԫ��
        headCell.setCellValue("������Ϣ��¼");//��Ԫ�����ݸ�ֵ
        
        // ���õ�Ԫ���ʽ����
        //HSSFCellStyle��Ԫ����ʽ
        HSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     
        headCell.setCellStyle(cellStyle);
        
        
        // ��ӱ�ͷ��
        hssfRow = sheet.createRow(1);//����һ��
        // ��ӱ�ͷ����
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("�û���");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("��ʵ����");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("��������");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("��������");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(4);
        headCell.setCellValue("ǩ��ʱ��");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(5);
        headCell.setCellValue("ǩ��ʱ��");
        headCell.setCellStyle(cellStyle);
        // �����������
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // ������Ԫ�񣬲�����ֵ
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

        // ����Excel�ļ�
        try {
        	response.setContentType("application/vnd.ms-excel");//����һ��xls���
        	response.setHeader("Content-disposition", "attachment;filename=duty.xls");//������ʽ����,�ļ�����duty.xls
            //OutputStream outputStream = new FileOutputStream("D:/duty.xls");//���Ǳ��浽����(��������)
            OutputStream outputStream=response.getOutputStream();//д���ͻ���
        	workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * ��ѯ�ҵĿ�����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findmy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp=(Employee) request.getSession().getAttribute("emp");
		String empid=emp.getEmpid();
		//����ҵ������ǩ������
		DutyService dutyService=new DutyServiceImpl();
		List<Duty> dutyList=dutyService.findmy(empid);
		//����Ҫҳ����ת,ֱ�ӷ�������
		response.setContentType("text/html;charset=utf-8");
		//�޸�ͨ��ajax����������date�������� ��ʽ����  
		PrintWriter out=response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonStr=gson.toJson(dutyList);
		out.println(jsonStr);
	}
	
}
