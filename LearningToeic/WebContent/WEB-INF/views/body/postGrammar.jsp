<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="${pageContext.request.contextPath}/resources/tinymce/js/tinymce/tinymce.min.js"></script>
<script>
	tinymce
			.init({
				selector : 'textarea#editable',
				height : 500,
				plugins : [
						'advlist autolink lists link image charmap print preview anchor',
						'searchreplace visualblocks code fullscreen',
						'insertdatetime media table contextmenu paste code' ],
				toolbar : 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
			});
</script>
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Create new grammar lession</h1>
	</div>

</div>
<div>
	<div class="col-lg-12">

		<form action="postGrammar" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Name</label> <input class="form-control" name="name" required />
			</div>
			<div class="form-group">
				<label>Description</label>
				<textarea class="form-control" rows="3" name="description" required></textarea>
			</div>
			<textarea id="editable" name="content"></textarea>
			<br/>
			<div class="form-group">
				<label>Upload exercise: </label> <input class="form-control"
					name="excelfile" multiple type="file" accept=".xlsx" required>
			</div>
			<br/>
			<button type="submit" class="btn btn-primary">Create new lesson</button>
			<br />
		</form>


	</div>
</div>