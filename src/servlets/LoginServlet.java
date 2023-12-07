package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Prestamo_NegocioImp;
import Negocio_Implementacion.Usuario_NegocioImp;
import dominio.Prestamo;
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
		System.out.println(usuario.getUsuario());
		if (usuario.getUsuario() != null) {
			session = request.getSession();

			if (usuario.getTipoUsuario() == 2) {
				session.setAttribute("client", usuario);
				request.getRequestDispatcher("/client").forward(request, response);
				
			
			} else {
				session.setAttribute("admin", usuario);
				Prestamo_NegocioImp prestamoNegocio = new Prestamo_NegocioImp();
				ArrayList<Prestamo> prestamos =  prestamoNegocio.readAll();
				ArrayList<Integer> meses = new ArrayList<>();
				ArrayList<Integer> cantPrestamos = new ArrayList<>();
				
				System.out.println("hola");
				
				for(Prestamo prestamo : prestamos){
					meses.add(LocalDate.parse(prestamo.getFechaPedido(), DateTimeFormatter.ISO_LOCAL_DATE).getMonthValue());
					
				}
				Set<Integer> aux = new HashSet<>(meses);
				
				ArrayList<Integer> mesesSinRepetir = new ArrayList<>(aux);
				
				for(int i = 0; i < mesesSinRepetir.size(); i++) {
					cantPrestamos.add(0);
					
				}
				
				for(Prestamo prestamo : prestamos) {
					for(int i = 0; i < mesesSinRepetir.size(); i++) {
						if(LocalDate.parse(prestamo.getFechaPedido(), DateTimeFormatter.ISO_LOCAL_DATE).getMonthValue() == mesesSinRepetir.get(i)) {
							
							cantPrestamos.set(i, cantPrestamos.get(i) + 1);
						}
						
					}
					
				}
				session.setAttribute("graficosAdminMeses", mesesSinRepetir);
				session.setAttribute("graficosAdminCantPrestamos", cantPrestamos);
				request.getRequestDispatcher("/admin").forward(request, response);
			}

		}else {
			RequestDispatcher dr = request.getRequestDispatcher("/index.jsp");
			dr.forward(request, response);
		}
	}

}
