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
import dao_Implement.CuentaDao_Implement;
import dao_Implement.MovimientoDao;
import dominio.Cuenta;
import dominio.Movimiento;


@WebServlet("/HistorialMovimientos")
public class HistorialMovimientosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public HistorialMovimientosServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cuenta_NegocioImp negocio = new Cuenta_NegocioImp ();
        ArrayList<Cuenta> listadoCuentas = negocio.listarCuentas();
        request.setAttribute("listadoCuentas", listadoCuentas);
        request.getRequestDispatcher("/client/HistorialMovimientos.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String idCuenta = (request.getParameter("numero_Cuenta").toString());
		if(request.getParameter("btnSeleccionar")!=null) {
			try {
            	MovimientoNegocio_Imp negocioMov = new MovimientoNegocio_Imp();
            	ArrayList<Movimiento> listadoMovimientos = negocioMov.listarFiltrado(idCuenta);
            	request.setAttribute("listadoMovimientos", listadoMovimientos);
            	 request.getRequestDispatcher("/client/HistorialMovimientos.jsp").forward(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
