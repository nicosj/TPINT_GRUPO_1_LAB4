<%@page import="dominio.Cuenta"%>
<%@page import="dominio.Intereses"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% if(session.getAttribute("client") != null) {%>
<jsp:include page="./header.jsp" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container">
    <h1 class="mt-5">Solicitud de Prestamo</h1>
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
            <select class="form-select" id="cuentas" name="cuenta">
                <% List<Cuenta> cuentas = (List<Cuenta>)request.getAttribute("cuentas"); %>
                <% for(Cuenta cuenta : cuentas) {%>
                <option value="<%= cuenta.getNumero_Cuenta() %>">
                    <strong>Cuenta n° <%= cuenta.getNumero_Cuenta() %></strong><br>
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

<jsp:include page="./footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        calculateFinalAmount();
    });
    document.getElementById("btnAcceptModal").addEventListener("click", function(){
        console.log("click");
        document.getElementById("loanForm").submit();
    })
    function calculateFinalAmount(){
        const amount = document.getElementById("monto").value;
        const interest = document.getElementById("cuotas").value;
        const cuotas = document.getElementById("cuotas").options[document.getElementById("cuotas").selectedIndex].label;
        const finalAmount = document.getElementById("costoFinal");
        const finalAmountModal = document.getElementById("costoFinalModal");
        const cuotasModal = document.getElementById("cuotasModal");
        const montoCuotasModal = document.getElementById("montoCuotasModal");
        const interesesLabel = document.getElementById("interesesLabel");
        const total = (amount * (interest/100)) + amount;

        console.log(interest/100);
        console.log(cuotas);
        console.log((amount * (interest/100)));

        finalAmount.value = total;
        finalAmountModal.innerHTML = "Monto final con intereses: " + total + "$";
        montoCuotasModal.innerHTML = "Monto por cuota a pagar: " + ((amount + (amount * (interest/100)))/cuotas).toFixed(2) + "$";
        interesesLabel.innerHTML = "Intereses fijos: " + interest + "%";
        document.getElementById("cantCuotas").value = cuotas;


    }

</script>

<% }else {
	response.sendRedirect("../index.jsp");
}%>