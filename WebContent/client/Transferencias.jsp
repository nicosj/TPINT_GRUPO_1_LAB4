<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("client") != null) {%>

<jsp:include page="./header.jsp" />
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




<jsp:include page="./footer.jsp" />
<% }else {
	response.sendRedirect("../index.jsp");
}%>