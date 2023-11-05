package servlets;


import dominio.Cuenta;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_Implement.CuentaDao_Implement;

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
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		
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
		if(request.getParameter("btnCrearCuenta")!=null){

			System.out.println("Servletpost");
			String numero_Cuenta = request.getParameter("numero_Cuenta");
			String idCliente= request.getParameter("idCliente");
			String TipoCuenta = request.getParameter("tipoCuenta");
			String CBU = request.getParameter("cbu");
			Double saldo = Double.parseDouble(request.getParameter("saldo"));
			String FechaCreacion= LocalDate.now().toString();
			Boolean Estado = true;

			
			
			//** --- Tener cuidado de no intentar leer las propiedades con un tipo de dato diferente al de la DB
			//** ---- o saltan las excepciones. Saldo en la DB es decimal
	
			
			
            Cuenta cuenta = new Cuenta( numero_Cuenta, idCliente, TipoCuenta, FechaCreacion,CBU, saldo, Estado);
            
            System.out.println(cuenta);
            CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
            try {
                boolean rowsUpdated = cuentaDao.update(cuenta); // Implement the update method in your DAO
                request.setAttribute("rowsUpdated", rowsUpdated);
            	request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

}