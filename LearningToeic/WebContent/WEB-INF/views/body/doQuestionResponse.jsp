<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="questionResponse" items="${test.questionResponses}"
	varStatus="i">
	<div class="row">
		<div class="col-lg-2">
			<span class="label label-primary ans"> Question ${i.index + 1}:
			</span>
		</div>
		<div class="col-lg-4">
			<audio controls>
				<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part2/${questionResponse.questionresponseid}.mp3"
					type="audio/wav" />
			</audio>
		</div>
		<div class="row">
			<c:forTokens items="A^B^C" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C" }%>' />
				<div class="col-lg-2">
					<label class="ans">
						<input type="radio" name="questionResponse-${i.index+1}" value="${alphabet[i_ans.index]}"> ${answer}
					</label>
				</div>
			</c:forTokens>
		</div>
	</div>
	<c:if test="${(i.index)%5==4 && test.questionResponses[i.index+1]!=null}">
		<hr/>
	</c:if>
	<br/>
</c:forEach>
