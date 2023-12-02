package dao;

import java.util.ArrayList;
import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

public interface PrestamoDao_Interfaz {
	public boolean insert(Prestamo prestamo);
	public ArrayList<Prestamo> readAllMonto(double total);
	public List<Prestamo> readAll();
	public ArrayList<Prestamo> readAllByCuenta(String cuenta);
}
