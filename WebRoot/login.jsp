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
<title>欢迎使用客户关系管理系统</title>
<link rel="stylesheet" href="css/crm.css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
	function changeRandom(){
		//获取图片
		//修改图片地址
		$("#randImg").attr("src","random.jpg?time="+new Date().toLocaleString());
	}
</script>
</head>
<body>
	<div class="logincenter">
		<img class="logo" src="images/logo.png" />
		<div class="logincenter_box">
			<form action="servlet/EmployeeServlet?method=login" method="post">
				<ul>
					<li>
						<div class="u">
							<img class="userimg" src="images/user.png" /> <input name="empid"
								type="text" class="loginuser" value=""
								onclick="JavaScript:this.value=''" /><br />
						</div>
						
					</li>
					<li>
						<div class="p">
							<img class="pwdimg" src="images/pwd.png" /> <input name="pwd"
								type="password" class="loginpwd" value=""
								onclick="JavaScript:this.value=''" /><br />
						</div>
					</li>
					<li>
						<div class="y">
							<input type="text" name="verifyCode" class="loginyzm" style="font-size:16px;"/> <cite> <img
								class="yzmimg" alt="" src="random.jpg" id="randImg" onclick="changeRandom()"/>
							</cite>
						</div>
					</li>
					<span style="position: absolute;margin-left: 150px;color: red;">${error}</span>
					<li><input name="" type="submit" class="loginbtn" value="登录" /> <label>
							<input name="" type="checkbox" value="" checked="checked" />&nbsp;记住密码
					</label> <label> <a>&nbsp;&nbsp;忘记密码</a>
					</label></li>
				</ul>
			</form>
		</div>
	</div>

	<div class="loginbuttom">
		<span id="loginbuttom_buttom">©1998-2018&nbsp;&nbsp;&nbsp;FC软件股份有限公司&nbsp;版权所有&nbsp;联系电话:10086</span>
	</div>
</body>
</html>

