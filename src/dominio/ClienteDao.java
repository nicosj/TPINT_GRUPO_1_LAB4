package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {
	private Connection cn = DB.obtenerConexion();

	  public int agregarCliente(Cliente cliente) {
		  try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			int filas=0;
			try
			{
				String query = "Insert into cliente (dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correo, telefono) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement st = cn.prepareStatement(query);
				st.setString(1, cliente.getDNI());
				st.setString(2, cliente.getCUIL());
				st.setString(3, cliente.getNombre());
				st.setString(4, cliente.getApellido());
				st.setString(5, cliente.getSexo());
				st.setString(6, cliente.getNacionalidad());
				st.setDate(7, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
				st.setString(8, cliente.getDireccion());
				st.setString(9, cliente.getLocalidad());
				st.setString(10, cliente.getProvincia());
				st.setString(11, cliente.getCorreo());
				st.setString(12, cliente.getTelefono());

				filas=st.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return filas;
	    }
}
