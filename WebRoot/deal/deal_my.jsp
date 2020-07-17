<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>添加交易单</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function change(size,index){
		location.href="servlet/BarginServlet?method=findmy&size="+size+"&index="+index;
	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">交易管理</a></li>
			<li><a href="#">我的交易单</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="servlet/BarginServlet?method=findmy" method="post">
			<ul class="prosearch">
				<li><label>请输入查询内容:</label> <input name="barid" type="text"
					class="scinput" /></li>
				<li><label>请选择查询方式:</label> <select class="select2">
						<option selected="selected">订单号</option>
						<option>客户姓名</option>
						<option>员工姓名</option>
				</select></li>
				<a> <input name="" type="submit" class="sure" value="查询" />
				</a>
				<a> <input name="" type="button" class="sure" value="导出" />
				</a>
			</ul>
		</form>
		<div class="formtitle1">
			<span>交易列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr style="background-color: #F0F5F7;">
					<th>订单号</th>
					<th>客户姓名</th>
					<th>员工姓名</th>
					<th>房屋编号</th>
					<th>成交价格</th>
					<th>成交时间</th>
					<th>备注信息</th>
					<th>交易状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="bar">
					<tr>
						<td>${bar.barid}</td>
						<td>${bar.client.cname}</td>
						<td>${bar.emp.ename}</td>
						<td>${bar.home.homid}</td>
						<td>${bar.price}</td>
						<td>${bar.bardate}</td>
						<td>${bar.remark}</td>
						<td>${bar.status}</td>
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