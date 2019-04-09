<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - Zamówienia</title>
	<meta charset="UTF-8">
	
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	<!-- 
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-case-style.css">
			-->
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>WMCase - Zamówienia</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
		
		
			<table>
				<tr>
					<th>Numer zamówienia</th>
					<th>Liczba skrzynek</th>
					<th>Komentarz</th>
					<th>Wartość</th>
                    <th>Status</th>
				</tr>
				
				<c:forEach var="tempOrder" items="${orders}">
					
					<tr>
						<td> ${tempOrder.id}</td>
						<td> ${tempOrder.id}</td>
						<td> ${tempOrder.comments}</td>
						<td> ${tempOrder.charge}</td>
						<td> ${tempOrder.order_status}</td>

					</tr>
						
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>

</body>








</html>