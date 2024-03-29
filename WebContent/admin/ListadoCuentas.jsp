<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp"/>
<%@page import="dominio.Cuenta"%>
<%@page import="dominio.Cliente"%>
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
            <table id="tablaConPaginadorYFiltro"  class="display">
                <thead>
                <tr>
                    <th>Nro. Cliente</th>
                    <th>Nombre Apellido</th>
                    <th>DNI</th>
                    <th>Fecha de Creacion</th>
                    <th>Tipo de Cuenta</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Nro. de Cuenta</th>
                    <th>Estado</th>
                    <th>Acciones </th>
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
					<td><%=c.getCliente().getIdCLiente()%></td>
					<td><%=c.getCliente().getNombreCompleto()%></td>
					<td><%=c.getCliente().getDNI()%></td>
                    <td><%=c.getFecha_Creacion()%></td> 
                    <td><%=c.getTipo_Cuenta()%></td> 
                    <td><%=c.getCBU()%></td> 
                    <td><%=c.getSaldo()%></td> 
                    <td><%=c.getNumero_Cuenta()%></td>
                    <%if(c.getEstado()){ %> 
                    <td>Activo</td>
                    <%}else{ %>
                    <td>Inactivo</td>
                    <%} %> 

                    <td>
                    <form action="EditCuentaServlet" method="post">

                        <a  href="#editCuentaModal" class="edit" data-toggle="modal"
   onclick="populateEditModal(
       '<%=c.getCliente().getIdCLiente()%>',
       '<%=c.getFecha_Creacion()%>',
       '<%=c.getTipo_Cuenta()%>',
       '<%=c.getCBU()%>',
       '<%=c.getSaldo()%>',
       '<%=c.getNumero_Cuenta()%>',
       '<%=c.getEstado()%>' === 'true' ? 'Activo' : 'Inactivo')">
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
                        <label for="idCliente">Cliente Nro.</label>
                        <input type="text" class="form-control" name="idCliente" id="idCliente" placeholder="idCliente">
                    </div>
                    <div class="form-group">
                        <label for="FechaCreacion">Fecha Creacion</label>
                        <input type="date" class="form-control" name="FechaCreacion" id="FechaCreacion" max="<%= java.time.LocalDate.now() %>" placeholder="FechaCreacion">
                    </div>
                    <div class="form-group">
				    <label for="TipoCuenta">Tipo de Cuenta</label>
 					   <select class="form-control" name="TipoCuenta" id="TipoCuenta">
					        <option value="CC">CC</option>
 					       <option value="CA">CA</option>
					    </select>
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
                        <label for="numero_Cuenta">Cuenta Nro.</label>
                        <input type="text" class="form-control" name="numero_Cuenta" id="numero_Cuenta" placeholder="numero_Cuenta" readonly>
                    </div>
                    <div class="form-group">
				    <label for="Estado">Estado</label>
 					   <select class="form-control" name="Estado" id="Estado">
					        <option value="Activo">Activo</option>
 					       <option value="Inactivo">Inactivo</option>
					    </select>
					</div>
                    <div class="error-message" style="color: red;"></div>
                    </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="btnEdit" class="btn btn-info" id="editar" value="Guardar">
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
                    <p>�Est� seguro de dar de baja esta cuenta?</p>
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

    var cuentaCountError = <%= request.getAttribute("cuentaCountError") %>;
    if (cuentaCountError) {
        
        $('#editCuentaModal .error-message').html('Error:  El Cliente ya posee 3 cuentas asignadas.');
    } else {
        // limpa los mensajes
        $('#editCuentaModal .error-message').html('');
    }
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


$(document).ready(function () {
	let table = new DataTable('#tablaConPaginadorYFiltro', {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
	    },
	});    
    $('#editar').on("click", function (e) {
        var self = $(this);
        e.preventDefault();
        Swal.fire({
            title: '�Estas seguro?',
            text: "Usted esta por modificar una cuenta!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
	cancelButtonColor: '#d33',
            confirmButtonText: 'Confirmar',


        }).then((result) => {
            if (result.isConfirmed) {
                self.off("click").click();
            }
        })
        })});


</script>

<%
    // se fija si hay algun error en el atributo
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
    <script type="text/javascript">
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: '<%= errorMessage %>',
            showConfirmButton: true
        });
    </script>
<%
    }
    // lo limpia para que no se muestre muchas vecess
    request.removeAttribute("errorMessage");
%>

<% }else {
	response.sendRedirect("../index.jsp");
}%>

