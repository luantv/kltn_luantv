<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="incompletesentence" items="${test.incompleteSentences}"
	varStatus="i">
	<div class="row">
		<div class="col-lg-12">
			<span class="label label-primary" style="margin-right: 15px"> Question ${i.index+1} </span> ${incompletesentence.question}
		</div>
		<br />
		<div class="row">
			<c:forTokens items="${incompletesentence.answer}" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
				<div class="col-lg-3">
					<label class="ans">
						<input type="radio" name="incompleteSentence-${i.index+1}" value="${alphabet[i_ans.index]}"> ${answer}
					</label>
				</div>
			</c:forTokens>
		</div>
	</div>
</c:forEach>
