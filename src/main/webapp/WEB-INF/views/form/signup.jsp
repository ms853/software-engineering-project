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
<title>Sign Up - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

</head>

<body>

	<!-- Include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar.jsp"%>

	<div class="form-title"></div>

	<!-- Sign up form -->
	<div class="container" style="min-height:100%;">
		<!-- Container start -->
		<div class="page-header">
			<!-- Logo -->
			<div class="text-center logo">
				<a href="/" rel="logo"><img
					src="/resources/images/GetDigital.png" /></a>
			</div>
		</div>

		<div class="col-lg-1"></div>
		<div class="col-lg-10">
			<div class="jumbotron">
				<!-- jumbotron start -->
				<p>
					<br />
				</p>

				<!-- Main Form -->
				<form:form class="form-horizontal" id="registration-form"
					action="/signup" method="POST" modelAttribute="user">

					<!-- Sign up as -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned">Sign up
							as</label>
						<div class="col-sm-8 btn-group btn-group-s btn-toggle">
							<button id="newLearner" type="button" class="btn active btn-info"
								data-toggle="tab" onclick="learner()">Learner</button>
							<button id="newTeacher" type="button" class="btn btn-default"
								data-toggle="tab" onclick="teacher()">Teacher</button>
						</div>
					</div>

					<form:input id="userRole" type="hidden" path="userRole"
						value="LEARNER" />

					<!-- Display Name -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="name">Display
							name*</label>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="name"
								placeholder="Enter your full name" path="name" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="name" class="help-block"></form:errors>
					</span>

					<!-- Email -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="email">Email*</label>
						<div class="col-sm-8">
							<form:input type="email" class="form-control" id="email"
								placeholder="Enter your email" path="email" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="email" class="help-block"></form:errors>
					</span>

					<!-- Confirm Email -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="email2">Re-enter
							Email*</label>
						<div class="col-sm-8">
							<form:input type="email2" class="form-control" id="email2"
								placeholder="Confirm your email" path="email2" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="email2" class="help-block"></form:errors>
					</span>

					<!-- Password -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="password">Password*</label>
						<div class="col-sm-8">
							<form:input type="password" class="form-control" id="password"
								placeholder="Enter an alpha-numeric password" path="password" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="password" class="help-block"></form:errors>
					</span>

					<!-- Confirm Password -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="password2">Re-enter
							Password*</label>
						<div class="col-sm-8">
							<form:input type="password" class="form-control" id="password2"
								placeholder="Confirm your password" path="password2" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="password2" class="help-block"></form:errors>
					</span>

					<!-- Submit -->
					<br />
					<div class="col-sm-8 col-sm-offset-3">

						<button type="submit" id="regbtn" class="btn btn-success">Sign
							up</button>

					</div>

				</form:form>
				<!-- End of Main form -->

				<br /><p></p><br />

				<form class="form-horizontal" action="/">
					<div class="col-sm-8 col-md-offset-3">
						<button type="submit" id="cancel" class="btn btn-danger">Cancel</button>
					</div>
				</form>

			</div>
			<!-- Jumbotron end -->
		</div>
	</div>
	<!-- Container end -->

	<!-- End of Sign up form -->

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>


	<!-- Scripts -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="/resources/js/signup.js"></script>
	<script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			if (window.location.hash == "#teacher") {
				$("#newTeacher").trigger("click");
			}
			
		});
	</script>

</body>
</html>