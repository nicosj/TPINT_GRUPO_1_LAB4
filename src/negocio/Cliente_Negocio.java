package negocio;

import java.util.ArrayList;

import dominio.Cliente;

public interface Cliente_Negocio {
	public ArrayList<Cliente> listarAllGeneros(String provincia);
	public ArrayList<Cliente> listarClientesFem(String provincia);
	public ArrayList<Cliente> listarClientesMasc(String provincia);
    public boolean existeDNI(String nuevoDNI, int idUsuario);
    public int insertarCliente(Cliente cli);    
    public Cliente obtenerCliente(int idCliente);    
    public Cliente obtenerClientePorDNI(String dni);   
    public void bajaLogicaCliente(int id);
	public boolean update(Cliente cliente2);
}
