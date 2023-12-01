package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.MovimientoDao;
import dominio.Movimiento;
import negocio.Movimiento_Negocio;

public class MovimientoNegocio_Imp implements Movimiento_Negocio{

	private MovimientoDao movimientos= new MovimientoDao();
	
	public ArrayList<Movimiento> listarFiltrado(String idCliente){
		ArrayList<Movimiento> listado = movimientos.obtenerMovimientos(idCliente);
		for(Movimiento mov : listado) {
			System.out.println(mov.toString());
		}
		return listado;
	}
	
	public ArrayList<Movimiento> readAllMovimientos(String cuentas){
		return movimientos.readAllMovimientos(cuentas);
	}
	
	public boolean insert(Movimiento mov) {
		System.out.println(mov.toString());
		return movimientos.insert(mov);
	}
}
