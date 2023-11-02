<jsp:include page="./header.jsp"/>
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
                        <h2>ABML <b>Clientes</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addCliente" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Nuevo Cliente</span></a>

                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>DNI</th>
                    <th>CUIL</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Sexo</th>
                    <th>F. Nac.</th>
                    <th>Direccion</th>
                    <th>Localidad</th>
                    <th>Provincia</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Usuario</th>
                    <th>Accion</th>
                </tr>
                </thead>
                <%
                    ArrayList<Cliente> clientes = null;
                    if(request.getAttribute("clientes")!=null)
                    {
                        clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
                    }

                %>
                <tbody>
                <%
                    if(clientes!=null)
                    for(Cliente c: clientes)
                    {%>
                <tr>
				<form method="post" action="altaClienteServlet">
                    <td><%=c.getDNI()%></td> <!-- DNI -->
                    <td><%=c.getCUIL()%></td> <!-- CUIL -->
                    <td><%=c.getNombre()%></td> <!-- Nombre -->
                    <td><%=c.getApellido()%></td> <!-- Apellido -->
                    <td><%=c.getSexo()%></td> <!-- Sexo -->
                    <td><%=c.getFechaNacimiento()%></td> <!-- F. Nac. -->
                    <td><%=c.getDireccion()%></td> <!-- Direccion -->
                    <td><%=c.getLocalidad()%></td> <!-- Localidad -->
                    <td><%=c.getProvincia()%></td> <!-- Provincia -->
                    <td><%=c.getCorreo()%></td> <!-- Email -->
                    <td><%=c.getTelefono()%></td> <!-- Telefono -->
                    <td><%=c.getNombre()%></td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                    </form>
                </tr>
                <%}%>
          <%--      <tr>

                    <td>34567890</td> <!-- DNI -->
                    <td>30-34567890-1</td> <!-- CUIL -->
                    <td>Carlos</td> <!-- Nombre -->
                    <td>Rodriguez</td> <!-- Apellido -->
                    <td>Masculino</td> <!-- Sexo -->
                    <td>10/12/1978</td> <!-- F. Nac. -->
                    <td>Calle 67 Este</td> <!-- Direccion -->
                    <td>Ciudad C</td> <!-- Localidad -->
                    <td>Provincia Z</td> <!-- Provincia -->
                    <td>carlos@example.com</td> <!-- Email -->
                    <td>555-123-7890</td> <!-- Telefono -->
                    <td>carlosrodriguez</td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
                <tr>

                    <td>45678901</td> <!-- DNI -->
                    <td>35-45678901-2</td> <!-- CUIL -->
                    <td>Laura</td> <!-- Nombre -->
                    <td>Gomez</td> <!-- Apellido -->
                    <td>Femenino</td> <!-- Sexo -->
                    <td>25/09/1993</td> <!-- F. Nac. -->
                    <td>Calle 123 Oeste</td> <!-- Direccion -->
                    <td>Ciudad D</td> <!-- Localidad -->
                    <td>Provincia W</td> <!-- Provincia -->
                    <td>laura@example.com</td> <!-- Email -->
                    <td>123-789-4560</td> <!-- Telefono -->
                    <td>lauragomez</td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
                <tr>

                    <td>56789012</td> <!-- DNI -->
                    <td>40-56789012-3</td> <!-- CUIL -->
                    <td>Pablo</td> <!-- Nombre -->
                    <td>Fernandez</td> <!-- Apellido -->
                    <td>Masculino</td> <!-- Sexo -->
                    <td>03/07/1980</td> <!-- F. Nac. -->
                    <td>Calle 98 Norte</td> <!-- Direccion -->
                    <td>Ciudad E</td> <!-- Localidad -->
                    <td>Provincia V</td> <!-- Provincia -->
                    <td>pablo@example.com</td> <!-- Email -->
                    <td>789-456-1230</td> <!-- Telefono -->
                    <td>pablofernandez</td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
                <tr>
                    <td>67890123</td> <!-- DNI -->
                    <td>45-67890123-4</td> <!-- CUIL -->
                    <td>Ana</td> <!-- Nombre -->
                    <td>Martinez</td> <!-- Apellido -->
                    <td>Femenino</td> <!-- Sexo -->
                    <td>12/11/1987</td> <!-- F. Nac. -->
                    <td>Calle 54 Sur</td> <!-- Direccion -->
                    <td>Ciudad F</td> <!-- Localidad -->
                    <td>Provincia U</td> <!-- Provincia -->
                    <td>ana@example.com</td> <!-- Email -->
                    <td>987-654-3210</td> <!-- Telefono -->
                    <td>anamartinez</td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>--%>
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Mostrar <b>5</b> de <b>25</b> clientes</div>
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
<div id="addCliente" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="altaClienteServlet">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dni">DNI</label>
                        <input type="text" class="form-control" id="dnii" name="dni" placeholder="DNI">
                    </div>
                    <div class="form-group">
                        <label for="cuil">CUIL</label>
                        <input type="text" class="form-control" id="cuili" name="cuil" placeholder="CUIL">
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombrei" name="nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" id="apellidoi" name="apellido" placeholder="Apellido">
                    </div>
                    <div class="form-group">
                        <label for="sexo">Sexo</label>
                        <select class="form-control" id="sexoi" name="sexo">
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                            <option value="B">No BInario</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nacionalidad">Nacionalidad</label>
                        <input type="text" class="form-control" id="nacionalidadi" name="nacionalidad" placeholder="Nacionalidad">
                    </div>
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimientoi" name="fechaNacimiento">
                    </div>
                    <div class="form-group">
                        <label for="direccion">Dirección</label>
                        <input type="text" class="form-control" id="direccioni" name="direccion" placeholder="Dirección">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="localidad">Localidad</label>
                            <input type="text" class="form-control" id="localidadi" name="localidad"  placeholder="Localidad">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="provincia">Provincia</label>
                            <input type="text" class="form-control" id="provinciai" name="provincia" placeholder="Provincia">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Correo Electrónico</label>
                        <input type="email" class="form-control" id="emaili" name="correo" placeholder="Correo Electrónico">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="telefono">Teléfono</label>
                            <input type="text" class="form-control" id="telefonoi" name="telefono" placeholder="Teléfono">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" id="usuarioi" name="usuario" placeholder="Usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contrasena">Contrasena</label>
                        <input type="password" class="form-control" id="contrasenai" name="contrasena" placeholder="Contrasena">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" id="altacliente" name="alta"  value="Alta CLiente">
                </div>
                  <%
                  if(request.getAttribute("filas")!=null){
                  boolean loco= (boolean)request.getAttribute("filas");
                   if(loco){
               	 %>
                 <script type="text/JavaScript">
                	Swal.fire({
                	  
                	  icon: 'success',
                	  title: 'Exito, Se inserto cliente',
                	  showConfirmButton: true,
                	  timer: 2500
                	}).then((result) => {
                    location.href="altaClienteServlet";
                	})
                </script>
                <%} else if(!loco){ %>
                <script type="text/JavaScript">
                	Swal.fire({
                	  
                	  icon: 'error',
                	  title: 'Datos duplicados o invalidos.',
                	  showConfirmButton: true,
                	  timer: 2500
                	}).then((result) => {
                    location.href="altaClienteServlet";
                	})
                </script>
                <%
                   }
                   }%>
              
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Editar Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dni">DNI</label>
                        <input type="text" class="form-control" id="dni" placeholder="DNI">
                    </div>
                    <div class="form-group">
                        <label for="cuil">CUIL</label>
                        <input type="text" class="form-control" id="cuil" placeholder="CUIL">
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" id="apellido" placeholder="Apellido">
                    </div>
                    <div class="form-group">
                        <label for="sexo">Sexo</label>
                        <select class="form-control" id="sexo">
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nacionalidad">Nacionalidad</label>
                        <input type="text" class="form-control" id="nacionalidad" placeholder="Nacionalidad">
                    </div>
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento">
                    </div>
                    <div class="form-group">
                        <label for="direccion">Dirección</label>
                        <input type="text" class="form-control" id="direccion" placeholder="Dirección">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="localidad">Localidad</label>
                            <input type="text" class="form-control" id="localidad" placeholder="Localidad">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="provincia">Provincia</label>
                            <input type="text" class="form-control" id="provincia" placeholder="Provincia">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Correo Electrónico</label>
                        <input type="email" class="form-control" id="email" placeholder="Correo Electrónico">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="telefono">Teléfono</label>
                            <input type="text" class="form-control" id="telefono" placeholder="Teléfono">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" id="usuario" placeholder="Usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contrasena">Contrasena</label>
                        <input type="password" class="form-control" id="contrasena" placeholder="Contrasena">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Guardar">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Dar de baja Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>¿Esta Seguro de bajar a este cliente?</p>
                    <%--<p class="text-warning"><small>Esta accion no se puede completar.</small></p>--%>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Borrar">
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="./footer.jsp"/>
<script>
    $(document).ready(function(){
        $('#altacliente').on("click",function(e) {
            var self = $(this);
            e.preventDefault();
            Swal.fire({
                title: '¿Estas seguro?',
                text: "No podras revertir esta accion!",
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
            
            
        });
        
           
    });
</script>