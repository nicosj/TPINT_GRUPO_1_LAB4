package dao;
import java.util.List;

import dominio.*;




public interface ClienteDao_Interfaz {
	public boolean insert(Cliente cliente);
	public boolean update(Cliente cliente_a_modificar);
	public boolean delete(Cliente cliente_a_eliminar);
	public List<Cliente> readAll();
	public Cliente obtenerPersona(int idCliente);

}
