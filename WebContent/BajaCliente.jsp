<%@page import="dominio.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<form action="BajaClienteServlet" method="post" >
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
	<tr> <th> Nombre </th><th> Apellido </th><th> DNI </th> </tr>
	<%
	if(listaClientes != null)
		for(Cliente cli : listaClientes){

	
	 %>
	<tr> <td><%=cli.getNombre() %></td> <td><%=cli.getApellido() %></td> <td><%=cli.getDNI() %></td> </tr>
	<%} %>
	</table>
	

	
</body>
</html>