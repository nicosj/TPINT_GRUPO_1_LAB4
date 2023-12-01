package negocio;

import java.util.ArrayList;

import dominio.Usuario;

public interface Usuario_Negocio {
	public ArrayList<Usuario> listarUsuarios();
	
	public boolean verificarNombreUsuario(String nuevoUsuario, int idUsuario);
	public void insertarUsuario(Usuario usuario);
	public boolean update(Usuario usuario, int idCliente);
	public Usuario login(Usuario usuario);
}
