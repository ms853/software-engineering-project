var currentVerifyLink = "";

function openLoginModal() {
	$("#loginModal").modal({
		backdrop: "static",
		keyboard: false,
		show: true
	});	
}
		
jQuery(document).ready(function() {
	var sucDiv = $("#loginFormSuccess");

	if (loginModalLoadAction == "verify-success") {
		openLoginModal();
		sucDiv.removeClass("hide").show().html("Account verification successful!");
	} else if (loginModalLoadAction == "reset-success") {
		openLoginModal();
		sucDiv.removeClass("hide").show().html("Your password has been changed successfully!");
	} else if (loginModalLoadAction == "2fa") {
		openLoginModal();
		$("#loginModalContent").hide();
		$("#tfaModalContent").removeClass("hide").show();
	}
});

$("#loginFormError").on("click", "a", function(e) {
	e.preventDefault();

	var errDiv = $("#loginFormError");
	var sucDiv = $("#loginFormSuccess");
	var loginBtn = $("#loginBtn");

	var errMsg404AlreadyVerified = "This account is already verified. Please try logging in.";
	var errMsgOther = "Failed to resend verification email.<br/>" +
					  "<a id=\"resend-verify\" href=\"#\">Click here</a> to try again.";

	loginBtn.prop("disabled", true);
	sucDiv.addClass("hide");
	errDiv.html("Resending verification email...");
	
	$.ajax({
		type : "GET",
		url : currentVerifyLink,
		timeout : 10000,
		success : function(data) {
			if (data.code == "200") {
				// successful resend
				console.log("OK: ", data);
				errDiv.hide();
				sucDiv.removeClass("hide");
				sucDiv.hide().fadeIn(250);
				loginBtn.prop("disabled", false);
			} else {
				// failed resend
				console.log("FAIL: ", data);
				errDiv.removeClass("hide");
				errDiv.html(data.code == "400" ? errMsg404AlreadyVerified : errMsgOther);
				errDiv.hide().fadeIn(250);
				loginBtn.prop("disabled", false);
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			errDiv.removeClass("hide");
			errDiv.html(errMsgOther);
			errDiv.hide().fadeIn(250);
			loginBtn.prop("disabled", false);
		},
	});
});


$('#loginForm').submit(function(ev) {
	ev.preventDefault();

	var frm = $("#loginForm");
	var errDiv = $("#loginFormError");
	var sucDiv = $("#loginFormSuccess");
	var loginBtn = $("#loginBtn");
	
	var errMsgBadCreds = "Invalid email address or password. Please try again.";
	var errMsgDisabled = "Your account is disabled.<br />" +
						 "Contact a system administrator for more information."
	var errMsgOther = "Could not log you in right now. Please try again later.";

	errDiv.addClass("hide");
	sucDiv.addClass("hide");
	loginBtn.prop("disabled", true);

	console.log(frm.serialize());

	$.ajax({
		type : frm.attr('method'),
		url : frm.attr('action'), 
		data : frm.serialize(),
		timeout : 10000,
		success : function(data) {
			if (data.code == "200") {
				// successful login, redirect to index
				console.log("OK: ", data);
				location.href = "/";
			} else {
				// failed login or additional auth required
				console.log("FAIL: ", data);
				errDiv.removeClass("hide");
				if (data.msg == "auth.user.requires2fa") {
					// 2fa needed
					// refresh needs to be done here as the new session will invalidate
					// the csrf token we already have...
					location.href = "/";
				} else if (data.msg == "auth.badcredentials") {
					errDiv.html(errMsgBadCreds);
				} else if (data.msg == "auth.user.disabled") {
					errDiv.html(errMsgDisabled);
				} else if (data.msg == "auth.user.unverified") {
					currentVerifyLink = "/signup/resend-verify?uId=" + data.result;
					errDiv.html(
						"You have not verified your email address for this account.<br />" +
						"<a id=\"resend-verify\" href=\"#\">Click here</a> to resend verification instructions to your email."
					);
				} else {
					errDiv.html(errMsgOther);
				}
				errDiv.hide().fadeIn(250);
				loginBtn.prop("disabled", false);
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			errDiv.removeClass("hide");
			errDiv.html(errMsgOther);
			errDiv.hide().fadeIn(250);
			loginBtn.prop("disabled", false);
		},
	});
});