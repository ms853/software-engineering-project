//-------------------- Attributes --------------------//

var valName = false, valEmail = false, valEmail2 = false, valPassword = false, valPassword2 = false;

// When an error occurs disable submit button, add error class
function doError(id) {
	var div = $("#" + id).closest("div");
	div.removeClass("has-success");
	$("#glypcn" + id).remove();
	div.addClass("has-error has-feedback");
	div
			.append('<span id="glypcn'
					+ id
					+ '" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>')
	$("#error" + id).remove();
	$("#empty" + id).remove();
	div
			.append('<span id="empty'
					+ id
					+ '" class="help-block" aria-hidden="true">Field is required</span>')
	$("#regbtn").prop("disabled", true);
}

// When validation passes remove error messages Add success class
function doSuccess(id) {
	var div = $("#" + id).closest("div");
	div.removeClass("has-error");
	div.addClass("has-success has-feedback");
	$("#glypcn" + id).remove();
	$("#error" + id).remove();
	$("#empty" + id).remove();
	div
			.append('<span id="glypcn'
					+ id
					+ '" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>')
}

// email validation using client-side and server checks
function validateEmail() {
	var div = $("#email").closest("div");
	var re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	if (re.test($("#email").val())) {

		// csrf
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");

		// query server to see if email doesn't already exist
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "signup/check-availability",
			data : $("#email").val(),
			dataType : 'json',
			timeout : 10000,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {

				console.log("SUCCESS: ", data);
				if (data.result == null) {
					// email doesn't already exist
					div.removeClass("loadinggif");
					valEmail = true;
					doSuccess("email");
				} else {
					// email already exists - cannot register with this
					div.removeClass("loadinggif");
					valEmail = false;
					customError("email", "This email is already registered");
				}

			},
			error : function(e) {

				// error communicating with server
				console.log("ERROR: ", e);
				div.removeClass("loadinggif");
				valEmail = false;
				customError("email",
						"Could not validate email, please try again later");

			}
		});

		return valEmail;
	} else {

		// email not valid format
		div.removeClass("loadinggif");
		valEmail = false;
		customError("email", "Enter a valid email address");
		return false;

	}

}

// custom error
function customError(id, message) {
	doError(id);
	var div = $("#" + id).closest("div");
	$("#empty" + id).remove();
	div.append('<span id="error' + id
			+ '" class="help-block" aria-hidden="true">' + message + '</span>')

}

// Validate inputs
function validateText(id) {
	// check if input is null or empty
	if ($("#" + id).val() == null || $("#" + id).val() == "") {

		doError(id);

	} else {

		// name
		if (id == "name") {
			var regName = /^([A-Za-z']+( [A-Za-z']+)*){3,30}$/;
			if (regName.test($("#name").val())) {
				valName = true;
				doSuccess("name");
			} else {
				valName = false;
				customError("name", "Enter your full name");
			}
		}

		// email
		if (id == "email") {
			var div = $("#email").closest("div");
			$("#glypcnemail").remove();
			$("#erroremail").remove();
			$("#emptyemail").remove();
			div.removeClass("has-success");
			div.removeClass("has-error");
			div.addClass("loadinggif");
			validateEmail();

			validateText("email2");
		}

		// confirm email
		if (id == "email2") {
			var reEmail = $("#email").val();
			if (reEmail == $("#email2").val()) {
				valEmail2 = true;
				doSuccess("email2");
			} else {
				valEmail2 = false;
				doError("email2");
				customError("email2", "Emails do not match");
			}
		}

		// password
		if (id == "password") {
			var pwd = $("#password").val()
			var letter = /[a-zA-Z]/;
			var number = /[0-9]/;
			if (number.test(pwd) && letter.test(pwd) && pwd.length >= 8) {
				valPassword = true;
				doSuccess("password");
			} else {
				valPassword = false;
				customError(
						"password",
						"Passwords should be at least 8 characters long and contain both letters & numbers");
			}

			validateText("password2");
		}

		// confirm password
		if (id == "password2") {
			var rePwd = $("#password").val();
			if (rePwd == $("#password2").val()) {
				valPassword2 = true;
				doSuccess("password2");
			} else {
				valPassword2 = false;
				doError("password2");
				customError("password2", "Passwords do not match");
			}
		}

	}
}

// Validation
jQuery(document).ready(

function validate() {
	// disable submit button
	$("#regbtn").prop("disabled", true);

	// input validation
	$('input').focusout(function() {

		validateText($(this).attr('id'));

		if (valName && valEmail && valEmail2 && valPassword && valPassword2) {
			$("#regbtn").prop("disabled", false);
		}

	});
	
	// change focus from cancel to registration button
	$("#cancel").focus(function() {
		$("#regbtn").focus();
	});
});

function learner() {

	// $("#userRole").setVal("learner");
	document.getElementById("userRole").setAttribute("value", "LEARNER");

	$("#newTeacher").removeClass("active btn-info");
	$("#newTeacher").addClass("btn-default");
	$("#newLearner").removeClass("btn-default");
	$("#newLearner").addClass("active btn-info");
}

function teacher() {

	// $("#userRole").setVal("teacher");
	document.getElementById("userRole").setAttribute("value", "TEACHER");

	$("#newLearner").removeClass("active btn-info");
	$("#newLearner").addClass("btn-default");
	$("#newTeacher").removeClass("btn-default");
	$("#newTeacher").addClass("active btn-info");
}
