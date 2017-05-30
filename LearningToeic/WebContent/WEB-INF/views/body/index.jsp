<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${intro.name }</h1>
	</div>
</div>
<div class="row col-lg-12">
	<c:if test="${param.login=='true'}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Error!</strong> Please login
		</div>
	</c:if>
	<c:if test="${param.activateSuccess=='true'}">
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Info!</strong> Activate your account success! 
		</div>
	</c:if>
	<c:if test="${param.registerSuccess=='true'}">
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Info!</strong> Register success! Please check mail to
			activate your account.
		</div>
	</c:if>
</div>
<div class="row">
	<div class="col-lg-12">${intro.content }</div>
</div>