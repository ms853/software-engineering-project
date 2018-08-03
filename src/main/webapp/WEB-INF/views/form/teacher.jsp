<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Teacher - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/teacher-style.css"
	var="teacherstylecss" />
<link href="${teacherstylecss}" rel="stylesheet" />

<!-- Scripts -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/1.2.1/bootstrap-filestyle.js"></script>

</head>

<body id="teacher-background">

	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>
	<div class="form-title" style="background: none;"></div>

	<c:if test="${emailNotVerified == true}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#myModal').modal({
					backdrop : 'static',
					keyboard : false
				})
				$('#myModal').modal('show');
			});
		</script>
	</c:if>

	<!-- Warning modal when email not verified -->
	<%@ include file="/WEB-INF/views/modal/warning-modal.jsp"%>

	<section id="top-section" style="height: auto">
		<div class="text-center">
			<div class="page-header"
				style="color: white; margin: 0; border-bottom: none;">
				<h1>Welcome ${teacher.getName()}</h1>
			</div>
		</div>
	</section>

	<c:choose>
		<c:when test="${course == null}">
			<div class="jumbotron" style="margin-bottom: 0;">
				<div class="container" id=course-buttons>
					<a href="/teacher/create-course/">


						<div class="col-lg-5 course-button white-border">
							<div class=col-xs-3>
								<i id="book" class="fa fa-book fa-2x "></i>
							</div>
							<div class=col-xs-8>
								<h5 style="color: white; margin-top: 5px; margin-left: 15%;">Create
									Course</h5>
							</div>


						</div>
					</a> <a href="/courses/"><div
							class="col-lg-5 course-button white-border">
							<div class=col-xs-3>
								<i id="star" class="fa fa-search fa-2x"></i>
							</div>
							<div class=col-xs-8 style="margin-top: 5px;">
								<h5 style="color: white; margin-left: 15%;">View Courses</h5>
							</div>
						</div></a>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<div class="jumbotron white-border">
				<div class="container">
				<!-- My Course -->
				<div class="col-sm-4 blue-border">
					<h3>
						My Course : <a href="/teacher/course/${course.getId()}">${course.getName()}</a>
					</h3>
						<img id="course-img" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					
				</div>

				<!-- Learners on Course -->
				<div class="col-sm-4 blue-border">
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
								<c:forEach items="${course.getLearners()}" var="learners">
									<tr>
										<td><c:out value="${learners.getName()}" /></td>
										<td><c:out value="${learners.getEmail()}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<!-- Not Enrolled Learners -->
				<div class="col-sm-4 blue-border">
					<h3>Learners not enrolled</h3>
					<div class="table-responsive table-content">
						<table class="table table-hover table-stripped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${learnersList}" var="learner">
									<tr>
										<td><c:out value="${learner.getLearner().getName()}" /></td>
										<td><c:out value="${learner.getLearner().getEmail()}" /></td>
										<c:choose>
											<c:when
												test="${learner.getInvitedCourse() == null || learner.getInvitedCourse() != course}">
												<td><a
													href="/teacher/invite/${learner.getLearner().getId()}/${course.getId()}"
													rel="link">Invite</a></td>
											</c:when>
											<c:otherwise>
												<td><span class="has-error">Invited</span></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button class="btn btn-primary" style="width:100%; font-size: 10pt;"
						onclick="location.href='/teacher/meetup/${course.getId()}';">Arrange Meetup</button>
				</div>

			</div>

			</div>

			<div class="jumbotron white-border" id="file-upload-view">
				<div class="container">
					<h3 style="color: white;">Course files</h3>

					<form class="form-horizontal" method="POST"
						action="/teacher/course/${course.getId()}/upload/new-upload"
						enctype="multipart/form-data">

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />


						<!-- Choose file -->
						<script>
							function setfilename(val) {
								var fileName = val.substring(val
										.lastIndexOf("\\") + 1, val
										.lastIndexOf("."));
								var ext = val.substr(val.lastIndexOf("."),
										val.length);
								document.getElementById("filename").value = fileName;
								document.getElementById("ext").value = ext;
							}
							//window.onload=
							function displayFileName(val) {
								var fileName = val.substring(val
										.lastIndexOf("\\") + 1, val.length);
								document.getElementById("file-display-name").innerHTML = fileName;
							}
						</script>
						<div class="form-group">
							<label class="col-sm-3 control-label right" style="color: white;">Upload
								Content</label>
							<div class="col-sm-8">
								<input class="filestyle" data-buttonName="btn btn-primary"
									type="file" name="file" onchange="setfilename(this.value);"
									style="color: white;" /> <input type="text" id="filename"
									name="fileName" style="color: white;" /><input type="text"
									id="ext" name="ext" readonly="readonly"
									style="width: 50px; color: white;" />
							</div>
						</div>
						<c:if test="${!message.isEmpty()}">
							<span class="col-sm-8 col-md-offset-3" style="color:white;"> <b
								>${message} </b>
							</span>
						</c:if>

						<!-- Submit -->
						<br />
						<div class="col-sm-8 col-sm-offset-3">
							<button type="submit" id="regbtn" class="btn btn-success">Upload</button>
						</div>
					</form>



					<div class="col-sm-8 container">
						<p>
							<br />
						</p>
						<c:if test="${!files.isEmpty()}">
							<div class="table-responsive table-content">
								<table class="table table-hover table-stripped">
									<thead>
										<tr>
											<th style="color: white;">Uploaded files</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${files}" var="file">
											<tr>
												<td><a href="${file}"
													onload="displayFileName(this.value)" id="file-display-name">${file}</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<c:if test="${meetups.isEmpty() == false}">
				<!-- Meetups -->
				<div class="jumbotron white-border" id="file-upload-view">
					<div class="container">
						<h3 style="color: white;">Meetups</h3>
					
						<div class="table-responsive table-content">
								<table class="table table-stripped">
									<thead>
										<tr>
											<th style="color: white;">Scheduled Date</th>
											<th style="color: white;">Longitude</th>
											<th style="color: white;">Latitude</th>
											<th></th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${meetups}" var="meetup">
											<tr>
												<td>${meetup.getScheduledDate()}</td>
												<td>${meetup.getLongitude()}</td>
												<td>${meetup.getLatitude()}</td>
												<td><a style="color: darkgray;" href="#">Map</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
					
					</div>
				</div>
			</c:if>

		</c:otherwise>
	</c:choose>


	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>