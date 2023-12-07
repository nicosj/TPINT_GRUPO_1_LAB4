<% if (session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp"/>
<%@page import="dominio.Cliente" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="dominio.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<div class="container-fluid">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>
                            ABML <b>Clientes</b>
                        </h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addCliente" class="btn btn-success" data-toggle="modal"><i
                                class="material-icons">&#xE147;</i> <span>Nuevo Cliente</span></a>

                    </div>


                </div>
            </div>
            <table  id="tablaConPaginadorYFiltro"  class="display">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>CUIL</th>
                    <th>Nombre</th>
                    <th>Apellido</th>

                    <th>Nacionalidad</th>
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
                    ArrayList<Usuario> usuario = null;
                    if (request.getAttribute("clientes") != null && request.getAttribute("usuarios") != null) {

                        clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
                        usuario = (ArrayList<Usuario>) request.getAttribute("usuarios");
                    }

                %>
                <tbody>
                <%
                    if (clientes != null && usuario != null)
                        for (Cliente c : clientes) {
                            for (Usuario u : usuario) {
                                if (u.getCliente().getIdCLiente() == c.getIdCLiente() && (u.getTipoUsuario() == 2) && c.isEstado()) {%>
                <tr class="clientRow">

                    <td><%=c.getIdCLiente()%>
                    </td>
                    <td><%=c.getDNI()%>
                    </td> <!-- DNI -->
                    <td><%=c.getCUIL()%>
                    </td> <!-- CUIL -->
                    <td><%=c.getNombre()%>
                    </td> <!-- Nombre -->
                    <td><%=c.getApellido()%>
                    </td> <!-- Apellido -->
                    <td><%=c.getSexo()%>
                    </td> <!-- Sexo -->
                    <td><%=c.getNacionalidad()%>
                    </td> <!-- Nacionalidad -->
                    <td><%=c.getFechaNacimiento()%>
                    </td> <!-- F. Nac. -->
                    <td><%=c.getDireccion()%>
                    </td> <!-- Direccion -->
                    <td><%=c.getLocalidad()%>
                    </td> <!-- Localidad -->
                    <td><%=c.getProvincia()%>
                    </td> <!-- Provincia -->
                    <td><%=c.getCorreo()%>
                    </td> <!-- Email -->
                    <td><%=c.getTelefono()%>
                    </td> <!-- Telefono -->
                    <td><%=u.getUsuario()%>
                    </td> <!-- Usuario -->
                    <td>
                        <a href="#editEmployeeModal" id="<%=c.getIdCLiente()%>" class="edit" data-toggle="modal"
                           onclick="parametr(`<%=c.getDNI()%>`,
                                   `<%=c.getCUIL()%>`,
                                   `<%=c.getNombre()%>`,
                                   `<%=c.getApellido()%>`,
                                   `<%=c.getSexo()%>`,
                                   `<%=c.getNacionalidad()%>`,
                                   `<%=c.getFechaNacimiento()%>`,
                                   `<%=c.getDireccion()%>`,
                                   `<%=c.getLocalidad()%>`,
                                   `<%=c.getProvincia()%>`,
                                   `<%=c.getCorreo()%>`,
                                   `<%=c.getTelefono()%>`,
                                   `<%=u.getUsuario()%>`
                                   )"
                        ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" id="<%=c.getIdCLiente()%>"
                           onclick="paramDel(`<%=c.getIdCLiente()%>`,
                                   `<%=c.getNombre()%>`,
                                   `<%=c.getApellido()%>`,
                                   )"
                           class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip"
                                                                 title="Delete">&#xE872;</i></a>

                    </td>

                </tr>
                <%
                                }
                            }
                        }
                %>

                </tbody>
            </table>

        </div>
    </div>
