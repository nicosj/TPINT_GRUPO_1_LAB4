package servlets;


import dominio.Cliente;
import dominio.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_Implement.ClienteDao_Implement;
import dao_Implement.UsuarioDao_Implement;

/**
 * Servlet implementation class AltaClienteServlet
 */
@WebServlet("/admin/altaClienteServlet")
public class altaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public altaClienteServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteDao_Implement clienteDao = new ClienteDao_Implement();
		UsuarioDao_Implement usuarioDao = new UsuarioDao_Implement();
		ArrayList<Cliente> clientes = clienteDao.readAll();
		ArrayList<Usuario> usuarios = usuarioDao.readAll();
		request.setAttribute("clientes", clientes);
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);

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



            Cliente cliente = new Cliente(0, dni, cuil,  nombre, apellido,  sexo,  nacionalidad,  fechaNacimiento, direccion, localidad, provincia,  correo,  telefono, estado);
            System.out.println("Servlet");
            System.out.println(cliente);
            ClienteDao_Implement clienteDao = new ClienteDao_Implement();
            UsuarioDao_Implement usuarioDao = new UsuarioDao_Implement();
            try{
                int idCliente= clienteDao.insert(cliente);
                Usuario us = new Usuario(0,usuario, contrasena,2, idCliente);
                usuarioDao.insert(us);
                request.setAttribute("filas", true);
                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

}
