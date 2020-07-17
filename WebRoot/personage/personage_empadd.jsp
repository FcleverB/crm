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
<title>添加员工</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript">
	KE.show({
		id : "ecp",
		width : "565px",
		height : "225px",
		skinType : "tinymce",
		resizeMode : "0"
	})
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">人事管理</a></li>
			<li><a href="#">添加员工</a></li>
		</ul>
	</div>
	<form action="servlet/EmployeeServlet?method=add" method="post">
		<div class="formbody">

			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<ul class="forminfo">
				<li><label>员工编号:</label> <input name="empid" type="text"
					class="dfinput" /><i></i></li>
				<li><label>员工姓名:</label> <input name="ename" type="text"
					class="dfinput" /><i></i></li>
				<li><label>性别</label><cite> <input name="sex"
						type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="sex" type="radio" value="女" />女
				</cite></li>
				<li><label>出生日期:</label> <input name="birth" type="text"
					class="dfinput"
					onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" /></li>
				<li><label>入职时间:</label> <input name="indate" type="text"
					class="dfinput"
					onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" /><i></i></li>

				<li><label>离职时间:</label> <input name="leave" type="text"
					class="dfinput"
					onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" /><i></i></li>
				<li><label>是否在职:</label><cite> <input name="onduty"
						type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="onduty" type="radio" value="0" />否
				</cite></li>
				<li><label>员工类型</label><cite> <input name="etype"
						type="radio" value="1" checked="checked" />基层员工&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="etype" type="radio" value="2" />各级管理人员
				</cite></li>
				<li><label>所属部门:</label>
					<div class="vocation1"
						style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
						<select class="select11" style="width: 150px;height: 40px;"
							name="deptid">
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.deptid}">${dept.deptname}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>从事岗位:</label>
					<div class="vocation1"
						style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
						<select class="select11" style="width: 150px;height: 40px;"
							name="posid">
							<c:forEach items="${posList}" var="pos">
								<option value="${pos.fpoid}">${pos.fponame}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>直接上级:</label>
					<div class="vocation1"
						style="width: 100px;height: 40px;float: left;margin-bottom: 10px;">
						<select class="select11" style="width: 150px;height: 40px;"
							name="upid">
							<option value="0">无上级</option>
							<c:forEach items="${mgrList}" var="mgr">
								<option value="${mgr.empid}">${mgr.ename}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>联系方式:</label> <input name="phone" type="text"
					class="dfinput" /></li>
				<li><label>QQ号:</label> <input name="qq" type="text"
					class="dfinput" /></li>
				<li><label>身份证号:</label> <input name="idcard" type="text"
					class="dfinput" /></li>
				<li><label>紧急联系人信息:</label> <textarea name="linkman" cols=""
						rows="" id="ecp" class="textinput"></textarea></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="确认保存" /></li>
			</ul>

		</div>
	</form>
	<span>${error}</span>
</body>

</html>
