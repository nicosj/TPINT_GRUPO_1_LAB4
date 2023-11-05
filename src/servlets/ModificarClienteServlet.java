package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;

/**
 * Servlet implementation class ModificarClienteServlet
 */
@WebServlet("/admin/ModificarClienteServlet")
public class ModificarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ModificarClienteServlet() {
        super();
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificar")!=null){
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
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



            Cliente cliente = new Cliente(idCliente, dni, cuil,  nombre, apellido,  sexo,  nacionalidad,  fechaNacimiento, direccion, localidad, provincia,  correo,  telefono, estado);

            System.out.println("Servlet");
            System.out.println(cliente);
            ClienteDao_Implement clienteDao = new ClienteDao_Implement();
            
            
            try{
                boolean filas= clienteDao.update(cliente);
                request.setAttribute("filas", filas);
                request.setAttribute("crud", "mod");
                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

}