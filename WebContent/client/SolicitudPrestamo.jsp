<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solicitud de Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
        <h1 class="mt-5">Solicitud de Préstamo</h1>
        <form id="loanForm">
            <div class="mb-3">
                <label for="monto" class="form-label">Monto a Solicitar (ARS)</label>
                <input type="number" class="form-control" id="monto" placeholder="Monto en ARS">
            </div>
            <div class="mb-3">
                <label for="cuotas" class="form-label">Cantidad de Cuotas</label>
                <select class="form-select" id="cuotas">
                    <option value="6">6 cuotas</option>
                    <option value="12">12 cuotas</option>
                    <option value="24">24 cuotas</option>
                    <option value="32">32 cuotas</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="cuentas" class="form-label">Seleccionar Cuenta</label>
                <select class="form-select" id="cuentas">
                    <option value="cuenta1">
                        <strong>Cuenta n°1</strong><br>
                        <span style="font-size: 12px; color: #555;">Saldo actual: 3450 ARS</span>
                    </option>
                    <option value="cuenta2">
                        <strong>Cuenta n°2</strong><br>
                        <span style="font-size: 12px; color: #555;">Saldo actual: 3450 ARS</span>
                    </option>
                    <option value="cuenta3">
                        <strong>Cuenta n°3</strong><br>
                        <span style="font-size: 12px; color: #555;">Saldo actual: 3450 ARS</span>
                    </option>
                </select>
            </div>
            <div class="mb-3">
                <label for="costoFinal" class="form-label">Costo final (ARS)</label>
                <input type="number" class="form-control" id="costoFinal" value="23500" disabled>
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
	                <h5 class="modal-title" id="confirmModalLabel">Confirmación de Costo</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                <p>Costo final: $23,500</p>
	                <p>Tasa de 240% fija</p>
	                <p>Monto de cuotas a pagar: $2,000</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Rechazar</button>
	                <button type="button" class="btn btn-primary">Aceptar</button>
	            </div>
	        </div>
	    </div>
	</div>

	<script src="../assets/js/solicitudPrestamo.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>