</div>
<!-- ADD Modal HTML -->
<div id="addCliente" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="altaClienteServlet">

                <div class="modal-header">
                    <h4 class="modal-title">Agregar Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dni">DNI</label> <input type="text"
                                                            class="form-control" id="dni" name="dni" placeholder="DNI">
                    </div>
                    <div class="form-group">
                        <label for="cuil">CUIL</label> <input type="text"
                                                              class="form-control" id="cuil" name="cuil"
                                                              placeholder="CUIL">
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre</label> <input type="text"
                                                                  class="form-control" id="nombre" name="nombre"
                                                                  placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label> <input type="text"
                                                                      class="form-control" id="apellido" name="apellido"
                                                                      placeholder="Apellido">
                    </div>
                    <div class="form-group">
                        <label for="sexo">Sexo</label> <select class="form-control"
                                                               id="sexo" name="sexo">
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="B">No BInario</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label for="nacionalidad">Nacionalidad</label> <input type="text"
                                                                              class="form-control" id="nacionalidad"
                                                                              name="nacionalidad"
                                                                              placeholder="Nacionalidad">
                    </div>
                    <div class="form-group">
    <label for="fechaNacimiento">Fecha de Nacimiento</label>
    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" max="<%= java.time.LocalDate.now() %>" required>
</div>

                    <div class="form-group">
                        <label for="direccion">Dirección</label> <input type="text"
                                                                        class="form-control" id="direccion"
                                                                        name="direccion"
                                                                        placeholder="Dirección">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="localidad">Localidad</label> <input type="text"
                                                                            class="form-control" id="localidad"
                                                                            name="localidad"
                                                                            placeholder="Localidad">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="provincia">Provincia</label>
                            <select class="form-control" id="provincia" name="provincia">
                                <option value="Buenos Aires">Buenos Aires</option>
                                <option value="Catamarca">Catamarca</option>
                                <option value="Chaco">Chaco</option>
                                <option value="Chubut">Chubut</option>
                                <option value="Cordoba">Cordoba</option>
                                <option value="Corrientes">Corrientes</option>
                                <option value="Entre Rios">Entre Rios</option>
                                <option value="Formosa">Formosa</option>
                                <option value="Jujuy">Jujuy</option>
                                <option value="La Pampa">La Pampa</option>
                                <option value="La Rioja">La Rioja</option>
                                <option value="Mendoza">Mendoza</option>
                                <option value="Misiones">Misiones</option>
                                <option value="Neuquen">Neuquen</option>
                                <option value="Rio Negro">Rio Negro</option>
                                <option value="Salta">Salta</option>
                                <option value="San Juan">San Juan</option>
                                <option value="San Luis">San Luis</option>
                                <option value="Santa Cruz">Santa Cruz</option>
                                <option value="Santa Fe">Santa Fe</option>
                                <option value="Santiago Del Estero">Santiago Del Estero</option>
                                <option value="Tierra Del Fuego">Tierra Del Fuego</option>
                                <option value="Tucuman">Tucuman</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Correo Electrónico</label> <input type="email"
                                                                             class="form-control" id="email"
                                                                             name="correo"
                                                                             placeholder="Correo Electrónico">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="telefono">Teléfono</label> <input type="text"
                                                                          class="form-control" id="telefono"
                                                                          name="telefono"
                                                                          placeholder="Teléfono">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="usuario">Usuario</label> <input type="text"
                                                                        class="form-control" id="usuario" name="usuario"
                                                                        placeholder="Usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contrasena">Contrasena</label> <input type="password"
                                                                          class="form-control" id="contrasena"
                                                                          name="contrasena"
                                                                          placeholder="Contrasena">
                    </div>

                    <div class="form-group">
                        <label for="contrasena2e">Repetir Contrasena</label> <input type="password"
                                                                                    class="form-control" id="contrasena2e"
                                                                                    name="contrasena2"
                                                                                    placeholder="Asegurese de que las contrasenias coincidan">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal"
                           value="Cancel"> <input type="submit"
                                                  class="btn btn-success" id="altacliente" name="alta"
                                                  value="Alta CLiente">
                </div>
                <%
                    if (request.getAttribute("filas") != null) {
                        boolean loco = (boolean) request.getAttribute("filas");
                        if (loco) {
                %>
                <script type="text/JavaScript">
                    Swal.fire({

                        icon: 'success',
                        <%if(request.getParameter("crud")!=null)
                        {
                            if(request.getParameter("crud") == "mod"){


                            %>
                        title: 'Exito, Se modifico cliente',
                        <%}else{


                        %>
                        title: 'Exito, Se inserto cliente',
                        <%}}%>

                        showConfirmButton: true,
                        timer: 2500,
                        timerProgressBar: true,
                    }).then((result) => {
                        location.href = "altaClienteServlet";
                    })
                </script>
                <%
                } else if (!loco) {
                %>
                <script type="text/JavaScript">
                    Swal.fire({

                        icon: 'error',
                        title: 'Datos duplicados o invalidos.',
                        showConfirmButton: true,
                        timer: 2500,
                        timerProgressBar: true,
                    }).then((result) => {
                        location.href = "altaClienteServlet";
                    })
                </script>
                <%
                        }
                    }
                %>

            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">

            <form method="post" action="ModificarClienteServlet">
                <input type="hidden" name="idCliente" id="idCliente"/>

                <div class="modal-header">
                    <h4 class="modal-title">Editar Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dnie">DNI</label> <input type="text"
                                                             class="form-control" id="dnie" name="dni"
                                                             placeholder="DNI">
                    </div>
                    <div class="form-group">
                        <label for="cuile">CUIL</label> <input type="text"
                                                               class="form-control" id="cuile" name="cuil"
                                                               placeholder="CUIL">
                    </div>
                    <div class="form-group">
                        <label for="nombree">Nombre</label> <input type="text"
                                                                   class="form-control" id="nombree" name="nombre"
                                                                   placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellidoe">Apellido</label> <input type="text"
                                                                       class="form-control" id="apellidoe"
                                                                       name="apellido"
                                                                       placeholder="Apellido">
                    </div>
                    <div class="form-group">
                        <label for="sexoe">Sexo</label> <select class="form-control"
                                                                id="sexoe" name="sexo">
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="B">No BInario</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label for="nacionalidade">Nacionalidad</label> <input type="text"
                                                                               class="form-control" id="nacionalidade"
                                                                               name="nacionalidad"
                                                                               placeholder="Nacionalidad">
                    </div>
                 <div class="form-group">
    <label for="fechaNacimientoe">Fecha de Nacimiento</label>
    <input type="date" class="form-control" id="fechaNacimientoe" name="fechaNacimiento" max="<%= java.time.LocalDate.now() %>" required>
