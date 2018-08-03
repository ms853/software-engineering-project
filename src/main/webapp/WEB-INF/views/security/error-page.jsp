<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Error - Get Digital</title>

<!-- Favicon -->
<link rel="shortcut icon" href="/resources/images/favicon.ico">
<link rel="icon" type="image/png"
	href="/resources/images/favicon-192x192.png" sizes="192x192">

<!-- Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	
<!-- Style sheet -->
<spring:url value="/resources/css/footer-style.css" var="footercss" />
<link href="${footercss}" rel="stylesheet" />
<spring:url value="/resources/css/style.css" var="stylecss" />
<link href="${stylecss}" rel="stylesheet" />

</head>

<body>

	<div class="form-title" style="height:800px;"></div>

	<!-- Sign up form -->
	<div class="container" >
		<!-- Container start -->
		<div class="page-header">
			<!-- Logo -->
			<div class="text-center logo">
				<a href="/" rel="logo"><img
					src="/resources/images/GetDigital.png" /></a>
			</div>
		</div>

		<div class="col-lg-1"></div>
		<div class="col-lg-10" style="height:500px;">
			<div class="jumbotron" style="padding-bottom: 20rem;" >
				<!-- jumbotron start -->
				<p>
					<br />
				</p>

				<div class="col-sm-8 col-md-offset-3 has-error">
					<h3 class="help-block">${exception.getExceptionMsg()}</h3>
				</div>

				<form class="form-horizontal" action="${url}">
					<div class="col-sm-8 col-md-offset-3">
						<p></p>
						<button type="submit" id="cancel" class="btn btn-danger">Return</button>
					</div>
				</form>

			</div>
			<!-- Jumbotron end -->
		</div>
	</div>
	<!-- Container end -->

	<!-- End of Sign up form -->

	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>