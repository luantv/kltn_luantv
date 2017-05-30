<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav nav-sidebar">
	<li class="active"><a>Avatar</a></li>
	<li class="text-center"><img src="${pageContext.request.contextPath}/resources/upload/profile/${user.image}" class="user-image img-responsive img-circle" /></li>
</ul>
<ul class="nav nav-sidebar">
	<li class="active"><a>Setting profile</a></li>
	<li><a href="${pageContext.request.contextPath}/profile/profile_info">Info</a></li>
	<li><a href="${pageContext.request.contextPath}/profile/profile_edit">Edit profile</a></li>
	<li><a href="${pageContext.request.contextPath}/profile/profile_change_password">Change password</a></li>
	<li><a href="${pageContext.request.contextPath}/profile/profile_change_image">Change profile image</a></li>
</ul>
<ul class="nav nav-sidebar">
	<li class="active"><a href="">Learning</a></li>
	<li><a href="${pageContext.request.contextPath}/profile/my_activities">Your Activities</a></li>
	<c:if test="${role eq 'user' }">
		<li><a href="${pageContext.request.contextPath}/profile/chart">Chart Score</a></li>
	</c:if>
</ul>
<ul class="nav nav-sidebar">
	<c:choose>
		<c:when test="${role eq 'admin' }">
			<li><a href="${pageContext.request.contextPath}/admin">Go to Home page</a></li>
		</c:when>
		<c:when test="${role eq 'subadmin' }">
			<li><a href="${pageContext.request.contextPath}/subadmin">Go to Home page</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}">Go to Home page</a></li>
		</c:otherwise>
	</c:choose>
</ul>
