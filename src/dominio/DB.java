package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static final String host = "jdbc:mysql://localhost:3306/banco";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection conexion;

    private DB() {
    	 try {
             Class.forName("com.mysql.jdbc.Driver");

         } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        try {
            conexion = DriverManager.getConnection(host, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
           // throw new RuntimeException("error de conexion a DB");
        }
    }

    public static Connection obtenerConexion() {
        if (conexion == null) {
            new DB(); 
        }
        return conexion;
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
}
