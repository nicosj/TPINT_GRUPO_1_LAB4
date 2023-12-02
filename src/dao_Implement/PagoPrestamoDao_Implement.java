package dao_Implement;

import dao.DB;
import dao.PagoPrestamoDao;
import dominio.PagoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            ps.setString(1, pagoPrestamo.getNumero_Cuenta());
            ps.setString(2, pagoPrestamo.getFecha_Pago());
            ps.setDouble(3, pagoPrestamo.getImporte_cuota());
            ps.setDouble(4, pagoPrestamo.getImporte_restante());
            ps.setInt(5, pagoPrestamo.getCuotas_restantes());
            ps.setInt(6, pagoPrestamo.getIdPrestamo());
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
        ArrayList<PagoPrestamo> lista = new ArrayList<PagoPrestamo>();
        try {
            System.out.println("asdasd1");
            ps = DB.getConexion().getSQLConexion().prepareStatement(readall);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagoPrestamo pagoPrestamo = new PagoPrestamo();
                pagoPrestamo.setIdPago(rs.getInt("idpago_prestamo"));
                pagoPrestamo.setNumero_Cuenta(rs.getString("numero_Cuenta"));
                pagoPrestamo.setFecha_Pago(rs.getString("Fecha_Pago"));
                pagoPrestamo.setImporte_cuota(rs.getDouble("Importe_Cuota"));
                pagoPrestamo.setImporte_restante(rs.getDouble("Impote_Restante"));
                pagoPrestamo.setCuotas_restantes(rs.getInt("Cuotas_Restantes"));
                pagoPrestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                lista.add(pagoPrestamo);
                System.out.println("asdasd"+lista);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("asdasd"+lista);
        return lista;
    }
}
