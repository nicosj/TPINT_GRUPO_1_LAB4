<%@page import="java.util.Objects"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="dominio.*" %>
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
                        <th>Importe Total</th>
                        <th>accion</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        /*ArrayList<Cliente> clientes = null;*/
                        ArrayList<Cuenta> cuentas = null;
                        ArrayList<Prestamo> prestamos = null;
                        /*ArrayList<PagoPrestamo> pagos = null;*/
                        double[] intereses = {0.1, 0.2, 0.3};
                        int pos = 0;
                        double montoIntereses = 0;

                        if(session.getAttribute("prestamos") != null) {

                            prestamos = (ArrayList<Prestamo>) session.getAttribute("prestamos");
                            cuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");

                        }


                        for (Prestamo prestamo : prestamos) {
                            if(prestamo.getCuotas() == 6) pos = 0;
                            if(prestamo.getCuotas() == 12) pos = 1;
                            if(prestamo.getCuotas() == 18) pos = 2;
                            montoIntereses = (prestamo.getTotalImporte()*intereses[pos])+prestamo.getTotalImporte();
                    %>
                        <tr>
                             <td><%= prestamo.getCuenta().getNumero_Cuenta() %></td>
                             <td><%= prestamo.getTotalImporte() %></td>
                             <td><%= prestamo.getFechaPedido() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                                <td><%= montoIntereses %></td>
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
        <%    ArrayList<PagoPrestamo> prestamox = (ArrayList<PagoPrestamo>)session.getAttribute("prestamoXU");%>
        <div <%=prestamox != null?"class='container' style='display:block;'":"style='display:none;'"%> >
            <h2 class="marcoTitu">Tabla de Pago de prestamos</h2>
            <table id="tablaConPaginadorYFiltroo" class="display">

                    <thead>
                    <tr>
                        <th>Id Prestamo</th>
                        <th>Fechas de pago</th>
                        <th>Importe de Cuota</th>
                        <th>Importe restante</th>
                        <th>Cuntas Restantes</th>
                        <th>Cuentas disponibles</th>
                        <th>accion</th>
                    </tr>

                    </thead>
                <tbody>
                <%


                    if(prestamox != null){
                    for (PagoPrestamo prestamoss : prestamox) {


                %>
                <tr>

                    <td><%= prestamoss.getPrestamo().getIdPrestamo() %></td>
                    <td><%= prestamoss.getFecha_Pago()!=null?prestamoss.getFecha_Pago():"No Pago" %></td>

                    <td><%= prestamoss.getImporte_cuota() %></td>
                    <td><%= prestamoss.getImporte_restante() %></td>
                    <%%>
                    <td><%= prestamoss.getCuotas_restantes() %></td>
                        <form method="post" action="PagarPrestamosServlet">
                            <td>
                                <Select id="cuotas" name="cuotas" class="form-control" required>
                                    <%
                                        for (Cuenta cuenta : cuentas) {

                                    %>
                                    <option value="<%= cuenta.getNumero_Cuenta() %>"><%= cuenta.getNumero_Cuenta() %></option>
                                    <%

                                        }
                                    %>
                                </Select>
                            </td>
                            <td>
                            <input type="hidden" name="idEstePrestamo" value="<%= prestamoss.getIdPago()%>"/>
                                    <%if(prestamox.size() == 1){ %>
                                <button type="submit" <%=prestamoss.getFecha_Pago() != null ? "class='btn btn-success' disabled" : "class='btn btn-warning'"%> name="pagarEstaCuota"> 💸 PagarEstaCuota
                                </button>

                                    <%} else{ %>
                                <button type="submit"
                                         <%=prestamoss.getFecha_Pago() != null ? "class='btn btn-success' disabled" : "class='btn btn-warning'"%>
                                        name="pagarEstaCuota"> 💸 PagarEstaCuota
                                </button>
                                    <%} %>
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

<script >
    $(document).ready(function () {

        let table = new DataTable('#tablaConPaginadorYFiltro', {
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
                },
            }
        );
        let tableo = new DataTable('#tablaConPaginadorYFiltroo', {
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
                },
            }
        );
    });

</script>

<% }
                    else {
    response.sendRedirect("../index.jsp");
}%>
	