<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title></title>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"servlet/DutyServlet?method=findmy",
			type:"post",
			success:function(jsonStr){
				//jasonstr---json object
				eval("var arr="+jsonStr);
				//拼接考勤信息列表字符串
				var str="";
				for(var i=0;i<arr.length;i++){
					str+=
					'<tr>'+
						'<td>'+arr[i].dtDate+'</td>'+
						'<td>'+arr[i].signinTime+'</td>'+
						'<td>'+arr[i].signoutTime+'</td>'+
					'</tr>';
				}
				//一次性写入到指定位置
				$("#tbody").html(str);
			}
		});
	})
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">考勤管理</a></li>
			<li><a href="#">我的考勤</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="formtitle1">
			<span>我的考勤</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>出勤日期</th>
					<th>签到时间</th>
					<th>签退时间</th>
				</tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>

		<div class="pagin">
			<div class="message">
				共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:;"><span
						class="pagepre"></span></a></li>
				<li class="paginItem"><a href="javascript:;">1</a></li>
				<li class="paginItem current"><a href="javascript:;">2</a></li>
				<li class="paginItem"><a href="javascript:;">3</a></li>
				<li class="paginItem"><a href="javascript:;">4</a></li>
				<li class="paginItem"><a href="javascript:;">5</a></li>
				<li class="paginItem more"><a href="javascript:;">...</a></li>
				<li class="paginItem"><a href="javascript:;">10</a></li>
				<li class="paginItem"><a href="javascript:;"><span
						class="pagenxt"></span></a></li>
			</ul>
		</div>

	</div>

</body>

</html>