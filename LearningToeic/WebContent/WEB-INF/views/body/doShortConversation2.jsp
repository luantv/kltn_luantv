<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="id" scope="session" value="${1}" />
<c:forEach var="shortConversation" items="${test.shortConversations}"
	varStatus="i">
	<div class="row photo ans">
		<div class="col-lg-12">
			<div class="row">
				<audio controls>
					<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part3/${shortConversation.shortconversationid}.mp3"
						type="audio/wav" />
				</audio>
			</div>
			<div class="row">${shortConversation.script}</div>
		</div>
		<br />
		<div class="row">
			<c:forEach var="shortConversationDetail" items="${shortConversation.shortConversationDetails}" varStatus="j">
				<div class="col-lg-12">
					<span class="label label-primary ans" style="margin-right: 15px"> Question ${id } </span> ${shortConversationDetail.question}
				</div>
				<c:forTokens items="${shortConversationDetail.answer}" delims="^" var="answer" varStatus="i_ans">
					<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
					<div class="col-lg-6">
						<label class="ans"
							<c:if test="${shortConversationDetail.correctanswer.equals(alphabet[i_ans.index])}"> style="color: blue;"</c:if>
							<c:if test="${shortConversations[id-1].equals(alphabet[i_ans.index]) }"> style="color: red;" </c:if>>
							<input type="radio" name="shortConversation-${id-1 }" disabled="disabled"
								<c:if test="${shortConversations[id-1].equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>
							value="${alphabet[i_ans.index]}"> ${answer}
						</label>
					</div>
				</c:forTokens>
				<c:set var="id" scope="session" value="${id+1}" />
			</c:forEach>
		</div>
	</div>
	<c:if test="${test.shortConversations[i.index+1]!=null}">
		<hr />
	</c:if>
</c:forEach>