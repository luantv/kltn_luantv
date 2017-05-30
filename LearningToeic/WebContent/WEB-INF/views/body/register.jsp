<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="row">
		<div class="col-lg-12">
			<br />
			<h1 class="page-header">Register User</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<c:if test="${userError=='true'}">
				<div class="alert alert-danger  alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					<strong>This ${mailError} already exists</strong>
				</div>
			</c:if>
		</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<div style="margin: 20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title text-center">Register</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<form role="form" action="register" method="POST" class="form-horizontal" id="myForm">
							<div class="form-group">
								<label for="fullname"
									class="col-md-3 col-sm-3 control-label">Full name</label>
								<div class="col-md-8 col-sm-8 controls">
									<input type="text" class="form-control" placeholder="Enter full name..." name="fullname" required />
								</div>
							</div>
							<div class="form-group">
								<label for="username"
									class="col-md-3 col-sm-3 control-label">Username</label>
								<div class="col-md-8 col-sm-8 controls">
									<input type="text" class="form-control" placeholder="Enter username..." name="username" required />
								</div>
							</div>
							<div class="form-group">
								<label for="email"
									class="col-md-3 col-sm-3 control-label">Email</label>
								<div class="col-md-8 col-sm-8">
									<input type="email" class="form-control" placeholder="Enter email..." name="email" id="email" required />
								</div>
							</div>
							<div class="form-group">
								<label for="confrimEmail"
									class="col-md-3 col-sm-3 control-label">Confirm Email</label>
								<div class="col-md-8 col-sm-8">
									<input type="email" class="form-control" placeholder="Enter confirm email" name="confrimEmail" id="confrimEmail" required />
								</div>
							</div>
							<div class="form-group">
								<label for="password"
									class="col-md-3 col-sm-3 control-label">Password</label>
								<div class="col-md-8 col-sm-8">
									<input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required />
								</div>
							</div>
							<div class="form-group">
								<label for="confrimPassword"
									class="col-md-3 col-sm-3 control-label">Confirm Password</label>
								<div class="col-md-8 col-sm-8">
									<input type="password" class="form-control" placeholder="Enter confirm password" name="confrimPassword" id="confrimPassword" required />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="checkbox">
										<label> <input type="checkbox" name="acceptTerms">
											I agree to the <a>Terms of Service</a> and <a>Privacy
												Policy</a>
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-7 col-sm-4">
									<input type="submit" value="Register" id="btnSubmit" class="btn btn-primary btn-block">
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
				fullname : {
					validators : {
						notEmpty : {
							message : 'The fullname is required and cannot be empty'
						},
						stringLength : {
							min : 6,
							max : 30,
							message : 'The fullname must be more than 6 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_ \.]+$/,
							message : 'The fullname can only consist of alphabetical, number, dot and underscore'
						}
					}
				},username : {
					validators : {
						notEmpty : {
							message : 'The username is required and cannot be empty'
						},
						stringLength : {
							min : 6,
							max : 30,
							message : 'The username must be more than 6 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_\.]+$/,
							message : 'The username can only consist of alphabetical, number, dot and underscore'
						}
					}
				},
				email : {
					validators : {
						notEmpty : {
							message : 'The email is required and cannot be empty'
						}
					},
					emailAddrees : {
						message : 'The input is not a valid email address'
					}
				},
				confrimEmail : {
					validators : {
						notEmpty : {
							message : 'The confirm Email is required and cannot be empty'
						},
						emailAddrees : {
							message : 'The input is not a valid email address'
						},
						identical : {
							field : 'email',
							message : 'The email and its confirm Email are not the same'
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
				},
				acceptTerms : {
					validators : {
						notEmpty : {
							message : 'You have to accept the terms and policies'
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