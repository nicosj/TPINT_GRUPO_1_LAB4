package dao_Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao_Interfaz;
import dao.DB;
import dominio.Cuenta;

public class CuentaDao_Implement implements CuentaDao_Interfaz {

	private static final String insert = "insert into cuenta (idCliente, FechaCreacion, TipoCuenta, CBU, Saldo, numero_Cuenta, estado) values (?, ? , ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE cuenta SET estado = 0 where numero_Cuenta = ? ";
	private static final String readall = "SELECT * FROM cuenta";	
	private static final String readallById = "SELECT * FROM cuenta where idCliente = ? ";

	private static final String update = "update cuenta set idCliente= ?, FechaCreacion = ?, TipoCuenta = ?, CBU=?, Saldo=?, estado = ?   where numero_Cuenta = ?";
	private static final String query = "Select * FROM cuenta WHERE numero_Cuenta = ?";
	private static final String queryCbu = "Select * FROM cuenta WHERE CBU = ?";
	private static final String queryGetAcountByClientId = "Select * FROM cliente c inner join cuenta cu on cu.idCliente = c.idCliente WHERE c.idCliente = ?";
	private static final String CuentaCountByIdCliente = "SELECT COUNT(*) AS cuenta_count FROM cuenta WHERE idCliente = ? AND estado = 1;";
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
            statement.setString(6, cuenta.getNumero_Cuenta());
            statement.setInt(7,  cuenta.getEstado()?1:0);
            
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
        
        }
        

        return actualizacionExitosa;
	}

	@Override
	public boolean delete(String numeroCuenta) {
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		boolean eliminacionExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
		     statement.setString(1, numeroCuenta);
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
	public Cuenta obtenerCuenta(String numero_cuenta) {
		Cuenta cuenta = new Cuenta();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(query);
		     statement.setString(1, numero_cuenta);
		     
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
		        
		        
		} catch(Exception e){
			e.printStackTrace();
		}
		return cuenta;
	}
	
	public List<Cuenta> obtenerCuentaByClientId(int idCliente) {
		Cuenta cuenta = new Cuenta();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>(); 
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(queryGetAcountByClientId);
		     statement.setInt(1, idCliente);
		     
		     ResultSet resultado = statement.executeQuery();

		        if (resultado.next()) {
		        	cuenta.setIdCliente(resultado.getString("idCliente"));
		        	cuenta.setTipo_Cuenta(resultado.getString("TipoCuenta"));
		        	cuenta.setCBU(resultado.getString("CBU"));
		        	cuenta.setFecha_Creacion(resultado.getString("FechaCreacion"));
		        	cuenta.setSaldo(resultado.getDouble("Saldo"));
		        	cuenta.setEstado(resultado.getBoolean("estado"));
		        	cuenta.setNumero_Cuenta(resultado.getString("numero_Cuenta"));	
		        	
		        	cuentas.add(cuenta);
		        }  
		        
		        resultado.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return cuentas;
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
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return lastCBU;
	}

	public int getCuentaCountByClientId(String idCliente) {
	    int cuentaCount = 0;
	    try {
	        Connection connection = DB.getConexion().getSQLConexion();
	        
	        PreparedStatement statement = connection.prepareStatement(CuentaCountByIdCliente);
	        statement.setString(1, idCliente);

	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            cuentaCount = resultSet.getInt("cuenta_count");
	        }

	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cuentaCount;
	}
	@Override
	public ArrayList<Cuenta> readAllByID(int clientId) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		DB conexion = DB.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallById);
			statement.setInt(1, clientId);
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

		System.out.println("Cuentas size dao: " + cuentas.size());
		return cuentas;
	}
	@Override
	public Cuenta obtenerCuentaCbu(String cbu) {
		Cuenta cuenta = new Cuenta();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(queryCbu);
			statement.setString(1, cbu);

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


		} catch(Exception e){
			e.printStackTrace();
		}
		return cuenta;
	}
	@Override
	public boolean ajusteCuenta(String cuenenta, double monto) {
		// TODO Auto-generated method stub
		Cuenta cuenta = new Cuenta();
		cuenta= obtenerCuenta(cuenenta);

		try {
			double saldo = cuenta.getSaldo() + monto;
			cuenta.setSaldo(saldo);
			if(update(cuenta)) {
				return true;
			}
			else {
				System.out.println("No se pudo actualizar la cuenta");
			}

		} catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}
}
