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
<title></title>
<link rel="stylesheet" href="css/crm.css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<body class="top">
	<div class="topleft">
		<img src="images/logo3.png" /> <img src="images/logo1.png" />
	</div>
	<div class="topcenter"></div>
	<div class="topright">
		<ul>
			<li><span> <img src="images/circle.png" class="uimg" /></span></li>
		    <a href="servlet/ClientServlet?method=toadd" target="rightFrame"><img src="images/icon01.png" title="添加客户"  class="rapid"/><h2 class="f">添加客户</h2></a>
		    <a href="servlet/HomeServlet?method=toadd" target="rightFrame"><img src="images/icon02.png" title="添加房屋"  class="rapid1"/><h2 class="f1">添加房屋</h2></a>
		    <a href="servlet/BarginServlet?method=toadd" target="rightFrame"><img src="images/icon03.png" title="添加交易"  class="rapid2"/><h2 class="f2">添加交易</h2></a>
		    <a href="attendance/attendance_add.jsp" target="rightFrame"><img src="images/icon04.png" title="签到签退"  class="rapid3"/><h2 class="f3">签到签退</h2></a>
		    <a href="servlet/OwnServlet?method=look&function=look" target="rightFrame"><img src="images/icon05.png" title="我的信息"  class="rapid4"/><h2 class="f4">我的信息</h2></a>
			
			<div class="user">
				<span>${sessionScope.emp.ename}</span>
			</div>
			<li><span> <img src="images/help.png" title="帮助"
					class="helpimg" />
			</span> <a href="" target="">帮助</a></li>
			<li><span> <img src="images/exit.png" title="退出"
					class="exitimg" />
			</span> <a href="servlet/EmployeeServlet?method=logout" target="_parent">退出</a></li>
		</ul>
	</div>
</body>
</html>
