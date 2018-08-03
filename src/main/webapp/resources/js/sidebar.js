$(document).ready(function() {
        $("#toggle-sidebar-button").click(function(){
        	var sidebar = $('#main-area');
        	if(sidebar.css('margin-left') == '60px'){
        		
        		$('#main-area').animate({
        	        'marginLeft' : "400px" 
        	        }, function(){
        	        	$('#sidebar-contents').css('margin-left','60px');
                		$('#sidebar-contents').css('visibility','visible');      	        	
        	        });

        	}
        	else{
        		$('#main-area').animate({
        	        'marginLeft' : "60px"
        	        });
        		$('#sidebar-contents').css('visibility','hidden');
        		$('#notifications').css('visibility','hidden');
        		$('#mailbox').css('visibility','hidden');
            	$('#newsfeed').css('visibility','hidden');
            	$('#friends-pane').css('visibility','hidden');
        		}
        });
        
        $("#bell").click(function(){
        	$('#mailbox').css('visibility','hidden');
        	$('#newsfeed').css('visibility','hidden');
        	$('#friends-pane').css('visibility','hidden');
        	var sidebar = $('#main-area');
        	if(sidebar.css('margin-left') == '60px'){
        		
        		$('#main-area').animate({
        	        'marginLeft' : "400px" 
        	        }, function(){
        	        	$('#sidebar-contents').css('margin-left','60px');
                		$('#sidebar-contents').css('visibility','visible');
                		$('#notifications').css('visibility','visible'); 
        	        });

        	}
        	else{
        		if($('#notifications').css('visibility') == 'hidden')
        			$('#notifications').css('visibility','visible');
 				else
        			$('#notifications').css('visibility','hidden');		
        	}	
        });
        
        $("#mail").click(function(){
        	$('#notifications').css('visibility','hidden');
        	$('#newsfeed').css('visibility','hidden');
        	$('#friends-pane').css('visibility','hidden');
        	var sidebar = $('#main-area');
        	if(sidebar.css('margin-left') == '60px'){
        		
        		$('#main-area').animate({
        	        'marginLeft' : "400px" 
        	        }, function(){
        	        	$('#sidebar-contents').css('margin-left','60px');
                		$('#sidebar-contents').css('visibility','visible');
                		$('#mailbox').css('visibility','visible'); 
        	        });

        	}
        	else{
        		if($('#mailbox').css('visibility') == 'hidden')
        			$('#mailbox').css('visibility','visible');
 				else
        			$('#mailbox').css('visibility','hidden');		
        	}	
        });
        
        $("#news").click(function(){
        	$('#notifications').css('visibility','hidden');
        	$('#mailbox').css('visibility','hidden');
        	$('#friends-pane').css('visibility','hidden');
        	var sidebar = $('#main-area');
        	if(sidebar.css('margin-left') == '60px'){	
        		$('#main-area').animate({
        	        'marginLeft' : "400px" 
        	        }, function(){
        	        	$('#sidebar-contents').css('margin-left','60px');
                		$('#sidebar-contents').css('visibility','visible');
                		$('#newsfeed').css('visibility','visible'); 
        	        });
        	}
        	else{
        		if($('#newsfeed').css('visibility') == 'hidden')
        			$('#newsfeed').css('visibility','visible');
 				else
        			$('#newsfeed').css('visibility','hidden');		
        	}	
        });
        
        $("#friends").click(function(){
        	$('#mailbox').css('visibility','hidden');
        	$('#newsfeed').css('visibility','hidden');
        	$('#notifications').css('visibility','hidden');
        	var sidebar = $('#main-area');
        	if(sidebar.css('margin-left') == '60px'){
        		
        		$('#main-area').animate({
        	        'marginLeft' : "400px" 
        	        }, function(){
        	        	$('#sidebar-contents').css('margin-left','60px');
                		$('#sidebar-contents').css('visibility','visible');
                		$('#friends-pane').css('visibility','visible'); 
        	        });

        	}
        	else{
        		if($('#friends-pane').css('visibility') == 'hidden')
        			$('#friends-pane').css('visibility','visible');
 				else
        			$('#friends-pane').css('visibility','hidden');		
        	}	
        });
        
     });