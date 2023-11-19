package negocio;

import java.util.ArrayList;

import dominio.Cliente;

public interface Cliente_Negocio {
	public ArrayList<Cliente> listarTodosClientes();
	public ArrayList<Cliente> listarClientesFem();
	public ArrayList<Cliente> listarClientesMasc();
}
