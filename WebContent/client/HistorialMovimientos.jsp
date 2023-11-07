<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dominio.Cuenta"%>
        <%@page import="dominio.Movimiento"%>
	<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <% if(session.getAttribute("client") != null) {%> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Historial de la cuenta</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
   <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
	<form method="get" action="HistorialMovimientosServlet">
	<h1>Mis cuentas</h1>
		<%ArrayList<Cuenta> cuentas = null;
        if(request.getAttribute("listadoCuentas")!=null)
        {
            cuentas = (ArrayList<Cuenta>) request.getAttribute("listadoCuentas");
        }%>
		<select name="dropCuentas">
		 <%  if(cuentas!=null)
                for(Cuenta cuenta: cuentas)
             {%>
			<option value="<%= cuenta.getNumero_Cuenta() %>">
			<%=cuenta.getNumero_Cuenta()%> 
			</option>
		<%}%>
		</select>
		<input type="submit" value="Seleccionar" name="btnSeleccionar">
	</form>
	<form method="post" action="HistorialMovimientosServlet">
    <div class="historialCuentas-titulo">
        <h1 class="fs-1">Historial de movimientos</h1>
    </div>
    <div class="historialCuentas-div">
        <ul class="list-group">
            <% if (request.getAttribute("listadoMovimientos") != null) {
                ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) request.getAttribute("listadoMovimientos");
                for (Movimiento movimiento : movimientos) {
            %>
            <li class="list-group-item">
                ID Movimiento: <%= movimiento.getIdMovimiento() %><br>
                Número de Cuenta: <%= movimiento.getNumero_Cuenta() %><br>
                Fecha de Movimiento: <%= movimiento.getFechaMovimiento() %><br>
                Detalle/Concepto: <%= movimiento.getDetalleConcepto() %><br>
                Importe de Movimiento: <%= movimiento.getImporteMovimiento() %><br>
                Tipo de Movimiento: <%= movimiento.getTipoMovimiento() %><br>
            </li>
            <% } %>
            <% } else { %>
            <li class="list-group-item">No hay movimientos disponibles.</li>
            <% } %>
        </ul>
        <div class="botonHistorialCuentas-div">
            <button type="button" class="btn btn-primary btn-sm">Volver atrás</button>
        </div>
    </div>
</form>
</body>
</html>
<%-- <% }else {
	response.sendRedirect("../index.jsp");
}--%> --%>
