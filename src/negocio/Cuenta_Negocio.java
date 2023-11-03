package negocio;

import java.util.ArrayList;

import dominio.Cuenta;

public interface Cuenta_Negocio {

	public boolean bajaCuenta(Cuenta cuenta_a_eliminar);
	public ArrayList<Cuenta> listarCuentas();
}
