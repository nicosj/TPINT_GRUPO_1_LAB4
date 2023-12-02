<%@page import="dominio.PagoPrestamo"%>
<%@page import="java.util.Objects"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% if(session.getAttribute("cliente") != null) { %>

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
                        <th>ID Pago</th>
                        <th>Numero de Cuenta</th>
                        <th>Fecha de Pago</th>
                        <th>Importe de Cuota</th>
                        <th>Importe Restante</th>
                        <th>Cuotas Restantes</th>
                        <th>ID Prestamo</th>
                        <th>Acciones</th>
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

                        
                        for (PagoPrestamo pago : pagos) {
                    %>
                        <tr>
                            <td>ID Pago</td>
                            <td>Numero de Cuenta</td>
                            <td>Fecha de Pago</td>
                            <td>$ Importe de Cuota</td>
                            <td>$ Importe Restante</td>
                            <td>Cuotas Restantes</td>
                            <td>ID Prestamo</td>
                            <td>
                                <form method="post" action="PagarPrestamosServlet">
                                    <input type="hidden" name="idPrestamo" value="1">
                                    <button type="submit" class="btn btn-success" name="pagarCuota">💸 Pagar</button>
                                </form>
                            </td>
                        </tr>
                    <%
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

<% } else {
    response.sendRedirect("../index.jsp");
}%>
	