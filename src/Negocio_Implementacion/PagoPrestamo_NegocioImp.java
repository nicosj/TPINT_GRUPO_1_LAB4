package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.PagoPrestamoDao_Implement;
import dao_Implement.PrestamoDao_Implement;
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
    public ArrayList<PagoPrestamo> readAll() {
        ArrayList<PagoPrestamo> listado = negocio.readAll();
        return listado;
    }
}