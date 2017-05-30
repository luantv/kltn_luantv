<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${vocabulary.vocabularyname }</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<video controls style="width: 100%;"> <source
			src="${pageContext.request.contextPath}/resources/upload/vocabulary/${vocabulary.video}"
			type="video/mp4"></video>
		<br />
		<h4><a href="${pageContext.request.contextPath}/resources/upload/vocabulary/${vocabulary.document }" target="_blank">Download document</a></h4>
	</div>
</div>
<div class="row">
	<div class="panel-heading">
		<h2 style="margin: 0px">Comments</h2>
		<div class="fb-comments" data-href="http://localhost:8080${requestScope['javax.servlet.forward.request_uri']}" data-width="100%" data-numposts="9"></div>
	</div>
</div>