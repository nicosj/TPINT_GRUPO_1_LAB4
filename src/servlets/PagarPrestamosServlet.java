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

        if (request.getParameter("pagarCuota") != null) {

            ArrayList<PagoPrestamo> filtrado = new ArrayList<PagoPrestamo>();
            /*trae datos*/
            PagoPrestamo_NegocioImp pagoT = new PagoPrestamo_NegocioImp();

            ArrayList<PagoPrestamo> pprestamo = pagoT.readAllByID(request.getParameter("idPrestamo"));


            for (PagoPrestamo prest : pprestamo) {

                if (request.getParameter("idPrestamo").trim().equals(String.valueOf(prest.getPrestamo().getIdPrestamo()))) {
                    PagoPrestamo pago = new PagoPrestamo();
                    pago.setIdPago(prest.getIdPago());

                    pago.setNumero_Cuenta(prest.getNumero_Cuenta());
                    pago.setFecha_Pago(prest.getFecha_Pago());

                    pago.setImporte_cuota(prest.getImporte_cuota());
                    pago.setImporte_restante(prest.getImporte_restante());
                    pago.setCuotas_restantes(prest.getCuotas_restantes());
                    pago.getPrestamo().setIdPrestamo(prest.getPrestamo().getIdPrestamo());
                    filtrado.add(pago);
                }

            }


            session.setAttribute("prestamoXU", filtrado);


        }

        if (request.getParameter("pagarEstaCuota") != null) {


            ArrayList<PagoPrestamo> filtradox = (ArrayList<PagoPrestamo>) session.getAttribute("prestamoXU");

            Optional<PagoPrestamo> cuxOptional = filtradox.stream().filter(p -> p.getIdPago() == Integer.parseInt(request.getParameter("idEstePrestamo"))).findFirst();


            if (cuxOptional.isPresent()) {
                request.getParameter("pagarEstaCuota");
                PagoPrestamo cux = cuxOptional.get();

                Cuenta cufin = cuentaNegocio.obtenerCuenta(request.getParameter("cuotas"));


                if (cufin.getSaldo() >= cux.getImporte_cuota() && cux.getCuotas_restantes() > 0) {

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Movimiento movimiento = new Movimiento();
                    PagoPrestamo_NegocioImp pagoT = new PagoPrestamo_NegocioImp();
                    PagoPrestamo pago = new PagoPrestamo();


                    if (cux.getFecha_Pago() == null) {
                        pagoT.update(cux.getIdPago());

                        cuentaNegocio.ajusteCuenta(cufin.getNumero_Cuenta(), -(cux.getImporte_cuota()));
                        MovimientoNegocio_Imp movimientoN = new MovimientoNegocio_Imp();

                        //cuenta origen
                        movimiento.setNumero_Cuenta(cufin.getNumero_Cuenta());

                        movimiento.setFechaMovimiento(dtf.format(now).toString());
                        movimiento.setDetalleConcepto("Pago Cuota restantes: " + cux.getCuotas_restantes());
                        movimiento.setImporteMovimiento(-(cux.getImporte_cuota()));
                        movimiento.setTipoMovimiento("Debito");
                        movimientoN.insert(movimiento);

                    }
                    if(cux.getCuotas_restantes() > 1 ){
                        // genera la proxima cuota
                        pago.setIdPago(cux.getIdPago());
                        pago.setNumero_Cuenta(cux.getNumero_Cuenta());
                        pago.setFecha_Pago(null);
                        pago.setImporte_cuota(cux.getImporte_cuota());
                        pago.setImporte_restante(cux.getImporte_restante() - cux.getImporte_cuota());
                        pago.setCuotas_restantes(cux.getCuotas_restantes() - 1);
                        pago.setIdPrestamo(cux.getIdPrestamo());
                        pagoT.insert(pago);

                    }
                } else
                    session.setAttribute("error", "No tiene saldo");
            } else
                session.setAttribute("error", "No se pudo realizar el pago");

            session.setAttribute("prestamoXU", null);


        }


        request.getRequestDispatcher("/client/PagarPrestamos.jsp").

                forward(request, response);
    }

}
