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
<title>添加房屋信息</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">员工相关</a></li>
			<li><a href="#">添加房屋信息</a></li>
		</ul>
	</div>
	<form action="servlet/HomeServlet?method=update&homid=${homid}" method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<ul class="forminfo">
				<li><label>房屋地址:</label> <input name="address" type="text"
					class="dfinput" style="width: 600px;" value="${home.address}"/></li>
				<li><label>户型:</label>
					<div class="vocation">
						<select class="select1" name="tyid">
							<c:forEach items="${typeList}" var="type">
								<c:if test="${type.htypid==home.htyp.htypid}">
									<option value="${type.htypid}" selected="selected">${type.name}</option>
								</c:if>
								<c:if test="${type.htypid!=home.htyp.htypid}">
									<option value="${type.htypid}">${type.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div></li>
				<li><label>面积:</label> <input name="area" type="text"
					class="dfinput" value="${home.area}"/> <i>(M²)</i></li>
				<li><label>每平米价格:</label> <input name="price" type="text"
					class="dfinput" value="${home.price}"/> <i>(/M²)</i></li>
				<li><label>总价:</label> <input name="sprice" type="text"
					class="dfinput" value="${home.sprice}"/> <i>(¥)</i></li>
				<li><label>交易状态:</label>
					<div class="vocation">
						<select class="select1" name="sta">
							<c:forEach items="${staList}" var="sta">
								<c:if test="${sta.hstaid==home.sta.hstaid}">
									<option value="${sta.hstaid}" selected="selected">${sta.name}</option>
								</c:if>
								<c:if test="${sta.hstaid!=home.sta.hstaid}">
									<option value="${sta.hstaid}">${sta.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div></li>
				<li><label>房屋环境:</label> <textarea name="envir" id="ecp"
						cols="" rows="" class="textinput">${home.envir}</textarea></li>
				<li><label>上传图片:</label> <input name="" type="file"
					style="height: 40px;margin-top: 5px;margin-bottom: -10px;" /></li>
				<li><label>备注信息:</label> <textarea name="remark" id="ecp"
						cols="" rows="" class="textinput">${home.remark}</textarea></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="提交" style="border: none;" /></li>
			</ul>
		</div>
	</form>
</body>

</html>