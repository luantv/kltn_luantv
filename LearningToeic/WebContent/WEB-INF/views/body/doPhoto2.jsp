<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="photo" items="${test.photos}" varStatus="i">
	<div class="row photo ans">
		<div class="col-lg-2">
			<span class="label label-primary ans"> Question ${i.index + 1}
			</span>
		</div>
		<div class="col-lg-10">
			<div class="row">
				<audio controls>
					<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part1/${photo.photoid}.mp3"
						type="audio/wav" />
				</audio>
			</div>
			<br />
			<div class="row col-lg-5">
				<img src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part1/${photo.photoid}.png" style="width: 100%;">
			</div>
			<div class="row">
				<c:forTokens items="${photo.answer }" delims="^" var="answer" varStatus="i_ans">
					<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
					<div class="col-lg-12">
						<label class="ans"
							<c:if test="${photo.correctanswer.equals(alphabet[i_ans.index])}"> style="color: blue;" checked="checked"</c:if>
							<c:if test="${photos[i.index].equals(alphabet[i_ans.index]) }"> style="color: red;" </c:if>>
							<input type="radio" name="photo-${i.index+1}"
								value="${alphabet[i_ans.index]}" disabled="disabled"
							<c:if test="${photos[i.index].equals(alphabet[i_ans.index]) }"> checked="checked" </c:if>>
							${answer}
						</label>
					</div>
				</c:forTokens>
			</div>
		</div>
	</div>
</c:forEach>