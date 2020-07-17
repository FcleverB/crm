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
<title>客户分布图</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/china.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"servlet/ClientServlet?method=getBarData",
			success:function(data){
				var datas=data;
				//基于准备好的dom,初始化Echarts实例
				var myChart = echarts.init(document.getElementById('main'));
				// 指定图表的配置项和数据
				option = {
					title : {
						text : '客户全国分布图',
						left : 'center'
					},
					tooltip : {
						trigger : 'item'
					},
					legend : {
						orient : 'vertical',
						left : 'left'
					},
					visualMap : {
						min : 0,
						max : 10,
						left : 'left',
						top : 'bottom',
						text : [ '高', '低' ], // 文本，默认为数值文本
						calculable : true
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						left : 'right',
						top : 'center',
						feature : {
							dataView : {
								readOnly : false
							},
							restore : {},
							saveAsImage : {}
						}
					},
					series : [ {
						type : 'map',
						mapType : 'china',
						roam : false,
						label : {
							normal : {
								show : true
							},
							emphasis : {
								show : true
							}
						},
						data : [ 
							{
								name : '北京市',
								value : datas["北京市"]
							},
							{
								name : '天津市',
								value : datas["天津市"]
							},
							{
								name : '上海市',
								value : datas["上海市"]
							},
							{
								name : '重庆市',
								value : datas["重庆市"]
							},
							{
								name : '河北省',
								value : datas["河北省"]
							},
							{
								name : '河南省',
								value : datas["河南省"]
							},
							{
								name : '云南省',
								value : datas["云南省"]
							},
							{
								name : '辽宁省',
								value : datas["辽宁省"]
							},
							{
								name : '黑龙江省',
								value : datas["黑龙江省"]
							},
							{
								name : '湖南省',
								value : datas["湖南省"]
							},
							{
								name : '安徽省',
								value : datas["安徽省"]
							},
							{
								name : '山东省',
								value : datas["山东省"]
							},
							{
								name : '新疆维吾尔自治区',
								value : datas["新疆维吾尔自治区"]
							},
							{
								name : '江苏省',
								value : datas["江苏省"]
							},
							{
								name : '浙江省',
								value : datas["浙江省"]
							},
							{
								name : '江西省',
								value : datas[0]
							},
							{
								name : '湖北省',
								value : datas["湖北省"]
							},
							{
								name : '广西省',
								value : datas["广西省"]
							},
							{
								name : '甘肃省',
								value :datas["甘肃省"]
							},
							{
								name : '山西省',
								value :datas["山西省"]
							},
							{
								name : '内蒙古自治区',
								value : datas["内蒙古自治区"]
							},
							{
								name : '陕西省',
								value :datas["陕西省"]
							},
							{
								name : '吉林省',
								value :datas["吉林省"]
							},
							{
								name : '福建省',
								value : datas["福建省"]
							},
							{
								name : '贵州省',
								value : datas["贵州省"]
							},
							{
								name : '广东省',
								value : datas["广东省"]
							},
							{
								name : '青海省',
								value :datas["青海省"]
							},
							{
								name : '西藏自治区',
								value : datas["西藏自治区"]
							},
							{
								name : '四川省',
								value :datas["四川省"]
							},
							{
								name : '宁夏回族自治区',
								value :datas["宁夏回族自治区"]
							},
							{
								name : '海南省',
								value :datas["海南省"]
							},
							{
								name : '台湾省',
								value : datas["台湾省"]
							},
							{
								name : '香港',
								value : datas["香港"]
							},
							{
								name : '澳门',
								value :datas["澳门"]
							}
						]
					}
			
					]
				};
			
				myChart.setOption(option);
			}
		})
	})
</script>
</head>
<body>
	<!--为Echarts准备一个具备大小(宽高的DOM)-->
	<div id="main" style="width: 1200px;height: 700px;"></div>
</body>

</html>
