package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;

public class Cliente_NegocioImp {

    ClienteDao_Implement cliente = new ClienteDao_Implement(); 	
    
    
	public ArrayList<Cliente> listarTodosClientes() {
		return  cliente.readAll();
		
	}
	
	public ArrayList<Cliente> listarClientesFem() {
		ArrayList<Cliente> lista = cliente.readAll();
		ArrayList<Cliente> filtradoFem = new ArrayList<Cliente>();
		
		for(Cliente cliente : lista) {
			if(cliente.getSexo().equals("F")) {
				filtradoFem.add(cliente);
			}
		}		
		
		return  filtradoFem;
		
	}
	public ArrayList<Cliente> listarClientesMasc() {
		ArrayList<Cliente> lista = cliente.readAll();
		ArrayList<Cliente> filtradoMasc = new ArrayList<Cliente>();
		
		for(Cliente cliente : lista) {
			if(cliente.getSexo().equals("M")) {
				filtradoMasc.add(cliente);
			}
		}		
		
		return  filtradoMasc;
		
	}
}
