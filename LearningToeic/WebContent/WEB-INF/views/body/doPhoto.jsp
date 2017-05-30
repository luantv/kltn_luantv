<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="photo" items="${test.photos}" varStatus="i">
	<div class="row photo ans">
		<div class="col-lg-2">
			<span class="label label-primary ans"> Question ${i.index + 1}
			</span>
		</div>
		<div class="col-lg-5">
			<div class="row">
				<audio controls>
					<source src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part1/${photo.photoid}.mp3"
						type="audio/wav" />
				</audio>
			</div>
			<br />
			<div class="row">
				<img src="${pageContext.request.contextPath}/resources/upload/Test_${test.testid}/part1/${photo.photoid}.png" style="width: 100%;">
			</div>
			<div class="row">
				<c:forTokens items="A^B^C^D" delims="^" var="answer" varStatus="i_ans">
					<c:set var="alphabet" value='<%=new String[] { "A", "B", "C", "D" }%>' />
					<div class="col-lg-2">
						<label class="ans">
							<input type="radio" name="photo-${i.index+1}" value="${alphabet[i_ans.index]}"> ${answer}
						</label>
					</div>
				</c:forTokens>
			</div>
		</div>
	</div>
</c:forEach>