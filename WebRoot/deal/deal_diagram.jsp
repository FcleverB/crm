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
<title>收入分段图</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"servlet/PaymentServlet?method=getBarData",
			success:function(data){
				//json string----json object
				eval("var arr="+data);
				//基于准备好的dom,初始化Echarts实例
				var myChart = echarts.init(document.getElementById('main'));
				var option = {
					title : {
						text : '西游地产收入统计图表',
						left : 'center'
					},
					tooltip : {
		      		 	trigger: 'axis',
		        		axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            		type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        		}
		    		},
					xAxis : {
						type : 'category',
						data : arr[0]
					},
					yAxis : {
						min:0,
						max:10,
						splitNumber:5,
						type : 'value'
					},
					series : [ {
						name:'销售个数',
						barWidth: '60%',
						data :arr[1],
						type : 'bar'
					} ]
				};
				myChart.setOption(option);
			}
		});
	});
</script>
</head>
<body>
	<div id="main" style="width: 1200px;height: 700px;"></div>
</body>
</html>

