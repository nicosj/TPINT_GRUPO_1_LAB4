package servlets;

import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dominio.Usuario;
/**
 * Servlet implementation class TransferenciasServlet
 */
@WebServlet("/client/TransferenciasServlet")
public class TransferenciasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferenciasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		session = request.getSession();
		Usuario client = (Usuario)session.getAttribute("client");
		if (client == null) {
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
		}

		session.setAttribute("clientTrans", client);

		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAllByID(client.getIdCliente());
		
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("stepOrigen", "1");
		session.setAttribute("stepDestino", "0");
		session.setAttribute("stepTrans", "0");
		session.setAttribute("cbushow", "0");
		session.setAttribute("cuentaOrigen",null);
		session.setAttribute("cuentaDestino",null);
		session.setAttribute("cuentatrans",null);
		session.setAttribute("clientetrans",null);
		session.setAttribute("error",null);
		request.getRequestDispatcher("/client/Transferencias.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session ;
		session = request.getSession();
		if(request.getParameter("pasoUno") != null ) {
			System.out.println("Transferir paso 1");
			String cuentaOrigen = request.getParameter("cuentasel");
			ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>)session.getAttribute("cuentas");
			ArrayList<Cuenta> filtra = new ArrayList<Cuenta>();
			for (Cuenta cuenta : cuentas) {
				if (!Objects.equals(cuenta.getNumero_Cuenta(), cuentaOrigen)) {
					filtra.add(cuenta);
				}
			}
			session.setAttribute("cbushow", "0");
			Cuenta cuentaO = new Cuenta();
			CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
			cuentaO = cuentaDao.obtenerCuenta(cuentaOrigen);
			session.setAttribute("cuentaOrigen", cuentaO);
			session.setAttribute("stepOrigen", "0");
			session.setAttribute("stepDestino", "1");
			session.setAttribute("stepTrans", "0");
			session.setAttribute("cuentasFiltrada", filtra);
			

		}
		else if(request.getParameter("valida") != null ) {
			System.out.println("valida");
			String cbu = request.getParameter("cbu");
			Cuenta cuenta = new Cuenta();
			CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
			cuenta = cuentaDao.obtenerCuentaCbu(cbu);
			if(cuenta != null) {
				System.out.println("Cuenta encontrada");
				Cliente cliente = new Cliente();
				ClienteDao_Implement clienteDao = new ClienteDao_Implement();
				cliente = clienteDao.obtenerCliente(Integer.parseInt(cuenta.getIdCliente()));
				System.out.println("Cliente encontrada"+cliente.getNombreCompleto());
				session.setAttribute("clientetrans", cliente);
				session.setAttribute("cuentatrans", cuenta);

				session.setAttribute("cbushow", "1");
				session.setAttribute("stepOrigen", "0");
				session.setAttribute("stepDestino", "1");
				session.setAttribute("stepTrans", "0");

			} else {
				request.setAttribute("errorMessage", "Cliente no existente");
			}
		}
		else if(request.getParameter("pasoCbu") != null ) {
			System.out.println("Transferir paso 2");
			String cbus = request.getParameter("cbus");
			System.out.println(cbus+" cebu paso");
			if (cbus != null) {
				System.out.println("CBU");
				Cuenta cuentat = new Cuenta();
				CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
				cuentat = cuentaDao.obtenerCuenta(cbus);

				if(cuentat != null) {
					session.setAttribute("cuentaDestino", cuentat);
					session.setAttribute("stepOrigen", "0");
					session.setAttribute("stepDestino", "0");
					session.setAttribute("cbushow", "0");
					session.setAttribute("stepTrans", "1");

				} else {
					request.setAttribute("errorMessage", "CBU no encontrado");
				}

			}
		}
		else if(request.getParameter("pasoDos") != null ) {
			System.out.println("Transferir paso 2");

			String entreCuentas = request.getParameter("entreCuentas");
			String historial = request.getParameter("historial");

		


			if(entreCuentas != null) {
				System.out.println("Entre cuentas");
				Cuenta cuentax = new Cuenta();
				CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
				cuentax = cuentaDao.obtenerCuenta(entreCuentas);
				session.setAttribute("cuentaDestino", cuentax);
				session.setAttribute("stepOrigen", "0");
				session.setAttribute("stepDestino", "0");
				session.setAttribute("cbushow", "0");
				session.setAttribute("stepTrans", "1");


			} else if (historial != null){

				System.out.println("Historial");
			}


		}
		else if(request.getParameter("pasoTres") != null ) {
			System.out.println("Transferir paso 3");

		}
		request.getRequestDispatcher("/client/Transferencias.jsp").forward(request, response);

	}

}
