<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<h1 class="page-header">Login into LEARNING TOEIC</h1>
		</div>
	</div>
	<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div style="margin: 20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title text-center">Login</h3>
				</div>
				<div class="panel-body">
					<form role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'
						class="form-horizontal" id="signinForm">
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8 col-lg-8">
								<input type="email" name="email" class="form-control" placeholder="Email..." required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8 col-lg-8">
								<input type="password" name="password" class="form-control" placeholder="Password..." required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8">
								<div class="checkbox">
									<label><input type="checkbox" name="remember-me" /> Remember me</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8 col-lg-8">
								<input type="submit" value="Login" class="btn btn-primary btn-block" />
								<c:if test="${param.error eq true }">
									<p style="color: red"><strong>Error!</strong> Email or Password is incorrect!</p>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8">
								<div class="row text-center">
									<p>
										<a href="${pageContext.request.contextPath}/forgotPassword">Forgot password?</a> | 
										<a href="register">Create a new account.</a>
									</p>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html> 	