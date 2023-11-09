package dominio;

import java.util.ArrayList;

import Negocio_Implementacion.MovimientoNegocio_Imp;
import dao_Implement.MovimientoDao;

public class Pruebas {

	public static void main(String[] args) {
		
		
	
			MovimientoDao movimientos = new MovimientoDao  ();
			ArrayList<Movimiento> listado = movimientos.readAll();
			ArrayList<Movimiento> filtrado = new ArrayList<Movimiento>();
			for(Movimiento mov : listado) {
				if(mov.getNumero_Cuenta().equals("12")) {
					filtrado.add(mov);
				}
			}
	
    	for(Movimiento mov : filtrado) {
    		System.out.println(mov.toString());
    	}

	}

}
