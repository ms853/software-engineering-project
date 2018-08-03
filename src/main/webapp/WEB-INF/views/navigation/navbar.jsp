<script src="/resources/js/navscript.js"></script>
<!-- Navbar -->
<nav id="nav" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/"><img class="navlogo"
				src="/resources/images/navbar-logo.png" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li><a href="/about">About</a></li>
				<li><a href="/courses">Courses</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="search" id="searchbar" placeholder="Search">
				</div>
				<button type="submit" onclick="return searchCourseGuest()" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>

			<ul class="nav navbar-nav navbar-right">
				<li><a id="loginButton" href="#" data-toggle="modal"
					data-target="#loginModal"> <span
						class="glyphicon glyphicon-log-in"></span> Log-in
				</a></li>
				<li><a href="/signup"><span
						class="glyphicon glyphicon-user"></span> Sign-up</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<script type="text/javascript">
	function searchCourseGuest(){
		
		var searchValue = document.getElementById("searchbar").value;
		searchValue = searchValue.toLowerCase();
		<c:forEach items="${courseList}" var="courses">
			var courseName = '${courses.getName()}';
			courseName = courseName.toLowerCase();
			if(courseName == searchValue){
				var courseId = '${courses.getId()}';
				var url = 'https://'+window.location.host+'/courses/course/'+courseId;
			    var http = new XMLHttpRequest();
			    http.open('HEAD', url, false);
			    http.send();
			    if (http.status != 404){
			    	window.location = url;
			    	return false;
			    } 	
			    else
			        window.location.reload();
			}
		</c:forEach>		
	}
</script>

<!-- Include login modal -->
<%@ include file="/WEB-INF/views/security/login-modal.jsp"%>
