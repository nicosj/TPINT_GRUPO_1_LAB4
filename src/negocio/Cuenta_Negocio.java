package negocio;

import java.util.ArrayList;

import dominio.Cuenta;

public interface Cuenta_Negocio {

	public boolean bajaCuenta(String numCuenta);
	public ArrayList<Cuenta> listarCuentas();
	
}
