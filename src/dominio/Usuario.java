package dominio;

public class Usuario {
	private int IdUsuario;
	private String usuario;
	private String Clave;
	private int tipoUsuario;
	private int IdCliente;

	public Usuario() {

	}
	public Usuario(int idUsuario, String usuario, String Clave, int tipoUsuario, int IdCliente){
		this.IdUsuario = idUsuario;
		this.usuario = usuario;
		this.Clave = Clave;
		this.tipoUsuario = tipoUsuario;
		this.IdCliente = IdCliente;
	}
	public Usuario(String usuario, String Clave) {
		this.usuario = usuario;
		this.Clave = Clave;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		usuario = usuario;
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
