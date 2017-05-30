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
		<h1 class="page-header">${intro.name }</h1>
	</div>
</div>
<form method="post">
	<div class="row">
		<div class="form-group">
			<textarea id="editable" name="content">${intro.content }</textarea>
		</div>
	</div>
	<input type="submit" class="btn btn-primary" value="Update">
</form>