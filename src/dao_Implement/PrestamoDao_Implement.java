package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.DB;
import dao.PrestamoDao_Interfaz;
import dominio.Prestamo;

public class PrestamoDao_Implement implements PrestamoDao_Interfaz{
	
	final static String insert = "insert into prestamos (numero_Cuenta, Importe_Cuota, Fecha_Pedido, Importe_Total, Cuotas, estado) values (?, ?, ?, ?, ?, ?)";

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
	public boolean update(Prestamo prestamo_a_modificar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Prestamo> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
