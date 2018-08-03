<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Administrator Web Panel - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/admin-style.css" var="adminstylecss" />
<link href="${adminstylecss}" rel="stylesheet" />
<spring:url value="/resources/css/star-rating/star-rating.css"
	var="starRatingCss" />
<link href="${starRatingCss}" rel="stylesheet" />

<!-- Scripts -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="/resources/js/navscript.js"></script>
<script src="/resources/js/star-rating/star-rating.js"></script>

</head>

<body style="background-color: #666666; padding-top: 100px;">
	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div class="form-title"></div>

	<div class="jumbotron" style="margin-bottom: 0;">
		<div class="text-center">
			<h1>Administrator Web Panel</h1>
		</div>
	</div>

	<div class="jumbotron">
		<div class="text-center">
			<a class="btn btn-primary btn-block" href="/admin/user-search">User
				search</a> <a class="btn btn-primary btn-block" href="/courses">Courses
				list</a>
		</div>
	</div>

	<div class="table-content jumbotron">
		<table class="table table-stripped">
			<thead>
				<tr>
					<th><h3 class="white">Reported Reviews
							(${allReviews.size()})</h3></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allReviews.isEmpty()}">
					<tr>
						<td><em>No reports to show</em></td>
					</tr>
				</c:if>
				<c:forEach items="${allReviews}" var="review">
					<tr>
						<td>
							<div class="card text-center">
								<div class="card-header">
									<span class="glyphicon glyphicon-user"></span>
									${review.getLearner().getName()}
								</div>
								<div class="card-block">
									<p class="card-text">${review.getComment()}</p>
								</div>
								<div class="card-footer text-muted">
									<span> <label class="control-label" for="overallRating">Overall</label>
										<input id="overallRating" type="text"
										value="${review.getOverallRating()}" class="rating"
										data-display-Only="true" data-size="xs" data-rtl="false" /> <br />
										<p>
											From course: <em><a
												href="/admin/course/${review.getCourse().getId()}">${review.getCourse().getName() }</a></em>
										</p> <form:form action="/admin/panel-review-delete" method="post"
											modelAttribute="delete">
											<form:hidden path="courseId"
												value="${review.getCourse().getId()}" />
											<form:hidden path="reviewId" value="${review.getId()}" />

											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />

											<a class="btn btn-secondary"
												href="/admin/user?id=${review.getLearner().getId()}">
												User details</a>
											<a href="/admin/review-dismiss?id=${review.getId()}"
												class="btn btn-secondary">Dismiss report</a>
											<button type="submit" class="btn btn-danger">Delete
												review</button>
										</form:form>
									</span>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>