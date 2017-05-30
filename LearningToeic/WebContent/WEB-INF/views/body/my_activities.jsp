<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="page-header">My Activities</h1>
<div class="row placeholders">
	<ul class="list-group">
		<c:choose>
			<c:when test="${numactivity eq 0 }">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Sorry!</strong> No Activity yet!
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${activities }" var="item">
					<li class="list-group-item"><span class="badge label label-primary">At: ${item.createDate }</span>
					<strong>${item.content }</strong></li>
				</c:forEach>
	</ul>
</div>
<div class="row text-center">
	<div class="col-md-12 column">
		<nav>
		<ul class="pagination">
			<li><a href="my_activities" aria-label="Previous"><span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach begin="1" end="${pageActivity }" var="item">
				<c:choose>
					<c:when test="${numPage eq item }">
						<li class="active"><a href="my_activities?numPage=${item }">${item }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="my_activities?numPage=${item }">${item }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="my_activities?numPage=${pageActivity }"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
		</nav>
	</div>
</div>
	</c:otherwise>
</c:choose>