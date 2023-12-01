package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cliente_NegocioImp;
import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;

/**
 * Servlet implementation class BajaClienteServlet
 */
@WebServlet("/admin/BajaClienteServlet")
public class BajaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BajaClienteServlet() {
		super();

	}

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnMostrarClientes") != null) {
			ClienteDao_Implement cli = new ClienteDao_Implement();
			ArrayList<Cliente> listaCli = cli.readAllActivos();

			request.setAttribute("listaC", listaCli);


			RequestDispatcher re = request.getRequestDispatcher("/adm_BajaCliente.jsp");
			re.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnEliminarCliente") != null) {
			int id = Integer.parseInt(request.getParameter("idCliente").toString());
			Cliente_NegocioImp cli = new Cliente_NegocioImp();
			cli.bajaLogicaCliente(id);
			request.setAttribute("filas", true);
			request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);


		}
	}

}
