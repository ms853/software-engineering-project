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
<title>Reset Password - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

</head>

<body>

	<div class="form-title"></div>

	<!-- Password reset form -->
	<div class="container">
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

				<div class="text-center">
					<h4><em>Password reset for ${displayName} (${email})</em></h4>
					<h5>To reset your password for this account, enter your new
						password down below.</h5>
				</div>

				<!-- Main Form -->
				<form:form class="form-horizontal" id="password-reset-form"
					action="/reset-password" method="POST" modelAttribute="reset">

					<!-- UserID and Password Reset Token -->
					<form:hidden path="userId" />
					<form:hidden path="passwordResetId" />

					<!-- Password -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="password">New
							Password*</label>
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
							New Password*</label>
						<div class="col-sm-8">
							<form:input type="password" class="form-control" id="password2"
								placeholder="Confirm your password" path="password2" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="password2" class="help-block"></form:errors>
					</span>

					<!-- Submit -->
					<div class="col-sm-8 col-md-offset-3">
						<button type="submit" id="resetbtn" class="btn btn-default">Reset
							your password</button>
					</div>

				</form:form>
				<!-- End of Main form -->
			</div>
			<!-- Jumbotron end -->
		</div>
	</div>
	<!-- Container end -->

	<!-- End of form -->

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

	<!-- Scripts -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="/resources/js/password-reset.js"></script>
	<script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>

</body>
</html>
