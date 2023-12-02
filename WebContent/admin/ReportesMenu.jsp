<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% if(session.getAttribute("admin") != null) {%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu de reportes</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@1&family=Dawning+of+a+New+Day&family=Kanit&family=Nunito:ital,wght@1,200&family=Quicksand&family=Roboto+Slab:wght@500&family=Shadows+Into+Light&display=swap');


	body{
	  	margin-top: 150px;
	}
	.titulo{
        padding:40px;
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }
    
  .titulo h1 {
        font-family: 'DM Serif Display', serif;
        font-size: 80px;
        font-weight: bold;
        color: blue;
    }
  .redireccion {
    display: flex;
    align-items: center;
    justify-content: center; 
    margin: 70px; 
    padding: 30px; 
    background-color: cornflowerblue;
    border-radius: 10px; /
    text-align: center;
    text-decoration: none; 
  }
	.redireccion a{
	    color: white; 
	    text-decoration: none;	
	    font-family: 'Roboto', sans-serif;
    	font-size: 24px;
	}
  .redireccion:hover {
    background-color: blue;
  }
  .icono{
    	display: flex;
    	align-items:center;
    	justify-content:center;
    	padding-left:20px;
    }
   .icono img{
   		max-width: 300px;
   		margin-bottom: 30px;	
   } 
</style>

</head>
<body>
	<%
	   String urlImagen = "https://www.tac.vic.gov.au/__data/assets/image/0020/408431/Clinical-notesand-treatment-reports.png";
	%>
	<div class="titulo">	
		<h1>SECCION DE REPORTES</h1>
	</div>
	<div class="icono">
   		<img src="<%= urlImagen %>" alt="Descripción de la imagen">
    </div>

  <div class="redireccion">

    <a href="ReporteClienteXProvincia.jsp">I. Ver clientes discriminados por provincia y genero</a>
  </div>

  <div class="redireccion">
    <a href="ReportePrestamosPendientes.jsp">II. Ver todos los prestamos pendientes de pago (contabilizados)</a>
  </div>

  <div class="redireccion">
    <a href="HistorialMovimientosServlet">III. Movimientos de cada cliente</a>
  </div>
</body>
</html>

<% }else {
	response.sendRedirect("../index.jsp");
}%>