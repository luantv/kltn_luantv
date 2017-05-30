<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.ans {
	margin-left: 30px;
}
</style>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">${grammarname}</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<c:if test="${updateExerciseSuccess eq true}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Update exercise success!
			</div>
		</c:if>
		<c:if test="${deleteExerciseSuccess eq true}">
			<div class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info!</strong> Delete exercise success!
			</div>
		</c:if>
	</div>
</div>
<c:choose>
	<c:when test="${numexercise eq 0 }">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Sorry!</strong> No Exercises yet!
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="exercise" items="${exercises}" varStatus="i">
			<div class="row">
				<div class="col-lg-12">
					<span class="label label-primary" style="margin-right: 15px">
						Question ${i.index+1} </span> ${exercise.question}
					<input type="hidden" name="exerciseid" value="${exercise.exerciseid}" />
				</div>
				<br />
				<div class="row">
					<c:forTokens items="${exercise.answer}" delims="^" var="answer" varStatus="i_ans">
						<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
						<div class="col-lg-2">
							<label class="ans"
								<c:if test="${exercise.correctanswer.equals(alphabet[i_ans.index])}"> style="color: blue;"</c:if>
								<c:if test="${exercises[i.index].equals(alphabet[i_ans.index]) }"> style="color: red;" </c:if>>
								<input type="radio" name="incompleteSentence-${i.index+1}" disabled="disabled"
									<c:if test="${exercise.correctanswer.equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>
									value="${alphabet[i_ans.index]}" /> ${answer}
							</label>
						</div>
					</c:forTokens>
					<div class="col-lg-3">
						<a href="${pageContext.request.contextPath}/admin/updateExercise?exerciseid=${exercise.exerciseid}&grammarid=${grammarid }" class="btn btn-success">Update</a>
						<a href="${pageContext.request.contextPath}/admin/confirmDeleteExercise?exerciseid=${exercise.exerciseid}&grammarid=${grammarid }" class="btn btn-danger">Delete</a>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="row text-center">
			<div class="col-md-12 column">
				<nav>
					<ul class="pagination">
						<li><a href="lessonid=${grammarid}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							<c:forEach begin="1" end="${pageExer }" var="item">
								<c:choose>
									<c:when test="${numPage eq item }">
										<li class="active"><a href="?numPage=${item }">${item }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="?numPage=${item }">${item }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<li><a href="?numPage=${pageExer }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</c:otherwise>
</c:choose>