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
<title>添加客户信息</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">客户中心</a></li>
			<li><a href="#">添加客户信息</a></li>
		</ul>
	</div>
	<form action="servlet/ClientServlet?method=add" method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<ul class="forminfo">
				<li><label>客户编号:</label> <input name="cliid" type="text"
					class="dfinput" value="${cli.cliid}" readonly="readonly"/><i></i></li>
				<li><label>客户姓名:</label> <input name="cname" type="text"
					class="dfinput" value="${cli.cname}" readonly="readonly"/></li>
				<li><label>性别:</label> <cite> 
					<c:if test="${cli.sex=='男' }">
						<input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="sex" type="radio" value="女" />女&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${cli.sex=='女' }">
						<input name="sex" type="radio" value="男"/>男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="sex" type="radio" value="女"  checked="checked"/>女&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				</cite></li>
				<li><label>客户状态:</label>
					<div class="vocation">
						<select class="select1" name="staid" disabled="disabled">
							<c:forEach items="${staList}" var="sta">
								<c:if test="${sta.staid==cli.sta.staid}">
									<option value="${sta.staid}" selected="selected">${sta.sname}</option>
								</c:if>
								<c:if test="${sta.staid!=cli.sta.staid}">
									<option value="${sta.staid}">${sta.sname}</option>
								</c:if>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户类型:</label>
					<div class="vocation">
						<select class="select1" name="tyid" disabled="disabled">
							<c:forEach items="${typeList}" var="type">
								<c:if test="${type.typid==cli.ctyp.typid}">
									<option value="${type.typid}" selected="selected">${type.cname}</option>
								</c:if>
								<c:if test="${type.typid!=cli.ctyp.typid}">
									<option value="${type.typid}">${type.cname}</option>
								</c:if>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户来源:</label>
					<div class="vocation">
						<select class="select1" name="srcid" disabled="disabled">
							<c:forEach items="${sourList}" var="sour">
								<c:if test="${sour.sourid==cli.sour.sourid}">
									<option value="${sour.sourid}" selected="selected">${sour.sname}</option>
								</c:if>
								<c:if test="${sour.sourid!=cli.sour.sourid}">
									<option value="${sour.sourid}">${sour.sname}</option>
								</c:if>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户公司:</label> <input name="comp" type="text"
					class="dfinput" value="${cli.comp}" readonly="readonly"/></li>
				<li><label>客户职位:</label> <input name="job" type="text"
					class="dfinput" value="${cli.job}" readonly="readonly"/></li>
				<li><label>出生日期:</label> <input name="birth" type="text"
					class="dfinput" value="${cli.birth}" readonly="readonly"/></li>
				<li><label>客户地址:</label>
					<div
						style="margin-left: 50px;width: 750px;margin-top: 15px;font-size: 16px;">
						省: 
						<select name="pre" id="pre" style="width: 100px;height: 30px" disabled="disabled">
							<option>${cli.pre}</option>
						</select>
						市: 
						<select name="city" id="city" style="width: 100px;height: 30px" disabled="disabled">
							<option>${cli.city}</option>
						</select>
						区/县: 
						<select name="town" id="town" style="width: 100px;height: 30px" disabled="disabled">
							<option>${cli.town}</option>
						</select>
					</div></li>
				<li><label>客户手机:</label> <input name="phone" type="text"
					class="dfinput" value="${cli.phone}" readonly="readonly"/></li>
				<li><label>QQ:</label> <input name="qq" type="text"
					class="dfinput" value="${cli.qq}" readonly="readonly"/></li>
				<li><label>邮箱:</label> <input name="email" type="text"
					class="dfinput" value="${cli.email}" readonly="readonly"/></li>
				<li><label>备注:</label> <textarea name="remark" id="ecp" cols=""
						rows="" class="textinput" readonly="readonly">${cli.remark}</textarea></li>
			</ul>
		</div>
	</form>
	<span>${error}</span>
</body>

</html>