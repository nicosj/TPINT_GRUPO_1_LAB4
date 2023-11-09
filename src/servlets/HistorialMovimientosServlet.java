package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.MovimientoNegocio_Imp;
import dominio.Cuenta;
import dominio.Movimiento;

@WebServlet("/client/HistorialMovimientosServlet")
public class HistorialMovimientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HistorialMovimientosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
        ArrayList<Cuenta> listadoCuentas = negocio.listarCuentas();
        request.setAttribute("listadoCuentas", listadoCuentas);
        request.getRequestDispatcher("/client/HistorialMovimientos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
            String idCuenta = request.getParameter("numeroCuenta").toString();
            System.out.println(idCuenta);
            
            if (idCuenta != null) {
                try {
                    MovimientoNegocio_Imp negocioMov = new MovimientoNegocio_Imp();
                    ArrayList<Movimiento> listadoMovimientos = negocioMov.listarFiltrado(idCuenta);
                    
                    if (listadoMovimientos != null) {
                        request.setAttribute("listadoMovimientos", listadoMovimientos);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        request.getRequestDispatcher("/client/HistorialMovimientos.jsp").forward(request, response);
    }
}
