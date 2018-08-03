
jQuery(document).ready(
		function passwordResetViaAjax() {
			var errMsg401 = "Incorrect authenticator code. Please try again.";
			var errMsgOther = "Could not authenticate you right now. Please try again later.<br/>" +
			                  "If the problem persists, try logging out and logging back in."

			var frm = $('#tfaForm');
			var errDiv = $("#tfaFormError");
			var tfaBtn = $("#tfaBtn");

			frm.submit(function(ev) {
				ev.preventDefault();

				errDiv.addClass("hide");
				tfaBtn.prop("disabled", true);
				
				console.log(frm.serialize());
				console.log($("meta[name='_csrf']").attr("content"));

				$.ajax({
					type : frm.attr('method'),
					url : frm.attr('action'), 
					data : frm.serialize(),
					timeout : 10000,
					success : function(data) {
						if (data.code == "200") {
							// successful auth
							console.log("OK: ", data);
							location.href = "/";
						} else {
							// failed reset
							console.log("FAIL: ", data);
							errDiv.removeClass("hide");
							errDiv.html(data.code == "401" ? errMsg401 : errMsgOther);
							errDiv.hide().fadeIn(250);
							tfaBtn.prop("disabled", false);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						errDiv.removeClass("hide");
						errDiv.html(errMsgOther);
						errDiv.hide().fadeIn(250);
						tfaBtn.prop("disabled", false);
					},
				});
			});
		});