package negocio;
import dominio.PagoPrestamo;
import java.util.ArrayList;


public interface PagoPrestamo_Negocio {

    boolean insert(PagoPrestamo pago);

    public ArrayList<PagoPrestamo> readAll();

}