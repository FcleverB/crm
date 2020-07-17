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
<title>添加交易单</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
			<li><a href="#">交易管理</a></li>
			<li><a href="#">添加交易单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form
			action="servlet/BarginServlet?method=add&empid=${sessionScope.emp.empid}"
			method="post">
			<ul class="forminfo">
				<li><label>创建人:</label> <input name="ename" type="text"
					class="dfinput" readonly="readonly"
					value="${sessionScope.emp.ename}" /></li>
				<li><label>客户姓名:</label> <select class="select1" name="cliname">
						<c:forEach items="${cliList}" var="cli">
							<option value="${cli.cliid}">${cli.cname}</option>
						</c:forEach>
				</select></li>
				<li><label>房屋编号:</label> <select class="select1" name="homeid">
						<c:forEach items="${homList}" var="hom">
							<option value="${hom.homid}">${hom.homid}</option>
						</c:forEach>
				</select></li>
				<li><label>成交价格:</label> <input name="price" type="text"
					class="dfinput" /></li>
				<li><label>成交日期:</label> <input name="bardate" type="text"
					class="dfinput" onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" /></li>
				<li><label>下个审核人:</label> <input name="nextemp" type="text"
					class="dfinput" value="${sessionScope.emp.mgr.empid}"
					readonly="readonly" /></li>
				<li><label>备注:</label> <textarea name="remark" id="ecp" cols=""
						rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="提交" style="border: none;" /></li>
			</ul>
			${error}
		</form>
	</div>
</body>

</html>
