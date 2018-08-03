<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Administrator Web Panel - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/admin-style.css" var="adminstylecss" />
<link href="${adminstylecss}" rel="stylesheet" />

<!-- Scripts -->
<!-- Scripts -->
<script src="/resources/js/pagination/custom.js"></script>
<script src="/resources/js/pagination/paginate.js"></script>

</head>

<body style="background-color: #666666; padding-top: 70px;">
	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>


	<div class="jumbotron">
		<!-- Search form -->
		<c:url value="/admin/user-search" var="searchUrl" />
		<form:form class="form-horizontal" id="searchForm"
			action="${searchUrl}" method="get" modelAttribute="search">
			<div class="wrapper text-center">
				<div class="form-group">
					<input class="form-control" type="text"
						placeholder="Enter the email or display name of the user"
						id="search" name="search" value="${search}" />
				</div>

				<button id="searchBtn" type="submit" class="btn btn-primary">Search</button>
			</div>
		</form:form>
	</div>
	<!-- Results -->
	<c:if test="${userPage != null}">

		<!-- Display page numbers -->
		<div class="text-center">
			<c:if test="${userPage.getTotalPages() > 0}">
				<c:forEach begin="${Math.max(0, userPage.getNumber() - 5)}"
					end="${Math.min(userPage.getTotalPages() - 1, userPage.getNumber() + 5)}"
					var="i">
					<c:choose>
						<c:when test="${i != userPage.getNumber()}">
							<a class="page-number" href="${searchUrl}?search=${search}&page=${i}">${i+1}</a>
						</c:when>
						<c:otherwise>
							<em class="page-number-active">${i+1}</em>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>

		<!-- Display results -->
		<c:choose>
			<c:when test="${userPage.getNumberOfElements() > 0}">
				<div class="container">
				<h5 class="page-result">${userPage.getTotalElements()} result(s):</h5>
				<c:forEach items="${userPage.getContent()}" var="user">
					<div class="jumbotron">
						<h4>${user.getEmail()}</h4>
						<h5>${user.getName()}</h5>
						<h6>Role: ${user.getRole().toString()}</h6>
						<c:if test="${user.isDisabled()}">
							<h6 class="text-warning">Disabled account</h6>
						</c:if>
						<a href="/admin/user?id=${user.getId()}">Details</a>
					</div>
				</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<h5 class="page-result">No results.</h5>
			</c:otherwise>
		</c:choose>

	</c:if>

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>