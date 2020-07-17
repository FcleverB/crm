<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function deleteEmp(empid){
		var flag=window.confirm("您确认要删除该员工吗?");
		if(flag){
			location.href="servlet/EmployeeServlet?method=delete&empid2="+empid;
		}
	}
	function change(size,index){
		location.href="servlet/EmployeeServlet?method=findEmp&size="+size+"&index="+index;
	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">人事管理</a></li>
			<li><a href="#">员工管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<form action="servlet/EmployeeServlet?method=findEmp" method="post">
			<ul class="prosearch">
				<li><label style="margin-left: 200px;">按用户名查询：</label> <input
					name="empid" type="text" class="scinput" style="margin-left: 20px;margin-top: 4px" value="${empid}"/></li>
				<li><label style="margin-left: 0px;">按所属部门查询：</label> <select
					name="deptid" class="select2" style="margin-left: 10px;margin-top: 4px;width: 154px;height: 29px">
						<option value="0">--全部--</option>
						<c:forEach items="${deptList}" var="dept">
							<c:if test="${dept.deptid==deptid}">
								<option value="${dept.deptid}" selected="selected">${dept.deptname}</option>
							</c:if>
							<c:if test="${dept.deptid!=deptid}">
								<option value="${dept.deptid}">${dept.deptname}</option>
							</c:if>
						</c:forEach>
				</select></li>
				<li>
					<label>是否在职：</label>
					<c:if test="${onduty==1 }">
						<input name="onduty" type="radio" value="1" checked="checked" />&nbsp;是&nbsp;&nbsp;
						<input name="onduty" type="radio" value="0" />&nbsp;否	
					</c:if>
					<c:if test="${onduty==0 }">
						<input name="onduty" type="radio" value="1"  />&nbsp;是&nbsp;&nbsp;
						<input name="onduty" type="radio" value="0" checked="checked"/>&nbsp;否	
					</c:if>		
					<c:if test="${empty onduty}">
						<input name="onduty" type="radio" value="1"  checked="checked" />&nbsp;是&nbsp;&nbsp;
						<input name="onduty" type="radio" value="0"/>&nbsp;否	
					</c:if>		
				</li>
				<input name="" type="submit" class="sure" value="查询" />
			</ul>
		</form>
		<div class="formtitle1">
			<span>员工列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>用户名</th>
					<th>员工姓名</th>
					<th>所属部门</th>
					<th>所属岗位</th>
					<th>上级编号</th>
					<th>上级姓名</th>
					<th>入职时间</th>
					<th>联系方式</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="emp">
					<tr>
						<td>${emp.empid}</td>
						<td>${emp.ename}</td>
						<td>${emp.dept.deptname}</td>
						<td>${emp.position.fponame}</td>
						<td>${emp.mgr.empid}</td>
						<td>${emp.mgr.ename}</td>
						<td>${emp.indate}</td>
						<td>${emp.phone}</td>
						<td>
							<a href="empInfo.html" class="tablelink">查看员工详细信息</a> 
							<a href="servlet/EmployeeServlet?method=toupdate&empid=${emp.empid}" class="tablelink">修改</a> 
							<a href="javascript:void(0)" onclick="deleteEmp('${emp.empid}')" class="tablelink"> 删除</a>
							<a href="servlet/EmployeeServlet?method=resetpwd&empid=${emp.empid}" class="tablelink"> 重置密码</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagin">
			<div class="message">
				共<i class="blue">${pageBean.totalCount}</i>条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前显示第&nbsp;<i
					class="blue">${pageBean.index}&nbsp;</i>页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					每页显示 
					<select onchange="change(this.value)">
						<c:forEach begin="5" end="25" step="5" var="i">
							<c:if test="${i==pageBean.size}">
								<option value="${i}" selected="selected">${i}</option>
							</c:if>
							<c:if test="${i!=pageBean.size}">
								<option value="${i}">${i}</option>
							</c:if>
						</c:forEach>
					</select>
					条记录
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:change(${pageBean.size},1)">首页</a></li>
				<c:if test="${pageBean.index!=1}">
					<li class="paginItem"><a href="javascript:change(${pageBean.size},${pageBean.index-1})"><span
						class="pagepre"></span></a></li>
				</c:if>
				<c:if test="${pageBean.index==1}">
					<li class="paginItem"><a><span class="pagepre"></span></a></li>
				</c:if>
				<c:forEach items="${pageBean.numbers}" var="num">
					<c:if test="${num==pageBean.index}">
						<li class="paginItem current"><a href="javascript:change(${pageBean.size},${num})">${num}</a></li>
					</c:if>
					<c:if test="${num!=pageBean.index}">
						<li class="paginItem"><a href="javascript:change(${pageBean.size},${num})">${num}</a></li>
					</c:if>
				</c:forEach>		
				<c:if test="${pageBean.index != pageBean.totalPageCount}">
					<li class="paginItem"><a href="javascript:change(${pageBean.size},${pageBean.index+1})"><span
						class="pagenxt"></span></a></li>
				</c:if>
				<c:if test="${pageBean.index == pageBean.totalPageCount}">
					<li class="paginItem"><a><span class="pagenxt"></span></a></li>
				</c:if>
				<li class="paginItem"><a href="javascript:change(${pageBean.size},${pageBean.totalPageCount})">末页</a></li>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</ul>
		</div>

	</div>
</body>

</html>