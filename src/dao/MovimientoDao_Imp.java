package dao;

import java.util.List;

import dominio.Movimiento;

public interface MovimientoDao_Imp {
	public boolean insert(Movimiento movimiento);
	public boolean update(Movimiento movimiento_a_modificar);	
	public List<Movimiento> readAll();
	public Movimiento obtenerMovimiento(String numero_Cuenta);
}




