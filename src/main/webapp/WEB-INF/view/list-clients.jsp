<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - Klienci</title>
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
			<h2>WMCase - Klienci</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
		
		
			<table>
				<tr>
					<th>Id Klienta</th>
					<th>Imię</th>
					<th>Nazwisko</th>
					<th>Email</th>
                    <th>Telefon</th>
                    <th>Akcja</th>
				</tr>
				
				<c:forEach var="tempClient" items="${clients}">
				
				<!-- Update and delete Links -->
				<c:url var="updateLink" value="/clientOrders">
					<c:param name="clientId" value="${tempClient.id}"/>
				</c:url>
				
				<c:url var="deleteLink" value="/deleteClient">
					<c:param name="clienId" value="${tempClient.id}"/>
				</c:url>
					
					<tr>
						<td> ${tempClient.id}</td>
						<td> ${tempClient.name}</td>
						<td> ${tempClient.surname}</td>
						<td> ${tempClient.email}</td>
						<td> ${tempClient.tel}</td>
						<td>
							<a href="${updateLink}">Zamówienia</a>
							 | 
							<a href="${deleteLink}" 
							onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
							Usuń</a>
						<td>
							
					</tr>
						
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>

</body>








</html>