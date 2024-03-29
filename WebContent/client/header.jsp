<!DOCTYPE html>
<%@page import="dominio.Usuario"%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Banco LAB - Sistema V1.0</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link href='//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../assets/css/style.css" />

</head>
<body>
<nav class="navbar navbar-expand-lg  navbar-global fixed-top">
  <div class="container">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
      <span class="glyphicon glyphicon-menu-hamburger"></span>
    </button>
    <a class="navbar-brand" href="#">BANCO LAB </a>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav navbar-user  ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="#"><span class="glyphicon glyphicon-user"></span><%= (((Usuario)session.getAttribute("client")).getUsuario()) %></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../Logout.jsp"><span class="glyphicon glyphicon-log-out"></span>Salir</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<nav class=" navbar-primary  ">

  <a href="#" class="btn-expand-collapse"><span class="glyphicon glyphicon-menu-left"></span> <-></a>
  <ul class="navbar-nav navbar-primary-menu">
    <li >
      <a class="nav-link" href="TransferenciasServlet"><span class="glyphicon glyphicon-user"></span><span class="nav-label">Transferencias</span></a>
    
    
      <a class="nav-link" href="CuentasYMovimientoServlet"><span class="glyphicon glyphicon-list-alt"></span><span class="nav-label">Movimientos</span></a>
      
      <a class="nav-link" href="SolicitudPrestamoServlet"><span class="glyphicon glyphicon-th-list"></span><span class="nav-label">Solicitud prestamo</span></a>
      <a class="nav-link" href="PagarPrestamosServlet"><span class="glyphicon glyphicon-th-list"></span><span class="nav-label">Pagar prestamo</span></a>

      <a class="nav-link" href="MisDatos"><span class="glyphicon glyphicon-th-list"></span><span class="nav-label">Mis datos</span></a>
    
    
    </li>
  </ul>
</nav>
<div class="main-content">