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
        Operación exitosa.
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
							Aprobación de Préstamos <b> Banco LAB IV </b>
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<script>
document.addEventListener('DOMContentLoaded', function() {
    let table = new DataTable('#tablaConPaginadorYFiltro', {
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
        },
    });

    // Manejar clic en botones de Aceptar y Rechazar
    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('btn-success')) {
            // Botón de Aceptar
            let idPrestamo = event.target.parentElement.querySelector('input[name="idPrestamo"]').value;
            let estadoPrestamo = event.target.parentElement.querySelector('input[name="estadoPrestamo"]').value;
            mostrarSweetAlert(idPrestamo, estadoPrestamo, 'success');
        } else if (event.target.classList.contains('btn-warning')) {
            // Botón de Rechazar
            let idPrestamo = event.target.parentElement.querySelector('input[name="idPrestamo"]').value;
            let estadoPrestamo = event.target.parentElement.querySelector('input[name="estadoPrestamo"]').value;
            mostrarSweetAlert(idPrestamo, estadoPrestamo, 'warning');
        }
    });

    // Función para mostrar SweetAlert
    function mostrarSweetAlert(idPrestamo, estadoPrestamo, icon) {
        Swal.fire({
            title: '¿Estás seguro?',
            text: 'Desea continuar?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar',
        }).then((result) => {
            if (result.isConfirmed) {
                // Usuario hizo clic en "Aceptar", enviar datos mediante Ajax
                enviarDatosAjax(idPrestamo, estadoPrestamo);
            }
        });
    }

    // Función para enviar datos mediante Ajax
    function enviarDatosAjax(idPrestamo, estadoPrestamo) {
        // Crear un objeto XMLHttpRequest
        let xhr = new XMLHttpRequest();
        
        // Configurar la solicitud
        xhr.open('POST', 'AprobarPrestamosServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        // Manejar el evento de carga
        xhr.onload = function () {
            if (xhr.status === 200) {
                // La solicitud fue exitosa, procesar la respuesta si es necesario
                console.log(xhr.responseText);
            }
        };

        // Crear datos a enviar
        let datos = 'idPrestamo=' + encodeURIComponent(idPrestamo) + '&estadoPrestamo=' + encodeURIComponent(estadoPrestamo);

        // Enviar la solicitud
        xhr.send(datos);
    }
});
</script>


