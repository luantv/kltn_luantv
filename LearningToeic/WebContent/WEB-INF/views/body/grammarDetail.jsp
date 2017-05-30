<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty lesson}">
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${lesson.name }</h1>
		<div class="row">
			<div class="col-lg-12">${lesson.content }</div>
		</div>
		<br />
		<div class="row">
			<div class="col-lg-12"><a href="${lesson.grammarid}/exercise" class="btn btn-primary">Go to Exercise</a></div>
		</div>
	</div>
</div>
<div class="row">
	<div class="panel-heading">
		<h2 style="margin: 0px">Comments</h2>
		<div class="fb-comments" data-href="http://localhost:8080${requestScope['javax.servlet.forward.request_uri']}" data-width="100%" data-numposts="9"></div>
	</div>
</div>
</c:if>
<c:if test="${empty lesson}">
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">The lesson not found</h1>
	</div>
</div>
</c:if>