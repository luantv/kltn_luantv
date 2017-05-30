<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="page-header">Change password</h1>
<div class="row">
		<div class="col-lg-12">
			<c:if test="${errorPass eq true}">
				<div class="alert alert-danger  alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					<strong>Error!</strong> Old Password is incorrect!
				</div>
			</c:if>
			<c:if test="${errorPassword eq true}">
				<div class="alert alert-danger  alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					<strong>Error!</strong> The password and its confirm password are not the same!
				</div>
			</c:if>
			<c:if test="${updateAccountSuccess eq true}">
				<div class="alert alert-info">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Info!</strong> Change password account success! 
				</div>
			</c:if>
		</div>
	</div>
				<div class="row placeholders">
					<form action="profile_change_password" method="POST" class="form-horizontal" id="changePassForm">
						<div class="form-group">
							<label for="oldPassword"
								class="col-md-3 col-sm-3 control-label">Current Password</label>
							<div class="col-md-5 col-sm-5 controls">
								<input type="password" class="form-control" placeholder="Enter current password" name="oldPassword" id="oldPassword" required />
							</div>
						</div>
						<div class="form-group">
							<label for="password"
								class="col-md-3 col-sm-3 control-label">New password</label>
							<div class="col-md-5 col-sm-5">
								<input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required />
							</div>
						</div>
						
						<div class="form-group">
							<label for="confrimPassword"
								class="col-md-3 col-sm-3 control-label">Re-type new password</label>
							<div class="col-md-5 col-sm-5">
								<input type="password" class="form-control" placeholder="Enter confirm password" name="confrimPassword" id="confrimPassword" required />
							</div>
						</div>
						
						<div class="form-group">
							<span class="col-md-3 col-sm-3 col-sm-offset-3"></span>
							<div class="col-sm-2">
								<button type="submit"
									class="btn btn-primary btn-block">Change password</button>
							</div>
						</div>
					</form>
				</div>
	<script type="text/javascript">
		var flag = false;
		$('#changePassForm').bootstrapValidator({
			feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
			fields: {
				oldPassword : {
					validators : {
						notEmpty : {
							message : 'The password is required and cannot be empty'
						}
					}
				},
				password : {
					validators : {
						notEmpty : {
							message : 'The password is required and cannot be empty'
						},
						stringLength : {
							min : 6,
							max : 20,
							message : 'The password must be more than 6 and less than 20 characters long'
						}
					}
				},
				confrimPassword : {
					validators : {
						notEmpty : {
							message : 'The confirm password is required and cannot be empty'
						},
						stringLength : {
							min : 6,
							max : 20,
							message : 'The confirm password must be more than 6 and less than 20 characters long'
						},
						identical : {
							field : 'password',
							message : 'The password and its confirm password are not the same'
						}
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