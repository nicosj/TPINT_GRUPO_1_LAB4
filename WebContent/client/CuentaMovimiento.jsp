<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Movimiento" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<% if (session.getAttribute("client") != null) {%>

<jsp:include page="./header.jsp"/>

<div class="container-fluid text-center">

    <h1 class="marcoTitu" > Cuenta y Movimientos</h1>
    <div class="row justify-content-center mt-0">

        <div class="col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2">

            <div class="<%= session.getAttribute("movimientos")!=null? "hidecla":"" %> ">
                <%
                    ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) session.getAttribute("cuentas");
                    for (Cuenta cuenta : cuentas) {
                        out.println("<form action='CuentasYMovimientoServlet' method='post' class='cuenta' style='cursor: pointer ;' id=" + cuenta.getNumero_Cuenta() + " >");
                        out.println("<div class='card marco px-0 pt-4 pb-0 mt-3 mb-3'>");
                        out.println("<div class='card-body'>");
                        out.println("<h2 class='text-center'>Tipo de cuenta: "+ cuenta.getTipo_Cuenta() +"</h2>");
                        out.println("<h2>Numero de cuenta: " + cuenta.getNumero_Cuenta() + "</h2>");
                        out.println("<h1>Saldo: $" + cuenta.getSaldo() + "</h1>");
                        out.println("<h4>CBU: " + cuenta.getCBU() + "</h4>");
                        out.println("<input type='hidden' name='ncuenta' value='" + cuenta.getNumero_Cuenta() + "'/>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</form>");

                    }
                %>
            </div>
                <%-- show data table  debit credit transfer                 --%>
                <table id="tablaConPaginadorYFiltro" class="<%= session.getAttribute("movimientos")==null? "hidecla":"display" %> ">


                    <%
                        if (session.getAttribute("movimientos") != null) {
                            ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) session.getAttribute("movimientos");
                    %>
                    <%= movimientos.size()==0?"<thead><tr><td>Nada por aca</td></tr></thead>":" <thead> <tr> <th>Fecha</th> <th>Descripcion</th> <th>Debito</th> <th>Credito</th> </tr> </thead> <tbody>"%>
                    <%

                            for (Movimiento cuenta : movimientos) {
                    %>
                    <tr>

                        <td><%= cuenta.getFechaMovimiento() %>
                        </td>
                        <td><%= cuenta.getDetalleConcepto() %>
                        </td>

                        <td>$<%= cuenta.getImporteMovimiento() < 0 ? cuenta.getImporteMovimiento() : 0 %>
                        </td>
                        <td>$<%= cuenta.getImporteMovimiento() > 0 ? cuenta.getImporteMovimiento() : 0 %>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>


                    </tbody>
                </table>
            <div  class="<%= session.getAttribute("movimientos")==null? "hidecla":"text-center" %> ">
                <a class="nav-link" href="CuentasYMovimientoServlet"><span class="nav-label">volver a cuentas</span></a>
            </div>

        </div>

    </div>
</div>

<jsp:include page="./footer.jsp"/>

<% } else {
    response.sendRedirect("../index.jsp");
}%>
<script >
    $(document).ready(function () {
        $('.hidecla').hide();
        $(".cuenta").click(function () {
            var id = $(this).attr("id");
            console.log(id);
            $(this).submit();
        });
        let table = new DataTable('#tablaConPaginadorYFiltro', {
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json',
                },
            }
        );

    });

</script>
