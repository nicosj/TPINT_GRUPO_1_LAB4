package servlets;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cuenta_NegocioImp;
import dominio.Cuenta;

/**
 * Servlet implementation class BajaCuentaServlet
 */
@WebServlet("/admin/BajaCuentaServlet")

public class BajaCuentaServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
	
	ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

	public BajaCuentaServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		listaCuentas = negocio.listarCuentas();
		request.setAttribute("cuentas", listaCuentas);
<<<<<<< HEAD
		RequestDispatcher rd = request.getRequestDispatcher("/admin/BajaCuentaCliente.jsp");
=======
		RequestDispatcher rd = request.getRequestDispatcher("/BajaCuentaCliente.jsp");
>>>>>>> 65248cfff45046c31b5893a78d36f9c763aff0f1
		rd.forward(request, response);
		  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  
		 if(request.getParameter("btnBajaCuenta")!=null) {
<<<<<<< HEAD

		     listaCuentas = negocio.listarCuentas();  
			 request.setAttribute("cuentas", listaCuentas);
			 RequestDispatcher rda = request.getRequestDispatcher("/admin/BajaCuentaCliente.jsp");
			 rda.forward(request, response);
=======
			 int numeroCuenta= Integer.parseInt(request.getParameter("numCuenta").toString());
			 boolean bajo= negocio.bajaCuenta(numeroCuenta);
			 if(bajo) {
				 System.out.println("Baja exitosa");
			 }
			 
>>>>>>> 65248cfff45046c31b5893a78d36f9c763aff0f1
		 }
		 listaCuentas = negocio.listarCuentas();  
		 request.setAttribute("cuentas", listaCuentas);
		 RequestDispatcher rda = request.getRequestDispatcher("BajaCuentaCliente.jsp");
		 rda.forward(request, response);
		
	}

}
