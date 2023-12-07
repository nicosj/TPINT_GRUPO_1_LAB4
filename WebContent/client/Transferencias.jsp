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
                <h2  class="marcoTitu" >Ventana Transferencias </h2>

                <%
                    Integer pasos= (Integer) session.getAttribute("pasos");
                    String cbushow=(String) session.getAttribute("cbushow");
                    Cliente clix= (Cliente)session.getAttribute("clientetrans") ;
                    Cuenta cux= (Cuenta) session.getAttribute("cuentatrans");
                    String error=(String)session.getAttribute("error");


                %>

                <div class="row">
                    <div class="col-md-12 mx-0">
                        <div id="msform">
                            <!-- progressbar -->
                            <ul id="progressbar">
                                <li id="account" <%= pasos>=0?"class='active'" :"" %> ><strong>Seleccione Cuenta</strong></li>
                                <li id="personal" <%= pasos>=1?"class='active'" :"" %>><strong>Destino</strong></li>
                                <li id="payment" <%= pasos>=2?"class='active'" :"" %>><strong>Transferencia</strong></li>
                                <li id="confirm" <%= pasos>=3?"class='active'" :"" %>><strong>Fin</strong></li>
                            </ul>
                            <!-- fieldsets -->
                            <fieldset class="<% if(pasos== 0){ %>activeTrans<%}else{%>desaTrans<%} %>" >
                                <form method="post" action="TransferenciasServlet">

                                    <div class="form-card">
                                        <h2 class="fs-title">Cuentas del Usuario</h2>
                                        <label class="pay">Cuenta Origen</label>
                                        <select class="tipoTrans "  name="cuentasel" id=pay>
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
                                        <div class="marcoNext">
                                            <input type="submit" class="next action-button" name="pasoUno" value="Siguiente"/>
                                        </div>
                                    </div>

                                </form>

                                <%--<input type="submit" name="next" class="next action-button" value="Siguientetest"/>--%>
                            </fieldset>
                            <fieldset <% if(pasos== 1){ %> style="display: block;opacity: 1;" <%}else{%>class="desaTrans" <% } %>>

                                <div class="form-card ">

                                    <h2 class="fs-title">Tipo de Transferencia</h2>
                                    <label for="tipoTrans">Tipo</label>
                                    <select name="tipoTrans" class="tipoTrans" id="tipoTrans">
                                        <option selected > Seleccione tipo de Transferencia</option>
                                        <option value="1"> Entre cuentas propias</option>

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
                                            <div class="marcoNext">
                                            <input type="submit" name="pasoDos" class="next action-button" value="Siguiente"/>
                                            </div>
                                        </form>
                                    </div>

                                    <%-- :-D Nuevo destino--%>
                                    <div  <% if(cbushow== "1"){ %> class="cleart" style="display: block;opacity: 1;"<%}else{%> class="hiddenCbu" <% } %> >

                                        <form action="TransferenciasServlet" method="post">
                                            <h2>Ingrese Nuevo CBU</h2>
                                            <input type="number" id="cbu" name="cbu" value="">
                                            <div class="marcoNext">
                                            <input type="submit" name="valida" class="next action-button"  value="Buscar"/>
                                            </div>
                                        </form>
                                        <div>


                                            <%
                                                if(clix!=null) {
                                                    out.println("<div class='activeCbu' style='display: block;' >");
                                                    out.println("<h1>Resultado</h1>");
                                                    out.println("<h2>Cliente:" + clix.getNombreCompleto() + "</h2>");
                                                    out.println("<h2>Cuil:" + clix.getCUIL() + "</h2>");
                                                    out.println("<h2>Numero de Cuenta Destino:" + cux.getNumero_Cuenta() + "</h2>");
                                                    out.println("<h2>Cbu Destino:" + cux.getCBU() + "</h2>");
                                                    out.println("</div>");

                                                }
                                                else if(error!=null){
                                                    out.println("<div class='activeCbu' style='display: block;' >");
                                                    out.println("<h1>Resultado</h1>");
                                                    out.println("<h1>" + error + "</h1>");
                                                    out.println("</div>");
                                                }
                                            %>


                                        </div>
                                        <form action="TransferenciasServlet" method="post">
                                            <input type="hidden" name="cbus" value="<%= cux!=null?cux.getNumero_Cuenta():""%>">
                                            <div class="marcoNext">
                                            <input type="submit" name="pasoCbu" class="next action-button" <%=error!=null?"disabled":""%> value="Siguiente"/>
                                            </div>
                                        </form>

                                        </div>

                                </div>
                                <%--Borrar--%>
                                <%--<input type="button" name="previous" class="previous action-button-previous"
                                       value="volver"/>--%>

                            </fieldset>
                            <fieldset <% if(pasos== 2){ %> style="display: block;opacity: 1;"<% } else { %> class="desaTrans" <% } %> >
                                <div class="form-card">
                                    <!--Tanferencia  cuentas-->
                                    <% 
                                    if( session.getAttribute("cuentaOrigen")!=null && session.getAttribute("cuentaDestino")!=null){ %>
                                    <form action="TransferenciasServlet" method="post">
                                    <div class="activaTransf">

                                        <% if (((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta()!=null){
                                        out.println("<h1>Cuenta Origen Seleccionada</h1>");
                                        out.println("<h3>Numero de Cuenta: " + ((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta() + "</h3>");
                                        out.println("<h3>Saldo: $" + ((Cuenta)session.getAttribute("cuentaOrigen")).getSaldo()+ "</h3>");
                                        out.println("<h1>Cuenta Destino</h1>");
                                        out.println("<h3>Numero de Cuenta: " + ((Cuenta)session.getAttribute("cuentaDestino")).getNumero_Cuenta() + "</h3>");
                                        out.println("<h3>CBU: " + ((Cuenta)session.getAttribute("cuentaDestino")).getCBU() + "</h3>");
                                        %>
                                         <%= clix!=null? "<h2>Nombre y Apellido:"+ clix.getNombreCompleto()+"</h2>": "<h2>Cuenta Propia</h2>" %>
                                         <%= clix!=null? "<h3>Cuil:"+ clix.getCUIL()+"</h3>": "" %>


                                        <h2>Ingrese monto a transferir</h2>
                                        <input type="text" class="form-control" id="transferencia" name="valorD" value="">
                                            <div class="marcoNext">
                                        <input type="submit" name="pasoTres" id="transferirFId" class="next action-button"  value="Transferir"/>
                                            </div>
                                        <h2 <%= error!=null?"class='errors'":"" %>><%= error!=null?error:""%></h2>
                                    </div>
                                    </form>
                                    <% }  }%>


                                </div>


                            </fieldset >
                            <fieldset <% if(pasos== 3){ %> style="display: block;opacity: 1;"<% } else { %> class="desaTrans" <% }%> >
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
                            <%--<button type="button" name="previous" class="previous action-button-previous"
                                   value="Volver"/>--%>
                            <div <% if(pasos>0 && pasos<=2){ %> style="display: block;opacity: 1;" <%}else{%>class="desaTrans" <% } %>>
                                <a id="cancelarFId" class="previous action-button-previous" href="TransferenciasServlet"><span class="nav-label">Volver</span></a>
                            </div>
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
        $('#transferirFId').on("click", function (e) {
            var self = $(this);
            e.preventDefault();
            Swal.fire({
                title: '¿Estas seguro?',
                text: "Usted esta por hacer una transferencia!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Confirmar',


            }).then((result) => {
                if (result.isConfirmed) {
                    self.off("click").click();
                }
            })


        });
        $('#cancelarFId').on("click", function (e) {
            var self = $(this);
            e.preventDefault();
            Swal.fire({
                title: '¿Estas seguro?',
                text: "Usted esta por cancelar la transferencia!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Confirmar',


            }).then((result) => {
                if (result.isConfirmed) {
                    self.off("click").click();
                }
            })
        });
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