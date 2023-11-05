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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	if(request.getParameter("alta")!=null){

			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String sexo = request.getParameter("sexo");
			String nacionalidad = request.getParameter("nacionalidad");
			String fechaNacimiento = request.getParameter("fechaNacimiento");
			String direccion = request.getParameter("direccion");
			String localidad = request.getParameter("localidad");
			String provincia = request.getParameter("provincia");
			String correo = request.getParameter("correo");
			String telefono = request.getParameter("telefono");
			String usuario = request.getParameter("usuario");
			String contrasena = request.getParameter("contrasena");
			boolean estado = true;



            Cuenta cuenta = new Cuenta();

            System.out.println("Servlet");
            System.out.println(cuenta);
            CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
            try{
                boolean filas= cuentaDao.insert(cuenta);
                request.setAttribute("filas", filas);
                request.getRequestDispatcher("/admin/ListadoCuenta.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	
	}
}
