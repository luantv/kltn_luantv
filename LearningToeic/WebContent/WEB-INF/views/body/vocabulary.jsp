<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<style>
.img-hover:hover {
	opacity: 0.6;
}
.panel:hover {
	border: 1px solid #1481e0;
}
</style>
<body>
	<div class="row">
		<div class="col-lg-12">
		<br/>
			<h1 class="page-header">Essential words for the TOEIC</h1>
		</div>
	</div>
	<div class="row">
		<div class="alert alert-danger  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			This site teaches you vocabulary that will help you when you take the
			TOEIC ( Test of English for International Communication). The TOEIC
			measures that English proficiency of people working in international
			business or planning to use English to communicate with others.
			<br /> This site contains 50 lessons and each lesson introduces to 12
			new words in a specific context. If you study one lesson every day, 
			in 50 days you can learn 600 new words.
		</div>
		<!-- /.row -->
			<form action="searchVocabulary" method="GET">
				<div class="input-group custom-search-form col-lg-6">
					<input type="text" class="form-control" placeholder="Search..." name="searchKey" />
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
					</span>
					<br /> 
				</div>
			</form>
		</div>
	<br />
	<div class="row">
		<c:forEach var="vocabulary" items="${listVocabularys }">
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<a href="vocabulary/${vocabulary.vocabularyid }">
							<img class="img-responsive img-hover img-related" style="width: 100%; height: 200px"
								src="${pageContext.request.contextPath}/resources/upload/vocabulary/${vocabulary.image }" />
						</a>
					</div>
					<div class="list-group">
						<h5><strong>${vocabulary.vocabularyname }</strong></h5>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row text-center">
		<div class="col-md-12 column">
			<nav>
				<ul class="pagination">
					<li><a href="vocabulary" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					<c:forEach begin="1" end="${pageVocab }" var="item">
						<c:choose>
							<c:when test="${numPage eq item }">
								<li class="active"><a href="vocabulary?numPage=${item }">${item }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="vocabulary?numPage=${item }">${item }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a href="vocabulary?numPage=${pageVocab }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>