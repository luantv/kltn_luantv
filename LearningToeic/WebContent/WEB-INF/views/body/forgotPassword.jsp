<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">
.input-group-addon {
	min-width: 150px;
}
</style>
<body>
	<div class="row">
		<div class="col-lg-12">
			<br />
			<h1 class="page-header">Reset password</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<c:if test="${userError=='true'}">
				<div class="alert alert-danger  alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					This ${mailError} not exists
				</div>
			</c:if>
			<c:if test="${userError=='false'}">
				<div class="alert alert-info">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Info! </strong>A new password has been emailed to the address you provided.
				</div>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div align="center">If you have forgotten your password, we can send a new password to email address registered with your account!</div>
		<div class="col-md-8 col-md-offset-2">
			<div style="margin: 20px;">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title text-center">Update</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<form role="form" action="forgotPassword" method="post" id="myForm" class="form-horizontal">
								<div class="form-group">
									<label for="email" class="col-md-3 col-sm-3 control-label">Email</label>
									<div class="col-md-8 col-sm-8">
										<input type="email" class="form-control" placeholder="Enter email..." name="email" id="email" required />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-7 col-sm-4">
										<input type="submit" value="Reset password" id="btnSubmit" class="btn btn-primary btn-block">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var flag = false;
		$('#myForm').bootstrapValidator({
			feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
			fields: {
				username : {
					validators : {
						notEmpty : {
							message : 'The email is required and cannot be empty'
						}
					},
					emailAddrees : {
						message : 'The input is not a valid email address'
					}
				}
			}
		}).on('error.form.bv', function(e) {
			console.log('error.form.bv');;
		}).on('success.form.bv', function(e) {
			//console.log('success.form.bv');
			flag = true;
			// If you want to prevent the default handler (bootstrapValidator._onSuccess(e))
			// e.preventDefault();
		}).on('error.field.bv', function(e, data) {
			//console.log('error.field.bv -->', data);
		}).on('success.field.bv', function(e, data) {
			//console.log('success.field.bv -->', data);
		});
	</script>
</body>
</html>