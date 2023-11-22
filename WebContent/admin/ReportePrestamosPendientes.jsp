<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dominio.Cliente" %>
    <%@page import="dominio.Prestamo" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form>
		<h1>SECCION DE REPORTES</h1>
		<h3>Ingrese un valor. Luego pulse el botón. Debajo podrá visualizar un listado con los prestamos iguales o mayores al monto ingresado y el cliente que lo realizó.</h3>
		<label>Monto: $</label>
		<input type="number" name="txtNumber" min="1">
		<input type="submit" name="btnAceptar" value="Aceptar">
		
			  <%
        ArrayList<Prestamo> prestamos = null;
        if (request.getAttribute("listadoPrestamos") != null) {
            prestamos = (ArrayList<Prestamo>) request.getAttribute("listadoPrestamos");
        %>
		<div class="div-tabla-reporte" id="contenidoTabla">
        <table class="table table-success table-striped">

            <thead>
                <tr>
                    <th>N Prestamo</th>
                    <th>Monto</th>
					<th>Cantidad de cuotas</th>    
    				<th>Monto de las cuotas</th>           
        			<th>Fecha del Prestamo</th>         
            		<th>Nº cuenta que lo adeuda</th>             
                </tr>
            </thead>
            <tbody>
            <%
                for (Prestamo prestamo : prestamos) {
            %>
                <tr>     
                    <td><%=prestamo.getIdPrestamo()%></td>
                    <td><%=prestamo.getNumero_Cuenta()%></td>
                     <td><%=prestamo.getTotalImporte()%></td>   
                    <td><%=prestamo.getFechaPedido()%></td>
                     <td><%=prestamo.getImporteCuota()%></td>
                     <td><%=prestamo.getCuotas()%></td> 
            <%
                }
            }
            %>
            </tbody>
        </table>
</form>
</body>
</html>