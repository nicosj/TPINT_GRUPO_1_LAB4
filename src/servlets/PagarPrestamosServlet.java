package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;


//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Negocio_Implementacion.*;
// TODO import Negocio_Implementacion.PagoPrestamo_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;
import dao_Implement.PrestamoDao_Implement;
import dominio.*;
import negocio.*;
import negocio.PagoPrestamo_Negocio;

/**
 * Servlet implementation class PagarPrestamosServlet
 */
@SuppressWarnings("unused")
@WebServlet("/client/PagarPrestamosServlet")
public class PagarPrestamosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PagarPrestamosServlet() {
        super();

    }


    // TODO Generar el negocio "PagoPrestamo_NegocionImp"
    //PagoPrestamo_NegocioImp pago = new PagoPrestamo_NegocioImp();
    ArrayList<PagoPrestamo> listaPagos = new ArrayList<PagoPrestamo>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        session = request.getSession();
        session.setAttribute("prestamoXU", null);
        Usuario clix = (Usuario) session.getAttribute("client");

        Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();

        ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerCuentaByClientId(clix.getIdCliente());
        PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            prestamos.addAll(prestamoDao.readAllByCuenta(cuenta.getNumero_Cuenta()));
        }
        session.setAttribute("prestamos", prestamos);
        session.setAttribute("cuentas", cuentas);
        System.out.println(prestamos + "prestamos");
        request.getRequestDispatcher("/client/PagarPrestamos.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session;
        session = request.getSession();
        Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();

        Usuario clix = (Usuario) session.getAttribute("client");

        ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerCuentaByClientId(clix.getIdCliente());
        PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();

        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

        for (Cuenta cuenta : cuentas) {
            prestamos.addAll(prestamoDao.readAllByCuenta(cuenta.getNumero_Cuenta()));
        }
        session.setAttribute("prestamos", prestamos);
        session.setAttribute("cuentas", cuentas);
        /*Prestmo cuotas*/
        String estadoPrestamo = request.getParameter("estadoPrestamo");
        String idPago = request.getParameter("idPago");
        System.out.println("test1");
        if (request.getParameter("pagarCuota") != null) {
            System.out.println("test2");
            ArrayList<PagoPrestamo> filtrado = new ArrayList<PagoPrestamo>();
            /*trae datos*/
            PagoPrestamo_NegocioImp pagoT = new PagoPrestamo_NegocioImp();

            ArrayList<PagoPrestamo> pprestamo = pagoT.readAll();

            for (PagoPrestamo prest : pprestamo) {

                if (request.getParameter("idPrestamo").trim().equals(String.valueOf(prest.getPrestamo().getIdPrestamo()))) {
                    PagoPrestamo pago = new PagoPrestamo();
                    pago.setIdPago(prest.getIdPago());
                    pago.getCuenta().setNumero_Cuenta((prest.getCuenta().getNumero_Cuenta()));
                    pago.setFecha_Pago(null);
                    pago.setImporte_cuota(prest.getImporte_cuota());
                    pago.setImporte_restante(prest.getImporte_restante());
                    pago.setCuotas_restantes(prest.getCuotas_restantes());
                    pago.getPrestamo().setIdPrestamo(prest.getPrestamo().getIdPrestamo());
                    filtrado.add(pago);
                    System.out.println("holu3");
                }

            }

            System.out.println("holu4");
            session.setAttribute("prestamoXU", filtrado);


        }
        if (request.getParameter("pagarEstaCuota") != null) {
            System.out.println(request.getParameter("idEstePrestamo" + "asd666666"));


            ArrayList<PagoPrestamo> filtradox = (ArrayList<PagoPrestamo>) session.getAttribute("prestamoXU");
            System.out.println(filtradox+"titos system");
            Optional<PagoPrestamo> cuxOptional = filtradox.stream().filter(p -> p.getIdPago() == Integer.parseInt(request.getParameter("idEstePrestamo"))).findFirst();
            if (cuxOptional.isPresent()) {
                request.getParameter("pagarEstaCuota");
                PagoPrestamo cux = cuxOptional.get();

                Cuenta cufin = cuentaNegocio.obtenerCuenta(request.getParameter("cuotas"));
                System.out.println(cufin.getSaldo() + "saldo");
                if (cufin.getSaldo() >= cux.getImporte_cuota() ){

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Movimiento movimiento = new Movimiento();
                    PagoPrestamo_NegocioImp pagoT = new PagoPrestamo_NegocioImp();
                    PagoPrestamo pago = new PagoPrestamo();
                    pago.setIdPago(cux.getIdPago());
                    pago.getCuenta().setNumero_Cuenta(cux.getCuenta().getNumero_Cuenta());
                    pago.setFecha_Pago(dtf.format(now).toString());
                    pago.setImporte_cuota(cux.getImporte_cuota());
                    pago.setImporte_restante(cux.getImporte_restante() - cux.getImporte_cuota());
                    pago.setCuotas_restantes(cux.getCuotas_restantes() - 1);
                    pago.getPrestamo().setIdPrestamo(cux.getPrestamo().getIdPrestamo());
                    if (cux.getPrestamo().getIdPrestamo() >= 0) {
                        pagoT.insert(pago);
                        cuentaNegocio.ajusteCuenta(cufin.getNumero_Cuenta(), -(cux.getImporte_cuota()));

                        MovimientoNegocio_Imp movimientoN = new MovimientoNegocio_Imp();

                        Cuenta cuenta = new Cuenta();
                        movimiento.getCuenta().setNumero_Cuenta(cufin.getNumero_Cuenta());
                        movimiento.setFechaMovimiento(dtf.format(now).toString());
                        movimiento.setDetalleConcepto("Pago Cuota restantes: " + cux.getCuotas_restantes());
                        movimiento.setImporteMovimiento(-(cux.getImporte_cuota()));
                        movimiento.setTipoMovimiento("Debito");
                        movimientoN.insert(movimiento);
                    }
                }
            } else
                session.setAttribute("error", "No se pudo realizar el pago");

             session.setAttribute("prestamoXU", null);


        /*trae datos*/

    }

        System.out.println("holu55555555555555");


        /*System.out.println("idPrestamo: " + idPrestamo);
        System.out.println("estadoPrestamo: " + estadoPrestamo);
        System.out.println("idPago: " + idPago);*/


        request.getRequestDispatcher("/client/PagarPrestamos.jsp").

    forward(request, response);
}

}
