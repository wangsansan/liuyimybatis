<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="null != user.id">Edit</s:if><s:else>Add</s:else> - S2SMDemo</title>
</head>
<body>
	<h1><s:if test="null != user.id">Edit</s:if><s:else>Add</s:else></h1>
	<form action="userAction!saveOrUpdate.do" method="post">
		<input type="hidden" name="id" value="<s:property value="user.id"/>">
		<table border="1">
			<tr>
				<td>用户名称</td>
				<td><input name="name" value="<s:property value="user.name"/>"></td>
			</tr>
			<tr>
				<td>登录账号</td>
				<td><input name="loginName" value="<s:property value="user.loginName"/>"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input name="password" value="<s:property value="user.email"/>"></td>
			</tr>
			<tr>
				<td>所属角色<s:property value="user.categoryId"/></td>
				<td>
					<select name="categoryId">
						<s:iterator value="categoryList" var="item">
							<option value="<s:property value="#item.id"/>"
							<s:if test="user.categoryId == #item.id">selected="selected"</s:if>><s:property value="#item.name"/> </option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>电子邮件</td>
				<td><input name="email" value="<s:property value="user.email"/>"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit">
					<input type="Reset" value="Reset">
				</td>
			</tr>
		</table>
	
	</form>
	
</body>
</html>