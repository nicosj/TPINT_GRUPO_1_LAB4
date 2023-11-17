<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("client") != null) {%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<title>Informacion personal</title>
</head>
<body>
<% Cliente client = (Cliente)request.getAttribute("clientInformation"); %>
<div class="container">
        <h1 class="mt-5">Editar Usuario</h1>
        <form>
            <div class="row mb-3">
                <div class="col">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" placeholder="Nombre" disabled value="<%= client.getNombre() %>">
                </div>
                <div class="col">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" class="form-control" id="apellido" placeholder="Apellido" value="<%= client.getApellido() %>" disabled>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="sexo" class="form-label">Sexo</label>
                    <input type="text" class="form-control" id="sexo" placeholder="Sexo" value="<%= client.getSexo() %>" disabled>
                </div>
                <div class="col">
                    <label for="nacionalidad" class="form-label">Nacionalidad</label>
                    <input type="text" class="form-control" id="nacionalidad" placeholder="Nacionalidad" value="<%= client.getNacionalidad() %>" disabled>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Email" value="<%= client.getCorreo()%>" disabled>
                </div>
                <div class="col">
                    <label for="telefonos" class="form-label">Teléfonos</label>
                    <input type="text" class="form-control" id="telefonos" placeholder="Teléfonos" value="<%= client.getTelefono() %>" disabled>
                </div>
            </div>
            <!-- Campos agregados -->
            <div class="row mb-3">
                <div class="col">
                    <label for="dni" class="form-label">DNI</label>
                    <input type="text" class="form-control" id="dni" placeholder="DNI" value="<%= client.getDNI() %>" disabled>
                </div>
                <div class="col">
                    <label for="cuil" class="form-label">CUIL</label>
                    <input type="text" class="form-control" id="cuil" placeholder="CUIL" value="<%= client.getCUIL() %>" disabled>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                    <input type="date" class="form-control" id="fechaNacimiento" value="<%= client.getFechaNacimiento() %>" disabled>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" placeholder="Dirección" value="<%= client.getDireccion() %>" disabled>
                </div>
                <div class="col">
                    <label for="localidad" class="form-label">Localidad</label>
                    <input type="text" class="form-control" id="localidad" placeholder="Localidad" value="<%= client.getLocalidad() %>" disabled>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="provincia" class="form-label">Provincia</label>
                    <input type="text" class="form-control" id="provincia" placeholder="Provincia" value="<%= client.getProvincia() %>" disabled>
                </div>
            </div>
            <!-- Fin de campos agregados -->
            <a href="index.jsp" class="btn btn-primary">Pagina de inicio</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoJtKh7z7lGz7fuP4F8nfdFvAOA6Gg/z6Y5J6XqqyGXYM2ntX" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8FqFjcJ6pajs/rfdfs3SO+kD4Ck5BdPtF+to8xMp9MvcJ4/Nd45mOZf48UtVbFfhL22P4a9n+88sv/D+xu" crossorigin="anonymous"></script>
</body>
</html>
<% }else {
	response.sendRedirect("../index.jsp");
}%>
