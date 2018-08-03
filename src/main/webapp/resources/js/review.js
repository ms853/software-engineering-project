//-------------------- Attributes --------------------//

var valComment = false;

//Validation
jQuery(document).ready(

		function validate() {

			//text area validation
			$('textarea').focusout(function() {
				if (!validateTextarea($(this).attr('id'))) {
					return false;
				}
				checkReviewBtn();				
			});

			// change focus from cancel to create course button
			$("#cancel").focus(function() {
				$("#reviewbtn").focus();
			});

		});

//Check if all fields are valid
//if valid them enable button
function checkReviewBtn(){
	if(valComment){
		$("#reviewbtn").prop("disabled", false);
	}
}

//Validate textarea
function validateTextarea(id){
	var textValue = $.trim($("#" + id).val());
	var div = $("#" + id).closest("div");
	if(textValue.length<=2000){
		valComment = true;
		$("#error" + id).remove();
		div.removeClass("has-error");
		div.addClass("has-success");
		return true;
	}else{
		valComment = false;
		$("#error" + id).remove();
		div.removeClass("has-success");
		div.addClass("has-error");
		div.append('<span id="error'+ id
				+ '" class="help-block" aria-hidden="true">Please enter comment between the character limit of 2000</span>')
				$("#reviewbtn").prop("disabled", true);
		return false;
	}
}