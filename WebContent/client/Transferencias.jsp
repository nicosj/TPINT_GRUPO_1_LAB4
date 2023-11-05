<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("client") != null) {%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Transferencias</title>
</head>
<body>
<div class="container">
        <h1 class="mt-5">Transferencias</h1>
        <form id="loanForm">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
  <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
  <label class="btn btn-outline-primary" for="btnradio1">A Cuenta Propia</label>

  <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
  <label class="btn btn-outline-primary" for="btnradio2">A terceros</label>
  </div>
            <div class="row align-items-center">
                <div class="col-3">
                <label for="Debito" class="form-label">Seleccione Cuenta de Debito:</label>
                </div>
                <div class="col-6">
                <select class="form-select" id="cuotas">
                    <option >Cuenta 1 + Saldo</option>
                    <option >Cuenta 2 + Saldo</option>
                    <option >Cuenta 3 + Saldo</option>
                </select>
                 </div>
            </div>
            <div class="row align-items-center">
                <div class="col-3">
                <label for="Acreditacion" class="form-label">Seleccionar Cuenta de Acreditacion:</label>
                </div>
                <div class="col-6">
                <select class="form-select" id="cuentas">
                    <option value="cuenta">
                        <strong>Desplegable con Cuentas Propias o TextBox para CBU</strong><br>
        
                    </option>
                </select>
            </div>
            </div>
             <div class="row align-items-center">
                <div class="col-3">
                <label for="Acreditacion" class="form-label">Seleccionar Cuenta de Acreditacion:</label>
                </div>
                <div class="col-6">
                 <input type="number" class="form-control" id="IdCBU" value="sa">
            </div>
            </div>
            <div class="row align-items-center">
                <div class="col-3">
                <label for="Acreditacion" class="form-label">Importe a transferir:</label>
                </div>
                <div class="col-6">
                 <input type="number" class="form-control" id="ImporteTransferencia" >
            </div>
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
                    <h5 class="modal-title">Confirmar Transferencia</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Realizar la transferencia?
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
<% }else {
	response.sendRedirect("../index.jsp");
}%>