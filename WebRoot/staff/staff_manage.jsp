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
<title>房屋信息管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function deleteHom(homid){
		var flag=window.confirm("您确认要删除该房屋吗?");
		if(flag){
			location.href="servlet/HomeServlet?method=delete&homid2="+homid;
		}
	}
	function change(size,index){
		location.href="servlet/HomeServlet?method=findHom&function=1&size="+size+"&index="+index;
	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">员工相关</a></li>
			<li><a href="#">房屋信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="servlet/HomeServlet?method=findHom&function=1" method="post">
		<ul class="prosearch">
			<li><label>按房屋地址查询:</label> <input name="address" type="text"
				class="scinput" value="${address}"/></li>
			<li><label>按户型查询:</label> <select class="select2" name="tyid" style="width: 150px;height: 30px;">
					<option value="0">--全部--</option>
					<c:forEach items="${typeList}" var="type">
						<c:if test="${type.htypid==tyid}">
							<option value="${type.htypid}" selected="selected">${type.name}</option>
						</c:if>
						<c:if test="${type.htypid!=tyid}">
							<option value="${type.htypid}">${type.name}</option>
						</c:if>
					</c:forEach>
			</select></li>
			<li><label>按交易状态查询:</label> <select class="select1" name="sta" style="width: 150px;height: 30px;">
					<c:forEach items="${staList}" var="sta">
						<c:if test="${sta.hstaid==stas}">
							<option value="${sta.hstaid}" selected="selected">${sta.name}</option>
						</c:if>
						<c:if test="${sta.hstaid!=stas}">
							<option value="${sta.hstaid}">${sta.name}</option>
						</c:if>
					</c:forEach>
			</select></li>
			<a> <input name="" type="submit" class="sure" value="查询" />
			</a>
		</ul>
		</form>
		<div class="formtitle1">
			<span>房屋列表</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr style="background-color: #F0F5F7;">
					<th>房屋编号</th>
					<th>房屋地址</th>
					<th>户型</th>
					<th>面积(M²)</th>
					<th>价格(/M²)</th>
					<th>总价(¥)</th>
					<th>状态</th>
					<th>房屋环境</th>
					<th>基本操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="hom">
					<tr>
						<td>${hom.homid}</td>
						<td>${hom.address}</td>
						<td>${hom.htyp.name}</td>
						<td>${hom.area}</td>
						<td>${hom.price}</td>
						<td>${hom.sprice}</td>
						<td>${hom.sta.hstaid}</td>
						<td>${hom.envir}</td>
						<td>
							<a href="servlet/HomeServlet?method=down&homid=${hom.homid}" class="tablelink">下载房屋图片</a> 
							<a href="servlet/HomeServlet?method=toupdate&homid=${hom.homid}" class="tablelink">修改</a> 
							<a href="javascript:void(0)" onclick="deleteHom('${hom.homid}')" class="tablelink click"> 删除</a>
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