<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Learner - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/learner-style.css" var="learnerstylecss" />
<link href="${learnerstylecss}" rel="stylesheet" />
<spring:url value="/resources/css/sidebar.css" var="sidecss" />
<link href="${sidecss}" rel="stylesheet" />


</head>

<body>
	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div style="width: 100%; overflow: hidden; background-color: #222;"
		class="wrapper">

		<!-- include sidebar -->
		<%@ include file="/WEB-INF/views/body/sidebar.jsp"%>

		<div style="margin-left: 60px;" class="flex-item" id="main-area">

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

			<c:if test="${course == null}">
				<script>
					$(document).ready(function() {
						$("#EnrolledCourse").addClass("hide");
					});
				</script>
			</c:if>

			<!-- Enrolled course -->
			<section id="EnrolledCourse">
				<div class="container white-border" id=Top-Section>
					<div class="col-md-3 main blue-border" id="To-Do-Col">
						<div class="center">
							<h3 class="blue">To-Do List:</h3>

							<ul>
								<li class="blue">Example 1</li>
								<li class="blue">Example 2</li>
								<li class="blue">Example 3</li>
							</ul>
						</div>
					</div>
					<div class="col-md-9" id="Course-Col">
						<div id="Current-Course" class="blue-border" >
							<h3 class="text-center blue">Current Course -
								${course.getName()}</h3>
							<div class="col-lg-3">
								<img id="course-img" src="/resources/images/placeholder.jpg"
									alt="Card image cap">
							</div>
							<div class="col-lg-9">
								<div id=course-buttons>
									<a href="/learner/course/${course.getId()}"><div
											class="col-lg-3 course-button white-border">
											<div class="col-xs-4 icon">
												<i id="book" class="fa fa-book fa-2x"></i>
											</div>
											<div class="col-xs-8 icon">
												<h6 style="color: white; margin-top: 5px;">Course Home
													Page</h6>
											</div>
										</div></a> <a href="/learner/course/${course.getId()}/review"><div
											class="col-lg-3 course-button white-border">
											<div class="col-xs-4 icon">
												<i id="star" class="fa fa-star fa-2x"></i>
											</div>
											<div class="col-xs-8 icon" style="margin-top: 5px;">
												<h6 style="color: white;">Review this course</h6>
											</div>
										</div></a> <a href="/learner/quit?courseId=${course.getId()}">
										<div class=" col-lg-3 course-button white-border">
											<div class="col-xs-4 icon">
												<i id="sign-out" class="fa fa-sign-out fa-2x"></i>
											</div>
											<div class="col-xs-8 icon">
												<h6 style="color: white; margin-top: 5px;">Quit this
													course</h6>
											</div>
										</div>
									</a>
								</div>

							</div>

						</div>
					</div>
				</div>

			</section>

			<!-- Recommended courses -->
			<c:if test="${item1.isEmpty() == false}">
				<section id="courses-mini">
					<div id="rec-courses" class="carousel slide white-border" data-ride="carousel">
						<h2 id="carousel-title" class="text-center" style="color: white;">Recommended
							Courses</h2>
						<!-- Carousel indicators -->
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<c:if test="${item2.isEmpty() == false}">
								<li data-target="#myCarousel" data-slide-to="1"></li>
							</c:if>
							<c:if test="${item3.isEmpty() == false}">
								<li data-target="#myCarousel" data-slide-to="2"></li>
							</c:if>
						</ol>

						<!-- Wrapper for carousel items -->
						<div class="carousel-inner">

							<!-- Item 1 -->
							<div class="item active">
								<div class="card-group">
									<c:forEach items="${item1}" var="item1">

										<div class="card">
											<img class="card-images-top"
												src="/resources/images/placeholder.jpg" alt="Card image cap">
											<div class="card-block">
												<h5 class="card-title" style="color: #336699">
													<a href="/learner/course/${item1.get(0)}">${item1.get(1)}</a>
												</h5>
												<p class="card-text" style="color: #336699">${item1.get(2)}</p>
												
												<c:if test="${course == null}">
												<div class="pull-right">
													<a class="btn btn-primary"
														href="/learner/enrol?courseId=${item1.get(0)}">Enrol</a>
												</div>
												</c:if>
												<p class="card-text">
													<small class="text-muted" >${item1.get(3)}</small>
												</p>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<!-- Item 2 -->
							<div class="item">
								<div class="card-group">
									<c:forEach items="${item2}" var="item2">
										<c:if test="${course.getName()!=item2.get(1)}">
											<div class="card">
												<img class="card-images-top"
													src="/resources/images/placeholder.jpg"
													alt="Card image cap">
												<div class="card-block">
													<h5 class="card-title" style="color: #336699">
														<a href="/learner/course/${item2.get(0)}">${item2.get(1)}</a>
													</h5>
													<p class="card-text" style="color: #336699">${item2.get(2)}</p>
													<div class="pull-right">
														<a class="btn btn-primary"
															href="/learner/enrol?courseId=${item2.get(0)}">Enrol</a>
													</div>
													<p class="card-text">
														<small class="text-muted">${item2.get(3)}</small>
													</p>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>

							<!-- Item 3 -->
							<div class="item">
								<div class="card-group">
									<c:forEach items="${item3}" var="item3">
										<c:if test="${course.getName()!=item3.get(1)}">
											<div class="card">
												<img class="card-images-top"
													src="/resources/images/placeholder.jpg"
													alt="Card image cap">
												<div class="card-block">
													<h5 class="card-title" style="color: #336699">
														<a href="/learner/course/${item3.get(0)}">${item3.get(1)}</a>
													</h5>
													<p class="card-text" style="color: #336699">${item3.get(2)}</p>
													<div class="pull-right">
														<a class="btn btn-primary"
															href="/learner/enrol?courseId=${item3.get(0)}">Enrol</a>
													</div>
													<p class="card-text">
														<small class="text-muted">${item3.get(3)}</small>
													</p>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>

						</div>

						<c:if test="${item2.isEmpty() == false}">
							<!-- Carousel controls -->
							<a class="carousel-control left" href="#rec-courses"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
							</a>
							<a class="carousel-control right" href="#rec-courses"
								data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</c:if>
					</div>

					<!-- Buttons -->
					<div class="text-center">
						<button type="button" class="btn btn-primary btn-lg" onclick="location.href='/courses';">Show
							All</button>

						<!-- Secondary, outline button -->
						<button type="button" class="btn btn-secondary btn-lg">Popular</button>
					</div>
				</section>
			</c:if>

			<!--Rating section, currently ratings will change every 3 seconds, no controls yet-->
			<section id="best-ratings" >

				<div id="myCarousel" class="carousel slide white-border" data-ride="carousel">
					<!-- Carousel indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- Wrapper for carousel items -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="/resources/images/placeholder.jpg" alt="First Slide">
						</div>
						<div class="item">
							<img src="/resources/images/placeholder1.jpg" alt="Second Slide">
						</div>
						<div class="item">
							<img src="/resources/images/placeholder.jpg" alt="Third Slide">
						</div>
					</div>
					<!-- Carousel controls -->
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a> <a class="carousel-control right" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>

			</section>

		</div>
	</div>

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>