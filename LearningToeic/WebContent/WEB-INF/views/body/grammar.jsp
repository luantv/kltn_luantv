<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<style type="text/css">
.panel-body {
	min-height: 100px;
}
.panel:hover {
	border: 1px solid #1481e0;
}
</style>
<body>
	<div class="row">
		<div class="col-lg-12">
			<br />
			<h1 class="page-header">TOEIC Grammar</h1>
		</div>
	</div>
	<div class="row">
		<div class="alert alert-danger  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			This grammar by well-known author Dave Willis describes the ways that
			speakers of LEARNING TOEIC make sentences from sets of words.
		</div>
		<div  class="col-lg-12">
			<form action="searchGrammar" method="GET">
				<div class="input-group custom-search-form col-lg-5">
					<input type="text" class="form-control" placeholder="Search..." name="searchKey" />
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
					</span>
					<br /> 
				</div>
			</form>
		</div>
	</div><br />
	<div class="row">
		<c:forEach var="lesson" items="${listGrammar }">
			<div class="col-lg-6">
				<div class="panel panel-info">
					<div class="panel-heading">
						<strong><a href="grammar/${lesson.grammarid }">${lesson.name }</a></strong>
					</div>
					<div class="panel-body">
						<p>${lesson.description }</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row text-center">
		<div class="col-md-12 column">
			<nav>
				<ul class="pagination">
					<li><a href=grammar aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					<c:forEach begin="1" end="${pageGram }" var="item">
						<c:choose>
							<c:when test="${numPage eq item }">
								<li class="active"><a href="grammar?numPage=${item }">${item }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="grammar?numPage=${item }">${item }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a href="grammar?numPage=${pageGram }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>