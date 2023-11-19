package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;

public class Cliente_NegocioImp {

    ClienteDao_Implement cliente = new ClienteDao_Implement(); 	
    
    
	public ArrayList<Cliente> listarClientes() {
		return  cliente.readAll();
		
	}
	
	public ArrayList<Cliente> listarAllGeneros(String provincia){
	 	 return cliente.obtenerClienteProvincia(provincia);
	}
	
	public ArrayList<Cliente> listarClientesFem(String provincia) {
		ArrayList<Cliente> lista = cliente.obtenerClienteProvincia(provincia);
		ArrayList<Cliente> filtradoFem = new ArrayList<Cliente>();
		
		for(Cliente cliente : lista) {
			if(cliente.getSexo().equalsIgnoreCase("F")) {
				filtradoFem.add(cliente);
			}
		}		
		
		return  filtradoFem;
		
	}
	public ArrayList<Cliente> listarClientesMasc(String provincia) {
		ArrayList<Cliente> lista = cliente.obtenerClienteProvincia(provincia);
		ArrayList<Cliente> filtradoMasc = new ArrayList<Cliente>();
		
		for(Cliente cliente : lista) {
			if(cliente.getSexo().equalsIgnoreCase("M")) {
				filtradoMasc.add(cliente);
			}
		}		
		
		return  filtradoMasc;
		
	}
}
