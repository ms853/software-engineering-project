<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Review course - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/star-rating/star-rating.css" var="starRatingCss" />
<link href="${starRatingCss}" rel="stylesheet" />

<!-- Scripts -->
<script src="/resources/js/star-rating/star-rating.js"></script>
<script src="/resources/js/review.js"></script>

</head>
<body>

	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div class="form-title"></div>

	<!-- Review form -->
	<div class="container">	<!-- Container start -->

		<div class="page-header">
			<!-- Logo -->
			<div class="text-center logo">
				<a href="/" rel="logo"><img
					src="/resources/images/GetDigital.png" /></a>
			</div>
		</div>

		<div class="col-lg-1"></div>
		<div class="col-lg-10">

			<div class="jumbotron white-border">	<!-- jumbotron start -->
				<p><br /></p>

				<!-- Course -->
				<div class="col-sm-8 col-md-offset-3 has-success">
					<span class="help-block"> 
						Course name: ${course.getName()} <br />
						Course Description: ${course.getDescription()}
					</span>
				</div>

				<!-- Main Form -->
				<form:form class="form-horizontal"
					action="/learner/course/${course.getId()}/review" method="POST"
					id="review-course" modelAttribute="review">

					<!-- Structure Rating -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="structureRating">Structure</label>
						<div class="col-sm-8">
							<form:input id="structureRating" name="structureRating"
								type="text" class="rating" data-rtl="false"
								path="structureRating" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="structureRating" class="help-block"></form:errors>
					</span>

					<!-- Difficulty Rating -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="difficultyRating">Difficulty</label>
						<div class="col-sm-8">
							<form:input id="difficultyRating" name="difficultyRating"
								type="text" class="rating" data-rtl="false"
								path="difficultyRating" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="difficultyRating" class="help-block"></form:errors>
					</span>
					
					<!-- Support Rating -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="supportRating">Support</label>
						<div class="col-sm-8">
							<form:input id="supportRating" name="supportRating"
								type="text" class="rating" data-rtl="false"
								path="supportRating" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="supportRating" class="help-block"></form:errors>
					</span>
					
					<!-- Overall Rating -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="overallRating">Overall</label>
						<div class="col-sm-8">
							<form:input id="overallRating" name="overallRating"
								type="text" class="rating" data-rtl="false"
								path="overallRating" />
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error"> <form:errors
							path="overallRating" class="help-block"></form:errors>
					</span>

					<!-- Comment field -->
					<div class="form-group">
						<label class="col-sm-3 control-label right" for="comment">Comment</label>
						<div class="col-sm-8">
							<form:textarea class="form-control" id="comment"
								name="comment" rows="10" cols="70" path="comment"></form:textarea>
						</div>
					</div>
					<span class="col-sm-8 col-md-offset-3 has-error">
						<form:errors path="comment" class="help-block"></form:errors>
					</span>				

					<!-- Submit and Cancel -->
					<div class="col-sm-8 col-md-offset-3">
						<button type="submit" id="reviewbtn" class="btn btn-success">Submit review</button>
					</div>

				</form:form> <!-- End of Main form -->

				<br />
				<p></p>
				<br />

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