package dao;

import java.util.List;

import dominio.Usuario;

public interface UsuarioDao_Interfaz {
	public boolean insert(Usuario usuario);
	public boolean update(Usuario usuario, int client);
	public boolean delete(int id);
	public List<Usuario> readAll();
	public Usuario readOnce(int id);
	public Usuario login(Usuario usuario);
	boolean verificarNombreUsuario(String usuario,int idUsuario);
	
	
	
}
