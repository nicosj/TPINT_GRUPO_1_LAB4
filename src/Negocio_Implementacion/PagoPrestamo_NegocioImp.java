package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.PagoPrestamoDao_Implement;
import dominio.PagoPrestamo;
import negocio.PagoPrestamo_Negocio;

public class PagoPrestamo_NegocioImp implements PagoPrestamo_Negocio{

    PagoPrestamoDao_Implement negocio = new PagoPrestamoDao_Implement();



    @Override
    public boolean insert(PagoPrestamo pago) {
        boolean inserto = negocio.insert(pago);
        return inserto;
    }
    @Override
    public boolean update(int id, String cuenta) {

        return negocio.update(id , cuenta);
    }
    @Override
    public ArrayList<PagoPrestamo> readAll() {
        ArrayList<PagoPrestamo> listado=new ArrayList<>();
        listado = negocio.readAll();
        return listado;
    }


    @Override
    public ArrayList<PagoPrestamo> readAllByID(String IdPrestamo) {
        ArrayList<PagoPrestamo> listado=new ArrayList<>();
        listado = negocio.readAllByID(IdPrestamo);
        return listado;
    }
}