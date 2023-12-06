package Negocio_Implementacion;

import java.util.ArrayList;
import java.util.List;

import dao_Implement.CuentaDao_Implement;

import dominio.Cuenta;
import negocio.Cuenta_Negocio;

public class Cuenta_NegocioImp implements Cuenta_Negocio {
 
    CuentaDao_Implement cuenta = new CuentaDao_Implement(); 	
    
    
	@Override
	public boolean bajaCuenta(String numCuenta) {
		boolean estado= cuenta.delete(numCuenta);
		return estado;
	}
	
		public ArrayList<Cuenta> listarCuentas() {
			return  cuenta.readAll();
			
		}
		public Cuenta obtenerCuenta(String numCuenta) {
			Cuenta acc = new Cuenta();
			acc= cuenta.obtenerCuenta(numCuenta);
			return acc;
		}

		public boolean update(Cuenta acc) {
			return cuenta.update(acc);
		}
		
		public int getCuentaCountByClientId(String idCliente) {
	        int cuentaXId = cuenta.getCuentaCountByClientId(idCliente);
	        return cuentaXId;
		}
		
		
	    public boolean insert(Cuenta acc) {
	        boolean exitosa = cuenta.insert(acc);
	        return exitosa;
	    }
	    
	    public ArrayList<Cuenta> readAllByID(int clientId) {
	    	return cuenta.readAllByID(clientId);
	    }

		public ArrayList<Cuenta> obtenerCuentaByClientId(int idCliente) {
	        ArrayList<Cuenta> cuentas = cuenta.readAllByID(idCliente);
	        return cuentas;
		}
		
		public Cuenta obtenerCuentaCbu(String cbu) {
			Cuenta acc = new Cuenta();
			acc = cuenta.obtenerCuentaCbu(cbu);
			return acc;
		}
		public boolean ajusteCuenta(String acc, double monto) {
			boolean ajusto = cuenta.ajusteCuenta(acc, monto);
			return ajusto;
		}
		
}


