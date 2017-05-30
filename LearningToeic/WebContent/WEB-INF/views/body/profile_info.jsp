<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<h1 class="page-header">Account Information</h1>
<div class="row">
	<div class="col-lg-12">
		<c:if test="${updateAccountSuccess eq true}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Update information account success! 
			</div>
		</c:if>
	</div>
</div>
<div class="row placeholders">
	<form action="" method="POST" class="form-horizontal" id="signupForm">
		<div class="form-group">
			<label for="username"
				class="col-md-3 col-sm-3 control-label">User name</label>
			<div class="col-md-8 col-sm-8 controls">
				<input type="text" class="form-control" value="${user.username }" readonly name="username" required />
			</div>
		</div>
		<div class="form-group">
			<label for="fullname"
				class="col-md-3 col-sm-3 control-label">Full name</label>
			<div class="col-md-8 col-sm-8 controls">
				<input type="text" class="form-control" value="${user.fullname }" readonly name="fullname" required />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-md-3 col-sm-3 control-label">Email</label>
			<div class="col-md-8 col-sm-8">
				<input type="email" class="form-control" value="${user.email }" readonly name="email" required />
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label for="inputBirthDate" class="col-md-3 col-sm-3 control-label">Birth date</label>
			<div class="col-md-8 col-sm-8">
				<form:input path="birthdate" class="form-control" type="text" id="inputBirthDate" />
				<form:input path="birthdate" class="form-control" id="inputBirthDate" placeholder="Birth date" readonly="true" />
			</div>
		</div>
		 -->
		<div class="form-group">
			<div class="col-sm-offset-9 col-sm-2">
				<a href="profile_edit" class="btn btn-primary btn-block">Edit Profile</a>
			</div>
		</div>
	</form>
</div>