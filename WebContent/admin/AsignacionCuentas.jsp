<jsp:include page="./header.jsp"/>
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
                    <th>DNI</th>
                    <th>CUIL</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Cuenta 1</th>
                    <th>Cuenta 2</th>
                    <th>Cuenta 3</th>
                </tr>
                </thead>
                <tbody>
                <tr>

                    <td>23456789</td> 
                    <td>25-23456789-0</td> 
                    <td>Maria</td> 
                    <td>Lopez</td> 
                    <td>123456789</td> 
                    <td>987654321</td> 
                    <td>Sin asignar</td>
                   
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
              
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
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Editar Cuentas</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="Cuenta1">Cuenta 1</label>
                        <input type="text" class="form-control" id="Cuenta1" placeholder="Cuenta 1">
                    </div>
                    <div class="form-group">
                        <label for="Cuenta2">Cuenta 2</label>
                        <input type="text" class="form-control" id="Cuenta2" placeholder="Cuenta 2">
                    </div>
                    <div class="form-group">
                        <label for="Cuenta 3">Cuenta 3</label>
                        <input type="text" class="form-control" id="Cuenta3" placeholder="Cuenta 3">
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
                    <h4 class="modal-title">Dar de baja Cuenta</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>¿Esta Seguro de bajar a esta cuenta?</p>
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