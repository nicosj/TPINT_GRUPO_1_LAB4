<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp"/>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="container-fluid">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>ABML <b>Cuentas Clientes</b></h2>
                    </div>

                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>idCliente</th>
                    <th>FechaCreacion</th>
                    <th>TipoCuenta</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>numero_Cuenta</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <%
                    ArrayList<Cuenta> cuentas = null;
                    if(request.getAttribute("cuentas")!=null)
                    {
                        cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
                    }

                %>
                <tbody>
                <%
                    if(cuentas!=null)
                    for(Cuenta c: cuentas)
                    {%>
                <tr>
				<form method="post" action="EditCuentaServlet">
					<td><%=c.getIdCliente()%></td>
                    <td><%=c.getFecha_Creacion()%></td> 
                    <td><%=c.getTipo_Cuenta()%></td> 
                    <td><%=c.getCBU()%></td> 
                    <td><%=c.getSaldo()%></td> 
                    <td><%=c.getNumero_Cuenta()%></td> 
                    <td><%=c.getEstado()%></td> 

                    <td>
                    <form action="EditCuentaServlet" method="post">

                        <a href="#editCuentaModal" class="edit" data-toggle="modal"
   onclick="populateEditModal(
       '<%=c.getIdCliente()%>',
       '<%=c.getFecha_Creacion()%>',
       '<%=c.getTipo_Cuenta()%>',
       '<%=c.getCBU()%>',
       '<%=c.getSaldo()%>',
       '<%=c.getNumero_Cuenta()%>',
       '<%=c.getEstado()%>'
   )">
   <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
</a>
                        </form>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal" onclick="deleteModal(
						       '<%=c.getNumero_Cuenta()%>',
						   )"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>                       
                       </a>
                    </td>
                    </form>
                </tr>
                <%}%>

				</tbody>
			</table>
            
            <div class="clearfix">
                <div class="hint-text">Mostrar <b>1</b> de <b>25</b> clientes</div>
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#">Anterior</a></li>
                    <li class="page-item"><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item active"><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link">Siguiente</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- Edit Modal HTML -->
<div id="editCuentaModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="editCuentaForm" method="post" action="EditCuentaServlet" onsubmit="return validateForm()">
 
                <div class="modal-header">
                    <h4 class="modal-title">Editar Cuentas</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="idCliente">idCliente</label>
                        <input type="text" class="form-control" name="idCliente" id="idCliente" placeholder="idCliente">
                    </div>
                    <div class="form-group">
                        <label for="FechaCreacion">Fecha</label>
                        <input type="text" class="form-control" name="FechaCreacion" id="FechaCreacion" placeholder="FechaCreacion">
                    </div>
                    <div class="form-group">
                        <label for="TipoCuenta">TipoCuenta</label>
                        <input type="text" class="form-control" name="TipoCuenta" id="TipoCuenta" placeholder="TipoCuenta">
                    </div>
                    <div class="form-group">
                        <label for="CBU">CBU</label>
                        <input type="text" class="form-control" name="CBU" id="CBU" placeholder="CBU">
                    </div>
                    <div class="form-group">
                        <label for="Saldo">Saldo</label>
                        <input type="text" class="form-control" name="Saldo" id="Saldo" placeholder="Saldo">
                    </div>
                    <div class="form-group">
                        <label for="numero_Cuenta">numero_Cuenta</label>
                        <input type="text" class="form-control" name="numero_Cuenta" id="numero_Cuenta" placeholder="numero_Cuenta">
                    </div>
                    <div class="form-group">
                        <label for="Estado">Estado</label>
                        <input type="text" class="form-control" name="Estado" id="Estado" placeholder="Estado">
                    </div>
                    
                    </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="btnEdit" class="btn btn-info" value="Guardar">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="BajaCuentaServlet">
            
                <div class="modal-header">
                    <h4 class="modal-title">Dar de baja Cuenta</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>¿Está seguro de dar de baja esta cuenta?</p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                    <input type="submit" class="btn btn-danger" name="btnBajaCuenta" value="Borrar">
                    <input type="hidden" name="numCuenta" id="deleteNumCuenta">
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="./footer.jsp"/>

<script>
function populateEditModal(idCliente, FechaCreacion, TipoCuenta, CBU, Saldo, numero_Cuenta, Estado) {
    // Rellena los campos con los valores de la fila elegida
    document.getElementById("idCliente").value = idCliente;
    document.getElementById("FechaCreacion").value = FechaCreacion;
    document.getElementById("TipoCuenta").value = TipoCuenta;
    document.getElementById("CBU").value = CBU;
    document.getElementById("Saldo").value = Saldo;
    document.getElementById("numero_Cuenta").value = numero_Cuenta;
    document.getElementById("Estado").value = Estado;

    // Updatea el event listener para chequear los campos vacios antes del submit
    var editForm = document.getElementById("editCuentaForm");
    editForm.addEventListener("submit", function (event) {
        // Chequea campos vacios
        if (!validateForm()) {
            event.preventDefault(); // evita la carga
            alert("Por favor, complete todos los campos");
        }
    });


    $('#editCuentaModal').modal('show');
}
function validateForm() {
    var fields = ["idCliente", "FechaCreacion", "TipoCuenta", "CBU", "Saldo", "numero_Cuenta", "Estado"];
    for (var i = 0; i < fields.length; i++) {
        var value = document.getElementById(fields[i]).value;
        if (value.trim() === "") {
            return false; // Algun campo vacio
        }
    }
    return true; //Campos llenos
}
function deleteModal(numero_Cuenta) {
    document.getElementById("deleteNumCuenta").value =numero_Cuenta;
}

</script>

<% }else {
	response.sendRedirect("../index.jsp");
}%>

