<%@page import="org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument.Include"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.ans {
	margin-left: 30px;
	margin-right: 15px;
}
.photo {
	margin-bottom: 20px;
}
</style>
</head>
<body onload="start()">
	<div class="row">
		<div class="col-lg-12">
			<br />
			<h1 class="page-header">Practice test</h1>
		</div>
	</div>
	<form action="${test.testid}/submit" method="post" id="submitTest">
		<div class="row">
			<div class="col-lg-12">
				<input type="hidden" name="testid" value="${test.testid }" id="testid" />
				<div class="panel-body">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="true" class="">Part 1: Photo (10 questions)</a>
								</h4>
							</div>
							<div id="collapse1" class="panel-collapse collapse in" aria-expanded="true">
								<div class="panel-body">
									<%@ include file="doPhoto.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="collapsed" aria-expanded="false">Part 2: Question-Response (30 questions)</a>
								</h4>
							</div>
							<div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doQuestionResponse.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse3" class="collapsed" aria-expanded="false">Part 3: Short conversation (30 questions)</a>
								</h4>
							</div>
							<div id="collapse3" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doShortConversation.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse4" class="collapsed" aria-expanded="false">Part 4: Short talk (30 questions)</a>
								</h4>
							</div>
							<div id="collapse4" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doShortTalk.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse5" class="collapsed" aria-expanded="false">Part 5: Incomplete Sentence (40 questions)</a>
								</h4>
							</div>
							<div id="collapse5" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doIncompleteSentence.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse6" class="collapsed" aria-expanded="false">Part 6: Text completion (12 questions)</a>
								</h4>
							</div>
							<div id="collapse6" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doTextCompletion.jsp"%>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse7" class="collapsed" aria-expanded="false">Part 7: Reading comprehension (48 questions)</a>
								</h4>
							</div>
							<div id="collapse7" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
								<div class="panel-body">
									<%@ include file="doReadingComprehension.jsp"%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<button type="button" class="btn btn-outline btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Submit</button>
	<br />
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- dialog body -->
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					Confirm
				</div>
				<!-- dialog buttons -->
				<div class="modal-footer">
					Are you sure you want to submit?
					<button type="button" class="btn btn-primary" onclick="submitTest()">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		var h = 2;
		var m = 0;
		var s = 0;
		var timeout = null;
		
		function start() {
			if (s === -1) {
				m -= 1;
				s = 59;
			}
			if (m === -1) {
				h -= 1;
				m = 59;
			}
			if (h == -1) {
				clearTimeout(timeout);
				alert('Time out');
				return false();
			}
			var time = h.toString() + ":";
			if (m < 10)
				time = time + "0" + m.toString();
			else
				time = time + m.toString();
			time = time + ":";
			if (s < 10)
				time = time + "0" + s.toString();
			else
				time = time + s.toString();
			document.getElementById('time').innerText = time;
			
			timeout = setTimeout(function() {
				s--;
				start();
			}, 1000);
		}
		
		function stop() {
			clearTimeout(timeout);
		}
		
		function submitTest() {
			document.getElementById("submitTest").submit();
		}
	</script>
</body>
</html>