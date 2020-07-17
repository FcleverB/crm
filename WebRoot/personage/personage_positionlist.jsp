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
<title>岗位管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function deletePos(fpoid) {
		var flag = window.confirm("您确定要删除该部门吗");
		if (flag) {
			location.href = "servlet/PositionServlet?method=delete&fpoid=" + fpoid;
		}

	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">人事管理</a></li>
			<li><a href="#">岗位管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="formtitle1">
			<span>岗位列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>岗位编号<i class="sort"></th>
					<th>岗位名称</th>
					<th>岗位描述</th>
					<th>相关操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${posList}" var="pos">
				<tr>
					<td>${pos.fpoid}</td>
					<td>${pos.fponame}</td>
					<td>${pos.fporemark}</td>
					<td><a href="servlet/PositionServlet?method=findById&fpoid=${pos.fpoid}" class="tablelink">修改</a> <a
						href="javascript:deletePos(${pos.fpoid})" class="tablelink click"> 删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span> <a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>

	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>