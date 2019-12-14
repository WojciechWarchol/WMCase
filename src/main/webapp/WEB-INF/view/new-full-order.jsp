<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - Wycena</title>
	<meta charset="UTF-8">

	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	<link rel="stylesheet"
	      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	      crossorigin="anonymous">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>WMCase - Prośba o wycenę</h2>
		</div>
	</div>
	
	
	<div id="container">
	
		<div id="content">

			<form:form action="sendOrder" modelAttribute="order" method="POST">
				<form:hidden path="id"/>
				<form:hidden path="date"/>
				
				<input type="submit" value="Wyślij zapytanie" class="add-button" />
	
				<table>
					<tbody>
						<tr>
							<th>Dane kontaktowe:</th>
						</tr>
						<tr>
							<td><label>Imię:</label></td>
							<td><input type = "text" name = "client.name"></td>
						</tr>
						<tr>
							<td><label>Nazwisko:</label></td>
							<td><input type = "text" name = "client.surname"></td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td><input type = "text" name = "client.email"></td>
						</tr>
						<tr>
							<td><label>Telefon:</label></td>
							<td><input type = "text" name = "client.tel"></td>
						</tr>
						<tr>
							<td><label>Komentarz do zamówienia:</label></td>
							<td><input type = "text" name = "comments"></td>
						</tr>	
					</tbody>
				</table>
				
			
			</form:form>
			
			<form:form action="newCaseInOrder" method="GET">
				<input name="order" type="hidden" value="${order}"/>
				<input type="submit" value="Dodaj Skrzynkę" class="add-button" />
			</form:form>
		
			<table>
				<tr>
					<th>Skrzynki</th>
				</tr>
				<tr>
					<th>Wymiary</th>
					<th>Typ</th>
					<th>Materiał</th>
                    <th>Kolor</th>
                    <th>Wypełnienie</th>
                    <th>Rączki</th>
                    <th>Koła</th>
                    <th>Zamki</th>
					<th>Uwagi</th>
					<th>Akcja</th>
				</tr>
				
				<c:forEach var="tempCase" items="${order.getCases()}">
				<%-- <c:forEach var="tempCase" items="${cases}"> --%>
				<!-- Update and delete Links -->
				<c:url var="updateLink" value="/updateCase">
					<c:param name="caseId" value="${tempCase.id}"/>
					<c:param name="orderId" value="${order}" />
				</c:url>
				
				<c:url var="deleteLink" value="/deleteCase">
					<c:param name="caseId" value="${tempCase.id}"/>
					<c:param name="orderId" value="${order}" />
				</c:url>
					
					<tr>
						<td> ${tempCase.length} x ${tempCase.width} x ${tempCase.height} mm</td>
						<td> ${tempCase.type.getType()}</td>
                        <td> ${tempCase.material.getMaterial()}</td>
                        <td> ${tempCase.color.getColor()}</td>
                        <td> ${tempCase.filling.getFilling()}</td>
                        <td> ${tempCase.handle.getHandle()} - ${tempCase.getHandleNum() } </td>
                        <td> ${tempCase.getWheels()} - ${tempCase.getWheelNum()}</td>
                        <td> ${tempCase.locks.getLocks()}
						<td> ${tempCase.comments}</td>
						<td>
							<a href="${updateLink}">Modyfikuj</a>
							 | 
							<a href="${deleteLink}" 
							onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
							Usuń</a>
						<td>
						
					</tr>
				<%-- </c:forEach> --%>		
				</c:forEach>
				
			</table>
		
			<a href="${pageContext.request.contextPath}/clientOrders?clientId=${clientId}">Wróć do zamówień</a>
		
		</div>
	
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>








</html>