package negocio;

import java.util.ArrayList;
import java.util.List;

import dominio.Cuenta;

public interface Cuenta_Negocio {

	public boolean bajaCuenta(String numCuenta);
	public ArrayList<Cuenta> listarCuentas();
	public Cuenta obtenerCuenta(String numCuenta);
	public boolean update(Cuenta acc);	
	public int getCuentaCountByClientId(String idCliente);	
    public boolean insert(Cuenta acc);    
    public ArrayList<Cuenta> readAllByID(int clientId);
	public ArrayList<Cuenta> obtenerCuentaByClientId(int idCliente);
	public Cuenta obtenerCuentaCbu(String cbu);
	public boolean ajusteCuenta(String acc, double monto);
}