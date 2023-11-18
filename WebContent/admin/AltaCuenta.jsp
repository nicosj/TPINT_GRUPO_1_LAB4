<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="dominio.Cliente"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta de Cuenta</title>

    <style>
        /* Estilo para el body */
       

        /* Estilo para el contenedor */
         .containered {
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            padding: 20px;
            max-width: 400px;
            width: 100%;
            margin: auto;
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

    <div class="containered">
        <h2>Alta de Cuenta</h2>
        <form action="AltaCuentaServlet" method="post">
            <fieldset>
                <legend>Buscar Cliente</legend>
                <p>
                    <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" placeholder="Dni" required>
            <input type="submit" value="Buscar" name="getCliente">
                </fieldset>
        </form>
        <form id="accountForm" action="AltaCuentaServlet" method="post">

            <fieldset>
                <legend>Nueva Cuenta</legend>
                <p>
                    <label for="dni">DNI:</label>
                    <%
                    if(request.getAttribute("cliente")!=null){
                    	Cliente cliente =(Cliente)request.getAttribute("cliente");
                    	%>
					<input type="hidden" name="idcliente" value="<%=cliente.getIdCLiente() %>" readonly/>
                   <input type="text" name="dni" value="<%=cliente.getDNI() %>" readonly/>
                    <input type="text" name="nombre" placeholder="Nombre" value="<%=cliente.getNombre() %>" readonly/>

                   <%} %>
                </p>
                <p>
                    <label for="cbu">CBU:</label>
                    <%if(request.getAttribute("cbu")!=null){
                    	String nuevoCBU =(String)request.getAttribute("cbu");%>
                    
                   <input type="text" name="cbu" id="cbu" value="<%=nuevoCBU %>" readonly>
                   <%} %>
                </p>
                <p>
                    <label for="montoInicial">Monto Inicial: $</label>
                    <input type="number" id="montoInicial" name="saldo" value="10000" readonly>
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
                    <button type="submit" id="btnCrearCuenta" name="btnCrearCuenta" class="btn btn-create">Crear Cuenta</button>
                </p>
                            <% if (request.getAttribute("error") != null) { %>
        <div class="error-message">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>
            </fieldset>

        </form>
    </div>

<jsp:include page="./footer.jsp" />

