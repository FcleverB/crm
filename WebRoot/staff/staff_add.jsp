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
<script type="text/javascript">
	function addPhoto() {
		//获取上传图片li
		var li = $("#uploadphoto");
		//console.info(li);
		//准备添加的dom对象
		var content = $("<span><label>&nbsp;</label><input name='photo' type='file' style='height: 40px;margin-top: 5px;margin-bottom: -10px;' /><i></i>" +
			"<input type='button'  class='btn'  value='删除' style='margin-left:3px;' onclick='removeCurrSpan(this)'/></span>");
		//加入到指定位置
		li.append(content);
	}
	function removeCurrSpan(obj) {
		$(obj).parent().remove();

	}
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">员工相关</a></li>
			<li><a href="#">添加房屋信息</a></li>
		</ul>
	</div>
	<form action="servlet/HomeServlet?method=add" method="post" enctype="multipart/form-data">
		<div class="formbody">
			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<ul class="forminfo">
				<li><label>房屋地址:</label> <input name="address" type="text"
					class="dfinput" style="width: 600px;" /></li>
				<li><label>户型:</label>
					<div class="vocation">
						<select class="select1" name="tyid">
							<c:forEach items="${typeList}" var="type">
								<option value="${type.htypid}">${type.name}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>面积:</label> <input name="area" type="text"
					class="dfinput" /> <i>(M²)</i></li>
				<li><label>每平米价格:</label> <input name="price" type="text"
					class="dfinput" /> <i>(/M²)</i></li>
				<li><label>总价:</label> <input name="sprice" type="text"
					class="dfinput" /> <i>(¥)</i></li>
				<li><label>交易状态:</label>
					<div class="vocation">
						<select class="select1" name="sta">
							<c:forEach items="${staList}" var="sta">
								<option value="${sta.hstaid}">${sta.name}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>房屋环境:</label> <textarea name="envir" id="ecp" cols=""
						rows="" class="textinput"></textarea></li>
				<!-- <li><label>上传图片:</label> <input name="photo" type="file"
					style="height: 40px;margin-top: 5px;margin-bottom: -10px;" />
					<input type="button" class="btn" value="添加图片" onclick="addPhoto()" />
				</li> -->
				<li id="uploadphoto"><label>上传图片:</label> <span> <input
						name="photo" type="file"  style="height: 40px;margin-top: 5px;margin-bottom: -10px;"/><i></i> <input
						type="button" class="btn" value="添加图片" onclick="addPhoto()" />
				</span></li>
				<li><label>备注信息:</label> <textarea name="remark" id="ecp" cols=""
						rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="提交" style="border: none;" /></li>
			</ul>
		</div>
		<span style="color: red;font-weight: bold;font-size: 16px;">${error}</span>
	</form>
</body>

</html>