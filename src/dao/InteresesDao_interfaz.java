package dao;

import java.util.List;
import dominio.Intereses;

public interface InteresesDao_interfaz {

	public boolean insert(Intereses interes);
	public boolean update(Intereses interes_a_modificar);
	public boolean delete(Intereses interes_a_eliminar);
	public List<Intereses> readAll();
	public Intereses obtenerInteres(int idInteres);
}
