<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>客户添加</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<div class="container-fluid">

		<!-- header -->
		<jsp:include page="/admin/header.jsp" flush="true"></jsp:include>
		<!--//header  -->

		<c:if test="${!empty failMsg }">
			<div class="alert alert-danger">${failMsg }</div>
		</c:if>
		
		<br> <br>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath }/admin/user_add"
			method="post">
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">用户名</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name"
						name="username" value="${u.username }" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">密码</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name"
						name="password" value="${u.password }" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">邮箱</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name"
						name="email" value="${u.email }" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">姓名</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name" name="name"
						value="${u.name }">
				</div>
			</div>
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">电话</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name"
						name="phone" value="${u.phone }">
				</div>
			</div>
			<div class="form-group">
				<label for="input_name" class="col-sm-1 control-label">地址</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="input_name"
						name="address" value="${u.address }">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="submit" class="btn btn-success">提交保存</button>
				</div>
			</div>
		</form>

		<span style="color: red;"></span>
	</div>
</body>
</html>