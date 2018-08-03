<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Course Creation - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" media="screen" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<!-- Style sheets -->
<spring:url value="/resources/css/course.css" var="coursecss" />
<link href="${coursecss}" rel="stylesheet" />

<!-- Scripts -->
<script	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/js/create-course.js"></script>
<script>
	$(function() {
		$('input[name=startDate]').datepicker({
			inline : true,
			format : 'dd/mm/yyyy'
		});
	});
</script>

</head>

<body>

	<div class="form-title"></div>
	<div class="container">	<!-- Container start -->
	
		<div class="page-header">
		<!-- Logo -->			
		  <div class="text-center logo">
			<a href="/" rel="logo"><img src="/resources/images/GetDigital.png"/></a>
		  </div>
		</div>

		<div class="col-lg-1"></div>
		<div class="col-lg-10">

			<div class="jumbotron">	<!-- jumbotron start -->
				<p><br /></p>

				<!-- Main Form -->
				<form:form class="form-horizontal" action="/teacher/create-course/add" method="POST"
					id="course-creation" modelAttribute="course">

					<!-- Course Name -->	
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="name">Course name</label>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="name"
								placeholder="Enter course name" path="name"/>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="name" class="help-block"></form:errors>
					</span>
					
					<!-- Max Learners -->	
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="maxLearners">Max number of learners</label>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" id="maxLearners"
								placeholder="Enter value between 1 to 300" path="maxLearners"/>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="maxLearners" class="help-block"></form:errors>
					</span>
					
					<!-- Start Date -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="startDate">Start Date</label>
						<div class="col-sm-8">
							<form:input type="text" class="form-control" path="startDate"/>
						</div>					
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="startDate" class="help-block"></form:errors>
					</span>

					<!-- Difficulty level -->
					<div class="form-group">
						<label class="col-sm-3 control-label righ-aligned" for="level">Difficulty Level</label>
						<div class="col-sm-8">
							<form:select id="level" class="selectpicker form-control" path="level">
								<form:option value="none" label="--Select--" selected="selected" />
								<form:option value="BEGINNER" label="Beginner" />
								<form:option value="INTERMEDIATE" label="Intermediate" />
								<form:option value="ADVANCED" label="Advanced" />
							</form:select>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="level" class="help-block"></form:errors>
					</span>
					
					<!-- Course category -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="category">Course Category</label>
						<div class="col-sm-8">
							<form:select id="category" class="selectpicker form-control" path="category">
								<form:option value="none" label="--Select--" selected="selected" />
								<form:option value="Math" label="Math" />
								<form:option value="Science" label="Science" />
								<form:option value="English" label="English" />
								<form:option value="Arts" label="Arts" />
							</form:select>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="category" class="help-block"></form:errors>
					</span>
					
					<!-- Description -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="description">Course Description</label>
						<div class="col-sm-8">
							​<textarea class="form-control" id="description"
								name="description" rows="10" cols="70"></textarea>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="description" class="help-block"></form:errors>
					</span>

					<!-- Submit and Cancel -->
					<div class="col-sm-8 col-md-offset-3">
						<button type="submit" id="coursebtn" class="btn btn-success">Create Course</button>
					</div>

				</form:form>
				<!-- End of Main form -->

				<br /><p></p><br />

				<form class="form-horizontal" action="/">
					<div class="col-sm-8 col-md-offset-3">
						<button type="submit" id="cancel" class="btn btn-danger">Cancel</button>
					</div>
				</form>

			</div> <!-- Jumbotron end -->
		</div>
	</div> <!-- Container end -->

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>