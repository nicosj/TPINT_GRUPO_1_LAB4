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

//import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Negocio_Implementacion.Cuenta_NegocioImp;


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
		
		Cuenta_NegocioImp cuenta = new Cuenta_NegocioImp();
		ArrayList<Cuenta> cuentas = cuenta.listarCuentas();
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
		String idCuenta = request.getParameter("numero_Cuenta");
		if(request.getParameter("btnTraerid")!=null) {
			try {
            	Cuenta cuenta2= new Cuenta();
            	Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
            	cuenta2= cuentaN.obtenerCuenta(idCuenta);
            	request.setAttribute("cuenta", cuenta2);
            	//request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
			} catch (Exception e) {
				
				// TODO: handle exception
			}
		}
	if(request.getParameter("btnEdit")!=null){
		cuenta.setIdCliente(request.getParameter("idCliente"));
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		cuenta.setFecha_Creacion(request.getParameter("FechaCreacion"));
		cuenta.setTipo_Cuenta(request.getParameter("TipoCuenta"));
		cuenta.setCBU(request.getParameter("CBU"));
		cuenta.setSaldo(Double.parseDouble(request.getParameter("Saldo")));
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		cuenta.setEstado(Boolean.parseBoolean(request.getParameter("Estado")));
			
		

            System.out.println("Servlet");
            System.out.println(cuenta);
            Cuenta_NegocioImp accNegocio = new Cuenta_NegocioImp();
            int cuentaCount = accNegocio.getCuentaCountByClientId(request.getParameter("idCliente"));
            try{
            	
                
                if (cuentaCount < 3) {
            	
            	Cuenta cuenta2= new Cuenta();
            	cuenta2= accNegocio.obtenerCuenta(idCuenta);
                boolean filas= accNegocio.update(cuenta);
                request.setAttribute("filas", filas);
                request.setAttribute("updateSuccess", true);                               
                }
                else {
                	System.out.println("uwu");
                	 request.setAttribute("errorMessage", "La cuenta no puede asociarse al nuevo cliente, ya posee 3 cuentas asociadas.");
                }
                ArrayList<Cuenta> cuentas = accNegocio.listarCuentas();
        		request.setAttribute("cuentas", cuentas);
                request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	
	}
}
