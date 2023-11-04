package dominio;
import java.util.ArrayList;

import Negocio_Implementacion.Cuenta_NegocioImp;

public class Pruebas {


	    public static void main(String[] args) {
	      
	        Cuenta_NegocioImp negocio = new Cuenta_NegocioImp();
	        ArrayList<Cuenta> listaCuentas = negocio.filtrarActivas();
	        if(listaCuentas == null) {
	        	System.out.println("Lista vacia");
	        }	       	       
	    }
	}
