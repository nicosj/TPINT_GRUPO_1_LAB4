package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_Implement.UsuarioDao_Implement;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		String usua = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		UsuarioDao_Implement usuarioDao = new UsuarioDao_Implement();
		
		usuario = usuarioDao.login(new Usuario(usua, clave));
		if(usuario.getUsuario() != null) {
			if(usuario.getTipoUsuario() == 2) {
				request.getRequestDispatcher("ListadoCuentasCliente.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("AltaCuenta.jsp").forward(request, response);
			}
		}
		
		request.getRequestDispatcher("Incio.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
