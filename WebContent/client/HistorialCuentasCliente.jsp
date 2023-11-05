<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("client") != null) {%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Historial de la cuenta</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
   <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
	<form>
		<div class="historialCuentas-titulo">
		<h1 class="fs-1">Historial de movimientos</h1>
		</div>
		<div class="historialCuentas-div">
			  <ul class="list-group">
				  <li class="list-group-item">movimiento 1</li>
				  <li class="list-group-item">movimiento 2</li>
				  <li class="list-group-item">movimiento 3</li>
				  <li class="list-group-item">movimiento 4</li>
			 </ul>
		<div class="botonHistorialCuentas-div">
			<button type="button" class="btn btn-primary btn-sm">Volver atrás</button>
		</div>		 
		</div>
	</form>
</body>
</html>
<% }else {
	response.sendRedirect("../index.jsp");
}%>
