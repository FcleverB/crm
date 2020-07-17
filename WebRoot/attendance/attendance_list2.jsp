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
<title></title>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function change(size,index){
		location.href="servlet/DutyServlet?method=findDuty&size="+size+"&index="+index;
	}
	$(function(){
		//通过AJax获取所有的部门列表并输出
		$.ajax({
		url:"servlet/DutyServlet?method=findAllDept",
		type:"POST",
		success:function(jsonStr){//输出所有的部门列表
			//jsontstr---json object
			eval("var arr = "+ jsonStr);
			//拼接所有的option字符串
			var str = '<option value="0">--全部--</option>';
			for(var i=0;i<arr.length;i++){
			 	str+='<option value="'+arr[i].deptid+'">'+arr[i].deptname+'</option>';
			}
			//一次性写入到select deptno列表中
			$("#deptno").html(str);
		}
		
		});
		//给查询按钮绑定单击事件
		$("#btn1").click(function(){
			//获取三个查询条件的值
			var empid=$("#empid").val();
			var deptno=$("#deptno").val();
			var dtDate=$("#deDate").val();
			//通过ajax获取请求得到数据并通过回调函数输出
			$.ajax({
				url:"servlet/DutyServlet?method=findDuty",
				type:"post",
				data:{"empid":empid,"deptno":deptno,"dtDate":dtDate},
				success:function(result){
					//jasonstr---json object
					eval("var arr="+result);
					//拼接考勤信息列表字符串
					var str="";
					for(var i=0;i<arr.length;i++){
						str+=
						'<tr>'+
							'<td>'+arr[i].emp.empid+'</td>'+
							'<td>'+arr[i].emp.ename+'</td>'+
							'<td>'+arr[i].emp.dept.deptname+'</td>'+
							'<td>'+arr[i].dtDate+'</td>'+
							'<td>'+arr[i].signinTime+'</td>'+
							'<td>'+arr[i].signoutTime+'</td>'+
						'</tr>';
					}
					//一次性写入到指定位置
					$("#tbody").html(str);
				}
			});
		});
		//给导出按钮绑定单击事件
		$("#btn2").click(function(){
			//获取三个查询条件的值
			var empid=$("#empid").val();
			var deptno=$("#deptno").val();
			var dtDate=$("#dtDate").val();
			//访问指定的Servlet,不适用Ajax(因为Ajax是通过回调函数处理结果的,导出XLS)
			location.href="servlet/DutyServlet?method=exportXls&empid="+empid+"&deptno="+deptno+"&dtDate="+dtDate+"";
		});
	});
	
</script>
</head>

<body onload="click()">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">考勤管理</a></li>
			<li><a href="#">考勤管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<ul class="prosearch">
			<li><label class="duty">查询：</label><i>用户名</i> <a> <input
					id="empid" name="" type="text" class="scinput" />
			</a><i>所属部门</i> <a> <select class="select2"
					style="width: 150px;height: 30px;" id="deptno">
				</select>
			</a> <i>考勤时间</i> <a> <input id="deDate" name="" type="text" class="scinput" />
			</a> <a> <input name="" type="button" id="btn1" class="sure" value="查询" />

			</a> <a> <input name="" type="button" id="btn2" class="scbtn2" value="导出" />

			</a></li>

		</ul>

		<div class="formtitle1">
			<span>考勤管理</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>所属部门</th>
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