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
<title>修改我的密码</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">个人平台</a></li>
			<li><a href="#">修改我的密码</a></li>
		</ul>
	</div>
	<form action="servlet/OwnServlet?method=uppwd" method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<ul class="forminfo">
				<li><label>员工姓名:</label> <input name="${sessionScope.emp.empid}"
					readonly="readonly" type="text" class="dfinput1" value="${sessionScope.emp.ename}" /></li>
				<li><label>新密码:</label> <input name="pwd1" type="text"
					class="dfinput" value="" /></li>
				<li><label>确认密码:</label> <input name="pwd2" type="text"
					class="dfinput" value="" /></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="确认保存" style="border: none;" /></li>
			</ul>
		</div>
	</form>
</body>
</html>
