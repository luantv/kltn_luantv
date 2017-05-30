<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Confirm delete User "${users.email }"</h1>
	</div>
</div>
<div class="alert alert-warning">
<img src="${pageContext.request.contextPath}/resources/img/warning-icon.png">
	<div style="display: inline;">
		Are you sure want to delete this lesson? 
			<a href="javascript:history.back()" class="label label-success">Cancel</a>
			<a href="${pageContext.request.contextPath}/subadmin/deleteUser?email=${users.email}" 
				class="label label-danger">Delete</a>
	</div>
</div>