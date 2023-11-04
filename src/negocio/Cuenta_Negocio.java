package negocio;

import java.util.ArrayList;

import dominio.Cuenta;

public interface Cuenta_Negocio {

	public boolean bajaCuenta(int numCuenta);
	public ArrayList<Cuenta> listarCuentas();
}
