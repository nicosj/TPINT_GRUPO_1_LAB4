<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dominio.Cliente" %>
    <%@page import="java.util.ArrayList" %>


<jsp:include page="./header.jsp" />


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("admin") != null) {%>
<html>

<head>
<style>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@1&family=Dawning+of+a+New+Day&family=Kanit&family=Nunito:ital,wght@1,200&family=Quicksand&family=Roboto+Slab:wght@500&family=Shadows+Into+Light&display=swap');
</style>

<style>
     body {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 10px;
        }

        .divInicial {
            margin-top: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .divInicial h1 {
         	font-family: 'DM Serif Display', serif;
            font-size: 90px;
            font-weight: bold;
            color: cornflowerblue;

        }

        .divInicial h3{
        	font-family: 'Nunito', sans-serif;
            font-size: 45px;
            color: black;
        }
		.divInicial p{
			font-family: 'Nunito', sans-serif;
			margin-top: 10px;
			color: black;
			font-size: 25px;
		}
		.divInicial select{
			margin-bottom: 20px;
		}
        .input-group {
            margin: 15px;
            display: flex;
            align-items: center;
        }

        .input-group label {
        font-family: 'Roboto Slab', serif;
            padding-left: 10px;
            font-size: 20px; 
            color: black;
        }

        .form-check-input {
            height: 50px;
        }

        .input-group + .input-group {
            margin-left: 20px; 
        }

        .div-tabla-reporte {
            margin: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .divControladores{
        	display: inline-block;
        	justify-content: space-between;
        	padding: 10px;
        }
        .divControladores a{
        	font-family: 'Roboto Slab', serif;
        	text-align: center;
            text-decoration: none;
            padding-left: 200px;
            font-size: 20px; 
        	color: cornflowerblue;
            font-weight: 500; 
        }
        .div-final{
        	display:flex;
        }
        .div-final h5{
        	font-family: 'Roboto Slab', serif;
        	text-align: center;
       		color: cornflowerblue;
        	padding-left: 300px;
            
        }
        
		.div-final a{
		    padding-left: 500px;
            font-size: 16px; 
       		color: cornflowerblue;
            text-align: center;
            font-family: 'Roboto Slab', serif;
            
		}
		 select, label, .btn, table {
		    font-family: 'Roboto Slab', serif;
		    font-size: 20px;
		}
	.divInicial select,
	.divControladores {
	    font-size: 18px; 
	}		

</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clientes por provincia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="../assets/css/style.css" />
</head>
<body>
	<form method="get" action="ReporteClienteXProvinciaServlet">
	<div class="divInicial">
		<h1>SECCION DE REPORTES</h1>
		<h3>Provincia con mayor cantidad de clientes</h3>
		<p>Seleccione una provincia</p>
		<select name="selectProvincias" class="form-select" aria-label="Provincias">
			<option value="BUENOS AIRES">Buenos Aires</option>
			<option value="CATAMARCA">Catamarca</option>
			<option value="CHACO">Chaco</option>
			<option value="CHUBUT">Chubut</option>
			<option value="CORDOBA">Cordoba</option>
			<option value="CORRIENTES">Corrientes</option>
			<option value="ENTRE RIOS">Entre Rios</option>
			<option value="FORMOSA">Formosa</option>
			<option value="JUJUY">Jujuy</option>
			<option value="LA PAMPA">La Pampa</option>
			<option value="LA RIOJA">La Rioja</option>
			<option value="MENDOZA">Mendoza</option>
			<option value="MISIONES">Misiones</option>
			<option value="NEQUEN">Neuquen</option>
			<option value="RIO NEGRO">Rio Negro</option>
			<option value="SALTA">Salta</option>
			<option value="SAN JUAN">San Juan</option>
			<option value="SAN LUIS">San Luis</option>
			<option value="SANTA CRUZ">Santa Cruz</option>
			<option value="SANTA FE">Santa Fe</option>
			<option value="SANTIAGO DEL ESTERO">Santiago Del Estero</option>
			<option value="TIERRA DEL FUEGO">Tierra Del Fuego</option>
			<option value="TUCUMAN">Tucuman</option>
		</select>
		
		<p>Seleccione el género que desea contabilizar</p>
            <div class="input-group">
                <input type="radio" name="radioGenero"  value="M">
                <label class="form-check-label">Masculino</label>
            </div>
            <div class="input-group">
                <input type="radio" name="radioGenero"   value="F">
                <label class="form-check-label">Femenino</label>
            </div>
            <div class="input-group">
                <input type="radio"  name="radioGenero" value="ALL">
                <label class="form-check-label">Ver todos (indiferente al género)</label>
            </div>
            

	
        <div class="divControladores">
            <input type="submit" name="btnAceptar" value="Aceptar"  class="btn btn-outline-info" style="padding: 10px 40px;">
            <a href="ReporteClienteXProvincia.jsp">Limpiar pantalla</a>
        </div>
     </div>		
		
		  <%
        ArrayList<Cliente> clientes = null;
        if (request.getAttribute("listadoClientes") != null) {
            clientes = (ArrayList<Cliente>) request.getAttribute("listadoClientes");
        %>
   <div class="div-tabla-reporte" id="contenidoTabla">
        <table class="table table-primary table-striped">

            <thead>
                <tr>
                    <th>N Cliente</th>
                    <th>DNI</th>
					<th>CUIL</th>    
    				<th>Nombre</th>           
        			<th>Apellido</th>         
            		<th>Sexo</th>             
            		<th>Nacionalidad</th>     
            		<th>Fecha de nacimiento</th>  
            		<th>Localidad</th>            
            		<th>Direccion</th>        
            		<th>Correo</th>           
            		<th>Telefono</th>         
                </tr>
            </thead>
            <tbody>
            <%
                for (Cliente cliente : clientes) {
            %>
                <tr>     
                    <td><%=cliente.getIdCLiente()%></td>
                    <td><%=cliente.getDNI()%></td>     
                    <td><%=cliente.getCUIL() %></td>               
                    <td><%=cliente.getNombre()%></td>
                    <td><%=cliente.getApellido()%> </td>  
                    <td><%=cliente.getSexo()%> </td>
                    <td><%=cliente.getNacionalidad()%> </td>
                    <td><%=cliente.getFechaNacimiento()%> </td>
                    <td><%=cliente.getLocalidad()%> </td>
                    <td><%=cliente.getDireccion()%> </td>
                    <td><%=cliente.getCorreo()%> </td>
                    <td><%=cliente.getTelefono() %></td>                                                                                                                                                    
                </tr>
            <%
                }
            }
            %>
            </tbody>
        </table>
	        </div>
	        <div class="div-final">
		        <% int cantidadClientes = 0;
		        Object cantidadClientesObj = request.getAttribute("cantClientes");
		
		        if (cantidadClientesObj != null) {
		            cantidadClientes = (int) cantidadClientesObj;
		        }
		        if(cantidadClientes>0){ %>
		        	<h5>Cantidad de clientes total: <%=cantidadClientes%> </h5>
		        	<a href="index.jsp">Volver atrás</a>
		       <%} else if(cantidadClientes==0 &&  clientes!=null){%>
		       		<h5>No hay clientes registrados que cumplan con los criterios.</h5>
		       <%} %>
		  </div> 
	</form>

</body>
</html>

<% }else {
	response.sendRedirect("../index.jsp");
}%>
		<jsp:include page="./footer.jsp" />