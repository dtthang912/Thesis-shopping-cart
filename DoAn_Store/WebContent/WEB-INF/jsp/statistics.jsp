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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="${appPath}/resources/js/js1.js"></script>
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
						<li><a
							href="${appPath}/employee/manageCustomer">Manage customer</a></li>
						<li><a href="${appPath}/employee/manageComment">Manage
								comment</a></li>
						<li><a href="${appPath}/employee/manageEvent">Manage
								event</a></li>
						<li><a href="${appPath}/employee/manageSaleProduct">Manage
								sale product</a></li>
						<li class="active"><a href="${appPath}/employee/statistics">Statistics</a></li>
					</c:if>
					<c:if test="${role == 'ONLINE_SELLER'}">
						<li><a href="${appPath}/employee/manageOrder">Manage
								order</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div class="message">
			<form:errors path="*" />
		</div>
		<div class="text-center">
			Get by :
			<form:form action="${appPath}/employee/statistics"
				modelAttribute="statisticForm" method="get"
				cssStyle="display : inline">
				<select name="time" onchange="submit()">
					<option ${param.time == 'Day' ? 'selected' : ''}>Day</option>
					<option ${param.time == 'Month' ? 'selected' : empty param.time ? 'selected' : ''}>Month</option>
					<option ${param.time == 'Year' ? 'selected' : ''}>Year</option>
				</select>
			</form:form>
		</div>
		<div class="text-center">
			<h3>Revenue and profit</h3>
		</div>
		<div class="top-10">
			<div class="col-md-11">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>Revenue</th>
							<th>Profit</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${fn:escapeXml(statistics.revenue)} đ</td>
							<td>${fn:escapeXml(statistics.profit)} đ</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<br>

		<div class="text-center">
			<h3>Top 10 best sellers</h3>
		</div>
		<div class="top-10">
			<div class="col-md-11">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>Product ID</th>
							<th>Product name</th>
							<th>Quantity</th>
							<th>Income</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="soldProductInfo"
							items="${statistics.soldProductInfoList}">
							<tr>
								<td>${fn:escapeXml(soldProductInfo.productId)}</td>
								<td>${fn:escapeXml(soldProductInfo.name)}</td>
								<td>${fn:escapeXml(soldProductInfo.quantity)}</td>
								<td>${fn:escapeXml(soldProductInfo.income)} đ</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>