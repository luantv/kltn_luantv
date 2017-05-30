<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1 class="page-header">Edit Account Information</h1>
<div class="row placeholders">
	<form action="profile_edit" method="POST" class="form-horizontal" id="profileEditForm">
		<div class="form-group">
			<label for="username"
				class="col-md-3 col-sm-3 control-label">User name</label>
			<div class="col-md-8 col-sm-8 controls">
				<input type="text" class="form-control" placeholder="Enter username..." value="${user.username }" name="username" required />
			</div>
		</div>
		<div class="form-group">
			<label for="fullname"
				class="col-md-3 col-sm-3 control-label">Full name</label>
			<div class="col-md-8 col-sm-8 controls">
				<input type="text" class="form-control" placeholder="Enter full name..." value="${user.fullname }" name="fullname" required />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-md-3 col-sm-3 control-label">Email</label>
			<div class="col-md-8 col-sm-8">
				<input type="email" class="form-control" value="${user.email }" readonly="readonly" name="email" id="email" required />
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label for="inputBirthDate" class="col-md-3 col-sm-3 control-label">Birth date</label>
			<div class="col-md-8 col-sm-8">
				<form:input path="birthdate" class="form-control" type="text" id="inputBirthDate" placeholder="Birth date YYYY-MM-DD" />
			</div>
		</div>
		 -->
		<div class="form-group">
			<div class="col-sm-offset-9 col-sm-2">
				<button type="submit" class="btn btn-primary btn-block">Save Profile</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var flag = false;
		var date = new Date();
		$('#profileEditForm').bootstrapValidator({
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
							min : 3,
							max : 30,
							message : 'The fullname must be more than 3 and less than 30 characters long'
						}
					}
				},
				username : {
					validators : {
						notEmpty : {
							message : 'The username is required and cannot be empty'
						},
						stringLength : {
							min : 3,
							max : 30,
							message : 'The username must be more than 3 and less than 30 characters long'
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
	});
</script>