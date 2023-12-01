package dao;
import java.util.ArrayList;
import java.util.List;

import dominio.*;




public interface ClienteDao_Interfaz {
	public int insert(Cliente cliente);
	public boolean update(Cliente cliente_a_modificar);
	public boolean delete(Cliente cliente_a_eliminar);
	public List<Cliente> readAll();
	public Cliente obtenerCliente(int idCliente);
	
	public void bajaLogicaCliente(int id);
	List<Cliente> readAllActivos();
	Cliente obtenerClienteDni(String dni);
	public ArrayList<Cliente> obtenerClienteProvincia(String provincia);
	boolean ExisteDNI(String nuevoDNI, int idUsuario);

}
