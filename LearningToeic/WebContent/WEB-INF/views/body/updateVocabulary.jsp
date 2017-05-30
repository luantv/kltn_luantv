<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Update vocabulary lesson</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<form action="updateVocabulary" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<input type="hidden" class="form-control" name="vocabularyid" value="${vocabulary.vocabularyid }" />
				<label>Name: </label>
				<input class="form-control" name="vocabularyname" value="${vocabulary.vocabularyname }" readonly="readonly" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name="video" value="${vocabulary.video }" />
				<label>Upload video: </label>
				<input class="form-control" name="video" multiple type="file" />
			</div>
			<br />
			<div class="form-group">
				<input type="hidden" class="form-control" name="document" value="${vocabulary.document }" />
				<label>Upload document: </label>
				<input class="form-control" name="document" multiple type="file" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name="image" value="${vocabulary.image }" />
				<label>Current image: </label>
				<img class="form-control" style="width: 50px; height: 50px"
					src="${pageContext.request.contextPath}/resources/upload/vocabulary/${vocabulary.image }"/>
				<label>Upload image: </label>
				<input class="form-control" name="image" multiple type="file" accept=".jpg, .png"/>
			</div>
			<button type="submit" class="btn btn-primary">Update lesson</button>
			<br />
		</form>
	</div>
</div>