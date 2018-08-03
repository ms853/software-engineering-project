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

					<input id="longitude" type="hidden" value="${longitude}"/>
					<input id="latitude" type="hidden" value="${latitude}"/>
					
					<!-- Map id -->
					<div id="map"></div>

					<!-- Map Implementation -->
					<script type="text/javascript">
						$(document)
								.ready(
										function initMap() {
											
											var latitude = $("#latitude").val();
											var longitude = $("#longitude").val();
											
											var mymap = L.map('map').setView(
													[ longitude, latitude ], 13);

											mapboxgl.accessToken = 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg';

											var map = new mapboxgl.Map(
													{
														container : 'map',
														style : 'mapbox://styles/mapbox/streets-v9',
														center : [ latitude,longitude],
														zoom : 8
													});
											
											var popup = L.popup().setLatLng([longitude,latitude]).setContent("Your meetup location.").openOn(mymap);
											
										});
					</script>

		</div>
	</div>


	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>
</body>
</html>