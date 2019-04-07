<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - skrzynki</title>
	<meta charset="UTF-8">
	
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-case-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>WMCase - Skrzynki</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
		
		
			<table>
				<tr>
					<th>Wymiary</th>
					<th>Powierzchnia</th>
					<th>Typ</th>
					<th>Materiał</th>
                    <th>Kolor</th>
                    <th>Wypełnienie</th>
                    <th>Rączki</th>
                    <th>Koła</th>
                    <th>Zamki</th>
					<th>Uwagi</th>
					<th>Cena</th>
				</tr>
				
				<c:forEach var="tempCase" items="${cases}">
					
					<tr>
						<td> ${tempCase.length} x ${tempCase.width} x ${tempCase.height} mm</td>
						<td> ${tempCase.surface} m² </td>
						<td> ${tempCase.type.getType()}</td>
                        <td> ${tempCase.material.getMaterial()}</td>
                        <td> ${tempCase.color.getColor()}</td>
                        <td> ${tempCase.filling.getFilling()}</td>
                        <td> ${tempCase.handle.getHandle()} - ${tempCase.getHandleNum() } </td>
                        <td> ${tempCase.getWheels()} - ${tempCase.getWheelNum()}</td>
                        <td> ${tempCase.locks.getLocks()}
						<td> ${tempCase.comments}</td>
						<td> ${tempCase.price}</td>
					</tr>
						
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>

</body>








</html>