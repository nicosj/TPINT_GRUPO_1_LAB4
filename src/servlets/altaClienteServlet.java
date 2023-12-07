package servlets;


import dominio.Cliente;
import dominio.Usuario;
import excepciones.ExcepcionDni;
import excepciones.ExcepcionFecha;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio_Implementacion.Cliente_NegocioImp;
import Negocio_Implementacion.Usuario_NegocioImp;

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

		Cliente_NegocioImp cliente = new Cliente_NegocioImp();		
		Usuario_NegocioImp usuario = new Usuario_NegocioImp();
		
		ArrayList<Cliente> clientes = cliente.listarClientes();
		ArrayList<Usuario> usuarios = usuario.listarUsuarios();

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
			int id=0;
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

			if(!esNumero(cuil) || !esNumero(dni) || !esNumero(telefono) || !esTexto(nombre) || 
			  !esTexto(apellido) || !esTexto(nacionalidad) || !esTexto(localidad) ) {
				System.out.println("Entrando a la wea");
	        	 request.setAttribute("errorMessage", "Alguno de los datos cargados era incorrecto. Recuerde completar los campos que se exigen.");
		         request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
		         return;
			}

			// Validando espacios vacios
	        if (dni.trim().isEmpty() || cuil.trim().isEmpty() || nombre.trim().isEmpty()
	                || apellido.trim().isEmpty() || sexo.trim().isEmpty()
	                || nacionalidad.trim().isEmpty() || fechaNacimiento.trim().isEmpty()
	                || direccion.trim().isEmpty() || localidad.trim().isEmpty()
	                || provincia.trim().isEmpty() || correo.trim().isEmpty()
	                || telefono.trim().isEmpty() || usuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
	            // Mensaje de error
	        	 request.setAttribute("errorMessage", "Por favor complete todos los campos");
	            request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
	            return;
	        }


	        Cliente cliente = new Cliente(id, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correo, telefono, estado);
	        System.out.println("Cliente paso validaciones");
	        System.out.println(cliente);

	        Usuario_NegocioImp us = new Usuario_NegocioImp();
	        Cliente_NegocioImp cli = new Cliente_NegocioImp();

	        try {
	            // Se fija si el usuario ya existe
	        		
	            if (us.verificarNombreUsuario(usuario, id) || cli.existeDNI(dni, id)) {
	               
	                request.setAttribute("existeUsuario", true);
	                request.setAttribute("errorMessage", "El nombre de usuario o DNI ya esta en uso. Por favor, elija otro.");
	                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
	                throw new ExcepcionDni();
	            } else {
	                // Si no existe, procedemos a crear el nuevo cliente
	                id = cli.insertarCliente(cliente);
	                System.out.println("Se crea cliente"+id);
	                cliente.setIdCLiente(id);
	                Usuario user = new Usuario(0, usuario, contrasena, 2, cliente);
	                us.insertarUsuario(user);
	                request.setAttribute("filas", true);
	                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	//-----------------  FUNCIONES PARA VALIDAR ----------------
	private boolean esNumero(String str) {
	    return str.matches("\\d+");
	}
	private boolean esTexto(String str) {
	    return str.matches("[a-zA-Z]+");
	}
}
