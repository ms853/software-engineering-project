<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Get Digital</title>

<!-- Favicon -->
<link rel="shortcut icon" href="/resources/images/favicon.ico">
<link rel="icon" type="image/png"
	href="/resources/images/favicon-192x192.png" sizes="192x192">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
	integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
	crossorigin="anonymous">

<!-- Stylesheets -->
<spring:url value="/resources/css/learner-style.css" var="stylecss" />
<link href="${stylecss}" rel="stylesheet" />
<spring:url value="/resources/css/reviews.css" var="reviewcss" />
<link href="${reviewcss}" rel="stylesheet" />
<spring:url value="/resources/css/responsive-sign-up.css"
	var="responsivecss" />
<link href="${responsivecss}" rel="stylesheet" />

<!-- Font-Awesome -->
<spring:url
	value="/resources/css/font-awesome-4.7.0/css/font-awesome.css"
	var="fontawesome" />
<link href="${fontawesome}" rel="stylesheet" />

<!-- Scripts -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="/resources/js/navscript.js"></script>
<script>
	$(document).ready(function() {
		$("#toggle-sidebar-button").click(function() {
			var sidebar = $('#main-area');
			if (sidebar.css('margin-left') == '60px') {

				$('#main-area').animate({
					'marginLeft' : "300px"
				}, function() {
					$('#sidebar-contents').css('margin-left', '60px');
					$('#sidebar-contents').css('visibility', 'visible');
				});

			} else {
				$('#main-area').animate({
					'marginLeft' : "60px"
				});
				$('#sidebar-contents').css('visibility', 'hidden');
			}
		});
	});
</script>

</head>

<body>
	<!-- Navbar -->
	<div>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">LOGO HERE</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" style="margin-left: 75px;">
						<li><a href="#">About</a></li>
						<li><a href="#">Pricing</a></li>
						<li><a href="#">Courses</a></li>
					</ul>
					<form class="navbar-form navbar-right">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</form>

					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>
								My Profile</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
								Log Out</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>

	<div style="width: 100%; overflow: hidden; background-color: #222;"
		class="wrapper">
		<div style="float: left;" class="fixed">
			<div class="sidebar" id="sidebar">
				<div id="sidebar-contents"
					style="position: absolute; visibility: hidden;">
					<h2>THIS IS A TEST OF THE SIDEBAR</h2>
				</div>
				<div id="toggle-sidebar-button">
					<img src="resources/images/Hamburger_icon.png"
						style="height: 100; width: 40px;"></img>
				</div>
			</div>
		</div>
		<div style="margin-left: 60px; margin-top: 80px" class="flex-item"
			id="main-area">
			<!-- Signup Code - Needs optimising -->

			<div id="title-bar" class="text-center">
				<h1>[Course Name] : Reviews</h1>
				<h3>Teacher Name:</h3>
				<h3>Course Details:</h3>
			</div>

			<div id="statistic">
				<div class="container">
					<div class="col-lg-3" id="left-review">
						<h3>[Overall Ratings]</h3>
						<h3>[stars]</h3>
						<h3>Average Rating</h3>
					</div>
					<div class="col-lg-8" id="right-review">
						<table>
							<tr class="row">
								<td class="bar">
									<div class="bar-rect">
										<div class="bar-fill" style="width: 50%;"></div>
									</div>
								</td>
								<td class="stars_wrapper"><img
									src="resources/images/stars/5 stars.png" class="star"></img></td>
								<td class="percentage">0%</td>
							</tr>
							<tr class="row">
								<td class="bar"><div class="bar-rect">
										<div class="bar-fill" style="width: 25%;"></div>
									</div></td>
								<td class="stars_wrapper"><img
									src="resources/images/stars/4 stars.png" class="star"></img></td>
								<td class="percentage">0%</td>
							</tr>
							<tr class="row">
								<td class="bar"><div class="bar-rect">
										<div class="bar-fill" style="width: 5%;"></div>
									</div></td>
								<td class="stars_wrapper"><img
									src="resources/images/stars/3 stars.png" class="star"></img></td>
								<td class="percentage">0%</td>
							</tr>
							<tr class="row">
								<td class="bar"><div class="bar-rect">
										<div class="bar-fill" style="width: 5%;"></div>
									</div></td>
								<td class="stars_wrapper"><img
									src="resources/images/stars/2 stars.png" class="star"></img></td>
								<td class="percentage">0%</td>
							</tr>
							<tr class="row">
								<td class="bar"><div class="bar-rect">
										<div class="bar-fill" style="width: 15%;"></div>
									</div></td>
								<td class="stars_wrapper"><img
									src="resources/images/stars/1 stars.png" class="star"></img></td>
								<td class="percentage">0%</td>
							</tr>
						</table>

					</div>
				</div>
			</div>

			<!--  Dynamically form -->
			<div id="review_wrapper">
				<div class="container">
					<div class="col-lg-4" id="left-review">
						<div class="col-lg-5">
							<img id="profile-pic" src="resources/images/badge.jpg"></img>
						</div>
						<div class="col-lg-6">
							<p>[Review Date]</p>
							<h5>[User Name]</h5>
							<a>Report</a>
						</div>
					</div>
					<div class="col-lg-8" id="right-review">
						<div id="review-stars">
							<img src="resources/images/stars/5 stars.png"
								class="profile-star">
						</div>
						<div id="review-content">[LOTS OF TEXT GOES HERE
							BLAHALSPJFOFJFOJFOJFOFJOFJFOJEGOEJGEOGJ GKNGK NKG NRENG EKNG EK
							NGK GNEK NGK NE KGNE KNG KENG EKGN EKNG EKG NEKGn ]</div>
					</div>
				</div>

			</div>

			<div id="show-more">
				<div class="container text-center">
					<button class="btn btn-primary btn-sx" id="show-more-button">Show
						More</button>
				</div>
			</div>

		</div>
	</div>



	<section id="footer">
		<div class='title'>
			<b>FOOTER</b>
		</div>
	</section>

</body>
</html>