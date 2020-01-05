<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
	<title>WMCASE - </title>
	<meta charset="UTF-8">

    <!--
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	-->
	<link rel="stylesheet"
	      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	      crossorigin="anonymous">

</head>

<body>
<c:set var="now" value="<%=new java.util.Date()%>" />

	<div class="container-fluid bg-dark m-0">
        <h2 class="text-light text-center p-3">WMCase - Prośba o wycenę</h2>
	</div>
	
	
	<div class="container-fluid allign-center">

        <form:form action="newCaseInOrder" method="GET">
            <input name="order" type="hidden" value="${order}"/>
            <input type="submit" value="Dodaj Skrzynkę" class="btn btn-dark my-3" />
        </form:form>

        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="10">Skrzynki</th>
                </tr>
                <tr>
                    <th scope="col">Wymiary</th>
                    <th scope="col">Typ</th>
                    <th scope="col">Materiał</th>
                    <th scope="col">Kolor</th>
                    <th scope="col">Wypełnienie</th>
                    <th scope="col">Rączki</th>
                    <th scope="col">Koła</th>
                    <th scope="col">Zamki</th>
                    <th scope="col">Uwagi</th>
                    <th scope="col">Ilość</th>
                    <th scope="col">Akcja</th>
                </tr>

            <c:forEach var="tempCase" items="${order.getCaseList()}">

            <!-- Update and delete Links -->
            <c:url var="updateLink" value="/updateCase">
                <c:param name="caseId" value="${tempCase.id}"/>
                <c:param name="orderId" value="${order}" />
            </c:url>

            <c:url var="deleteLink" value="/deleteCase">
                <c:param name="caseId" value="${tempCase.id}"/>
                <c:param name="orderId" value="${order}" />
            </c:url>

                <tbody>
                    <tr>
                        <td> ${tempCase.key.length} x ${tempCase.key.width} x ${tempCase.key.height} mm</td>
                        <td> ${tempCase.key.type.getType()}</td>
                        <td> ${tempCase.key.material.getMaterial()}</td>
                        <td> ${tempCase.key.color.getColor()}</td>
                        <td> ${tempCase.key.filling.getFilling()}</td>
                        <td> ${tempCase.key.handle.getHandle()} - ${tempCase.getHandleNum() } </td>
                        <td> ${tempCase.key.getWheels()} - ${tempCase.getWheelNum()}</td>
                        <td> ${tempCase.key.locks.getLocks()} </td>
                        <td> ${tempCase.key.comments}</td>
                        <td>
                            <form:form action="updateQuantity" method="PUT">
                            <form:input class="form-control col text-right"
                                        path="order.cases"
                                        value="${order.getCases().get(tempCase)}"/>
                            </form:form></td>
                        <td>
                            <a href="${updateLink}">Modyfikuj</a>
                             |
                            <a href="${deleteLink}"
                            onclick="if (!(confirm('Czy na pewno chcesz usunąć skrzynkę?'))) return false">
                            Usuń</a>
                        <td>
                    </tr>
                </tbody>
            <%-- </c:forEach> --%>
            </c:forEach>

        </table>
        <hr>


        <h3 class="">Dane kontaktowe:</h3>
        <form:form action="sendOrder" modelAttribute="order" method="POST">
            <form:hidden path="id"/>

            <div class="form-inline">
                <div class="col-auto form-group row">
                    <label class="col-form-label">Imię:</label>
                    <form:input class="form-control mx-3 text-right" type="text" path="client.name" />
                </div>
                <div class="col-auto form-group row">
                    <label class="col-form-label">Nazwisko:</label>
                    <form:input class="form-control mx-3 text-right" type="text" path="client.surname" />
                </div>
                <div class="col-auto form-group row">
                    <label class="col-form-label">Email:</label>
                    <form:input class="form-control mx-3 text-right" type="text" path="client.email" />
                </div>
                <div class="col-auto form-group row">
                    <label class="col-form-label">Telefon:</label>
                    <form:input class="form-control ml-3 text-right" type="text" path="client.tel" />
                </div>
            </div>
            <div>
                <label class="col-form-label">Komentarz:</label>
                <form:textarea rows="4" cols="30" class="form-control" type="text" path="comments" />
            </div>

            <button type="submit" class="btn btn-dark my-3" >Wyślij zapytanie</button>

         </form:form>

        <a href="${pageContext.request.contextPath}/clientOrders?clientId=${clientId}">Wróć do zamówień</a>
	
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>








</html>