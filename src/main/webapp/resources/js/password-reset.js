//-------------------- Attributes --------------------//

var valPassword = false, valPassword2 = false;

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
	$("#resetbtn").prop("disabled", true);
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
	$("#resetbtn").prop("disabled", true);

	$('input').focusout(function() {

		validateText($(this).attr('id'));

		if (valPassword && valPassword2) {
			$("#resetbtn").prop("disabled", false);
		}

	});
});
