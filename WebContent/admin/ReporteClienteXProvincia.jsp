<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clientes por provincia</title>
</head>
<body>
<h1>SECCION DE REPORTES</h1>
<h3>Provincia con mayor cantidad de clientes</h3>
<p>Seleccione una provincia</p>
<select>
<option>Provincias</option>
</select>

<p>Seleccione el género que desea contabilizar.</p>

<p>Masculino</p>
<input type="radio" name="radioMasc">
<p>Femenino</p>
<input type="radio" name="radioFem">
<p>Ver todos (indiferente al género)</p>
<input type="radio" name="radioAll">



<input type="button" name="btnAceptar" value="Aceptar">
</body>
</html>