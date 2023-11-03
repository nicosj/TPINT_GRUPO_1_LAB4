package dominio;

public class Usuario {
	private int IdUsuario;
	private String Usuario;
	private String Clave;
	private int tipoUsuario;
	private int IdCliente;

	public Usuario() {

	}
	public Usuario(int idUsuario, String Usuario, String Clave, int tipoUsuario, int IdCliente){
		this.IdUsuario = idUsuario;
		this.Usuario = Usuario;
		this.Clave = Clave;
		this.tipoUsuario = tipoUsuario;
		this.IdCliente = IdCliente;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}

}
