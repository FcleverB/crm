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
<script type="text/javascript">
	KE.show({
		id : "ecp",
		width : "565px",
		height : "225px",
		skinType : "tinymce",
		resizeMode : "0"
	})
</script>
<script type="text/javascript">
	//页面加载成功请求省的信息
	$(function(){
   		//发起ajax请求,请求所有的省信息
   			getData(0,"pre");
   		//页面加载成功给省下拉框添加onchange事件
   		$("#pre").change(function(){
   			//获取当前选择省的areaid
   			var areaid=$("#pre").val();
   			getData(areaid,"city");
   		})
   		//页面加载成功给市下拉框添加onchange事件
   		$("#city").change(function(){
   			//获取当前选择市的areaid
   			var areaid=$("#city").val();
   			getData(areaid,"town");
   		})
   		//封装公共方法
   		function getData(areaid,sid){
   			//发起ajax请求,请求当前省所有的市信息
   			$.get("area",{parentid:areaid},function(data){
   				//使用eval方法将数据转换为js对象
    				eval("var areas="+data);
    			//将响应市数据填充到市下拉框中
    				//获取下拉框对象
    				var sel=$("#"+sid);
    				//清空原有内容
    				sel.empty();
    				//遍历
    				for(var i=0;i<areas.length;i++){
    					//填充
    					sel.append("<option value='"+areas[i].areaid+"'>"+areas[i].areaname+"</option>");
    				}
    				//触发市下拉框的change事件
    				$("#"+sid).trigger("change");
   			})
   		}
    })
</script>
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
					class="dfinput" /><i></i></li>
				<li><label>客户姓名:</label> <input name="cname" type="text"
					class="dfinput" /></li>
				<li><label>性别:</label> <cite> <input name="sex"
						type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="sex" type="radio" value="女" />女&nbsp;&nbsp;&nbsp;&nbsp;
				</cite></li>
				<li><label>客户状态:</label>
					<div class="vocation">
						<select class="select1" name="staid">
							<c:forEach items="${staList}" var="sta">
								<option value="${sta.staid}">${sta.sname}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户类型:</label>
					<div class="vocation">
						<select class="select1" name="tyid">
							<c:forEach items="${typeList}" var="type">
								<option value="${type.typid}">${type.cname}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户来源:</label>
					<div class="vocation">
						<select class="select1" name="srcid">
							<c:forEach items="${sourList}" var="sour">
								<option value="${sour.sourid}">${sour.sname}</option>
							</c:forEach>
						</select>
					</div></li>
				<li><label>客户公司:</label> <input name="comp" type="text"
					class="dfinput" /></li>
				<li><label>客户职位:</label> <input name="job" type="text"
					class="dfinput" /></li>
				<li><label>出生日期:</label> <input name="birth" type="text"
					class="dfinput"  onfocus="WdatePicker({skin:'whyGreen',lang:'zh-cn'})" /></li>
				<li><label>客户地址:</label>
					<div
						style="margin-left: 50px;width: 750px;margin-top: 15px;font-size: 16px;">
						省: 
						<select name="pre" id="pre" style="width: 100px;height: 30px"></select>
						市: 
						<select name="city" id="city" style="width: 100px;height: 30px"></select>
						区/县: 
						<select name="town" id="town" style="width: 100px;height: 30px"></select>
					</div></li>
				<li><label>客户手机:</label> <input name="phone" type="text"
					class="dfinput" /></li>
				<li><label>QQ:</label> <input name="qq" type="text"
					class="dfinput" /></li>
				<li><label>邮箱:</label> <input name="email" type="text"
					class="dfinput" /></li>
				<li><label>备注:</label> <textarea name="remark" id="ecp" cols=""
						rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label> <input name="" type="submit"
					class="btn" value="确认保存" style="border: none;" /></li>
			</ul>
		</div>
	</form>
	<span>${error}</span>
</body>

</html>