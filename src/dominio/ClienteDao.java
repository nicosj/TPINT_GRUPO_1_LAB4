package dominio;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class ClienteDao {
	  private Connection cn = DB.obtenerConexion();
	  
	  
	  public int agregarCliente(Cliente cliente) 
	  {
		
			int filas=0;
			try
			{
				String query = "Insert into cliente (DNI, CUIL, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correo, telefono) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement st = cn.prepareStatement(query);
				st.setString(1, cliente.getDNI());
				st.setString(2, cliente.getCUIL());
				st.setString(3, cliente.getNombre());
				st.setString(4, cliente.getApellido());
				st.setString(5, cliente.getSexo());
				st.setString(6, cliente.getNacionalidad());
				st.setString(7, cliente.getFechaNacimiento());
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

		public ArrayList<Cliente> listarClientes() {



           
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				Statement st = cn.createStatement();
				ResultSet rs= st.executeQuery("SELECT * FROM cliente");
				while(rs.next()){
					
					Cliente cliente= new Cliente();
					cliente.setIdCLiente(Integer.parseInt(rs.getString("idCliente")));
					cliente.setDNI(rs.getString("dni"));
					cliente.setCUIL(rs.getString("cuil"));
					cliente.setNombre(rs.getString("nombre"));
					cliente.setApellido(rs.getString("apellido"));
					cliente.setSexo(rs.getString("sexo"));
					cliente.setNacionalidad(rs.getString("nacionalidad"));
					cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
					cliente.setDireccion(rs.getString("direccion"));
					cliente.setLocalidad(rs.getString("localidad"));
					cliente.setProvincia(rs.getString("provincia"));
					cliente.setCorreo(rs.getString("correo"));
					cliente.setTelefono(rs.getString("telefono"));
					System.out.println(cliente);
					
					clientes.add(cliente);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}

			return clientes;
		}
}
