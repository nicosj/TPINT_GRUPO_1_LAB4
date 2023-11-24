package negocio;

import java.util.ArrayList;

import dominio.Prestamo;

public interface Prestamo_Negocio {

	public ArrayList<Prestamo> listarPrestamosFiltrado (double montoMax);
}
