package Negocio_Implementacion;

import java.util.ArrayList;

import dao_Implement.ClienteDao_Implement;
import dominio.Cliente;
import negocio.Cliente_Negocio;

public class Cliente_NegocioImp implements Cliente_Negocio {

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
	
	
    public boolean existeDNI(String nuevoDNI, int idUsuario) {
        boolean repetido = cliente.ExisteDNI(nuevoDNI, idUsuario);
        return repetido;
    }
	
    public int insertarCliente(Cliente cli) {
        int nuevoID = cliente.insert(cli);
        return nuevoID;
    }
    
    public Cliente obtenerCliente(int idCliente) {
    	Cliente cli = cliente.obtenerCliente(idCliente);
    	return cli;
    }
    
    public Cliente obtenerClientePorDNI(String dni) {
        Cliente cli = cliente.obtenerClienteDni(dni);
        return cli;
    }
    
    public void bajaLogicaCliente(int id) {
    	cliente.bajaLogicaCliente(id);	
    }

	public boolean update(Cliente cliente2) {
		boolean actualizo = cliente.update(cliente2);
		return actualizo;
	}

}
