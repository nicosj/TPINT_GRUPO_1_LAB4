package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.CuentaDao_Implement;
import dominio.Cuenta;
import negocio.Cuenta_Negocio;

public class Cuenta_NegocioImp implements Cuenta_Negocio {
 
    CuentaDao_Implement cuenta = new CuentaDao_Implement(); 	
    
    
	@Override
	public boolean bajaCuenta(Cuenta cuenta_a_eliminar) {
		boolean estado=false;
		if(cuenta_a_eliminar.getNumero_Cuenta().trim().length()>0 && cuenta_a_eliminar.getEstado())
		{
			estado = cuenta.delete(cuenta_a_eliminar);
		}
		return estado;
	}
		public ArrayList<Cuenta> listarCuentas() {
			return cuenta.readAll();
		}
}


