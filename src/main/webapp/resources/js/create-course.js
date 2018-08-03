
//-------------------- Attributes --------------------//

var valName = false,
    valMaxLearners = false,
    valLevel = false,
    valCategory = false,
    valDescription = false;

//Validation
jQuery(document).ready(

		function validate() {
			// open calendar
			$('.selectpicker').selectpicker();
			
			// disable create course button
			$("#coursebtn").prop("disabled", true);
			
			//input validation
			$('input').focusout(function() {
				if (!validateText($(this).attr('id'))) {
					return false;
				}
				checkCourseBtn();
			});
			
			//select validation
			$('select').on('changed.bs.select', function () {
				if (!validateSelect($(this).attr('id'))){
					return false;
				}
				checkCourseBtn();
			});
			
			//text area validation
			$('textarea').focusout(function() {
				if (!validateTextarea($(this).attr('id'))) {
					return false;
				}
				checkCourseBtn();				
			});
			
			// change focus from cancel to create course button
			$("#cancel").focus(function() {
				$("#coursebtn").focus();
			});
			
		});

//Check if all fields are valid
//if valid them enable button
function checkCourseBtn(){
	if(valName && valMaxLearners && valLevel && 
			valCategory && valDescription ){
		$("#coursebtn").prop("disabled", false);
	}
}

//name validation using client-side and server checks
function validateName() {
	var div = $("#name").closest("div");
	var regName = /^([A-Za-z']+( [A-Za-z']+)*){3,30}$/;
	if (regName.test($("#name").val())){

		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		
		// query server to see if course name doesn't already exist
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "check-availability",
			data : $("#name").val(),
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
					valName = true;
					doSuccess("name");
				} else {
					// course name already exists - cannot register with this
					div.removeClass("loadinggif");
					valName = false;
					customError("name", "This course name is already registered");
				}

			},
			error : function(e) {

				// error communicating with server
				console.log("ERROR: ", e);
				div.removeClass("loadinggif");
				valName = false;
				customError("name",
						"Could not validate course name, please try again later");

			}
		});

		return valName;
	} else {

		// email not valid format
		div.removeClass("loadinggif");
		valName = false;
		customError("name", "Enter an appropriate course name");
		return false;

	}

}

//Validate inputs
function validateText(id) {
	// check if input is null or empty
	if ($("#" + id).val() == null || $("#" + id).val() == "") {
		doError(id);
		return false;
	} else {
		//name
		if(id == "name"){
			var div = $("#name").closest("div");
			$("#glypcnname").remove();
			$("#errorname").remove();
			$("#emptyname").remove();
			div.removeClass("has-success");
			div.removeClass("has-error");
			div.addClass("loadinggif");
			validateName();
		}
		
		//max learners
		if(id == "maxLearners"){
			var regMaxLearners = /^(?:300|[12]\d{2}|[1-9]\d?)$/;
			if (regMaxLearners.test($("#" + id).val())){
				valMaxLearners = true;
				doSuccess(id);
				return true;
			}else{
				valMaxLearners = false;
				customError(id,"Enter a whole number between 1 to 300 (no preceding 0)");
				return false;
			}
		}
		
		//start date
		if(id == "startDate"){
			var regStartDate = /^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[\/\-]\d{4}$/;
			
			if(regStartDate.test($("#"+id).val())){
				var currentDate = new Date(),
				    currentDay = currentDate.getDate(),
				    currentMonth = currentDate.getMonth()+1,
				    currentYear = currentDate.getFullYear();
				
                var date = $("#" + id).val().split("/");
			    var d = parseInt(date[0], 10),
			        m = parseInt(date[1], 10),
			        y = parseInt(date[2], 10);
				
                if(y > currentYear 
                		|| (y == currentYear && m > currentMonth) 
                		|| (y == currentYear && m == currentMonth && d > currentDay)){
                	doSuccess(id);
                	return true;
                }else{ 

                	customError(id,"Please enter a future date");
                	return false;
                }
			
			}else{
				customError(id,"Please enter a date of format DD/MM/YYYY");
			}
			
		}
	}
}

//Validate selects
function validateSelect(id){
	if($("#" + id).val()!="none"){
		if (id == "level") { valLevel = true; }
		else {valCategory = true;}
		doSelectSuccess(id);
	}else{
		if (id == "level"){ valLevel = false; }
		else{ valCategory = false; }
		doSelectError(id,"Please select a "+id);
	}
	return true;
}

//Validate textarea
function validateTextarea(id){
	var textValue = $.trim($("#" + id).val());
	var div = $("#" + id).closest("div");
	if(textValue.length>10 && textValue.length<=2000){
		valDescription = true;
		$("#error" + id).remove();
		div.removeClass("has-error");
		div.addClass("has-success");
		return true;
	}else{
		valDescription = false;
		$("#error" + id).remove();
		div.removeClass("has-success");
		div.addClass("has-error");
		div.append('<span id="error'+ id
				+ '" class="help-block" aria-hidden="true">Please enter description between the character limit of 10 to 2000</span>')
		$("#coursebtn").prop("disabled", true);
		return false;
	}
}

//custom error
function customError(id, message){
	doError(id);
	var div = $("#" + id).closest("div");
	$("#empty" + id).remove();
	div.append('<span id="error'+ id
			+ '" class="help-block" aria-hidden="true">'+message+'</span>')
}

//When an error occurs disable submit button, add error class
function doError(id) {
	var div = $("#" + id).closest("div");
	div.removeClass("has-success");
	$("#glypcn" + id).remove();
	div.addClass("has-error has-feedback");
	div.append('<span id="glypcn'+ id
			+ '" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>')
			$("#error" + id).remove();
	$("#empty" + id).remove();
	div.append('<span id="empty'+ id
			+ '" class="help-block" aria-hidden="true">Field is required</span>')
	$("#coursebtn").prop("disabled", true);
}

//When an error occurs disable submit button, add danger class
function doSelectError(id, message){
	var div = $("#" + id).closest("div");
	$("#"+id).selectpicker('setStyle', 'btn-success',"remove");
	$("#"+id).selectpicker('setStyle', 'btn-danger');
	div.addClass("has-error");
	div.append('<span id="error'+ id
			+ '" class="help-block" aria-hidden="true">'+message+'</span>')
	$("#coursebtn").prop("disabled", true);
}

//When validation passes remove error messages Add success class
function doSuccess(id) {
	var div = $("#" + id).closest("div");
	div.removeClass("has-error");
	div.addClass("has-success has-feedback");
	$("#glypcn" + id).remove();
	$("#error" + id).remove();
	$("#empty" + id).remove();
	div.append('<span id="glypcn'+ id
			+ '" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>')
}

//When validation passes remove error messages Add success class
function doSelectSuccess(id){
	var div = $("#" + id).closest("div");
	$("#error" + id).remove();
	div.removeClass("has-error");
	$("#"+id).selectpicker('setStyle', 'btn-danger',"remove");
	$("#"+id).selectpicker('setStyle', 'btn-success');
}