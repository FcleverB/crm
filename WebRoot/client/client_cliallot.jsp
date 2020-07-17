<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">客户中心</a></li>
			<li><a href="#">分配客户</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="servlet/ClientServlet?method=allot" method="post">
			<ul class="forminfo">
				<li><label>客户编号</label><input name="cliid" type="text"
					class="dfinput" value="${cli.cliid}" readonly="readonly" /></li>
				<li><label>所属员工</label><input name="empid" type="text"
					class="dfinput" value="${cli.emp.empid}" /></li>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="确认保存" /></li>
			</ul>
		</form>

	</div>
	<span style="color:red;font-size: 16px; font-weight: bold">${error}</span>

</body>

</html>