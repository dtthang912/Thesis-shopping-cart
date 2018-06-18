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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${appPath}/resources/css/css1.css">
<title>E-shop</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<ul class="nav navbar-nav navbar-right">
		</ul>
	</div>
	</nav>
	<div class="container well">
		<div class="col-md-4 col-md-offset-4">
			<h1>Login</h1>

			<form:form action="${appPath}/employee/login" method="post" modelAttribute="loginForm">
				<div class="message">
					<form:errors path="*" />
				</div>
				<table>
					<tr>
						<td>Username :</td>
						<td><form:input type="text" path="userName" maxlength="45" />
						</td>
					</tr>
					<tr>
						<td>Password :</td>
						<td><form:input type="password" path="pass" maxlength="45" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Login" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${appPath}/resources/js/js1.js"></script>
</body>
</html>