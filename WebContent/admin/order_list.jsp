<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单列表</title>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<div class="container-fluid">

		<!-- header -->
		<jsp:include page="/admin/header.jsp" flush="true"></jsp:include>
		<!--//header  -->

		<br>

		<ul role="tablist" class="nav nav-tabs">
			<li <c:if test="${status==0 }">class="active"</c:if>
				role="presentation"><a
				href="${pageContext.request.contextPath }/admin/order_list">全部订单</a></li>
			<li <c:if test="${status==1 }">class="active"</c:if>
				role="presentation"><a
				href="${pageContext.request.contextPath }/admin/order_list?status=1">未付款</a></li>
			<li <c:if test="${status==2 }">class="active"</c:if>
				role="presentation"><a
				href="${pageContext.request.contextPath }/admin/order_list?status=2">已付款</a></li>
			<li <c:if test="${status==3 }">class="active"</c:if>
				role="presentation"><a
				href="${pageContext.request.contextPath }/admin/order_list?status=3">配送中</a></li>
			<li <c:if test="${status==4 }">class="active"</c:if>
				role="presentation"><a
				href="${pageContext.request.contextPath }/admin/order_list?status=4">已完成</a></li>
		</ul>

		<br>

		<table class="table table-bordered table-hover">

			<tr>
				<th width="5%">ID</th>
				<th width="5%">总价</th>
				<th width="15%">商品详情</th>
				<th width="20%">收货信息</th>
				<th width="10%">订单状态</th>
				<th width="10%">支付方式</th>
				<th width="10%">下单用户</th>
				<th width="10%">下单时间</th>
				<th width="10%">操作</th>
			</tr>

			<c:forEach items="${p.list }" var="o">
				<tr>
					<td><p>${o.id }</p></td>
					<td><p>${o.total }</p></td>
					<td><c:forEach items="${o.itemList }" var="item">
							<p>${item.name }(${item.price })x${item.amount }</p>
						</c:forEach></td>
					<td>
						<p>收件人：${o.name }</p>
						<p>联系电话：${o.phone }</p>
						<p>收货地址：${o.address }</p>
					</td>
					<td>
						<p>
							<c:if test="${o.status==1 }">
								<span style="color: red;">未付款</span>
							</c:if>
							<c:if test="${o.status==2 }">
								<span style="color: blue;">已付款</span>
							</c:if>
							<c:if test="${o.status==3 }">
								<span style="color: green;">配送中</span>
							</c:if>
							<c:if test="${o.status==4 }">
								<span style="color: black;">已完成</span>
							</c:if>
						</p>
					</td>
					<td>
						<p>
							<c:if test="${o.paytype==1 }">微信</c:if>
							<c:if test="${o.paytype==2 }">支付宝</c:if>
							<c:if test="${o.paytype==3 }">货到付款</c:if>
						</p>
					</td>
					<td>
						<p>${o.user.username }</p>
					</td>
					<td><p>${o.datetime }</p></td>
					<td>
						<c:if test="${o.status==2 }">
							<a class="btn btn-success"
								href="${pageContext.request.contextPath }/admin/order_status?id=${o.id }&status=3">发货</a>
						</c:if> <c:if test="${o.status==3 }">
							<a class="btn btn-warning"
								href="${pageContext.request.contextPath }/admin/order_status?id=${o.id }&status=4">完成</a>
						</c:if> <a class="btn btn-danger"
						href="${pageContext.request.contextPath }/admin/order_delete?id=${o.id }&status=${o.status}&pageNo=${p.pageNo }">删除</a></td>
				</tr>
			</c:forEach>

		</table>

		<br>
		<!-- page分页 -->
		<div>
			<jsp:include page="../page.jsp" flush="true">
				<jsp:param value="/admin/order_list" name="url" />
				<jsp:param value="&status=${status }" name="param" />
			</jsp:include>
		</div>
		<!-- //page分页 -->
		<br>
	</div>
</body>
</html>