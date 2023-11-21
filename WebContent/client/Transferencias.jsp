<%@ page import="dominio.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if (session.getAttribute("client") != null) {%>

<jsp:include page="./header.jsp"/>

<div class="container-fluid" id="grad1">
    <div class="row justify-content-center mt-0">
        <div class="col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2">
            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                <h2><strong>Ventana Transferencias cliente <%= ((Usuario)session.getAttribute("clientTrans")).getIdCliente() %></strong></h2>

                <div class="row">
                    <div class="col-md-12 mx-0">
                        <div id="msform">
                            <!-- progressbar -->
                            <ul id="progressbar">
                                <li  id="personal" class="active"><strong>Seleccione Cuenta</strong></li>
                                <li id="personal" class="active"><strong>Destino</strong></li>
                                <li id="payment" class="active"><strong>Transferencia</strong></li>
                                <li id="confirm"><strong>Fin</strong></li>
                            </ul>
                            <!-- fieldsets -->
                            <fieldset>
                                <form method="post" action="TransferenciasServlet" >

                                <div class="form-card">
                                    <h2 class="fs-title">Cuentas del Usuario</h2>
                                    <label class="pay">Cuenta Origen</label>
                                    <select class="form-control " name="" id="">
                                        <option value=""> Cuenta1 | Nuemro Cuenta | $Saldo</option>
                                        <option value=""> Cuenta1 | Nuemro Cuenta | $Saldo</option>
                                        <option value=""> Cuenta1 | Nuemro Cuenta | $Saldo</option>
                                    </select>
                                </div>
                                <input type="submit" name="next" class="next action-button" value="Siguiente"/>
                                </form>
                            </fieldset>
                            <fieldset>
                                <div class="form-card">

                                    <h2 class="fs-title">Tipo de Transferencia</h2>
                                    <label for="tipo">Tipo</label>
                                    <select name="Tipo" id="tipo">
                                        <option value=""> Entre cuentas propias</option>
                                        <option value=""> A terceros</option>
                                        <option value=""> Nuevo Destinatario</option>
                                    </select>

                                    <h2>Seleccin id de cuenta Destino</h2>
                                    <select name="" id="">
                                        <option value="">Destino 1 </option>
                                        <option value="">Destino 2 </option>
                                    </select>
                                    <h2>Seleccione Historial de transgferencia</h2>
                                    <select name="" id="">
                                        <option value="">Historial 1 </option>
                                        <option value="">Historial 2 </option>
                                    </select>
                                    <h2>Ingrese Nuevo CBU</h2>
                                    <input type="number" id="cbo"    name="" value="">
                                    <span>Mostrar datos de busqueda</span>
                                    <button>Buscar CLiente</button>


                                </div>
                                <input type="button" name="previous" class="previous action-button-previous" value="volver"/>
                                <input type="button" name="next" class="next action-button" value="Siguiente"/>
                            </fieldset>
                            <fieldset >
                                <div class="form-card">
                                    <!--Tanferencia  cuentas-->
                                    <div class="form-card text-center">
                                        <h2>Cuenta Seleccionada</h2>
                                        <span> cuenta numero  6565656 monto $656956</span>
                                        <h2>Cuenta Destino</h2>
                                        <h2>Nombre y apellido</h2>
                                        <span> cuenta numero 999999</span>
                                        <h2>Monto de transferencia</h2>
                                        <input type="number" id="transferencia"  name value="" >
                                    </div>

                                </div>
                                <input type="button" name="previous" class="previous action-button-previous" value="Anterior"/>
                                <input type="button" name="make_payment" class="next action-button" value="Siguiente"/>
                            </fieldset>
                            <fieldset>
                                <div class="form-card">
                                    <h2 class="fs-title text-center">Exito !</h2>
                                    <br><br>
                                    <div class="row justify-content-center">
                                        <div class="col-3">
                                            <img src="https://img.icons8.com/color/96/000000/ok--v2.png" class="fit-image">
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="row justify-content-center">
                                        <div class="col-7 text-center">
                                            <h5>Transferencia exitosa</h5>
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
    $(document).ready(function(){

        var current_fs, next_fs, previous_fs; //fieldsets
        var opacity;

        $(".next").click(function(){

            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            //Add Class Active
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function(now) {
                    // for making fielset appear animation
                    opacity = 1 - now;

                    current_fs.css({
                        'display': 'none',
                        'position': 'relative'
                    });
                    next_fs.css({'opacity': opacity});
                },
                duration: 600
            });
        });

        $(".previous").click(function(){

            current_fs = $(this).parent();
            previous_fs = $(this).parent().prev();

            //Remove class active
            $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

            //show the previous fieldset
            previous_fs.show();

            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function(now) {
                    // for making fielset appear animation
                    opacity = 1 - now;

                    current_fs.css({
                        'display': 'none',
                        'position': 'relative'
                    });
                    previous_fs.css({'opacity': opacity});
                },
                duration: 600
            });
        });

        $('.radio-group .radio').click(function(){
            $(this).parent().find('.radio').removeClass('selected');
            $(this).addClass('selected');
        });

        $(".submit").click(function(){
            return false;
        })

    });
</script>

<% } else {
    response.sendRedirect("../index.jsp");
}%>