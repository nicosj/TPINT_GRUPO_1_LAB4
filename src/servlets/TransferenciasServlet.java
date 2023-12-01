package servlets;


import dao_Implement.CuentaDao_Implement;

import dominio.*;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Cuenta_NegocioImp;
import Negocio_Implementacion.MovimientoNegocio_Imp;
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
		try {
		Usuario client = (Usuario)session.getAttribute("client");
		if (client == null) {
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
		}

		session.setAttribute("clientTrans", client);

		Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
		ArrayList<Cuenta> cuentas = cuentaN.readAllByID(client.getIdCliente());
		/*Last version*/
		session.setAttribute("pasos", 0);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("cbushow", "0");
		session.setAttribute("cuentaOrigen",null);
		session.setAttribute("cuentaDestino",null);
		session.setAttribute("cuentatrans",null);
		session.setAttribute("clientetrans",null);
		session.setAttribute("error",null);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
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
			session.setAttribute("pasos", 1);
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
			Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
			cuentaO = cuentaN.obtenerCuenta(cuentaOrigen);
			session.setAttribute("cuentaOrigen", cuentaO);
			session.setAttribute("cuentasFiltrada", filtra);
			

		}
		else
			if(request.getParameter("valida") != null ) {

			System.out.println("valida");
			String cbu = request.getParameter("cbu");
			Cuenta cuenta = new Cuenta();

			Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();			
			cuenta = cuentaN.obtenerCuentaCbu(cbu);
			System.out.println(cuenta + " verrrrrrrrrrrr");
			if(cuenta.getNumero_Cuenta() != null) {
				System.out.println("Cuenta encontrada");
				Cliente cliente = new Cliente();
				Cliente_NegocioImp clienteN = new Cliente_NegocioImp();	
				cliente = clienteN.obtenerCliente(Integer.parseInt(cuenta.getIdCliente()));
				System.out.println("Cliente encontrada"+cliente.getNombreCompleto());
				session.setAttribute("clientetrans", cliente);
				session.setAttribute("cuentatrans", cuenta);

				session.setAttribute("cbushow", "1");

				session.setAttribute("error", null);

			} else {
				session.setAttribute("cbushow", "1");

				session.setAttribute("error", "Cliente no existente");
			}
		}
		else
			if(request.getParameter("pasoCbu") != null ) {

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

					session.setAttribute("cbushow", "0");

					session.setAttribute("pasos", 2);
				} else {
					request.setAttribute("error", "CBU no encontrado");
				}

			}
		}
		else if(request.getParameter("pasoDos") != null ) {

			System.out.println("Transferir paso 2");

			String entreCuentas = request.getParameter("entreCuentas");
			if(entreCuentas != null) {
				System.out.println("Entre cuentas");
				Cuenta cuentax = new Cuenta();		
				Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();	
				cuentax = cuentaN.obtenerCuenta(entreCuentas);
				session.setAttribute("cuentaDestino", cuentax);

				session.setAttribute("cbushow", "0");

				session.setAttribute("pasos", 2);

			}


		}
		else if(request.getParameter("pasoTres") != null ) {

			System.out.println("Paso 3");
			System.out.println(request.getParameter("valorD") +" valor test");
			if(request.getParameter("valorD")!=null) {
				String valor = request.getParameter("valorD");
				System.out.println(valor+" valor");
				if(((Cuenta)session.getAttribute("cuentaOrigen")).getSaldo()>=Double.parseDouble(valor)){

					session.setAttribute("cbushow", "0");

					session.setAttribute("pasos", 3);
					CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
					Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();	
					
					if(cuentaN.ajusteCuenta(((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta(),-(Double.parseDouble(valor)))){
						

						if(cuentaN.ajusteCuenta(((Cuenta)session.getAttribute("cuentaDestino")).getNumero_Cuenta(),(Double.parseDouble(valor)))){
						
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
							LocalDateTime now = LocalDateTime.now();
							Movimiento movimiento = new Movimiento();
							MovimientoNegocio_Imp movimientoN = new MovimientoNegocio_Imp();
							
							//cuenta origen
							movimiento.setNumero_Cuenta(((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta());
							movimiento.setFechaMovimiento(dtf.format(now).toString());
							movimiento.setDetalleConcepto("Transferencia");
							movimiento.setImporteMovimiento(-(Double.parseDouble(valor)));
							movimiento.setTipoMovimiento("Debito");
							movimientoN.insert(movimiento);

							//Destino

							movimiento.setNumero_Cuenta(((Cuenta)session.getAttribute("cuentaDestino")).getNumero_Cuenta());
							movimiento.setFechaMovimiento(dtf.format(now).toString());
							movimiento.setDetalleConcepto("Transferencia");
							movimiento.setImporteMovimiento(Double.parseDouble(valor));
							movimiento.setTipoMovimiento("Credito");

							movimientoN.insert(movimiento);


							session.setAttribute("error", null);
						}else{
							cuentaDao.ajusteCuenta(((Cuenta)session.getAttribute("cuentaOrigen")).getNumero_Cuenta(),(Double.parseDouble(valor)));
							session.setAttribute("error", "Error al transferir");
						}
					}
				}else{
					session.setAttribute("pasos", 2);

					session.setAttribute("cbushow", "0");

					session.setAttribute("error", "Saldo insuficiente");
				}

			}

		}
		request.getRequestDispatcher("/client/Transferencias.jsp").forward(request, response);

	}

}
