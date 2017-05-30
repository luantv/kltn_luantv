<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Create new vocabulary lesson</h1>
	</div>

</div>
<div class="row">
	<div class="col-lg-12">
		<form action="postVocabulary" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Name: </label>
				<input class="form-control" name="vocabularyname" required />
			</div>
			<div class="form-group">
				<label>Upload video: </label>
				<input class="form-control" name="video" multiple type="file" required accept=".mp3, .mp4" />
			</div>
			<div class="form-group">
				<label>Upload document: </label>
				<input class="form-control" name="document" multiple type="file" required />
			</div>
			<div class="form-group">
				<label>Upload image: </label>
				<input class="form-control" name="image" type="file" accept=".png, .jpg" />
			</div>
			<br />
			<div id="status"></div>
			<button type="submit" class="btn btn-primary">Create new vocabulary</button>
			<br />
		</form>
	</div>
</div>