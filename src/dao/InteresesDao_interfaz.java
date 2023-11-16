package dao;

import java.util.List;

import dominio.Intereses;

public interface InteresesDao_interfaz {
	public boolean update(Intereses intereses_a_modificar);
	
	public List<Intereses> readAll();
	boolean delete(Intereses interes_a_eliminar);
	public Intereses obtenerInteres(int idInteres);
	public boolean insert(Intereses interes);
	public Intereses obtenerInteresByCuota(int cuota);
}
