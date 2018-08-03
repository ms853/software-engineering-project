
jQuery(document).ready(
		function passwordResetViaAjax() {
			var errMsg404 = "There is no user registered with that email. Please try again.";
			var errMsgOther = "Could not request a password reset right now. Please try again later."

			var frm = $('#resetForm');
			var email = $("#resetEmail");
			var sucDiv = $("#resetFormSuccess");
			var errDiv = $("#resetFormError");
			var resetBtn = $("#resetBtn");

			frm.submit(function(ev) {
				ev.preventDefault();

				errDiv.addClass("hide");
				sucDiv.addClass("hide");
				resetBtn.prop("disabled", true);

				$.ajax({
					type : frm.attr('method'),
					url : frm.attr('action'), 
					data : frm.serialize(),
					timeout : 10000,
					success : function(data) {
						if (data.code == "200") {
							// successful reset
							sucDiv.removeClass("hide");
							sucDiv.hide().fadeIn(250);
							email.val("");
							resetBtn.prop("disabled", false);
						} else {
							// failed reset
							errDiv.removeClass("hide");
							errDiv.text(data.code == "404" ? errMsg404 : errMsgOther);
							errDiv.hide().fadeIn(250);
							resetBtn.prop("disabled", false);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						errDiv.removeClass("hide");
						errDiv.text(errMsgOther);
						errDiv.hide().fadeIn(250);
						resetBtn.prop("disabled", false);
					},
				});
			});
		});