<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import ="java.util.ArrayList"%>
 <%@ page import="dominio.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

        /* Estilo para el botón "Eliminar Cuenta" */
        .btn-delete {
            background-color: #ff2a00;
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
        <form id="accountForm" action="BajaCuentaServlet" method="post">
            <h2>Baja de Cuenta</h2>
            <fieldset>
                <legend>Seleccione Cuenta a dar de Baja</legend>
 <p>
            <label>CBU:</label>
            <select  name="desplegableCbu">
                <%  if(request.getAttribute("cuentas")!=null){
                	ArrayList<Cuenta> listaCuentas = null; 
                	listaCuentas = (ArrayList<Cuenta>)request.getAttribute("cuentas");
	               	  if(listaCuentas!=null){
					  	for(Cuenta acc :listaCuentas){%>
	               			<option value="<%= acc.getCBU() %>"><%=acc.getCBU() %></option>
	               		<%}
					  }
                } %>		
              </select>  	
        </p>
                <p>
                    <!-- Keep the type attribute as "button" -->
                    <button type="button" id="btnEliminarCuenta" class="btn btn-delete">Eliminar Cuenta</button>
                </p>
            </fieldset>
        </form>
    </div>


</body>
</html>