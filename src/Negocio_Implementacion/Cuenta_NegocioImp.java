package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.CuentaDao_Implement;
import dominio.Cuenta;
import negocio.Cuenta_Negocio;

public class Cuenta_NegocioImp implements Cuenta_Negocio {
 
    CuentaDao_Implement cuenta = new CuentaDao_Implement(); 	
    
    
	@Override
	public boolean bajaCuenta(int numCuenta) {
		boolean estado= cuenta.delete(numCuenta);
		return estado;
	}
		public ArrayList<Cuenta> listarCuentas() {
			return  cuenta.readAll();
			
		}
		public ArrayList<Cuenta> filtrarActivas(){
			ArrayList<Cuenta> filtradas = new ArrayList<Cuenta>();
			ArrayList<Cuenta> total = cuenta.readAll();
			for(Cuenta acc : total) {
				if(acc.getEstado()) {
					filtradas.add(acc);
				}
			}
			return filtradas;
		}
}


