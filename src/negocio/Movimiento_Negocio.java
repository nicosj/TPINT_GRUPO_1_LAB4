package negocio;

import java.util.ArrayList;

import dominio.Movimiento;

public interface Movimiento_Negocio {

	public ArrayList<Movimiento> listarFiltrado(String numeroCuenta);
}
