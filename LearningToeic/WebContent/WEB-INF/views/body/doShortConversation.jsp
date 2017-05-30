<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%int id=1; %>
<c:forEach var="shortConversation" items="${test.shortConversations}"
	varStatus="i">
	<div class="col-lg-12 ans" style="margin-bottom: 10px">
		<audio controls>
			<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part3/${shortConversation.shortconversationid}.mp3"
				type="audio/wav" />
		</audio>
	</div>
	<div class="row">
		<c:forEach var="shortConversationDetail" items="${shortConversation.shortConversationDetails}" varStatus="j">
			<div class="col-lg-12">
				<span class="label label-primary ans"> Question <%=id %> </span> ${shortConversationDetail.question}
			</div>
			<c:forTokens items="${shortConversationDetail.answer}" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
				<div class="col-lg-6">
					<label class="ans">
						<input type="radio" name="shortConversation-<%=id %>" value="${alphabet[i_ans.index]}"> ${answer}
					</label>
				</div>
			</c:forTokens>
			<%id++; %>
		</c:forEach>
	</div>
	<c:if test="${test.shortConversations[i.index+1]!=null}">
		<hr/>
	</c:if>
</c:forEach>