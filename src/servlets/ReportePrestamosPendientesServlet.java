package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Prestamo_NegocioImp;
import dominio.Prestamo;

@WebServlet("/admin/ReportePrestamosPendientesServlet")
public class ReportePrestamosPendientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReportePrestamosPendientesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAceptar")!=null) {
			if(request.getParameter("txtNumber")!=null) {
				double montoMax = Double.parseDouble(request.getParameter("txtNumber"));
				Prestamo_NegocioImp negocio = new Prestamo_NegocioImp();
				ArrayList <Prestamo> listado = negocio.listarPrestamosFiltrado(montoMax);
				request.setAttribute("listadoPrestamos", listado);
				request.getRequestDispatcher("/admin/ReportePrestamosPendientes.jsp").forward(request, response);				
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
