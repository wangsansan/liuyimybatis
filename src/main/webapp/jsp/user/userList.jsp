<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
<title>Show List - S2SMDemo</title>
<script type="text/javascript">
	var totalPageNums = '<s:property value="user.TotalPage"/>';
	
	function goFirstPage(pageNum){
		var url = "userAction!getUserAll.do";
		window.location.href= url;
	}
	
	function goUpPage(pageNum){
		if((pageNum-1) < 1){
			alert("提示:已经是第一页");
			return false;			
		}
		var url = "userAction!getUserAll.do?page="+(pageNum-1);
		window.location.href= url;
	}
	
	function goDownPage(pageNum){
		if((pageNum+1) > totalPageNums){
			alert("提示:已经是最后一页");
			return false;
		}
		var url = "userAction!getUserAll.do?page="+(pageNum+1);
		window.location.href= url;
	}
	
	function goLastPage(pageNum){
		var url = "userAction!getUserAll.do?page="+totalPageNums;
		window.location.href= url;
	}
	
	function editUser(userId){
		var url = "userAction!editUser.do?id="+userId;
		window.location.href= url;
	}
	
	function delUser(userId){
		var url = "userAction!delUser.do?id="+userId;
		window.location.href= url;
	}
</script>
</head>
<body>
<div id="optStyle">
	<a href="userAction!addUser.do">新增用户</a>
	<a href="userAction.do">返回首页</a>
</div>

<table border="1">
	<tr>
		<th>ID</th>
		<th>账号</th>
		<th>密码</th>
		<th>邮箱</th>
		<th>注册时间</th>
		<th>所属角色</th>
		<th>操作</th>
	</tr>
	<s:iterator value="userList" id="item">
	<tr>
		<td><s:property value="#item.id"/></td>
		<td><s:property value="#item.loginName"/></td>
		<td><s:property value="#item.password"/></td>
		<td><s:property value="#item.email"/></td>
		<td><s:property value="#item.registTime"/></td>
		<td><s:property value="#item.cname"/></td>
		<td>
			<a href="javascript:none;" onclick="editUser(<s:property value="#item.id"/>);">编辑</a>
			<a href="javascript:none;" onclick="delUser(<s:property value="#item.id"/>);">删除</a>
		</td>
	</tr>
	</s:iterator>
	
</table>

<div id="pageStyle">
	<a href="javascript:none;" onclick="goFirstPage(<s:property value="user.page"/>);">首页</a>
	<a href="javascript:none;" onclick="goUpPage(<s:property value="user.page"/>);">上一页</a>
	<a href="javascript:none;" onclick="goDownPage(<s:property value="user.page"/>);">下一页</a>
	<a href="javascript:none;" onclick="goLastPage('最后一页');">最后一页</a>
</div>

</body>
</html>