<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Update exercise</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<form action="${pageContext.request.contextPath}/admin/updateExercise" method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<input type="text" class="form-control" value="${exercise.question}" name="question"> 
			</div>
			<div class="panel-body">
				<c:forTokens items="${exercise.answer}" delims="^" var="answer" varStatus="i_ans">
					<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
						<div class="col-lg-3">
							<label>
								<input type="radio" name="ans" style="display: inline;"
									<c:if test="${exercise.correctanswer.equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>
									value="${alphabet[i_ans.index]}" />
								<input type="text" style="display: inline;" class="form-control"
									value="${answer}" name="${alphabet[i_ans.index]}" />
							</label>
					</div>
				</c:forTokens>
			</div>
			<div class="panel-footer">
				<input type="hidden" name="grammarid" value="${grammarid }" />
				<input type="hidden" name="exerciseid" value="${exercise.exerciseid }" />
				<button type="submit" class="btn btn-primary">Update</button>
			</div>
		</div>
		
		</form>
	</div>
</div>
