<%@page import="dominio.PagoPrestamo"%>
<%@page import="java.util.Objects"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% if(session.getAttribute("client") != null) { %>

<jsp:include page="./header.jsp" />

<div class="container-fluid">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Pago de Préstamos <b> Banco LAB IV </b></h2>
                    </div>
                </div>
            </div>
            <table id="tablaConPaginadorYFiltro" class="display">
                <thead>
                    <tr>
                        <th>Numero de Prestamo</th>
                        <th>Monto</th>
                        <th>Fecha</th>
                        <th>Cantidad de Cuotas</th>
                        <th>accion</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Cliente> clientes = null;
                        ArrayList<Cuenta> cuentas = null;
                        ArrayList<Prestamo> prestamos = null;
                        ArrayList<PagoPrestamo> pagos = null;

                        if(session.getAttribute("prestamos") != null) {
                            clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
                            prestamos = (ArrayList<Prestamo>) session.getAttribute("prestamos");
                            cuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");
                            pagos = (ArrayList<PagoPrestamo>) session.getAttribute("pagos");
                        }


                        for (Prestamo prestamo : prestamos) {
                    %>
                        <tr>
                             <td><%= prestamo.getNumero_Cuenta() %></td>
                             <td><%= prestamo.getTotalImporte() %></td>
                             <td><%= prestamo.getFechaPedido() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                            <td>
                                <form method="post" action="PagarPrestamosServlet">
                                    <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %> " >
                                    <button type="submit" class="btn btn-success" name="pagarCuota" >💸 Pagar </button>
                                </form>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div>
            <table id="tablaConPaginadorYFiltro" class="display">

                    <thead>
                    <tr>
                        <th>Id Prestamo</th>
                        <th>Fechas de pago</th>
                        <th>Importe de Cuota</th>
                        <th>Importe restante</th>
                        <th>Cuntas Restantes</th>
                        <th>accion</th>
                    </tr>

                    </thead>
                <tbody>
                <%
                    ArrayList<PagoPrestamo> prestamox = (ArrayList<PagoPrestamo>)session.getAttribute("prestamoXU");
                    if(prestamox != null){
                    for (PagoPrestamo prestamoss : prestamox) {
                %>
                <tr>
                    <td><%= prestamoss.getIdPrestamo() %></td>
                    <td><%= prestamoss.getFecha_Pago() %></td>
                    <td><%= prestamoss.getImporte_cuota() %></td>
                    <td><%= prestamoss.getImporte_restante() %></td>
                    <td><%= prestamoss.getCuotas_restantes() %></td>
                    <td>
                        <form method="post" action="PagarPrestamosServlet">
                            <input type="hidden" name="idEstePrestamo" value="<%= prestamoss.getIdPago()%>"/>
                            <button type="submit" class="btn btn-success" name="pagarEstaCuota" > 💸 PagarEstaCuota </button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                    }
                %>
                </tbody>

            </table>
        </div>
    </div>
</div>

<jsp:include page="./footer.jsp" />

<script>
    // Script para el paginado + buscar
    let table = new DataTable('#tablaConPaginadorYFiltro', {
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
        },
    });
</script>

<% }
                    else {
    response.sendRedirect("../index.jsp");
}%>
	