<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Profile - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/learner-style.css"
	var="learnerstylecss" />
<link href="${learnerstylecss}" rel="stylesheet" />
<spring:url value="/resources/css/responsive-sign-up.css"
	var="responsivecss" />
<link href="${responsivecss}" rel="stylesheet" />
<spring:url value="/resources/css/sidebar.css" var="sidecss" />
<link href="${sidecss}" rel="stylesheet" />

</head>

<body>

	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div
		style=" width: 100%; overflow: hidden; background-color: #222;"
		class="wrapper">

		<!-- Include sidebar -->
		<%@ include file="/WEB-INF/views/body/sidebar.jsp"%>

		<div style="margin-left: 60px;" class="flex-item main-area-profile"
			id="main-area">

			<div id="profile-area" class="col-lg-3 col-lg-push-9 white-border" style="margin-bottom:20px !important;">
				<div id="profile-data">
					<h3 class="text-center" style="color: white;">${user.getName()}</h3>
					<div id="profile-pic-div">
						<img class="profile-picture circle-image"
							src="/resources/images/badge.jpg">
					</div>

					<h6 class="text-center" style="color: white;">Email:
						${user.getEmail()}</h6>
					<h6 class="text-center" style="color: white;">Location:
						UNKNOWN</h6>
					<h6 class="text-center" style="color: white;">Level: UNKNOWN</h6>

				</div>
				<c:if test="${visitor == true && notFriends== true}">
					<div id="profile-buttons">
						<a href="/learner/profile/add-friend/${learner.getId()}">
							<button type="button" class="btn btn-primary profile-button"
								id="friend-button">Add to Friends</button>
						</a>
					</div>
				</c:if>

			</div>

			<div id="content-area" class="col-lg-9 col-lg-pull-3">

				<div id="badges">

					<div id="badges-carousel" class="carousel slide white-border"
						data-ride="carousel" data-interval="false">
						<h2 id="carousel-title" class="text-center" style="color: white;">BADGES</h2>
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#badges-carousel" data-slide-to="0"
								class="active"></li>
							<li data-target="#badges-carousel" data-slide-to="1"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active inner-badges">
								<img class="badge-image img-circle"
									src="/resources/images/badge.jpg"> <img
									class="badge-image img-circle"
									src="/resources/images/badge.jpg"> <img
									class="badge-image img-circle"
									src="/resources/images/badge.jpg"> <img
									class="badge-image img-circle"
									src="/resources/images/badge.jpg">
							</div>

							<div class="item inner-badges">
								<img class="badge-image" src="/resources/images/placeholder.jpg">
							</div>
						</div>

						<!-- Left and right controls -->
						<div id="badge-control">
							<a class="left carousel-control" href="#badges-carousel"
								role="button" data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#badges-carousel"
								role="button" data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>

				</div>

				<div id="courses" style="margin-bottom: 200px;">
					<div id="courses-carousel" class="carousel slide white-border"
						data-ride="carousel" data-interval="false">
						<h2 class="text-center" id="carousel-title" style="color: white;">COMPLETED
							COURSES</h2>
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#courses-carousel" data-slide-to="0"
								class="active"></li>

							</section>
							<li data-target="#courses-carousel" data-slide-to="1"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active inner-courses">
								<img class="courses-image"
									src="/resources/images/placeholder.jpg"> <img
									class="courses-image" src="/resources/images/placeholder.jpg">
								<img class="courses-image"
									src="/resources/images/placeholder.jpg">
							</div>

							<div class="item inner-courses">
								<img class="courses-image"
									src="/resources/images/placeholder.jpg">
							</div>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#courses-carousel"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#courses-carousel"
							role="button" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>