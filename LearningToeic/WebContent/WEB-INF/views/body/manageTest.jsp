<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Manage Test</h1>
	</div>
</div>
<div class="row">
<div class="col-lg-12">
	<c:if test="${createTestSuccess eq true}">
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Info!</strong> Create new test success!
		</div>
	</c:if>
	<c:if test="${errorName eq true}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Sorry!</strong> The test name already exits!
		</div>
	</c:if>
	<c:if test="${delete eq true}">
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Info!</strong> Delete test success!
		</div>
	</c:if>
</div>

<div class="col-lg-12">
	<a href="${pageContext.request.contextPath}/admin/createTest"
		class="btn btn-primary">Create new Test</a><br /><br />
	<form action="searchTest" method="GET">
			<div class="input-group custom-search-form col-lg-6">
				<input type="text" class="form-control" placeholder="Search..." name="searchKey" /">  
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit"> <i class="fa fa-search"></i></button>
				</span> <br />
			</div>
		</form>
</div>
</div>
<br />
<div class="row">
<div class="col-lg-12">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Level</th>
				<th width="110px">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="test" items="${testList}" varStatus="i">
				<tr>
					<td>${i.index +1}</td>
					<td>${test.testname }</td>
					<td>${test.level }</td>
					<td>
						<div class="btn-group">
							<a class="label label-info"
								href="${pageContext.request.contextPath}/admin/viewTest?testid=${test.testid}">View</a>
							<a class="label label-danger"
								href="${pageContext.request.contextPath}/admin/confirmDeleteTest?testid=${test.testid}">Delete</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
<div class="row text-center">
	<div class="col-md-12 column">
		<nav>
			<ul class="pagination">
				<li><a href=manageTest aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach begin="1" end="${pageTest }" var="item">
					<c:choose>
						<c:when test="${numPage eq item }">
							<li class="active"><a href="manageTest?numPage=${item }">${item }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="manageTest?numPage=${item }">${item }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="manageTest?numPage=${pageTest }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
	</div>
</div>