package dao_Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dao.CuentaDao_Interfaz;
import dao.DB;
import dominio.Cuenta;

public class CuentaDao_Implement implements CuentaDao_Interfaz {

	private static final String insert = "insert into cuenta (idCliente, FechaCreacion, TipoCuenta, CBU, Saldo, numero_Cuenta, estado) values (?, ? , ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE cuenta SET estado = 0 where numero_Cuenta = ? ";
	private static final String readall = "SELECT * FROM cuenta";	
	private static final String update = "update cuenta set idCliente= ?, FechaCreacion = ?, TipoCuenta = ?, CBU=?, Saldo=?, estado = ?   where numero_Cuenta = ?";
	private static final String query = "Select * FROM cuenta WHERE numero_Cuenta = ?";

	
	@Override
	public boolean insert(Cuenta cuenta) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setString(1, cuenta.getIdCliente());
            statement.setString(2, cuenta.getFecha_Creacion());
            statement.setString(3, cuenta.getTipo_Cuenta());
            statement.setString(4, cuenta.getCBU());
            statement.setDouble(5, cuenta.getSaldo());
            statement.setBoolean(6,  cuenta.getEstado());
            statement.setString(7, cuenta.getNumero_Cuenta());
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

            statement.setString(1, cuenta_a_modificar.getIdCliente());
            statement.setString(2, cuenta_a_modificar.getFecha_Creacion());
            statement.setString(3, cuenta_a_modificar.getTipo_Cuenta());
            statement.setString(4, cuenta_a_modificar.getCBU());
            statement.setDouble(5, cuenta_a_modificar.getSaldo());
            statement.setBoolean(6, cuenta_a_modificar.getEstado());
            statement.setString(7, cuenta_a_modificar.getNumero_Cuenta());
            
            
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
	public boolean delete(int numeroCuenta) {
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		boolean eliminacionExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
		     statement.setInt(1, numeroCuenta);
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
	public ArrayList<Cuenta> readAll() {
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
	    for (Cuenta cuenta : cuentas) {
	    		System.out.println("Cuenta: " + cuenta.toString());	 
	    	
	    }

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
		        	cuenta.setIdCliente(resultado.getString("idCliente"));
		        	cuenta.setTipo_Cuenta(resultado.getString("TipoCuenta"));
		        	cuenta.setCBU(resultado.getString("CBU"));
		        	cuenta.setFecha_Creacion(resultado.getString("FechaCreacion"));
		        	cuenta.setSaldo(resultado.getDouble("Saldo"));
		        	cuenta.setEstado(resultado.getBoolean("estado"));
		        	cuenta.setNumero_Cuenta(resultado.getString("numero_Cuenta"));		            
		        }  
		        
		        resultado.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return cuenta;
	}
	private Cuenta getCuenta(ResultSet resultSet) throws SQLException
	{
		String idCliente = resultSet.getString("idCliente");
		String fechaCreacion = resultSet.getString("FechaCreacion");
		String TipoCuenta = resultSet.getString("TipoCuenta");
		String CBU = resultSet.getString("CBU");
		double saldo = resultSet.getDouble("Saldo");
		String numCuenta = resultSet.getString("numero_Cuenta");
		boolean estado = resultSet.getBoolean("estado");
		
		return new Cuenta( idCliente, numCuenta, TipoCuenta, fechaCreacion, CBU, saldo, estado);
	}

	
	
	public int getLastCBU() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int lastCBU = 0;
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco","root","");
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT CBU FROM cuenta\r\n" + 
					"ORDER BY CBU DESC\r\n" + 
					"LIMIT 1;");
			
			
			while(rs.next()){
				
				lastCBU = rs.getInt("CBU");
				System.out.println(lastCBU);
			
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return lastCBU;
	}


}
