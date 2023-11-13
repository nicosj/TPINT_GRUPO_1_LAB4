package dao_Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DB;
import dao.InteresesDao_interfaz;
import dominio.Cliente;
import dominio.Intereses;

public class InteresesDao_Implement implements InteresesDao_interfaz {
	private static final String insert = "Insert into intereses (idinteres, Cuotas, Porcentaje)  values (?, ?, ?)";
	private static final String delete = "DELETE FROM intereses WHERE idinteres = ?";
	private static final String readall = "SELECT * FROM intereses";	
	private static final String update = "update interes set  Cuota = ?, Porcentaje = ? where idinteres = ?";
	private static final String query = "Select * FROM cliente WHERE idinteres = ?";
	
	
	@Override
	public boolean update(Intereses interes_a_modificar) {
		PreparedStatement statement;
		Connection conexion =DB.getConexion().getSQLConexion();
        boolean actualizacionExitosa = false;
        try
        {
            statement = conexion.prepareStatement(update);

            statement.setInt(1,  interes_a_modificar.getCuotas());
            statement.setFloat(2,  interes_a_modificar.getPorcentaje());
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
	public boolean delete(Intereses interes_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = DB.getConexion().getSQLConexion();
		boolean eliminacionExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, interes_a_eliminar.getidInteres());
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
	public List<Intereses> readAll() {

		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Intereses> intereses = new ArrayList<Intereses>();
		DB conexion = DB.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				intereses.add(getInteres(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return intereses;
	}

	@Override
	public Intereses obtenerInteres(int idInteres) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Intereses interes) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private Intereses getInteres(ResultSet resultSet) throws SQLException
	{
		int idInteres = resultSet.getInt("idinteres");
		float porcentaje = resultSet.getFloat("Porcentaje");
		int cuotas = resultSet.getInt("Cuotas");
        
        
		return new Intereses(idInteres, cuotas, porcentaje);
	}
	

}
