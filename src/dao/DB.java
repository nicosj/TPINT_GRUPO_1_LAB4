package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	private static DB instancia;
	private Connection connection;


	private DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco","root","");
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static DB getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new DB();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}








/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	private static final String host = "jdbc:mysql://localhost:3306/banco";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection conexion;

    private DB() {
        try {
            conexion = DriverManager.getConnection(host, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error de conexion a DB");
        }
    }

    public static Connection obtenerConexion() {
        if (conexion == null) {
            new DB(); 
        }
        return conexion;
    }
    public Connection getSQLConexion() 
    {
        return this.conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public PreparedStatement prepareStatement(String update) {
		// TODO Auto-generated method stub
		return null;
	}
}*/