</div>

                    <div class="form-group">
                        <label for="direccione">Dirección</label> <input type="text"
                                                                         class="form-control" id="direccione"
                                                                         name="direccion"
                                                                         placeholder="Dirección">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="localidade">Localidad</label> <input type="text"
                                                                             class="form-control" id="localidade"
                                                                             name="localidad"
                                                                             placeholder="Localidad">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="provinciae">Provincia</label>
                            <select class="form-control" id="provinciae" name="provincia">
                                <option value="Buenos Aires">Buenos Aires</option>
                                <option value="Catamarca">Catamarca</option>
                                <option value="Chaco">Chaco</option>
                                <option value="Chubut">Chubut</option>
                                <option value="Cordoba">Cordoba</option>
                                <option value="Corrientes">Corrientes</option>
                                <option value="Entre Rios">Entre Rios</option>
                                <option value="Formosa">Formosa</option>
                                <option value="Jujuy">Jujuy</option>
                                <option value="La Pampa">La Pampa</option>
                                <option value="La Rioja">La Rioja</option>
                                <option value="Mendoza">Mendoza</option>
                                <option value="Misiones">Misiones</option>
                                <option value="Neuquen">Neuquen</option>
                                <option value="Rio Negro">Rio Negro</option>
                                <option value="Salta">Salta</option>
                                <option value="San Juan">San Juan</option>
                                <option value="San Luis">San Luis</option>
                                <option value="Santa Cruz">Santa Cruz</option>
                                <option value="Santa Fe">Santa Fe</option>
                                <option value="Santiago Del Estero">Santiago Del Estero</option>
                                <option value="Tierra Del Fuego">Tierra Del Fuego</option>
                                <option value="Tucuman">Tucuman</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="emaile">Correo Electrónico</label> <input type="email"
                                                                              class="form-control" id="emaile"
                                                                              name="correo"
                                                                              placeholder="Correo Electrónico">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="telefonoe">Teléfono</label> <input type="text"
                                                                           class="form-control" id="telefonoe"
                                                                           name="telefono"
                                                                           placeholder="Teléfono">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="usuarioe">Usuario</label> <input type="text"
                                                                         class="form-control" id="usuarioe"
                                                                         name="usuario"
                                                                         placeholder="Usuario" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contrasenae">Contrasena</label> <input type="password"
                                                                           class="form-control" id="contrasenae"
                                                                           name="contrasena"
                                                                           placeholder="Contrasena">

                        <div class="form-group">
                            <label for="contrasena2ew">Repetir Contrasena</label> <input type="password"
                                                                                         class="form-control" id="contrasena2ew"
                                                                                         name="contrasena2"
                                                                                         placeholder="Asegurese de que las contrasenias coincidan">
                        </div>




                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal"
                                   value="Cancel"> <input type="submit" name="btnModificar" class="btn btn-info"
                                                          value="Guardar">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        console.log("Script ejecutado");

        document.getElementById("addCliente").addEventListener("submit", function (event) {
            console.log("Form enviado");

            var nuevoUsuario = document.getElementById("usuarioe").value;

            // Verificar si el nuevo nombre de usuario ya existe
            verificarNombreUsuario(nuevoUsuario, function (existe) {
                if (existe) {
                    console.log("El nuevo nombre de usuario ya existe");
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'El nuevo nombre de usuario ya está en uso. Por favor, elige otro.',
                        showConfirmButton: true
                    });

                    event.preventDefault(); // Evita que el formulario se envíe
                } else {
                    // Continuar con el envío del formulario si el nombre de usuario no existe
                    console.log("El nuevo nombre de usuario no existe");
                }
            });
        });

    });

