<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>User Details - Get Digital</title>

<!-- Include header -->
<%@ include file="/WEB-INF/views/body/header.jsp"%>

<!-- Stylesheets -->
<spring:url value="/resources/css/admin-style.css" var="adminstylecss" />
<link href="${adminstylecss}" rel="stylesheet" />

<!-- Scripts -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="/resources/js/navscript.js"></script>

</head>

<div class="modal fade" id="confirmModal" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">

		<!-- Disable -->
		<div class="modal-content" id="disableModalContent">
			<div class="modal-header">
				<button type="button" class="close" id="confirmModalClose"
					data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Disable user?</h4>
			</div>
			<div class="modal-body">
				<div class="text-center">
					<h5>
						Are you sure you want to disable this account?<br /> <em>${user.getEmail()}</em>
						will no longer be able to sign into this account until it is
						re-enabled.
					</h5>
				</div>

				<c:url value="/admin/user-disable" var="disableUrl" />
				<form:form class="form-horizontal" id="disableForm"
					action="${disableUrl}" method="post" modelAttribute="disable">
					<br />

					<form:hidden path="id" value="${user.getId()}" />

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<button id="disableBtn" type="submit" class="btn btn-danger">Disable</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
				</form:form>

			</div>
		</div>

		<!-- Enable -->
		<div class="modal-content" id="enableModalContent">
			<div class="modal-header">
				<button type="button" class="close" id="confirmModalClose"
					data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Enable user?</h4>
			</div>
			<div class="modal-body">
				<div class="text-center">
					<h5>
						Are you sure you want to enable this account?<br /> <em>${user.getEmail()}</em>
						will be able to log in using this account again.
					</h5>
				</div>

				<c:url value="/admin/user-enable" var="enableUrl" />
				<form:form class="form-horizontal" id="enableForm"
					action="${enableUrl}" method="post" modelAttribute="enable">
					<br />

					<form:hidden path="id" value="${user.getId()}" />

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<button id="enableBtn" type="submit" class="btn btn-primary">Enable</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
				</form:form>

			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<body style="background-color: #666666; padding-top: 45px;">
	<!-- include navbar -->
	<%@ include file="/WEB-INF/views/navigation/navbar-logged-in.jsp"%>

	<div class="text-center">
		<div class="jumbotron">
			<h4>${user.getEmail()}</h4>
			<h5>${user.getName()}</h5>
			<p class="small">
				ID: ${user.getId()}<br /> Created: ${user.getCreationDate()}<br />Role:
				${user.getRole().toString()}<br /> Verified:
				${user.isEmailVerified()}<br /> Disabled: ${user.isDisabled()} <br />
				<c:if test="${teacher != null}">
					<c:choose>
						<c:when test="${teacher.getCourse() != null}">
						Taught course: <a
								href="/admin/course/${teacher.getCourse().getId()}">${teacher.getCourse().getName()}
								(ID: ${teacher.getCourse().getId()})</a>
						</c:when>
						<c:otherwise>
							Not teaching any courses.
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${learner != null}">
					<c:choose>
						<c:when test="${learner.getCourse() != null}">
						Enrolled course: <a
								href="/admin/course/${learner.getCourse().getId()}">${learner.getCourse().getName()}
								(ID: ${learner.getCourse().getId()})</a>
						</c:when>
						<c:otherwise>
							Not enrolled in any courses.
						</c:otherwise>
					</c:choose>
				</c:if>
			</p>

			<c:if test="${user.isDisabled()}">
				<a href="#" id="enableLink">Enable account</a>
				<br />
			</c:if>
			<c:if test="${!user.isDisabled()}">
				<a href="#" id="disableLink">Disable account</a>
				<br />
			</c:if>

			<br /> <a href="javascript:history.back()">Back</a>

		</div>
	</div>

	<script type="text/javascript">
		function openModal() {
			$("#confirmModal").modal("show");
		}

		function showEnableModal() {
			$("#enableModalContent").show();
			$("#disableModalContent").hide();
			$("#deleteModalContent").hide();
			$("#loginAsModalContent").hide();
		}

		function showDisableModal() {
			$("#enableModalContent").hide();
			$("#disableModalContent").show();
			$("#deleteModalContent").hide();
			$("#loginAsModalContent").hide();
		}

		$("#enableLink").click(function(e) {
			e.preventDefault();
			openModal();
			showEnableModal();
		});

		$("#disableLink").click(function(e) {
			e.preventDefault();
			openModal();
			showDisableModal();
		});
	</script>

	<!-- Include footer -->
	<%@ include file="/WEB-INF/views/body/footer.jsp"%>

</body>
</html>