<h1 class="page-header">Upload profile image</h1>
<div class="row placeholders">
	<div class="col-md-2 col-sm-2">
		<img class="img-responsive img-hover"
			src="${pageContext.request.contextPath}/resources/upload/profile/${user.image }"
			alt="..." style="height: 150px; display: block;">
	</div>
	<div class="col-md-10 col-sm-10">
		<form action="profile_change_image" method="POST" enctype="multipart/form-data" class="form-horizontal" id="uploadImageForm">
			<div class="form-group">
				<label for="image" class="col-md-3 col-sm-3 control-label"> Select file to upload: </label>
				<div class="col-md-3 col-sm-3 controls">
					<input type="file" id="image" name="image" size="50" accept=".png, .jpg" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-3 col-sm-3">
					<input type="submit" value="Upload" class="btn btn-primary btn-block" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
		var flag = false;
		$('#uploadImageForm').bootstrapValidator({
			fields: {
				image : {
					validators : {
						notEmpty : {
							message : 'Please select image need upload!'
						}
					}
				}
			}
		}).on('error.form.bv', function(e) {
			console.log('error.form.bv');;
		}).on('success.form.bv', function(e) {
			//console.log('success.form.bv');
			flag = true;
			// If you want to prevent the default handler (bootstrapValidator._onSuccess(e))
			// e.preventDefault();
		}).on('error.field.bv', function(e, data) {
			//console.log('error.field.bv -->', data);
		}).on('success.field.bv', function(e, data) {
			//console.log('success.field.bv -->', data);
		});
	</script>