package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.MovimientoDao;
import dominio.Movimiento;
import negocio.Movimiento_Negocio;

public class MovimientoNegocio_Imp implements Movimiento_Negocio{

	public ArrayList<Movimiento> listarFiltrado(String idCliente){
		
		MovimientoDao movimientos = new MovimientoDao  ();
		ArrayList<Movimiento> listado = movimientos.obtenerMovimientos(idCliente);
		for(Movimiento mov : listado) {
			System.out.println(mov.toString());
		}
		return listado;
	}
}
