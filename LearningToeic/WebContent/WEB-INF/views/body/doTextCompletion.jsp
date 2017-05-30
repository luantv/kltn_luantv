<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="textCompletion" items="${test.textCompletions}" varStatus="i">
	<div class="row" style="margin-left: 10px">
		<span class="label label-primary ans"> Text ${i.index + 1}: </span><br/> ${textCompletion.script }
	</div>
	<c:if test="${test.textCompletions[i.index+1]!=null}">
		<hr />
	</c:if>
</c:forEach>