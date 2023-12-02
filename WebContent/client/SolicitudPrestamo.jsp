<%@page import="dominio.Cuenta"%>
<%@page import="dominio.Intereses"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Prestamo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<% if(session.getAttribute("client") != null) {%>
<jsp:include page="./header.jsp" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container">
    <h1 class="mt-5">Solicitud de Prestamo</h1>
    <div class="marcoBott">
    <form id="loanForm" action="SolicitudPrestamoServlet" method="post">
        <div class="mb-3">
            <label for="monto" class="form-label">Monto a Solicitar (ARS)</label>
            <input required type="number" class="form-control" id="monto" placeholder="Monto en ARS" oninput="calculateFinalAmount()" name="monto">
        </div>
        <div class="mb-3">
            <label for="cuotas" class="form-label">Cantidad de Cuotas</label>
            <select class="form-select" id="cuotas" onchange="calculateFinalAmount()" name="interes">
                <option value="10" label="6"></option>
                <option value="20" label="12"></option>
                <option value="30" label="18"></option>
            </select>
            <label id="interesesLabel" for="cuotas" class="form-label" ></label>
            <input id="cantCuotas" name="cantCuotas" style="display: none;">
        </div>
        <div class="mb-3">
            <label for="cuentas" class="form-label">Seleccionar Cuenta</label>
            <select class="form" id="cuentas" name="cuenta">
                <% ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>)session.getAttribute("cuentas"); %>
                <% for(Cuenta cuenta : cuentas) {%>
                <option value="<%= cuenta.getNumero_Cuenta() %>">
                    <strong>Cuenta $ <%= cuenta.getNumero_Cuenta() %></strong><br>
                    <span style="font-size: 12px; color: #555;">Saldo actual: <%= cuenta.getSaldo() %>$</span>
                </option>
                <% } %>

            </select>
        </div>
        <div class="mb-3">
            <label for="costoFinal" class="form-label">Costo final (ARS)</label>
            <input type="number" class="form-control" id="costoFinal" name="costoFinal" value="" disabled>
        </div>
        <button type="button" id="acceptButton" class="btn btn-primary " data-bs-toggle="modal" data-bs-target="#confirmModal">
            Aceptar
        </button>
        <button type="button" class="btn btn-secondary">Cancelar</button>
    </form>
    </div>
    <div class="marcoTabla" >

        <%
            ArrayList<Prestamo> prestamos=(ArrayList<Prestamo>) session.getAttribute("prestamos");
            if(prestamos != null){
                out.println("<h1 class='mt-5 marcoTitu'>Prestamos Pedidos</h1>");
        %>
        <table id="tablaConPaginadorYFiltro" class="display">
            <thead>
                <tr >
                    <th>Numero de Prestamo</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                    <th>Cantidad de Cuotas</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <% for(Prestamo prestamo : prestamos) {%>
                <tr>
                    <td><%= prestamo.getNumero_Cuenta()%></td>
                    <td><%= prestamo.getTotalImporte() %></td>
                    <td><%= prestamo.getFechaPedido() %></td>
                    <td><%= prestamo.getCuotas() %></td>
                    <td><% if(prestamo.getEstado()==1) out.println("<span  class='badge badge-success'>Aprobado</span>"); else if(prestamo.getEstado()==-1) out.println("<span  class='badge badge-danger'>Rechazado</span>"); else out.println("<span  class='badge badge-Secondary'>Pendiente</span>"); %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <%
            }
        %>
    </div>
</div>

<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">Confirmacion de Costo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p id="costoFinalModal"></p>
                <p id="cuotasModal"></p>
                <p id="montoCuotasModal"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Rechazar</button>
                <button id="btnAcceptModal" type="button" class="btn btn-primary">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Error de Validación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="errorMessage"></div>
        </div>
    </div>
</div>


<jsp:include page="./footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        calculateFinalAmount();
    });
    document.getElementById("btnAcceptModal").addEventListener("click", function() {
        // Validar campos antes de enviar el formulario
        if (validateFields()) {
            // Mostrar SweetAlert2 para confirmar la acción
            Swal.fire({
                title: '¿Estás seguro?',
                text: '¿Quieres enviar el formulario?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Sí',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    document.getElementById("loanForm").submit();
                }
            });
        }
    });
    
    
    function validateFields() {
        const amount = parseInt(document.getElementById("monto").value);
        const interest = parseInt(document.getElementById("cuotas").value);
        const cuotas = parseInt(document.getElementById("cuotas").options[document.getElementById("cuotas").selectedIndex].label);

        // Validar monto
        if (isNaN(amount) || amount <= 0) {
	        showAlert("El monto debe ser un número positivo y no debe contener letras.");
	        return false;
	    }




        // Todas las validaciones pasaron
        return true;
    }

    function showAlert(message) {
        Swal.fire({
            title: 'Error de Validación',
            text: message,
            icon: 'error',
            confirmButtonText: 'Cerrar'
        });
    }
    
    function calculateFinalAmount(){
        const amount = parseInt(document.getElementById("monto").value);
        const interest = parseInt(document.getElementById("cuotas").value);
        const cuotas = parseInt(document.getElementById("cuotas").options[document.getElementById("cuotas").selectedIndex].label);
        const finalAmount = document.getElementById("costoFinal");
        const finalAmountModal = document.getElementById("costoFinalModal");
        const cuotasModal = document.getElementById("cuotasModal");
        const montoCuotasModal = document.getElementById("montoCuotasModal");
        const interesesLabel = document.getElementById("interesesLabel");
        const total = ((amount * (interest/100)) + amount);
		
        console.log(amount);

        finalAmount.value = total;
        finalAmountModal.innerHTML = "Monto final con intereses: " + total + "$";
        montoCuotasModal.innerHTML = "Monto por cuota a pagar: " + ((amount + (amount * (interest/100)))/cuotas).toFixed(2) + "$";
        interesesLabel.innerHTML = "Intereses fijos: " + interest + "%";
        document.getElementById("cantCuotas").value = cuotas;



    }
    $(document).ready(function() {
        let table = new DataTable('#tablaConPaginadorYFiltro', {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
            },
        });
    });
</script>

<% }else {
	response.sendRedirect("../index.jsp");
}%>