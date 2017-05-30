<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<h1 class="page-header">Chart Score</h1>
<c:choose>
	<c:when test="${errorScore eq true }">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<strong>Sorry!</strong> You have not done any test yet!
		</div>
	</c:when>
	<c:otherwise>
		<div class="panel panel-primary">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<div id="chart_div"></div>
								<script type="text/javascript">
								$(document).ready(function () {
									$.ajax({
										type : 'GET',
										headers : {
											Accept : "application/json; charset=utf-8",
											"Content-Type" : "application/json; charset=utf-8"
										},
										url: "${pageContext.request.contextPath}/resources/myscore.json",
										success : function(result) {
											google.charts.load('current', {
												packages : [ 'corechart', 'line' ]
											});
											google.charts.setOnLoadCallback(function() {
												drawChart(result);
											});
										}
									});
								
									function drawChart(result) {
										var data = new google.visualization.DataTable();
										data.addColumn('string', 'Time');
										data.addColumn('number', 'Score');
										var dataArray = [];
										$.each(result, function(i, obj) {
											dataArray.push([ obj.time, obj.score ]);
										});
										data.addRows(dataArray);
										var options = {
											title: 'BIỂU ĐỒ KẾT QUẢ CÁC BÀI TEST',
											titleTextStyle: {
												color: '#01579b',
												fontSize: 18,
							  	          		fontName: 'Arial',
												bold: true,
												italic: true,
											},
											hAxis: {
												title: 'Time Test',
												textStyle: {
													color: '#01579b',
													fontName: 'Arial',
													bold: false,
													italic: true
												},
												titleTextStyle: {
													color: '#01579b',
													fontSize: 16,
													fontName: 'Arial',
													bold: true,
													italic: true
												},
											},
											vAxis: {
												title: 'Score',
												textStyle: {
													color: '#01579b',
													fontName: 'Arial',
													bold: false,
													italic: true
												},
												titleTextStyle: {
													color: '#01579b',
													fontSize: 18,
													fontName: 'Arial',
													bold: true,
													italic: true
												}
											},
											height: 300
										};
										var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
										chart.draw(data, options);
									}
								});
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>