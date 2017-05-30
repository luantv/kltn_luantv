<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Search lesson grammar for <strong style="color:red">"${searchKey }"</strong></h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<c:if test="${delete=='true'}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Delete lesson success!
			</div>
		</c:if>
	</div>
	<div class="col-lg-12">
		<div>
        	<h4><span class="label label-success">${numGram } results found</span></h4>
        </div><br />
		<form action="searchGrammar" method="GET">
			<div class="input-group custom-search-form col-lg-5">
				<input type="text" class="form-control" placeholder="Search..." name="searchKey" />
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
				</span> <br />
			</div>
		</form>
	</div>
</div>
<br />
<c:choose>
	<c:when test="${numGram eq 0 }"></c:when>
	<c:otherwise>
<div class="row">
	<div class="col-lg-12">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Description</th>
					<th width="220px">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lesson" items="${listGrammar}" varStatus="i">
					<tr>
						<td>${i.index +1}</td>
						<td>${lesson.name }</td>
						<td>${lesson.description }</td>
						<td><div class="btn-group">
								<a href="${pageContext.request.contextPath}/admin/updateGrammar?lessonid=${lesson.grammarid}"
									class="label label-success"> Update</a>
								<a href="${pageContext.request.contextPath}/admin/manageExercise/lessonid=${lesson.grammarid}"
									class="label label-info"> Manage exercise</a> 
								<a href="${pageContext.request.contextPath}/admin/confirmDeleteGrammar?lessonid=${lesson.grammarid}"
									class="label label-danger"> Delete </a>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row text-center">
	<div class="col-lg-12">
		<ul class="pagination">
			<li><a href="searchGrammar?searchKey=${searchKey}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			<c:forEach begin="1" end="${pageGram }" var="item">
				<c:choose>
					<c:when test="${numPage eq item }">
						<li class="active"><a href="searchGrammar?searchKey=${searchKey}&numPage=${item }">${item }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="searchGrammar?searchKey=${searchKey}&numPage=${item }">${item }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="searchGrammar?searchKey=${searchKey}&numPage=${pageGram }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</div>
</div>
</c:otherwise>
</c:choose>