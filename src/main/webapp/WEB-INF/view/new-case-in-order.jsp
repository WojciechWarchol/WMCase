<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- The below enabled Polish character rendering -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="pl">

<head>
<title>WMCASE - skrzynki</title>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<!-- 
	<link type="text/css"
		rel= "stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />
	 -->
<link rel="stylesheet"
	  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">

    </head>


<body>

	<div class="container allign-center" id="wrapper">
		<div id="header">
			<h2>WMCase - Tworzenie skrzynki</h2>
		</div>
	</div>

	<div class="container" id="form">
		<h3>
			<font face="Lato">Stwórz skrzynkę 
		</h3>

		<form:form action="addCaseToOrder" modelAttribute="case" method="POST">

			<form:hidden path="id" />
			<%-- <input name="orderId" type="hidden" value="${orderId}"/>
			<input name="clientId" type="hidden" value="${clientId}"/> --%>

			<!-- Needs a Controller method -->


			<div class="form-group row">
				<label class="col-form-label col-sm-2">Długość:</label>
				<div class="input-group col-sm-3 px-0">
					<form:input class="form-control text-right" path="length" />
					<div class="input-group-append">
						<span class="input-group-text">mm</span>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Szerokość:</label>
				<div class="input-group col-sm-3 px-0">
					<form:input class="form-control text-right" path="width" />
					<div class="input-group-append">
						<span class="input-group-text">mm</span>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Wysokość:</label>
				<div class="input-group col-sm-3 px-0">
					<form:input class="form-control text-right" path="height" />
					<div class="input-group-append">
						<span class="input-group-text">mm</span>
					</div>
				</div>
			</div>
			<hr>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Typ:</label>
				<form:select class="form-control col-sm-3" path="type">
					<form:options itemLabel="type" />
				</form:select>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Wypełnienie:</label>
				<form:select class="form-control col-sm-3" path="filling">
					<form:options itemLabel="filling" />
				</form:select>
			</div>
			<hr>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Materiał:</label>
				<form:select class="form-control col-sm-3" path="material">
					<form:options itemLabel="material" />
				</form:select>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Kolor:</label>
				<form:select class="form-control col-sm-3" path="color">
					<form:options itemLabel="color" />
				</form:select>
			</div>
			<hr>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Rączki:</label>
				<form:select class="form-control col-sm-3" path="handle">
					<form:options itemLabel="handle" />
				</form:select>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Ilość rączek:</label>
				<form:input class="form-control col-sm-3 text-right"
					path="handleNum" />
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Koła:</label>
				<form:select class="form-control col-sm-3" path="wheels">
					<form:option value="true" label="Tak" />
					<form:option value="false" label="Nie" />
				</form:select>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Ilość kół:</label>
				<form:input class="form-control col-sm-3 text-right" path="wheelNum" />
			</div>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Zamki:</label>
				<form:select class="form-control col-sm-3" path="locks">
					<form:options itemLabel="locks" />
				</form:select>
			</div>
			<hr>
			<div class="form-group row">
				<label class="col-form-label col-sm-2">Uwagi:</label>
				<form:textarea rows="8" cols="30" class="form-control col-sm-3"
					path="comments" />
			</div>
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-3 px-0 d-flex flex-row">
					<a class="btn btn-light flex-fill mr-1"
						href="${pageContext.request.contextPath}/continueOrder?cases=${cases}">Wróć</a>
					<label></label>
					<button type="submit" class="btn btn-dark flex-fill ml-1">Dodaj</button>
				</div>
			</div>


		</form:form>

		<div style=""></div>

		<p></p>


	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>

</html>