<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
  <link rel="stylesheet" href="../assets/css/style.css" />
  <title>Baja de cuentas</title>
</head>
<body>

	<style>
/* Estilo para el body */
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
.div-tabla{
	display:flex;
	justify-content: center;
	aling-items: center;
}
</style>
</head>
<body>		
		<form id="accountForm" action="BajaCuentaServlet" method="post">					
			<input type="button" name="inputCargar" value="Carga">
		</form>
			<%
			ArrayList<Cuenta> listaCuentas = null;
			if (request.getAttribute("cuentas") != null) {
			    listaCuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
			}	
			%>
			<div class="historialCuentas-div">				
				<table class="table">
				    <tr>
				        <th scope="row">CBU N:</th>
				    </tr>
				    <%
				    if (listaCuentas != null) {
				        for (Cuenta acc : listaCuentas) {
				        	if(acc.getEstado())
				    %>
				  
				    <tr>
				    	<form action="BajaCuentaServlet" method="post">
				        <td ><%= acc.getCBU() %></td>
				       		<th scope="row"> Numero de cuenta: </th>
				        <td> <%= acc.getNumero_Cuenta()%> value="<%= acc.getNumero_Cuenta()%>">  </td>
				        <td> <input type="submit" name="btnBajaCuenta" value="Eliminar" class="btn btn-outline-primary"></td>
				   		</form>
				    </tr>
				    <%
				        }
				    }
				    %>
				</table>
		</div>	
</body>
</html>