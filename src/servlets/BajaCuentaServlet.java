package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/BajaCuentaServlet")

public class BajaCuentaServlet extends HttpServlet {
	
    public BajaCuentaServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		  Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
		  ArrayList<Cuenta> listaCuentas = negocio.listarCuentas(); 
		  request.setAttribute("cuentas", listaCuentas);
		  request.getRequestDispatcher("BajaCuenta.jsp").forward(request, response);
		  for (Cuenta cuenta : listaCuentas) {
			    System.out.println("Cuenta: " + cuenta.toString());
			}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
