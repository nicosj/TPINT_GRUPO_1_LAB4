package servlets;

import java.io.IOException;
import java.util.List;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao_Implement.CuentaDao_Implement;
import dao_Implement.InteresesDao_Implement;
import dominio.Cuenta;
import dominio.Intereses;
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
		
		System.out.println(intereses.get(0).getPorcentaje());
		request.setAttribute("intereses", intereses);
		request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("/client/SolicitudPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
