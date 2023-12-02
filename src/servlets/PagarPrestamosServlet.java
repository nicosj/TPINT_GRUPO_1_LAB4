package servlets;

import java.io.IOException;
import java.util.ArrayList;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.PagoPrestamo_NegocioImp;
import Negocio_Implementacion.Prestamo_NegocioImp;
// TODO import Negocio_Implementacion.PagoPrestamo_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;
import dao_Implement.PrestamoDao_Implement;
import dominio.*;
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

    Cliente_NegocioImp cliente = new Cliente_NegocioImp();
    ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
    ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    Prestamo_NegocioImp negocioImp = new Prestamo_NegocioImp();
    ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();

    // TODO Generar el negocio "PagoPrestamo_NegocionImp"
    //PagoPrestamo_NegocioImp pago = new PagoPrestamo_NegocioImp();
    ArrayList<PagoPrestamo> listaPagos = new ArrayList<PagoPrestamo>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        session = request.getSession();
        session.setAttribute("prestamoXU", null);
        Usuario clix=(Usuario)session.getAttribute("client");

        Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();

        ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerCuentaByClientId(clix.getIdCliente());
        PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
        ArrayList<Prestamo> prestamos =new ArrayList<>();
        for(Cuenta cuenta : cuentas) {
            prestamos.addAll(prestamoDao.readAllByCuenta(cuenta.getNumero_Cuenta()));
        }
        session.setAttribute("prestamos", prestamos);
        System.out.println(prestamos+"prestamos");
        request.getRequestDispatcher("/client/PagarPrestamos.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session;
        session = request.getSession();
        Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();
        Usuario clix=(Usuario)session.getAttribute("client");
        ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerCuentaByClientId(clix.getIdCliente());
        PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
        ArrayList<Prestamo> prestamos =new ArrayList<>();
        for(Cuenta cuenta : cuentas) {
            prestamos.addAll(prestamoDao.readAllByCuenta(cuenta.getNumero_Cuenta()));
        }
        session.setAttribute("prestamos", prestamos);
        /*Prestmo cuotas*/
        String estadoPrestamo = request.getParameter("estadoPrestamo");
        String idPago = request.getParameter("idPago");

        if(request.getParameter("pagarCuota")!=null){

            ArrayList<PagoPrestamo> filtrado=new ArrayList<PagoPrestamo>();
            /*trae datos*/
            PagoPrestamo_NegocioImp pagoT = new PagoPrestamo_NegocioImp();
            ArrayList<PagoPrestamo> pprestamo=pagoT.readAll();

            for(PagoPrestamo prest : pprestamo)
            {
                System.out.println("prest"+prest);
                if (request.getParameter("idPrestamo").equals(String.valueOf(prest.getIdPrestamo()))){
                //if(request.getParameter("idPrestamo")==String.valueOf(prest.getIdPrestamo())){
                    PagoPrestamo pago = new PagoPrestamo();
                    pago.setNumero_Cuenta(prest.getNumero_Cuenta());
                    pago.setFecha_Pago("2020-10-10");
                    pago.setImporte_cuota(prest.getImporte_cuota());
                    pago.setImporte_restante(prest.getImporte_restante());
                    pago.setCuotas_restantes(prest.getCuotas_restantes());
                    pago.setIdPrestamo(prest.getIdPrestamo());
                    filtrado.add(pago);
                }
            }
            session.setAttribute("prestamoXU", filtrado);

        }
        /*System.out.println("idPrestamo: " + idPrestamo);
        System.out.println("estadoPrestamo: " + estadoPrestamo);
        System.out.println("idPago: " + idPago);*/


        request.getRequestDispatcher("/client/PagarPrestamos.jsp").forward(request, response);
    }

}
