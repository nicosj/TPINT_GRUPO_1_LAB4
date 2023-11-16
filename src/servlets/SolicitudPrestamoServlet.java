package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao_Implement.CuentaDao_Implement;
import dao_Implement.InteresesDao_Implement;
import dao_Implement.PrestamoDao_Implement;
import dominio.Cuenta;
import dominio.Intereses;
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
		InteresesDao_Implement daoIntImplement = new InteresesDao_Implement();
		CuentaDao_Implement daoCuentaImplement = new CuentaDao_Implement();
		
		List<Intereses> intereses =  daoIntImplement.readAll();
		List<Cuenta> cuentas = daoCuentaImplement.obtenerCuentaByClientId(idClient);
		
		System.out.println(cuentas.get(0).getNumero_Cuenta());
		request.setAttribute("intereses", intereses);
		request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("/client/SolicitudPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Llego la peticion!!");
		System.out.println(request.getParameter("cuenta"));
		System.out.println(request.getParameter("cantCuotas"));
		System.out.println(request.getParameter("monto"));
		System.out.println(request.getParameter("interes"));
		int cuotas = Integer.parseInt(request.getParameter("cantCuotas"));
		double intereses = Double.parseDouble(request.getParameter("interes"));
		double monto = Double.parseDouble(request.getParameter("monto"));
		double importCuota = (monto + (monto*intereses)) / cuotas;
		
		Prestamo prestamo = new Prestamo();
		PrestamoDao_Implement prestamoDaoImpl = new PrestamoDao_Implement();
		Intereses interes = new Intereses(); 
		InteresesDao_Implement interesDaoImpl = new InteresesDao_Implement();
		
		interes = interesDaoImpl.obtenerInteresByCuota(Integer.parseInt(request.getParameter("cantCuotas")));
		
		LocalDate fechaHoy = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaHoy.format(formato);
		
		prestamo.setNumero_Cuenta(request.getParameter("cuenta"));
		prestamo.setTotalImporte(monto);
		prestamo.setImporteCuota(importCuota);
		prestamo.setFechaPedido(fechaFormateada);
		prestamo.setCuotas(Integer.parseInt(request.getParameter("cantCuotas")));
		prestamo.setIdIntereses(interes.getidInteres());
		prestamo.setEstado(1);
		
		prestamoDaoImpl.insert(prestamo);
		
		request.getRequestDispatcher("/client").forward(request, response);
		
	}

}
