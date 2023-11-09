<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="dominio.Cuenta" %>
<%@page import="dominio.Movimiento" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <a href="HistorialMovimientosServlet">Ver cuentas</a>
        <select name="selectedCuenta">
            <%
            ArrayList<Cuenta> cuentas = null;
            if (request.getAttribute("listadoCuentas") != null) {
                cuentas = (ArrayList<Cuenta>) request.getAttribute("listadoCuentas");
            }
            if (cuentas != null) {
                for (Cuenta cuenta : cuentas) {
            %>
                <option value="<%= cuenta.getNumero_Cuenta() %>">
                    <%= cuenta.getNumero_Cuenta() %> 
                </option>
            <%
                }
            }
            %>
        </select>
        <input type="submit" name="btnSeleccionar" value="Seleccionar">
    </form>

    <form method="post" action="HistorialMovimientosServlet">
        <input type="hidden" name="numeroCuenta" value="<%= request.getParameter("selectedCuenta") %>">
        <div class="historialCuentas-titulo">
            <h1 class="fs-1">Historial de movimientos</h1>
        </div>
        <div class="historialCuentas-div">
            <input type="submit" name="btnMostrar" value="Ver movimientos">
        </div>
        <%
        ArrayList<Movimiento> movimientos = null;
        if (request.getAttribute("listadoMovimientos") != null) {
            movimientos = (ArrayList<Movimiento>) request.getAttribute("listadoMovimientos");
        %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Número de Cuenta</th>
                    <th>Fecha de Movimiento</th>
                    <th>Detalle/Concepto</th>
                    <th>Importe de Movimiento</th>
                    <th>Tipo de Movimiento</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Movimiento movimiento : movimientos) {
            %>
                <tr>     
                    <td><%= movimiento.getNumero_Cuenta() %></td>
                    <td><%= movimiento.getFechaMovimiento() %></td>
                    <td><%= movimiento.getDetalleConcepto() %></td>
                    <td><%= movimiento.getImporteMovimiento() %></td>
                    <td><%= movimiento.getTipoMovimiento() %></td>
                </tr>
            <%
                }
            }
            %>
            </tbody>
        </table>
    </div>
    </form>
</body>
</html>