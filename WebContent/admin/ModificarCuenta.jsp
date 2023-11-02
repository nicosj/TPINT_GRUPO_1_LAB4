<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Cuenta</title>
</head>
<body>
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

        /* Estilo para el botón "Modificar Cuenta" */
        .btn-delete {
            background-color: #ff00fb;
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
        <form id="accountForm" action="servletHTM" method="post">
            <h2>Modificación de Cuenta</h2>
            <fieldset>
                <legend>Seleccione Cuenta a Modificar</legend>
 		<p>
            <label>CBU:</label>
            <select name="cbu" id="cbuSelect">
                <%-- Define las opciones de CBU --%>
                <option value="CBU-1">CBU-123456789</option>
                <option value="CBU-2">CBU-222333444</option>
                <option value="CBU-3">CBU-666666666</option>
            </select>
        </p>
        <p>
                    <label>Cliente:</label>
            <select name="cliente" id="clienteSelect">
                <%-- Define las opciones de Cliente --%>
                <option value="Cliente-1">Sr. Ratón, Jerry</option>
                <option value="Cliente-2">Sr. Gato, Tom</option>
                <option value="Cliente-3">Sra. Tortuga, Manuelita</option>
            </select>
        </p>
<p>
            <label>Fecha de Creación: </label>
            <%-- Obtiene la fecha de creación y formatea --%>
            <%
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm a");
                java.util.Date currentDate = new java.util.Date(); // Fecha y hora actual
                String formattedDate = dateFormat.format(currentDate);
            %>
            <%= formattedDate %>
        </p>
                <p>
                    <label>Tipo de Cuenta:</label>
            <select name="tipoCuenta" id="tipoCuentaSelect">
                <%-- Define las opciones de tipo de cuenta --%>
                <option value="TipoCuenta-1">Cuenta en ARS</option>
                <option value="TipoCuenta-2">Cuenta en USD</option>
                <option value="TipoCuenta-3">Cuenta en Rupias</option>
            </select>
        </p>
                <p>
            <label>Saldo de Cuenta: </label>
            <input type="text" name="saldo" value="$ 12,000" id="saldoField" onblur="formatSaldo()" />
        </p>
      
        
        <!-- Una cuenta tiene un cliente asignado, fecha de creación, tipo de
cuenta, un número de cuenta, CBU y un saldo -->
                <p>
                    <!-- Keep the type attribute as "button" -->
                    <button type="button" id="btnModificarCuenta" class="btn btn-delete">Modificar Cuenta</button>
                </p>
            </fieldset>
        </form>
    </div>

</body>
</html>