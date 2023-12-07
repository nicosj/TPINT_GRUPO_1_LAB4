package negocio;
import dominio.PagoPrestamo;
import java.util.ArrayList;


public interface PagoPrestamo_Negocio {

    public boolean insert(PagoPrestamo pago);

    public ArrayList<PagoPrestamo> readAll();

    ArrayList<PagoPrestamo> readAllByID(String  IdPrestamo);
    public boolean update(int id,String cuenta);
}
