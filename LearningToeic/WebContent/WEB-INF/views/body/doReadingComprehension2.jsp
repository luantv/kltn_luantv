<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="id" scope="session" value="${1}" />
<c:forEach var="readingComprehension" items="${test.readingComprehensions}" varStatus="i">
	<div class="col-lg-12 ans" style="margin-bottom: 10px">
		<img src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part7/${readingComprehension.readingcomprehensionid}.png" />
	</div>
	<div class="row">
		<c:forEach var="readingComprehensionDetail" items="${readingComprehension.readingComprehensionDetails}" varStatus="j">
			<div class="col-lg-12">
				<span class="label label-primary ans" style="margin-right: 15px"> Question ${id} </span> ${readingComprehensionDetail.question}
			</div>
			<c:forTokens items="${readingComprehensionDetail.answer}" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
				<div class="col-lg-6">
					<label class="ans"
						<c:if test="${readingComprehensionDetail.correctanswer.equals(alphabet[i_ans.index])}"> style="color: blue;"</c:if>
						<c:if test="${readingComprehensions[id-1].equals(alphabet[i_ans.index]) }"> style="color: red;" </c:if>>
					 	<input type="radio" name="readingComprehension-${id-1 }" disabled="disabled"
							<c:if test="${readingComprehensions[id-1].equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>
							value="${alphabet[i_ans.index]}" /> ${answer}
					</label>
				</div>
			</c:forTokens>
			<c:set var="id" scope="session" value="${id+1}" />
		</c:forEach>
	</div>
	<c:if test="${test.readingComprehensions[i.index+1]!=null}">
		<hr />
	</c:if>
</c:forEach>