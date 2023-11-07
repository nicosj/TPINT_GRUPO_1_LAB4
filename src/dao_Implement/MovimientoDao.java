package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DB;
import dao.MovimientoDao_Imp;
import dominio.Movimiento;

public class MovimientoDao implements MovimientoDao_Imp {

	private static final String insert =  "insert into cuenta (idMovimiento, numero_Cuenta, Fecha, Detalle_Concepto, Importe, Tipo_Movimiento) values (?, ? , ?, ?, ?, ?)";
	private static final String readall = "SELECT * FROM Movimiento";	
	private static final String update = "update cuenta set numero_Cuento= ?, Fecha = ?, Detalle_Concepto = ?, Importe = ?, Tipo_Movimiento = ?   where idMovimiento = ?";
	private static final String historialCuentas = "Select * FROM Movimiento WHERE numero_Cuenta = ?";

	
	public boolean insert(Movimiento movimiento) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setString(1, movimiento.getIdMovimiento());
            statement.setString(2, movimiento.getNumero_Cuenta());
            statement.setString(3, movimiento.getFechaMovimiento());
            statement.setString(4, movimiento.getDetalleConcepto());
            statement.setDouble(5, movimiento.getImporteMovimiento());
            statement.setString(6, movimiento.getTipoMovimiento());
            
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
            statement.setString(2, movimiento_a_modificar.getNumero_Cuenta());
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
		String tipoMovimiento = resultSet.getString("Tipo_Movimiento");
		double importe = resultSet.getDouble("Saldo");

		
		
		return new Movimiento(idMovimiento, fecha, detalleConcepto, numCuenta, importe, tipoMovimiento);
	}


	@Override
	public Movimiento obtenerMovimiento(String numero_Cuenta) {
		Movimiento movimiento = new Movimiento();
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(historialCuentas);
		     statement.setString(1, numero_Cuenta);
		     
		     ResultSet resultado = statement.executeQuery();

		        if (resultado.next()) {
		        	movimiento.setImporteMovimiento(resultado.getDouble("Importe"));
		        	movimiento.setDetalleConcepto(resultado.getString("Detalle_Concepto"));
		        	movimiento.setNumero_Cuenta(resultado.getString("numero_Cuenta"));
		        	movimiento.setTipoMovimiento(resultado.getString("idCuenta"));  
		        	movimiento.setFechaMovimiento(resultado.getString("Fecha"));
		        }  
		        
		        
		} catch(Exception e){
			e.printStackTrace();
		}
		return movimiento;
	}





}


