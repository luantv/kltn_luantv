<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Manager User</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="col-md-4 col-sm-6 col-xs-6">
			<div class="panel panel-back noti-box">
				<span class="icon-box bg-color-blue set-icon"> <i
					class="fa fa-users"></i>
				</span>
				<div class="text-box">
					<p class="main-text">${numAllUser } Users</p>
					<p class="text-muted">Total</p>
					<p class="text-right"><a class="btn btn-primary" href="subadmin/manageUser">View Detail</a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-6 col-xs-6">
			<div class="panel panel-back noti-box">
				<span class="icon-box bg-color-green set-icon"> <i
					class="fa fa-users"></i>
				</span>
				<div class="text-box">
					<p class="main-text">${numUserActive } Users</p>
					<p class="text-muted">Active</p>
					<p class="text-right"><a class="btn btn-primary" href="subadmin/list_user_active">View Detail</a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-6 col-xs-6">
			<div class="panel panel-back noti-box">
				<span class="icon-box bg-color-red set-icon"> <i
					class="fa fa-users"></i>
				</span>
				<div class="text-box">
					<p class="main-text">${numUserBlocked } Users</p>
					<p class="text-muted">Blocked</p>
					<p class="text-right"><a class="btn btn-primary" href="subadmin/list_user_blocked">View Detail</a></p>
				</div>
			</div>
		</div>
	</div>
</div>