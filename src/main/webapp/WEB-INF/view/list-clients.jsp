<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - Klienci</title>
	<meta charset="UTF-8">
	<!--
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

	<div class="container-fluid bg-dark m-0">
        <h2 class="text-light text-center p-3">WMCase - Klienci</h2>
	</div>
	
	<div class="container-fluid allign-center m-3">
	
		<div>
		
			<table class="table">
				<thead class="thead-dark">
                    <tr>
                        <th scope="col">Id Klienta</th>
                        <th scope="col">Imię</th>
                        <th scope="col">Nazwisko</th>
                        <th scope="col">Email</th>
                        <th scope="col">Telefon</th>
                        <th scope="col">Akcja</th>
                    </tr>
				</thead>
				
				<c:forEach var="tempClient" items="${clients}">
				
				<!-- Update and delete Links -->
				<c:url var="updateLink" value="/clientOrders">
					<c:param name="clientId" value="${tempClient.id}"/>
				</c:url>
				
				<c:url var="deleteLink" value="/deleteClient">
					<c:param name="clientId" value="${tempClient.id}"/>
				</c:url>

				<tbody>
					<tr>
						<td> ${tempClient.id}</td>
						<td> ${tempClient.name}</td>
						<td> ${tempClient.surname}</td>
						<td> ${tempClient.email}</td>
						<td> ${tempClient.tel}</td>
						<td>
							<a class="btn btn-dark btn-sm" href="${updateLink}">Zamówienia</a>
							<a class="btn btn-danger btn-sm" href="${deleteLink}"
							onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
							Usuń</a>
						<td>
							
					</tr>
				<tbody>
						
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</body>








</html>