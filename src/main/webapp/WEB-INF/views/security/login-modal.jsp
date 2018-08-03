<!-- Login Modal -->

<div class="modal fade" id="loginModal" role="dialog" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">

		<!-- Login -->
		<div class="modal-content" id="loginModalContent">
			<div class="modal-header">
				<button type="button" class="close" id="loginModalClose"
					data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Login</h4>
			</div>
			<div class="modal-body">
				<div class="text-center">
					<img style="height: 150px;" src="/resources/images/GetDigital.png" />
				</div>

				<div id="login-content">
					<c:url value="/login" var="loginUrl" />
					<form:form class="form-horizontal" id="loginForm"
						action="${loginUrl}" method="post" modelAttribute="user">
						<br />

						<!-- Email -->
						<div class="form-group">
							<label for="username"
								class="col-sm-3 control-label right-aligned">Email</label>
							<div class="col-sm-8">
								<input class="form-control" type="text"
									placeholder="Enter your email" id="loginUsername"
									name="username" />
							</div>
						</div>

						<!-- Password -->
						<div class="form-group">
							<label class="col-sm-3 control-label right-aligned"
								for="password">Password</label>
							<div class="col-sm-8">
								<input class="form-control" type="password"
									placeholder="Enter your password" id="loginPassword"
									name="password" />
							</div>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<button id="loginBtn" type="submit"
							class="btn btn-primary btn-block">Login</button>

						<div class="text-center small">
							<a id="show-password-reset-content" href="#">Forgotten your
								password?</a>
						</div>

						<div class="form-group">
							<br />

							<div id="loginFormSuccess" class="hide text-success text-center">Instructions
								on how to verify your account have been sent to your email
								address.</div>
							<div id="loginFormError" class="hide text-danger text-center">Invalid
								email address or password. Please try again.</div>

						</div>

					</form:form>
				</div>

				<div id="password-reset-content">
					<div class="text-center">
						<h5>Forgotten your password?</h5>
						<p>
							If you have forgotten your password, please enter your account's
							email address below.<br /> Instructions on how to reset your
							password will be sent to your email address.<br />
						</p>
					</div>

					<c:url value="/reset-password/request" var="resetUrl" />
					<form:form id="resetForm" class="form-horizontal"
						action="${resetUrl}" method="post" modelAttribute="resetPassword">

						<!-- Email -->
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label right-aligned">Email</label>
							<div class="col-sm-8">
								<input class="form-control" type="text"
									placeholder="Enter your email" id="resetEmail" name="email" />
							</div>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<button id="resetBtn" type="submit"
							class="btn btn-primary btn-block">Send instructions</button>

						<div class="text-center small">
							<a id="show-login-content" href="#">Return to login</a>
						</div>

						<div class="form-group">
							<br />

							<div id="resetFormSuccess" class="hide text-success text-center">Instructions
								on how to reset your password have been sent to your email
								address.</div>
							<div id="resetFormError" class="hide text-danger text-center">There
								is no user registered with that email. Please try again.</div>

						</div>

					</form:form>
				</div>

			</div>
		</div>

		<!-- TFA -->
		<div class="modal-content hide" id="tfaModalContent">
			<div class="modal-header">
				<h4 class="modal-title">Two-Factor Authentication</h4>
			</div>
			<div class="modal-body">
				<div class="text-center">
					<img style="height: 150px;" src="/resources/images/GetDigital.png" />
				</div>


				<div id="tfa-content">
					<div class="text-center">
						<h5>Two-Factor Authentication</h5>
						<p>In order to access content on this account, please enter
							your code from your authenticator app below.</p>
					</div>

					<c:url value="/auth-continue/2fa" var="tfaUrl" />
					<form:form class="form-horizontal" id="tfaForm" action="${tfaUrl}"
						method="post" modelAttribute="tfa">
						<br />

						<!-- Code -->
						<div class="form-group">
							<label for="code" class="col-sm-3 control-label right-aligned">Code</label>
							<div class="col-sm-8">
								<input class="form-control" type="text"
									placeholder="Enter your authenticator code" id="tfaCode"
									name="code" />
							</div>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<button id="tfaBtn" type="submit"
							class="btn btn-primary btn-block">Verify code</button>

						<div class="text-center small">
							<a href="/logout">Logout</a>
						</div>

						<div class="form-group">
							<br />

							<div id="tfaFormError" class="hide text-danger text-center">Incorrect
								authenticator code. Please try again.</div>

						</div>

					</form:form>
				</div>

			</div>
		</div>

	</div>
</div>
<!-- Modal end -->

<!-- Login Modal scripts -->
<script type="text/javascript">
    var loginModalLoadAction = "<c:out value='${loginModalLoadAction}'/>";

	function showLoginContent() {
		$("#password-reset-content").hide();
		$("#login-content").show();
	}

	function showPasswordResetContent() {
		$("#login-content").hide();
		$("#password-reset-content").show();
	}

	$("#loginModal").on("show.bs.modal", function() {
		$("#loginFormSuccess").hide();
		$("#loginFormError").hide();
		showLoginContent();
	});

	$("#show-password-reset-content").click(function(e) {
		e.preventDefault();
		showPasswordResetContent();
	});

	$("#show-login-content").click(function(e) {
		e.preventDefault();
		showLoginContent();
	});
</script>
<script src="/resources/js/login.js"></script>
<script src="/resources/js/2fa.js"></script>
<script src="/resources/js/password-reset-request.js"></script>
<!-- Modal scripts end -->