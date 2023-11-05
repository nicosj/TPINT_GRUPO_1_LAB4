package servlets;


import dominio.Cuenta;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Negocio_Implementacion.Cuenta_NegocioImp;
import dao_Implement.CuentaDao_Implement;

/**
 * Servlet implementation class EditCuentaServlet
 */
@WebServlet("/admin/EditCuentaServlet")
public class EditCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * Default constructor. 
     */
    public EditCuentaServlet() {
        // TODO Auto-generated constructor stub
    }

	Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
	
	ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
		ArrayList<Cuenta> cuentas = cuentaDao.readAll();
		request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Cuenta cuenta = new Cuenta();
		int idCuenta = Integer.parseInt(request.getParameter("numero_Cuenta"));
		if(request.getParameter("btnTraerid")!=null) {
			try {
            	Cuenta cuenta2= new Cuenta();
            	CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
            	cuenta2= cuentaDao.obtenerCuenta(idCuenta);
            	request.setAttribute("cuenta", cuenta2);
            	 request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
			} catch (Exception e) {
				
				// TODO: handle exception
			}
		}
	if(request.getParameter("btnEdit")!=null){
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		cuenta.setFecha_Creacion(request.getParameter("FechaCreacion"));
		cuenta.setTipo_Cuenta(request.getParameter("TipoCuenta"));
		cuenta.setCBU(request.getParameter("CBU"));
		cuenta.setSaldo(Double.parseDouble(request.getParameter("Saldo")));
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		cuenta.setEstado(Boolean.parseBoolean(request.getParameter("Estado")));
			
		

            System.out.println("Servlet");
            System.out.println(cuenta);
            CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
            try{
            	Cuenta cuenta2= new Cuenta();
            	cuenta2= cuentaDao.obtenerCuenta(idCuenta);
                boolean filas= cuentaDao.update(cuenta);
                request.setAttribute("filas", filas);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	
	}
}
