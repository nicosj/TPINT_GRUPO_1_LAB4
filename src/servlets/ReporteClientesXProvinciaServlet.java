package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cliente_NegocioImp;
import dominio.Cliente;

/**
 * Servlet implementation class ReporteCXProvincia
 */
@WebServlet("/admin/ReporteClienteXProvinciaServlet")
public class ReporteClientesXProvinciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReporteClientesXProvinciaServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente_NegocioImp negocio = new Cliente_NegocioImp();
		ArrayList<Cliente> clientesFiltrado = new ArrayList<Cliente>();
		int cantidadClientes = 0;
		
		if(request.getParameter("btnAceptar")!=null) {
				String genero = request.getParameter("radioGenero");
				String provincia = request.getParameter("selectProvincias");
				if(genero.equalsIgnoreCase("M")) {
					clientesFiltrado= negocio.listarClientesMasc(provincia);
				
				} else if(genero.equalsIgnoreCase("F")) {
					clientesFiltrado=negocio.listarClientesFem(provincia);
				}
				else if(genero.equalsIgnoreCase("ALL")) {
					clientesFiltrado=negocio.listarAllGeneros(provincia);
				}
				cantidadClientes = clientesFiltrado.size();
				request.setAttribute("listadoClientes", clientesFiltrado);
				request.setAttribute("cantClientes", cantidadClientes);
				request.getRequestDispatcher("/admin/ReporteClienteXProvincia.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/admin/ReporteClienteXProvincia.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
