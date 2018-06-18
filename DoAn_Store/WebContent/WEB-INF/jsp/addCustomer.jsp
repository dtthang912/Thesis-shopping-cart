<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="appPath" value="/DoAn_Store" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${appPath}/resources/js/js1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script
	src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css"
	href="${appPath}/resources/css/css1.css">
<title>E-shop</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="logout" href="${appPath}/employee/logout">Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="container-fluid well">
		<div class="col-md-1 sidebar">
			<ul class="nav nav-sidebar">
				<li><a href="${appPath}/employee">Home</a></li>
				<c:forEach var="role" items="${employee.roleList}">
					<c:if test="${role == 'ADMIN'}">
						<li class="active"><a
							href="${appPath}/employee/manageCustomer">Manage customer</a></li>
						<li><a href="${appPath}/employee/manageComment">Manage
								comment</a></li>
						<li><a href="${appPath}/employee/manageEvent">Manage
								event</a></li>
						<li><a href="${appPath}/employee/manageSaleProduct">Manage
								sale product</a></li>
						<li><a href="${appPath}/employee/statistics">Statistics</a></li>
					</c:if>
					<c:if test="${role == 'ONLINE_SELLER'}">
						<li><a href="${appPath}/employee/manageOrder">Manage
								order</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<form:form modelAttribute="customerForm" styleClass="form-horizontal"
			action="${appPath}/employee/manageCustomer" method="post">
			<div class="form-group">
				<div class="col-md-offset-4 col-md-7">
					<div class="message">
						<form:errors path="*" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Username</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="userName"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Password</div>
				<div class="col-md-7">
					<form:input type="password" cssClass="form-control" path="pass"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">First name</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="fName"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Middle name</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="mName"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Last name</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="lName"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Num</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="num"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Street</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="street"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Distric</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="distric"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">City</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="city"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Phone</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="phone"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">Email</div>
				<div class="col-md-7">
					<form:input type="text" cssClass="form-control" path="email"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2">Gender</div>
				<div class="col-md-7">
					<form:select id="gender" path="gender">
						<option value="male">male</option>
						<option value="female">female</option>
					</form:select>
				</div>
			</div>
			<form:hidden path="cPage" />
			<div class="form-group text-center">
				<form:button type="submit" class="btn btn-default" name="method"
					value="add">
					Add
				</form:button>
				<a href="manageCustomer?method=list&cPage=${customerForm.cPage}"
					class="btn btn-default"> Cancel </a>
			</div>
		</form:form>
	</div>
</body>
</html>