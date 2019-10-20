<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style='text-align: center;'>
	<a class='btn btn-info' <c:if test="${p.pageNo==1 }">disabled</c:if>
		<c:if test="${p.pageNo!=1 }">href="${pageContext.request.contextPath }${param.url }?pageNo=1${param.param }"</c:if>>
		首页</a> <a class='btn btn-info'
		<c:if test="${p.pageNo==1 }">disabled</c:if>
		<c:if test="${p.pageNo!=1 }">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo-1 }${param.param }"</c:if>>
		上一页</a>
	<h2 style='display: inline;'>[${p.pageNo }/${p.tatolPage }]</h2>
	<h2 style='display: inline;'>[${p.totalCount }]</h2>
	<a class='btn btn-info'
		<c:if test="${p.tatolPage==0 || p.pageNo==p.tatolPage}">disabled</c:if>
		<c:if test="${p.pageNo!=p.tatolPage}">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo+1 }${param.param }"</c:if>>
		下一页</a> <a class='btn btn-info'
		<c:if test="${p.tatolPage==0 || p.pageNo==p.tatolPage}">disabled</c:if>
		<c:if test="${p.pageNo!=p.tatolPage}">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.tatolPage }${param.param }"</c:if>>
		尾页</a> <input type='text' class='form-control'
		style='display: inline; width: 60px;'/> <a
		class='btn btn-info' href='javascript:void(0);'
		onclick='location.href="${pageContext.request.contextPath }/${param.url }?pageNo="+(previousSibling.previousSibling.value)+"${param.param }"'>GO</a>
</div>