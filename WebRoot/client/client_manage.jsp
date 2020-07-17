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
<title>客户管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function deleteCli(cliid){
		var flag=window.confirm("您确认要删除该员工吗?");
		if(flag){
			location.href="servlet/ClientServlet?method=delete&cliid2="+cliid;
		}
	}
	function change(size,index){
		location.href="servlet/ClientServlet?method=findCli&function=manage&size="+size+"&index="+index;
	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">客户中心</a></li>
			<li><a href="#">管理客户信息</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="servlet/ClientServlet?method=findCli&function=manage" method="post">
			<ul class="prosearch">
				<li><label>查询内容:</label> <input name="cname" type="text"
					class="scinput" /></li>
				<li><label>请选择查询方式:</label> <select name="select" class="select2" style="width: 150px;height: 30px;margin-left: 20px;margin-top: 2px">
						<option value="1">客户姓名</option>
						<option>客户性别</option>
						<option>客户状态</option>
						<option>客户来源</option>
						<option>所属员工</option>
						<option>客户类型</option>
						<option>客户手机</option>
				</select></li>
				<a> <input name="" type="submit" class="sure" value="查询" />
				</a>
			</ul>
		</form>
		<div class="formtitle1">
			<span>客户列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<c:if test="${function=='manage'}">
					<tr style="background-color: #F0F5F7;">
						<th>客户姓名</th>
						<th>客户状态</th>
						<th>客户来源</th>
						<th>所属员工</th>
						<th>客户类型</th>
						<th>客户性别</th>
						<th>客户手机</th>
						<th>客户公司</th>
						<th>客户职位</th>
						<th>相关操作</th>
					</tr>
				</c:if>
				<c:if test="${function=='public'}">
					<tr style="background-color: #F0F5F7;">
						<th>客户姓名</th>
						<th>客户状态</th>
						<th>客户来源</th>
						<th>所属员工</th>
						<th>客户类型</th>
						<th>客户性别</th>
						<th>客户手机</th>
						<th>客户公司</th>
						<th>客户职位</th>
						<th>相关操作</th>
					</tr>
				</c:if>
				<c:if test="${function=='my'}">
					<tr style="background-color: #F0F5F7;">
						<th>客户姓名</th>
						<th>客户状态</th>
						<th>客户来源</th>
						<th>客户类型</th>
						<th>客户性别</th>
						<th>客户手机</th>
						<th>客户公司</th>
						<th>客户职位</th>
						<th>相关操作</th>
					</tr>
				</c:if>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="cli">
					<tr>
					<td>${cli.cname}</td>
					<td>${cli.sta.sname}</td>
					<td>${cli.sour.sname}</td>
					<c:if test="${function!='my'}">
						<td>${cli.emp.empid}</td>
					</c:if>
					<td>${cli.ctyp.cname}</td>
					<td>${cli.sex}</td>
					<td>${cli.phone}</td>
					<td>${cli.comp}</td>
					<td>${cli.job}</td>
					<td>
						 <a href="servlet/ClientServlet?method=look&cliid=${cli.cliid}" class="tablelink">查看</a>
						 <a href="servlet/ClientServlet?method=toupdate&cliid=${cli.cliid}&function=1" class="tablelink">修改</a> 
						 <a href="javascript:void(0)" onclick="deleteCli('${cli.cliid}')" class="tablelink"> 删除</a>
						 <a href="servlet/ClientServlet?method=toupdate&cliid=${cli.cliid}&function=2" class="tablelink">分配</a>
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