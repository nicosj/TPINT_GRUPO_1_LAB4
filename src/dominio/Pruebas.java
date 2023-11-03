package dominio;
import java.util.ArrayList;

import Negocio_Implementacion.Cuenta_NegocioImp;
import dao_Implement.CuentaDao_Implement;

public class Pruebas {


	    public static void main(String[] args) {
	        CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
	        ArrayList<Cuenta> cuentas = cuentaDao.readAll();
	        Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
	        ArrayList<Cuenta> listaCuentas = negocio.listarCuentas(); 

	        for (Cuenta cuenta : listaCuentas) {
	            System.out.println("Número de cuenta: " + cuenta.getNumero_Cuenta());
	            System.out.println("ID del cliente: " + cuenta.getIdCliente());
	            System.out.println("Tipo de cuenta: " + cuenta.getTipo_Cuenta());
	            System.out.println("CBU: " + cuenta.getCBU());
	            System.out.println("Saldo: " + cuenta.getSaldo());
	            System.out.println("Estado: " + cuenta.getEstado());
	            System.out.println("Fecha de creación: " + cuenta.getFecha_Creacion());
	            System.out.println();
	        }
	        
	        
	    }
	}
