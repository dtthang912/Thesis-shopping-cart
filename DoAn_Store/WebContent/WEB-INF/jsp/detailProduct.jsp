<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://thang.com/functions" prefix="util"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<c:set var="appPath" value="/DoAn_Store" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Shop</title>
<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="${appPath}/resources/css/bootstrap.min.css" media="screen" />
<link href="${appPath}/resources/css/base.css" rel="stylesheet"
	media="screen" />

<!-- Bootstrap style responsive -->
<link href="${appPath}/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link href="${appPath}/resources/css/font-awesome.css" rel="stylesheet"
	type="text/css">

<!-- Google-code-prettify -->
<link href="${appPath}/resources/css/prettify.css" rel="stylesheet" />
</head>
<body>
	<div id="header">
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					<spring:message code="welcome.title" />
					!<strong> <c:choose>
							<c:when test="${not empty sessionScope.customer}">
					${fn:escapeXml(customer.fName)}
					</c:when>
							<c:otherwise>
								<spring:message code="guest.title" />
							</c:otherwise>
						</c:choose>
					</strong>
				</div>
				<div class="span6">
					<div class="pull-right">
						<form
							class="nav navbar-nav navbar-left form-inline language-selector"
							action="" method="get">
							<select name="language" onchange="submit()">
								<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
								<option value="vi" ${language == 'vi' ? 'selected' : ''}>Tiếng
									Việt</option>
							</select>
						</form>
						<a href="product_summary.html"><span
							class="btn btn-mini btn-primary"><i
								class="icon-shopping-cart icon-white"></i> <span
								class="no-in-cart">${fn:escapeXml(fn:length(lineItemList))}</span>
								<spring:message code="items.in.cart.title" /></span> </a>
					</div>
				</div>
			</div>
			<!-- Navbar ================================================== -->
			<div id="logoArea" class="navbar">
				<div class="navbar-inner">
					<a class="brand" href="${appPath}"><img
						src="${appPath}/resources/icon/logo.png" alt="Eshop" /></a>
					<form:form cssClass="form-inline navbar-search" method="get"
						action="${appPath}/search" modelAttribute="productSearchForm">
						<form:input id="srchFld" cssClass="srchTxt" path="name"
							value="${fn:escapeXml(productSearchForm.name)}" />
						<form:select cssClass="srchTxt" path="category">
							<form:option value="All">
								<spring:message code="all.title" />
							</form:option>
							<c:forEach var="rootCategory" items="${rootCategoryList}">
								<form:option
									value="${fn:escapeXml(util:getCategoryTitleByLanguage(rootCategory,language))}" />
							</c:forEach>
						</form:select>
						<form:button type="submit" id="submitButton"
							class="btn btn-primary">
							<spring:message code="button.search" />
						</form:button>
					</form:form>
					<ul id="topMenu" class="nav pull-right">
						<li class=""><a href="special_offer.html"><spring:message
									code="sale.product.title" /></a></li>
						<li class=""><a href="contact.html"><spring:message
									code="contact.title" /></a></li>
						<c:choose>
							<c:when test="${empty sessionScope.customer}">
								<li class=""><a href="#login" role="button"
									data-toggle="modal" style="padding-right: 0"><span
										class="btn btn-large btn-success"><spring:message
												code="login.title" /></span></a>
									<div id="login" class="modal hide fade in" role="dialog">
										<div class="modal-header">
											<h3>
												<spring:message code="login.title" />
											</h3>
										</div>
										<div class="modal-body">
											<form:form cssClass="form-horizontal loginFrm" method="post"
												action="${appPath}/login" modelAttribute="loginForm">
												<div class="control-group">
													<form:input cssClass="span3" type="text" id="username"
														placeholder="Username" path="userName" required="true"/>
												</div>
												<div class="control-group">
													<form:input cssClass="span3" type="password" id="pass"
														placeholder="Password" path="pass" required="true"/>
												</div>
												<div class="control-group">
													<form:button type="submit" class="btn btn-success">
														<spring:message code="login.title" />
													</form:button>
													<form:button class="btn" data-dismiss="modal"
														aria-hidden="true">
														<spring:message code="button.close" />
													</form:button>
												</div>
											</form:form>
										</div>
									</div></li>
							</c:when>
							<c:otherwise>
								<li class="dropdown"><a class="btn-link dropdown-toggle"
									data-toggle="dropdown">${fn:escapeXml(sessionScope.customer.userName)} <span
										class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="${appPath}/profile"><spring:message
													code="edit.profile.title" /></a></li>
										<li class="divider"></li>
										<li><a href="${appPath}/logout"><spring:message
													code="logout.title" /></a></li>
									</ul></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Header End====================================================================== -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<div id="sidebar" class="span3">
					<div class="well well-small">
						<c:set var="totalPrice" value="0" />
						<c:forEach var="lineItem" items="${lineItemList}">
							<c:set var="totalPrice"
								value="${totalPrice + lineItem.price * lineItem.quantity}" />
						</c:forEach>
						<a id="myCart" href="${appPath}/cart"><img
							src="${appPath}/resources/icon/ico-cart.png" alt="cart"><span
							class="no-in-cart">${fn:escapeXml(fn:length(lineItemList))}</span>
							<spring:message code="items.in.cart.title" /> <span
							class="badge badge-warning pull-right total-price">${fn:escapeXml(totalPrice)}
								đ</span></a>
					</div>
					<ul id="sideManu" class="nav nav-tabs nav-stacked">
						<c:forEach var="rootCategory" items="${rootCategoryList}">
							<li class="subMenu open"><a>${fn:escapeXml(util:getCategoryTitleByLanguage(rootCategory,language))}</a>
								<ul>
									<c:forEach var="subcategory"
										items="${rootCategory.subcategoryList}">
										<li><a class="active"
											href="${appPath}/search?category=${util:getCategoryTitleByLanguage(subcategory,language)}"><i
												class="icon-chevron-right"></i>${fn:escapeXml(util:getCategoryTitleByLanguage(subcategory,language))}
										</a></li>
									</c:forEach>
								</ul></li>
						</c:forEach>
					</ul>
					<br />
					<div class="thumbnail">
						<img src="${appPath}/resources/icon/payment_methods.png"
							title="Bootshop Payment Methods" alt="Payments Methods">
						<div class="caption">
							<h5>
								<spring:message code="payment.method.title" />
							</h5>
						</div>
					</div>
				</div>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="${appPath}"><spring:message
									code="homepage.title" /></a> <span class="divider">/</span></li>
						<li><a href="${appPath}/search"><spring:message
									code="products.title" /></a> <span class="divider">/</span></li>
						<li class="active"><spring:message
								code="detail.product.title" /></li>
					</ul>
					<div class="row">
						<div id="gallery" class="span3">
							<img src="${appPath}/resources/image/${product.imageUrl}"
								style="width: 100%"
								alt="${util:getProductNameByLanguage(product,language)}" />
						</div>
						<div class="span6">
							<h3>${fn:escapeXml(util:getProductNameByLanguage(product,language))}</h3>
							<hr/>
							<form class="form-horizontal qtyFrm">
								<div class="control-group">
									<span class="original-price">${fn:escapeXml(util:getOriginalPrice(product,saleProductList))}
									</span><br> <br> <span class="sale-price">${fn:escapeXml(util:getCurrentPrice(product,saleProductList))}
									</span>
									<div class="controls">
										<spring:message code="field.quantity" var="qtyPlaceHolder" />
										<input type="number" class="span1 product-quantity"
											placeholder="${qtyPlaceHolder}" min="1" value="1" />
										<spring:message code="add.to.cart.success.title"
											var="successTitle" />
										<button type="button"
											class="btn btn-large btn-primary pull-right add-to-cart-btn"
											data-productid="${product.id}" data-alert="${successTitle}">
											<spring:message code="button.add.to.cart" />
											<i class=" icon-shopping-cart"></i>
										</button>
									</div>
								</div>
							</form>

							<hr />

							<h4>${fn:escapeXml(product.quantity)}
								<spring:message code="items.in.stock.title" />
							</h4>

							<hr class="soft clr" />
						</div>

						<div class="span9">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="home">
									<h4>
										<spring:message code="product.info.title" />
									</h4>
									<table class="table table-bordered">
										<tbody>
											<tr class="techSpecRow">
												<th colspan="2"><spring:message
														code="detail.product.title" /></th>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1"><spring:message
														code="distributor.title" />:</td>
												<td class="techSpecTD2">${fn:escapeXml(product.distributor.name)}</td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1"><spring:message
														code="description.title" />:</td>
												<td class="techSpecTD2">${fn:escapeXml(util:getProductDescriptionByLanguage(product,language))}</td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1"><spring:message
														code="category.title" />:</td>
												<td class="techSpecTD2">${fn:escapeXml(util:getCategoryTitleByLanguage(product.category,language))}</td>
											</tr>
										</tbody>
									</table>
									<hr class="soft" />
									<h4>
										<spring:message code="comment.title" />
									</h4>
									<c:if test="${not empty sessionScope.customer}">
										<form:form method="post" action="${appPath}/comment"
											modelAttribute="commentForm">
											<div class="form-group" style="color: red">
												<form:errors path="*" />
											</div>
											<br>
											<form:textarea cssClass="span7" path="content" required="true"/>
											<form:hidden path="productId"
												value="${fn:escapeXml(product.id)}" />
											<form:hidden path="productNameUrl"
												value="${fn:replace(util:getProductNameByLanguage(product,language),' ','-')}" />
											<form:button type="submit" class="btn btn-danger"
												style="margin-left : 10px">
												<spring:message code="post.comment.title" />
											</form:button>
										</form:form>
									</c:if>
									<hr class="soft" />
									<c:forEach var="comment" items="${commentList}">
										<br>
										<div style="font-size: 15px; margin-bottom: 3px">
											<b>${fn:escapeXml(comment.customer.fName)}
												${fn:escapeXml(comment.customer.mName)}
												${fn:escapeXml(comment.customer.lName)}</b> :
											${fn:escapeXml(comment.content)}
										</div>

										<div style="font-size: 12px">${fn:escapeXml(comment.time)}</div>
										<br>
									</c:forEach>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<div id="footerSection">
		<div class="container">
			<div class="row">
				<div class="span3">
					<h5>
						<spring:message code="account.title" />
					</h5>
					<a href="login.html"><spring:message code="your.account.title" /></a>
					<a href="login.html"><spring:message code="personal.info.title" /></a>
					<a href="login.html"><spring:message code="order.history.title" /></a>
				</div>
				<div class="span3">
					<h5>
						<spring:message code="info.title" />
					</h5>
					<a href="contact.html"><spring:message
							code="contact.strong.title" /></a> <a href="${appPath}/register"><spring:message
							code="register.title" /></a> <a href="tac.html"><spring:message
							code="term.and.condition.title" /></a> <a href="faq.html"><spring:message
							code="faq.title" /></a>
				</div>
				<div class="span3">
					<h5>
						<spring:message code="event.title" />
					</h5>
					<a href="#"><spring:message code="latest.product.strong.title" /></a>
					<a href="#"><spring:message code="top.seller.title" /></a> <a
						href="special_offer.html"><spring:message
							code="sale.product.strong.title" /></a> <a href="#"><spring:message
							code="distributor.strong.title" /></a>
				</div>
				<div id="socialMedia" class="span3 pull-right">
					<h5>
						<spring:message code="social.media.title" />
					</h5>
					<a href="#"><img width="60" height="60"
						src="${appPath}/resources/icon/facebook.png" title="facebook"
						alt="facebook" /></a> <a href="#"><img width="60" height="60"
						src="${appPath}/resources/icon/twitter.png" title="twitter"
						alt="twitter" /></a> <a href="#"><img width="60" height="60"
						src="${appPath}/resources/icon/youtube.png" title="youtube"
						alt="youtube" /></a>
				</div>
			</div>
			<p class="pull-right">&copy; EShop</p>
		</div>
		<!-- Container End -->
	</div>

	<script src="${appPath}/resources/js/jquery.js" type="text/javascript"></script>
	<script src="${appPath}/resources/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="${appPath}/resources/js/prettify.js"></script>
	<script src="${appPath}/resources/js/eshop.js"></script>
	<script src="${appPath}/resources/js/jquery.lightbox-0.5.js"></script>
</body>
</html>