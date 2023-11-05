<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("admin") != null) {%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
   <link rel="stylesheet" href="../assets/css/style.css" />
<title>Reportes administrativos</title>
</head>
<body>
	<form>
	<div class="reportes-titulo">
		<h1>REPORTES</h1>
	</div>
	<div class="reportes-grupo">
			<div class="reportes-div">
			<p>(Reporte 1)<p>
		</div>
		<div class="reportes-div">
			<select class="reportes-sel">
				<option>Filtro 1</option>
				<option>Filtro 2</option>
				<option>Filtro 3</option>
			</select>
		</div>
		<div class="reportes-div">
			<button type="button" class="btn btn-outline-primary">Ver información</button>
		</div>
	</div>	
	<div class="reporte-grupo">
		<div class="reportes-div">
			<p>(Reporte 2)<p>
		</div>
		<div class="reportes-div">
			<select class="reportes-sel">
				<option>Filtro 1</option>
				<option>Filtro 2</option>
				<option>Filtro 3</option>
			</select>
		</div>
		<div class="reportes-div">
			<button type="button" class="btn btn-outline-primary">Ver información</button>
		</div>
	</div>
	</form>

</body>
</html>
<% }else {
	response.sendRedirect("../index.jsp");
}%>