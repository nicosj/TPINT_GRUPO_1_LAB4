package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Cliente_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;
import dominio.Usuario;

/**
 * Servlet implementation class MisDatos
 */
@WebServlet("/client/MisDatos")
public class MisDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MisDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		session = request.getSession();
		int idCliente = ((Usuario)session.getAttribute("client")).getIdCliente();
		Cliente cliente = new Cliente();
		ClienteDao_Implement clienteDaoImpl = new ClienteDao_Implement();
		
		
		cliente = clienteDaoImpl.obtenerCliente(idCliente);
		
		request.setAttribute("clientInformation", cliente);
		request.getRequestDispatcher("/client/personalInformation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
