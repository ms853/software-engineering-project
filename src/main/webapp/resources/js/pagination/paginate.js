(function($){

	$.fn.customPaginate = function(options)
	{
		var paginationContainer = this;
		var itemsToPaginate;


		var defaults = {

				itemsPerPage : 5

		};

		var settings = {};

		$.extend(settings, defaults, options);

		var itemsPerPage = settings.itemsPerPage;

		itemsToPaginate = $(settings.itemsToPaginate);
		var numberOfPaginationLinks = Math.ceil((itemsToPaginate.length / itemsPerPage));

		if(numberOfPaginationLinks > 5){
			$("<span id='prev'>&laquo;</span><ul id='pages'></ul><span id='next'>&raquo;</span>").prependTo(paginationContainer);
		}else{
			$("<ul id='pages'></ul>").prependTo(paginationContainer);
		}

		for(var index = 0; index < numberOfPaginationLinks; index++)
		{
			paginationContainer.find("ul").append("<li>"+ (index+1) + "</li>");
		}

		// show initial set
		showNext($('ul'));
		
		itemsToPaginate.filter(":gt(" + (itemsPerPage - 1)  + ")").hide();

		paginationContainer.find("ul li").first().addClass(settings.activeClass).end().on('click', function(){

			var $this = $(this);

			$this.addClass(settings.activeClass);

			$this.siblings().removeClass(settings.activeClass);

			var linkNumber = $this.text();

			var itemsToHide = itemsToPaginate.filter(":lt(" + ((linkNumber-1) * itemsPerPage)  + ")");
			$.merge(itemsToHide, itemsToPaginate.filter(":gt(" + ((linkNumber * itemsPerPage) - 1)  + ")"));

			var itemsToShow = itemsToPaginate.not(itemsToHide);

			$("html,body").animate({scrollTop:"0px"}, function(){
				itemsToHide.hide();
				itemsToShow.show();
			});
		});
		
		$("#prev").css("display", "none");
		
		// clicking on the '>>' link:
		$('#next').click(function(e) {
		  e.preventDefault();
		  showNext($("#pages"));
		});
		
		// clicking on the '<<' link:
		$('#prev').click(function(e) {
		  e.preventDefault();
		  showPrevious($("#pages"));
		});

	}

}(jQuery));

var from = 0, step = 5;

function showNext(list) {	
  list
    .find('li').hide().end()
    .find('li:lt(' + (from + step) + '):not(li:lt(' + from + '))')
      .show();
  from += step;
  $("#prev").css("display", "inline");
  
  if($('#pages > li:last:visible').length > 0){
	  $("#next").css("display", "none");
  }
}

function showPrevious(list) {
  from -= step;
  list
    .find('li').hide().end()
    .find('li:lt(' + from + '):not(li:lt(' + (from - step) + '))')
      .show();
  
  $("#next").css("display", "inline");
  
  if($('#pages > li:first:visible').length > 0){
	  $("#prev").css("display", "none");
  }
}
