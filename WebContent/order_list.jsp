<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>我的订单</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp" flush="true">
		<jsp:param value="6" name="flag" />
	</jsp:include>
	<!--//header-->

	<!--cart-items-->
	<div class="cart-items">
		<div class="container">

			<h2>我的订单</h2>
			<table class="table table-bordered table-hover">
				<tr>
					<th width="7%">ID</th>
					<th width="10%">总价</th>
					<th width="20%">商品详情</th>
					<th width="20%">收货信息</th>
					<th width="10%">订单状态</th>
					<th width="10%">支付方式</th>
					<th width="10%">下单时间</th>
					<th width="13%">操作</th>
				</tr>
				<c:forEach items="${orderList }" var="o">
					<tr>
						<td><p>${o.id }</p></td>
						<td><p>${o.total }</p></td>
						<td><c:forEach items="${o.itemList }" var="item">
								<p>${item.name }(${item.price })x${item.amount }</p>
							</c:forEach>
						<td>
							<p>${o.name }</p>
							<p>${o.phone }</p>
							<p>${o.address }</p>
						</td>
						<td>
							<p>
								<c:if test="${o.status==1 }">
									<span style="color: red;">未支付</span>
								</c:if>
								<c:if test="${o.status==2 }">
									<span style="color: blue;">已支付</span>
								</c:if>
								<c:if test="${o.status==3 }">
									<span style="color: green;">配送中</span>
								</c:if>
								<c:if test="${o.status==4 }">
									<span style="color: red;">已完成</span>
								</c:if>
							</p>
						</td>
						<td><c:if test="${o.paytype==1 }">
								<p>微信</p>
							</c:if> <c:if test="${o.paytype==2 }">
								<p>支付宝</p>
							</c:if> <c:if test="${o.paytype==3 }">
								<p>货到付款</p>
							</c:if></td>
						<td><p>${o.datetime }</p></td>
						<td><a
							class="btn btn-danger <c:if test="${o.status!=4 }">disabled</c:if>"
							href="${pageContext.request.contextPath }/order_delete?id=${o.id} ">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<!--//cart-items-->

	<!--footer-->
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
	<!--//footer-->

</body>
</html>