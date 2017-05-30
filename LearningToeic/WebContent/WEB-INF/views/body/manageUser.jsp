<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">All User</h1>
	</div>
</div>
<br />
<div class="row">
	<div class="col-lg-12">
		<c:if test="${delete=='true'}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Delete user success!
			</div>
		</c:if>
	</div>
	<div class="col-lg-12">
		<c:if test="${changeSuccess=='true'}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Change role user success!
			</div>
		</c:if>
	</div>
	<div class="col-lg-12">
		<form action="searchUser" method="GET">
			<div class="input-group custom-search-form col-lg-5">
				<input type="text" class="form-control" placeholder="Enter email to Search..." name="searchKey" /> 
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
				</span>
			</div>
		</form>
	</div>
</div>
<br />
<div class="row">
	<div class="col-lg-12">
		<table class="table table-striped table-bordered table-hover" id="table-user">
			<thead>
				<tr>
					<th>#</th>
					<th>User name</th>
					<th class="hide">Full name</th>
					<th class="hide">Image</th>
					<th>Email</th>
					<th>Status</th>
					<th>Detail</th>
					<th>Role</th>
					<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${listUser}" varStatus="i">
					<tr>
						<td>${i.index +1}</td>
						<td>${user.username }</td>
						<td class="hide">${user.fullname }</td>
						<td class="hide">${user.image }</td>
						<td>${user.email }</td>
						<td>
							<c:choose>
								<c:when test="${user.enabled eq 1 }"><span class="label label-success">Active</span></c:when>
								<c:otherwise><span class="label label-danger">Blocked</span></c:otherwise>
							</c:choose>
						</td>
						<td>
							<button class="btn btn-primary" onclick="display_detail(${i.index +1});">Detail</button>
						</td>
						<td>
							<div class="btn-group">
								<a href="${pageContext.request.contextPath}/subadmin/changeRole?email=${user.email}"
									class="btn btn-primary">Change Role</a>
							</div>
						</td>
						<td>
							<div class="btn-group">
								<c:choose>
									<c:when test="${user.enabled eq 1 }">
										<button class="btn btn-danger"
											onclick="active_block_user(${user.enabled},'${user.email }');">Block </button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-success"
											onclick="active_block_user(${user.enabled},'${user.email }');">Active </button>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div class="btn-group">
								<a href="${pageContext.request.contextPath}/subadmin/confirmDeleteUser?email=${user.email}"
									class="btn btn-danger"> Delete</a>
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
				<li><a href=manageUser aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach begin="1" end="${pageUser }" var="item">
					<c:choose>
						<c:when test="${numPage eq item }">
							<li class="active"><a href="manageUser?numPage=${item }">${item }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="manageUser?numPage=${item }">${item }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="manageUser?numPage=${pageUser }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
	</div>
</div>

<script>
function active_block_user(enabled, email){
	if(enabled == 0){
		$.ajax({
			type: 'GET',
			url: 'activeUser?email=' + email ,
			success: function(data){
				if(data == 'done'){
					alert("Active user done.")
					location.reload();
				}
				else if(data == 'error'){
					alert("Error Active user.")
				}
			},
			error: function(error){
				alert("Error: " + error);
			}
		});
	}
	else if(enabled == 1){
		$.ajax({
			type: 'GET',
			url: 'blockUser?email=' + email ,
			success: function(data){
				if(data == 'done'){
					alert("Block user done.")
					location.reload();
				}
				else if(data == 'error'){
					alert("Error Block user.")
				}
			},
			error: function(error){
				alert("Error: " + error);
			}
		})
	};
}

function display_detail(num){
	var userName = $('#table-user tr').eq(num).find('td').eq(1).text();
	var fullName = $('#table-user tr').eq(num).find('td').eq(2).text();
	var image = $('#table-user tr').eq(num).find('td').eq(3).text();
	var email = $('#table-user tr').eq(num).find('td').eq(4).text();
	var img_url = "${pageContext.request.contextPath}/resources/upload/profile/"+image;
	
	$('#userName').val(userName);
	$('#fullName').val(fullName);
	$('#email').val(email);
	$('#img-user').attr('src',img_url);
	$('#modalUser').modal('show');
}
</script>
<%@ include file="modal_user.jsp"%>