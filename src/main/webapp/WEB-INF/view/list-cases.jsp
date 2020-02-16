<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - skrzynki</title>
	<meta charset="UTF-8">
    <!--
	<link href="<c:url value="/resources/css/style.css" />"
	      rel="stylesheet">
	      -->
	<link rel="stylesheet"
	      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	      crossorigin="anonymous">

</head>

<body>

	<div class="container-fluid bg-dark m-0">
        <h2 class="text-light text-center p-3">WMCase - Skrzynki</h2>
	</div>
	
	<div class="container-fluid allign-center m-3">

	    <h3>
	        <font face="Lato">Skrzynki zamówienia ${orderId}
	    </h3>

		<div>

            <div class="form-group row">
                <form:form action="newCase" method="GET">
                    <input name="orderId" type="hidden" value="${orderId}"/>
                    <input name="clientId" type="hidden" value="${clientId}"/>
                    <!--
                    <input type="submit" value="Dodaj Skrzynkę" class="add-button" />
                    -->
                    <button type="submit" class="btn btn-dark mx-3">Dodaj Skrzynkę</button>
    			</form:form>
					<a class="btn btn-light" href="${pageContext.request.contextPath}/clientOrders?clientId=${clientId}">Wróć do zamówień</a>
			</div>


		
			<table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Wymiary</th>
                        <th scope="col">Powierzchnia</th>
                        <th scope="col">Typ</th>
                        <th scope="col">Materiał</th>
                        <th scope="col">Kolor</th>
                        <th scope="col">Wypełnienie</th>
                        <th scope="col">Rączki</th>
                        <th scope="col">Koła</th>
                        <th scope="col">Zamki</th>
                        <th scope="col">Uwagi</th>
                        <th scope="col">Cena</th>
                        <th scope="col" style="width: 10%">Ilość</th>
                        <th scope="col">Akcja</th>
                    </tr>
                </thead>

				<c:forEach var="tempCase" items="${order.getCases()}">
				<!-- Update and delete Links -->
                <c:url var="updateQuantityLink" value="/updateQuantity">
                    <c:param name="tempCase" value="${tempCase}"/>
                    <c:param name="orderId" value="${orderId}"/>
                    <c:param name="clientId" value="${clientId}"/>
                </c:url>
				<c:url var="updateLink" value="/updateCase">
					<c:param name="caseId" value="${tempCase.key.id}"/>
					<c:param name="orderId" value="${orderId}" />
					<c:param name="clientId" value="${clientId}"/>
				</c:url>
				<c:url var="deleteLink" value="/deleteCase">
					<c:param name="caseId" value="${tempCase.key.id}"/>
					<c:param name="orderId" value="${orderId}" />
					<c:param name="clientId" value="${clientId}"/>
				</c:url>

				<tbody>
					<tr>
						<td> ${tempCase.key.length} x ${tempCase.key.width} x ${tempCase.key.height} mm</td>
						<td> ${tempCase.key.surface} m² </td>
						<td> ${tempCase.key.type.getType()}</td>
                        <td> ${tempCase.key.material.getMaterial()}</td>
                        <td> ${tempCase.key.color.getColor()}</td>
                        <td> ${tempCase.key.filling.getFilling()}</td>
                        <td> ${tempCase.key.handle.getHandle()} - ${tempCase.key.getHandleNum() } </td>
                        <td> ${tempCase.key.getWheels()} - ${tempCase.key.getWheelNum()}</td>
                        <td> ${tempCase.key.locks.getLocks()}
						<td> ${tempCase.key.comments}</td>
						<td> ${tempCase.key.price}</td>
						<td>
                            <form:form action="updateQuantity" modelAttribute="tempQuantity" method="PUT">
                                <input name="tempCase" type="hidden" value="${tempCase.key}"/>
                                <input name="orderId" type="hidden" value="${orderId}"/>
                                <input name="clientId" type="hidden" value="${clientId}"/>
                                <div class="input-group input-group-sm">
                                    <form:input class="form-control"
                                                path="quantity"
                                                value="${tempCase.value.quantity}"
                                                />
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-outline-secondary"> Zapisz ilość</button>
                                    </div>
                                </div>
                            </form:form>
						</td>
						<td>
							<a class="btn btn-dark btn-sm" href="${updateLink}">Modyfikuj</a>
							<a class="btn btn-danger btn-sm" href="${deleteLink}"
							onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
							Usuń</a>
						<td>
						
					</tr>
				</tbody>
				</c:forEach>
				
			</table>

		
		</div>
	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>








</html>