<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%int id4=1; %>
<c:forEach var="shortTalk" items="${test.shortTalks}"
	varStatus="i">
	<div class="col-lg-12 ans" style="margin-bottom: 10px">
		<audio controls>
			<source
				src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part4/${shortTalk.shorttalkid}.mp3"
				type="audio/wav" />
		</audio>
	</div>
	<div class="row">
		<c:forEach var="shortTalkDetail"
			items="${shortTalk.shortTalkDetails}" varStatus="j">
			<div class="col-lg-12">
				<span class="label label-primary ans"> Question <%=id4 %> </span> ${shortTalkDetail.question}
			</div>
			<c:forTokens items="${shortTalkDetail.answer}" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
				<div class="col-lg-6">
					<label class="ans"> <input type="radio"
						name="shortTalk-<%=id4 %>"
						value="${alphabet[i_ans.index]}"> ${answer}
					</label>
				</div>
			</c:forTokens>
			<%id4++; %>
		</c:forEach>
	</div>
	<c:if test="${test.shortTalks[i.index+1]!=null}">
		<hr/>
	</c:if>
</c:forEach>