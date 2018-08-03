<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>All Courses - Get Digital</title>

<!-- include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/paginate.css" var="paginatecss" />
<link href="${paginatecss}" rel="stylesheet" />
<spring:url value="/resources/css/courses.css" var="coursescss" />
<link href="${coursescss}" rel="stylesheet" />

<!-- Scripts -->
<script src="/resources/js/pagination/custom.js"></script>
<script src="/resources/js/pagination/paginate.js"></script>

</head>

<body style="background: #999999; padding-top: 100px;">

	<c:choose>
		<c:when test="${user == true}">
			<!-- include navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar.jsp"%>

		</c:when>
		<c:otherwise>
			<!-- include logged in navbar -->
			<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

		</c:otherwise>

	</c:choose>
	
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
				<div class="container"> 
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${admin == true}">
							<a href="/admin/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel().toString()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>

			</div>
			</div>
		</div>

	</c:forEach>

	<!-- DUPLICATE COURSES FOR PAGINATION TEST START -->

	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
			<div class="container">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel().toString()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>
				</div>
			</div>
		</div>

	</c:forEach>
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
			<div class="container">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>
				</div>
			</div>
		</div>

	</c:forEach>
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
			<div class="container">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>
				</div>
			</div>
		</div>

	</c:forEach>
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
			<div class="container">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5 ">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>
				</div>
			</div>
		</div>

	</c:forEach>
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
			<div class="container">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ;" src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>
				</div>
			</div>
		</div>

	</c:forEach>
	<c:forEach items="${courseList}" var="courses">

		<div class="post">
			<div class="jumbotron white-border">
				<div class="col-sm-2"></div>

				<div class="col-sm-5">
					<h3>
						<c:if test="${user == true}">
							<a href="/courses/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${learner == true}">
							<a href="/learner/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>
						<c:if test="${teacher == true}">
							<a href="/teacher/course/${courses.getId()}">${courses.getName()}</a> -
					${courses.getTeacher().getName()}
					</c:if>

					</h3>
					<div class="col-sm-12">
						<img style="width: 50%;  ; " src="/resources/images/placeholder.jpg"
							alt="Card image cap">
					</div>
				</div>

				<div class="col-sm-5">
					<blockquote class="blockquote">
						<h3>About Course</h3>
						<div class="blockquote-footer">Description:
							${courses.getDescription()}</div>
						<div class="blockquote-footer">Category:
							${courses.getCategory()}</div>
						<div class="blockquote-footer">Difficulty:
							${courses.getLevel()}</div>
						<div class="blockquote-footer">Start Date:
							${courses.getStartDate()}</div>
					</blockquote>
				</div>

			</div>
		</div>

	</c:forEach>

	<!-- DUPLICATE COURSES FOR PAGINATION TEST END -->

	<div class="text-center">
		<div class="pagination"></div>
	</div>

	<!-- include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>
