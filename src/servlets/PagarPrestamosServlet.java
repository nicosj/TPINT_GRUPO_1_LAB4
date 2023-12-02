package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.Prestamo_NegocioImp;
// TODO import Negocio_Implementacion.PagoPrestamo_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;
import dao_Implement.PrestamoDao_Implement;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.PagoPrestamo;
import dominio.Prestamo;

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
	    
		ClienteDao_Implement clienteDao = new ClienteDao_Implement();
		ArrayList<Cliente> clientes = clienteDao.readAll();
		
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> prestamos = prestamoDao.readAll();
		
		//TODO 
	    //PagoPrestamo_NegocioImp pagoNegocio = new PagoPrestamo_NegocioImp();
	    //ArrayList<PagoPrestamo> pagos = pagoNegocio.readAll();
		
		System.out.println(prestamos + "Prestamosss");
		System.out.println(clientes + "Clientesss");
		System.out.println(cuentas + "Cuentasss");
		//TODO
		//System.out.println(pagos + "Pagosss");
	    
		session.setAttribute("clientes", clientes);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("prestamos", prestamos);
		//TODO
		//session.setAttribute("pagos", pagos);
		
		request.getRequestDispatcher("/client/PagarPrestamosServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPrestamo = request.getParameter("idPrestamo");
		String estadoPrestamo = request.getParameter("estadoPrestamo");
		String idPago =request.getParameter("idPago");
		
		System.out.println("idPrestamo: " + idPrestamo);
		System.out.println("estadoPrestamo: " + estadoPrestamo);
		System.out.println("idPago: " + idPago);
		
		
		
		
		
		request.getRequestDispatcher("/client/PagoPrestamos.jsp").forward(request, response);
	}

}