</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        console.log("Script ejecutado");

        document.getElementById("editEmployeeModalForm").addEventListener("submit", function (event) {
            console.log("Form enviado");

            var nuevoUsuario = document.getElementById("usuarioe").value;

            // Verificar si el nuevo nombre de usuario ya existe
            verificarNombreUsuario(nuevoUsuario, function (existe) {
                if (existe) {
                    console.log("El nuevo nombre de usuario ya existe");
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'El nuevo nombre de usuario ya está en uso. Por favor, elige otro.',
                        showConfirmButton: true
                    });

                    event.preventDefault(); // Evita que el formulario se envíe
                } else {
                    // Continuar con el envío del formulario si el nombre de usuario no existe
                    console.log("El nuevo nombre de usuario no existe");
                }
            });
        });

        /*// Función para verificar la existencia del nombre de usuario
        function verificarNombreUsuario(usuario, callback) {

            $.ajax({
                url: "VerificarNombreUsuarioServlet",
                method: "POST",
                data: { usuario: usuario },
                success: function (response) {

                    var existe = response === "true";
                    callback(existe);
                },
                error: function () {
                    console.error("Error al verificar el nombre de usuario");

                    callback(false);
                }
            });
        }*/
    });
</script>

<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="BajaClienteServlet">
                <input type="hidden" name="idCliente" id="idClienteb"/>
                <div class="modal-header">
                    <h4 class="modal-title">Dar de baja Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;
                    </button>
                </div>
                <div class="modal-body">
                    <p>¿Esta Seguro de bajar a este cliente? <span id="datos"></span></p>
                    <%--<p class="text-warning"><small>Esta accion no se puede completar.</small></p>--%>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal"
                           value="Cancel"> <input type="submit"
                                                  class="btn btn-danger" name="btnEliminarCliente" value="Borrar">
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Script para que conicidan las contrasenenias en nuevo cliente -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        console.log("Script ejecutado");

        document.getElementById("addCliente").addEventListener("submit", function (event) {
            console.log("Form enviado");

            var contrasena = document.getElementById("contrasena").value;
            var contrasena2 = document.getElementById("contrasena2e").value;

            if (contrasena !== contrasena2) {
                console.log("Las contrasenias no son iguales");
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Las contraseñas no coinciden. Por favor, inténtalo de nuevo.',
                    showConfirmButton: true
                });

                event.preventDefault(); // Evita que el formulario se envíe
            }
        });
    });
