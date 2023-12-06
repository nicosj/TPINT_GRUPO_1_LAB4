package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio_Implementacion.*;
import dao_Implement.ClienteDao_Implement;
import dao_Implement.CuentaDao_Implement;

import dao_Implement.PrestamoDao_Implement;
import dominio.*;

/**
 * Servlet implementation class AprobarPrestamosServlet
 */
@WebServlet("/admin/AprobarPrestamosServlet")
public class AprobarPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AprobarPrestamosServlet() {
        super();

    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session;
        session = request.getSession();
		
		ClienteDao_Implement clienteDao = new ClienteDao_Implement();
		ArrayList<Cliente> clientes = clienteDao.readAll();
		
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> prestamos = prestamoDao.readAll();
		
		session.setAttribute("clientes", clientes);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("prestamos", prestamos);
	
		request.getRequestDispatcher("/admin/AprobarPrestamos.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session;
		session = request.getSession();
		String idPrestamo = request.getParameter("idPrestamo");
		String estadoPrestamo = request.getParameter("estadoPrestamo");
		
		System.out.println("idPrestamo: " + idPrestamo);

		System.out.println("estadoPrestamo: " + estadoPrestamo);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		

		if (idPrestamo != null) {
		    if (request.getParameter("aprobarPrestamo") != null) {
		        // Acciones para aprobar el pr�stamo
		        if ("0".equals(estadoPrestamo)) {
		            Prestamo_NegocioImp prestamoN = new Prestamo_NegocioImp();
		            prestamoN.aprobarPrestamo(Integer.parseInt(idPrestamo));
					ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>)session.getAttribute("prestamos");
					/* Actualiza el credito a cuenta*/
					Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
					cuentaN.ajusteCuenta(prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getCuenta().getNumero_Cuenta(), prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getTotalImporte());
					/* Genera el movimiento */
					Movimiento movimiento = new Movimiento();
					Cuenta cuenta = new Cuenta();
					cuenta.setNumero_Cuenta((prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo))).findFirst().get().getCuenta().getNumero_Cuenta());
					MovimientoNegocio_Imp movimientoN = new MovimientoNegocio_Imp();
					Prestamo prestamoAPago = new Prestamo();
					prestamoAPago = (prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo))).findFirst().get();
				
					
					movimiento.setCuenta(cuenta);
					
					movimiento.setFechaMovimiento(dtf.format(now).toString());
					movimiento.setDetalleConcepto("Prestamo Nro: " + idPrestamo);
					movimiento.setImporteMovimiento((prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getTotalImporte()));
					movimiento.setTipoMovimiento("Credito");
					movimientoN.insert(movimiento);
					/* Actualiza el prestamo */
					PagoPrestamo_NegocioImp pagoN = new PagoPrestamo_NegocioImp();
					PagoPrestamo pago = new PagoPrestamo();
					//pago.setNumero_Cuenta(prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getNumero_Cuenta());
					pago.setCuenta(cuenta);
				
					pago.setFecha_Pago(null);
					pago.setImporte_cuota(prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getImporteCuota());
					pago.setImporte_restante(prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getTotalImporte());
					pago.setCuotas_restantes(prestamos.stream().filter(p -> p.getIdPrestamo() == Integer.parseInt(idPrestamo)).findFirst().get().getCuotas());
					pago.setPrestamo(prestamoAPago);
					pagoN.insert(pago);//aca no habra que llamar al negocio?

					//Asi estan las columnas tal cual en la base de datos
					// idpago_prestamo numero_Cuenta Fecha_Pago Importe_Cuota Impote_Restante Cuotas_Restantes idPrestamo



				} else {
		            System.out.println("Nulidad al aprobar");
		        }

		    } else if (request.getParameter("rechazarPrestamo") != null) {
		        // Acciones para rechazar el pr�stamo
		        if ("0".equals(estadoPrestamo)) {
		        	Prestamo_NegocioImp prestamoN = new Prestamo_NegocioImp();
		            prestamoN.rechazarPrestamo(Integer.parseInt(idPrestamo));
		        } else {
		            System.out.println("Nulidad al rechazar");
		        }
		    }
		} else {
		    System.out.println("idPrestamo es nulo");
		}
		

		
		Cliente_NegocioImp clienteN = new Cliente_NegocioImp();
		ArrayList<Cliente> clientes = clienteN.listarClientes();
		
		
		Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
		//CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaN.listarCuentas();
		
		Prestamo_NegocioImp prestamoN = new Prestamo_NegocioImp(); 
		ArrayList<Prestamo> prestamos = prestamoN.readAll();
		
		System.out.println(prestamos + "Prestamosss");
		System.out.println(clientes + "Clientesss");
		System.out.println(cuentas + "Cuentasss");
		
		session.setAttribute("clientes", clientes);
		session.setAttribute("cuentas", cuentas);
		session.setAttribute("prestamos", prestamos);
		

		

		// Redirige a la p�gina adecuada despues de procesar el formulario

		response.sendRedirect(request.getContextPath() + "/admin/AprobarPrestamos.jsp?resultado=exito");
		}
	}
