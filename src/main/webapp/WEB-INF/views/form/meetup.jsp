<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Meetup - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/meetup.css" var="meetupcss" />
<link href="${meetupcss}" rel="stylesheet" />

<spring:url value="/resources/css/style.css" var="stylecss" />
<link href="${stylecss}" rel="stylesheet" />

<spring:url value="/resources/css/Control.OSMGeocoder.css"
	var="geoSearchCss" />
<link href="${geoSearchCss}" rel="stylesheet" />

<!-- Leaflet -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css"
	integrity="sha512-07I2e+7D8p6he1SIM+1twR5TIrhUQn9+I6yjqD53JQjFiMf8EtC93ty0/5vJTZGF8aAocvHYNEDJajGdNx1IsQ=="
	crossorigin="" />

<!-- Mapbox-->
<script src='https://api.mapbox.com/mapbox-gl-js/v0.36.0/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v0.36.0/mapbox-gl.css'
	rel='stylesheet' />

<!-- Script for Leaflet API -->
<script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"
	integrity="sha512-A7vV8IFfih/D732iSSKi20u/ooOfj/AGehOKq0f4vLT1Zr2Y+RX7C+w8A1gaSasGtRUZpF/NZgzSAu4/Gc41Lg=="
	crossorigin=""></script>
<spring:url value="/resources/js/navscript.js" var="navscriptjs"></spring:url>
<script src="${navscriptjs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<spring:url value="/resources/js/map.js" var="mapjs"></spring:url>
<script src="${mapjs}"></script>

<spring:url value="/resources/js/Control.OSMGeocoder.js"
	var="geoSearchJs"></spring:url>
<script src="${geoSearchJs}"></script>

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

<!-- Date picker -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

</head>

<body>

	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div style="width: 100%; overflow: hidden; background-color: #222;"
		class="wrapper">
		<div style="float: left;" class="fixed">
			<div class="sidebar" id="sidebar">
				<div id="sidebar-contents"
					style="position: absolute; visibility: hidden;">
					<h2>THIS IS A TEST OF THE SIDEBAR</h2>
				</div>
			</div>
		</div>
		<div style="margin-left: 60px; margin-top: 80px" class="flex-item"
			id="main-area">

			<div class="white-border container" style="padding-bottom: 20px;">
				<div id="title">
					<h1 class="text-center" id="title-text">Course Meet-up Details</h1>
				</div>

				<div id="map_wrapper">

					<!-- Map id -->
					<div id="map"></div>

					<!-- Map Implementation -->
					<script type="text/javascript">
						$(document)
								.ready(
										function initMap() {
											var mymap = L.map('map').setView(
													[ 52.6369, -1.1398 ], 13);

											mapboxgl.accessToken = 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg';

											var map = new mapboxgl.Map(
													{
														container : 'map',
														style : 'mapbox://styles/mapbox/streets-v9',
														center : [ -1.1398,
																52.6369 ],
														zoom : 8
													});

											var newMarker = null;

											function onMapClick(e) {
												if (confirm("Select ok if you like this to be your meetup place")) {
													if (newMarker != null) {
														mymap
																.removeLayer(newMarker);
													}
													
													alert(e.latlng);
													
													newMarker = new L.marker(
															e.latlng)
															.addTo(mymap);
													$("#location").prop("value",
															e.latlng);
												}
											}

											mymap.on('click', onMapClick);

										});
					</script>


					<div class="text-center blue-border" id="meetup_info">
						<div class="jumbotron">
							<!-- jumbotron start -->
							<p>
								<br />
							</p>

							<!-- Main Form -->
							<form:form class="form-horizontal" id="registration-form"
								action="/teacher/meetup/${course.getId()}" method="POST"
								modelAttribute="meetup">

								<!-- Course Title -->
								<div class="form-group">
									<label class="col-sm-3 control-label righ-aligned" for="courseName">Course
										Title</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control" id="courseName"
											value="${course.getName()}" path="courseName" />
									</div>
								</div>
								<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
										path="courseName" class="help-block"></form:errors>
								</span>

								<!-- Course Instructor -->
								<div class="form-group">
									<label class="col-sm-3 control-label righ-aligned"
										for="teacherName">Course Instructor</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control" id="teacherName"
											value="${course.getTeacher().getName()}" path="teacherName"
											 />
									</div>
								</div>
								<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
										path="teacherName" class="help-block"></form:errors>
								</span>

								<!-- Meetup Place -->
								<div class="form-group">
									<label class="col-sm-3 control-label righ-aligned" for="location">Meetup
										Place</label>
									<div class="col-sm-8">
										<form:input type="text" class="form-control" id="location"
											placeholder="Click on the map to select a place"
											path="location" />
									</div>
								</div>
								<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
										path="location" class="help-block"></form:errors>
								</span>

								<!-- Scheduled Date -->
								<div class="form-group">
									<label class="col-sm-3 control-label righ-aligned"
										for="scheduledDate">Scheduled Date</label>
									<div class="col-sm-8">
										<form:input type="text" id="scheduledDate"
											class="form-control" name="scheduledDate"
											path="scheduledDate" />
									</div>
								</div>
								<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
										path="scheduledDate" class="help-block"></form:errors>
								</span>
								<script>
									$(function() {
										$('input[name=scheduledDate]')
												.datepicker({
													inline : true,
													format : 'dd/mm/yyyy'
												});
									});
								</script>

								<!-- Submit -->
								<br />
								<div class="col-sm-8 col-sm-offset-3">

									<button type="submit" id="regbtn" class="btn btn-success">Arrange
										meetup</button>

								</div>

							</form:form>
							<!-- End of Main form -->

						</div>
					</div>

				</div>

			</div>


			<!-- <!-- This segment of the code is dead
			<div id="bottom-section-wrapper">
				<div id="route" class="col-lg-4">
					<h2 class="text-center">Route</h2>
					<div class="text-wrapper" style="height: 500px;"></div>
				</div>
				<div id="notes" class="col-lg-4">
					<h2 class="text-center">Notes/Announcements</h2>
					<div class="text-wrapper" style="height: 500px;"></div>
				</div>
				<div id="assignments" class="col-lg-4">
					<h2 class="text-center">Course Assignments</h2>
					<div class="text-wrapper" style="height: 500px;"></div>
				</div>
			</div> -->

		</div>
	</div>


	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>
</body>
</html>