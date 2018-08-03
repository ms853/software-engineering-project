$(document).ready(function() {
	
	$(window).resize(function() {
	if($(window).width()<=767){
		$('#nav').removeClass('navbar');
	}else{
		$('#nav').addClass('navbar');
	}
	});
	
	$(window).scroll(function() {
  	if($(document).scrollTop() > 50 && $(window).width()>767) {
    	$('#nav').removeClass('navbar');
    }
    else {
    	if($(window).width()<=767){
    		$('#nav').removeClass('navbar');
    	}else{
    		$('#nav').addClass('navbar');
    	}

    }
  });
});