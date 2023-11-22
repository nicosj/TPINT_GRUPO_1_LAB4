package dao;

import java.util.ArrayList;
import java.util.List;

import dominio.Prestamo;

public interface PrestamoDao_Interfaz {
	public boolean insert(Prestamo prestamo);
	public ArrayList<Prestamo> readAllMonto(double total);
}
