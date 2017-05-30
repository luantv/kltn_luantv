<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Change Role User</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h4>
					<b>Upload User Role</b>
				</h4>
			</div>
			<div class="panel-body">
				<form action="changeRole" method="POST">
					<div class="form-group">
						<label for="email" class="col-md-2 col-sm-2">Email</label>
						<div class="col-md-10 col-sm-10">
							<input type="hidden" class="form-control" name="email"
								value="${users.email }" /> <input name="email" type="text"
								class="form-control" value="${users.email }" disabled="disabled" />
						</div>
					</div>
					<br /> <br /> <br />
					<div class="form-group">
						<label for="role" class="col-md-2 col-sm-2">Role</label>
						<div class="col-md-10 col-sm-10">
							<select name="role">
								<c:choose>
									<c:when test="${users.role eq 'ROLE_USER' }">
										<option selected="selected" value="ROLE_USER">USER</option>
										<option value="ROLE_ADMIN">ADMIN</option>
									</c:when>
									<c:otherwise>
										<option value="ROLE_USER">USER</option>
										<option selected="selected" value="ROLE_ADMIN">ADMIN</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-5" style="display: inline;">
							<input type="submit" class="btn btn-info" value="Update Role">
							<a href="javascript:history.back()" class="btn btn-warning">Cancel</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>