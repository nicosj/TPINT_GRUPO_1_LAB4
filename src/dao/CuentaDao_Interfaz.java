package dao;

import java.util.List;
import dominio.Cuenta;

public interface CuentaDao_Interfaz {
	public boolean insert(Cuenta cuenta);
	public boolean update(Cuenta cuenta_a_modificar);
	public boolean delete(int numeroCuenta);
	public List<Cuenta> readAll();
	public Cuenta obtenerCuenta(int numero_cuenta);
	
}
