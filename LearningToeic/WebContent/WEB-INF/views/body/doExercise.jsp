<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.ans {
	margin-left: 30px;
}
</style>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${lessonName }</h1>
	</div>
</div>
<c:choose>
	<c:when test="${numexercise eq 0 }">
		<div class="alert alert-danger  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<strong>Sorry!</strong> No Exercises yet!
		</div>
	</c:when>
	<c:otherwise>
		<form method="post">
			<c:forEach var="exercise" items="${listExercise}" varStatus="i">
			<div class="row">
				<div class="col-lg-12">
					<span class="label label-primary" style="margin-right: 16px;">
					Question ${i.index+1} </span> ${exercise.question}
				</div>
				<br />
				<div class=row>
					<div class="col-lg-3">
						<label class="ans"> <input type="radio"
							name="question-${i.index}" value="A"> ${listAnswer[i.index][0]}
						</label>
					</div>
					<div class="col-lg-3">
						<label class="ans"> <input type="radio"
							name="question-${i.index}" value="B"> ${listAnswer[i.index][1]}
						</label>
					</div>
					<div class="col-lg-3">
						<label class="ans"> <input type="radio"
							name="question-${i.index}" value="C"> ${listAnswer[i.index][2]}
						</label>
					</div>
					<div class="col-lg-3">
						<label class="ans"> <input type="radio"
							name="question-${i.index}" value="D"> ${listAnswer[i.index][3]}
						</label>
					</div>
				</div>
			</div>
			</c:forEach>
			<input type="submit" value="Submit" class="btn btn-default"> 
		</form>
	</c:otherwise>
</c:choose>
