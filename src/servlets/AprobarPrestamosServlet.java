package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.Prestamo_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;
import dao_Implement.PrestamoDao_Implement;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Servlet implementation class AprobarPrestamosServlet
 */
@WebServlet("/admin/AprobarPrestamosServlet")
public class AprobarPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AprobarPrestamosServlet() {
        super();

    }
    Cliente_NegocioImp cliente = new Cliente_NegocioImp();
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    
    Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
	ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
	
    Prestamo_NegocioImp negocioImp = new Prestamo_NegocioImp();
    ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session;
        session = request.getSession();
		
		ClienteDao_Implement clienteDao = new ClienteDao_Implement();
		ArrayList<Cliente> clientes = clienteDao.readAll();
		
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> prestamos = prestamoDao.readAll();
		
		System.out.println(prestamos + "Prestamosss");
		System.out.println(clientes + "Clientesss");
		System.out.println(cuentas + "Cuentasss");
		
		session.setAttribute("clientes", clientes);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("prestamos", prestamos);
	
		request.getRequestDispatcher("/admin/AprobarPrestamos.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idPrestamo = request.getParameter("idPrestamo");
		String estadoPrestamo = request.getParameter("estadoPrestamo");
		
		System.out.println("idPrestamo: " + idPrestamo);
		System.out.println("estadoPrestamo: " + idPrestamo);
		
		if (idPrestamo != null) {
		    if (request.getParameter("aprobarPrestamo") != null) {
		        // Acciones para aprobar el préstamo
		        if ("0".equals(estadoPrestamo)) {
		            PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		            prestamoDao.aprobarPrestamo(Integer.parseInt(idPrestamo));
		        } else {
		            System.out.println("Nulidad al aprobar");
		        }

		    } else if (request.getParameter("rechazarPrestamo") != null) {
		        // Acciones para rechazar el préstamo
		        if ("1".equals(estadoPrestamo)) {
		            PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		            prestamoDao.rechazarPrestamo(Integer.parseInt(idPrestamo));
		        } else {
		            System.out.println("Nulidad al rechazar");
		        }
		    }
		} else {
		    System.out.println("idPrestamo es nulo");
		}
		
		HttpSession session;
        session = request.getSession();
		
		ClienteDao_Implement clienteDao = new ClienteDao_Implement();
		ArrayList<Cliente> clientes = clienteDao.readAll();
		
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> prestamos = prestamoDao.readAll();
		
		System.out.println(prestamos + "Prestamosss");
		System.out.println(clientes + "Clientesss");
		System.out.println(cuentas + "Cuentasss");
		
		session.setAttribute("clientes", clientes);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("prestamos", prestamos);
		

		// Redirige a la página adecuada después de procesar el formulario
		response.sendRedirect(request.getContextPath() + "/admin/AprobarPrestamos.jsp?resultado=exito");
		}
	}
