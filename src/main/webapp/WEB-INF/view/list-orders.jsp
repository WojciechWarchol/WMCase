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
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
					<th>Data zamówienia</th>
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
					<c:param name="clientId" value="${clientId}"/>
				</c:url>
				
				<c:url var="deleteLink" value="/deleteOrder">
					<c:param name="orderId" value="${tempOrder.id}"/>
					<c:param name="clientId" value="${clientId}"/>
				</c:url>
					
					<tr>
						<td> ${tempOrder.date}</td>
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
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</body>








</html>