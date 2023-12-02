package dao;

import dominio.PagoPrestamo;

import java.util.ArrayList;

public interface PagoPrestamoDao {
    public boolean insert(PagoPrestamo pagoPrestamo);
    public ArrayList<PagoPrestamo> readAll();
}
