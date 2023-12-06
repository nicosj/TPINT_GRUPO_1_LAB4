package dao_Implement;

import dao.DB;
import dao.PagoPrestamoDao;
import dominio.Cuenta;
import dominio.PagoPrestamo;
import dominio.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagoPrestamoDao_Implement implements PagoPrestamoDao {
    private static final String insert = "INSERT INTO pago_prestamo (numero_Cuenta, Fecha_Pago, importe_cuota, impote_restante, cuotas_restantes, idPrestamo) VALUES (?,?,?,?,?,?)";
    private static final String readall = "SELECT * FROM pago_prestamo";

    private static final String readallByCuenta = "SELECT * FROM PagoPrestamo WHERE numero_Cuenta = ?";


    @Override
    public boolean insert(PagoPrestamo pagoPrestamo) {
        PreparedStatement ps;
        Connection conexion = DB.getConexion().getSQLConexion();
        boolean insercionExitosa = false;
        try
        {

            ps =  conexion.prepareStatement(insert);
            ps.setString(1, pagoPrestamo.getCuenta().getNumero_Cuenta());
            ps.setString(2, pagoPrestamo.getFecha_Pago());
            ps.setDouble(3, pagoPrestamo.getImporte_cuota());
            ps.setDouble(4, pagoPrestamo.getImporte_restante());
            ps.setInt(5, pagoPrestamo.getCuotas_restantes());
            ps.setInt(6, pagoPrestamo.getPrestamo().getIdPrestamo());
            if(ps.executeUpdate() > 0)
            {
                ((Connection) conexion).commit();
                insercionExitosa = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return insercionExitosa;
    }

    @Override
    public ArrayList<PagoPrestamo> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<PagoPrestamo> lista = new ArrayList<>();
        DB conexion = DB.getConexion();
        try {
            System.out.println("asdasd1");
            ps = conexion.getSQLConexion().prepareStatement(readall);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(obtenerPrestamo(rs));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("asdasd"+lista);
        return lista;
    }
    private PagoPrestamo obtenerPrestamo(ResultSet resultSet) throws SQLException
    {

            int idPrestamo = resultSet.getInt("idpago_prestamo"); // **VER
            Prestamo prestamo = new Prestamo();
            prestamo.setIdPrestamo(idPrestamo);
            String numero_Cuenta =resultSet.getString("numero_Cuenta");
            Cuenta cuenta = new Cuenta();
            cuenta.setNumero_Cuenta(numero_Cuenta);
            String fechaPedido = resultSet.getString("Fecha_Pago");
            double importeCuota = resultSet.getDouble("Importe_Cuota");
            double totalImporte = resultSet.getDouble("Impote_Restante");
            int cuotas = resultSet.getInt("Cuotas_Restantes");
            int estado = resultSet.getInt("IdPrestamo");

            return new PagoPrestamo(estado, cuenta, fechaPedido, importeCuota, totalImporte, cuotas, prestamo);
    }
}
