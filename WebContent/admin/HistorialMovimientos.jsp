<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Movimiento" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="./header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@1&family=Dawning+of+a+New+Day&family=Kanit&family=Nunito:ital,wght@1,200&family=Quicksand&family=Roboto+Slab:wght@500&family=Shadows+Into+Light&display=swap');
</style>

<style>
    body {
        margin: 10px;
    }
    .div-cuentas {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .div-cuentas h1,
    .div-cuentas a,
    .div-cuentas select,
    .div-cuentas input {
        margin-bottom: 20px;
    }
    .div-cuentas select {
        width: 30%;
    }
    .div-cuentas h1, .historialCuentas-titulo {
        margin-top: 20px;
        font-family: 'DM Serif Display', serif;
        font-size: 80px;
        font-weight: bold;
        color: #17A2B8;
    }
    .div-cuentas h3{
        	font-family: 'Roboto Slab', serif;
            font-size: 30px;
            color: black;
            margin-bottom: 30px;
        }
    .div-cuentas a {
        font-family: 'Roboto Slab', serif;
        text-align: center;
        text-decoration: none;
        font-size: 20px;
        color: #17A2B8;
    }
    table {
        font-family: 'Roboto Slab', serif;
        font-size: 14px;
        margin: 20px;
    }
    .table-container {
        max-width: 80%;
        margin: 0 auto;
    }
</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos de clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link rel="stylesheet" href="../assets/css/style.css" />

<script>
    function cargarMovimientos() {
        document.forms['movimientosForm'].submit();
    }
</script>
</head>
<body>
<form method="get" action="HistorialMovimientosServlet">
    <div class="div-cuentas">
        <h1>Movimientos de clientes </h1>
        <h3>Discriminados por cuentas</h3>
        <select name="idCliente" class="form-select" onchange="this.form.submit()">
             <option value="">Seleccione un cliente</option> <!-- Primer elemento vacío -->
  
            <%
                ArrayList<Cliente> clientes = null;
                if (request.getAttribute("listadoClientes") != null) {
                    clientes = (ArrayList<Cliente>) request.getAttribute("listadoClientes");
                }
                if (clientes != null) {
                    for (Cliente cliente : clientes) {
            %>
                        <option value="<%= cliente.getIdCLiente()%>">
                            <%= cliente.getIdCLiente()%> 
                        </option>
            <%
                    }
                }
            %>
        </select>
    </div>
</form>

<div class="table-container">
    <%
        ArrayList<Movimiento> movimientos = null;
        if (request.getAttribute("listadoMovimientos") != null) {
            movimientos = (ArrayList<Movimiento>) request.getAttribute("listadoMovimientos");
    %>
        <div class="historialCuentas-titulo">
            <h1>Historial del cliente: <%= request.getParameter("idCliente") %></h1>
        </div>
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
</body>
</html>
<jsp:include page="./footer.jsp" />