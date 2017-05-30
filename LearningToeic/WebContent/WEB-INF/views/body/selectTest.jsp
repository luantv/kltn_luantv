<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<style type="text/css">
a{text_decoration: none}
.easy{background-color: green;}
.medium{background-color: orange;}
.hard{background-color: red;}
.test {width: 100px; height: auto;}
</style>
<body>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Select Test</h1>
	</div>
</div>

<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<div style="margin: 20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-center"><b>Toeic Test</b></h3>
				</div>
				<div class="panel-body">
					<div class="row text-center">
						<div class="form-group">
							<p><b>Time do Test</b>: 120 minute</p>
						</div>
						<div class="form-group">
							<p><b>Question</b>: 200 with Reading: 100 question and Listening: 100 question</p>
						</div>
						<div class="form-group">
							<p><b>Max Score</b>: 990 with Reading: 495 and Listening: 495</p>
						</div>
						<hr />
						<div class="col-lg-4">
							<a data-toggle="modal" data-target="#myModal1"  class="test btn btn-success">Easy</a><br />
						</div>
						<div class="col-lg-4">
							<a data-toggle="modal" data-target="#myModal2"  class="test btn btn-warning">Medium</a><br />
						</div>
						<div class="col-lg-4">
							<a data-toggle="modal" data-target="#myModal3"  class="test btn btn-danger">Hard</a><br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Toeic Easy Test</h4>
			</div>
			<div class="modal-body">
				<c:choose>
					<c:when test="${errorEasy eq true}">
						<div class="alert alert-danger" align="center">
							<string>Sorry</string>, No easy test yet!
						</div>
					</c:when>
					<c:otherwise>
						<div align="center">
							<a class="btn btn-primary" href="${pageContext.request.contextPath}/practiceFullTest/${easyTest }">Start</a>
						</div>
					</c:otherwise>
				</c:choose>	
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal2" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Toeic Medium Test</h4>
			</div>
			<div class="modal-body">
				<c:choose>
					<c:when test="${errorMedium eq true}">
						<div class="alert alert-danger" align="center">
							<string>Sorry</string>, No medium test yet!
						</div>
					</c:when>
					<c:otherwise>
						<div align="center">
							<a class="btn btn-primary" href="${pageContext.request.contextPath}/practiceFullTest/${mediumTest }">Start</a>
						</div>
					</c:otherwise>
				</c:choose>				
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal3" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Toeic Hard Test</h4>
			</div>
			<div class="modal-body">
				<c:choose>
					<c:when test="${errorHard eq true}">
						<div class="alert alert-danger" align="center">
							<string>Sorry</string>, No hard test yet!
						</div>
					</c:when>
					<c:otherwise>
						<div align="center">
							<a class="btn btn-primary" href="${pageContext.request.contextPath}/practiceFullTest/${hardTest}">Start</a>
						</div>
					</c:otherwise>
				</c:choose>				
			</div>
		</div>
	</div>
</div>
</body>
</html>
