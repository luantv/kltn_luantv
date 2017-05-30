<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../includes/meta.jsp" %>
	<title><tiles:getAsString name="title" /></title>
	<%@ include file="../includes/script-style-common.jsp" %>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top navbar-fixed-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header">
			<c:choose>
				<c:when test="${role eq 'admin' }">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">
						<img src="${pageContext.request.contextPath}/resources/img/uet.png" style="height: 150%; display: initial;"> LEARNING TOEIC
					</a>
				</c:when>
				<c:when test="${role eq 'subadmin' }">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/subadmin">
						<img src="${pageContext.request.contextPath}/resources/img/uet.png" style="height: 150%; display: initial;"> LEARNING TOEIC
					</a>
				</c:when>
				<c:otherwise>
					<a class="navbar-brand" href="${pageContext.request.contextPath}">
						<img src="${pageContext.request.contextPath}/resources/img/uet.png" style="height: 150%; display: initial;"> LEARNING TOEIC
					</a>
				</c:otherwise>
			</c:choose>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<c:choose>
				<c:when test="${user.email == null }">
					<li><a href="${pageContext.request.contextPath}/signin"><i 
						class="glyphicon glyphicon-log-in"></i> Login</a></li>
					<li><a href="${pageContext.request.contextPath}/register"><i
						class="glyphicon glyphicon-lock"></i> Register</a></li>
				</c:when>
				<c:when test="${user.email != null }">
					<li class="dropdown">
						<a aria-expanded="false" class="dropdown-toggle" role="button" data-toggle="dropdown" href="#">
							<img alt="avatar" class="img-circle" style="height: 20px;"
								src="${pageContext.request.contextPath}/resources/upload/profile/${user.image}">
								Hi, <c:out value="${user.username}" />
								<span class="caret"></span>
						</a>
						<ul id="g-account-menu" class="dropdown-menu" role="menu">
							<li><a href="${pageContext.request.contextPath}/profile/profile_info"><i class="glyphicon glyphicon-user"></i> My Profile</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/logout"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
				</c:when>
			</c:choose>
			<li><span class="label label-danger" id="time" style="font-size: 25px;"></span></li>
		</ul>
	</nav>
	<div class="navbar-default sidebar" role="navigation" style="margin-top: 52px;">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<tiles:insertAttribute name="menu" />
			</ul>
		</div>
	</div>
		
	<div id="page-wrapper">
		<div class="container-fluid">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</body>
</html>
