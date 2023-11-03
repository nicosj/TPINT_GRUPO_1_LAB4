package servlets;


import dominio.Cuenta;


import java.io.IOException;

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
@WebServlet("/admin/altaCuentaServlet")
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
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		String cbu = Integer.toString(cuentaDao.getLastCBU()+1);
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
		if(request.getParameter("alta")!=null){

			String numero_Cuenta = request.getParameter("numero_Cuenta");
			String idCliente = request.getParameter("idCliente");
			String FechaCreacion = request.getParameter("FechaCreacion");
			String TipoCuenta = request.getParameter("TipoCuenta");
			String CBU = request.getParameter("CBU");
			String saldo = request.getParameter("Saldo");
			double saldoParseado = Double.parseDouble(saldo); 
			boolean estado = true; // *chequear, está agregado para que funcione el constructor


			//** --- Tener cuidado de no intentar leer las propiedades con un tipo de dato diferente al de la DB
			//** ---- o saltan las excepciones. Saldo en la DB es decimal
	
			
			
            Cuenta cuenta = new Cuenta( idCliente, FechaCreacion, TipoCuenta, CBU, numero_Cuenta, saldoParseado, estado);
            System.out.println("Servlet");
            System.out.println(cuenta);
            CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
            try{
                boolean filas= cuentaDao.insert(cuenta);
                request.setAttribute("filas", filas);
                request.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

}