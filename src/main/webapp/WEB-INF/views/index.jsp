<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

</head>
<body>

	<!-- Include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar.jsp"%>

	<!-- Sign up options -->
	<section id="signup">
		<div class='split-pane col-xs-12 col-sm-6 uiux-side'>
			<div>
				<img src='https://bit.ly/BCR-design'>
				<div class='text-content'>
					<div>Want to...</div>
					<div class='big'>LEARN?</div>
				</div>
				<a href="/signup" class='button'>Sign Up</a>
			</div>
		</div>
		<div class='split-pane col-xs-12 col-sm-6 frontend-side'>
			<div>
				<img src='https://bit.ly/bcr-dev'>
				<div class='text-content'>
					<div>Want to...</div>
					<div class='big'>TEACH?</div>
				</div>
				<a href="/signup#teacher" class='button'>Sign Up</a>
			</div>
		</div>
	</section>

	<!-- Courses -->
	<section id="courses-mini">
		<div class='title'>
			<b>OUR COURSES</b>
		</div>

		<c:if test="${item1.isEmpty() == false}">
			<div id="myCarousel" class="carousel slide white-border" data-ride="carousel">
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
										src="resources/images/placeholder.jpg" alt="Card image cap">
									<div class="card-block">
										<h5 class="card-title">
											<a href="/courses/course/${item1.get(0)}">${item1.get(1)}</a>
										</h5>
										<p class="card-text">${item1.get(2)}</p>
										<p class="card-text">
											<small class="text-muted">${item1.get(3)}</small>
										</p>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- Item 2 -->
					<c:if test="${item2.isEmpty() == false}">
						<div class="item">
							<div class="card-group">
								<c:forEach items="${item2}" var="item2">
									<div class="card">
										<img class="card-images-top"
											src="resources/images/placeholder.jpg" alt="Card image cap">
										<div class="card-block">
											<h5 class="card-title">
												<a href="/courses/course/${item2.get(0)}">${item2.get(1)}</a>
											</h5>
											<p class="card-text">${item2.get(2)}</p>
											<p class="card-text">
												<small class="text-muted">${item2.get(3)}</small>
											</p>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>

					<!-- Item 3 -->
					<c:if test="${item3.isEmpty() == false}">
						<div class="item">
							<div class="card-group">
								<c:forEach items="${item3}" var="item3">
									<div class="card">
										<img class="card-images-top"
											src="resources/images/placeholder.jpg" alt="Card image cap">
										<div class="card-block">
											<h5 class="card-title">
												<a href="/courses/course/${item3.get(0)}">${item3.get(1)}</a>
											</h5>
											<p class="card-text">${item3.get(2)}</p>
											<p class="card-text">
												<small class="text-muted">${item3.get(3)}</small>
											</p>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</div>

				<c:if test="${item2.isEmpty() == false}">
					<!-- Carousel controls -->
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a>
					<a class="carousel-control right" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</c:if>
			</div>
		</c:if>

		<!-- Buttons -->
		<div class="text-center">
			<button type="button" class="btn btn-primary btn-lg"
				onclick="location.href='/courses';">Show All</button>

			<!-- Secondary, outline button -->
			<button type="button" class="btn btn-secondary btn-lg">Popular</button>
		</div>
	</section>

	<!--Rating section, currently ratings will change every 3 seconds, no controls yet-->
	<section id="best-ratings">

		<div id="myCarousel2" class="carousel slide white-border" data-ride="carousel">
			<!-- Carousel indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel2" data-slide-to="1"></li>
				<li data-target="#myCarousel2" data-slide-to="2"></li>
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
			<a class="carousel-control left" href="#myCarousel2"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span>
			</a> <a class="carousel-control right" href="#myCarousel2"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>

	</section>

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>