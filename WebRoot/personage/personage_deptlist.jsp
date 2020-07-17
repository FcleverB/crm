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
<title>部门管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function deleteDept(deptid) {
		var flag = window.confirm("您确定要删除该部门吗");
		if (flag) {
			location.href = "servlet/DepartmentServlet?method=delete&deptid=" + deptid;
		}

	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">人事管理</a></li>
			<li><a href="#">部门管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="formtitle1">
			<span>部门列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"></th>
					<th>部门名称</th>
					<th>办公地点</th>
					<th>部门描述</th>
					<th>基本操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${deptList}" var="dept">
					<tr>
						
						<td>${dept.deptid}</td>
						<td>${dept.deptname}</td>
						<td>${dept.deptloc}</td>
						<td>${dept.remark}</td>
						<td><a href="servlet/DepartmentServlet?method=findById&deptid=${dept.deptid}" class="tablelink">修改</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="javascript:deleteDept(${dept.deptid})"
							class="tablelink click"> 删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>