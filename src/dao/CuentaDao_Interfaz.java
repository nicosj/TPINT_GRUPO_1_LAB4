package dao;

import java.util.ArrayList;
import java.util.List;
import dominio.Cuenta;

public interface CuentaDao_Interfaz {
	public boolean insert(Cuenta cuenta);
	public boolean update(Cuenta cuenta_a_modificar);
	
	public List<Cuenta> readAll();
	public Cuenta obtenerCuenta(String numero_cuenta);
	public Cuenta obtenerCuentaCbu(String cbu);
	boolean delete(String numeroCuenta);
	 ArrayList<Cuenta> readAllByID(int clientId);
}
