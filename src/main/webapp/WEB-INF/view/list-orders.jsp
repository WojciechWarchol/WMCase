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
                    <th>Akcja</th>
				</tr>
				
				<c:forEach var="tempOrder" items="${orders}">
				
				<!-- Update and delete Links -->
				<c:url var="updateLink" value="/updateOrder">
					<c:param name="orderId" value="${tempOrder.id}"/>
					<%-- <c:param name="theOrder" items="${tempOrder}"/> --%>
				</c:url>
				
				<c:url var="deleteLink" value="/deleteCase">
					<c:param name="orderId" value="${tempOrder.id}"/>
				</c:url>
					
					<tr>
						<td> ${tempOrder.id}</td>
						<td> ${tempOrder.getCases().size()}</td>
						<td> ${tempOrder.comments}</td>
						<td> ${tempOrder.charge}</td>
						<td> ${tempOrder.getOrderStatus()}</td>
						<td> 
							<a href="${updateLink}">Modyfikuj</a>
							 | 
							<a href="${deleteLink}" 
							onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
							Usuń</a>
						<td>

					</tr>
						
				</c:forEach>
				
			</table>
		
			<a href="${pageContext.request.contextPath}/clientList">Wróć do klientów</a>		
		
		</div>
	
	</div>

</body>








</html>