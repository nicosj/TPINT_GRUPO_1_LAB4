<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="dominio.Cliente" %>
<%@page import="dominio.Prestamo" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp" />
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
        padding:60px;
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }

    .divInicial h1 {
        font-family: 'DM Serif Display', serif;
        font-size: 70px;
        font-weight: bold;
        color: cornflowerblue;
    }

    .divInicial h3{
    	margin-top: 20px;
        font-family: 'Roboto', sans-serif;
        font-size: 30px;
        color: cornflowerblue;
    }
    .divMonto{
        display: flex;
        align-items: center;
        text-align: center;
        max-width: 500px;
        margin-top:50px;
        margin-bottom:30px;
    }

    .div-tabla-reporte {
        margin: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        padding: 15px;
    }
    .div-tabla-reporte table{
        font-family: 'Roboto Slab', serif;
        font-size: 18px;
    }

    .divA {
        display: flex;
        align-items: center;
        justify-content: center; 
        text-align: center;
        padding: 10px;
        margin-top: 20px;
    }

    .divA a {
        font-family: 'Roboto Slab', serif;
        text-align: center;
        text-decoration: none;
        font-size: 20px;
        color: cornflowerblue;
        font-weight: 500;
        margin-left: 10px;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporte prestamos pendientes</title>
<link rel="stylesheet" href="../assets/css/style.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
    <form method="get" action="ReportePrestamosPendientesServlet" onsubmit="return validarFormulario()">
        <div class="divInicial">
            <h1>Prestamos pendientes</h1>
            <h3>Ingrese un valor. Luego pulse el botón. Debajo podrá visualizar un listado con los prestamos iguales o mayores al monto ingresado y el cliente que lo realizó.</h3>
            <div class="divMonto">
                <span class="input-group-text">$</span>
                <input type="text" class="form-control"  name="txtNumber" aria-label="Amount (to the nearest dollar)">
            </div>
            <input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-outline-info" style="padding-right:50px; padding-left:50px; font-family: 'Roboto Slab', serif; font-size: 18px;">      
        </div>  

        <div class="divA">
            <a href="ReportePrestamosPendientes.jsp">Limpiar pantalla</a>
        </div>

        <% ArrayList<Prestamo> prestamos = null;
        if (request.getAttribute("listadoPrestamos") != null) {
            prestamos = (ArrayList<Prestamo>) request.getAttribute("listadoPrestamos");
            %>
            <div class="div-tabla-reporte" id="contenidoTabla">
                <table class="table table-primary table-striped-columns">
                    <thead>
                        <tr>
                            <th>N Prestamo</th>
                            <th>Nº cuenta que lo adeuda</th>             
                            <th>Fecha del Prestamo</th>         
                            <th>Monto de las cuotas</th>           
                            <th>Monto</th>
                            <th>Cantidad de cuotas</th>    
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        for (Prestamo prestamo : prestamos) {
                            %>
                            <tr>     
                                <td><%=prestamo.getIdPrestamo()%></td>
                                <td><%=prestamo.getNumero_Cuenta()%></td>
                                <td><%=prestamo.getFechaPedido()%></td>
                                <td><%=prestamo.getImporteCuota()%></td>
                                <td><%=prestamo.getTotalImporte()%></td>   
                                <td><%=prestamo.getCuotas()%></td> 
                                <%
                            }
                        }
                        %>
                    </div>
                </tbody>
            </table>
        </form>

        <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title  fs-5" id="errorModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p style="font-size: 20px;">Ingresa un valor válido.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script>
            function mostrarErrorModal() {
                $('#errorModal').modal('show');
            }
        </script>

        <script>
            function validarFormulario() {
                var valor = document.getElementsByName("txtNumber")[0].value;
                if (isNaN(valor) || valor.trim() === "") {
                    mostrarErrorModal();
                    return false; 
                }

                return true; 
            }
        </script>

    </body>
    </html>
<jsp:include page="./footer.jsp" />
<% }else {
	response.sendRedirect("../index.jsp");
}%>