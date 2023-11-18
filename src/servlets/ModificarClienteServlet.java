package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_Implement.ClienteDao_Implement;
import dao_Implement.UsuarioDao_Implement;
import dominio.Cliente;
import dominio.Usuario;

@WebServlet("/admin/ModificarClienteServlet")
public class ModificarClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModificarClienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnModificar") != null) {
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

            // Validando espacios vacios
            if (dni.trim().isEmpty() || cuil.trim().isEmpty() || nombre.trim().isEmpty() || apellido.trim().isEmpty()
                    || sexo.trim().isEmpty() || nacionalidad.trim().isEmpty() || fechaNacimiento.trim().isEmpty()
                    || direccion.trim().isEmpty() || localidad.trim().isEmpty() || provincia.trim().isEmpty()
                    || correo.trim().isEmpty() || telefono.trim().isEmpty() || usuario.trim().isEmpty()
                    || contrasena.trim().isEmpty()) {
                // Mensaje de error
                request.setAttribute("errorMessage", "Por favor complete todos los campos");
                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
                return;
            }

         // Verificar si el nuevo nombre de usuario ya existe
            ClienteDao_Implement clienteDao = new ClienteDao_Implement();
            UsuarioDao_Implement usuarioDao = new UsuarioDao_Implement();

            // Obtener el usuario existente para comparar el nombre de usuario
            Usuario usuarioExistente = usuarioDao.readOnce(idCliente);
            String nombreUsuarioExistente = usuarioExistente.getUsuario();

            // Verificar si el nombre de usuario se ha modificado y si el nuevo nombre ya existe
            if (!usuario.equals(nombreUsuarioExistente) && usuarioDao.verificarNombreUsuario(usuario, idCliente)) {
                // Mostrar un mensaje de error o manejar de alguna manera adecuada
                request.setAttribute("errorMessage", "El nuevo nombre de usuario ya está en uso.");
                // Redirigir de nuevo a la página de edición con el mensaje de error
                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
                return;
            }

            // Crear instancias de Cliente y Usuario
            Cliente cliente = new Cliente(idCliente, dni, cuil, nombre, apellido, sexo, nacionalidad,
                    fechaNacimiento, direccion, localidad, provincia, correo, telefono, estado);
            Usuario user = new Usuario();
            user.setUsuario(usuario);
            user.setClave(contrasena);

            try {
                boolean filas = clienteDao.update(cliente);
                boolean filas2 = usuarioDao.update(user);
                request.setAttribute("filas", filas);
                request.setAttribute("crud", "mod");
                request.getRequestDispatcher("/admin/cliente.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
