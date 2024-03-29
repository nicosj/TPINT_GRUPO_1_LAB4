package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.PrestamoDao_Implement;
import dominio.Cliente;
import dominio.Prestamo;
import negocio.Prestamo_Negocio;

public class Prestamo_NegocioImp implements Prestamo_Negocio {

	
	 PrestamoDao_Implement prestamo = new PrestamoDao_Implement();
	 
	@Override
	public ArrayList<Prestamo> listarPrestamosFiltrado(double montoMax) {
		PrestamoDao_Implement prestamoDao = new PrestamoDao_Implement();
		ArrayList<Prestamo> listado = prestamoDao.readAllMonto(montoMax);		
		return listado;
	}

	public boolean insert(Prestamo pres) {
		boolean inserto = prestamo.insert(pres);
		return inserto;
	}
	
	  public void aprobarPrestamo(int idPrestamo) {
	        prestamo.aprobarPrestamo(idPrestamo);
	    }

	    public void rechazarPrestamo(int idPrestamo) {
	        prestamo.rechazarPrestamo(idPrestamo);
	    }
	    
	    
		public ArrayList<Prestamo> readAll() {
			return  prestamo.readAll();	
		}
		
	    public ArrayList<Prestamo> readAllByCuenta(String cuenta) {
	        ArrayList<Prestamo> listaPrestamos = prestamo.readAllByCuenta(cuenta);

	        for (Prestamo prestamo : listaPrestamos) {
	            System.out.println("Prestamo: " + prestamo.toString());
	        }

	        return listaPrestamos;
	    }
}
