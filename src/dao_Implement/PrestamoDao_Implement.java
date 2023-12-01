package dao_Implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DB;
import dao.PrestamoDao_Interfaz;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.Prestamo;

public class PrestamoDao_Implement implements PrestamoDao_Interfaz{
	
	private final static String insert = "insert into prestamos (numero_Cuenta, Importe_Cuota, Fecha_Pedido, Importe_Total, Cuotas, estado) values (?, ?, ?, ?, ?, ?)";
	private final static String read = "SELECT * FROM prestamos where Importe_Total >= ?";
	private static final String readall = "SELECT * FROM prestamos";
    private static final String updateEstadoAprobado = "UPDATE prestamos SET estado = ? WHERE idprestamo = ?";
    private static final String updateEstadoRechazado = "UPDATE prestamos SET estado = ? WHERE idprestamo = ?";
    
	@Override
	public boolean insert(Prestamo prestamo) {
		PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {
            statement =  conexion.prepareStatement(insert);
            statement.setString(1, prestamo.getNumero_Cuenta());
            statement.setDouble(2, prestamo.getImporteCuota());
            statement.setString(3, prestamo.getFechaPedido());
            statement.setDouble(4, prestamo.getTotalImporte());
            statement.setDouble(5, prestamo.getCuotas());
            statement.setInt(6, prestamo.getEstado());
            
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
	public ArrayList<Prestamo> readAllMonto(double total) {
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		PreparedStatement statement;
		ResultSet resultSet;

		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(read);
			statement.setDouble(1, total);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				listaPrestamos.add(obtenerPrestamo(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	    for (Prestamo prestamo: listaPrestamos) {
	    		System.out.println("Prestamo: " + prestamo.toString());	 
	    	
	    }
		return listaPrestamos;
	}	
	private Prestamo obtenerPrestamo(ResultSet resultSet) throws SQLException
	{

		 int idPrestamo = resultSet.getInt("idPrestamo");
		 String numero_Cuenta =resultSet.getString("numero_Cuenta");
		 String fechaPedido = resultSet.getString("Fecha_Pedido");
		 double importeCuota = resultSet.getDouble("Importe_Cuota");
		 double totalImporte = resultSet.getDouble("Importe_Total");
		 int cuotas = resultSet.getInt("Cuotas");
		 int estado = resultSet.getInt("estado");
				
		
		return new Prestamo(idPrestamo, numero_Cuenta, fechaPedido, importeCuota, totalImporte, cuotas, estado);
	}


	private Prestamo getPrestamo(ResultSet resultSet) throws SQLException
	{
		 int idPrestamo = resultSet.getInt("idPrestamo");
		 String numero_Cuenta =resultSet.getString("numero_Cuenta");
		 String fechaPedido = resultSet.getString("Fecha_Pedido");
		 double importeCuota = resultSet.getDouble("Importe_Cuota");
		 double totalImporte = resultSet.getDouble("Importe_Total");
		 int cuotas = resultSet.getInt("Cuotas");
		 int estado = resultSet.getInt("estado");
		
		 return new Prestamo(idPrestamo, numero_Cuenta, fechaPedido, importeCuota, totalImporte, cuotas, estado);
	}
	public ArrayList<Prestamo> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamo(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	    for (Prestamo prestamo : prestamos) {
	    		System.out.println("Prestamo: " + prestamo.toString());	 
	    	
	    }

		return prestamos;
	}
	
	//funcion para aprobar prestamo
    public void aprobarPrestamo(int idPrestamo) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(updateEstadoAprobado);
            statement.setInt(1, 1); 
            statement.setInt(2, idPrestamo);

            statement.executeUpdate();
            ((Connection) conexion).commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Funcion para rechazar prestamo
    public void rechazarPrestamo(int idPrestamo) {
        PreparedStatement statement;
        Connection conexion = DB.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(updateEstadoRechazado);
            statement.setInt(1, 0);
            statement.setInt(2, idPrestamo);

            statement.executeUpdate();
            ((Connection) conexion).commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
