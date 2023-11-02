package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;


import dao.ClienteDao_Interfaz;
import dao.DB;
import dominio.Cliente;

public class ClienteDao_Implement implements ClienteDao_Interfaz {
	private static final String insert = "insert into cliente (DNI, CUIL, nombre, apellido, sexo, naciolidad, fechaNacimiento, direccion, localidad, provincia, correo, telefono) values (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM cliente WHERE idCliente = ?";
	private static final String readall = "SELECT * FROM cliente";	
	private static final String update = "update cliente set DNI = ?, CUIL = ?, nombre = ?, apellido = ?, sexo = ?, naciolidad = ?, fechaNacimiento = ?, direccion = ?, localidad = ?, provincia = ?, correo = ?, telefono = ? where idCliente = ?";
	private static final String query = "Select * FROM cliente WHERE idCliente = ?";

	
	@Override
		public boolean insert(Cliente cliente) {

	        PreparedStatement statement;
	        Connection conexion = DB.getConexion().getSQLConexion();
	        boolean insercionExitosa = false;
	        try
	        {
	            statement =  conexion.prepareStatement(insert);
	            statement.setString(1,cliente.getDNI());
	            statement.setString(2,cliente.getCUIL());
	            statement.setString(3,cliente.getNombre());
	            statement.setString(4,cliente.getApellido());
	            statement.setString(5,cliente.getSexo());
	            statement.setString(6,cliente.getNacionalidad());
	            statement.setDate(7,cliente.getFechaNacimiento());
	            statement.setString(8,cliente.getDireccion());
	            statement.setString(9,cliente.getLocalidad());
	            statement.setString(10,cliente.getProvincia());
	            statement.setString(11,cliente.getCorreo());
	            statement.setString(12,cliente.getTelefono());
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
	public boolean update(Cliente cliente_a_modificar) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);

            statement.setString(1,  cliente_a_modificar.getDNI());
            statement.setString(2,  cliente_a_modificar.getCUIL());
            statement.setString(3,  cliente_a_modificar.getNombre());
            statement.setString(4,  cliente_a_modificar.getApellido());
            statement.setString(5,  cliente_a_modificar.getSexo());
            statement.setString(6,  cliente_a_modificar.getNacionalidad());
            statement.setDate(7,  cliente_a_modificar.getFechaNacimiento());
            statement.setString(8,  cliente_a_modificar.getDireccion());
            statement.setString(9,  cliente_a_modificar.getLocalidad());
            statement.setString(10,  cliente_a_modificar.getProvincia());
            statement.setString(11,  cliente_a_modificar.getCorreo());
            statement.setString(12,  cliente_a_modificar.getTelefono());
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
	public boolean delete(Cliente cliente_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obtenerPersona(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}
}
