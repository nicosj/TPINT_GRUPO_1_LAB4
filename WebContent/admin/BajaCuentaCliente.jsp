<jsp:include page="./header.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.*"%>



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
		display:block;
		justify-content: center;
		aling-items: center;
	}
	.div.tabla a{
		margin-top: 50px;
		margin-bottom: 20px; 
		justify-content:center;
	}
</style>

		<div class="div-tabla">
			<a href="BajaCuentaServlet">Mostrar las cuentas</a>
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
				        	if(acc.getEstado()){
				    %>
						  
						    <tr>
						    	<form action="BajaCuentaServlet" method="post">
						        <td ><%= acc.getCBU() %></td>
						       		<th scope="row"> Numero de cuenta: </th>
						        <td> <%= acc.getNumero_Cuenta()%> <input type="hidden" name="numCuenta" value="<%= acc.getNumero_Cuenta()%>">  </td>
						        <td> <input type="submit" name="btnBajaCuenta" value="Eliminar" class="btn btn-outline-primary"></td>
						   		</form>
						    </tr>
						    <%
						    }
				        }
				    }
				    %>
				</table>
			</div>	
		</div>
		<jsp:include page="./footer.jsp" />
