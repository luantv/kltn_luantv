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
<div class="row">
	<div class="alert alert-danger alert-dismissable">${score}.</div>
</div>
<form>
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
						name="question-${i.index}" value="A" disabled  <c:if test="${listCheck[i.index].equals('A') }"> checked="checked" </c:if>>
						${listAnswer[i.index][0]}
					</label>
				</div>
				<div class="col-lg-3">
					<label class="ans"> <input type="radio"
						name="question-${i.index}" value="B" disabled   <c:if test="${listCheck[i.index].equals('B') }"> checked="checked" </c:if>>
						${listAnswer[i.index][1]}
					</label>
				</div>
				<div class="col-lg-3">
					<label class="ans"> <input type="radio"
						name="question-${i.index}" value="C" disabled   <c:if test="${listCheck[i.index].equals('C') }"> checked="checked" </c:if>>
						${listAnswer[i.index][2]}
					</label>
				</div>
				<div class="col-lg-3">
					<label class="ans"> <input type="radio"
						name="question-${i.index}" value="D" disabled   <c:if test="${listCheck[i.index].equals('D') }"> checked="checked" </c:if>>
						${listAnswer[i.index][3]}
					</label>
				</div>
			</div>
		</div>

	</c:forEach>
	<input type="submit" value="Again" class="btn btn-default">
</form>