package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Usuario_NegocioImp;
import dominio.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session;
		Usuario usuario = new Usuario();
		String usua = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		Usuario_NegocioImp usuarioN = new Usuario_NegocioImp();
		
		usuario = usuarioN.login(new Usuario(usua, clave));
		
		if (usuario.getUsuario() != null) {
			session = request.getSession();


			if (usuario.getTipoUsuario() == 2) {
				session.setAttribute("client", usuario);
				request.getRequestDispatcher("/client").forward(request, response);
			} else {
				session.setAttribute("admin", usuario);
				request.getRequestDispatcher("/admin").forward(request, response);
			}

		}else {
			RequestDispatcher dr = request.getRequestDispatcher("/index.jsp");
			dr.forward(request, response);
		}
	}

}
