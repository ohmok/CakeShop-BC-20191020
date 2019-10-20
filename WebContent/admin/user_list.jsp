<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<div class="container-fluid">

		<!-- header -->
		<jsp:include page="/admin/header.jsp" flush="true"></jsp:include>
		<!--//header  -->

		<c:if test="${!empty msg }">
			<div class="alert alert-success">${msg }</div>
		</c:if>
		<c:if test="${!empty failMsg }">
			<div class="alert alert-danger">${failMsg }</div>
		</c:if>

		<div class="text-right">
			<a class="btn btn-warning"
				href="${pageContext.request.contextPath }/admin/user_add.jsp">添加客户</a>
		</div>

		<br>

		<table class="table table-bordered table-hover">

			<tr>
				<th width="3%">ID</th>
				<th width="10%">用户名</th>
				<th width="10%">邮箱</th>
				<th width="10%">收件人</th>
				<th width="10%">电话</th>
				<th width="10%">地址</th>
				<th width="12%">操作</th>
			</tr>
			<c:forEach items="${p.list }" var="u">
				<c:if test="${u.id!=1 }">
					<tr>
						<td><p>${u.id }</p></td>
						<td><p>${u.username }</p></td>
						<td><p>${u.email }</p></td>
						<td><p>${u.name }</p></td>
						<td><p>${u.phone }</p></td>
						<td><p>${u.address }</p></td>
						<td><a class="btn btn-info"
							href="${pageContext.request.contextPath }/admin/user_reset.jsp?id=${u.id }&username=${u.username}&email=${u.email}">重置密码</a>
							<a class="btn btn-primary"
							href="${pageContext.request.contextPath }/admin/user_editshow?id=${u.id}">修改</a>
							<a class="btn btn-danger"
							href="${pageContext.request.contextPath }/admin/user_delete?id=${u.id}">删除</a></td>
					</tr>
				</c:if>
			</c:forEach>

		</table>

		<br>
		<!-- page分页 -->
		<div>
			<jsp:include page="../page.jsp" flush="true">
				<jsp:param value="/admin/user_list" name="url" />
			</jsp:include>
		</div>
		<!-- //page分页 -->
		<br>
	</div>
</body>
</html>