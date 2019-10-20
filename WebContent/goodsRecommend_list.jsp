<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>蛋糕店- <c:choose>
		<c:when test="${typeid==2}">热销</c:when>
		<c:otherwise>新品</c:otherwise>
	</c:choose></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/simpleCart.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp" flush="true">
		<jsp:param value="3" name="flag" />
	</jsp:include>
	<!--//header-->

	<!--products-->
	<div class="products">
		<div class="container">

			<h2>
				<c:choose>
					<c:when test="${typeid==2}">热销</c:when>
					<c:otherwise>新品</c:otherwise>
				</c:choose>
			</h2>

			<div class="col-md-12 product-model-sec">

				<c:forEach items="${p.list }" var="g">
					<div class="product-grid">
						<a href="detail.action?goodid=1">
							<div class="more-product">
								<span> </span>
							</div>
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="${pageContext.request.contextPath }${g.cover }"
									class="img-responsive" alt="${g.name }" width="240"
									height="240">
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">
										<a
											href="${pageContext.request.contextPath }/goods_detail?id=${g.id }"><button>查看详情</button></a>
									</h4>
								</div>
							</div>
						</a>
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${g.name }</h4>
								<span class="item_price">¥ ${g.price }</span> <input
									type="button" class="item_add items" value="加入购物车"
									onclick="buy(${g.id })">
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>

			<!-- page分页 -->
			<div>
				<jsp:include page="page.jsp" flush="true">
					<jsp:param value="/goodsRecommend_list" name="url" />
					<jsp:param value="&typeid=${typeid}" name="param" />
				</jsp:include>
			</div>
			<!-- //page分页 -->
		</div>
	</div>
	<!--//products-->

	<!--footer-->
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
	<!--//footer-->

</body>
</html>