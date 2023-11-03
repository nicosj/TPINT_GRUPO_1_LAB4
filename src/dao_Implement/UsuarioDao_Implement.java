package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DB;
import dao.UsuarioDao_Interfaz;
import dominio.Cliente;
import dominio.Usuario;

public class UsuarioDao_Implement implements UsuarioDao_Interfaz  {
	
	private static final String insert = "Insert into usuarios (usuario, clave, tipoUsuario, idCliente)  values (?, ?, ?, ?)";
	private static final String delete = "DELETE FROM usuarios WHERE idUsuario = ?";
	private static final String readall = "SELECT * FROM Usuarios";	
	private static final String update = "update usuarios set usuario = ?, clave = ?, tipoUsuario = ?, idCliente = ?";
	private static final String query = "Select * FROM usuarios WHERE idUsuario = ?";
	
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
            statement.setInt(4, usuario.getIdCliente());
            
            if(statement.executeUpdate() > 0)
            {
                ((Connection) conexion).commit();
                insercionExitosa = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try {
                ((Connection) conexion).rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                ((Connection) conexion).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return insercionExitosa;
	}
	@Override
	public boolean update(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);

            statement.setString(1,  usuario.getUsuario());
            statement.setString(2,  usuario.getClave());
            statement.setInt(3,  usuario.getTipoUsuario());
            statement.setInt(4,  usuario.getIdCliente());

            if(statement.executeUpdate() > 0)
            {
               conexion.commit();
                actualizacionExitosa = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                 conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
	public List<Usuario> readAll() {
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
		            usuario.setIdCliente(resultado.getInt("idCliente"));

		        }  
		        
		        resultado.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return usuario;
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException
	{
		int idUsuario = resultSet.getInt("idUsuario");
		String usuario = resultSet.getString("usuario");
		String clave = resultSet.getString("clave");
		int tipoUsuario = resultSet.getInt("tipoUsuario");
		int idCliente = resultSet.getInt("idCliente");      
        
        
		return new Usuario(idUsuario, usuario, clave, tipoUsuario, idCliente);
	}

	
}
