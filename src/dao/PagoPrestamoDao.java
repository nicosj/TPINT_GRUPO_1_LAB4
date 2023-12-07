package dao;

import dominio.PagoPrestamo;

import java.util.ArrayList;

public interface PagoPrestamoDao {
    public boolean insert(PagoPrestamo pagoPrestamo);
    public ArrayList<PagoPrestamo> readAll();
    public ArrayList<PagoPrestamo> readAllByID(String IdPrestamo);
    public boolean update(int id);
}
