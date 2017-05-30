<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Confirm delete test "${test.testname }"</h1>
	</div>
</div>
<div class="alert alert-warning">
	<img src="${pageContext.request.contextPath}/resources/img/warning-icon.png">
	<div style="display: inline;">
		Are you sure want to delete this test?
			<a href="javascript:history.back()" class="label label-success"> Cancel</a>
			<a href="${pageContext.request.contextPath}/admin/deleteTest?testid=${test.testid}"
				class="label label-danger"> Delete </a>
	</div>
</div>