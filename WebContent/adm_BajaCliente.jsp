<%@page import="dominio.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("admin") != null) {%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Baja de Clientes </title>
</head>

<body>
	<form action="BajaClienteServlet" method="get" >
		<input type="submit" name="btnMostrarClientes"
			value="Mostrar Clientes">
		</form>
	
	
	<%
	ArrayList<Cliente> listaClientes=null;
	if(request.getAttribute("listaC")!=null)
	{
		listaClientes = (ArrayList<Cliente>) request.getAttribute("listaC");
	}
	%>
	
	<table border="1">
	<tr> <th> ID </th><th> Nombre </th><th> Apellido </th><th> DNI </th>  </tr>
	<%
	if(listaClientes != null)
		for(Cliente cli : listaClientes){

	
	 %>
<tr> 
    <form action="BajaClienteServlet" method="post">
        <td><%= cli.getIdCLiente() %> <input type="hidden" name="idcliente" value="<%= cli.getIdCLiente() %>"></td>
        <td><%= cli.getNombre() %></td> 
        <td><%= cli.getApellido() %></td> 
        <td><%= cli.getDNI() %></td> 
        <td> <input type="submit" name="btnEliminarCliente" value="Eliminar" 
             onclick="return confirm('¿Está seguro de que desea eliminar el cliente con los siguientes datos?\n\nID: <%= cli.getIdCLiente() %>\nNombre: <%= cli.getNombre() %>\nApellido: <%= cli.getApellido() %>\nDNI: <%= cli.getDNI() %>');">
    </form>
</tr>

	<%} %>
	</table>
	

	
</body>
</html>
<% }else {
	response.sendRedirect("../index.jsp");
}%>