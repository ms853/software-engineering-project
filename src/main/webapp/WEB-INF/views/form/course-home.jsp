<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Course - Get Digital</title>

<!-- include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/course.css" var="coursecss" />
<link href="${coursecss}" rel="stylesheet" />
<spring:url value="/resources/css/star-rating/star-rating.css"
	var="starRatingCss" />
<link href="${starRatingCss}" rel="stylesheet" />
<spring:url value="/resources/css/responsive-course-page.css"
	var="responsivecoursecss" />
<link href="${responsivecoursecss}" rel="stylesheet" />

<!-- Scripts -->
<script src="/resources/js/navscript.js"></script>
<script src="/resources/js/star-rating/star-rating.js"></script>

</head>
<body class="course-background">

	<c:choose>
		<c:when test="${user == true}">
			<!-- include navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar.jsp"%>

		</c:when>
		<c:otherwise>
			<!-- include logged in navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

		</c:otherwise>

	</c:choose>

	<div class="modal fade" id="reportedModal" role="dialog"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="confirmModalClose"
						data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thanks for your report!</h4>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<h5>
							We have received your report and it will be reviewed shortly.<br />
							Thank you for making Get Digital a constructive environment for
							learning!
						</h5>
					</div>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		var openReportDiag = "<c:out value='${reported}'/>";
		jQuery(document).ready(function() {
			if (openReportDiag != "") {
				$("#reportedModal").modal("show");
			}
		});
	</script>
	<!-- Modal end -->

	<div style="padding-top: 100px;" class="row">
		<div class="jumbotron white-border" style="padding-bottom: 15rem;">
			<div class="col-md-4" id="left-col"
				style="margin-bottom: 30px; padding-right: 5%; padding-left: 5%">

				<h2 style="color: white; text-align: center;">
					<strong>${course.getName()}</strong>
				</h2>


				<h3 style="color: white; margin-left: 2em !important;">Instructor:
					${course.getTeacher().getName()}</h3>

				<c:if test="${admin}">
					<a class="btn btn-primary"
						href="/admin/user?id=${course.getTeacher().getId()}">User
						details</a>
				</c:if>

				<c:if test="${learner == true && learnerEnrolled != true}">
					<div style="text-align: center;">
						<a href="/learner/enrol?courseId=${course.getId()}"
							class="btn btn-primary">Enrol on this course</a>
					</div>
				</c:if>
				<c:if test="${user == true}">
					<div class="has-warning">
						<kbd>Login or register to enrol on this course.</kbd>
					</div>
				</c:if>

			</div>
			<div class="col-md-4" id="center-col"
				style="padding-bottom: 50px; text-align: center;">
				<div class="col-sm-12"></div>
				<div class="container">
					<label class="col-xs-4 control-label right" for="structureRating"
						style="color: white">Structure</label>
					<div class="col-xs-8">
						<input id="structureRating" type="text"
							value="${averageReviews.get(0)}" class="rating"
							data-display-Only="true" data-size="xs" data-rtl="false" />
					</div>


					<div class="col-sm-12"></div>
					<label class="col-xs-4 control-label right" for="difficultyRating"
						style="color: white">Difficulty</label>
					<div class="col-xs-8">
						<input id="difficultyRating" type="text"
							value="${averageReviews.get(1)}" class="rating"
							data-display-Only="true" data-size="xs" data-rtl="false" />
					</div>

					<div class="col-sm-12"></div>
					<label class="col-xs-4 control-label right" for="supportRating"
						style="color: white">Support</label>
					<div class="col-xs-8">
						<input id="supportRating" type="text"
							value="${averageReviews.get(2)}" class="rating"
							data-display-Only="true" data-size="xs" data-rtl="false" />
					</div>


					<div class="col-sm-12"></div>
					<label class="col-xs-4 control-label right" for="overallRating"
						style="color: white">Overall</label>
					<div class="col-xs-8">
						<input id="overallRating" type="text"
							value="${averageReviews.get(2)}" class="rating"
							data-display-Only="true" data-size="xs" data-rtl="false" />
					</div>
				</div>
			</div>

			<div class="col-md-4" style="padding-right: 5%; padding-left: 10%;">
				<img class="course-img " src="/resources/images/placeholder.jpg"
					alt="Card image cap" style="max-height: 240px !important;">
			</div>
		</div>
	</div>

	<div class="jumbotron row white-border">
		<!-- Course Details -->
		<div class="col-lg-8">
			<!-- About Course -->
			<blockquote class="blockquote">
				<h3 style="color: white">About Course</h3>
				<div class="blockquote-footer">Description:
					${course.getDescription()}</div>
				<div class="blockquote-footer">Category:
					${course.getCategory()}</div>
				<div class="blockquote-footer">Difficulty:
					${course.getLevel().toString()}</div>
				<div class="blockquote-footer">Start Date:
					${course.getStartDate()}</div>
				<c:if test="${teacher == true || admin == true}">
					<div class="blockquote-footer">Max Learners:
						${course.getMaxLearners()}</div>
				</c:if>
			</blockquote>

			<c:if test="${homeCourse == true || teacher == true}">
				<!-- Course content -->
				<blockquote class="blockquote">
					<h3>Link to slides</h3>
					<c:if test="${!files.isEmpty()}">
						<c:forEach items="${files}" var="file">
							<div class="blockquote-footer">
								<a href="${file}">${file}</a>
							</div>
						</c:forEach>
					</c:if>
				</blockquote>
			</c:if>
		</div>

		<!-- Learners on Course -->
		<c:if test="${user != true}">
			<div class="col-sm-4">
				<h3>Learners on this course</h3>
				<div class="table-responsive table-content">
					<table class="table table-hover table-stripped">
						<thead>
							<tr>
								<th>Name</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${learners}" var="varLearner">
								<tr>
									<td><c:out value="${varLearner.getLearner().getName()}" /></td>
									<td><c:out value="${varLearner.getLearner().getEmail()}" /></td>
									<c:if test="${learner == true }">
										<td><a href="/learner/profile/${varLearner.getId()}"
											rel="link">Profile</a></td>
									</c:if>
									<c:if test="${admin == true}">
										<td><a
											href="/admin/user?id=${varLearner.getLearner().getId()}"
											rel="link">User details</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>

	<c:if test="${allReviews.isEmpty() == false}">

		<div class="table-content">
			<table class="table table-stripped">
				<thead>
					<tr>
						<th><h3 class="white">Reviews</h3></th>
					</tr>
				</thead>
				<tbody>
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
											data-display-Only="true" data-size="xs" data-rtl="false" />
											<c:if test="${admin}">
												<br />
												<form:form action="/admin/review-delete" method="post"
													modelAttribute="delete">
													<form:hidden path="courseId" value="${course.getId()}" />
													<form:hidden path="reviewId" value="${review.getId()}" />

													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />

													<a class="btn btn-secondary"
														href="/admin/user?id=${review.getLearner().getId()}">
														User details</a>
													<button type="submit" class="btn btn-danger">Delete
														review</button>
												</form:form>
											</c:if> <c:if
												test="${learner && thisLearner != null && review.getLearner().getId() != thisLearner.getId()}">
												<br />
												<a class="btn btn-danger"
													href="/learner/course/report-review?id=${review.getId()}">Report
													review</a>
											</c:if> <c:if test="${teacher}">
												<br />
												<a class="btn btn-danger"
													href="/teacher/course/report-review?id=${review.getId()}">Report
													review</a>
											</c:if>
										</span>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>

	<div class="row">
		<!-- include footer -->
		<%@ include file="/WEB-INF/views/body/footer.jsp"%>
	</div>

</body>
</html>
