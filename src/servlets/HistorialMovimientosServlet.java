package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.MovimientoNegocio_Imp;
import dominio.Cliente;
import dominio.Movimiento;

@WebServlet("/admin/HistorialMovimientosServlet")
public class HistorialMovimientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HistorialMovimientosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente_NegocioImp negocio = new Cliente_NegocioImp();
        ArrayList<Cliente> listadoClientes = negocio.listarClientes();
        request.setAttribute("listadoClientes", listadoClientes);

        String idCliente = request.getParameter("idCliente");

        if (idCliente != null && !idCliente.isEmpty()) {
            try {
                MovimientoNegocio_Imp negocioMov = new MovimientoNegocio_Imp();
                ArrayList<Movimiento> listadoMovimientos = negocioMov.listarFiltrado(idCliente);

                if (listadoMovimientos != null) {
                    request.setAttribute("listadoMovimientos", listadoMovimientos);
                    int cantidadMovimientos = listadoMovimientos.size();
                    request.setAttribute("cantMov", cantidadMovimientos);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/admin/HistorialMovimientos.jsp").forward(request, response);
 
    }
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    request.getRequestDispatcher("/admin/HistorialMovimientos.jsp").forward(request, response);
    }

}
