<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>About - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

</head>

<body>

	<c:choose>
		<c:when test="${user != true}">
			<!-- include logged in navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>
		</c:when>
		<c:otherwise>
			<!-- include navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar.jsp"%>
		</c:otherwise>
	</c:choose>
	
	<div class="form-title"></div>

	<div class="container">	<!-- Container start -->
	
		<div class="page-header"> <!-- Page header start -->
			<!-- Logo -->
			<div class="text-center logo">
				<a href="/" rel="logo"><img
					src="/resources/images/GetDigital.png" /></a>
			</div>
		</div> <!-- Page header end -->

		<div class="col-lg-1"></div>
		<div class="col-lg-10">
		
			<div class="jumbotron white-border">	<!-- jumbotron start -->
				<p><br /></p>

				<!-- Main body -->
				<div class="text-center">
					<h3>About Get Digital</h3>

					<p>
						We are a place for Education.<br />
						A platform for teachers to pass on their knowledge <br />
						as well as a platform for learners to expand their knowledge.
					</p>

					<p>
						In order to become a teacher or a learner, you must sign up through our sign up page
					</p>
					<br /><br />

					<a id="regbtn" href="/" class="btn btn-danger">Return to home</a>
				</div>

			</div> <!-- Jumbotron end -->
		</div>
	</div> <!-- Container end -->

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>
