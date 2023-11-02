package dao_Implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao_Interfaz;
import dao.DB;
import dominio.Cuenta;

public class CuentaDao_Implement implements CuentaDao_Interfaz {

	private static final String insert = "insert into cuenta (idCliente, FechaCreacion, TipoCuenta, CBU, Saldo, numero_Cuenta) values (?, ? , ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM cuenta WHERE numero_Cuenta = ?";
	private static final String readall = "SELECT * FROM cuenta";	
	private static final String update = "update idCliente= ?, FechaCreacion=?, TipoCuenta=?, CBU=?, Saldo=?   where numero_Cuenta = ?";
	private static final String query = "Select * FROM cuenta WHERE numero_Cuenta = ?";

	
	@Override
	public boolean insert(Cuenta cuenta) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setInt(1, cuenta.getIdCliente());
            statement.setDate(2, cuenta.getFecha_Creacion());
            statement.setString(3, cuenta.getTipo_Cuenta());
            statement.setString(4, cuenta.getCBU());
            statement.setDouble(5, cuenta.getSaldo());
            statement.setString(6, cuenta.getNumero_Cuenta());
            
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
	public boolean update(Cuenta cuenta_a_modificar) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);

            statement =  conexion.prepareStatement(insert);
            statement.setInt(1, cuenta_a_modificar.getIdCliente());
            statement.setDate(2, cuenta_a_modificar.getFecha_Creacion());
            statement.setString(3, cuenta_a_modificar.getTipo_Cuenta());
            statement.setString(4, cuenta_a_modificar.getCBU());
            statement.setDouble(5, cuenta_a_modificar.getSaldo());
            statement.setString(6, cuenta_a_modificar.getNumero_Cuenta());
            
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
	public boolean delete(Cuenta cuenta_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		boolean eliminacionExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, cuenta_a_eliminar.getNumero_Cuenta());
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
	public List<Cuenta> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cuentas.add(getCuenta(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println(cuentas.get(0));
		return cuentas;
	}

	@Override
	public Cuenta obtenerCuenta(int numero_cuenta) {
		Cuenta cuenta = new Cuenta();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(query);
		     statement.setInt(1, numero_cuenta);
		     
		     ResultSet resultado = statement.executeQuery();

		        if (resultado.next()) {
		        	cuenta.setIdCliente(resultado.getInt("idCliente"));
		        	cuenta.setTipo_Cuenta(resultado.getString("TipoCuenta"));
		        	cuenta.setCBU(resultado.getString("CBU"));
		        	cuenta.setFecha_Creacion(resultado.getDate("FechaCreacion"));
		        	cuenta.setSaldo(resultado.getDouble("Saldo"));
		            
		        }  
		        
		        resultado.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return cuenta;
	}
	private Cuenta getCuenta(ResultSet resultSet) throws SQLException
	{
		String numCuenta = resultSet.getString("numero_Cuenta");
		int idCliente = resultSet.getInt("idCliente");
		Date fechaCreacion = resultSet.getDate("FechaCreacion");
		String TipoCuenta = resultSet.getString("TipoCuenta");
		String CBU = resultSet.getString("CBU");
		double saldo = resultSet.getDouble("Saldo");
		
		
		return new Cuenta(numCuenta, idCliente, TipoCuenta, fechaCreacion, CBU, saldo);
	}

}