<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% if(session.getAttribute("admin") != null) {%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta de Cuenta</title>

    <style>
        /* Estilo para el body */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Estilo para el contenedor */
        .container {
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            padding: 20px;
            max-width: 400px;
            width: 100%;
        }

        /* Estilo para el fieldset */
        fieldset {
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        /* Estilo para el botón "Crear Cuenta" */
        .btn-create {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-create:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <form id="accountForm" action="servletHTML" method="post">
            <h2>Alta de Cuenta</h2>
            <fieldset>
                <legend>Nueva Cuenta</legend>
                <p>
                    <label for="cbu">CBU:</label>
                    <% if(request.getAttribute("cbu") != null) {
                    	String al=(String)request.getAttribute("cbu");%>
                   <input type="text" name="cbu" value="<%= al %>" readonly>
                   <%} %>

                </p>
                <p>
                    <label for="montoInicial">Monto Inicial: $</label>
                    <input type="text" name="montoInicial" value="10000" readonly>
                </p>
                <p>
                                <p>
                    <label>Tipo de Cuenta:</label>
            <select name="tipoCuenta" id="tipoCuentaSelect">
                <%-- Define las opciones de tipo de cuenta --%>
                <option value="CA">Caja de Ahorro</option>
                <option value="CC">Cuenta Corriente</option>
            </select>
            </p>
                    <p><!-- Keep the type attribute as "button" -->
                    <button type="button" id="btnCrearCuenta" class="btn btn-create">Crear Cuenta</button>
                </p>
            </fieldset>
        </form>
    </div>

   

</body>

</html>
<% }else {
	response.sendRedirect("../Inicio.jsp");
}%>







