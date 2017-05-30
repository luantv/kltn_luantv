<div class="modal fade bs-example-modal-lg" id="modalUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">Detail User</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="img-user" class="control-label">Avatar:</label><br />
								<img id="img-user" style="height: auto; width: 150px;" alt="avatar here" 
									src="${pageContext.request.contextPath}/resources/upload/profile/"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="userName" class="control-label">User Name:</label>
								<input type="text" readonly="readonly" class="form-control" id="userName">
							</div>
							<div class="form-group">
								<label for="fullName" class="control-label">Full Name:</label>
								<input type="text" readonly="readonly" class="form-control" id="fullName">
							</div>
							<div class="form-group">
								<label for="email" class="control-label">Email:</label>
								<input type="text" readonly="readonly" class="form-control" id="email">
							</div>
							<!-- 
							<div class="form-group">
								<label for="birthdate" class="control-label">Birthdate:</label>
								<input type="text" readonly="readonly" class="form-control" id="birthdate">
							</div>
							 -->
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>