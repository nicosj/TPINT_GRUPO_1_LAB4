<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("client") != null) {%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mis cuentas</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
   <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
	<form>
	<div class="listadoCuentas-titulo">
		<h1 class="fs-1">Visualización de cuentas del usuario</h1>
	</div>
	<div class="listadoCuentas-div">
		<div class="list-group mt-4">
		  <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
		    Cuenta actual
		  </a>
		  <a href="#" class="list-group-item list-group-item-action">Primera cuenta</a>
		  <a href="#" class="list-group-item list-group-item-action">Segunda cuenta</a>
		  <a href="#" class="list-group-item list-group-item-action">Tercera cuenta</a>
		</div>	
	</div>
	<div class="listadoCuentas-titulo">
		<h3 class="fs-5">Para ver el historial de movimientos, seleccione una cuenta.</h3>
	</div>
	
	</form>
</body>
</html>
<% }else {
	response.sendRedirect("../index.jsp");
}%>
