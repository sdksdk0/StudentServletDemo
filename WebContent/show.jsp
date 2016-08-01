<%@page import="cn.tf.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="cn.tf.dao.StudentDao"%>
<%@page import="cn.tf.utils.PageUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta  charset="UTF-8">
<title>学生信息</title>
</head>
<style>
	table tr td img{
		width:200px;
		height:150px;
	}

</style>
<body>

	<c:if test="${empty  currentLogin }" >
		<script>
			location.href="login.html";
		
		</script>
	
	</c:if>
	<c:if  test="${not empty  currentLogin }">


<fieldset>
		<legend>学生信息浏览</legend>
		<table border="1px" cellspacing="0px" cellpaddig="0px" width="90%"  align="center"  style="border-collapse:collapse;"  >
			<thead>
				<tr>
					<th>图片</th>
					<th>学号</th>
					<th>姓名</th>
					<th>年龄</th>
					<th>联系方式</th>
					<th>所在班级</th>
				</tr>
			
			</thead>
			<tbody id="show_student"  align="center"  >
		
			<c:forEach  items="${studentInfo }"  var="item" >
				<tr>
					<td>${item.photo }</td>
					<td>${item.sid }</td>
					<td>${item.snameStr}</td>
					<td>${item.age }</td>
					<td>${item.tel }</td>
					<td>${item.cname }</td>
				</tr>
			</c:forEach>	
			
			
			
			</tbody>
		
		</table> 
		
	</fieldset>
	
	<center><p>
		
			<a href="dopage.jsp?op=1">首页</a>
			<a href="dopage.jsp?op=2">上一页</a>
			<a href="dopage.jsp?op=3">下一页</a>
			<a href="dopage.jsp?op=4">尾页</a>
			<span>当前第${pageUtil.pageNo }页/共${pageUtil.totalPage }页 &nbsp;&nbsp;每页${pageUtil.pageSize }条/总${pageUtil.totalSize }条</span>
		</p>
		</center>
	</c:if>
</body>
</html>