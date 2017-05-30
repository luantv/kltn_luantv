<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${test.testname }</h1>
	</div>
</div>
<div class="panel-body">
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
			<br/>
			<%@ include file="doPhoto2.jsp"%>
		</div>
		<div class="tab-pane fade" id="part2">
			<br/>
			<%@ include file="doQuestionResponse2.jsp"%>
		</div>
		<div class="tab-pane fade" id="part3">
			<br/>
			<%@ include file="doShortConversation2.jsp"%>
		</div>
		<div class="tab-pane fade" id="part4">
			<br/>
			<%@ include file="doShortTalk2.jsp"%>
		</div>
		<div class="tab-pane fade" id="part5">
			<br/>
			<%@ include file="doIncompleteSentence2.jsp"%>
		</div>
		<div class="tab-pane fade" id="part6">
			<br/>
			<%@ include file="doTextCompletion.jsp"%>
		</div>
		<div class="tab-pane fade" id="part7">
			<br/>
			<%@ include file="doReadingComprehension2.jsp"%>
		</div>
	</div>
</div>
<!-- /.panel-body -->
