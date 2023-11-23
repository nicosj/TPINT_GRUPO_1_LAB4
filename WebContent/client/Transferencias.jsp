<%@ page import="dominio.Usuario" %>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<% if (session.getAttribute("client") != null) {%>

<jsp:include page="./header.jsp"/>

<div class="container-fluid" id="grad1">
    <div class="row justify-content-center mt-0">
        <div class="col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2">
            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                <h2><strong>Ventana Transferencias
                    cliente <%= ((Usuario) session.getAttribute("clientTrans")).getIdCliente() %>
                </strong></h2>
                <h2><strong>Ventana Transferencias cliente <%= ((ArrayList) session.getAttribute("cuentas")).size() %>
                </strong></h2>
                <%
                    String origen= (String) session.getAttribute("stepOrigen");
                    String destino= (String) session.getAttribute("stepDestino");
                    String cbushow=(String) session.getAttribute("cbushow");
                    String transf=(String) session.getAttribute("stepTrans");//trabajar
                    Cliente clix= (Cliente)session.getAttribute("clientetrans") ;
                    Cuenta cux= (Cuenta) session.getAttribute("cuentatrans");
                    String error=(String)session.getAttribute("error");
                    String exito=(String)session.getAttribute("stepFinal");

                %>

                <div class="row">
                    <div class="col-md-12 mx-0">
                        <div id="msform">
                            <!-- progressbar -->
                            <ul id="progressbar">
                                <li id="account" class="active"><strong>Seleccione Cuenta</strong></li>
                                <li id="personal"><strong>Destino</strong></li>
                                <li id="payment"><strong>Transferencia</strong></li>
                                <li id="confirm"><strong>Fin</strong></li>
                            </ul>
                            <!-- fieldsets -->
                            <fieldset class="<% if(origen== "1"){ %>activeTrans<%}else{%>desaTrans<%} %>" >
                                <form method="post" action="TransferenciasServlet">

                                    <div class="form-card">
                                        <h2 class="fs-title">Cuentas del Usuario</h2>
                                        <label class="pay">Cuenta Origen</label>
                                        <select class="form-control " name="cuentasel" id=pay>
                                            <%
                                                ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");

                                                if (cuentas != null) {
                                                    for (Cuenta cu : cuentas) {
                                            %>
                                            <option value="<%= cu.getNumero_Cuenta() %>"><%= "Tipo de Cuenta: " + cu.getTipo_Cuenta() + "Numero de Cuenta: " + cu.getNumero_Cuenta() + "|" + " Saldo: $" + cu.getSaldo() %>
                                            </option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <input type="submit" class="next action-button" name="pasoUno" value="Siguiente"/>
                                </form>

                                <%--<input type="submit" name="next" class="next action-button" value="Siguientetest"/>--%>
                            </fieldset>
                            <fieldset <% if(destino== "1"){ %> style="display: block;opacity: 1;" <%}else{%>class="desaTrans" <% } %>>

                                <div class="form-card ">

                                    <h2 class="fs-title">Tipo de Transferencia</h2>
                                    <label for="tipoTrans">Tipo</label>
                                    <select name="tipoTrans" class="tipoTrans" id="tipoTrans">
                                        <option selected > Seleccione tipo de Transferencia</option>
                                        <option value="1"> Entre cuentas propias</option>
                                  <%--      <option value="2"> A terceros</option>--%>
                                        <option value="3"> Nuevo Destinatario</option>
                                    </select>
                                    <%-- :-D cuentas propias--%>

                                    <div class="hiddenMy">
                                        <form action="TransferenciasServlet" method="post">
                                            <h2>Seleccione otra cuenta de su propiedad</h2>
                                            <select name="entreCuentas" id="entreCuentas">
                                                <%
                                                    ArrayList<Cuenta> cuentasd = (ArrayList<Cuenta>) session.getAttribute("cuentasFiltrada");
                                                    if (cuentasd != null) {
                                                        for (Cuenta cu : cuentasd) {
                                                %>
                                                <option value="<%= cu.getNumero_Cuenta() %>"><%= "Tipo de Cuenta: " + cu.getTipo_Cuenta() + " | Numero de Cuenta: " + cu.getNumero_Cuenta() + "|" + " | Saldo: $" + cu.getSaldo() %>
                                                </option>
                                                <%

                                                        }
                                                    }
                                                %>
                                            </select>

                                            <input type="submit" name="pasoDos" class="next action-button" value="Siguiente"/>
                                        </form>
                                    </div>
                                    <%-- :-D historial de transferencias--%>
                                  <%--  <div class="hiddenHistory">
                                        <form action="TransferenciasServlet" method="post">
                                        <h2>Seleccione Historial de transgferencia</h2>
                                        <select name="historial" id="historial">
                                            <%
                                                ArrayList<Cuenta> hcuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");

                                                if (hcuentas != null) {
                                                    for (Cuenta cu : hcuentas) {
                                            %>
                                            <option value="<%= cu.getNumero_Cuenta() %>"><%= "Tipo de Cuenta: " + cu.getTipo_Cuenta() + "Numero de Cuenta: " + cu.getNumero_Cuenta() + "|"%>
                                            </option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>

                                            <input type="submit" name="pasoDos" class="next action-button" value="Siguiente"/>
                                        </form>
                                    </div>--%>
                                    <%-- :-D Nuevo destino--%>
                                    <div  <% if(cbushow== "1"){ %> class="cleart" style="display: block;opacity: 1;"<%}else{%> class="hiddenCbu" <% } %> >

                                        <form action="TransferenciasServlet" method="post">
                                            <h2>Ingrese Nuevo CBU</h2>
                                            <input type="number" id="cbu" name="cbu" value="">
                                            <input type="submit" name="valida" class="next action-button" value="Buscar"/>
                                        </form>
                                        <div>

                                            <h1>Resultado</h1>
                                            <h2>Cliente: <%= clix!=null?clix.getNombreCompleto():"" %> </h2>
                                            <h2>Cuil:<%= clix!=null?clix.getCUIL():""%>
                                            </h2>
                                            <h2>Numero de Cuenta:<%= cux!=null?cux.getNumero_Cuenta():"" %>
                                            </h2>
                                            <h2>CBU:<%= cux!=null?cux.getCBU():"" %>
                                            </h2>

                                            <h2><%= error!=null?error:"" %></h2>

                                        </div>
                                        <form action="TransferenciasServlet" method="post">
                                            <input type="hidden" name="cbus" value="<%= cux!=null?cux.getNumero_Cuenta():""%>">
                                            <input type="submit" name="pasoCbu" class="next action-button" value="Siguiente"/>
                                        </form>

                                        </div>

                                </div>
                                <%--Borrar--%>
                                <%--<input type="button" name="previous" class="previous action-button-previous"
                                       value="volver"/>--%>

                            </fieldset>
                            <fieldset <% if(transf== "1"){ %> style="display: block;opacity: 1;"<% } else { %> class="desaTrans" <% } %> >
                                <div class="form-card">
                                    <!--Tanferencia  cuentas-->
                                    <% 
                                    if( session.getAttribute("cuentaOrigen")!=null && session.getAttribute("cuentaDestino")!=null){ %>
                                    <form action="TransferenciasServlet" method="post">
                                    <div class="form-card text-center">
                                        <h2>Cuenta Origen Seleccionada</h2>
                                        <% if (((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta()!=null){ %>
                                        <span><%= ((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta() %> </span>
                                        <span><%= ((Cuenta)session.getAttribute("cuentaOrigen")).getSaldo() %></span>
                                        <h2>Cuenta Destino</h2>
                                        <span><%= ((Cuenta)session.getAttribute("cuentaDestino")).getNumero_Cuenta() %></span>
                                        <h2>Nombre y apellido</h2>
                                        <h2>Ingrese monto a transferir</h2>
                                        <input type="text" id="transferencia" name="valorD" value="">
                                        <input type="submit" name="pasoTres" class="next action-button" value="Siguiente"/>
                                    </div>
                                    </form>
                                    <% }  }%>
                                    <h2><%= error!=null?error:""%></h2>

                                </div>
                                <input type="button" name="previous" class="previous action-button-previous"
                                       value="Anterior"/>

                            </fieldset >
                            <fieldset <% if(exito== "1"){ %> style="display: block;opacity: 1;"<% } else { %> class="desaTrans" <% }%> >
                                <div class="form-card">
                                    <h2 class="fs-title text-center">Exito !</h2>
                                    <br><br>
                                    <div class="row justify-content-center">
                                        <div class="col-3">
                                            <img src="https://img.icons8.com/color/96/000000/ok--v2.png"
                                                 class="fit-image">
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="row justify-content-center">
                                        <div class="col-7 text-center">
                                            <h5><%= error!=null?error:"transferencia exitosa"%></h5>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="./footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {

        $('.tipoTrans').change(function () {
            var value = $(this).val();
            $('.cleart').hide();
            if (value === "1") {
                $('.hiddenMy').show();
                $('.hiddenHistory').hide();
                $('.hiddenCbu').hide();
                console.log("tito"+value);
            } else if (value === "2") {
                $('.hiddenMy').hide();
                $('.hiddenHistory').show();
                $('.hiddenCbu').hide();
                console.log("tito"+value);
            } else if (value === "3") {
                $('.hiddenMy').hide();
                $('.hiddenHistory').hide();
                $('.hiddenCbu').show();
                $('.cleart').show();
                console.log("tito"+value);
            }
        });
    });
</script>

<% } else {
    response.sendRedirect("../index.jsp");
}%>