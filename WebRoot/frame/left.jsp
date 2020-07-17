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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson .header").click(function() {
			var $parent = $(this).parent();
			$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

			$parent.addClass("active");
			if (!!$(this).next('.sub-menus').size()) {
				if ($parent.hasClass("open")) {
					$parent.removeClass("open").find('.sub-menus').hide();
				} else {
					$parent.addClass("open").find('.sub-menus').show();
				}

			}
		});

		// 三级菜单点击
		$('.sub-menus li').click(function(e) {
			$(".sub-menus li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('.menuson').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('.menuson').slideUp();
			} else {
				$(this).next('.menuson').slideDown();
			}
		});
	})
</script>

</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>导航菜单
	</div>
	<dl class="leftmenu">
	<c:if test="${sessionScope.emp.etype==2 }">
			<dd>
				<div class="title">
					<span><img src="images/leftico03.png" /></span>客户中心
				</div>
				<ul class="menuson">
					<li><cite></cite> <a href="servlet/ClientServlet?method=toadd"
						target="rightFrame">添加客户信息</a><i></i></li>
					<li><cite></cite> <a href="servlet/ClientServlet?method=findCli&function=manage"
						target="rightFrame">管理客户信息</a><i></i></li>
					<li><cite></cite> <a href="servlet/ClientServlet?method=findCli&function=public"
						target="rightFrame">公共客户信息</a><i></i></li>
					<%-- <li><cite></cite> <a href="client/client_diagram.jsp"
						target="rightFrame">客户分布图 </a><i></i></li> --%>
					<li><cite></cite> <a href="servlet/ClientServlet?method=findCli&function=my"
						target="rightFrame">我的客户信息</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico03.png" /></span>员工相关
				</div>
				<ul class="menuson">

					<li><cite></cite> <a href="servlet/HomeServlet?method=toadd"
						target="rightFrame">添加房屋信息</a><i></i></li>
					<li><cite></cite> <a href="servlet/HomeServlet?method=findHom&function=1"
						target="rightFrame">房屋信息管理</a><i></i></li>
					<li><cite></cite> <a href="servlet/HomeServlet?method=findHom&function=2"
						target="rightFrame">房屋信息查看</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico03.png" /></span>交易管理
				</div>
				<ul class="menuson">

					<li><cite></cite> <a href="servlet/BarginServlet?method=toadd"
						target="rightFrame">添加交易单</a><i></i></li>
					<li><cite></cite> <a href="servlet/BarginServlet?method=toaudit"
						target="rightFrame">查看交易单</a><i></i></li>
					<li><cite></cite> <a href="deal/deal_diagram.jsp"
						target="rightFrame">收入情况图</a><i></i></li>
					<li><cite></cite> <a href="servlet/BarginServlet?method=findmy"
						target="rightFrame">我的交易单</a><i></i></li>
				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico03.png" /></span>人事管理
				</div>
				<ul class="menuson">
					<li><cite></cite> <a
						href="servlet/EmployeeServlet?method=toAdd" target="rightFrame">添加员工</a><i></i>
					</li>
					<li><cite></cite> <a
						href="servlet/EmployeeServlet?method=findEmp" target="rightFrame">员工管理</a><i></i></li>
					<li><cite></cite> <a href="personage/personage_deptadd.jsp"
						target="rightFrame">添加部门</a><i></i></li>
					<li><cite></cite> <a
						href="servlet/DepartmentServlet?method=findAll"
						target="rightFrame">部门管理</a><i></i></li>
					<li><cite></cite> <a
						href="personage/personage_positionadd.jsp" target="rightFrame">添加岗位</a><i></i>
					</li>
					<li><cite></cite> <a
						href="servlet/PositionServlet?method=findAll" target="rightFrame">岗位管理</a><i></i></li>
				</ul>
			</dd>

			<dd>
				<div class="title">
					<span><img src="images/leftico03.png" /></span>考勤管理
				</div>
				<ul class="menuson">
					<li><cite></cite><a href="attendance/attendance_add.jsp"
						target="rightFrame">签到签退</a><i></i></li>
					<li><cite></cite><a href="attendance/attendance_list.jsp"
						target="rightFrame">考勤管理</a><i></i></li>
					<li><cite></cite><a href="attendance/attendance_my.jsp"
						target="rightFrame">我的考勤</a><i></i></li>
				</ul>
			</dd>
	</c:if>
	<c:if test="${sessionScope.emp.etype==1 }">
		<dd>
			<div class="title">
				<span><img src="images/leftico03.png" /></span>客户中心
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="servlet/ClientServlet?method=toadd"
					target="rightFrame">添加客户信息</a><i></i></li>
				<li><cite></cite> <a href="servlet/ClientServlet?method=findCli&function=my"
					target="rightFrame">我的客户信息</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico03.png" /></span>员工相关
			</div>
			<ul class="menuson">

				<li><cite></cite> <a href="servlet/HomeServlet?method=toadd"
					target="rightFrame">添加房屋信息</a><i></i></li>
				<li><cite></cite> <a href="servlet/HomeServlet?method=findHom&function=2"
					target="rightFrame">房屋信息查看</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico03.png" /></span>交易管理
			</div>
			<ul class="menuson">

				<li><cite></cite> <a href="servlet/BarginServlet?method=toadd"
					target="rightFrame">添加交易单</a><i></i></li>
				<li><cite></cite> <a href="servlet/BarginServlet?method=findmy"
					target="rightFrame">我的交易单</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico03.png" /></span>考勤管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="attendance/attendance_add.jsp"
					target="rightFrame">签到签退</a><i></i></li>
				<li><cite></cite><a href="attendance/attendance_my.jsp"
					target="rightFrame">我的考勤</a><i></i></li>
			</ul>
		</dd>

	</c:if>
	<dd>
		<div class="title">
			<span><img src="images/leftico03.png" /></span>个人平台
		</div>
		<ul class="menuson">
			<li><cite></cite> <a href="servlet/OwnServlet?method=look&function=look"
				target="rightFrame">查看我的信息</a><i></i></li>
			<li><cite></cite> <a href="servlet/OwnServlet?method=look&function=message"
				target="rightFrame">修改我的信息</a><i></i></li>
			<li><cite></cite> <a href="personnel/personnel_pwd.jsp"
				target="rightFrame">修改我的密码</a><i></i></li>
		</ul>
	</dd>
	</dl>
</body>

</html>