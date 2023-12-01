package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.Prestamo_NegocioImp;
import dominio.Cuenta;
import dominio.Intereses;
import dominio.Movimiento;
import dominio.Prestamo;
import dominio.Usuario;

/**
 * Servlet implementation class SolicitudPrestamo
 */
@WebServlet("/client/SolicitudPrestamoServlet")
public class SolicitudPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idClient = ((Usuario)session.getAttribute("client")).getIdCliente();
	
		Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
		List<Cuenta> cuentas = cuentaN.obtenerCuentaByClientId(idClient);
		
		System.out.println(cuentas.get(0).getNumero_Cuenta());
		request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("/client/SolicitudPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Llego solicitud de prestamo");
		try {
			String cuenta = request.getParameter("cuenta");
			int cuotas = Integer.parseInt(request.getParameter("cantCuotas"));
			double intereses = Double.parseDouble(request.getParameter("interes"));
			double monto = Double.parseDouble(request.getParameter("monto"));
			double importCuota = (monto + (monto*intereses)) / cuotas;
			int cantCuotas = Integer.parseInt(request.getParameter("cantCuotas"));
			
			Prestamo prestamo = new Prestamo();
			Prestamo_NegocioImp prestamoN = new Prestamo_NegocioImp();
			
			LocalDate fechaHoy = LocalDate.now();
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String fechaFormateada = fechaHoy.format(formato);
	        
			prestamo.setNumero_Cuenta(cuenta);
			prestamo.setTotalImporte(monto);
			prestamo.setImporteCuota(importCuota);
			prestamo.setFechaPedido(fechaFormateada);
			prestamo.setCuotas(cantCuotas);
			prestamo.setEstado(1);
			
			if(prestamoN.insert(prestamo)) {
				System.out.println("Solicitud insertada con exito UWU");
			}
			
			request.getRequestDispatcher("/client").forward(request, response);
		}catch(Exception ex) {
			
		}
		
		
	}

}
