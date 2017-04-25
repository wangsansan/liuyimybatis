<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>登录</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#link_checkCode").click(function() {
			$("#img_checkCode").attr("src", "checkCode.do?t=" + new Date());
		});
	});
</script>
</head>
<body>
	<h1 style="text-align: center">虚拟气象环境</h1>
	<div style="text-align: center">
		<p style="color: red;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
		<form action="j_spring_security_check" method="post">
			<table width="400px" height="100px" align="center" cellpadding="3">
				<tr align="left">
					<td>用户名：<input id="userName" name="j_username" value="developer"/></td>
				</tr>
				<tr align="left">
					<td>密&nbsp;&nbsp;码：<input id="password" type="password"
						name="j_password" value="123"/></td>
				</tr>
				<tr align="left">
					<td>验证码：<input name="checkCode" type="text" size="8"
						maxlength="4" /> <img src="checkCode.do" id="img_checkCode"
						align="middle"> <a href="javascript:void(0)"
						id="link_checkCode">&nbsp;看不清,换一个</a></td>
				</tr>
				<tr align="left">
					<td><input type="submit" value="登录 " /> &nbsp;&nbsp;<input
						type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

