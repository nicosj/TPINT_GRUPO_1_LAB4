package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DB;
import dao.MovimientoDao_Imp;
import dominio.Cuenta;
import dominio.Movimiento;

public class MovimientoDao implements MovimientoDao_Imp {

	private static final String insert =  "insert into Movimiento (numero_Cuenta, Fecha, Detalle_Concepto, Importe, Tipo_Movimiento) values ( ? , ?, ?, ?, ?)";
	private static final String readall = "SELECT * FROM Movimiento";	
	private static final String readallCuentas = "SELECT * FROM Movimiento where numero_Cuenta =?";
	private static final String update = "update cuenta set numero_Cuenta= ?, Fecha = ?, Detalle_Concepto = ?, Importe = ?, Tipo_Movimiento = ?   where idMovimiento = ?";
	private static final String historialCuentas =  "SELECT * FROM MOVIMIENTO M "
	        + "INNER JOIN Cuenta C ON C.numero_Cuenta = M.numero_Cuenta "
	        + "INNER JOIN Cliente Cli ON Cli.idCliente = C.idCliente "
	        + "WHERE Cli.idCliente = ?";
	
	public boolean insert(Movimiento movimiento) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        
        
		System.out.println(movimiento.toString());
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setString(1, movimiento.getCuenta().getNumero_Cuenta());
            statement.setString(2, movimiento.getFechaMovimiento());
            statement.setString(3, movimiento.getDetalleConcepto());
            statement.setDouble(4, movimiento.getImporteMovimiento());
            statement.setString(5, movimiento.getTipoMovimiento());
            
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

	
	public boolean update(Movimiento movimiento_a_modificar) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);

            statement.setString(1, movimiento_a_modificar.getIdMovimiento());
            statement.setString(2, movimiento_a_modificar.getCuenta().getNumero_Cuenta());
            statement.setString(3, movimiento_a_modificar.getFechaMovimiento());
            statement.setString(4, movimiento_a_modificar.getDetalleConcepto());
            statement.setDouble(5, movimiento_a_modificar.getImporteMovimiento());
            statement.setString(6, movimiento_a_modificar.getTipoMovimiento());
            
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
	public ArrayList<Movimiento> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				listaMovimientos.add(obtenerMovimiento(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	    for (Movimiento movimientos : listaMovimientos) {
	    		System.out.println("Movimientos: " + movimientos.toString());	 
	    	
	    }

		return listaMovimientos;
	}

	
	
	

	
	private Movimiento obtenerMovimiento(ResultSet resultSet) throws SQLException
	{
		String idMovimiento = resultSet.getString("idMovimiento"); 
		String fecha = resultSet.getString("Fecha");
		String detalleConcepto = resultSet.getString("Detalle_Concepto");
		String numCuenta = resultSet.getString("numero_Cuenta");
		Cuenta cuenta = new Cuenta();
		cuenta.setNumero_Cuenta(numCuenta);
		String tipoMovimiento = resultSet.getString("Tipo_Movimiento");
		double importe = resultSet.getDouble("Importe");

		
		
		return new Movimiento(idMovimiento, cuenta, fecha, detalleConcepto, importe, tipoMovimiento);
	}


	@Override

	public Movimiento obtenerMovimiento(String idCliente) {
		Movimiento movimiento = new Movimiento();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(historialCuentas);
		     statement.setString(1, idCliente);
		     
		     ResultSet resultado = statement.executeQuery();

		        if (resultado.next()) {
		        	movimiento.setIdMovimiento(resultado.getString("idMovimiento"));
		        	movimiento.getCuenta().setNumero_Cuenta(resultado.getString("numero_Cuenta"));
		        	movimiento.setFechaMovimiento(resultado.getString("Fecha"));
		        	movimiento.setDetalleConcepto(resultado.getString("Detalle_Concepto"));
		        	movimiento.setImporteMovimiento(resultado.getDouble("Importe"));
		        	movimiento.setTipoMovimiento(resultado.getString("TipoMovimiento"));
		        }  
		        
		        
		} catch(Exception e){
			e.printStackTrace();
		}
		return movimiento;
	}
	@Override
	public ArrayList<Movimiento> readAllMovimientos(String cuentas) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
		DB conexion = DB.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallCuentas);
			statement.setString(1, cuentas);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				listaMovimientos.add(obtenerMovimiento(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		for (Movimiento movimientos : listaMovimientos) {
			System.out.println("Movimientos: " + movimientos.toString());

		}

		return listaMovimientos;
	}


	public ArrayList<Movimiento> obtenerMovimientos(String idCliente) {
	    ArrayList<Movimiento> movimientos = new ArrayList<>();
	    PreparedStatement statement;
	    Connection conexion = DB.getConexion().getSQLConexion();

	    try {
	        statement = conexion.prepareStatement(historialCuentas);
	        statement.setString(1, idCliente);

	        try (ResultSet resultado = statement.executeQuery()) {
	            while (resultado.next()) {
	                Movimiento movimiento = new Movimiento();
		        	movimiento.setIdMovimiento(resultado.getString("idMovimiento"));
		        	movimiento.getCuenta().setNumero_Cuenta(resultado.getString("numero_Cuenta"));
		        	movimiento.setFechaMovimiento(resultado.getString("Fecha"));
		        	movimiento.setDetalleConcepto(resultado.getString("Detalle_Concepto"));
		        	movimiento.setImporteMovimiento(resultado.getDouble("Importe"));
		        	movimiento.setTipoMovimiento(resultado.getString("Tipo_Movimiento"));
	                movimientos.add(movimiento);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return movimientos;
	}

}


