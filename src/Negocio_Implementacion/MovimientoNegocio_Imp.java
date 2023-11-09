package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.MovimientoDao;
import dominio.Movimiento;
import negocio.Movimiento_Negocio;

public class MovimientoNegocio_Imp implements Movimiento_Negocio{

	public ArrayList<Movimiento> listarFiltrado(String numeroCuenta){
		MovimientoDao movimientos = new MovimientoDao  ();
		ArrayList<Movimiento> listado = movimientos.readAll();
		ArrayList<Movimiento> filtrado = new ArrayList<Movimiento>();
		for(Movimiento mov : listado) {
			if(mov.getNumero_Cuenta().equals(numeroCuenta)) {
				filtrado.add(mov);
			}
		}
		return filtrado;
	}
}
