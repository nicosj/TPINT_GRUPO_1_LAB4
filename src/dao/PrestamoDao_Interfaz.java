package dao;

import java.util.List;

import dominio.Prestamo;

public interface PrestamoDao_Interfaz {
	public boolean insert(Prestamo prestamo);
	public boolean update(Prestamo prestamo_a_modificar);	
	public List<Prestamo> readAll();
}
