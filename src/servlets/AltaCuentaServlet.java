package servlets;



import dominio.Cliente;
import dominio.Cuenta;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Cuenta_NegocioImp;

/**
 * Servlet implementation class AltaCuentaServlet
 */

@WebServlet("/admin/AltaCuentaServlet")

public class AltaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AltaCuentaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ServletGet");
		
		Cuenta_NegocioImp cuenta = new Cuenta_NegocioImp();
		ArrayList<Cuenta> cuentas = cuenta.listarCuentas();
		
		String cbu = Cuenta.generarCBU();
		request.setAttribute("cuentas", cuentas);
		request.setAttribute("cbu", cbu);
		request.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(request, response);
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(request.getParameter("getCliente")!=null){
			Cliente cliente = new Cliente();
			
			Cliente_NegocioImp clienteN = new Cliente_NegocioImp();
			cliente= clienteN.obtenerClientePorDNI(request.getParameter("dni"));
			System.out.println("altacuenta");
			System.out.println(cliente);
			request.setAttribute("cliente", cliente);
			String cbu = Cuenta.generarCBU();
			request.setAttribute("cbu", cbu);
			request.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(request, response);

		}
		if(request.getParameter("btnCrearCuenta")!=null){

			System.out.println("Servletpost");
			String numero_Cuenta = request.getParameter("numero_Cuenta");
			String idCliente= request.getParameter("idcliente");
			String TipoCuenta = request.getParameter("tipoCuenta");
			String CBU = request.getParameter("cbu");
			Double saldo = Double.parseDouble(request.getParameter("saldo"));
			String FechaCreacion= LocalDate.now().toString();
			Boolean Estado = true;

			 
			
			//** --- Tener cuidado de no intentar leer las propiedades con un tipo de dato diferente al de la DB
			//** ---- o saltan las excepciones. Saldo en la DB es decimal
	
			
			
            Cuenta cuenta = new Cuenta(idCliente, Cuenta.generarCuenta() , TipoCuenta, FechaCreacion,CBU, saldo, Estado);
            
            System.out.println(cuenta);     
            Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
            
            
            int cuentaCount = cuentaN.getCuentaCountByClientId(request.getParameter("idcliente"));
            
            if (cuentaCount < 3) {
            try {
                boolean rowsUpdated = cuentaN.insert(cuenta); // Implement the update method in your DAO
                request.setAttribute("rowsUpdated", rowsUpdated);
                request.setAttribute("error", "Cuenta creada y asociada correctamente");
				request.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(request, response);

            	//request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		else {System.out.println("uwu");
            request.setAttribute("error", "El Cliente ya posee 3 cuentas asignadas. No puede asignar una nueva.");
            request.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(request, response);
            }
		}
	}
}
