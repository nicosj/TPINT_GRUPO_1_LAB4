package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.PrestamoDao_Implement;
import dominio.Prestamo;
import negocio.Prestamo_Negocio;

public class Prestamo_NegocioImp implements Prestamo_Negocio {

	@Override
	public ArrayList<Prestamo> listarPrestamosFiltrado(double montoMax) {
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> listado = prestamoDao.readAllMonto(montoMax);		
		return listado;
	}

	
}
