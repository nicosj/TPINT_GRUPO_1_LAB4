<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Alta de Cuenta</h2>
	<fieldset>
    <legend>Nueva Cuenta</legend>

    <form action="servletHTM" method="post">
    <p>
        <label for="cbu">CBU:</label>
        <input type="text" name="cbu" value="<%= "generarCBU()" %>" readonly><br>
        <!-- todo: Funcion para generar CBU automaticamente -->
    </p>
     <p>
        <label for="montoInicial">Monto Inicial: $</label>
        <input type="text" name="montoInicial" value="10000" readonly><br>
   </p>
    <p>
        <input type="submit" value="Crear Cuenta">
     </p>
        </fieldset>
    </form>

</body>
</html>