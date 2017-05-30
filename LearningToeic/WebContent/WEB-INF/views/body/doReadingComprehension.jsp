<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%int id7=1; %>
<c:forEach var="readingComprehension" items="${test.readingComprehensions}"
	varStatus="i">
	<div class="col-lg-12 ans" style="margin-bottom: 10px">
			<img src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part7/${readingComprehension.readingcomprehensionid}.png" />
	</div>
	<div class="row">
		<c:forEach var="readingComprehensionDetail" items="${readingComprehension.readingComprehensionDetails}" varStatus="j">
			<div class="col-lg-12">
				<span class="label label-primary ans"> Question <%=id7 %> </span> ${readingComprehensionDetail.question}
			</div>
			<c:forTokens items="${readingComprehensionDetail.answer}" delims="^" var="answer" varStatus="i_ans">
				<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
				<div class="col-lg-6">
					<label class="ans">
						<input type="radio" name="readingComprehension-<%=id7 %>" value="${alphabet[i_ans.index]}"> ${answer}
					</label>
				</div>
			</c:forTokens>
			<%id7++; %>
		</c:forEach>
	</div>
	<c:if test="${test.readingComprehensions[i.index+1]!=null}">
		<hr/>
	</c:if>
</c:forEach>