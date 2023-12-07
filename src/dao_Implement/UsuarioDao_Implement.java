package dao_Implement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DB;
import dao.UsuarioDao_Interfaz;
import dominio.Cliente;
import dominio.Usuario;

public class UsuarioDao_Implement implements UsuarioDao_Interfaz  {
	
	private static final String insert = "Insert into usuario (usuario, clave, tipoUsuario, idCliente)  values (?, ?, ?, ?)";
	private static final String delete = "DELETE FROM usuario WHERE idUsuario = ?";
	private static final String readall = "SELECT * FROM usuario";	
	private static final String update = "update usuario set clave = ? where idCliente = ?";
	private static final String query = "Select * FROM usuario WHERE idUsuario = ?";
	private static final String login = "Select * FROM usuario WHERE usuario = ? and clave = ?";
	private static final String verificarNombreUsuario = "SELECT * FROM usuario WHERE usuario = ? AND idUsuario <> ?";
	
	@Override
	public boolean insert(Usuario usuario) {
		PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getClave());
            statement.setInt(3, usuario.getTipoUsuario());
            statement.setInt(4, usuario.getCliente().getIdCLiente());
            
            if(statement.executeUpdate() > 0)
            {
                ((Connection) conexion).commit();
                insercionExitosa = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        
        
        return insercionExitosa;
	}
	@Override
	public boolean update(Usuario usuario, int idCliente) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);
			statement.setString(1,  usuario.getClave());
            statement.setInt(2, idCliente);

            if(statement.executeUpdate() > 0)
            {
               conexion.commit();
                actualizacionExitosa = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        

        return actualizacionExitosa;
	}
	@Override
	public boolean delete(int id) {
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		boolean eliminacionExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, id);
			if(statement.executeUpdate()> 0)
			{
				conexion.commit();
				eliminacionExitosa = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return eliminacionExitosa;
	}
	@Override
	public ArrayList<Usuario> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuarios.add(getUsuario(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return usuarios;
	}
	@Override
	public Usuario readOnce(int id) {
		Usuario usuario = new Usuario();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(query);
		     statement.setInt(1, id);
		     
		     ResultSet resultado = statement.executeQuery();

		        if (resultado.next()) {
		        	
		            usuario.setIdUsuario(resultado.getInt("idUsuario"));
		            usuario.setUsuario(resultado.getString("usuario"));
		            usuario.setClave(resultado.getString("clave"));
		            usuario.setTipoUsuario(resultado.getInt("tipoUsuario"));
		            usuario.getCliente().setIdCLiente(resultado.getInt("idCliente"));

		        }  
		        
		        resultado.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	
	@Override
	public Usuario login(Usuario usuario) {
		Usuario aux = new Usuario(null, null);
		ResultSet result;
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(login);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getClave());
			result = statement.executeQuery();
			while(result.next()) {
				aux.setIdUsuario(result.getInt("idUsuario"));
				aux.setUsuario(result.getString("usuario"));
				aux.setClave(result.getString("clave"));
				aux.setTipoUsuario(result.getInt("tipoUsuario"));
				Cliente cliente = new Cliente();
				cliente.setIdCLiente(result.getInt("idCliente"));
				aux.setCliente(cliente);
				System.out.println(aux.getUsuario());
			}
			
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("asdasd"+aux.getUsuario());
		return aux;
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException
	{
		int idUsuario = resultSet.getInt("idUsuario");
		String usuario = resultSet.getString("usuario");
		String clave = resultSet.getString("clave");
		int tipoUsuario = resultSet.getInt("tipoUsuario");
		Cliente idCliente= new Cliente();
		idCliente.setIdCLiente(resultSet.getInt("idCliente"));
        
		return new Usuario(idUsuario, usuario, clave, tipoUsuario, idCliente);
	}
	
	@Override
	public boolean verificarNombreUsuario(String nuevoUsuario, int idUsuario) {
	    PreparedStatement statement;
	    ResultSet resultSet;
	    Connection conexion = DB.getConexion().getSQLConexion();
	    boolean existeUsuario = false;
	    try {
	        statement = conexion.prepareStatement(verificarNombreUsuario);
	        statement.setString(1, nuevoUsuario);
	        statement.setInt(2, idUsuario); // Agregar el id del usuario actual

	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            existeUsuario = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existeUsuario;
	}
	
}
