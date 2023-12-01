<%@page import="java.util.Objects"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cliente"%>
<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp" />
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%
    String resultado = request.getParameter("resultado");
    if (resultado != null && resultado.equals("exito")) {
%>
    <div class="alert alert-success" role="alert">
        Operaci�n exitosa.
    </div>
<%
    }
%>
<div class="container-fluid">
	<div class="table-responsive">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>
							Aprobaci�n de Pr�stamos <b> Banco LAB IV </b>
						</h2>
					</div>

				</div>
			</div>
			<table id="tablaConPaginadorYFiltro" class="display">
				<thead>
					<tr>
						<th>ID Prestamo</th>
						<th>Numero de Cuenta</th>
						<th>Cliente</th>
						<th>Fecha de Pedido</th>
						<th>Importe Cuota</th>
						<th>Importe Total</th>
						<th>Cantidad de Cuotas</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<%
                	ArrayList<Cliente> clientes = null;
                    ArrayList<Cuenta> cuentas = null;
                    ArrayList<Prestamo> prestamos = null;
                	
                    if(session.getAttribute("prestamos")!=null)
                    	
                    {	clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
                        prestamos = (ArrayList<Prestamo>) session.getAttribute("prestamos");
                        cuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");
                    }

                %>
				<tbody>
<%
    if (prestamos != null) {
        for (Prestamo p : prestamos) {
%>
            <tr>
                <form method="post" action="AprobarPrestamosServlet">
                    <td><%=p.getIdPrestamo()%></td>
                    <td><%=p.getNumero_Cuenta()%></td>
                    <%
   
                        for(Cuenta c : cuentas){
                            if(Objects.equals(c.getNumero_Cuenta(), p.getNumero_Cuenta())){
                                for(Cliente cl : clientes){
                                    if(Objects.equals(cl.getIdCLiente(), Integer.parseInt(c.getIdCliente()))){
                                        %>
                                        <td><%=cl.getNombreCompleto()%></td>
                                        <%
                                    }
                                }
                            }
                        }

                    %>
                    <td><%=p.getFechaPedido()%></td>
                    <td><%=p.getImporteCuota()%></td>
                    <td><%=p.getTotalImporte()%></td>
                    <td><%=p.getCuotas()%></td>
                    <td><%=p.getEstado()%></td>

                    <td>
                        <input type="hidden" name="idPrestamo" value="<%=p.getIdPrestamo()%>">
                        <input type="hidden" name="estadoPrestamo" value="<%=p.getEstado()%>">
                        <button type="submit" class="btn btn-success" name="aprobarPrestamo">Aprobar</button>
                        <button type="submit" class="btn btn-warning" name="rechazarPrestamo">Rechazar</button>
                    </td>
                </form>
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

<!--EOF-->
<% }else {
	response.sendRedirect("../index.jsp");
}%>

<script>

//Script para el paginado + buscar
let table = new DataTable('#tablaConPaginadorYFiltro', {
    language: {
        url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
    },
});

</script>

