<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - skrzynki</title>
	
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	
	<link type="text/css"
		rel= "stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>


<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>WMCase - Tworzenie skrzynki</h2>
		</div>
	</div>

	<div id="container">
		<h3><font face="Lato">Stwórz skrzynkę</h3>
		
		<form:form action="addCaseToOrder" modelAttribute="case" method="POST">
			
			<form:hidden path="id"/>
			<input name="orderId" type="hidden" value="${orderId}"/>
			<input name="clientId" type="hidden" value="${clientId}"/>
			
			<!-- Needs a Controller method -->
			
			<table>
				<tbody>
					<tr>
						<td><label>Długość:</label></td>
						<td><form:input path="length" /></td>
					</tr>
					<tr>
						<td><label>Szerokość:</label></td>
						<td><form:input path="width" /></td>
					</tr>
					<tr>
						<td><label>Wysokość:</label></td>
						<td><form:input path="height" /></td>
					</tr>
					<tr>
						<td><label>Typ:</label></td>
						<td><form:select path="type" >
							<form:options itemLabel="type"/>
							</form:select></td>
					</tr>
					<tr>
						<td><label>Materiał:</label></td>
						<td><form:select path="material" >
						<form:options itemLabel="material"/>
						</form:select></td>
					</tr>
					<tr>
						<td><label>Kolor:</label></td>
						<td><form:select path="color" >
						<form:options itemLabel="color"/>
						</form:select></td>
					</tr>
					<tr>
						<td><label>Wypełnienie:</label></td>
						<td><form:select path="filling" >
						<form:options itemLabel="filling"/>
						</form:select></td>
					</tr>
					<tr>
						<td><label>Rączki:</label></td>
						<td><form:select path="handle" >
						<form:options itemLabel="handle"/>
						</form:select></td>
					</tr>
                    <tr>
                        <td><label>Ilość rączek:</label></td>
                        <td><form:input path="handleNum" /></td>
                    </tr>
                    <tr>
                        <td><label>Koła:</label></td>
                        <td><form:select path="wheels" >
                        <form:option value="true" label="Tak"/>
                        <form:option value="false" label="Nie"/>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Ilość kół:</label></td>
                        <td><form:input path="wheelNum" /></td>
                    </tr>
                    <tr>
                        <td><label>Zamki:</label></td>
                        <td><form:select path="locks" >
                        <form:options itemLabel="locks"/>
                        </form:select></td>
                    </tr>
					<tr>
						<td><label>Uwagi:</label></td>
						<td><form:input path="comments" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Zapisz" class="save" /></td>
					</tr>
				</tbody>
			
			</table>
			
		</form:form>

		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/continueOrder?cases=${cases}">Wróć do skrzynek</a>
		</p>
		
		
		</div>
	


</body>

</html>