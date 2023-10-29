<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Solicitud de Préstamo</title>
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
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmModal">
                Aceptar
            </button>
            <button type="button" class="btn btn-secondary">Cancelar</button>
        </form>
    </div>

    <!-- Modal de Confirmación -->
    <div class="modal" id="confirmModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Confirmar Solicitud</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro de solicitar el préstamo?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Rechazar</button>
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Aceptar</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoJtKh7z7lGz7fuP4F8nfdFvAOA6Gg/z6Y5J6XqqyGXYM2ntX" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8FqFjcJ6pajs/rfdfs3SO+kD4Ck5BdPtF+to8xMp9MvcJ4/Nd45mOZf48UtVbFfhL22P4a9n+88sv/D+xu" crossorigin="anonymous"></script>

</body>
</html>