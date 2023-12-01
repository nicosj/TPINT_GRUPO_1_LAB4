package Negocio_Implementacion;


import java.util.ArrayList;


import dao_Implement.UsuarioDao_Implement;
import dominio.Usuario;
import negocio.Usuario_Negocio;

public class Usuario_NegocioImp implements Usuario_Negocio {

	
	
    UsuarioDao_Implement us = new UsuarioDao_Implement(); 	
    
    
	public ArrayList<Usuario> listarUsuarios() {
		return  us.readAll();
	}
	
	
	public boolean verificarNombreUsuario(String nuevoUsuario, int idUsuario) {
		 boolean existeUsuario = us.verificarNombreUsuario(nuevoUsuario, idUsuario);
		 return existeUsuario;
	}
	
	public void insertarUsuario(Usuario usuario) {
		us.insert(usuario);
	}
	
	public boolean update(Usuario usuario, int idCliente) {
		boolean actualizo = us.update(usuario, idCliente);
		return actualizo;	
	}
	public Usuario login(Usuario usuario) {
		// Mirar usuario
	    Usuario aux = new Usuario();
		aux=us.login(usuario);
		return aux;
	}
}