</script><!-- Script para que conicidan las contrasenenias en la funcion de editar -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        console.log("Script ejecutado");

        document.getElementById("editEmployeeModal").addEventListener("submit", function (event) {
            console.log("Form enviado");

            var contrasena = document.getElementById("contrasenae").value;
            var contrasena2 = document.getElementById("contrasena2ew").value;

            if (contrasena !== contrasena2) {
                console.log("Las contrasenias no son iguales");
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Las contraseñas no coinciden. Por favor, inténtalo de nuevo.',
                    showConfirmButton: true
                });

                event.preventDefault(); // Evita que el formulario se envíe
            }
        });
    });
</script>



<jsp:include page="./footer.jsp"/>
<% } else {
    response.sendRedirect("../index.jsp");
}%>

<script>
    $(document).ready(function () {
        $('#altacliente').on("click", function (e) {
            var self = $(this);
            e.preventDefault();
            Swal.fire({
                title: '¿Estas seguro?',
                text: "Usted esta por registrar un nuevo usuario!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Confirmar',


            }).then((result) => {
                if (result.isConfirmed) {
                    self.off("click").click();
                }
            },);


        });

        $('.edit').on("click", function () {
            var self = $(this).attr("id");
            $("#idCliente").val(self);
            //alert(self);


        })

        let table = new DataTable('#tablaConPaginadorYFiltro', {

            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
            },
            responsive: true,
            order: [[0, 'desc']],

        });
    });

    function paramDel(id, nombre, apaellido) {
        $("#idClienteb").val(id);
        $("#datos").empty();
        $("#datos").append("<h2>" + nombre + " " + apaellido + "</h2>");
    }

    function parametr(dni,
                      cuil,
                      nombre,
                      apellido,
                      nacionalidad,
                      sexo,
                      fechaNacimiento,
                      direccion,
                      localidad,
                      provincia,
                      correo,
                      telefono,
                      usuario
    ) {
        $("#dnie").val(dni);
        $("#cuile").val(cuil);
        $("#nombree").val(nombre);
        $("#apellidoe").val(apellido);
        $("#nacionalidade").val(nacionalidad);
        $("#sexoe").val(sexo);
        $("#fechaNacimientoe").val(fechaNacimiento);
        $("#direccione").val(direccion);
        $("#localidade").val(localidad);
        $("#provinciae").val(provincia);
        $("#emaile").val(correo);
        $("#telefonoe").val(telefono);
        $("#usuarioe").val(usuario);

    }
</script>

<%
    // Check if there is an error message attribute
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
    // Clear the error message attribute to avoid displaying it multiple times
    request.removeAttribute("errorMessage");
%>

<%--<script>
function filtrarTabla() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("buscador");
    filter = input.value.toUpperCase();
    table = document.querySelector(".table-striped table-hover");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        for (var j = 0; j < td.length; j++) {
            if (td[j]) {
                txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break; p
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}
</script>--%>

<!-- Modal por si existe ya un nombre de usuario -->
<div id="modalUsuarioExistente" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <p id="errorMessage"></p>
    </div>
</div>

<script>
    function openModal(message) {
        document.getElementById("errorMessage").innerHTML = message;
        document.getElementById("modalUsuarioExistente").style.display = "block";
    }

    function closeModal() {
        document.getElementById("modalUsuarioExistente").style.display = "none";
    }
</script>
