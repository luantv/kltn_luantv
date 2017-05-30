<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="questionResponse" items="${test.questionResponses}" varStatus="i">
	<div class="row photo ans">
		<div class="col-lg-2">
			<span class="label label-primary ans"> Question ${i.index + 1} </span>
		</div>
		<div class="col-lg-10">
			<div class="row">
				<audio controls>
					<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part2/${questionResponse.questionresponseid}.mp3"
						type="audio/wav" />
				</audio>
			</div>
			<div class="row">
				${questionResponse.question}
			</div>
			<div class="row">
				<c:forTokens items="${questionResponse.answer }" delims="^" var="answer"
					varStatus="i_ans">
					<c:set var="alphabet"
						value='<%=new String[] { "A", "B", "C", "D" }%>' />
					<div class="col-lg-12">
						<label class="ans" 
							<c:if test="${questionResponse.correctanswer.equals(alphabet[i_ans.index])}"> style="color: blue;"</c:if>
							<c:if test="${questionResponses[i.index].equals(alphabet[i_ans.index]) }"> style="color: red;" </c:if>>
							<input type="radio" name="questionResponse-${i.index+1}" value="${alphabet[i_ans.index]}" disabled="disabled" 
								<c:if test="${questionResponses[i.index].equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>>
							${answer}
						</label>
					</div>
				</c:forTokens>
			</div>
		</div>
	</div>
	<c:if test="${(i.index)%5==4 && test.questionResponses[i.index+1]!=null}">
		<hr/>
	</c:if>
	<br/>
</c:forEach>