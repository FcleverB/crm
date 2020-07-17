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
<title>查看我的信息</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">客户中心</a></li>
			<li><a href="#">查看我的信息</a></li>
		</ul>
	</div>
	<div class="my1">
		<img src="images/head.png" class="head" style="margin-left: -200px;"/>
		<div class="my2">
			<i>${emp.ename}</i><br />
			<br /> <i>这个人很懒,什么也没留下</i>
		</div>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<ul class="forminfo">
			<li><label>员工编号:</label> <input name="empid" type="text"
					class="dfinput" value="${emp.empid}" readonly="readonly"/><i></i></li>
			<li><label>员工姓名:</label> <input name="ename" type="text"
				class="dfinput" value="${emp.ename}" readonly="readonly"/><i></i></li>
			<li><label>性别</label><cite> 
				<c:if test="${emp.sex=='男' }">
					<input name="sex" type="radio" value="男" checked="checked"  />男
				</c:if>
				<c:if test="${emp.sex=='女' }">
					<input name="sex" type="radio" value="女"  checked="checked"  />女
				</c:if>
			</cite></li>
			<li><label>出生日期:</label> <input name="birth" type="text"
				class="dfinput"
				onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" value="${emp.birth}" readonly="readonly"/></li>
			<li><label>入职时间:</label> <input name="indate" type="text"
				class="dfinput"
				onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" value="${emp.indate}" readonly="readonly"/><i></i></li>

			<li><label>离职时间:</label> <input name="leave" type="text"
				class="dfinput"
				onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" value="${emp.leave}" readonly="readonly"/><i></i></li>
			<li><label>是否在职:</label><cite> 
				<c:if test="${emp.onduty==1}">
					<input name="onduty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="onduty" type="radio" value="0" />否
				</c:if>
				<c:if test="${emp.onduty==0}">
					<input name="onduty" type="radio" value="1" />是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="onduty" type="radio" value="0"  checked="checked"/>否
				</c:if>
			</cite></li>
			<li><label>员工类型</label><cite> 
					<c:if test="${2==emp.etype}">
						<input name="etype"type="radio" value="1"/>基层员工&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="etype"type="radio" value="2" checked="checked" />各级管理人员
					</c:if>
					<c:if test="${1==emp.etype}">
						<input name="etype"type="radio" value="1" checked="checked" />基层员工&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="etype"type="radio" value="2"  />各级管理人员
					</c:if>
			</cite></li>
			<li><label>所属部门:</label>
				<div class="vocation1"
					style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
					<select class="select11" style="width: 150px;height: 40px;"
						name="deptid" disabled="disabled">
						<c:forEach items="${deptList}" var="dept">
							<c:if test="${dept.deptid==emp.dept.deptid}">
								<option value="${dept.deptid}" selected="selected">${dept.deptname}</option>
							</c:if>
							<c:if test="${dept.deptid!=emp.dept.deptid}">
								<option value="${dept.deptid}">${dept.deptname}</option>
							</c:if>
						</c:forEach>
					</select>
				</div></li>
			<li><label>从事岗位:</label>
				<div class="vocation1"
					style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
					<select class="select11" style="width: 150px;height: 40px;"
						name="posid" disabled="disabled">
						<c:forEach items="${posList}" var="pos">
							<c:if test="${pos.fpoid==emp.position.fpoid}">
								<option value="${pos.fpoid}" selected="selected">${pos.fponame}</option>
							</c:if>
							<c:if test="${pos.fpoid!=emp.position.fpoid}">
								<option value="${pos.fpoid}">${pos.fponame}</option>
							</c:if>
						</c:forEach>
					</select>
				</div></li>
			<li><label>直接上级:</label>
				<div class="vocation1"
					style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
					<select class="select11" style="width: 150px;height: 40px;"
						name="upid" disabled="disabled">
						<option value="0">无上级</option>
						<c:forEach items="${mgrList}" var="mgr">
							<c:if test="${mgr.empid==emp.mgr.empid }">
								<option value="${mgr.empid}" selected="selected">${mgr.ename}</option>
							</c:if>
							<c:if test="${mgr.empid!=emp.mgr.empid }">
								<option value="${mgr.empid}">${mgr.ename}</option>
							</c:if>
						</c:forEach>
					</select>
				</div></li>
			<li><label>联系方式:</label> <input name="phone" type="text"
				class="dfinput" value="${emp.phone}" readonly="readonly"/></li>
			<li><label>QQ号:</label> <input name="qq" type="text"
				class="dfinput" value="${emp.qq}" readonly="readonly"/></li>
			<li><label>身份证号:</label> <input name="idcard" type="text"
				class="dfinput" value="${emp.idcard }" readonly="readonly"/></li>
			<li><label>紧急联系人信息:</label> <textarea name="linkman" cols=""
					rows="" id="ecp" class="textinput" readonly="readonly">${emp.linkman }</textarea></li>
		</ul>
	</div>
</body>
</html>




