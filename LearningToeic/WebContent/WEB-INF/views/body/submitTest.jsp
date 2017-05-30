<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
.ans {
	margin-left: 30px;
}
</style>
<script>
$(document).ready(function(){
	$("div.toggles").hide();
    $("div.toggle").click(function(){
        $("div.toggles").toggle();
    });
});
</script>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Result Test</h1>
	</div>
</div>
<div class="row">
<div class="panel panel-info">
	<div class="panel-heading">
		<h1 style="margin: 0px">Your Score: ${score}</h1>
	</div>
	<div class="panel-body">
		<h2 style="margin-left: 50px">Listening: ${listening}/100</h2>
		<h2 style="margin-left: 50px">Reading: ${reading}/100</h2>
	</div>
</div>
</div>
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading toggle"><h2 style="margin: 0px">View answer</h2></div>
		<!-- /.panel-heading -->
		<div class="panel-body toggles">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="active"><a href="#part1" data-toggle="tab">Photo</a></li>
				<li><a href="#part2" data-toggle="tab">Question - Response</a></li>
				<li><a href="#part3" data-toggle="tab">Short conversation</a></li>
				<li><a href="#part4" data-toggle="tab">Short talk</a></li>
				<li><a href="#part5" data-toggle="tab">Incomplete sentence</a></li>
				<li><a href="#part6" data-toggle="tab">Text completion</a></li>
				<li><a href="#part7" data-toggle="tab">Reading comprehension</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade in active" id="part1">
					<h3 class="alert alert-info">Photo: ${p1}/10</h3>
					<%@ include file="doPhoto2.jsp"%>
					
				</div>
				<div class="tab-pane fade" id="part2">
					<h3 class="alert alert-info">Question - Response: ${p2}/30</h3>
					<%@ include file="doQuestionResponse2.jsp" %>
				</div>
				<div class="tab-pane fade" id="part3">
					<h3 class="alert alert-info">Short conversation: ${p3}/30</h3>
					<%@ include file="doShortConversation2.jsp" %>
				</div>
				<div class="tab-pane fade" id="part4">
					<h3 class="alert alert-info">Short talk: ${p4}/30</h3>
					<%@ include file="doShortTalk2.jsp" %>
				</div>
				<div class="tab-pane fade" id="part5">
					<h3 class="alert alert-info">Incomplete sentence: ${p5}/40</h3>
					<%@ include file="doIncompleteSentence2.jsp" %>
				</div>
				<div class="tab-pane fade" id="part6">
					<h3 class="alert alert-info">Text completion: ${p6}/12</h3>
					<%@ include file="doTextCompletion.jsp" %>
				</div>
				<div class="tab-pane fade" id="part7">
					<h3 class="alert alert-info">Reading comprehension: ${p7}/48</h3>
					<%@ include file="doReadingComprehension2.jsp" %>
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>
</div>
<div class="row">
	<div style="display: inline;">
		<a href="${pageContext.request.contextPath}/practiceFullTest/${test.testid }" class="btn btn-default">Again</a>
		<a href="${pageContext.request.contextPath}/testList" class="btn btn-primary">Continue</a>
	</div>
</div>